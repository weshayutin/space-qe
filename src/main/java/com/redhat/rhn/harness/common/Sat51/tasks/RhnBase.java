package com.redhat.rhn.harness.common.Sat51.tasks;

import com.redhat.rhn.harness.baseInterface.IRhnBase;

public class RhnBase extends com.redhat.rhn.harness.common.Sat50.tasks.RhnBase implements IRhnBase {

	
	public void createCustomChannelWithArchitecture(String channelName,
			String architecture){
		task_Channels.createCustomChannelWithArchitecture(channelName, architecture);
	}

	public void createCustomChannelWithParent(String channelName,
											  String parentChannel,
											  String architecture){
		task_Channels.createCustomChannelWithParent(channelName, parentChannel, architecture);
	}
	
	public void verifyPatchInChannel(String Channel, String Patch){
		task_Channels.verifyPatchInChannel(Channel, Patch);
	}
	
	 public void updateOrgSoftwareChannelEntitlements(String orgName, boolean openAndLogin, int channel, String count, boolean successful){
	 	 task_SatelliteTools.updateOrgSoftwareChannelEntitlements(orgName, openAndLogin, channel, count, successful);
 }

 public String verifyOrgSystemEntitlementUsage(boolean openAndLogin, int entitlement){
	 	return task_SatelliteTools.verifyOrgSoftwareEntitlementUsage(openAndLogin, entitlement);
 }

 public int verifyOrgSoftwareEntitlementCounts(boolean openAndLogin, int entitlement, int column){
	 return task_SatelliteTools.verifyOrgSoftwareEntitlementCounts(openAndLogin, entitlement, column);
 }

 
 public String verifyOrgSoftwareEntitlementUsage(boolean openAndLogin, int entitlement){
	 return task_SatelliteTools.verifyOrgSoftwareEntitlementUsage(openAndLogin, entitlement);
 }

	
	
	public int verifyOrgSystemEntitlementCounts(boolean openAndLogin, int entitlement, int column){
		return task_SatelliteTools.verifyOrgSystemEntitlementCounts(openAndLogin, entitlement, column);
	}

	/*public void registerMultipleProfiles(String system,String testSystemRootName, int numberOfSystems,String command, boolean deleteOld, boolean hosted, boolean openAndLogin){
		tp.registerMultipleProfiles(system, testSystemRootName, numberOfSystems, command, deleteOld, hosted, openAndLogin);
	}*/
	
	public boolean quickSearch(int quickSearchType, String searchValue, boolean openAndLogin, boolean testResults){
		 return task_Search.quickSearch(quickSearchType, searchValue, openAndLogin, testResults);
	}
	
	 public void createNewOrganization(String orgName, String user,String email, String password, boolean openAndLogin){
		 task_SatelliteTools.createNewOrganization(orgName, user, email, password, openAndLogin);
	 }

	 public void deleteAllCustomChannels(){
			task_Channels.deleteAllCustomChannels();
	 }

	 public void goToOrganization(String orgName, boolean openAndLogin){
			task_SatelliteTools.goToOrganization(orgName, openAndLogin);
	 }

	 public void deleteOrganization(String orgName, boolean openAndLogin){
			task_SatelliteTools.deleteOrganization(orgName, openAndLogin);
	 }

	 public void updateOrganizationName(String orgName, boolean openAndLogin, String newOrgName){
			task_SatelliteTools.updateOrganizationName(orgName, openAndLogin, newOrgName);
	 }

	 public void updateOrgSystemEntitlements(String orgName, boolean openAndLogin,int entitlement, String count,boolean successful){
		 	task_SatelliteTools.updateOrgSystemEntitlements(orgName, openAndLogin, entitlement, count, successful);
	 }

	 public int verifyEntitlementCounts(boolean openAndLogin, int entitlement, boolean consumed){
		    return task_YourRhn.verifyEntitlementCounts(openAndLogin, entitlement, consumed);
	 }




	 public void goToUser(String user){
		 task_UserRoles.goToUser(user);
	 }
		/*public void quickSearch(int quickSearchType, String searchValue, boolean openAndLogin, boolean testResults){
			 sr.quickSearch(quickSearchType, searchValue, openAndLogin, testResults);
		}*/

	 public void advancedSearch(int quickSearchType, int fieldToSearch, String searchValue, boolean openAndLogin, boolean testResults){
		 task_Search.advancedSearch(quickSearchType, fieldToSearch, searchValue, openAndLogin, testResults);
	 }
	 
	 public void command_change_webForceUnentitlement(String system,boolean turnOn,boolean showlogs){
		 task_TestPrep.command_change_webForceUnentitlement(system, turnOn, showlogs);
	 }
	 
	 public void createActivationKeyWithOSAD(String name,String key, String usageLimit){
		 task_ActivationKeys.createActivationKeyWithOSAD(name, key, usageLimit);
	 }
	 
	 public void deleteActivationKey(String name, boolean openAndLogin){
		 task_ActivationKeys.deleteActivationKey(name, openAndLogin);
	 }
	 
	 public void createActivationKey(String name, String key, String usageLimit,
				boolean monitoring, boolean provisioning, boolean virt,
				boolean virt_Platform, boolean unvDefault) {

			task_ActivationKeys.createActivationKey(name, key, usageLimit, monitoring, provisioning, virt, virt_Platform, unvDefault);

		}
	 
	 public void AddActivationKeyToKickstartProfile(String profileName){
		 task_KickStart.AddActivationKeyToKickstartProfile(profileName);
	 }
	 
	 
	 public void removePackage(String system, String pkg){
		 //SdcSoftware sdcs = HarnessConfiguration.sdcSoftware;
		 task_Sdc.removePackage(system, pkg);
	 }
	 
	 public void installPackage(String system, String pkg){
		 task_Sdc.installPackage(system, pkg);
	 }
	 
	 public void deleteCustomChannel(String clonedChannelName){
		 task_Channels.deleteCustomChannel(clonedChannelName);
	 }
	 
	 public void UserEditRole(String user, int role, boolean check){
		 task_UserRoles.UserEditRole(user, role, check);
	 }
	 
	 public String get_BareMetal_KickstartUrl(String ksProfile){
		 return task_KickStart.get_BareMetal_KickstartUrl(ksProfile);
	 }
	 
	 public void enableRHNConfigManag(String system, boolean hosted,boolean openAndLogin) {
		  task_TestPrep.enableRHNConfigManag(system, hosted, openAndLogin);
	 }
	 
	 public void enableMonitoringScout(boolean openAndLogin){
		 task_Monitoring.enableMonitoringScout(openAndLogin);
	 }
	 
		 
	 public void DeleteAllErrata(boolean openAndLogin){
		 task_ErrataMang.DeleteAllErrata(openAndLogin);
		}
	 
	 public void deleteAllOrganizations(boolean openAndLogin){
		 task_SatelliteTools.deleteAllOrganizations(openAndLogin);
		}
	 
	 public void deleteAllUsers(){
		 task_UserRoles.deleteAllUsers();
	 }



}
