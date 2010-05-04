package com.redhat.rhn.harness.common.Sat42.tasks;

import java.util.ArrayList;
import java.util.Random;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;

/**
 * This is a collection of the publicly available methods in the from
 * framework. These can be called from any test class.
 * @author whayutin
 *
 */

// THIS CLASS IS DEPRECATED, THIS WAS FORMALLY JUST A WRAPPER CLASS
// Currently I'm using this class as a holding area for methods that I'm not sure where to put yet :)

//  Other classes that use RhnBase should be changed to use the task_class.method
public class RhnBase extends SeleniumSetup implements IRhnBase {
	
	public void OpenAndLogIn(){
		page_RhnCommon.OpenAndLogIn();
	}

	public void OpenAndLogIn(String user,String password){
		page_RhnCommon.OpenAndLogIn(user, password);
	}

	public void JustOpen(){
		page_RhnCommon.JustOpen();
	}

	public void LogIn(String user,String password){
		page_RhnCommon.LogIn(user, password);
	}

	public void clickSignOut(){
		page_RhnCommon.clickSignOut();
	}
	
	public void SignOut(){
		page_RhnCommon.clickSignOut();
	}

	public void clickSignOut01(){
		page_RhnCommon.clickSignOut01();
	}

	public void clickYourRHN(){
		page_RhnCommon.clickYourRHN();
	}

	public void clickSideMenuSystems(){
		page_RhnCommon.clickSideMenuSystems();
	}

	public boolean goToSystem(String system){
		return task_Search.goToSystem(system);
	}

	public void goToSystemSDC(String system){
		task_Search.goToSystemSDC(system);
	}
	
	

	public void addPackageToKey(String key, String pkg) {
		task_ActivationKeys.addPackageToKey(key, pkg);
		

	}
	public void createActivationKeyWithOSAD(String name,String key, String usageLimit){
		task_ActivationKeys.createActivationKeyWithOSAD(name, key, usageLimit);
	}

	public void createActivationKey(String name, String key, String usageLimit,
			boolean monitoring, boolean provisioning, boolean virt,
			boolean virt_Platform, boolean unvDefault) {

		task_ActivationKeys.createActivationKey(name, key, usageLimit, monitoring, provisioning, virt, virt_Platform, unvDefault);

	}
	
	public void createActivationKeyWithBaseChannel(String name, String key, String basechan, String usageLimit,
			boolean monitoring, boolean provisioning, boolean virt,
			boolean virt_Platform, boolean unvDefault) {
		task_ActivationKeys.createActivationKeyWithBaseChannel(name, key, basechan, usageLimit, monitoring, provisioning, virt, virt_Platform, unvDefault);
	}


	public void deleteActivationKey(String name, boolean openAndLogin){
		task_ActivationKeys.deleteActivationKey(name, openAndLogin);
	}

	public boolean isSystemActivated(String system, String key) {
		return task_ActivationKeys.isSystemActivated(system, key);
	}

/*	public void confirmSearchResult(String details, String system) {
		sr.confirmSearchResult(details, system);
	}*/

	public void restartSatellite(){
		task_SatelliteTools.restartSatellite();
	}
		
	
	public String getSystemID(String system, boolean hosted, boolean searchForSystem) {
		return task_Search.getSystemID(system, hosted, searchForSystem);
	}

	public String getSystemInfo(String system, int info, boolean searchForSystem){
		return task_Search.getSystemInfo(system, info, searchForSystem);
	}

	public int totalInList() {
		return task_Search.totalInList();
	}

	public int totalySystemsInList() {
		return task_Search.totalySystemsInList();
	}



	public boolean findSearchResult(String result){
		return task_Search.findSearchResult(result);
	}

	public void deleteAllCustomChannels(){
		task_Channels.deleteAllCustomChannels();
		
	}

	public void verifyChannelExists(String channel, boolean exists){
		task_Channels.verifyChannelExists(channel, exists);
	}

