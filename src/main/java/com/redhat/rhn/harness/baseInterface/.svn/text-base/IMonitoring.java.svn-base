package com.redhat.rhn.harness.baseInterface;

public interface IMonitoring {

	/**
	 * Enables Monitoring, does NOT restart tomcat(see testprep)
	 * @param openAndLogin
	 */
	public void enableMonitoring(boolean openAndLogin);
	
	/**
	 * push scout config
	 * @param openAndLogin
	 */
	public void pushMonitoringScoutConfig(boolean openAndLogin,boolean waitForComplete);
	
	/**
	 * Create a Monitoring probe suite
	 * @param openAndLogin
	 * @param probeName
	 */
	public void monitoring_createProbeSuite(boolean openAndLogin, String probeName);
	
	/**
	 * Create a probe for a given suite
	 * @param openAndLogin
	 * @param probeSuite
	 */
	public void monitoring_addProbesToSuite_LinuxMemory(boolean openAndLogin,String probeSuite);
	
	/**
	 * Add a system to a given monitoring probe suite
	 * @param openAndLogin
	 * @param probeSuite
	 * @param system
	 */
	public void monitoring_addSystemToProbeSuite(boolean openAndLogin,String probeSuite,String system);
	
	/**
	 * Checks the status of monitoring for a given probe, for a given system
	 * @param system
	 * @param probe
	 * @param status1
	 * @param status2 TODO
	 */
	public void monitoring_checkProbeStatus(String system, String probe, String status1, String status2);
	
	/**
	 * Add a Network Services probe to suite
	 * @param openAndLogin
	 * @param probeSuite
	 */
	public void monitoring_addProbesToSuite_NetworkPing(boolean openAndLogin,String probeSuite);

	/**
	 * Deletes all Monitoring probe suites
	 * @param openAndLogin
	 */
	public void monitoring_delete_ProbesToSuite(boolean openAndLogin);
	
	/**
	 * Enables Monitoring Scout
	 * @param openAndLogin
	 */
	public void enableMonitoringScout(boolean openAndLogin);
	
	/**
	 * Gets the monitoring scouts public ssh key
	 * @param openAndLogin
	 * @param proxy
	 * @return
	 */
	public String monitoring_getSSHPublicKey(boolean openAndLogin,boolean proxy);
	
}
