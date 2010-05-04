package com.redhat.rhn.harness.Space01.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class ActivationKeysPage extends com.redhat.rhn.harness.Sat51.pages.ActivationKeysPage {

	RhnHelper rh = new RhnHelper();

	public void checkBox_UniversalDefault(boolean check){
		rh.checkRadioButton("xpath=//input[@name='universal']",check);
	}
	public void clickLink_SubscribeToChannels(){
		rh.clickLink("link=Subscribe to Channels",true);
	}
	public void clickLink_ListUnsubscribeFromChannels(){
		rh.clickLink("link=List/Unsubscribe from Channels",true);
	}
	public void clickLink_ViewModifyRankings(){
		rh.clickLink("link=View/Modify Rankings",true);
	}	
	public void checkBox_firstConfigChannel(boolean check){
		// may also not exist the search result.
		if(rh.isElementPresent("xpath=//table[@class='list']/tbody/tr[1]/td[@class='first-column']/input[@type='checkbox']", false))
			rh.checkRadioButton("xpath=//table[@class='list']/tbody/tr[1]/td[@class='first-column']/input[@type='checkbox']",check);
	}
	public void clickButton_ContinueFilterConfigChannel(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Continue' and @name='dispatch']",true);
    }
	public void clickButton_UpdateRankingConfigChannel(){
		if(rh.isElementPresent("xpath=//input[@type='submit' and @value='Update Channel Rankings']", false))
			rh.clickButton("xpath=//input[@type='submit' and @value='Update Channel Rankings']",true);
	}
	public void clickImage_ChangeRankingUp(){
		if(rh.isElementPresent("xpath=//input[@class='button' and @type='image' and @value='up']", false)){
			rh.clickImage("xpath=//input[@class='button' and @type='image' and @value='up']", "up", false);
		}
	}
	public void clickImage_ChangeRankingDown(){
		if(rh.isElementPresent("xpath=//input[@class='button' and @type='image' and @value='down']", false)){
			rh.clickImage("xpath=//input[@class='button' and @type='image' and @value='down']", "down", false);
		}
	}
	
}