	public void deleteCustomChannel(String clonedChannelName){
		task_Channels.deleteCustomChannel(clonedChannelName);
	}

	public void createChannelClone(String origChannel, String clonedChannelName,String clonedChannelLabel, int errata ){
		task_Channels.createChannelClone(origChannel, clonedChannelName, clonedChannelLabel, errata);
	}

	public void rhnPushPackageToChannel(String channel, String packageName, String packagePath, String server, String user, String passwd){
		task_Channels.rhnPushPackageToChannel(channel, packageName, packagePath, server, user, passwd);
	}
	
	public ArrayList<String> rhnPushPackageToChannelViaRsh(String hostname, String patchloc, String patchname, String channel){
		return task_Channels.rhnPushPackageToChannelViaRsh(hostname, patchloc, patchname, channel);
	}

	public void createCustomChannel(String channelName){
		task_Channels.createCustomChannel(channelName);
	}
	
	
	
	public void createCustomChannelWithArchitecture(String channelName,
													String architecture){
		task_Channels.createCustomChannelWithArchitecture(channelName, architecture);
	}
	
	public void createCustomChannelWithParent(String channelName,
											  String parentChannel,
											  String architecture){
		task_Channels.createCustomChannelWithParent(channelName, parentChannel, architecture);
	}

	public void verifyPackageInChannel(String Channel, String Package){
		task_Channels.verifyPackageInChannel(Channel, Package);
	}
	
	public void verifyPatchInChannel(String Channel, String Patch){
		task_Channels.verifyPatchInChannel(Channel, Patch);
	}

	public void DeleteKickstartProfile(String profileName, boolean openAndLogin){
		task_KickStart.DeleteKickstartProfile(profileName, openAndLogin);
	}

	public void kickstartWaitforKickstartCompletion(String system, String kickstartProfileName, boolean openAndLogin){
		task_KickStart.kickstartWaitforKickstartCompletion(system, kickstartProfileName, openAndLogin);
	}

	public void virtGuest_Status(String system,String guestLabel,boolean openAndLogin, String Status){
		task_KickStart.virtGuest_Status(system, guestLabel, openAndLogin, Status);
		}

	public void runRhnRegisterGUI(String server,String user, String password, String profilename){
		task_RhnRegister.runRhnRegisterGUI(server, user, password, profilename);
	}


	public void takeSnapShot(String system, boolean openAndLogin){
		task_Sdc.takeSnapShot(system, openAndLogin);
	}

	public void installPackage(String system, String pkg){
		task_Sdc.installPackage(system, pkg);
	}

	public void installErrata(String system, String errataName, String latestPackage, String installedPackage, String packageShortName){
		task_Sdc.installErrata(system, errataName, latestPackage, installedPackage, packageShortName);
	}

	public void removePackage(String system, String pkg){
		task_Sdc.removePackage(system, pkg);
	}

	public boolean listPackage(String system, String pkg){
		return task_Sdc.listPackage(system, pkg);
	}

	public void rollBackPackages(String system, boolean openAndLogin){
		task_Sdc.rollBackPackages(system, openAndLogin);
	}

	public void select_File_Checkbox(String file){
		task_Sdc.select_File_Checkbox(file);
	}

	public void removeRHNConfigFilesFromSystem(String system){
		task_TestPrep.removeRHNConfigFilesFromSystem(system);
	}

	public void command_wget(String fullUrl){
		task_TestPrep.command_wget(fullUrl);
	}

	public void command_rpm(String arguments,String system, boolean showlogs){
		task_TestPrep.command_rpm(arguments, system, showlogs);
	}

	public void command_GrubDefaultBoot0(String system,boolean showlogs){
		task_TestPrep.command_GrubDefaultBoot0(system, showlogs);
	}

	public void command_RestartSatellite(String system, boolean showlogs, boolean restartTomcatOnly, boolean restartOracle, boolean restartALL){
		task_TestPrep.command_RestartSatellite(system, showlogs, restartTomcatOnly, restartOracle, restartALL);
	}

