package com.redhat.rhn.harness.gold.Space01;

import org.testng.annotations.Test;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;

public class Monitoring extends com.redhat.rhn.harness.Space01.Monitoring{
	
	protected RhnHelper rh = new RhnHelper();

	//Oracle
	
	@Test(dependsOnGroups="monitoring-test-probes",groups = { "oracle-monitoring-setup" })
	public void testMon30SetupOracleClient(){
//		task_TestPrep.registerSystem(IRhnBase.SERVER_HOSTNAME,IRhnBase.RHN_SAT_REG_CMD, true,true);
//		setupMonitoringOnClient(IRhnBase.SERVER_HOSTNAME, false);
	}
	
	@Test(dependsOnGroups="monitoring-test-probes", dependsOnMethods="testMon30SetupOracleClient",groups = { "oracle-monitoring-setup" })
	public void testMon31CreateOracleProbeSuite(){
//		task_Monitoring.monitoring_createProbeSuite(true, "oracleProbeSuite");
	}

	@Test(dependsOnGroups="monitoring-test-probes", dependsOnMethods="testMon31CreateOracleProbeSuite",groups = { "oracle-monitoring-setup" })
	public void testMon32CreateOracleProbes(){
//		createProbes_Oracle("oracleProbeSuite", true, IRhnBase.SERVER_HOSTNAME);
	}

	@Test(dependsOnGroups="monitoring-test-probes", dependsOnMethods="testMon32CreateOracleProbes",groups = { "oracle-monitoring-setup" })
	public void testMon33AddOracleSystemToSuite(){
//		task_Monitoring.monitoring_addSystemToProbeSuite(true, "oracleProbeSuite", IRhnBase.SERVER_HOSTNAME);
//		task_Monitoring.pushMonitoringScoutConfig(true,true);
	
	}
		
}
