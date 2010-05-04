package com.redhat.rhn.harness.Space01;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Sat51.tasks.BVT_Tasks;
import com.redhat.rhn.harness.satellite51.ActivationKeys;
import com.redhat.rhn.harness.satellite51.Authentication;
import com.redhat.rhn.harness.satellite51.Channels;
import com.redhat.rhn.harness.satellite51.ConfigManagement;
import com.redhat.rhn.harness.satellite51.ErrataManagement;
import com.redhat.rhn.harness.satellite51.MultiOrg;
import com.redhat.rhn.harness.satellite51.Pagination01;
import com.redhat.rhn.harness.satellite51.RhnRegister;
import com.redhat.rhn.harness.satellite51.SdcSoftware;
import com.redhat.rhn.harness.satellite51.UserRoles;
import com.redhat.rhn.harness.satellite51.YourRHN;

public class Suite01 extends BVT_Tasks{
	RhnHelper rh = new RhnHelper();

	public static Test suite(){
	TestSuite suite = new TestSuite("test");
	
	suite.addTestSuite(QuickSearch01.class);
	suite.addTestSuite(AdvancedSearch01.class);
	suite.addTestSuite(Channels.class);
	suite.addTestSuite(ErrataManagement.class);
	suite.addTestSuite(ConfigManagement.class);
	suite.addTestSuite(Pagination01.class);
	suite.addTestSuite(RhnRegister.class);
	suite.addTestSuite(SdcSoftware.class);
	suite.addTestSuite(ActivationKeys.class);
	suite.addTestSuite(UserRoles.class);
	suite.addTestSuite(Authentication.class);
	suite.addTestSuite(MultiOrg.class);
	//suite.addTestSuite(SatelliteProxy.class);
	
	//suite.addTestSuite(SSM.class);

	//long runs
//
	
	//
	/*suite.addTestSuite(KickStart.class);
	suite.addTestSuite(KickStartAdvanced.class);
	suite.addTestSuite(VirtualizationTestSuite.class);
	suite.addTestSuite(Monitoring.class);*/
	
	return suite;
}
}
