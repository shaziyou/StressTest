package com.win.jmeter;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.DistributedRunner;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.RemoteThreadsListenerTestElement;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

import com.win.jmeter.collector.MyResultCollector;

public class StressMain {

	static {
		JMeterUtils.loadJMeterProperties("jmeter.properties");
	}
	public static DistributedRunner distributedRunner = new DistributedRunner();

	public void test() {
		// System.setProperty("javax.net.ssl.trustStore",
		// System.getProperty("user.dir") + File.separator + "wso2carbon.jks");
		// System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");

		distributedRunner.init(getRemoteHosts(), getTree(100));
		distributedRunner.start();
		// distributedRunner.stop();
		// MyResultCollector.showThreadInfo();
	}

	public static HashTree getTree(int threadNum) {
		HashTree tree = new HashTree();
		TestPlan testPlan = new TestPlan("Stress Test");

		HTTPSamplerProxy httpSamplerProxy = new HTTPSamplerProxy();
		// httpSampler.setProtocol("https"); // 仅https
		httpSamplerProxy.setDomain("localhost");
		httpSamplerProxy.setFollowRedirects(true);
		httpSamplerProxy.setUseKeepAlive(true);
		httpSamplerProxy.setPort(8087);
		httpSamplerProxy.setPath("/index");
		httpSamplerProxy.setMethod("GET");
		//httpSampler.setContentEncoding("");
//		httpSampler.setArguments(new Arguments());
//		HeaderManager =

		LoopController loopCtrl = new LoopController();
		loopCtrl.setLoops(1000);
		ResultCollector result = new MyResultCollector();

		org.apache.jmeter.threads.ThreadGroup threadGroup = new org.apache.jmeter.threads.ThreadGroup();
		threadGroup.setNumThreads(threadNum);
//		threadGroup.setRampUp(1);
		threadGroup.setRampUp(0);
		threadGroup.setSamplerController(loopCtrl);
//		threadGroup.addTestElement(httpSamplerProxy);
//		threadGroup.addTestElement(result);

		tree.add(testPlan);
		HashTree testPlanTree = tree.getTree(testPlan);
		testPlanTree.add(threadGroup);
		testPlanTree.add(new RemoteThreadsListenerTestElement());

		HashTree threadGroupTree = testPlanTree.getTree(threadGroup);

		threadGroupTree.add(httpSamplerProxy);
//		threadGroupTree.add(loopCtrl);
		threadGroupTree.add(result);

		return tree;
	}

	public static List<String> getRemoteHosts() {
		String remoteHostsString = JMeterUtils.getPropDefault("remote_hosts", "127.0.0.1");
		StringTokenizer st = new StringTokenizer(remoteHostsString, ",");
		List<String> list = new LinkedList<>();
		while (st.hasMoreElements()) {
			list.add((String) st.nextElement());
		}
		return list;
	}

}
