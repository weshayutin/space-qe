package com.redhat.rhn.harness.Space01;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;

@Test(groups="tests")
public class AdvancedSearch01 extends com.redhat.rhn.harness.satellite51.AdvancedSearch01{

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

	
	@Test(groups="testplan-SearchErrata")
	public void test_AdvancedSearch_Errata_bySynopsis(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_ERRATA,
				IRhnBase.SEARCH_Errata_AllFields,
				"Important",
				true,
				false);
		assertTrue(task_Search.searchForResult("kernel", task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_System_bySystemID(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
						IRhnBase.SEARCH_FieldToSearch_ID,
						"10",
						true,
						false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
		task_RhnBase.getSystemID(IRhnBase.TESTSYSTEM01, false, false);
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_System_bySystemID_HostName_IP_Kernel(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_NameDescription,
				IRhnBase.TESTSYSTEM01,
				true,
				true);
		task_RhnBase.clickLink_SystemName(IRhnBase.TESTSYSTEM01);
		String myId = task_Search.getDetails_System_ID();
		log.fine("System ID="+myId);
		String myHostname = task_Search.getDetails_System_Hostname();
		log.fine("hostname ="+myHostname);
		String myIP = task_Search.getDetails_System_IPAddress();
		log.fine("IP ="+myIP);
		String myKernel = task_Search.getDetails_System_KernelLevel();
		log.fine("kernel ="+myKernel);
		
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_ID,
				myId,
				false,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));

		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_NameDescription,
				IRhnBase.TESTSYSTEM01,
				false,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));

		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_NETWORK_Hostname,
				myHostname,
				false,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));

		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_NETWORK_IP,
				myIP,
				false,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
		
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_Details_RunningKernel,
				myKernel,
				false,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_Packages_Installed(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_Packages_Installed,
				"bash",
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_Packages_Needed(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_Packages_Needed,
				"kernel", //TODO Cannot guarantee the kernel package needs updating
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}

}
