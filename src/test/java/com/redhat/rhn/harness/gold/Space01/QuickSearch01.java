package com.redhat.rhn.harness.gold.Space01;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;

@Test(groups="tests")
public class QuickSearch01 extends com.redhat.rhn.harness.Space01.QuickSearch01{

	@BeforeSuite(groups = "setup")
	public void test00_Prep00(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.changePaginationSettings("5", false);
//		task_TestPrep.removeAllSystemProfiles(false);
		task_TestPrep.removeAllProfilesOfASystem(IRhnBase.TESTSYSTEM01);
		task_TestPrep.removeAllProfilesOfASystem(IRhnBase.TESTSYSTEM02);
		task_Channels.deleteAllCustomChannels();
		
		task_TestPrep.registerMultipleProfiles(rh.getTestSystem01Name(),IRhnBase.TESTPREFIX, 2,HarnessConfiguration.RHN_SAT_REG_CMD, true, true, false);
		task_TestPrep.fillInSystemLocation(IRhnBase.TESTSYSTEM01, true);
		task_TestPrep.fillInSystemLocation(IRhnBase.TESTSYSTEM02, true);
		task_TestPrep.createCustomSystemInfo(customInfoKey, keyDescription);
		task_Sdc.editCostumInfo(IRhnBase.TESTSYSTEM01, customInfoKey, system1Value, false);
		task_Sdc.editCostumInfo(IRhnBase.TESTSYSTEM02, customInfoKey, system2Value, false);
		task_TestPrep.command_rpm(" -e --nodeps zsh", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.command_rpm(" -e --nodeps zsh", IRhnBase.SYSTEM_HOSTNAME02, true);
		task_Sdc.takeSnapShot(snapshot1, IRhnBase.TESTSYSTEM01, false);
		task_Sdc.takeSnapShot(snapshot2, IRhnBase.TESTSYSTEM02, false);
		
		//should be able to remove this soon 5.3
		log.info("Allow time for search index to be built...");
		task_TestPrep.sleepForSeconds(180);
	}
	
	@Test(groups="testplan-SearchErrata")
	public void test04_Errata_QuickSearch_ssh(){
/*		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_ERRATA, "kernel", true, false);
		assertTrue(task_Search.searchForResult("kernel-utils", task_Search.totalInList()));
	    assertTrue(task_Search.searchForResult("Advisory",task_Search.totalInList()));
	    assertTrue(task_Search.searchForResult("Enterprise", task_Search.totalInList()));
	    //rh.screenShot("Errata", "quickSearchErrata001");

*/	}

	@Test(groups="testplan-SearchPackage")
	public void test08_QuickSearch_Packages_shell(){
/*		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "shell", true, false);
		assertTrue(task_Search.searchForResult("zsh", task_Search.totalInList()));
        assertTrue(task_Search.searchForResult("A powerful interactive shell", task_Search.totalInList()));
        assertTrue(task_Search.searchForResult("aspell-pt", task_Search.totalInList()));
*/	}

	@Test(groups="testplan-SearchSystem")
	public void test12_QuickSearch_Systems_microsoft(){
/*		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS, "microsoft", true, false);
		//assertTrue(rb.searchForResult("No results found.", sr.totalInList()));
		assertTrue(rh.isTextPresent("No results found."));
*/	}
	
	@Test(groups="sniff")
	public void test05_Errata_QuickSearch_RHBA	(){
/*		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_ERRATA, "RHBA", true, false);
		assertTrue(task_Search.searchForResult("update", task_Search.totalInList()));
*/
	}
	
}

