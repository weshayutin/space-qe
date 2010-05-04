package com.redhat.rhn.harness.Sat42.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.RhnHelper;
import com.thoughtworks.selenium.SeleniumException;
/**
 * Collection of objects (links,buttons,etc..) used with activation keys
 * @author whayutin
 *
 */
public class ActivationKeysPage extends RhnPage {

	RhnHelper rh = new RhnHelper();

	public String getLocation() {
		 return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_ACTIVATIONKEY_PAGE;
	}

	public static String XPATH_ACTIVATION_KEY="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]";

	public void clickLink_CreateNewKey(){
		rh.clickLink("link=create new key",true);
	}

	public void clickLink_DeleteKey(){
		rh.clickLink("link=delete key",true);
	}

	public void clickButton_Delete(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Delete']",true);
    }

	public void clickLink_Details(){
		rh.clickLink("link=Details",true);
	}

	public void clickLink_ChildChannels(){
		rh.clickLink("link=Child Channels",true);
	}

	public void clickLink_Packages(){
		rh.clickLink("link=Packages",true);
	}

	public void clickLink_Configuration(){
		rh.clickLink("xpath=//a[contains(@href, '/rhn/activationkeys/configuration/List.do')]", "Configuration",true);
	}
	

	public void clickLink_Groups(){
		rh.clickLink("link=Groups",true);
	}

	public void clickLink_ActivatedSystem(){
		rh.clickLink("link=Activated Systems",true);
	}

	public void setTxt_Description(String txt){
		sel.setText("xpath=//input[@name='token:note']", txt);
    }

	public void setTxt_Key(String txt){
		sel.setText("xpath=//input[@name='token:key']", txt);
    }

	public void setTxt_UsageLimit(String txt){
		sel.setText("xpath=//input[@name='token:usage_limit']", txt);
    }

	public void select_BaseChannel(String item){
		rh.selectComboBoxItem("token_base_channel", item, false);
	}

	public void checkBox_Monitoring(boolean check){
			rh.checkRadioButton("xpath=//input[@value='monitoring_entitled']",check);
	}

	public void checkBox_Provisioning(boolean check){
		rh.checkRadioButton("xpath=//input[@value='provisioning_entitled']",check);
	}

	public void checkBox_Virtualization(boolean check){
		rh.checkRadioButton("xpath=//input[@value='virtualization_host']",check);
	}

	public void checkBox_VirtualizationPlatform(boolean check){
		rh.checkRadioButton("xpath=//input[@value='virtualization_host_platform']",check);
	}

	public void select_UniversalDefault(String item){
		rh.selectComboBoxItem("org_default", item, false);
	}

	public void clickButton_CreateKey(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Create Key']",true);
    }

	public void clickButton_UpdateActivationKey(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Update Key']",true);
    }

	public void selectChildChannel(String value){
		rh.selectBoxItem("token_child_channels", value, false);
	}

	public void setTxt_EnterPackageNames(String txt){
		//sel.setText("xpath=//input[@name='packages']", txt);
		sel.type("packages", txt);
    }

	public void selectGroup(String value){
		rh.selectBoxItem("token_groups", value, false);
	}
	
	public void checkBox_UniversalDefault(boolean check){
		throw new SeleniumException("using the wrong version");
	}
	
	public void clickButton_UpdateKey(){
        throw new SeleniumException("wrong version of page");
    }
	/**
	 * @since Space01
	 */
	public void clickLink_SubscribeToChannels(){
		// Not sure, but the necessity to use it comes in Space01 package.
		throw new SeleniumException("using the wrong version");
	}
	/**
	 * @since Space01
	 */
	public void clickLink_ListUnsubscribeFromChannels(){
		throw new SeleniumException("using the wrong version");
	}
	/**
	 * @since Space01
	 */
	public void clickLink_ViewModifyRankings(){
		throw new SeleniumException("using the wrong version");
	}	
	/**
	 * @since Space01
	 * @param check
	 */
	public void checkBox_firstConfigChannel(boolean check){
		throw new SeleniumException("using the wrong version");
	}
	/**
	 * @since Space01
	 */
	public void clickButton_ContinueFilterConfigChannel(){
		throw new SeleniumException("using the wrong version");
    }
	/**
	 * @since Space01
	 */
	public void clickButton_UpdateRankingConfigChannel(){
		throw new SeleniumException("using the wrong version");
	}
	/**
	 * @since Space01
	 */
	public void clickImage_ChangeRankingUp(){
		throw new SeleniumException("using the wrong version");
	}
	/**
	 * @since Space01
	 */
	public void clickImage_ChangeRankingDown(){
		throw new SeleniumException("using the wrong version");
	}












}
