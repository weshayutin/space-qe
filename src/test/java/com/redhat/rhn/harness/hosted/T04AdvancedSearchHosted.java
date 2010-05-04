package com.redhat.rhn.harness.hosted;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Hosted.tasks.RhnBase;
import com.redhat.rhn.harness.common.Hosted.tasks.Search;




public class T04AdvancedSearchHosted extends Search{
	RhnHelper rh = new RhnHelper();
	RhnBase rb = new RhnBase();

/*
	public void testPrep01(){
		
		task_TestPrep.unregisterAllProfilesOfSystem(rh.getTestSystem01Name(), true);
	}
	public void testPrep02(){
		
		task_TestPrep.unregisterAllProfilesOfSystem(IRhnBase.TESTPREFIX, true);
	}

	public void testPrep03(){
		
		rb.registerMultipleProfiles(rh.getTestSystem01Name(),IRhnBase.TESTPREFIX, 2,IRhnBase.RHN_REG_CMD, true, true, true);
	}


	public void testAdvancedSearchSystem001(){
		
		rb.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS, 
						IRhnBase.SEARCH_FieldToSearch_NameDescription,
						IRhnBase.TESTSYSTEM01,
						true, 
						true);
	}

	public void testAdvancedSearchSystem002(){
		
		rb.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS, 
						IRhnBase.SEARCH_FieldToSearch_ID,
						"10",
						true, 
						false);
		rh.searchForResult(IRhnBase.TESTSYSTEM01, totalInList());
		rh.clickSystemProfileLink(IRhnBase.TESTSYSTEM01);
		rb.getSystemID(IRhnBase.TESTSYSTEM01, true, false);	
	}

	public void testAdvancedSearchSystems003(){
		
		rb.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS, 
				IRhnBase.SEARCH_FieldToSearch_NameDescription,
				IRhnBase.TESTSYSTEM01,
				true, 
				true);
		String myId = getDetails_System_ID();
		String myHostname = getDetails_System_Hostname();
		String myIP = getDetails_System_IPAddress();
		String myKernel = getDetails_System_KernelLevel();
		
		rb.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS, 
				IRhnBase.SEARCH_FieldToSearch_ID,
				myId,
				false, 
				false);
		rh.searchForResult(IRhnBase.TESTSYSTEM01, totalInList());
		
		rb.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS, 
				IRhnBase.SEARCH_FieldToSearch_NameDescription,
				IRhnBase.TESTSYSTEM01,
				false, 
				false);
		rh.searchForResult(IRhnBase.TESTSYSTEM01, totalInList());
		
		rb.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS, 
				IRhnBase.SEARCH_FieldToSearch_NETWORK_Hostname,
				myHostname,
				false, 
				false);
		rh.searchForResult(IRhnBase.TESTSYSTEM01, totalInList());
		
		rb.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS, 
				IRhnBase.SEARCH_FieldToSearch_NETWORK_IP,
				myIP,
				false, 
				false);
		rh.searchForResult(IRhnBase.TESTSYSTEM01, totalInList());
	}

	public void testAdvancedSearchSystems004(){
		
		rb.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS, 
				IRhnBase.SEARCH_FieldToSearch_SystemSoftware_Installed,
				"bash",
				true, 
				false);
		rh.searchForResult(IRhnBase.TESTSYSTEM01, totalInList());
	}

	public void testAdvancedSearchSystems005(){
		
		rb.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS, 
				IRhnBase.SEARCH_FieldToSearch_CpuGreaterThan,
				"1",
				true, 
				false);
		rh.searchForResult(IRhnBase.TESTSYSTEM01, totalInList());
		
	}
	
	public void testAdvancedSearchSystems006(){
		
		rb.advancedSearch(IRhnBase.ADVANCED_SEARCH_SYSTEMS, 
				IRhnBase.SEARCH_FieldToSearch_RamLessThan,
				"100000",
				true, 
				false);
		rh.searchForResult(IRhnBase.TESTSYSTEM01, totalInList());
		
	}

	public void testAdvancedSearchErrata001(){
		
		rb.advancedSearch(IRhnBase.ADVANCED_SEARCH_ERRATA, 
				IRhnBase.SEARCH_Errata_Synopsis,
				"Moderate",
				true, 
				false);
		rh.searchForResult("update", totalInList());	
	}

	public void testAdvancedSearchErrata002(){
		
		//"RHSA-2007:0384"
		rb.advancedSearch(IRhnBase.ADVANCED_SEARCH_ERRATA, 
				IRhnBase.SEARCH_Errata_ErratumAdvisory,
				"RHSA-2007:0384",
				true, 
				false);
		rh.searchForResult("Critical: krb5 security update", totalInList());
	}

	public void testAdvancedSearchErrata003(){
		
		rb.advancedSearch(IRhnBase.ADVANCED_SEARCH_ERRATA, 
				IRhnBase.SEARCH_Errata_PackageName,
				"kernel",
				true, 
				false);
		rh.searchForResult("bug fix update", totalInList());
        rh.searchForResult("RHSA", totalInList());
        rh.searchForResult("kernel security update", totalInList());
	}

	public void testAdvancedSearchPackages001(){
		
		rb.advancedSearch(IRhnBase.ADVANCED_SEARCH_PACKAGES, 
				IRhnBase.SEARCH_Package_Name,
				"bash",
				true, 
				false);
		rh.searchForResult("Bourne Again", totalInList());
	}
	public void testAdvancedSearchPackages002(){
		
		rb.advancedSearch(IRhnBase.ADVANCED_SEARCH_PACKAGES, 
				IRhnBase.SEARCH_Package_Name,
				"gtk+",
				true, 
				false);
		rh.searchForResult("GIMP", totalInList());
		rh.searchForResult("tool", totalInList());
	}
	
	
	public void testAdvancedSearchPackages003(){
		
		
		rb.advancedSearch(IRhnBase.ADVANCED_SEARCH_PACKAGES, 
				IRhnBase.SEARCH_Package_Name,
				"kernel-hugemem",
				true, 
				false);
		rh.searchForResult("Gigabytes of memory or more.", totalInList());
	}
*/

}
