package com.redhat.rhn.harness.Sat42.pages;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.thoughtworks.selenium.SeleniumException;

/**
 * Collection of objects (links,buttons,etc..) with RHN base,custom and cloned channels
 * @author whayutin
 *
 */
public class ChannelsPage extends RhnPage {
	
	RhnHelper rh = new RhnHelper();

	//public static String XPATH_CFG_MANG_HOSTED_CENT_CHANNEL_COMPARE="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]";
	public String XPATH_LIST_TOTAL_PACKAGES="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table[4]/tbody/tr/td[2]/strong[3]";


	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_CHANNELS_PAGE;
    }

	public void click_SoftwareChannelsLink(){
        rh.clickLink("link=Software Channels",true);
    }

	public void click_RelevantLink(){
        rh.clickLink("link=Relevant",true);
    }

	public void click_RelevantTab(){
        rh.clickLink("link=Relevant channels",true);
    }

	public void click_AllLink(){
        rh.clickLink("link=All",true);
    }

	public void click_AllChannels(){
        rh.clickLink("link=All channels",true);
    }

	public void click_DeleteSoftwareChannel(){
        rh.clickLink("link=delete software channel",true);
    }

	public void click_RetiredLink(){
        rh.clickLink("link=Retired",true);
    }

	public void click_RetiredTab(){
        rh.clickLink("link=Retired channels",true);
    }

	public void click_DownloadSoftwareLink(){
        rh.clickLink("link=Download Software",true);
    }

	public void click_PackageSearch(){
        rh.clickLink("link=Package Search",true);
    }
	public void click_ManageSoftwareChannels(){
		rh.clickLink("xpath=//a[contains(@href, '/network/software/channels/manage/index.pxt')]", "Manage Software Channels",true);
    }
	
	public void click_Subscribers(){
			rh.clickLink("xpath=//a[contains(@href, '/network/software/channels/manage/subscribers.pxt')]", "Subscribers",true);
	}
	
	public String link_Subscribers(){
		return "xpath=//a[contains(@href, '/network/software/channels/manage/subscribers.pxt')]";
	}

	public void click_CloneChannel(){
        rh.clickLink("link=clone channel",true);
    }

	public void click_createNewChannel(){
        rh.clickLink("link=create new channel",true);
    }

	public void click_listRemovePackages(){
        //rh.clickLink("link=List / Remove Packages",true);
		rh.clickLink("xpath=//tbody/tr/td[2]/ul/li/a", "List / Remove Packages", true);
    }
	
	public void click_listRemovePatches(){
        rh.clickLink("link=List / Remove Patches",true);
    }

	public void click_addPackages(){
        rh.clickLink("link=Add Packages",true);
    }	

	public void click_comparePackages(){
		throw new SeleniumException("wrong version");
    }	
	
	
	public void click_channelDetails(){
        rh.clickLink("xpath=//tr/td[2]/div[2]/ul/li/a","Details",true);
    }

	public void click_channelManagers(){
        rh.clickLink("xpath=//tr/td[2]/div[2]/ul/li[2]/a","Managers",true);
    }

	public void click_channelErrata(){
        rh.clickLink("xpath=//tr/td[2]/div[2]/ul/li[3]/a","Errata",true);
    }

	public void click_channelPackages(){
        rh.clickLink("xpath=//tbody/tr/td[2]/div[2]/ul/li[4]/a","Packages",true);
    }
	
	public void click_channelPatches(){
		rh.clickLink("xpath=//html/body/div/div[4]/table/tbody/tr/td[2]/div[2]/ul/li[4]/a","Patches",true);
	}

	public void select_CloneFrom(String channel){
		//String entireString="label=regexp:\\s+*"+channel.trim()+"*";
		//String entireString="value=*Tools for RHEL*";
		//rh.selectComboBoxItem("clone_from","value="+channel ,false);
		sel.select("clone_from", channel);
		//sel.select("clone_from", "label=regexp:\\s+wesley");
	}
	
	public void select_ParentChannel(String parentChannel){
		rh.selectBoxItem("channel_parent", parentChannel, false);
	}
	
	public void select_ViewChannel(String parentChannel){
		throw new SeleniumException("wrong version");
	}
	
	public void select_Architecture(String architecture){
		rh.selectBoxItem("channel_arch", architecture, false);
	}
	/*public void select_CloneFromPre(String channel){
		rh.selectComboBoxItem("clone_from","label="+channel ,false);
	}
	*/
	/*public void westest(){
		sel.select("clone_from", "label=regexp:\\s+wesley");
	}*/

	public void CheckRadio_ChannelAllErrata(boolean check){
		rh.checkRadioButton("xpath=//input[@value='current']", check);
	}
	public void CheckRadio_ChannelNoErrata(boolean check){
		rh.checkRadioButton("xpath=//input[@value='original']", check);
	}
	public void CheckRadio_SelectErrata(boolean check){
		rh.checkRadioButton("xpath=//input[@value='select_errata']", check);
	}
	
	public void CheckRadio_UnsubscribeSystems(boolean check){
		rh.checkRadioButton("xpath=//input[@name='force_unsubscribe']", "Unsubscribe Systems", check);
	}

	public void CheckRadio_MakeIdentical(boolean check){
		throw new SeleniumException("wrong version");
	}

	public void clickButton_CreateChannel(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Create Channel']", true);
	}
	
	public void clickButton_UpdateChannel(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Update Channel']", true);
	}
	
	public void clickButton_ConfirmRemoval(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Confirm Removal']", true);
	}
	
	public void clickButton_ConfirmAddition(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Confirm Addition']", true);
	}
	
	public void clickButton_ConfirmAddPackages(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Add Package(s)']", true);
	}
	
	public void clickButton_Remove(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Remove']", true);
	}
	
	public void clickButton_ConfirmDeletion(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Confirm Deletion']","Confirm Deletion", true);
	}
	
	public void clickButton_Confirm(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Confirm']", true);
	}

	public void clickButton_Compare(){
		throw new SeleniumException("wrong version");
	}

	public void clickButton_MergeDifferences(){
		throw new SeleniumException("wrong version");
	}

	public void clickButton_PreviewMerge(){
		throw new SeleniumException("wrong version");
	}

	public void clickButton_SelectAll(){
		throw new SeleniumException("wrong version");
	}

	public String Button_Confirm(){
		return "xpath=//input[@type='submit' and @value='Confirm']";
	}

	public void clickButton_DeleteChannel(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Delete Channel']", true);
	}


	public void clickButton_CreateChannel_longWait(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Create Channel']", false);
		//sel.waitForCondition("selenium.isElementPresent(\"link=delete software channel\");", "600000");
		try{
		rh.waitFor("delete software channel", 600);
		}
		catch(SeleniumException se){
			rh.waitFor("delete software channel", 600);
		}


	}

	public void setTxt_ChannelLabel(String txt){
		sel.setText("xpath=//input[@name='channel_name']", txt);
    }
	

	

	public void setTxt_ChannelSummary(String txt){
		sel.setText("xpath=//input[@name='channel_summary']", txt);
    }

	public void setTxt_ChannelName(String txt){
		sel.setText("xpath=//input[@name='new_channel_label']", txt);
    }
	
	
	public void clickButton_PaginateNext(){
    	rh.clickLink("name=Next", true);
	}
	
	public void clickButton_PaginateLast(){
		rh.clickLink("name=Last", true);
	}
	
	public void clickButton_PaginateFirst(){
		rh.clickLink("name=First", true);
	}
	
	public void clickButton_PaginatePrevious(){
		rh.clickLink("name=Prev", true);
	}

	public void click_channelPatchClusters(){
		throw new SeleniumException("wrong page version");
	}
	
	public void click_listRemovePatchsets(){
		throw new SeleniumException("wrong page version");
	}
	
	public void CheckRadio_OrgSharing_private(boolean check){
		throw new SeleniumException("wrong version");
	}
	
	public void CheckRadio_OrgSharing_protected(boolean check){
		throw new SeleniumException("wrong version");
	}
	
	public void CheckRadio_OrgSharing_public(boolean check){
		throw new SeleniumException("wrong version");
	}
	
	public void CheckRadio_OrgSharing_AllUsers(boolean check){
		throw new SeleniumException("wrong version");
	}
	
	public void CheckRadio_OrgSharing_SelectedUsers(boolean check){
		throw new SeleniumException("wrong version");
	}

	public void setTxt_ClonedChannelName(String txt){
		throw new SeleniumException("wrong version");
    }
	
	public void setTxt_ClonedChannelLabel(String txt){
		throw new SeleniumException("wrong version");
    }
	
	public void setTxt_ClonedChannelSummary(String txt){
		throw new SeleniumException("wrong version");
    }
	
	public void clickButton_DenyAccessAndConfirm(){
		throw new SeleniumException("wrong version");
	}
	
	public void clickButton_GrantAccessAndConfirm(){
		throw new SeleniumException("wrong version");
	}

	public void CheckBox_ChannelOrgAccess(boolean check, String orgname){
		throw new SeleniumException("wrong version");
	}
	
	public void clickButton_ModifyAccess(){
		throw new SeleniumException("wrong version");
	}

	public void click_channelAddErrata(){
		throw new SeleniumException("wrong version");
	}

	public void click_addErrata(String errataType){
		throw new SeleniumException("wrong version");
	}
    
	public void click_channelListRemoveErrata(){
		throw new SeleniumException("wrong version");
	}

	public void setTxt_FilterBySynopsis(String txt){
		throw new SeleniumException("wrong version");
	}
	
	public void clickButton_CloneErrata(){
		throw new SeleniumException("wrong version");
	}
}
