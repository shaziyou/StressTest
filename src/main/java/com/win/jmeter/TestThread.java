package com.win.jmeter;

public class TestThread {
	public static void main(String[] args) {
		new Thread("TestThread"){
			@Override
			public void run() {
				new StressMain().test();
			}
		}.start();
	}
}
