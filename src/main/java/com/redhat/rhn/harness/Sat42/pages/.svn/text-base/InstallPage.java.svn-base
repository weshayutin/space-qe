package com.redhat.rhn.harness.Sat42.pages;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class InstallPage extends RhnPage {

	RhnHelper rh = new RhnHelper();

	public String getLocation(){
        return HarnessConfiguration.RHN_HOST;
    }


	public void setTxt_DesiredLogin(String txt){
		sel.setText("login",txt);
	}

	public void setTxt_DesiredPassword(String txt){
		sel.setText("desiredpassword",txt);
	}

	public void setTxt_ConfirmPassword(String txt){
		sel.setText("desiredpasswordConfirm",txt);
	}

	public void select_Prefix(String prefix){
		rh.selectComboBoxItem("prefix", prefix, false);
	}


	public void setTxt_FirstName(String txt){
		sel.setText("firstNames",txt);
	}

	public void setTxt_LastName(String txt){
		sel.setText("lastName",txt);
	}

	public void setTxt_Email(String txt){
		sel.setText("email",txt);
	}

	public void clickButton_CreateLogin(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Create Login']",true);
    }

}
