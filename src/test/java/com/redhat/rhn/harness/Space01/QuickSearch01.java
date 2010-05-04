package com.redhat.rhn.harness.Space01;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;

@Test(groups="tests")
public class QuickSearch01 extends com.redhat.rhn.harness.satellite51.QuickSearch01{

	@BeforeMethod(groups = { "testplan-SearchErrata", "testplan-SearchSystem", "testplan-SearchPackage" })
	public void test00Prep_ClearSELinux(){
		log.finer("Clearing SELinux logs");
		page_RhnCommon.JustOpen();
		task_TestPrep.command_clear_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME);
	}


	@AfterMethod(groups = { "teardown" })
	public void test_999_TestSELinux(){
		log.finer("Checking SELinux logs");
		assertTrue(task_TestPrep.command_check_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME));
		
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test15_QuickSearch_Systems_bufferOverRun(){
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS, "$%^&*$%^&*$%^&*$%^&*$%^&*$%^&*$%^&*$%^&*$%^&*", true, false);
		Assert.assertTrue(rh.isTextPresent("Could not parse query"));
	}

	
	@Override //due to bugzilla 513439 this test must use a more refined search string in sat5.3.0
	@Test(groups="sniff")
	public void test05_Errata_QuickSearch_RHBA	(){
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_ERRATA, "RHBA-2009:0263", true, false);
		assertTrue(task_Search.searchForResult("update", task_Search.totalInList()));

	}
	
}

