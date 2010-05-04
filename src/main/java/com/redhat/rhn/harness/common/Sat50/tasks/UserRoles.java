package com.redhat.rhn.harness.common.Sat50.tasks;

import org.testng.Assert;

import com.redhat.rhn.harness.Hosted.pages.SatelliteSystemsPage;
import com.redhat.rhn.harness.Sat50.pages.RhnCommon;
import com.redhat.rhn.harness.Sat50.pages.SDCPage;
import com.redhat.rhn.harness.Sat50.pages.UsersPage;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.baseInterface.IUserRoles;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

/**
 * Tasks used to user roles,groups in RHN Satellite
 * 
 * @author whayutin
 * 
 */
public class UserRoles extends com.redhat.rhn.harness.common.Sat42.tasks.UserRoles {



	public void goToUser(String user) {
		page_RhnCommon.clickUsers();
		page_RhnCommon.setTxt_FilterByUserName(user);
		page_RhnCommon.clickButton_Filter_Go();
		Assert.assertTrue(rh.isTextPresent(user));
		page_RhnCommon.clickButton_Filter_Go();
		rh.clickLink("link=" + user, true);
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
	
	
}