	public void command_cancelShutdown(String system, boolean showlogs){
		task_TestPrep.command_cancelShutdown(system, showlogs);
	}


	public void command_rebootSystem(String system, boolean showlogs){
		task_TestPrep.command_rebootSystem(system, showlogs);
	}

	public String generate_randomLabel(){
		Random rand = new Random();
		return "test-" + rand.nextInt();
	}
	

	

	

	public void enableProvisioning(String system, boolean openSystemPage){
		task_TestPrep.enableProvisioning(system, openSystemPage);
	}

	public void enableMonitoringEntitlement(String system, boolean openSystemPage){
		task_TestPrep.enableMonitoringEntitlement(system, openSystemPage);
	}

	public void enableRHNConfigManag(String system, boolean hosted, boolean openAndLogin){
		task_TestPrep.enableRHNConfigManag(system, hosted, openAndLogin);
	}

	public void enableVirtualizationPlatform(String system, boolean openSystemPage){
		task_TestPrep.enableVirtualizationPlatform(system, openSystemPage);
	}

	public void updateYourAccount_Position(String position){
		task_YourRhn.updateYourAccount_Position(position);
	}

	public String createNonAdminUser(boolean satellite,String firstName, String lastName,String emailFirst, String emailLast, String address,
			String city, String state, String zip, String phone){
		return task_YourRhn.createNonAdminUser(satellite, firstName, lastName, emailFirst, emailLast, address, city, state, zip, phone);
	}

	public void createUserCustom(String login,String firstName, String lastName,String emailFirst, String emailLast){
		task_YourRhn.createUserCustom(login, firstName, lastName, emailFirst, emailLast);
	}

	

	public void clickErrata(){
		page_RhnCommon.clickErrata();
	}

	public void clickSystems(){
		page_RhnCommon.clickSystems();
	}

	public void clickChannels(){
		page_RhnCommon.clickChannels();
	}

	public void clickYourAccount(){
		page_RhnCommon.clickLink_YourAccount();
	}

	public void clickConfiguration(){
		page_RhnCommon.clickConfiguration();
	}

	public void clickSchedule(){
		page_RhnCommon.clickSchedule();
	}

	public void clickUsers(){
		page_RhnCommon.clickUsers();
	}

	public void clickLink_SystemName(String system){
		page_RhnCommon.clickLink_SystemName(system);
	}

	 public void clickLink_GeneralLink(String link){
		 page_RhnCommon.clickLink_GeneralLink(link);
	 }

	 public void check_FirstItemInList(){
		 page_RhnCommon.check_FirstItemInList();
	 }

	 public void check_SelectAll_CheckBox(){
		 page_RhnCommon.check_SelectAll_CheckBox();
	 }

	 public String getCurrentUserId(){
		 return page_RhnCommon.getCurrentUserId();
	 }

	 public void clickQuickSearchSubmit(){
		 page_RhnCommon.clickQuickSearchSubmit();
	 }

	 public void setQuickSearchText(String txt){
		 page_RhnCommon.setQuickSearchText(txt);
	 }

	

	 public void selectQuickSearchPackages(){
		 page_RhnCommon.selectQuickSearchPackages();
	 }

	 public void selectQuickSearchErrata(){
		 page_RhnCommon.selectQuickSearchErrata();
	 }

	 public void selectQuickSearchSystems(){
		 page_RhnCommon.selectQuickSearchSystems();
	 }

	 public void setTxt_FilterBy(String txt){
		 page_RhnCommon.setTxt_FilterBy(txt);
	 }

	 public void setTxt_FilterByUserName(String txt){
		 page_RhnCommon.setTxt_FilterByUserName(txt);
	 }

	 public void clickButton_Filter_Go(){
		 page_RhnCommon.clickButton_Filter_Go();
	 }

