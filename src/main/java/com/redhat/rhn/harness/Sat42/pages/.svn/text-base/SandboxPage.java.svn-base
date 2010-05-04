package com.redhat.rhn.harness.Sat42.pages;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.RhnHelper;

/**
 * This is just a playpen or sandbox to play around in.
 * @author whayutin
 *
 */
public class SandboxPage extends RhnPage{
	RhnHelper rh = new RhnHelper();


	public String getLocation(){
        return "http://popuptest.com";
    }

	public void clickLink_MultiPopUpTest2(){
		rh.clickLink("link=Multi-PopUp Test #2", true);
	}

	public void clickLink_ComeAndGo(){
		rh.clickLink("link=Come & Go Test", true);
	}

	public void clickLink_Shannon(){
		rh.clickLink("link=Come & Go Test", true);
	}

	public void setTxt_JbossUser(String txt){
		sel.setText("xpath=//input[@name='j_username']", txt);
    }
	public void setTxt_JbossPass(String txt){
		sel.setText("xpath=//input[@name='j_password']", txt);
    }

	public void clickButton_JbossLogin(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Login']",true);
    }
}
