package com.win.jmeter.entity;

import org.apache.jorphan.collections.HashTree;

public abstract class StressTestItem {

	private String apiName;
	private String version;
	private String provider;

	private String protocol = "http";
	private String domain;
	private int port = 80;
	private String path = "/";
	private String method;
	private boolean followRedirects;
	private boolean useKeepAlive;

	private int loopNum;
	private int concurrentNum; // threadNum while RampUp is 0

	public abstract HashTree generateHashTree();

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public boolean isFollowRedirects() {
		return followRedirects;
	}

	public void setFollowRedirects(boolean followRedirects) {
		this.followRedirects = followRedirects;
	}

	public boolean isUseKeepAlive() {
		return useKeepAlive;
	}

	public void setUseKeepAlive(boolean useKeepAlive) {
		this.useKeepAlive = useKeepAlive;
	}

	public int getLoopNum() {
		return loopNum;
	}

	public void setLoopNum(int loopNum) {
		this.loopNum = loopNum;
	}

	public int getConcurrentNum() {
		return concurrentNum;
	}

	public void setConcurrentNum(int concurrentNum) {
		this.concurrentNum = concurrentNum;
	}

}
