package com.redhat.rhn.harness.satellite51;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.redhat.rhn.harness.common.RhnHelper;

public class SuiteFunction02 {

	RhnHelper rh = new RhnHelper();

	public static Test suite(){
	TestSuite suite = new TestSuite("test");
	
	//suite.addTestSuite(YourRhn.class);
	//suite.addTestSuite(YourRhnPart2.class);
	//suite.addTestSuite(QuickSearch01.class);
	//suite.addTestSuite(AdvancedSearch01.class);
	/*suite.addTestSuite(Channels.class);
	suite.addTestSuite(ErrataManagement.class);
	suite.addTestSuite(ConfigManagement1.class);
	suite.addTestSuite(Pagination01.class);
	suite.addTestSuite(RhnRegister.class);
	suite.addTestSuite(SdcSoftware.class);
	suite.addTestSuite(ActivationKeys.class);
	suite.addTestSuite(MultiOrg.class);
	suite.addTestSuite(UserRoles.class);*/

	//long runs
//
	suite.addTestSuite(VirtualizationTestSuite.class);
	suite.addTestSuite(KickStart.class);
	suite.addTestSuite(KickStartAdvanced.class);
	suite.addTestSuite(Monitoring.class);
	
	return suite;
}
	
}
