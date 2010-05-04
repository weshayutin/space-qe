package com.redhat.rhn.harness.satellite50;

import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;

@Test(groups="tests")
public class AdvancedSearch01 extends com.redhat.rhn.harness.satellite42.AdvancedSearch01{


	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_System_bySystemID_HostName_IP_Kernel(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_NameDescription,
				IRhnBase.TESTSYSTEM01,
				true,
				true);
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
	}

	@Test(groups="testplan-SearchErrata")
	public void test_AdvancedSearch_Errata_byErratumAdvistory(){
		//"RHSA-2007:0384"
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_ERRATA,
				IRhnBase.SEARCH_Errata_ErratumAdvisory,
				"RHSA",
				true,
				false);
		assertTrue(task_Search.searchForResult("Moderate:", task_Search.totalInList()));
	}

	@Test(groups="testplan-SearchErrata")
	public void test_AdvancedSearch_Errata_PackageName(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_ERRATA,
				IRhnBase.SEARCH_Errata_PackageName,
				"kernel",
				true,
				false);
		assertTrue(task_Search.searchForResult("kernel-smp", task_Search.totalInList()));
        assertTrue(task_Search.searchForResult("kernel-devel", task_Search.totalInList()));
	}

	
	
	@Test(groups="testplan-SearchPackage")
	public void test14_AdvancedSearch_Package_gtk(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_PACKAGES,
				IRhnBase.SEARCH_Package_Name,
				"gtk+",
				true,
				false);
		assertTrue(task_Search.searchForResult("GIMP", task_Search.totalInList()));
		assertTrue(task_Search.searchForResult("tool", task_Search.totalInList()));
	}

	@Test(groups="testplan-SearchPackage")
	public void test15_AdvancedSearch_Package_kernel(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_PACKAGES,
				IRhnBase.SEARCH_Package_Name,
				"kernel-hugemem",
				true,
				false);
		assertTrue(task_Search.searchForResult("Gigabytes of memory or more.", task_Search.totalInList()));
	}


}
