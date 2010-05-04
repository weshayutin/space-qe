package com.redhat.rhn.harness.regression.satellite531;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

/**
 * 
 * @see Errata: https://errata.devel.redhat.com/errata/info/9051
 * @see Bugzilla: https://bugzilla.redhat.com/show_bug.cgi?id=527881
 * @author gkhachik
 *
 */
public class BZ_527881 extends SeleniumSetup{
	protected RhnHelper rh = new RhnHelper();
	protected static String autoBaseChannel01 = "autobasechannel1";
	
	@BeforeClass(groups = { "setup" })
	public void test00_preparing(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.deleteCustomChannel(autoBaseChannel01);
		task_Channels.createChannelClone(IRhnBase.RHN_CHANNEL01, 
				autoBaseChannel01,autoBaseChannel01, 1);
		task_RhnBase.SignOut();
	}
	
	@Test(groups = { "bz-527881" })
	public void test01_mergeChannelsMakeIdentical(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.gotoManageCustomChannel(autoBaseChannel01);
		page_Channels.click_channelPackages();
		page_Channels.click_comparePackages();		
		page_Channels.select_ViewChannel(IRhnBase.RHN_CHANNEL01);
		page_Channels.clickButton_Compare();
		page_Channels.clickButton_MergeDifferences();
		page_Channels.CheckRadio_MakeIdentical(true);
		page_Channels.clickButton_PreviewMerge();
		String spackInfo = sel.getText(
				"xpath=//table[@class='list-pagination']/tbody/tr[1]/td[2]/strong[3]");
		String s = spackInfo.substring(0, spackInfo.indexOf("(")).trim();
		int allPackages = Integer.parseInt(s);
		page_Channels.clickButton_SelectAll();
		assertTrue(rh.isTextPresent("("+allPackages+" selected)"));
		assertTrue(rh.isElementPresent(
				"xpath=//input[@type='submit' and @value='Unselect All']", false));
		// at least there is 2 packages to check if they're selected
		for(int i=0; i<Math.min(2, allPackages);i++){
			assertTrue(rh.isElementPresent(
					"xpath=//table[@class='list']/tbody/tr["+(i+1)+
					"]/td[@class='list-checkbox']/" +
					"input[@type='checkbox' and @checked='1']", false));				
		}
	}
}
