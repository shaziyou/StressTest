package com.win.jmeter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.jmeter.engine.DistributedRunner;
import org.apache.jmeter.util.JMeterUtils;

import com.win.jmeter.entity.StressTestItem;

public class StressTestContext {

	private static StressTestContext instance = new StressTestContext();

	private StressTestContext() {
	}

	public static StressTestContext getInstance() {
		return instance;
	}

	private DistributedRunner runner = new DistributedRunner();
	private List<String> remoteHosts;
	private BlockingQueue<StressTestItem> waitQueue = new LinkedBlockingQueue<>();
	private volatile boolean isTesting = false;

	public boolean isWaitQueueEntity() {
		return this.waitQueue.isEmpty();
	}

	public void addTestItem(StressTestItem item) {
		this.waitQueue.add(item);
	}

	public StressTestItem getTestItem() throws InterruptedException {
		return this.waitQueue.take();
	}

	public DistributedRunner getRunner() {
		return runner;
	}

	public List<String> getRemoteHosts() {
		// TODO 多个测试同时运行时，考虑并发
		if (null == remoteHosts) {
			remoteHosts = new ArrayList<>();
			String remoteHostsString = JMeterUtils.getPropDefault("remote_hosts", "127.0.0.1");
			StringTokenizer st = new StringTokenizer(remoteHostsString, ",");
			while (st.hasMoreElements()) {
				remoteHosts.add((String) st.nextElement());
			}
		}
		return remoteHosts;
	}

	public boolean isTesting() {
		return this.isTesting;
	}

	public void setIsTesting(boolean isTesting) {
		this.isTesting = isTesting;
	}

}
