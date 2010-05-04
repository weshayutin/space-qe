package com.redhat.rhn.harness.Space01;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;

@Test(groups="tests")
public class YourRHN extends com.redhat.rhn.harness.satellite51.YourRHN{
	
	//YourRHN in 5.3 is now "Overview" in the Satellite webui

	@BeforeClass(groups = { "setup" })
	public void test00Prep_ClearSELinux(){
		log.finer("Clearing SELinux logs");
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.command_clear_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME);
		
	}
	
	/*@BeforeClass(groups = { "setup", "performance" }) //TODO jsefler: This performance setup/test may better belong in its own script?
	public void test00Prep_runClientLoadTool(){
		log.fine("Executing client load tool");
		task_RhnBase.OpenAndLogIn();
		task_Perf.setupThreadPool(IRhnBase.SYSTEM_HOSTNAME_LOADTEST);
		task_Perf.runThreadPool(IRhnBase.SYSTEM_HOSTNAME_LOADTEST, "10", true, "10", IRhnBase.USER, IRhnBase.PASSWORD, true, "5","2", true);
		task_TestPrep.command_generic("ps ", "-ef | grep python", IRhnBase.SYSTEM_HOSTNAME_LOADTEST, "root", true);
		
	}



	@AfterMethod(groups = { "teardown" })
	public void test_999_TestSELinux(){
		log.finer("Checking SELinux logs");
		assertTrue(task_TestPrep.command_check_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME));
		
	}*/



}
