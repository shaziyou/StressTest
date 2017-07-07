package com.win.jmeter.collector;

import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.samplers.SampleEvent;
import org.apache.jmeter.samplers.SampleResult;

import com.win.jmeter.StressMain;

public class MyResultCollector extends ResultCollector {

	private static final long serialVersionUID = 2246427490051735945L;
	private static int num = 0;
	private long startTime;

	@Override
	public void sampleOccurred(SampleEvent event) {
		super.sampleOccurred(event);
		SampleResult result = event.getResult();
		System.out.println(result.getThreadName() + "   " + result.getEndTime() + "   -->" + result.getResponseCode());
		// result.connectEnd();
		// result.
		num++;
	}

	@Override
	public void testStarted(String host) {
		super.testStarted(host);
		startTime = System.currentTimeMillis();
	}

	@Override
	public void testEnded(String host) {
		long endTime = System.currentTimeMillis();
		super.testEnded(host);
		System.out.println("---> " + num);
		System.out.println("Start Time : " + startTime);
		System.out.println("End Time : " + endTime);
		System.out.println("Time : " + (endTime - startTime));
		showThreadInfo();
		if (num < 60) {
			StressMain.distributedRunner.stop();
			StressMain.distributedRunner.init(StressMain.getRemoteHosts(), StressMain.getTree(30));
			StressMain.distributedRunner.start();
		}
	}

	@Override
	public void sampleStopped(SampleEvent e) {
		super.sampleStopped(e);
	}

	public static void showThreadInfo() {
		int i = 0;
		for (Thread thread : Thread.getAllStackTraces().keySet()) {
			i++;
			System.out.println(thread.getName() + " : " + thread.isAlive());
			// for (StackTraceElement stack : thread.getStackTrace()) {
			// System.out.println(
			// "---> " + stack.getClassName() + " : " + stack.getMethodName() +
			// " : " + stack.getLineNumber());
			// }
		}
		System.out.println("Thread Num : " + i);
	}

}
