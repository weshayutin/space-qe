package com.redhat.rhn.harness.satellite51;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.redhat.rhn.harness.common.RhnHelper;

public class SuiteFunctionALL {
	
	RhnHelper rh = new RhnHelper();

	public static Test suite(){
	TestSuite suite = new TestSuite("test");
	
	suite.addTestSuite(YourRHN.class);
	suite.addTestSuite(QuickSearch01.class);
	suite.addTestSuite(AdvancedSearch01.class);
	suite.addTestSuite(Channels.class);
	suite.addTestSuite(ErrataManagement.class);
	suite.addTestSuite(ConfigManagement.class);
	suite.addTestSuite(Pagination01.class);
	suite.addTestSuite(RhnRegister.class);
	suite.addTestSuite(SdcSoftware.class);
	suite.addTestSuite(ActivationKeys.class);
	suite.addTestSuite(MultiOrg.class);
	suite.addTestSuite(UserRoles.class);
	suite.addTestSuite(Authentication.class);
	suite.addTestSuite(SatelliteProxy.class);
	suite.addTestSuite(SSM.class);

	//long runs
//
	
    suite.addTestSuite(KickStart.class);
	suite.addTestSuite(KickStartAdvanced.class);
	suite.addTestSuite(VirtualizationTestSuite.class);
	suite.addTestSuite(Monitoring.class);
	
	return suite;
}

}
