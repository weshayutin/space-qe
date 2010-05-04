package com.redhat.rhn.harness.common.Sat51.tasks;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IRhnBase;

public class UserRoles extends com.redhat.rhn.harness.common.Sat50.tasks.UserRoles {

	
	/*protected static String ActivationRole = "userActivationRole";
	protected static String ChannelRole = "userChannelRole";
	protected static String SysGroupRole = "userSysGroupRole";
	protected static String ConfigAdminRole = "userConfigAdminRole";
	protected static String OrgAdminRole = "userOrgAdminRole";
	protected static String MonitorRole = "userMonitorRole";
	protected static String SatAdminRole = "userSatAdminRole"; //5.1
*/

	public void goToUser(String user){
		page_RhnCommon.clickUsers();
		page_RhnCommon.setTxt_FilterByUserName(user);
		page_RhnCommon.clickButton_Filter_Go();
		Assert.assertTrue(rh.isTextPresent(user));
		page_RhnCommon.clickButton_Filter_Go();
		rh.clickLink("link="+user, true);
	}

	

	public void groupCreate(String systemGroupName){
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_SystemGroups();
		page_SatelliteSystems.clickLink_CreateNewGroup();
		page_SatelliteSystems.setText_SystemGroup_Name(systemGroupName);
		page_SatelliteSystems.setText_SystemGroup_Description("description");
		page_SatelliteSystems.clickButton_CreateGroup();
		Assert.assertTrue(rh.isTextPresent("System group "+systemGroupName+" created"));
	}
	
	public void groupDelete(String systemGroupName){
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_SystemGroups();
		if(rh.isTextNotPresent("Your organization has no system groups.")){
			page_RhnCommon.setTxt_FilterBy(systemGroupName);
			page_RhnCommon.clickButton_Filter_Go();
				if(rh.isTextPresent(systemGroupName)){
					page_RhnCommon.clickLink_GeneralLink(systemGroupName);
					page_SatelliteSystems.clickLink_delete_group();
					page_SatelliteSystems.clickButton_Confirm_Deletion();
				}
			}
		
	}

	public void addSystemToSystemGroup(String system, String systemGroup){
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_SystemGroups();
		page_RhnCommon.setTxt_FilterBy(systemGroup);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.clickLink_GeneralLink(systemGroup);
		page_SatelliteSystems.clickLink_Group_TargetSystems();
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInList();
		page_SatelliteSystems.clickButton_Group_AddSystem();
		Assert.assertTrue(rh.isTextPresent("added to "+systemGroup));
	}
	

	public void UserEditRole(String user, int role, boolean check){
		goToUser(user);
		if(role == IRhnBase.USER_ROLE_ACTIVATION)
			page_Users.checkBox_UserRole_Activation(check);
		if(role == IRhnBase.USER_ROLE_CHANNEL)
			page_Users.checkBox_UserRole_Channel(check);
		if(role == IRhnBase.USER_ROLE_SYSTEM_GROUP)
			page_Users.checkBox_UserRole_SystemGroup(check);
		if(role == IRhnBase.USER_ROLE_CONFIGURATION)
			page_Users.checkBox_UserRole_Configuration(check);
		if(role == IRhnBase.USER_ROLE_ORG_ADMIN)
			page_Users.checkBox_UserRole_Organization(check);
		if(role == IRhnBase.USER_ROLE_MONITORING)
			page_Users.checkBox_UserRole_Monitoring(check);
		if(role == IRhnBase.USER_ROLE_SAT_ADMIN){
			log.info("TODO");
		}

		page_RhnCommon.clickButton_Submit();
		Assert.assertTrue(rh.isTextPresent("User information updated"));
	}
	
	public void deleteAllUsers() {
		String user = null;
		page_RhnCommon.clickUsers();
		while(rh.isElementPresent("xpath=//tbody/tr/td[2]/form/table[2]/tbody/tr[2]/td/a", true)){
			user = sel.getText("xpath=//tbody/tr/td[2]/form/table[2]/tbody/tr[2]/td/a");
			if(user.equalsIgnoreCase(IRhnBase.USER)){
				while(rh.isElementPresent("xpath=//tbody/tr/td[2]/form/table[2]/tbody/tr[3]/td/a", true)){
					user = sel.getText("xpath=//tbody/tr/td[2]/form/table[2]/tbody/tr[3]/td/a");
					rh.clickLink("link=" + user, true);
					page_Users.clickLink_DeleteUser();
					page_Users.clickButton_DeleteUser();
					if(rh.isTextPresent("You cannot delete another organization administrator.")){
						page_Users.checkBox_UserRole_Organization(false);
						page_RhnCommon.clickButton_Submit();
						page_Users.clickLink_DeleteUser();
						page_Users.clickButton_DeleteUser();	
					}
					page_RhnCommon.clickUsers();
				}
			}
			else{
			rh.clickLink("link=" + user, true);
			page_Users.clickLink_DeleteUser();
			page_Users.clickButton_DeleteUser();
			}
			if(rh.isTextPresent("You cannot delete another organization administrator.")){
				page_Users.checkBox_UserRole_Organization(false);
				page_RhnCommon.clickButton_Submit();
				page_Users.clickLink_DeleteUser();
				page_Users.clickButton_DeleteUser();	
			}
			page_RhnCommon.clickUsers();
		}
	}




}