	 public void clickButton_PaginateNext(){
		 page_RhnCommon.clickButton_PaginateNext();
	 }

	 public void clickButton_PaginateLast(){
		 page_RhnCommon.clickButton_PaginateLast();
	 }

	 public void clickButton_PaginateFirst(){
		 page_RhnCommon.clickButton_PaginateFirst();
	 }

	 public void clickButton_PaginatePrevious(){
		 page_RhnCommon.clickButton_PaginatePrevious();
	 }

	 public void clickLink_AllSystems(){
		 page_RhnCommon.clickLink_AllSystems();
	 }

	 public void clickButton_UpdateList(){
		 page_RhnCommon.clickButton_UpdateList();
	 }

	 public void clickButton_SelectAll(){
		 page_RhnCommon.clickButton_SelectAll();
	 }

	 public void clickButton_UnselectAll(){
		 page_RhnCommon.clickButton_UnselectAll();
	 }

	 public void command_runRhnCheckWithAT(String system, boolean showlogs){
		 task_TestPrep.command_runRhnCheckWithAT(system, showlogs);
	 }

	 public String getRedHat_Release(String system){
		 return task_TestPrep.getRedHat_Release(system);
	 }

	 public void command_runRhnCheckInForeground(String system, boolean showlogs){
		 task_TestPrep.command_runRhnCheckInForeground(system, showlogs);
	 }

	 public void command_runRhnCheckInBackGround(String system, boolean showlogs){
		 task_TestPrep.command_runRhnCheckInBackGround(system, showlogs);
	 }

	 public void command_turn_On_Off_GPG_Check(String system,boolean showlogs){
		 task_TestPrep.command_turn_On_Off_GPG_Check(system, showlogs);
	 }

	 public void command_xmList(String system, boolean showlogs){
		 task_TestPrep.command_xmList(system, showlogs);
	 }

	 public void command_importRPMKeys(String system, boolean showlogs){
		 task_TestPrep.command_importRPMKeys(system, showlogs);
	 }

	 public void command_tailLog(String system,String logName){
		 task_TestPrep.command_tailLog(system, logName);
	 }

	 public boolean runRPM_Query_Command(String system, String rpm){
		 boolean result = false;
		 result = task_TestPrep.command_rpmQuery(system, rpm);
		 return result;
	 }


	 public void command_up2date(String arguments,String system, boolean showlogs){
		 task_TestPrep.command_up2date(arguments, system, showlogs);
	 }

	 public void command_yum(String arguments,String system, boolean showlogs){
		 task_TestPrep.command_yum(arguments, system, showlogs);
	 }

	 public void command_generic(String command, String arguments,String system, boolean showlogs){
		 task_TestPrep.command_generic(command, arguments, system, showlogs);
	 }

	 public void command_generic(String command, String arguments,String system, String user, boolean showlogs){
		 task_TestPrep.command_generic(command, arguments, system, user, showlogs);
	 }

	 public void command_test(String command, String arguments,String system, boolean showlogs){
		 task_TestPrep.command_test(command, arguments, system, showlogs);
	 }

	 public void command_kill_Firefox_OnSeleniumServer(){
		 task_TestPrep.command_kill_Firefox_OnSeleniumServer();
	 }

	 public void command_RemoveSSHKnownHosts(String system){
		 task_TestPrep.command_RemoveSSHKnownHosts(system);
	 }

	 public void command_remove_localKnownHosts(boolean showlogs){
		 task_TestPrep.command_remove_localKnownHosts(showlogs);
	 }
	 
	 public void command_remove_localKnownHost(String hostname, boolean showlogs){
		 task_TestPrep.command_remove_localKnownHost(hostname, showlogs);
	 }

	 
	 
	 public void changeLanguageSettingFromEnglish(boolean openAndLogin, String language){
		 task_TestPrep.changeLanguageSettingFromEnglish(openAndLogin, language);
	 }

