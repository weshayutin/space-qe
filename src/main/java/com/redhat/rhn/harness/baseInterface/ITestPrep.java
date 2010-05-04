package com.redhat.rhn.harness.baseInterface;


public interface ITestPrep {
	/**
	 * Removes rhncfg rpm files from a system
	 * @param system
	 */
	public void removeRHNConfigFilesFromSystem(String system);

	/**
	 * Runs wget from the system that is executing the script
	 * @param fullUrl
	 */

	/**
	 * runs killall -9 firefox-bin on selenium server.
	 * ssh keys must be shared to the selenium server for this to work
	 */
	public void command_kill_Firefox_OnSeleniumServer();

	/**
	 * Runs remote rpm commands
	 * @param arguments
	 * @param system
	 * @param showlogs
	 */
	public void command_rpm(String arguments,String system, boolean showlogs);

	public void command_wget(String fullUrl);

	/**
	 * sets grub default boot to 0
	 * @param system
	 * @param showlogs
	 */
	public void command_GrubDefaultBoot0(String system,boolean showlogs);

	/**
	 * Submits a command to the system using the "at" utility, the command is read from /tmp/autoCommand
	 * @param system
	 * @param command
	 * @param showlogs
	 */
	public void command_runCommandWithAT(String system,String command, boolean showlogs); 
	
	/**
	 * Runs rhn_check on specified system using the "at" command
	 * at now -f /tmp/file (where file contains "/usr/sbin/rhn_check -vvv")
	 * This command will be skipped if osad is enabled
	 * @param system
	 * @param showlogs
	 */
	public void command_runRhnCheckWithAT(String system, boolean showlogs);

	/**
	 * Cancels a current shutdown command w/ shutdown -c
	 * This comand will be skipped if osad is enabled
	 * @param system
	 * @param showlogs
	 */
	public void command_cancelShutdown(String system, boolean showlogs);

	/**
	 * Reboots a system
	 * This command will be skipped if osad is enabled
	 * @param system
	 * @param showlogs
	 */
	public void command_rebootSystem(String system, boolean showlogs);

	/**
	 * Run rhn_check w/o backgrounding the process. This is not suggested as your running the command over ssh
	 * rhn_check -vvv
	 * @param system
	 * @param showlogs
	 */
	public void command_runRhnCheckInForeground(String system, boolean showlogs);

	/**
	 * Rhn Rhn_check, rhn_check &
	 * @param system
	 * @param showlogs
	 */
	public void command_runRhnCheckInBackGround(String system, boolean showlogs);

	/**
	 * Runs a user specified command over ssh to remote system
	 * @param command
	 * @param arguments
	 * @param system
	 * @param showlogs
	 */
	public void command_generic(String command, String arguments,String system, boolean showlogs);

	/**
	 * Runs a user specified command over ssh to remote system for specified user
	 * @param command
	 * @param arguments
	 * @param system
	 * @param user
	 * @param showlogs
	 */
	public void command_generic(String command, String arguments,String system, String user, boolean showlogs);

	/**
	 * A very basic command to local machine (really only for test purposes)
	 * @param command
	 * @param arguments
	 * @param system
	 * @param showlogs
	 */
	public void command_test(String command, String arguments,String system, boolean showlogs);

	/**
	 * Runs up2date --nox plus what ever arguments you pass in or no arguments
	 * @param arguments
	 * @param system
	 * @param showlogs
	 */
	public void command_up2date(String arguments,String system, boolean showlogs);

	/**
	 * Runs yum plus any arguments you pass in
	 * @param arguments
	 * @param system
	 * @param showlogs
	 */
	public void command_yum(String arguments,String system, boolean showlogs);

	/**
	 * removes local ssh known_hosts file
	 * @param showlogs
	 */
	public void command_remove_localKnownHosts(boolean showlogs);

	/**
	 * restarts rhn-satellite service
	 * BECAREFULL, but throw auto into a loop for 4 minutes to allow restart
	 * @param system
	 * @param showlogs
	 * @param restartTomcatOnly TODO
	 * @param restartOracle TODO
	 * @param restartALL TODO
	 */
	public void command_RestartSatellite(String system, boolean showlogs, boolean restartTomcatOnly, boolean restartOracle, boolean restartALL);

	/**
	 * Returns the contents of /etc/redhat-release
	 * @param system
	 * @return
	 */
	public String getRedHat_Release(String system);

	/**
	 * Sets GPG=0 on rhel4 and rhel5 remote systems
	 * @param system
	 * @param showlogs
	 */
	public void command_turn_On_Off_GPG_Check(String system,boolean showlogs);

