package com.redhat.rhn.harness.Space01;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;


public class Monitoring extends SeleniumSetup{
	
	protected RhnHelper rh = new RhnHelper();

	@BeforeMethod(groups = { "testplan-Monitoring-Satellite" })
	public void test00Prep_ClearSELinux(){
		log.finer("Clearing SELinux logs");
		page_RhnCommon.JustOpen();
		task_TestPrep.command_clear_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME);
	}


	@AfterMethod(groups = { "teardown" })
	public void test_999_TestSELinux(){
		log.fine("Checking SELinux logs");
		assertTrue(task_TestPrep.command_check_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME));
		
	}
	
/*	@AfterClass(groups = { "teardown" })
	public void test_999_registerAndActivateSat(){
		log.fine("Reregistering satellite to hosted and activating");
		task_TestPrep.registerSystem(IRhnBase.SERVER_HOSTNAME, "--serverUrl=http://xmlrpc.rhn.webqa.redhat.com/XMLRPC --force", HarnessConfiguration.RHN_WEBQA_ID, "redhat", false);
		task_TestPrep.command_generic(" rhn-satellite-activate", " -rhn-cert="+HarnessConfiguration.RHN_SATELLITE_CERT, IRhnBase.SERVER_HOSTNAME, true);
	}*/
	
	
	
	@Test(groups = { "monitoring-setup" })
	public void testMon01EnableMonitoring(){
		task_TestPrep.changePaginationSettings("25", true);
		task_Monitoring.enableMonitoring(false);
		task_TestPrep.command_RestartSatellite(IRhnBase.SERVER_HOSTNAME, true, true, false, false);
		task_Monitoring.enableMonitoringScout(false);
		task_TestPrep.command_generic("/etc/init.d/MonitoringScout", " restart", IRhnBase.SERVER_HOSTNAME, true);
	}
	
	
	@Test(dependsOnMethods="testMon01EnableMonitoring",groups = { "monitoring-setup" })
	public void testMon02EnableMonitoring(){
		task_RhnBase.OpenAndLogIn();
//		task_TestPrep.removeAllSystemProfiles(false);
		task_TestPrep.removeAllProfilesOfASystem(IRhnBase.SYSTEM_HOSTNAME01);		
		task_Monitoring.monitoring_delete_ProbesToSuite(true);
		log.info("running rpm commands on "+ IRhnBase.SYSTEM_HOSTNAME01);
		task_TestPrep.removeRHNConfigFilesFromSystem(IRhnBase.SYSTEM_HOSTNAME01);
		//rb.removePackage(IRhnBase.SYSTEM_HOSTNAME01, "rhnmd");
		task_TestPrep.command_rpm("-e --nodeps rhnmd", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.command_rpm("-e --nodeps rhns-proxy-monitoring", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.command_rpm("-e --nodeps NPusers", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.command_getSatelliteSSLCert(IRhnBase.SERVER_HOSTNAME, IRhnBase.SYSTEM_HOSTNAME01);
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,
				IRhnBase.RHN_SAT_REG_CMD + " --sslCACert=/usr/share/rhn/RHN-ORG-TRUSTED-SSL-CERT",
				 true, false);
	}



	@Test(dependsOnMethods="testMon02EnableMonitoring",groups = { "monitoring-setup" })
	public void testMon09SetupClient(){
		setupMonitoringOnClient(IRhnBase.SYSTEM_HOSTNAME01, false);
	}

	@Test(dependsOnMethods="testMon09SetupClient",groups = { "monitoring-setup" })
	public void testMon10PushScoutConfig(){
		task_Monitoring.pushMonitoringScoutConfig(true,true);
	}

	@Test(dependsOnMethods="testMon10PushScoutConfig",groups = { "monitoring-setup" })
	public void testMon11CreateProbeSuite(){
		task_Monitoring.monitoring_createProbeSuite(true, "autotestMon01");
	}
	
	@Test(dependsOnMethods="testMon11CreateProbeSuite",groups = { "monitoring-setup" })
	public void testMon12CreateProbes(){
		createProbes01("autotestMon01");
	}

	@Test(dependsOnMethods="testMon12CreateProbes",groups = { "monitoring-setup" })
	public void testMon13AddSystemToSuite(){
		task_Monitoring.monitoring_addSystemToProbeSuite(true, "autotestMon01", IRhnBase.SYSTEM_HOSTNAME01);
		task_Monitoring.pushMonitoringScoutConfig(true,true);
	
	}

	
	//TEST FIRST COUPLE PROBES
	
	@Test(dependsOnGroups="monitoring-setup",groups = { "monitoring-test-probes" })
	public void testMon15Check_LinuxMemory(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_Monitoring.monitoring_checkProbeStatus(IRhnBase.SYSTEM_HOSTNAME01, "Linux: Memory Usage", "CRITICAL, RAM free", "WARNING, RAM free");
 
	}

	@Test(dependsOnGroups="monitoring-setup",groups = { "monitoring-test-probes" })
	public void testMon16Check_NetworkServices_PING(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_Monitoring.monitoring_checkProbeStatus(IRhnBase.SYSTEM_HOSTNAME01, "Network Services: Ping", "OK, Round-trip avg", "OK, Round-trip avg");
	}
	
	@Test(dependsOnGroups="monitoring-setup",groups = { "monitoring-test-probes" })
	public void testMon17Check_General_TCP(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_Monitoring.monitoring_checkProbeStatus(IRhnBase.SYSTEM_HOSTNAME01, "General: TCP Check", "OK, TCP port 80:", "400 Bad Request");
	}
	
	@Test(dependsOnGroups="monitoring-setup",groups = { "monitoring-test-probes" })
	public void testMon17Check_Linux_Load(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_Monitoring.monitoring_checkProbeStatus(IRhnBase.SYSTEM_HOSTNAME01, "Linux: Load", "OK, CPU load", "15-min ave");
	}
	
	@Test(dependsOnGroups="monitoring-setup",groups = { "monitoring-test-probes" })
	public void testMon17Check_Linux_PorcessCountTotal(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_Monitoring.monitoring_checkProbeStatus(IRhnBase.SYSTEM_HOSTNAME01, "Linux: Process Count Total", "OK, Processes", "Processes");
	}
	
	//Oracle
	
	@Test(dependsOnGroups="monitoring-test-probes",groups = { "oracle-monitoring-setup" })
	public void testMon30SetupOracleClient(){
		task_TestPrep.registerSystem(IRhnBase.SERVER_HOSTNAME,IRhnBase.RHN_SAT_REG_CMD, true,true);
		setupMonitoringOnClient(IRhnBase.SERVER_HOSTNAME, false);
	}
	
	@Test(dependsOnGroups="monitoring-test-probes", dependsOnMethods="testMon30SetupOracleClient",groups = { "oracle-monitoring-setup" })
	public void testMon31CreateOracleProbeSuite(){
		task_Monitoring.monitoring_createProbeSuite(true, "oracleProbeSuite");
	}

	@Test(dependsOnGroups="monitoring-test-probes", dependsOnMethods="testMon31CreateOracleProbeSuite",groups = { "oracle-monitoring-setup" })
	public void testMon32CreateOracleProbes(){
		createProbes_Oracle("oracleProbeSuite", true, IRhnBase.SERVER_HOSTNAME);
	}

	@Test(dependsOnGroups="monitoring-test-probes", dependsOnMethods="testMon32CreateOracleProbes",groups = { "oracle-monitoring-setup" })
	public void testMon33AddOracleSystemToSuite(){
		task_Monitoring.monitoring_addSystemToProbeSuite(true, "oracleProbeSuite", IRhnBase.SERVER_HOSTNAME);
		task_Monitoring.pushMonitoringScoutConfig(true,true);
	
	}
		
	
	
	
	
	//NOW TRY W/ PROXY
	
	@Test(dependsOnGroups="oracle-monitoring-setup",groups = { "proxy-monitoring-setup" })
	public void testMonProxy50Prep01(){
//		task_TestPrep.removeAllSystemProfiles(true);
		task_TestPrep.removeAllProfilesOfASystem(IRhnBase.SYSTEM_HOSTNAME02);		
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME02,IRhnBase.RHN_SAT_REG_CMD, true,false);
		try{
			task_Monitoring.monitoring_delete_ProbesToSuite(false);
			}
			catch(SeleniumException se){
				se.printStackTrace();
			}
			log.info("running rpm commands on "+ IRhnBase.SYSTEM_HOSTNAME02);
			task_TestPrep.command_rpm("-e --nodeps rhnmd", IRhnBase.SYSTEM_HOSTNAME02, true);
			task_TestPrep.command_rpm("-e --nodeps rhns-proxy-monitoring", IRhnBase.SYSTEM_HOSTNAME02, true);
			task_TestPrep.command_rpm("-e --nodeps NPusers", IRhnBase.SYSTEM_HOSTNAME02, true);
			task_TestPrep.removeRHNConfigFilesFromSystem(IRhnBase.SYSTEM_HOSTNAME02);
			task_TestPrep.command_rpm("-e --nodeps rhnmd", IRhnBase.SYSTEM_HOSTNAME02, true);
			task_TestPrep.command_rpm("-e --nodeps rhns-proxy-monitoring", IRhnBase.SYSTEM_HOSTNAME02, true);
			task_TestPrep.command_rpm("-e --nodeps NPusers", IRhnBase.SYSTEM_HOSTNAME02, true);
			task_TestPrep.command_remove_localKnownHosts(false);
			task_TestPrep.enableRHNConfigManag(IRhnBase.SYSTEM_HOSTNAME02, false, false);
		
	}
	
	@Test(dependsOnGroups="oracle-monitoring-setup", dependsOnMethods="testMonProxy50Prep01",groups = { "proxy-monitoring-setup" })
	public void testMonProxy53InstallProxy(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME02);
		task_Proxy.installProxy(true,"RHN Proxy v5.3");
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME02, false);
		rh.waitForStatus("Completed", "Deploy config files to system scheduled by admin", true, IRhnBase.SYSTEM_HOSTNAME02, 30);
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME02);
		assertTrue(task_RhnBase.checkIfProxyInstalled(IRhnBase.SYSTEM_HOSTNAME02));
		task_TestPrep.command_generic("/etc/init.d/Monitoring", " restart", IRhnBase.SERVER_HOSTNAME, true);
		task_TestPrep.command_generic("/etc/init.d/MonitoringScout", " restart", IRhnBase.SERVER_HOSTNAME, true);
		task_TestPrep.command_generic("/etc/init.d/iptables", " stop", IRhnBase.SYSTEM_HOSTNAME02, true);
	}
	

	@Test(dependsOnGroups="oracle-monitoring-setup", dependsOnMethods="testMonProxy53InstallProxy",groups = { "proxy-monitoring-setup" })
	public void testMonProxy54Prep04(){
		task_RhnBase.OpenAndLogIn();
		String proxyCert = "wget http://"+IRhnBase.SYSTEM_HOSTNAME02+"/pub/RHN-ORG-TRUSTED-SSL-CERT";
		task_TestPrep.command_generic("rm  "," -Rf /root/RHN-ORG-TRUSTED-SSL-CERT" , IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_generic("echo ","\""+ proxyCert+ "\" > /root/getCommand" , IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_generic("at "," now -f /root/getCommand" , IRhnBase.SYSTEM_HOSTNAME01, false);
	}


	@Test(dependsOnGroups="oracle-monitoring-setup", dependsOnMethods="testMonProxy54Prep04",groups = { "proxy-monitoring-setup" })
	public void testMonProxy55RegisterViaProxy(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.command_rpm(" -e rhnmd", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.SYSTEM_HOSTNAME01,
				"--serverUrl=http://"+IRhnBase.SYSTEM_HOSTNAME02+"/XMLRPC --sslCACert=/root/RHN-ORG-TRUSTED-SSL-CERT  --force",
				IRhnBase.USER, IRhnBase.PASSWORD, true, false);
	}

	@Test(dependsOnGroups="oracle-monitoring-setup", dependsOnMethods="testMonProxy55RegisterViaProxy",groups = { "proxy-monitoring-setup" })
	public void testMonProxy56SetupMonitoringOnClient(){
		setupMonitoringOnClient(IRhnBase.SYSTEM_HOSTNAME01, true);
	}
	
	@Test(dependsOnGroups="oracle-monitoring-setup", dependsOnMethods="testMonProxy56SetupMonitoringOnClient",groups = { "proxy-monitoring-setup" })
	public void testMonProxy58PushScoutConfig(){
		task_Monitoring.pushMonitoringScoutConfig(true,true);
	}

	@Test(dependsOnGroups="oracle-monitoring-setup", dependsOnMethods="testMonProxy58PushScoutConfig",groups = { "proxy-monitoring-setup" })
	public void testMonProxy59CreateProbeSuite(){
		task_Monitoring.monitoring_createProbeSuite(true, "proxyProbeSuite");
	}
	
	@Test(dependsOnGroups="oracle-monitoring-setup", dependsOnMethods="testMonProxy59CreateProbeSuite",groups = { "proxy-monitoring-setup" })
	public void testMonProxy60CreateProbes(){
		createProbes01("proxyProbeSuite");
	}
	
	@Test(dependsOnGroups="oracle-monitoring-setup", dependsOnMethods="testMonProxy60CreateProbes",groups = { "proxy-monitoring-setup" })
	public void testMonProxy61AddSystemToSuite(){
		task_Monitoring.monitoring_addSystemToProbeSuite(true, "proxyProbeSuite", IRhnBase.SYSTEM_HOSTNAME01);
	}

	@Test(dependsOnGroups="oracle-monitoring-setup", dependsOnMethods="testMonProxy61AddSystemToSuite",groups = { "proxy-monitoring-setup" })
	public void testMonProxy62PushScoutConfig(){
		task_Monitoring.pushMonitoringScoutConfig(true,true);
	}

	//TEST FIRST COUPLE PROBES
	
	@Test(dependsOnGroups="oracle-monitoring-setup", dependsOnMethods="testMonProxy62PushScoutConfig",groups = { "proxy-monitoring-setup" })
	public void testMonProxy70CheckMemoryProbeStatus(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_Monitoring.monitoring_checkProbeStatus(IRhnBase.SYSTEM_HOSTNAME01, "Linux: Memory Usage", "CRITICAL, RAM free", "WARNING, RAM free");
 
	}

	@Test(dependsOnGroups="oracle-monitoring-setup", dependsOnMethods="testMonProxy70CheckMemoryProbeStatus",groups = { "proxy-monitoring-setup" })
	public void testMonProxy71CheckPingProbeStatus(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_Monitoring.monitoring_checkProbeStatus(IRhnBase.SYSTEM_HOSTNAME01, "Network Services: Ping", "OK, Round-trip avg", "OK, Round-trip avg");
	}
	
	
	
	
	
	
	private void createProbes01(String Suite){
		task_RhnBase.OpenAndLogIn();
			task_Monitoring.monitoring_addProbesToSuite_ApacheProcesses(false, Suite, IRhnBase.SYSTEM_HOSTNAME01 );
			task_Monitoring.monitoring_addProbesToSuite_Linux_ProcessCount(false, Suite);
			task_Monitoring.monitoring_addProbesToSuite_LinuxLoad(false, Suite);
			task_Monitoring.monitoring_addProbesToSuite_LinuxMemory(false, Suite);
			task_Monitoring.monitoring_addProbesToSuite_LinuxVirtualMemory(false, Suite);
			task_Monitoring.monitoring_addProbesToSuite_MySQL_DatabaseAccessibility(false, Suite, IRhnBase.SYSTEM_HOSTNAME01);
			task_Monitoring.monitoring_addProbesToSuite_NetworkPing(false, Suite);
			task_Monitoring.monitoring_addProbesToSuite_NetworkServices_DNS_Lookup(false, Suite);
			task_Monitoring.monitoring_addProbesToSuite_NetworkServices_FTP(false, Suite,IRhnBase.SYSTEM_HOSTNAME01);
			//task_Monitoring.monitoring_addProbesToSuite_NetworkServices_HTTPS(false, Suite,IRhnBase.SERVER_HOSTNAME);
			task_Monitoring.monitoring_addProbesToSuite_NetworkServices_Ping(false, Suite);
			task_Monitoring.monitoring_addProbesToSuite_NetworkServices_Remote_Ping(false, Suite);
			task_Monitoring.monitoring_addProbesToSuite_NetworkServices_RPC(false, Suite,IRhnBase.SYSTEM_HOSTNAME01);
			task_Monitoring.monitoring_addProbesToSuite_NetworkServices_SMTP(false, Suite,IRhnBase.SYSTEM_HOSTNAME01);
			task_Monitoring.monitoring_addProbesToSuite_TCP_Check_Port80(false, Suite);
				
	}
	
	private void createProbes_Oracle(String Suite, boolean setupOracle, String System){
		task_RhnBase.OpenAndLogIn();
	
			if(setupOracle){
			//this sets up oracle on another satellite w/ a public listener.ora for monitoring purposes.
			task_TestPrep.command_Oracle_open_Listener_ora(System, true);
			task_TestPrep.command_RestartSatellite(System, true, false, true, false);
			}
			task_Monitoring.monitoring_addProbesToSuite_Oracle_TNS_Ping(false, Suite);
			task_Monitoring.monitoring_addProbesToSuite_Oracle_Availability(false, Suite);
			task_Monitoring.monitoring_addProbesToSuite_Oracle_Tablespace_Usage(false, Suite);
			task_Monitoring.monitoring_addProbesToSuite_NetworkServices_HTTPS(false, Suite,IRhnBase.SERVER_HOSTNAME);
		
		}

	private void setupMonitoringOnClient(String system, boolean proxy){
			task_RhnBase.OpenAndLogIn();
			//rhncfg not needed
			try{
			//task_TestPrep.enableRHNConfigManagNoAsserts(IRhnBase.SYSTEM_HOSTNAME01, false, false);
				task_TestPrep.enableRHNConfigManag(system, false, false);
			}
			catch(Exception e){
				log.info("just make sure that tools channel is subscribed to system");
			}
			task_TestPrep.enableMonitoringEntitlement(system, true);
			if(!task_Sdc.listPackage(IRhnBase.SYSTEM_HOSTNAME01, "rhnmd")){
				task_Sdc.installPackage(system, "rhnmd");
			}
			task_TestPrep.command_generic("chkconfig ", "rhnmd on", IRhnBase.SYSTEM_HOSTNAME01, false);
			task_TestPrep.command_generic("/etc/init.d/rhnmd ", "start", IRhnBase.SYSTEM_HOSTNAME01, false);
			task_TestPrep.command_generic("/etc/init.d/iptables ", "stop", IRhnBase.SYSTEM_HOSTNAME01, false);
			String scout_key;
			scout_key = task_Monitoring.monitoring_getSSHPublicKey(false, proxy);	
			task_TestPrep.command_generic("echo ", scout_key+ " >> /var/lib/nocpulse/.ssh/authorized_keys", IRhnBase.SYSTEM_HOSTNAME01, "root", true);
			task_TestPrep.command_generic("/etc/init.d/rhnmd ", "restart", IRhnBase.SYSTEM_HOSTNAME01, false);
			
			
			
		}

		
		
	
	

	

}
