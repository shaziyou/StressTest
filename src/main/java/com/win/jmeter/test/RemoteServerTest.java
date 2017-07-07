package com.win.jmeter.test;

import java.rmi.RemoteException;

import org.apache.jmeter.engine.RemoteJMeterEngineImpl;
import org.apache.jmeter.util.JMeterUtils;

public class RemoteServerTest {
	public static void main(String[] args) {
//		JMeterUtils.setJMeterHome(System.getProperty("user.dir"));
		JMeterUtils.loadJMeterProperties("conf/jmeter.properties");
//		System.setProperty("java.rmi.server.hostname", "192.168.114.1");
//		HttpMirrorServer.main(new String[]{});
		try {
			RemoteJMeterEngineImpl.startServer(8088);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
