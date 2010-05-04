package com.redhat.rhn.harness.baseInterface;

public interface ISatelliteProxy {
	
	/**
	 * Installs a sat proxy, prereqs must be taken care of prior to running this method
	 * 
	 * @param enable_monitoring
	 * @param proxyVersion TODO
	 */
	public void installProxy(boolean enable_monitoring, String proxyVersion);
	
	/**
	 * Checks if Proxy is installed
	 * @param system TODO
	 * @return
	 */
	public boolean checkIfProxyInstalled(String system);

}
