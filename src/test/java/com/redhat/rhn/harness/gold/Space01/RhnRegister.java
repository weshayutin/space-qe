package com.redhat.rhn.harness.gold.Space01;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.redhat.rhn.harness.baseInterface.IRhnBase;

@Test(groups="tests")
public class RhnRegister extends com.redhat.rhn.harness.Space01.RhnRegister{

	@BeforeClass(groups = { "setup" })
	public void test00Prep(){
		task_TestPrep.removeAllProfilesOfASystem(IRhnBase.SYSTEM_HOSTNAME01);
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, true);  // to make sure the client has been registered at least once
		task_TestPrep.removeAllProfilesOfASystem(IRhnBase.SYSTEM_HOSTNAME01);
	}	
}
