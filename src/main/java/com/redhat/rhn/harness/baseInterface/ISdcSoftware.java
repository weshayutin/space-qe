package com.redhat.rhn.harness.baseInterface;

public interface ISdcSoftware {

	
	//public void prepUp2dateFile(String system);
	
	//public void prepRhnYumPlugin(String system);
	
	//public void rhnPluginFix(String system);
	
	/**
	 * Creates a snapshot profile
	 * Found under provisioning, can be used to roll back the system
	 */
	public void takeSnapShot(String system, boolean openAndLogin);
	
	/**
	 * Installs the specified package (if available) on the specified system
	 * @param system
	 * @param pkg
	 */
	public void installPackage(String system, String pkg);

	/**
	 * Installs Errata through sdc software/packages/upgrade
	 * @param system
	 * @param errataName
	 * @param latestPackage TODO
	 * @param installedPackage TODO
	 * @param packageShortName TODO
	 */
	public void installErrata(String system, String errataName, String latestPackage, String installedPackage, String packageShortName);
	/**
	 * Removes the specified package (if installed) on the specified system
	 * @param system
	 * @param pkg
	 */
	public void removePackage(String system, String pkg);
	
	/**
	 * Verifies the package is listed in RHN
	 * It also runs rpm -q on the specified package on the specified system
	 * @param system
	 * @param pkg
	 * @return  only returns true if true in RHN and on local system
	 */
	public boolean listPackage(String system, String pkg);
	
	/**
	 * Uses default snapshot profile "automationBase" and rolls back system 
	 * @param system
	 * @param openAndLogin
	 */
	public void rollBackPackages(String system, boolean openAndLogin);
	
	/**
	 * Selects the check box for a particular pkg file name
	 * @param file
	 */
	public void select_File_Checkbox(String file);

	/**
	 * Change base channel of a system
	 * @param system
	 * @param channel
	 */
	public void changeBaseChannel(String system, String channel);
	
	/**
	 * Alter channel subscriptions
	 * 
	 * @param openAndLogin
	 * @param system
	 * @param channel : this is really selected by the order of the channel
	 */
	public void alterBaseChannelSubscriptions(boolean openAndLogin, String system, String channel);
	
	
}
