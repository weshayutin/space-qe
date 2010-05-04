package com.redhat.rhn.harness.satellite51;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;

@Test(groups="tests")
public class ActivationKeys extends com.redhat.rhn.harness.satellite50.ActivationKeys{

	public ActivationKeys() {
		// override super class variables
		ADDITIONAL_COMMAND = "--activationkey=1-"+KEY+"  --force";
	}
	
	@BeforeClass(groups = { "setup" })
	public void test01Prep01(){
		task_RhnBase.OpenAndLogIn();
		task_ActivationKeys.deleteActivationKey("autotest01", false);
		task_ActivationKeys.deleteActivationKey("autotest02", false);
		task_ActivationKeys.deleteActivationKey("autotest03", false);
		task_TestPrep.modifyServerParent(IRhnBase.SYSTEM_HOSTNAME01, true, "serverURL="+SERVER_REG);
	}
	
}
