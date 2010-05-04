package com.redhat.rhn.harness.satellite50;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

@Test(groups="pagination")
public class Pagination01 extends SeleniumSetup {

	protected RhnHelper rh = new RhnHelper();
	
	

	@BeforeClass(groups="testplan-General_WebUI_Regression")
	public void test01Prep01(){
		task_TestPrep.removeAllSystemProfiles(true);
		task_TestPrep.changePaginationSettings("5", false);
		//task_TestPrep.unregisterAllProfilesOfSystem(IRhnBase.SYSTEM_HOSTNAME01, true);
	}

	/*@BeforeClass
	public void testPrep02(){
		
		task_TestPrep.unregisterAllProfilesOfSystem(IRhnBase.TESTPREFIX, true);
	}*/
	
	@Test(groups="sniff")
	public void testPackageSearchPaginationSniff(){
		task_RhnBase.OpenAndLogIn();
		task_Pagination.packageSearchPagination();
	}


	@Test(groups="testplan-General_WebUI_Regression")
	public void testSystemSearchPagination(){
		task_RhnBase.OpenAndLogIn();
		task_Pagination.systemSearchPagination(true, IRhnBase.TESTPREFIX);
	}
	
	@Test(dependsOnMethods="testSystemSearchPagination",groups="testplan-General_WebUI_Regression", alwaysRun=true)
	public void testPackageSearchPagination(){
		task_RhnBase.OpenAndLogIn();
		task_Pagination.packageSearchPagination();
	}


}