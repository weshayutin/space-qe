package com.redhat.rhn.harness.satellite42;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.redhat.rhn.harness.common.Sat42.tasks.RhnBase;

@Test(groups="tests")
public class BVT02 extends SeleniumSetup{
	protected RhnHelper rh = new RhnHelper();
	
	
	
    protected static boolean goodBuild;

    @Test(groups = { "tests" })
	public void test01_InitialLogin(){
		
		goodBuild = task_BVT.createInitialSatAdminUser();
		if(goodBuild){
			log.info("SUCCESS: Initial Build Success");
			Assert.assertTrue(goodBuild);
		}
		else{
			log.info("FAILURE: Initial Build Failed");
			Assert.assertTrue(goodBuild);
		}

	}

    @Test(groups = { "tests" }, dependsOnMethods="test01_InitialLogin")
	public void test02_ChangePaginationSetting(){
		Assert.assertTrue(goodBuild);
		if(goodBuild){
		
		task_TestPrep.changePaginationSettings("10", true);
		}
	}

    @Test(groups = { "tests" }, dependsOnMethods="test02_ChangePaginationSetting")
	public void test03RegisterSystemWithRHN(){
		Assert.assertTrue(goodBuild);
		if(goodBuild){
		
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, true);
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		}

	}

}
