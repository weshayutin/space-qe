package com.redhat.rhn.harness.Sat51.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class UsersPage extends com.redhat.rhn.harness.Sat50.pages.UsersPage {
	RhnHelper rh = new RhnHelper();

	 public String getLocation(){
	        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_USERS_PAGE;
	    }

	public void open(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_USERS_PAGE,true, "Users");
    }
	
	public void checkBox_UserRole_Activation(boolean check){
		rh.checkRadioButton("xpath=//input[@name='role_activation_key_admin']",check);
}

 public void checkBox_UserRole_Channel(boolean check){
		rh.checkRadioButton("xpath=//input[@name='role_channel_admin']",check);
}

 public void checkBox_UserRole_SystemGroup(boolean check){
		rh.checkRadioButton("xpath=//input[@name='role_system_group_admin']",check);
}

 public void checkBox_UserRole_Configuration(boolean check){
		rh.checkRadioButton("xpath=//input[@name='role_config_admin']",check);
}

 public void checkBox_UserRole_Organization(boolean check){
		rh.checkRadioButton("xpath=//input[@name='role_org_admin']",check);
}

 public void checkBox_UserRole_Monitoring(boolean check){
		rh.checkRadioButton("xpath=//input[@name='role_monitoring_admin']",check);
}
 
 



	


}
