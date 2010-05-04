package com.redhat.rhn.harness.common.Sat42.tasks;

import org.testng.Assert;

import com.redhat.rhn.harness.Sat42.pages.RhnCommon;
import com.redhat.rhn.harness.Sat42.pages.SDCPage;
import com.redhat.rhn.harness.Sat42.pages.SatelliteSystemsPage;
import com.redhat.rhn.harness.Sat42.pages.UsersPage;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.baseInterface.IUserRoles;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

/**
 * Tasks used to user roles,groups in RHN Satellite
 * 
 * @author whayutin
 * 
 */
public class UserRoles extends SeleniumSetup {  // implements IUserRoles {

	
	protected RhnHelper rh = new RhnHelper();
	
	
	
	



	public void goToUser(String user) {
		page_RhnCommon.clickUsers();
		page_RhnCommon.setTxt_FilterByUserName(user);
		page_RhnCommon.clickButton_Filter_Go();
		Assert.assertTrue(rh.isTextPresent(user));
		page_RhnCommon.clickButton_Filter_Go();
		rh.clickLink("link=" + user, true);
	}
	
	public void deleteUser(String user) {
		page_RhnCommon.clickUsers();
		page_RhnCommon.setTxt_FilterByUserName(user);
		page_RhnCommon.clickButton_Filter_Go();
		Assert.assertTrue(rh.isTextPresent(user));
		page_RhnCommon.clickButton_Filter_Go();
		rh.clickLink("link=" + user, true);
		page_Users.clickLink_DeleteUser();
		page_Users.clickButton_DeleteUser();
		//txt after = Deleted user + user
	}
	
	public void deleteAllUsers() {
		String user = null;
		page_RhnCommon.clickUsers();
		while(rh.isElementPresent("xpath=//tbody/tr/td[2]/form/table[2]/tbody/tr/td/a", true)){
			user = sel.getText("xpath=//tbody/tr/td[2]/form/table[2]/tbody/tr/td/a");
			if(user.equalsIgnoreCase(IRhnBase.USER)){
				while(rh.isElementPresent("xpath=//tbody/tr/td[2]/form/table[2]/tbody/tr[2]/td/a", true)){
					user = sel.getText("xpath=//tbody/tr/td[2]/form/table[2]/tbody/tr[2]/td/a");
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

	public void UserEditRole(String user, int role, boolean check) {
		goToUser(user);
		if (role == IRhnBase.USER_ROLE_ACTIVATION)
			page_Users.checkBox_UserRole_Activation(check);
		if (role == IRhnBase.USER_ROLE_CHANNEL)
			page_Users.checkBox_UserRole_Channel(check);
		if (role == IRhnBase.USER_ROLE_SYSTEM_GROUP)
			page_Users.checkBox_UserRole_SystemGroup(check);
		if (role == IRhnBase.USER_ROLE_CONFIGURATION)
			page_Users.checkBox_UserRole_Configuration(check);
		if (role == IRhnBase.USER_ROLE_ORG_ADMIN)
			page_Users.checkBox_UserRole_Organization(check);
		if (role == IRhnBase.USER_ROLE_MONITORING)
			page_Users.checkBox_UserRole_Monitoring(check);
		if (role == IRhnBase.USER_ROLE_SAT_ADMIN) {
			log.info("TODO");
		}

		page_RhnCommon.clickButton_Submit();
		Assert.assertTrue(rh.isTextPresent("User information updated"));
	}

	public void groupCreate(String systemGroupName) {
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_SystemGroups();
		page_SatelliteSystems.clickLink_CreateNewGroup();
		page_SatelliteSystems.setText_SystemGroup_Name(systemGroupName);
		page_SatelliteSystems.setText_SystemGroup_Description("description");
		page_SatelliteSystems.clickButton_CreateGroup();
		rh.isTextPresent("That group name is already in use. Please choose another.");
		Assert.assertTrue(rh.isTextPresent("System group " + systemGroupName
				+ " created"));

	}

	public void addSystemToSystemGroup(String system, String systemGroup) {
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
		Assert.assertTrue(rh.isTextPresent("added to " + systemGroup + " group"));
	}
	
	public void addAdminToSystemGroup(String system, String systemGroup, int numberOfUsers) {
		numberOfUsers++;
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_SystemGroups();
		page_RhnCommon.setTxt_FilterBy(systemGroup);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.clickLink_GeneralLink(systemGroup);
		page_SatelliteSystems.clickLink_Group_Admins();
		for(int i=2; i <= numberOfUsers;i++){
			String myLink=null;
			myLink = "xpath=//table/tbody/tr/td[2]/form/table[2]/tbody/tr["+i+"]/td/input";
			//log.info(myLink);
			sel.check(myLink);
		}
		page_RhnCommon.clickButton_Update();
		Assert.assertTrue(rh.isTextPresent("Admin list for system group " + systemGroup ));
	}
	
	
	public void groupDelete(String systemGroupName){
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_SystemGroups();
		if(rh.isTextNotPresent("Your organization has no system groups.")){
			page_RhnCommon.setTxt_FilterBy(systemGroupName);
			page_RhnCommon.clickButton_Filter_Go();
				if(rh.isTextPresent(systemGroupName)){
					page_RhnCommon.clickLink_GeneralLink("link="+systemGroupName);
					page_SatelliteSystems.clickLink_delete_group();
					page_SatelliteSystems.clickButton_Confirm_Deletion();
				}
			}
		
	}

}
