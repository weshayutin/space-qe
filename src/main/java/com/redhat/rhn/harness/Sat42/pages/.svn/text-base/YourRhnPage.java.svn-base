package com.redhat.rhn.harness.Sat42.pages;

import com.redhat.qe.auto.selenium.ExtendedSelenium;
import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.thoughtworks.selenium.SeleniumException;

/**
 *  Collection of objects (links,buttons,etc..) from the RHN Satellite YourRHN Page
 * @author whayutin
 *
 */
public class YourRhnPage extends RhnPage {
	//static RhnHelper rh = new RhnHelper();
	 static RhnHelper rh = new RhnHelper();
	 public String entriesPerPage = "5";
	 

    public String getLocation(){
        return HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_YOUR_RHN_PAGE;
    }

    public void open(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_YOUR_RHN_PAGE,true, "Your Rhn");
    }

    public void openPub(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + "/pub",true, "pub dir");
    }

    public String getRhnUserName(){
    	return HarnessConfiguration.RHN_USER;
    }

    public String getRhnUserPassword(){
    	return HarnessConfiguration.RHN_PASS;
    }

    public void clickLink_YourPreferences(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/account/UserPreferences.do", true,"Your Preferences");
    }
    
    public void clickLink_LocalePreferences(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/account/LocalePreferences.do", true,"Locale Preferences");
    }
    
    public void clickLink_YourAccount(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/account/UserDetails.do", true, "Your Account");
    }

    public void clickLink_YourAddress(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/account/Addresses.do", true, "Address");
    }

    public void clickLink_ChangeEmail(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/account/ChangeEmail.do", true, "Change Email");
    }
    public void clickLink_SystemEntitlements(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/systems/SystemEntitlements.do", true, "System Entitlements");
    }

    public void clickLink_SoftwareChannelEntitlements(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/channels/software/Entitlements.do", true, "Software Channel Entitlements");
    }
    public void clickLink_PurchaseOrRenew(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/account/SubscriptionManagement.do", true, "Purchase or Renew");
    }

    public void clickLink_ExpirationDatesAndPurchaseHistory(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/account/PurchaseHistory.do", true, "ExpirationDatesAndPurchaseHistory");
    }

    public String getBaseEntitlement_Mang_Total(){
    	try{
    	return sel.getText("xpath=//tr/td[2]/form/table[5]/tbody/tr[2]/td/strong[3]");
    	}
    	catch(Exception e){
    		return sel.getText("xpath=//tr/td[2]/form/table/tbody/tr[2]/td/strong[3]");
    	}
    }

    public String getBaseEntitlement_Mang_Consumed(){
    	try{
    	return sel.getText("xpath=//tr/td[2]/form/table[5]/tbody/tr[2]/td/strong");
    	}
    	catch(Exception e){
    	return sel.getText("xpath=//tr/td[2]/form/table/tbody/tr[2]/td/strong");
    	}
    }

    public String getBaseEntitlement_Provisioning_Total(){
    	try{
    	return sel.getText("xpath=//tr/td[2]/form/table[6]/tbody/tr/td/strong[3]");
    	//xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[6]/tbody/tr/td/strong[3]
    	}
    	catch(Exception e){
    								   //html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]/tbody/tr/td/strong[3]
    		return sel.getText("xpath=//tr/td[2]/form/table[2]/tbody/tr/td/strong[3]");
    		//xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]/tbody/tr/td/strong[3]
    	}
    }

    public String getBaseEntitlement_Provisioning_Consumed(){
    	try{
    	return sel.getText("xpath=//tr/td[2]/form/table[6]/tbody/tr/td/strong");
    	//xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[6]/tbody/tr/td/strong
    	}
    	catch(Exception e){
    	return sel.getText("xpath=//tr/td[2]/form/table[2]/tbody/tr/td/strong");
    	//xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]/tbody/tr/td/strong")
    	}
    }

    public void select_NumberOfEntries(String number){
    	rh.selectComboBoxItem("name=pagesize", number, false);
    }
    
    public void select_AddOnEntitlement(String entitlement){
    	rh.selectComboBoxItem("name=addOnEntitlement", entitlement, false);
    }

	 public void clickButton_SavePreferences(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Save Preferences']",true);
	 }
	 	 
	 public void clickButton_SavePreferencesInGerman(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Präferenzen speichern']",true);
	 }
	 
	 public void clickButton_Update(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Update']",true);
	 }

	 public void clickButton_Unentitle(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Unentitle']",true);
	 }

	 public void clickButton_SetToUpdateEntitled(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Set to Update Entitled']",true);
	 }

	 public void clickButton_SetToManagementEntitled(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Set to Management Entitled']",true);
	 }
	 public void clickButton_RemoveEntitlement(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Remove Entitlement']",true);
	 }
	 public void clickButton_AddEntitlement(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Add Entitlement']",true);
	 }
	 public void clickButton_Submit(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Submit']",true);
	 }
	 
	 public void checkBox_LanguageSetting(boolean check, String language){
		 	String path = "xpath=//input[@value='"+ language + "']";
			rh.checkRadioButton(path,check);
	}

	 public void checkBox_Tasks(boolean check){
			rh.checkRadioButton("xpath=//input[@value='tasks']",check);
	}

	 public void checkBox_MostCriticalSystems(boolean check){
			rh.checkRadioButton("xpath=//input[@value='critical-systems']",check);
	}
	 public void checkBox_SystemGroups(boolean check){
			rh.checkRadioButton("xpath=//input[@value='system-groups-widget']",check);
	}
	 public void checkBox_RelevantSecurityErrata(boolean check){
			rh.checkRadioButton("xpath=//input[@value='latest-errata']",check);
	}
	 public void checkBox_InactiveSystems(boolean check){
			rh.checkRadioButton("xpath=//input[@value='inactive-systems']",check);
	}
	 public void checkBox_RecentlyScheduledActions(boolean check){
			rh.checkRadioButton("xpath=//input[@value='pending-actions']",check);
	}
	 public void checkBox_RecentlyRegisteredSystems(boolean check){
			rh.checkRadioButton("xpath=//input[@value='recently-registered-systems']",check);
	}

	 public void checkBox_OrganizationAdmin(boolean check){
			rh.checkRadioButton("xpath=//input[@value='org_admin']",check);
	}
	 public void checkBox_ConfigAdmin(boolean check){
			rh.checkRadioButton("xpath=//input[@value='config_admin']",check);
	}
	 public void checkBox_SystemGroupAdmin(boolean check){
			rh.checkRadioButton("xpath=//input[@value='system_group_admin']",check);
	}
	 public void checkBox_ActivationKeyAdmin(boolean check){
			rh.checkRadioButton("xpath=//input[@value='activation_key_admin']",check);
	}
	 public void checkBox_ChannelAdmin(boolean check){
			rh.checkRadioButton("xpath=//input[@value='channel_admin']",check);
	}
	 public void setTxt_FirstName(String txt){
			sel.setText("xpath=//input[@name='firstNames']", txt);
	    }

	 public void setTxt_LastName(String txt){
			sel.setText("xpath=//input[@name='lastName']", txt);
	    }

	 public void setTxt_Position(String txt){
			sel.setText("xpath=//input[@name='title']", txt);
	    }
	 public void setTxt_Address(String txt){
			sel.setText("xpath=//input[@name='address1']", txt);
	    }
	 public void setTxt_Phone(String txt){
			sel.setText("xpath=//input[@name='phone']", txt);
	    }
	 public void setTxt_City(String txt){
			sel.setText("xpath=//input[@name='city']", txt);
	    }
	 public void setTxt_State(String txt){
			sel.setText("xpath=//input[@name='state']", txt);
	    }
	 public void setTxt_Zip(String txt){
			sel.setText("xpath=//input[@name='zip']", txt);
	    }

	 public void setTxt_Email(String txt){
			sel.setText("xpath=//input[@name='email']", txt);
	    }


	 public void clickLink_EditThisAddress(){
			rh.clickLink("link=Edit this address",true);
		}




    private static void loginHelper(String user, String password, ExtendedSelenium sel) {
        sel.type("username", user);
        log.info("USER_NAME= " +user);
        sel.type("password", password);
        log.info("PASSWORD= " +password);
        try{
        	sel.clickAndWait(HarnessConfiguration.EVENT_ID_SUBMIT);
        }
        	catch(SeleniumException se){
        		log.finest("RHN web submit button not found, will try satellite sign_in button");
        		//selenium.click("value= Sign In ");
        		rh.clickButton("xpath=//input[@type='submit']",true);

        }
        log.info("Click: Log In");
        //task_RhnBase.sleepForSeconds(5);
        
    }

    public void loginRHN() {
        loginHelper(getRhnUserName(), getRhnUserPassword(), sel);
    }


    public void loginRHN(String user, String password) {
        loginHelper(user, password, sel);
    }
    
    
    public void clickLink_OrganizationalTrusts(){
		throw new SeleniumException("wrong page version");
	}
    
    public void clickLink_OrganizationalTrusts_Details(){
    	throw new SeleniumException("wrong page version");
	}
    
    public void clickLink_OrganizationalTrusts_Channels_Provided(){
    	throw new SeleniumException("wrong page version");
	}
    
    public void clickLink_OrganizationalTrusts_Channels_Consumed(){
    	throw new SeleniumException("wrong page version");
	}

}
