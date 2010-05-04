package com.redhat.rhn.harness.Sat50.pages;

import com.redhat.rhn.harness.common.RhnHelper;


public class ChannelsPage extends com.redhat.rhn.harness.Sat42.pages.ChannelsPage {

	RhnHelper rh = new RhnHelper();

	//public static String XPATH_CFG_MANG_HOSTED_CENT_CHANNEL_COMPARE="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]";
	public String XPATH_LIST_TOTAL_PACKAGES="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table[4]/tbody/tr/td[2]/strong[3]";

	public void click_channelPatches(){
		rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[2]/ul/li[4]/a","Patches",true);
	}
}
