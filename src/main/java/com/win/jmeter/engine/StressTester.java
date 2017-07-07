package com.win.jmeter.engine;

public class StressTester {
	private static StressTester instance = new StressTester();

	private StressTester() {
	}

	public static StressTester getInstance() {
		return instance;
	}
}
