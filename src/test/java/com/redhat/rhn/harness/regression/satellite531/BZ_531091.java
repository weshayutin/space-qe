package com.redhat.rhn.harness.regression.satellite531;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

/**
 * 
 * @see Errata: https://errata.devel.redhat.com/errata/info/9051
 * @see Bugzilla: https://bugzilla.redhat.com/show_bug.cgi?id=531091
 * @author gkhachik
 *
 */
public class BZ_531091 extends SeleniumSetup{
	protected RhnHelper rh = new RhnHelper();
	protected static String autoBaseChannel01 = "autobasechannel-bz531091";
	
	@BeforeClass(groups = { "setup" })
	public void test00_preparing(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.deleteCustomChannel(autoBaseChannel01);
		task_Channels.createChannelClone(IRhnBase.RHN_CHANNEL01, 
				autoBaseChannel01,autoBaseChannel01, 1);
		task_RhnBase.SignOut();
	}
	
	@Test(groups = { "bz-531091" })
	public void test01_applyErratasToClonedChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.gotoManageCustomChannel(autoBaseChannel01);
		page_Channels.click_channelErrata();
		page_Channels.click_channelAddErrata();
		page_Channels.click_addErrata("Add Red Hat Errata");
		page_Channels.setTxt_FilterBySynopsis("critical");
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_SelectAll_CheckBox();
		page_RhnCommon.clickButton_Confirm();
		page_Channels.clickButton_CloneErrata();
		assertTrue(rh.isTextPresent("The cloned Errata should appear in the channel " +
				"and applicable to systems within a few minutes."));
	}
	
	@Test(dependsOnMethods="test01_applyErratasToClonedChannel", groups = { "bz-531091" })
	public void test02_listErratasOfClonedChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.gotoManageCustomChannel(autoBaseChannel01);
		page_Channels.click_channelErrata();
		page_Channels.click_channelListRemoveErrata();
		assertTrue(rh.isElementPresent("xpath=//table[@class='list']/tbody/tr[1]/td[contains(text(),'Critical')]", false));
		int iCnt = Integer.parseInt(sel.getText("xpath=//strong[@id='list_total']"));
		assertTrue(iCnt>0);
	}
	
}
