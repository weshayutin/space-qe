package com.redhat.rhn.harness.Sat51.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class ActivationKeysPage extends com.redhat.rhn.harness.Sat50.pages.ActivationKeysPage {

	RhnHelper rh = new RhnHelper();

	public String getLocation() {
		 return "https://"+HarnessConfiguration.RHN_HOST + "/rhn/activationkeys/List.do";
		// http://rlx-2-04.rhndev.redhat.com/rhn/activationkeys/List.do
	}

	public void setTxt_Description(String txt){
		sel.setText("xpath=//input[@name='description']", txt);
    }

	public void setTxt_Key(String txt){
		sel.setText("xpath=//input[@name='key']", txt);
    }

	public void setTxt_UsageLimit(String txt){
		sel.setText("xpath=//input[@name='usageLimit']", txt);
    }

	public void select_BaseChannel(String item){
		rh.selectComboBoxItem("selectedChannel", item, false);
	}


	public void checkBox_UniversalDefault(boolean check){
		rh.checkRadioButton("xpath=//input[@name='universal']",check);
	}
	
	//Create Activation Key
	public void clickButton_CreateKey(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Create Activation Key']",true);
    }
	public void clickButton_UpdateActivationKey(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Update Activation Key']",true);
    }
	
	public void clickButton_UpdateKey(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Update Key']",true);
    }
	
	public void clickButton_Delete(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Delete Activation Key']",true);
    }
	
}
