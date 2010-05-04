package com.redhat.rhn.harness.common.Sat50.tasks;

import com.redhat.rhn.harness.baseInterface.IRhnBase;

public class RhnBase extends com.redhat.rhn.harness.common.Sat42.tasks.RhnBase implements IRhnBase {

	



	
	public String getSystemID(String system, boolean hosted, boolean searchForSystem) {
		return task_Search.getSystemID(system, hosted, searchForSystem);
	}


	/*public void registerMultipleProfiles(String system,String testSystemRootName, int numberOfSystems,String command, boolean deleteOld, boolean hosted, boolean openAndLogin){
		tp.registerMultipleProfiles(system, testSystemRootName, numberOfSystems, command, deleteOld, hosted, openAndLogin);
	}*/
	
	public boolean quickSearch(int quickSearchType, String searchValue, boolean openAndLogin, boolean testResults){
		 return task_Search.quickSearch(quickSearchType, searchValue, openAndLogin, testResults);
	}
	
	public void createUserCustom(String login, String firstName,
			String lastName, String emailFirst, String emailLast){
		task_YourRhn.createUserCustom(login, firstName, lastName, emailFirst, emailLast);
	}
	
	public void goToUser(String user) {
		task_UserRoles.goToUser(user);
	}
	public void UserEditRole(String user, int role, boolean check){
		task_UserRoles.UserEditRole(user, role, check);
	}
	
	public void enableMonitoringEntitlement(String system,boolean openSystemPage) {
		task_TestPrep.enableMonitoringEntitlement(system, openSystemPage);
	}
	

	
	public void deleteAllCustomChannels(){
		task_Channels.deleteAllCustomChannels();
	}
	
	public void removeAllSystemProfiles(boolean openAndLogin){
		task_TestPrep.removeAllSystemProfiles(openAndLogin);
	}

}
