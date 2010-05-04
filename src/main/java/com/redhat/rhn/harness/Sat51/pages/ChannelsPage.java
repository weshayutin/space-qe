package com.redhat.rhn.harness.Sat51.pages;

import com.redhat.rhn.harness.common.RhnHelper;


public class ChannelsPage extends com.redhat.rhn.harness.Sat50.pages.ChannelsPage {

	RhnHelper rh = new RhnHelper();

	//public static String XPATH_CFG_MANG_HOSTED_CENT_CHANNEL_COMPARE="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]";
	public String XPATH_LIST_TOTAL_PACKAGES="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table[4]/tbody/tr/td[2]/strong[3]";

	public void clickButton_PaginateNext(){
    	rh.clickLink("alt=Next", true);
	}
	
	public void clickButton_PaginateLast(){
		rh.clickLink("alt=Last", true);
	}
	
	public void clickButton_PaginateFirst(){
		rh.clickLink("alt=First", true);
	}
	
	public void clickButton_PaginatePrevious(){
		rh.clickLink("alt=Prev", true);
	}
	
	public void click_channelPatches(){
		rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[2]/ul/li[4]/a","Patches",true);
	}
	
	public void click_channelPatchClusters(){
		rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div/ul/li[5]/a","Patchsets",true);
	}
	
	public void click_listRemovePatchsets(){
		rh.clickLink("link=List/Remove Patchsets",true);
	}

}