	 public void changeLanguageSettingFromGerman(boolean openAndLogin, String language){
		 task_TestPrep.changeLanguageSettingFromGerman(openAndLogin, language);
	 }
	 
	 public int checkRHELVersion(String system){
		 return task_TestPrep.checkRHELVersion(system);
	 }

	 public void disablePkgSkipListForUp2date(String system, boolean showlogs){
		 task_TestPrep.disablePkgSkipListForUp2date(system, showlogs);
	 }

	 public void monitoring_delete_ProbesToSuite(boolean openAndLogin){
		 task_Monitoring.monitoring_delete_ProbesToSuite(openAndLogin);
	 }

	 public void monitoring_createProbeSuite(boolean openAndLogin, String probeName){
		 task_Monitoring.monitoring_createProbeSuite(openAndLogin, probeName);
	 }

	 public void monitoring_addProbesToSuite_LinuxMemory(boolean openAndLogin,String probeSuite){
		 task_Monitoring.monitoring_addProbesToSuite_LinuxMemory(openAndLogin, probeSuite);
	 }

	 public void monitoring_addProbesToSuite_NetworkPing(boolean openAndLogin,String probeSuite){
		 task_Monitoring.monitoring_addProbesToSuite_NetworkPing(openAndLogin, probeSuite);
	 }

	 public void monitoring_addSystemToProbeSuite(boolean openAndLogin,String probeSuite,String system){
		 task_Monitoring.monitoring_addSystemToProbeSuite(openAndLogin, probeSuite, system);
	 }

	 public void monitoring_checkProbeStatus(String system, String probe, String status1, String status2){
		 task_Monitoring.monitoring_checkProbeStatus(system, probe, status1, status2);
	 }

	 public void modifyServerParent(String system, boolean showlogs, String value){
		 task_TestPrep.modifyServerParent(system, showlogs, value);
	 }

	 public void disableYumGPGCheck(String system, boolean showlogs){
		 task_TestPrep.disableYumGPGCheck(system, showlogs);
	 }

	 public boolean quickSearch(int quickSearchType, String searchValue, boolean openAndLogin, boolean testResults){
		 return task_Search.quickSearch(quickSearchType, searchValue, openAndLogin, testResults);
	 }

	 public void advancedSearch(int quickSearchType, int fieldToSearch, String searchValue, boolean openAndLogin, boolean testResults){
		 task_Search.advancedSearch(quickSearchType, fieldToSearch, searchValue, openAndLogin, testResults);
	 }

	 public void createNewOrganization(String orgName, String user,String email, String password, boolean openAndLogin){
		 task_SatelliteTools.createNewOrganization(orgName, user, email, password, openAndLogin);
	 }


	/* public void goToOrganization(String orgName, boolean openAndLogin){
			satt.goToOrganization(orgName, openAndLogin);
	 }

	 public void deleteOrganization(String orgName, boolean openAndLogin){
			satt.deleteOrganization(orgName, openAndLogin);
	 }

	 public void updateOrganizationName(String orgName, boolean openAndLogin, String newOrgName){
			satt.updateOrganizationName(orgName, openAndLogin, newOrgName);
	 }

	 public void updateOrgSystemEntitlements(String orgName, boolean openAndLogin,int entitlement, String count,boolean successful){
		 	satt.updateOrgSystemEntitlements(orgName, openAndLogin, entitlement, count, successful);
	 }

	 public int verifyEntitlementCounts(boolean openAndLogin, int entitlement, boolean consumed){
		    return yrhn.verifyEntitlementCounts(openAndLogin, entitlement, consumed);
	 }

	 public void updateOrgSoftwareChannelEntitlements(String orgName, boolean openAndLogin, int channel, String count, boolean successful){
		    satt.updateOrgSoftwareChannelEntitlements( orgName, openAndLogin,  channel,  count,  successful);
	 }*/

