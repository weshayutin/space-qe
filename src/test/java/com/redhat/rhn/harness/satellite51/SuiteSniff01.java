package com.redhat.rhn.harness.satellite51;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Sat42.tasks.BVT_Tasks;
import com.redhat.rhn.harness.common.Sat42.tasks.RhnBase;

public class SuiteSniff01 extends BVT_Tasks{

	RhnHelper rh = new RhnHelper();
	
    private static boolean goodBuild;

	
	
	public static Test suite(){
		TestSuite suite = new TestSuite("test");

		suite.addTestSuite(YourRHN.class);
		
		
		return suite;
		
	}

}
