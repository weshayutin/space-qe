package com.redhat.rhn.harness.common.Space01.tasks;


import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.baseInterface.ISatelliteTools;
import com.thoughtworks.selenium.SeleniumException;

public class SatelliteTools extends com.redhat.rhn.harness.common.Sat51.tasks.SatelliteTools implements ISatelliteTools{
	
	
	
	

	
	public void goToOrganization(String orgName, boolean openAndLogin){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_SatelliteTools.open();
		page_SatelliteTools.clickLink_Admin();
		page_SatelliteTools.clickLink_Organizations();
		page_RhnCommon.setTxt_FilterBy(orgName);
		page_RhnCommon.clickButton_Filter_Go();
		rh.clickLink("link="+orgName, true);
	}
	
	protected void filterOrg(String orgName){
		page_SatelliteTools.open();
		page_SatelliteTools.clickLink_Admin();
		page_SatelliteTools.clickLink_Organizations();
		page_RhnCommon.setTxt_FilterBy(orgName);
		page_RhnCommon.clickButton_Filter_Go();
	}
	
	public void modifyOrgTrusts(String trustee, String trustor, boolean trust){
		page_SatelliteTools.open();
		goToOrganization(trustee, false);
		page_SatelliteTools.clickLink_Trusts();
		page_RhnCommon.setTxt_FilterBy(trustor);
		page_RhnCommon.clickButton_Filter_Go();
		if(trust){
			page_SatelliteTools.checkBox_Trust(trust);
		}
		else{
			page_RhnCommon.check_FirstItemInList();
			page_RhnCommon.uncheck_FirstItemInList();
		}
		//page_RhnCommon.clickButton_UnselectAll(); sat bug here
		page_SatelliteTools.clickButton_ModifyTrusts();
		if(page_RhnCommon.existsButton_Confirm())
			page_RhnCommon.clickButton_Confirm();
	}
	
	public void createNewOrganization(String orgName, String user,String email, String password, boolean openAndLogin){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_SatelliteTools.open();
		page_SatelliteTools.clickLink_Organizations();
		page_RhnCommon.setTxt_FilterBy(orgName);
		page_RhnCommon.clickButton_Filter_Go();
		if(rh.isTextPresent(orgName)){
//			rh.clickLink("link="+orgName, true);
//			page_SatelliteTools.clickLink_DeleteOrganization();
//			page_SatelliteTools.clickButton_DeleteOrganization();
		}
		page_SatelliteTools.clickLink_CreateNewOrganization();
		page_SatelliteTools.setTxt_OrganizationName(orgName);
		page_SatelliteTools.setTxt_Organization_Login(user);
		page_SatelliteTools.setTxt_Organization_Email(email);
		page_SatelliteTools.setTxt_Organization_DesiredPassword(password);
		page_SatelliteTools.setTxt_Organization_ConfirmPassowrd(password);
		page_SatelliteTools.setTxt_Organization_FirstName("auto");
		page_SatelliteTools.setTxt_Organization_LastName("man");
		page_SatelliteTools.clickButton_CreateOrganization();
	}
	
	public boolean orgExists(String orgName, boolean openAndLogin){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_SatelliteTools.open();
		page_SatelliteTools.clickLink_Admin();
		page_SatelliteTools.clickLink_Organizations();
		page_RhnCommon.setTxt_FilterBy(orgName);
		page_RhnCommon.clickButton_Filter_Go();
		return rh.isElementPresent("link="+orgName, false);
	}
	
	
	
	
}