	 public void DeleteConfigChannel(String channel, boolean openAndLogin){
		 	task_ConfigMang.DeleteConfigChannel(channel, openAndLogin);
	 }

	 public void CreateNewConfigChannel(String channel, boolean openAndLogin, boolean deleteOld, boolean successful){
		 task_ConfigMang.CreateNewConfigChannel(channel, openAndLogin, deleteOld, successful);
	 }

	 public boolean goToChannel(String channel, boolean openAndLogin){
		 return  task_ConfigMang.goToChannel(channel, openAndLogin);
	 }

	 public void removeConfigFile(String channel, String file, boolean openAndLogin, boolean successful){
		 task_ConfigMang.removeConfigFile(channel, file, openAndLogin, successful);
	 }

	 public void uploadFilesToChannel(String channel, String file, boolean binary, String path, boolean succesful, int revisionNumber){
		 task_ConfigMang.uploadFilesToChannel(channel, file, binary, path, succesful, revisionNumber);
	 }

	 public void updateConfigurationFile(String channel, String file, String updatedTxt, int revision, boolean openAndLogin, boolean binary){
		 task_ConfigMang.updateConfigurationFile(channel, file, updatedTxt, revision, openAndLogin, binary);
	 }

	 public void changeBaseChannel(String system, String channel){
		 task_Sdc.changeBaseChannel(system, channel);
	 }

	public void virtGuest_Action(String system,String guestLabel,boolean openAndLogin, boolean runRhnCheck, int action){
		 task_KickStart.virtGuest_Action(system, guestLabel, openAndLogin, runRhnCheck, action);
	 }

	 public String getVirtGuest_HostName(String system,String guestLabel,boolean openAndLogin){
		 return task_KickStart.getVirtGuest_HostName(system,guestLabel,openAndLogin);
	 }

	 public void AddActivationKeyToKickstartProfile(String profileName){
		 task_KickStart.AddActivationKeyToKickstartProfile(profileName);
	 }

	

	 public void DeleteAllErrata(boolean openAndLogin){
		 task_ErrataMang.DeleteAllErrata(openAndLogin);
	 }

	 public void addPackageToUnPubErrataAndPublishToChannel(String errata, String channel, String packageName){
		 task_ErrataMang.addPackageToUnPubErrataAndPublishToChannel(errata, channel, packageName);
	 }

	 public void goToUser(String user){
		 task_UserRoles.goToUser(user);
	 }

	 public void UserEditRole(String user, int role, boolean check){
		 task_UserRoles.UserEditRole(user, role, check);
	 }

	 public void groupCreate(String systemGroupName){
			task_UserRoles.groupCreate(systemGroupName);
		}

	 public void addSystemToSystemGroup(String system, String systemGroup){
		 task_UserRoles.addSystemToSystemGroup(system, systemGroup);
	 }

	 public void enableMonitoring(boolean openAndLogin){
		 task_Monitoring.enableMonitoring(openAndLogin);
	 }

	 public void installProxy(boolean enable_monitoring, String proxyVersion){
		 task_Proxy.installProxy(enable_monitoring, proxyVersion);
	 }
	 public boolean checkIfProxyInstalled(String system){
		 return task_Proxy.checkIfProxyInstalled(system);
	 }
	 
	 public String get_BareMetal_KickstartUrl(String ksProfile){
			return task_KickStart.get_BareMetal_KickstartUrl(ksProfile);
		}
	 
	 public void command_runCommandWithAT(String system,String command, boolean showlogs){
		 task_TestPrep.command_runCommandWithAT(system, command, showlogs);
	 }
	 
	 public void sleepForSeconds(int seconds){
		 task_TestPrep.sleepForSeconds(seconds);
	 }
	 
	 public void changeProfileName(String originalName, String newName, boolean openAndLogin){
		 task_TestPrep.changeProfileName(originalName, newName, openAndLogin);
	 }
	 
	 public void goToKey(String key){
		 task_ActivationKeys.goToKey(key);
	 }
	 
