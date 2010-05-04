package com.redhat.rhn.harness.Space01.pages;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.thoughtworks.selenium.SeleniumException;

/**
 * Collection of objects (links,buttons,etc..) with RHN base,custom and cloned channels
 * @author whayutin
 *
 */
public class ChannelsPage extends com.redhat.rhn.harness.Sat51.pages.ChannelsPage {

	RhnHelper rh = new RhnHelper();

	//public static String XPATH_CFG_MANG_HOSTED_CENT_CHANNEL_COMPARE="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]";
	public String XPATH_LIST_TOTAL_PACKAGES="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table[4]/tbody/tr/td[2]/strong[3]";


	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + "/rhn/software/channels/All.do";
    }
	
	public void click_AllChannels(){
        rh.clickLink("link=All Channels",true);
        //rh.clickLink("xpath=//a[contains(@href, '/channels/All.do)]", "All Channels",true);
        }
	
	public void select_Architecture(String architecture){
		rh.selectBoxItem("arch", architecture, false);
	}
	
	public void select_ParentChannel(String parentChannel){
		if(rh.isElementPresent("parent", false))
			rh.selectBoxItem("parent", parentChannel, false);
		else if (rh.isElementPresent("channel_parent", false)){
			rh.selectBoxItem("channel_parent", parentChannel, false);
		}
	}
	
	public void select_ViewChannel(String parentChannel){
		rh.selectBoxItem("view_channel", parentChannel, false);
	}

	public void setTxt_ChannelName(String txt){
		sel.setText("xpath=//input[@name='name']", txt);
    }
	
	
	public void setTxt_ChannelLabel(String txt){
		sel.setText("xpath=//input[@name='label']", txt);
    }
	
	public void setTxt_ChannelSummary(String txt){
		sel.setText("xpath=//input[@name='summary']", txt);
    }
	
	public void setTxt_ClonedChannelName(String txt){
		sel.setText("xpath=//input[@name='channel_name']", txt);
    }
	
	public void setTxt_ClonedChannelLabel(String txt){
		sel.setText("xpath=//input[@name='new_channel_label']", txt);
    }
	
	public void setTxt_ClonedChannelSummary(String txt){
		sel.setText("xpath=//input[@name='channel_summary']", txt);
    }
	
	public void setTxt_FilterBySynopsis(String txt){
		assertTrue(rh.isTextPresent("Filter by Synopsis"));
    	String locator = "xpath=//td[starts-with(normalize-space(.),'Filter')]/input[@type='text']";
    	//this is generic for systems, packages, and other
         if(rh.isElementPresent(locator, true)){
    		sel.setText(locator, "Filter by Synopsis:", txt);
    	}
	}

	public void CheckRadio_OrgSharing_private(boolean check){
		rh.checkRadioButton("xpath=//input[@value='private']", check);
	}
	
	public void CheckRadio_OrgSharing_protected(boolean check){
		rh.checkRadioButton("xpath=//input[@value='protected']", check);
	}
	
	public void CheckRadio_OrgSharing_public(boolean check){
		rh.checkRadioButton("xpath=//input[@value='public']", check);
	}
	
	public void CheckRadio_OrgSharing_AllUsers(boolean check){
		rh.checkRadioButton("xpath=//input[@value='all']", check);
	}
	
	public void CheckRadio_OrgSharing_SelectedUsers(boolean check){
		rh.checkRadioButton("xpath=//input[@value='selected']", check);
	}
	
	public void clickButton_DenyAccessAndConfirm(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Deny Access and Confirm']", true);
	}
	
	public void clickButton_GrantAccessAndConfirm(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Grant Access and Confirm']", true);
	}
	
	public void clickButton_ModifyAccess(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Modify Access']", true);
	}

	public void clickButton_Compare(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Compare']", true);
	}

	public void clickButton_MergeDifferences(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Merge Differences']", true);
	}
		
	public void clickButton_PreviewMerge(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Preview Merge']", true);
	}

	public void clickButton_SelectAll(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Select All']", true);
	}

	public void clickButton_CloneErrata(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Clone Errata']","Clone Errata", true);
	}

	public void CheckBox_ChannelOrgAccess(boolean check, String orgname){
		rh.checkRadioButton("xpath=//*[@id=\"list_*\"]",orgname ,check);
	}
	
	public void click_channelPatches(){
		//TODO Make this xpath more robust
		rh.clickLink("xpath=//html/body/div/div[2]/div/table/tbody/tr/td[2]/div[2]/ul/li[4]/a","Patches",true);
	}
	
	public void click_channelPatchClusters(){
		//TODO Make this xpath more robust
		rh.clickLink("xpath=//html/body/div/div[2]/div/table/tbody/tr/td[2]/div[2]/ul/li[5]/a","Patch Clusters",true);
	}
	
	public void click_comparePackages(){
        rh.clickLink("link=Compare Packages",true);
    }	
	
	public void click_channelAddErrata(){
		rh.clickLink("link=Add Errata",true);
	}
	
	/**
	 * @param errataType: current version specific values are - 
	 * "Add Red Hat Errata", "Add Custom Errata", "Create Custom Errata"
	 */
	public void click_addErrata(String errataType){
		rh.clickLink("link="+errataType,true);
	}

	public void click_channelListRemoveErrata(){
		rh.clickLink("link=List/Remove Errata",true);
	}

	public void CheckRadio_UnsubscribeSystems(boolean check){
		rh.checkRadioButton("xpath=//input[@name='unsubscribeSystems']", "Unsubscribe Systems", check);
	}
	
	public void CheckRadio_MakeIdentical(boolean check){
		rh.checkRadioButton("xpath=//input[@name='sync_type']", "Make identical to comparing channel", check);
	}
	
	// LEFT HAND SIDE NAV ELEMENTS
	
	public void click_ManageSoftwareChannels(){
		rh.clickLink("xpath=//div[@id='sidenav']//a[normalize-space(.)=" +
				"'Manage Software Channels']", "Manage Software Channels",true);
    }
	
}
