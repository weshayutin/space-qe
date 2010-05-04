package com.redhat.rhn.harness.hosted;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Hosted.tasks.TestPrep;
import com.redhat.rhn.harness.common.Hosted.tasks.YourRhn;

public class T02YourRhn02Hosted extends YourRhn{
	//this should be executed after a number of other tests have been
	//run.  Basically a dirty test.

	RhnHelper rh = new RhnHelper();
	//RhnBase rb = new RhnBase();
	TestPrep tp = new TestPrep();


	public void testRegisterMultipleSystemsWithRHN(){
		
		tp.registerMultipleProfiles(rh.getTestSystem01Name(),IRhnBase.TESTPREFIX, 11,IRhnBase.RHN_REG_CMD, false, true, true);
	}

	public void testPaginationSettings(){
		
		tp.changePaginationSettings("5", true);
	}


}