	 public void checkpage(){
		 page_RhnCommon.checkpage();
	 }
	 
	 public void createKickstartDistribution(String label, String externalUrl,String baseChannel, String kickstartRPM, String installerGeneration){
		 task_KickStart.createKickstartDistribution(label, externalUrl, baseChannel, kickstartRPM, installerGeneration);
	 }
	 
	 public void deleteKickstartDistribution(String label){
		 task_KickStart.deleteKickstartDistribution(label);
	 }
	 
	 public void enableMonitoringScout(boolean openAndLogin){
		 task_Monitoring.enableMonitoringScout(openAndLogin);
	 }
	 
	 public String monitoring_getSSHPublicKey(boolean openAndLogin,boolean proxy){
		 return task_Monitoring.monitoring_getSSHPublicKey(openAndLogin, proxy);
	 }
	 
	 public void alterBaseChannelSubscriptions(boolean openAndLogin, String system, String channel){
		 task_Sdc.alterBaseChannelSubscriptions(openAndLogin, system, channel);
	 }
	 
	 public void verifySystemProfile(String system){
		 task_TestPrep.verifySystemProfile(system);
	 }
	 
	 public void groupDelete(String systemGroupName){
		 task_UserRoles.groupDelete(systemGroupName);
	 }
	 
	 public void deleteAllConfigChannels(boolean openAndLogin){
		 task_ConfigMang.deleteAllConfigChannels(openAndLogin);
	 }
	 
	 public void removeAllOrganizations(boolean openAndLogin){
		 task_SatelliteTools.deleteAllOrganizations(openAndLogin);
	 }
	 
	 public void deleteUser(String user) {
		 task_UserRoles.deleteUser(user);
	 }
	 
	 public void deleteAllUsers() {
		 task_UserRoles.deleteAllUsers();
	 }
	 
	 public void deleteSysGroup(String sysGroup){
		 throw new SeleniumException("wrong task version");
	 }
	 
	 public boolean checkForSharedChannel(String channel, String org){
		 return task_YourRhn.checkForSharedChannel(channel, org);
	 }
	 
	 public void createCustomChannel(String channelName, int orgChannelSharingSetting, int orgChannelUserRestriction, String parentChannel){
		 task_Channels.createCustomChannel(channelName, orgChannelSharingSetting, orgChannelUserRestriction, null);
	 }
	 
	 public void deleteAllOrganizations(boolean openAndLogin){
		 throw new SeleniumException("wrong task version");
		}
	 
	 public int verifyEntitlementCounts(boolean openAndLogin, int entitlement, boolean consumed){
		 throw new SeleniumException("wrong task version");
	 }
	 
	 public void command_change_webForceUnentitlement(String system,boolean turnOn,boolean showlogs){
		 throw new SeleniumException("wrong task version");
	 }
	 
	 public String verifyOrgSystemEntitlementUsage(boolean openAndLogin, int entitlement, int column){
		 throw new SeleniumException("wrong task version");
	 }

	 public int verifyOrgSoftwareEntitlementCounts(boolean openAndLogin, int entitlement, int column){
		 throw new SeleniumException("wrong task version");
	 }


	 public String verifyOrgSoftwareEntitlementUsage(boolean openAndLogin, int entitlement){
		 throw new SeleniumException("wrong task version");
	 }
	 
	 public void createActivationKey(String name,String key, String usageLimit, String channel,
				boolean monitoring, boolean provisioning,
				boolean virt, boolean virt_Platform,boolean unvDefault){
		 task_ActivationKeys.createActivationKey(name, key, usageLimit, channel, monitoring, provisioning, virt, virt_Platform, unvDefault);
	 }
	 
		public void pushMonitoringScoutConfig(boolean openAndLogin, boolean waitForComplete){
			task_Monitoring.pushMonitoringScoutConfig(openAndLogin, waitForComplete);
		}



}
