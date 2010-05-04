package com.redhat.rhn.harness.satellite42;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

@Test(groups="tests")
public class QuickSearch01 extends SeleniumSetup{
	protected static RhnHelper rh = new RhnHelper();
	protected String customInfoKey="autoCustomInfoKey";
	protected String keyDescription="autoKeyDescription";
	protected String system1Value=("system1");
	protected String system2Value=("system2");
	protected String snapshot1="searchSnap1";
	protected String snapshot2="searchSnap2";
	


	@BeforeSuite(groups = "setup")
	public void test00_Prep00(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.changePaginationSettings("5", false);
		task_TestPrep.removeAllSystemProfiles(false);
//		task_Channels.deleteAllCustomChannels();
		// Comment out the custome channels deletion ONLY if you really need it,
		// as for now it seems to be useless, so I commented it [gkhachik]
		
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
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_ERRATA, "kernel", true, false);
		assertTrue(task_Search.searchForResult("kernel-utils", task_Search.totalInList()));
	    assertTrue(task_Search.searchForResult("Advisory",task_Search.totalInList()));
	    assertTrue(task_Search.searchForResult("Enterprise", task_Search.totalInList()));
	    //rh.screenShot("Errata", "quickSearchErrata001");

	}
	
	@Test(groups="sniff")
	public void test05_Errata_QuickSearch_RHBA	(){
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_ERRATA, "RHBA", true, false);
		assertTrue(task_Search.searchForResult("update", task_Search.totalInList()));

	}
	
	@Test(groups="sniff")
	public void test06_QuickSearch_Packages_bash_001(){
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "bash", true, false);
		assertTrue(task_Search.searchForResult("The GNU Bourne Again shell (bash)", task_Search.totalInList()));
	}

	@Test(groups="testplan-SearchPackage")
	public void test06_QuickSearch_Packages_bash_002 (){
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "bash", true, false);
		assertTrue(task_Search.searchForResult("The GNU Bourne Again shell (bash)", task_Search.totalInList()));
	}

	@Test(groups="testplan-SearchPackage")
	public void test07_QuickSearch_Packages_glibc(){
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "glibc", true, false);
		assertTrue(task_Search.searchForResult("compat-glibc", task_Search.totalInList()));
       // assertTrue(rb.searchForResult("compat-glibc-headers", sr.totalInList()));
        assertTrue(task_Search.searchForResult("glibc-common", task_Search.totalInList()));
        assertTrue(task_Search.searchForResult("glibc-utils", task_Search.totalInList()));
	}

	@Test(groups="testplan-SearchPackage")
	public void test08_QuickSearch_Packages_shell(){
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "shell", true, false);
		assertTrue(task_Search.searchForResult("zsh", task_Search.totalInList()));
        assertTrue(task_Search.searchForResult("A powerful interactive shell", task_Search.totalInList()));
        assertTrue(task_Search.searchForResult("aspell-pt", task_Search.totalInList()));
	}

	@Test(groups="testplan-SearchPackage")
	public void test09_QuickSearch_Packages_additionalSpace(){
		log.info("Test with additional space in front of search tearm");
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, " bash", true, false);
		assertTrue(task_Search.searchForResult("The GNU Bourne Again shell (bash)", task_Search.totalInList()));
	}

	@Test(groups="testplan-SearchPackage")
	public void test10_QuickSearch_Packages_gtk(){
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "gtk+", true, true);
	}

	@Test(groups="testplan-SearchPackage")
	public void test11_QuickSearch_Packages_kernelXen(){
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "kernel-xen-2.6.18-53.el5.i686", true, false);
		assertTrue(task_Search.searchForResult("kernel-xen", task_Search.totalInList()));
		log.info("break");
	}

	@Test(groups="testplan-SearchPackage")
	public void test11_QuickSearch_Packages_smp(){
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "PAE", true, false);
		assertTrue(task_Search.searchForResult("kernel-PAE", task_Search.totalInList()));
	}

	@Test(groups="testplan-SearchPackage")
	public void test11_QuickSearch_Packages_spell(){
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "spell", true, false);
		assertTrue(task_Search.searchForResult("aspell", task_Search.totalInList()));
	}

	@Test(groups="testplan-SearchPackage")
	public void test11_QuickSearch_Packages_wildcard(){
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "*", true, false);
		Assert.assertTrue(rh.isTextNotPresent("Internal Server Error"));
	}

	@Test(groups="testplan-SearchPackage")
	public void test11_QuickSearch_Packages_carrot(){
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "^", true, false);
		Assert.assertTrue(rh.isTextNotPresent("Internal Server Error"));
	}

	@Test(groups="testplan-SearchPackage")
	public void test11Packages_QuickSearch_Packages_space(){
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, " ", true, false);
		Assert.assertTrue(rh.isTextNotPresent("Internal Server Error"));
	}

	@Test(groups="sniff")
	public void test12Systems000(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.RHN_SAT_REG_CMD, true, false);
		rh.sleepForSeconds(30); //default search index for systems is 30seconds
		assertTrue(task_Search.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS, IRhnBase.SYSTEM_HOSTNAME01, false, true));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test12_QuickSearch_Systems_microsoft(){
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS, "microsoft", true, false);
		//assertTrue(rb.searchForResult("No results found.", sr.totalInList()));
		assertTrue(rh.isTextPresent("No results found."));
	}

	@Test(groups="testplan-SearchSystem")
	public void test13_QuickSearch_Systems_systemName1(){
		String id=null;
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS, IRhnBase.TESTSYSTEM01, true, true);;
		id=task_Search.getSystemID(IRhnBase.TESTSYSTEM01, false, false);
		int i = Integer.valueOf(id);
		assertTrue(i>100);
	}

	@Test(groups="testplan-SearchSystem")
	public void test14_QuickSearch_Systems_systemName2(){
		assertTrue(task_Search.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS, IRhnBase.TESTSYSTEM02, true, true));
	}

	@Test(groups="testplan-SearchSystem")
	public void test15_QuickSearch_Systems_bufferOverRun(){
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS, "$%^&*$%^&*$%^&*$%^&*$%^&*$%^&*$%^&*$%^&*$%^&*", true, false);
		Assert.assertTrue(rh.isTextPresent("Search string is required."));
	}

	@Test(groups="testplan-SearchSystem")
	public void test16_QuickSearch_Systems_partialName(){
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS, IRhnBase.TESTPREFIX, true, true);
		task_Search.verifySystemName(IRhnBase.TESTSYSTEM01);
		task_Search.verifySystemName(IRhnBase.TESTSYSTEM02);
	}

	@Test(groups="testplan-SearchSystem")
	public void test17_QuickSearch_Systems_prependSpace(){
		log.info("testing w/ an additional space in front of the query string");
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS, " "+IRhnBase.TESTPREFIX, true, true);
		task_Search.verifySystemName(IRhnBase.TESTSYSTEM01);
		task_Search.verifySystemName(IRhnBase.TESTSYSTEM02);
	}


	

	}

