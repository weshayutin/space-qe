package com.redhat.rhn.harness.regression.satellite531;

import java.text.ParseException;
import java.util.StringTokenizer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

/**
 * 
 * @see Errata: https://errata.devel.redhat.com/errata/show/9089
 * @see Bugzilla: https://bugzilla.redhat.com/show_bug.cgi?id=537094
 * @author gkhachik
 *
 */
public class BZ_537094 extends SeleniumSetup{
	protected RhnHelper rh = new RhnHelper();
	protected static final String cmd_yumlistsec_cves = "yum list-sec cves"; 
	protected static String autoBaseChannel01 = "clonebase-bz-537094";
	protected static String actKeyForBaseChannel01 = "actKey-bz-537094";
	
	@BeforeClass(groups = { "setup" })
	public void test00_preparing(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.removeAllProfilesOfASystem(IRhnBase.SYSTEM_HOSTNAME01);
		task_ActivationKeys.deleteActivationKey(actKeyForBaseChannel01, false);
		task_Channels.deleteCustomChannel(autoBaseChannel01);
		task_Channels.createChannelClone(IRhnBase.RHN_CHANNEL01, 
				autoBaseChannel01,autoBaseChannel01, 0);
		task_ActivationKeys.createActivationKeyWithBaseChannel(actKeyForBaseChannel01, 
				"", autoBaseChannel01,"1", false, false, false, false, false);
		task_RhnBase.SignOut();
	}
	
	@Test(groups = { "bz-537094" })
	public void test01_getYumListSecCVEs_BaseChannel() throws ParseException{
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.registerSystemToSatellite(
				IRhnBase.SYSTEM_HOSTNAME01, true, false);
		ssh.executeViaSSH(IRhnBase.SYSTEM_HOSTNAME01, "yum clean all");
		String sAll = ssh.executeViaSSHWithReturn(
				IRhnBase.SYSTEM_HOSTNAME01,cmd_yumlistsec_cves)[0];
		StringTokenizer tokens = new StringTokenizer(sAll,"\n");
		boolean hasAtLeastOneCVE = false;
		while(tokens.hasMoreTokens()){
			if(tokens.nextToken().trim().startsWith("CVE-")){
				hasAtLeastOneCVE = true;
				break; // found it - break
			}			
		}
		assertTrue(hasAtLeastOneCVE);
		task_RhnBase.SignOut();
	}

	@Test(dependsOnMethods="test01_getYumListSecCVEs_BaseChannel", 
			groups = { "bz-537094" })
	public void test02_getYumListSecCVEs_ClonnedChannel() 
	throws ParseException{
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.removeAllProfilesOfASystem(IRhnBase.SYSTEM_HOSTNAME01);
		String actKeyID = task_ActivationKeys.getAKFromKeyName(
				actKeyForBaseChannel01);
		task_TestPrep.registerSystemWithActKey(
				IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.SYSTEM_HOSTNAME01, 
				actKeyID, true);
		ssh.executeViaSSH(IRhnBase.SYSTEM_HOSTNAME01, "yum clean all");

		// Wait while repodata for the custom channel is creating.
		page_Channels.open();
		while(!rh.isElementPresent("link="+autoBaseChannel01,false) && rh.isElementPresent("xpath=//input[@type='image' and @alt='Next Page']",false) ){
			sel.clickAndWait("xpath=//input[@type='image' and @alt='Next Page']");
		}
		rh.clickLink("link="+autoBaseChannel01, true);
		assertTrue(sel.isElementPresent("//table[@class='details']/tbody/tr[10]/th[text()='Repo Cache Status:']"));
		while(!sel.isElementPresent("//table[@class='details']/tbody/tr[10]/td[contains(text(),'Completed')]")){
			rh.sleepForSeconds(180);
			sel.refresh();
		}
		
		String sAll  = ssh.executeViaSSHWithReturn(
				IRhnBase.SYSTEM_HOSTNAME01,cmd_yumlistsec_cves)[0];
		StringTokenizer tokens = new StringTokenizer(sAll,"\n");
		boolean hasAtLeastOneCVE = false;
		while(tokens.hasMoreTokens()){
			if(tokens.nextToken().trim().startsWith("CVE-")){
				hasAtLeastOneCVE = true;
				break; // found it - break
			}			
		}
		assertTrue("If the string \"CVE-\" could be found",hasAtLeastOneCVE);
		task_RhnBase.SignOut();
	}
		
}
