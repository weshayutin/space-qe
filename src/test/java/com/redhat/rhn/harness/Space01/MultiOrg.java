package com.redhat.rhn.harness.Space01;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;

@Test(groups="tests")
public class MultiOrg extends com.redhat.rhn.harness.satellite51.MultiOrg {

	@BeforeClass(groups = { "setup" })
	public void test00Prep_ClearSELinux(){
		log.finer("Clearing SELinux logs");
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.command_clear_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME);
		
	}


	@AfterMethod(groups = { "teardown" })
	public void test_999_TestSELinux(){
		log.finer("Checking SELinux logs");
		assertTrue(task_TestPrep.command_check_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME));
		
	}

}
