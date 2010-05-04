package com.redhat.rhn.harness.satellite51;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Sat42.tasks.RhnBase;
import com.redhat.rhn.harness.common.Sat42.tasks.YourRhn;

public class zplaypen extends YourRhn{
	
	RhnHelper rh = new RhnHelper();
	
	
	
	public void test01RegisterSystemWithRHN(){
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, true);
	}
	
	
	public void test02PrepEnableRHNConfigMgt(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		//rb.checkpage();
		task_TestPrep.enableRHNConfigManag(IRhnBase.SYSTEM_HOSTNAME01, false, false);
	}
	
	

}
