package com.redhat.rhn.harness.satellite42;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.Sat42.tasks.*;

@Test(groups="tests")
public class AdvancedSearch01 extends SeleniumSetup{
	
	protected RhnHelper rh = new RhnHelper();
	protected String customInfoKey="autoCustomInfoKey";
	protected String keyDescription="autoKeyDescription";
	protected String system1Value=("system1");
	protected String system2Value=("system2");
	protected String snapshot1="searchSnap1";
	protected String snapshot2="searchSnap2";
	
	
/*	@BeforeSuite(groups = "setup")
	public void test00_Prep002(){
		task_RhnBase.OpenAndLogIn();
	
	}*/


	@Test(groups="testplan-SearchSystem")
	public void test04_AdvancedSearch_System_NameDescription(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
						IRhnBase.SEARCH_FieldToSearch_NameDescription,
						IRhnBase.TESTSYSTEM01,
						true,
						true);
	}

	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_System_bySystemID(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
						IRhnBase.SEARCH_FieldToSearch_ID,
						"10",
						true,
						false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
		rh.clickSystemProfileLink(IRhnBase.TESTSYSTEM01);
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
		
	}

	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_System_byInstalledSoftware(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_SystemSoftware_Installed,
				"bash",
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}

	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_System_CPUGreater(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_CpuGreaterThan,
				"1",
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_System_NumberOfCPUGreater(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_CpuNumberGreaterThan,
				"0",
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_System_CPUModel(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_CpuModel,
				"intel",
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}

	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_System_RamLess(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_RamLessThan,
				"100000",
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_System_DMI_System(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_DMI_System,
				"PRIMERGY",
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_System_DMI_BIOS(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_DMI_Bios,
				"Phoenix",
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_System_DMI_AssetTag(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_DMI_AssetTag,
				"SQ523",
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_Location_Address(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_Location_Address,
				"Varsity",
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_Location_Building(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_Location_Building,
				"Building1",
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_Location_Room(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_Location_Room,
				"Room1",
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_Location_Rack(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_Location_Rack,
				"Rack1",
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_Details_CustomInfo(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_CustomInfo,
				"system1",
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_Details_SnapShotTag(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_SnapShotTag,
				"system1",
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_Activity_DaysSinceLastCheckIn(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_DaysSinceLastCheckin,
				"0",
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_Activity_DaysSinceLastCheckIn_negative(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_DaysSinceLastCheckin,
				"1",
				true,
				false);
		assertFalse(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_Activity_DaysSinceFirstRegistered(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_DaysSinceFirstRegistered,
				"0",
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_Activity_DaysSinceFirstRegistered_negative(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_DaysSinceFirstRegistered,
				"1",
				true,
				false);
		assertFalse(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_Hardware_Description(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_HD_Description,
				"intel",
				true,
				false);
		assertTrue(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
		assertTrue(rh.isTextPresent("Linux"));
	}
	
	@Test(groups="testplan-SearchSystem")
	public void test_AdvancedSearch_Hardware_Description_negative(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS,
				IRhnBase.SEARCH_FieldToSearch_HD_Description,
				"zzzzz",
				true,
				false);
		assertFalse(task_Search.searchForResult(IRhnBase.TESTSYSTEM01, task_Search.totalInList()));
	}
	
	
	
	

	
	
	
	
	

	@Test(groups="testplan-SearchErrata")
	public void test_AdvancedSearch_Errata_bySynopsis(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_ERRATA,
				IRhnBase.SEARCH_Errata_Synopsis,
				"Moderate",
				true,
				false);
		assertTrue(task_Search.searchForResult("update", task_Search.totalInList()));
	}

	@Test(groups="testplan-SearchErrata")
	public void test_AdvancedSearch_Errata_byErratumAdvistory(){
		//"RHSA-2007:0384"
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_ERRATA,
				IRhnBase.SEARCH_Errata_ErratumAdvisory,
				"RHSA",
				true,
				false);
		assertTrue(task_Search.searchForResult("Critical:", task_Search.totalInList()));
	}

	@Test(groups="testplan-SearchErrata")
	public void test_AdvancedSearch_Errata_PackageName(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_ERRATA,
				IRhnBase.SEARCH_Errata_PackageName,
				"kernel",
				true,
				false);
		assertTrue(task_Search.searchForResult("kernel-hugemem", task_Search.totalInList()));
        assertTrue(task_Search.searchForResult("kernel-PAE", task_Search.totalInList()));
	}

	@Test(groups="testplan-SearchPackage")
	public void test_AdvancedSearch_Package_bash(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_PACKAGES,
				IRhnBase.SEARCH_Package_Name,
				"bash",
				true,
				false);
		assertTrue(task_Search.searchForResult("Bourne Again", task_Search.totalInList()));
	}
	
	@Test(groups="testplan-SearchPackage")
	public void test14_AdvancedSearch_Package_gtk(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_PACKAGES,
				IRhnBase.SEARCH_Package_Name,
				"gtk",
				true,
				false);
		assertTrue(task_Search.searchForResult("GIMP", task_Search.totalInList()));
		assertTrue(task_Search.searchForResult("tool", task_Search.totalInList()));
	}

	@Test(groups="testplan-SearchPackage")
	public void test15_AdvancedSearch_Package_kernel(){
		task_Search.advancedSearch(IRhnBase.ADVANCED_SEARCH_PACKAGES,
				IRhnBase.SEARCH_Package_Name,
				"kernel",
				true,
				false);
		assertTrue(task_Search.searchForResult("Gigabytes of memory or more.", task_Search.totalInList()));
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

	@AfterClass
	public void test100Prep00(){
		task_TestPrep.changePaginationSettings("5", true);
	}


}