	/**
	 * Runs xm list and returns output to log
	 * @param system
	 * @param showlogs
	 */
	public void command_xmList(String system, boolean showlogs);
	/**
	 * Imports rpm keys for rhel 4 and 5
	 * @param system
	 * @param showlogs
	 */
	public void command_importRPMKeys(String system, boolean showlogs);
	/**
	 * Runs default tail command against specified file
	 * @param system
	 * @param logName
	 */
	public void command_tailLog(String system,String logName);

	/**
	 * Removes ~/.ssh/known_hosts to eliminate ssh errors.
	 * @param system
	 */
	public void command_RemoveSSHKnownHosts(String system);

	/**
	 * Removes system profile from RHN
	 * @param system
	 * @param login  boolean login or skip login if already logged in.
	 */
	public void unregisterSystem(String system, boolean login);

	/**
	 * Removes profiles that match a regular expression of systems name
	 * To Remove all profiles, use removeAllSystemProfiles
	 * @param system
	 * @param openAndLogin TODO
	 */
	public void unregisterAllProfilesOfSystem(String system, boolean openAndLogin);

	/**
	 * Creates multiple profiles of one real system based on the system name incremented by number
	 * @param system
	 * @param testSystemRootName
	 * @param numberOfSystems
	 * @param command, can use HarnessConfiguration.RHN_REG_CMD OR HarnessConfiguration.RHN_SAT_REG_CMD
	 * @param deleteOld
	 * @param hosted
	 * @param openAndLogin TODO
	 */
	public void registerMultipleProfiles(String system,String testSystemRootName, int numberOfSystems,String command, boolean deleteOld, boolean hosted, boolean openAndLogin);

	/**
	 * Registers a system to RHN, with a generic user defined command
	 * This generic command can be useful with Activation keys
	 * @param system
	 * @param command
	 * @param successful
	 * @param openAndLogin TODO
	 */
	public void registerSystem(String system, String command, boolean successful, boolean openAndLogin);

	/**
	 * Registers a system to RHN with the traditional parameters
	 * @param system
	 * @param profileName
	 * @param additionalCommand
	 * @param username
	 * @param password
	 * @param successful
	 * @param login
	 */
	public void registerSystemCustom(String system, String profileName, String additionalCommand,
			String username,String password, boolean successful, boolean login);

	/**
	 * Registers a system by running command line only, and does not check the webui
	 * @param system
	 * @param profileName
	 * @param additionalCommand
	 * @param username
	 * @param password
	 */
	public void registerSystemNOGUI(String system, String profileName, String additionalCommand,
			String username,String password);

	/**
	 * Removes all system profiles listed in RHN
	 * @param openAndLogin TODO
	 */
	public void removeAllSystemProfiles(boolean openAndLogin);

	/**
	 * Enables provisioning for the specified system
	 * @param system
	 * @param openSystemPage
	 */
	public void enableProvisioning(String system, boolean openSystemPage);

	/**
	 * Enables Config Mang on a system
	 * @param system
	 * @param hosted
	 * @param openAndLogin TODO
	 */
	public void enableRHNConfigManag(String system, boolean hosted, boolean openAndLogin);

	/**
	 * Enables the Virtualization entitlement for the specified system
	 * @param system
	 * @param openSystemPage
	 */
	public void enableVirtualizationPlatform(String system, boolean openSystemPage);

	/**
	 * Modifies the pagination setting or items per page in RHN
	 * @param entriesPerPage
	 * @param openAndLogin TODO
	 */
	public void changePaginationSettings(String entriesPerPage, boolean openAndLogin);

	/**
	 * Returns the RHEL level 4 or 5 for the specified system
	 * @param system
	 * @return
	 */
	public int checkRHELVersion(String system);

	/**
	 * Disables the pkgskiplist setting in up2date client settings for RHEL 3,4
	 * @param system
	 * @param showlogs
	 */
	public void disablePkgSkipListForUp2date(String system, boolean showlogs);

	/**
	 * Modifies the server parent setting in the rhn.conf file
	 * This is useful when channel syncing or registering a satellite
	 * @param system
	 * @param showlogs
	 * @param value
	 */
	public void modifyServerParent(String system, boolean showlogs, String value);

	/**
	 * Disables GPG checking for yum based clients, RHEL 5+
	 * @param system
	 * @param showlogs
	 */
	public void disableYumGPGCheck(String system, boolean showlogs);

	/**
	 * Enables Monitoring Entilement in SDC
	 * @param system
	 * @param openSystemPage
	 */
	public void enableMonitoringEntitlement(String system, boolean openSystemPage);


/**
 * 
	 * @param seconds
	 */
	public void sleepForSeconds(int seconds);
		
	

	/**
	 * Change system profile name
	 * @param originalName
	 * @param newName
	 * @param openAndLogin
	 */
	public void changeProfileName(String originalName, String newName, boolean openAndLogin);

	/**
	 * Assert that a system profile is present
	 * @param system
	 */
	public void verifySystemProfile(String system);
	
	
	
}
