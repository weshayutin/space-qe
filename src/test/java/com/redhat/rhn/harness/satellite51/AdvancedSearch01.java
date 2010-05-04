package com.redhat.rhn.harness.satellite51;

import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;

@Test(groups="tests")
public class AdvancedSearch01 extends com.redhat.rhn.harness.satellite50.AdvancedSearch01{

	

	
	
	
	
	
	@Test(groups="testplan-SearchPackage")
	public void test14_AdvancedSearch_Package_gtk(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_PACKAGES,
				IRhnBase.SEARCH_Package_Name,
				"gtk+",
				true,
				false);
		assertTrue(task_Search.searchForResult("gtk2", task_Search.totalInList()));
		assertTrue(task_Search.searchForResult("GIMP", task_Search.totalInList()));
	}

	@Test(groups="testplan-SearchPackage")
	public void test15_AdvancedSearch_Package_kernel(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_PACKAGES,
				IRhnBase.SEARCH_Package_Name,
				"kernel-xen",
				true,
				false);
		assertTrue(task_Search.searchForResult("kernel-xen", task_Search.totalInList()));
		//assertTrue(rb.searchForResult("kernel-debug", totalInList()));
		assertTrue(task_Search.searchForResult("The Linux kernel compiled for", task_Search.totalInList()));
		//assertTrue(rb.searchForResult("Header files for the Linux kernel for use by glibc", totalInList()));
	}

/*	public void testSystemInfo(){
		
		rb.OpenAndLogIn();
		rb.getSystemInfo(IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.SYSTEM_DETAIL_HOSTNAME, true);
		rb.getSystemInfo(IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.SYSTEM_DETAIL_IP_ADDRESS, false);
		rb.getSystemInfo(IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.SYSTEM_DETAIL_KERNEL, false);
		rb.getSystemInfo(IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.SYSTEM_DETAIL_RHN_SYSTEM_ID, false);
		rb.getSystemInfo(IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.SYSTEM_DETAIL_RHN_TIME_REGISTERED, false);
		rb.getSystemInfo(IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.SYSTEM_DETAIL_RHN_TIME_CHECKED_IN, false);
		rb.getSystemInfo(IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.SYSTEM_DETAIL_RHN_TIME_LAST_BOOTED, false);
		rb.getSystemInfo(IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.SYSTEM_DETAIL_ENTITLEMENTS, false);
		rb.getSystemInfo(IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.SYSTEM_DETAIL_NOTIFICATIONS, false);
		rb.getSystemInfo(IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.SYSTEM_DETAIL_AUTO_ERRATA_UPDATE, false);
		rb.getSystemInfo(IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.SYSTEM_DETAIL_SYSTEM_NAME, false);
		rb.getSystemInfo(IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.SYSTEM_DETAIL_DESCRIPTION, false);
		rb.getSystemInfo(IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.SYSTEM_DETAIL_LOCATION, false);
	}*/

	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_System_RamGreater(){
		task_RhnBase.OpenAndLogIn();
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_RamGreaterThan,
				"1",
				false,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_System_CPUModel(){
	task_RhnBase.OpenAndLogIn();
	task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
			IRhnBase.SEARCH_FieldToSearch_CpuModel,
			"Pentium",
			false,
			false);
	assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	
	@Test(groups="testplan-SearchErrata")
	public void test_AdvancedSearch_Errata_PackageName(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_ERRATA,
				IRhnBase.SEARCH_Errata_PackageName,
				"kernel",
				true,
				false);
		assertTrue(task_Search.searchForResult("kernel-xen", task_Search.totalInList()));
        assertTrue(task_Search.searchForResult("kernel-doc", task_Search.totalInList()));
	}

/*public void test14AdvancedSearchSystems011(){
	task_RhnBase.OpenAndLogIn();
	task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
			IRhnBase.SEARCH_FieldToSearch_NumberOfCpu_LESSTHAN,
			"10",
			false,
			false);
	assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));

}*/

	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_System_DaysSinceLastCheckin(){
	task_RhnBase.OpenAndLogIn();
	task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
			IRhnBase.SEARCH_FieldToSearch_DaysSinceLastCheckin,
			"0",
			false,
			false);
	assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}

	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_System_DaysSinceFirstRegistered(){
	task_RhnBase.OpenAndLogIn();
	task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
			IRhnBase.SEARCH_FieldToSearch_DaysSinceFirstRegistered,
			"0",
			false,
			false);
	assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));

}
//need to add snapshot tag to advanced search
/*public void test17AdvancedSearchSystems014(){
	task_RhnBase.OpenAndLogIn();
	task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
			IRhnBase.SEARCH_FieldToSearch_SnapShotTag,
			"10",
			false,
			false);
	assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));

}*/




}
