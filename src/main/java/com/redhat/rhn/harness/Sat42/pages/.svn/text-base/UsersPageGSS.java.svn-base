package com.redhat.rhn.harness.Sat42.pages;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

/**
 * Users page for RHN Hosted Environments, included single sign on objects
 * @author whayutin
 *
 */
public class UsersPageGSS extends RhnPage{

	RhnHelper rh = new RhnHelper();

	 public String getLocation(){
	        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_USERS_PAGE;
	    }

	public void open(){
   	rh.openLink(HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_USERS_PAGE,true, "Users");
   }

	public void clickLink_CreateNewUser(){
		rh.clickLink("link=create new user",true);
	}

	public void setTxt_DesiredLogin(String txt){
		sel.setText("xpath=//input[@name='login']", txt);
   }
	public void setTxt_DesiredPassword(String txt){
		sel.setText("xpath=//input[@name='desiredpassword']", txt);
   }
	public void setTxt_ConfirmPassword(String txt){
		sel.setText("xpath=//input[@name='desiredpasswordConfirm']", txt);
   }

	public void setTxt_FirstName(String txt){
		sel.setText("xpath=//input[@name='personalInfo.firstName']", txt);
   }
	public void setTxt_LastName(String txt){
		sel.setText("xpath=//input[@name='personalInfo.lastName']", txt);
   }
	public void setTxt_Address(String txt){
		sel.setText("xpath=//input[@name='personalSite.address.address1']", txt);
   }
public void setTxt_Phone(String txt){
		sel.setText("xpath=//input[@name='personalSite.contactInfo.phoneNumber']", txt);
   }
public void setTxt_City(String txt){
		sel.setText("xpath=//input[@name='personalSite.address.city']", txt);
   }
public void setTxt_State(String txt){
		sel.setText("xpath=//input[@name='state']", txt);
   }
public void setTxt_Zip(String txt){
		sel.setText("xpath=//input[@name='personalSite.address.postalCode']", txt);
   }

public void setTxt_Email(String txt){
		sel.setText("xpath=//input[@name='email']", txt);
   }


public void clickLink_EditThisAddress(){
		rh.clickLink("link=Edit this address",true);
	}

public void select_State(String state){
	rh.selectComboBoxItem("name=personalSite.address.state", state, false);
}

public void clickButton_CreateLogin(){
    rh.clickButton("xpath=//input[@type='submit' and @value='Create Login']",true);
}
public void clickButton_Continue(){
    rh.clickButton("xpath=//input[@type='submit' and @value='Continue']",true);
}

public void clickButton_Finish(){
    rh.clickButton("xpath=//input[@type='submit' and @value='Finish']",true);
}

public void checkBox_ConsentToTerms(boolean check){
		rh.checkRadioButton("xpath=//input[@value='true']",check);
}


}
