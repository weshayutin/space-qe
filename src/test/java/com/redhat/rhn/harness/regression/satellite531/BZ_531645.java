package com.redhat.rhn.harness.regression.satellite531;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

/**
 * 
 * @see Errata: https://errata.devel.redhat.com/errata/info/9051
 * @see Bugzilla: https://bugzilla.redhat.com/show_bug.cgi?id=531645
 * @author gkhachik
 *
 */
public class BZ_531645 extends SeleniumSetup{
	protected RhnHelper rh = new RhnHelper();
	public static final String sysgroup = "auto-sg-bz531645";
	
	@BeforeClass(groups = { "setup" })
	public void test00_preparing(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.deleteSysGroup(sysgroup);
		task_TestPrep.removeAllProfilesOfASystem(IRhnBase.SYSTEM_HOSTNAME01);
		task_RhnBase.SignOut();
	}
	
	@Test(groups = { "bz-531645" })
	public void test01_createSysGroupAndJoinSystem() throws Exception{
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.registerSystemToSatellite(
				IRhnBase.SYSTEM_HOSTNAME01, true, false);
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_SystemGroups();
		page_SatelliteSystems.clickLink_CreateNewGroup();
		page_SatelliteSystems.setText_SystemGroup_Name(sysgroup);
		page_SatelliteSystems.setText_SystemGroup_Description("Description: "+sysgroup);
		page_SatelliteSystems.clickButton_CreateGroup();
		task_RhnBase.goToSystem(IRhnBase.SYSTEM_HOSTNAME01);
		rh.clickLink("link="+IRhnBase.SYSTEM_HOSTNAME01, true);
		rh.clickLink("xpath=//ul[@class='content-nav-rowone']/li[3]/a[text()='Groups']", "Groups", true);
		rh.clickLink("xpath=//ul[@class='content-nav-rowtwo']/li[2]/a[text()='Join']", "Join to System Group", true);
		joinToSysGroup();		
		task_RhnBase.SignOut();
	}

	@Test(dependsOnMethods="test01_createSysGroupAndJoinSystem", 
			groups = { "bz-531645" })
	public void test02_sortErratasByDateForChannel() throws ParseException{
		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_SystemGroups();
		int ipos = iterateToPageForSysGroup();
		assertTrue(!sel.isElementPresent("xpath=//table[@class='list']/tbody/tr["+ipos+"]/td[2]/a/img[@alt='No Applicable Errata']"));
		assertTrue(!sel.isElementPresent("xpath=//table[@class='list']/tbody/tr["+ipos+"]/td[2]/a/img[@src='/img/icon_up2date.gif']"));
	}	

	private void joinToSysGroup() throws Exception{
		int iPos = iterateToPageForSysGroup();
		sel.checkUncheck("xpath=//table[@class='list']/tbody/tr["+iPos+"]/td[1]/input[@type='checkbox']", true);
		page_SatelliteSystems.clickButton_JoinSelectedGroups();
		assertTrue(rh.isTextPresent("1 system groups added."));
	}
	
	/**
	 * Iterates and returns the id in the current table with a location of the current page 
	 * (if there are multi pages for the system groups).   
	 * @return the position in the table of the system group in current page. 
	 * @throws ParseException Exception if something goes wrong
	 */
	private int iterateToPageForSysGroup() throws ParseException{
		int sysGroupCount = NumberFormat.getInstance().parse(sel.getText("id=list_total").trim()).intValue();
		int sysgroupsInPage = NumberFormat.getInstance().parse(sel.getText("id=list_max").trim()).intValue();
		int pagesCount = sysGroupCount / sysgroupsInPage;
		if(sysGroupCount%sysgroupsInPage > 0) pagesCount++;
		for(int i=0;i<pagesCount;i++){
			for(int j=1;j<=sysgroupsInPage;j++){
				// for the last page there could be less elements than the sysgroupsInPage has, but it's ok
				// as we are using the isElementPresent method...
				if(sel.isElementPresent("xpath=//table[@class='list']/tbody/tr["+j+"]/td[2]")){
					if(sel.getText("xpath=//table[@class='list']/tbody/tr["+j+"]/td[2]").equals(sysgroup)){
						return j;
					}
				}
			}
			if(sel.isElementPresent("xpath=//input[@type='image' and @alt='Next Page']"))
				sel.clickAndWait("xpath=//input[@type='image' and @alt='Next Page']");
		}
		return -1;
	}
}
