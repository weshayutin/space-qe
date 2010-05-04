package com.redhat.rhn.harness.satellite51;

import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;

@Test(groups="tests")
public class Monitoring extends com.redhat.rhn.harness.satellite50.Monitoring {


	//protected OKickStart okick = new com.redhat.rhn.harness.common.octokick.OKickStart();
	
	protected String virtLabel01 = "proxyServer";
	protected String virtLabel02 = "proxyClient";
	protected static String virtProxyServer;
	protected static String virtProxyClient;

	@Test(groups = { "testplan-Monitoring-Satellite" })
	public void testMon01EnableMonitoring(){
		
		task_Monitoring.enableMonitoring(true);
		task_TestPrep.command_RestartSatellite(IRhnBase.SERVER_HOSTNAME, true, true, false, false);
	}

	@Test(dependsOnMethods="testMon01EnableMonitoring",groups = { "testplan-Monitoring-Satellite" })
	public void testMon02EnableMonitoringScout(){
		task_Monitoring.enableMonitoringScout(true);
		//task_TestPrep.command_RestartSatellite(IRhnBase.SERVER_HOSTNAME, true);
	}

	@Test(dependsOnMethods="testMon02EnableMonitoringScout",groups = { "testplan-Monitoring-Satellite" })
	public void testMon03Prep01(){
		task_TestPrep.removeAllSystemProfiles(true);
	}

	@Test(dependsOnMethods="testMon03Prep01",groups = { "testplan-Monitoring-Satellite" })
	public void testMon03Prep03(){
		task_Monitoring.monitoring_delete_ProbesToSuite(true);
		log.info("running rpm commands on "+ IRhnBase.SYSTEM_HOSTNAME01);
		task_TestPrep.removeRHNConfigFilesFromSystem(IRhnBase.SYSTEM_HOSTNAME01);
		//rb.removePackage(IRhnBase.SYSTEM_HOSTNAME01, "rhnmd");
		task_TestPrep.command_rpm("-e --nodeps rhnmd", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.command_rpm("-e --nodeps rhns-proxy-monitoring", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.command_rpm("-e --nodeps NPusers", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, false);
	}

	@Test(dependsOnMethods="testMon03Prep03",groups = { "testplan-Monitoring-Satellite" })
	public void testMon06PrepEnableRHNConfigMgt(){
		task_RhnBase.OpenAndLogIn();
		//rhncfg not needed
		try{
		//task_TestPrep.enableRHNConfigManagNoAsserts(IRhnBase.SYSTEM_HOSTNAME01, false, false);
			task_TestPrep.enableRHNConfigManag(IRhnBase.SYSTEM_HOSTNAME01, false, false);
		}
		catch(Exception e){
			log.info("just make sure that tools channel is subscribed to system");
		}
		task_TestPrep.enableMonitoringEntitlement(IRhnBase.SYSTEM_HOSTNAME01, true);
	}

	@Test(dependsOnMethods="testMon06PrepEnableRHNConfigMgt",groups = { "testplan-Monitoring-Satellite" })
	public void testMon07EnableRHNMD(){
		task_RhnBase.OpenAndLogIn();
		task_Sdc.installPackage(IRhnBase.SYSTEM_HOSTNAME01, "rhnmd");
		task_TestPrep.command_generic("chkconfig ", "rhnmd on", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_generic("/etc/init.d/rhnmd ", "start", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_generic("/etc/init.d/iptables ", "stop", IRhnBase.SYSTEM_HOSTNAME01, false);
	}

	/*@Test(dependsOnMethods="testMon07EnableRHNMD",groups = { "testplan-Monitoring-Satellite" })
	public void testMon08PushScoutConfig(){
		task_Monitoring.pushMonitoringScoutConfig(true,false);
	}*/

	@Test(dependsOnMethods="testMon07EnableRHNMD",groups = { "testplan-Monitoring-Satellite" })
	public void testMon09Prep01SetupKey(){
		String scout_key;
		scout_key = task_Monitoring.monitoring_getSSHPublicKey(true, false);	
		task_TestPrep.command_generic("echo ", scout_key+ " >> /opt/nocpulse/.ssh/authorized_keys", IRhnBase.SYSTEM_HOSTNAME01, "root", true);
		//rb.command_generic("cmown ", " -R nocpulse:nocpulse /opt/nocpulse/.ssh", IRhnBase.SYSTEM_HOSTNAME01, "root", true);
		task_TestPrep.command_generic("/etc/init.d/rhnmd ", "restart", IRhnBase.SYSTEM_HOSTNAME01, false);
	}
	
	
	@Test(dependsOnMethods="testMon09Prep01SetupKey",groups = { "testplan-Monitoring-Satellite" })
	public void testMon10PushScoutConfig(){
		task_Monitoring.pushMonitoringScoutConfig(true,true);
	}

	@Test(dependsOnMethods="testMon10PushScoutConfig",groups = { "testplan-Monitoring-Satellite" })
	public void testMon11CreateProbeSuite(){
		task_Monitoring.monitoring_createProbeSuite(true, "autotestMon01");
	}

	@Test(dependsOnMethods="testMon11CreateProbeSuite",groups = { "testplan-Monitoring-Satellite" })
	public void testMon12AddProbeToSuite00_NetworkPing(){
		task_Monitoring.monitoring_addProbesToSuite_NetworkPing(true, "autotestMon01");
	}

	@Test(dependsOnMethods="testMon12AddProbeToSuite00_NetworkPing",groups = { "testplan-Monitoring-Satellite" })
	public void testMon12AddProbeToSuite01_LinuxMemory(){
		task_Monitoring.monitoring_addProbesToSuite_LinuxMemory(true, "autotestMon01");
	}
	
	@Test(dependsOnMethods="testMon12AddProbeToSuite01_LinuxMemory",groups = { "testplan-Monitoring-Satellite" })
	public void testMon12AddProbeToSuite02_ApacheProcesses(){
		task_Monitoring.monitoring_addProbesToSuite_ApacheProcesses(true, "autotestMon01", IRhnBase.SYSTEM_HOSTNAME01 );
	}
	
	@Test(dependsOnMethods="testMon12AddProbeToSuite02_ApacheProcesses",groups = { "testplan-Monitoring-Satellite" })
	public void testMon12AddProbeToSuite03_LinuxLoad(){
		task_Monitoring.monitoring_addProbesToSuite_LinuxLoad(true, "autotestMon01");
	}
	
	@Test(dependsOnMethods="testMon12AddProbeToSuite03_LinuxLoad",groups = { "testplan-Monitoring-Satellite" })
	public void testMon12AddProbeToSuite04_LinuxVirtMemory(){
		task_Monitoring.monitoring_addProbesToSuite_LinuxVirtualMemory(true, "autotestMon01");
	}

	@Test(dependsOnMethods="testMon12AddProbeToSuite04_LinuxVirtMemory",groups = { "testplan-Monitoring-Satellite" })
	public void testMon13AddSystemToSuite(){
		task_Monitoring.monitoring_addSystemToProbeSuite(true, "autotestMon01", IRhnBase.SYSTEM_HOSTNAME01);
	}

	@Test(dependsOnMethods="testMon13AddSystemToSuite",groups = { "testplan-Monitoring-Satellite" })
	public void testMon14PushScoutConfig(){
		task_Monitoring.pushMonitoringScoutConfig(true,true);
	}

	@Test(dependsOnMethods="testMon14PushScoutConfig",groups = { "testplan-Monitoring-Satellite" })
	public void testMon15CheckMemoryProbeStatus(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_Monitoring.monitoring_checkProbeStatus(IRhnBase.SYSTEM_HOSTNAME01, "Linux: Memory Usage", "CRITICAL, RAM free", "WARNING, RAM free");
 
	}

	@Test(dependsOnMethods="testMon15CheckMemoryProbeStatus",groups = { "testplan-Monitoring-Satellite" })
	public void testMon16CheckPingProbeStatus(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_Monitoring.monitoring_checkProbeStatus(IRhnBase.SYSTEM_HOSTNAME01, "Network Services: Ping", "OK, Round-trip avg", "OK, Round-trip avg");
	}

	
	/*//NOW TRY W/ PROXY
	@Test(groups = { "MonitoringWithProxy" })
	public void testMon18EnableMonitoring(){
		task_Monitoring.enableMonitoring(true);
		task_TestPrep.command_RestartSatellite(IRhnBase.SERVER_HOSTNAME, false);
	}

	@Test(dependsOnMethods="testMon18EnableMonitoring",groups = { "MonitoringWithProxy" })
	public void testMon19EnableMonitoringScout(){
		task_Monitoring.enableMonitoringScout(true);
		task_TestPrep.command_RestartSatellite(IRhnBase.SERVER_HOSTNAME, false);
	}
	
	@Test(dependsOnMethods="testMon19EnableMonitoringScout",groups = { "MonitoringWithProxy" })
	public void test20Prep01(){
		task_TestPrep.removeAllSystemProfiles(true);
		
	}
	
	//virt guest setup
	
	@Test(dependsOnMethods="test20Prep01",groups = { "MonitoringWithProxy" })
	public void test21Prep01(){
			task_RhnBase.OpenAndLogIn();
			task_TestPrep.command_generic("xm ", "destroy "+"automation_"+IRhnBase.SYSTEM_HOSTNAME02, IRhnBase.SYSTEM_HOSTNAME02, false);
			task_TestPrep.command_generic("xm ", "destroy "+virtProxyServer+IRhnBase.SYSTEM_HOSTNAME02, IRhnBase.SYSTEM_HOSTNAME02, false);
			task_TestPrep.command_generic("xm ", "destroy "+virtProxyClient+IRhnBase.SYSTEM_HOSTNAME02, IRhnBase.SYSTEM_HOSTNAME02, false);
			task_TestPrep.command_generic("rm " , " -Rf /var/lib/xen/images/*", IRhnBase.SYSTEM_HOSTNAME02, false);
			task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME02,HarnessConfiguration.RHN_SAT_REG_CMD, true, false);
			task_RhnBase.command_turnOff_GPG_Check(IRhnBase.SYSTEM_HOSTNAME02, false);
			task_TestPrep.enableProvisioning(IRhnBase.SYSTEM_HOSTNAME02,true);
			task_TestPrep.enableVirtualizationPlatform(IRhnBase.SYSTEM_HOSTNAME02, true);
			task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME02, false);
			
	}
	
	@Test(dependsOnMethods="test21Prep01",groups = { "MonitoringWithProxy" })
	public void test22Prep02(){
		task_KickStart.DeleteKickstartProfile(virtLabel01, true);
		okick.testCreateKickStartProfile(IRhnBase.SYSTEM_HOSTNAME02, "Red Hat Enterprise Linux AS (v. 4 for 32-bit x86)", "ks-rhel-i386-as-4-u6", virtLabel01, IRhnBase.SSH_PUBLIC_KEY, "false", "true", true);
		okick.KickStartVirtSystem(IRhnBase.SYSTEM_HOSTNAME02, virtLabel01);
	}
	
	@Test(dependsOnMethods="test22Prep02",groups = { "MonitoringWithProxy" })
	public void test22Prep03WaitForVirtGuestInstall_RHEL5Guest(){
		task_KickStart.kickstartWaitforKickstartCompletion(IRhnBase.SYSTEM_HOSTNAME02, virtLabel01,true);
		virtProxyServer = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, virtLabel01+"_"+IRhnBase.SYSTEM_HOSTNAME02, false);
		okick.checkIfSSHOpen(virtProxyServer);
		Assert.assertTrue(okick.checkIfSSHOpen(virtProxyServer));

	}
	
	@Test(dependsOnMethods="test22Prep03WaitForVirtGuestInstall_RHEL5Guest",groups = { "MonitoringWithProxy" })
	public void test23Prep01(){
		task_KickStart.DeleteKickstartProfile(virtLabel02,true);
		okick.testCreateKickStartProfile(IRhnBase.SYSTEM_HOSTNAME02, "Red Hat Enterprise Linux AS (v. 4 for 32-bit x86)", "ks-rhel-i386-as-4-u6",virtLabel02, IRhnBase.SSH_PUBLIC_KEY, "false", "true", true);
		okick.KickStartVirtSystem(IRhnBase.SYSTEM_HOSTNAME02, virtLabel02);
	}
	
	@Test(dependsOnMethods="test23Prep01",groups = { "MonitoringWithProxy" })
	public void test23Prep02WaitForVirtGuestInstall_RHEL5Guest(){
		task_KickStart.kickstartWaitforKickstartCompletion(IRhnBase.SYSTEM_HOSTNAME02, virtLabel02,true);
		virtProxyClient = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, virtLabel02+"_"+IRhnBase.SYSTEM_HOSTNAME02, false);
		okick.checkIfSSHOpen(virtProxyClient);
		Assert.assertTrue(okick.checkIfSSHOpen(virtProxyClient));

	}
		
	
	
	
	//virt guest setup
	
	
	
	
	
	@Test(dependsOnMethods="test23Prep02WaitForVirtGuestInstall_RHEL5Guest",groups = { "MonitoringWithProxy" })
	public void test29Prep03(){
		try{
		task_Monitoring.monitoring_delete_ProbesToSuite(true);
		}
		catch(SeleniumException se){
			se.printStackTrace();
		}
		log.info("running rpm commands on "+ IRhnBase.SYSTEM_HOSTNAME01);
		task_TestPrep.command_rpm("-e --nodeps rhnmd", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.command_rpm("-e --nodeps rhns-proxy-monitoring", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.command_rpm("-e --nodeps NPusers", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.removeRHNConfigFilesFromSystem(IRhnBase.SYSTEM_HOSTNAME02);
		task_TestPrep.command_rpm("-e --nodeps rhnmd", IRhnBase.SYSTEM_HOSTNAME02, true);
		task_TestPrep.command_rpm("-e --nodeps rhns-proxy-monitoring", IRhnBase.SYSTEM_HOSTNAME02, true);
		task_TestPrep.command_rpm("-e --nodeps NPusers", IRhnBase.SYSTEM_HOSTNAME02, true);
	}


	@Test(dependsOnMethods="test29Prep03",groups = { "MonitoringWithProxy" })
	public void test30Prep05EnableRHNConfigMgt(){
		virtProxyServer = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, virtLabel01+"_"+IRhnBase.SYSTEM_HOSTNAME02, true);
		task_TestPrep.command_remove_localKnownHosts(false);
		task_RhnBase.command_command_turnOff_GPG_Check(virtProxyServer, false);
		task_TestPrep.registerSystem(virtProxyServer,IRhnBase.RHN_SAT_REG_CMD, true,false);
		task_TestPrep.enableRHNConfigManag(virtProxyServer, false, false);
		task_TestPrep.enableMonitoringEntitlement(virtProxyServer, true);

	}

	@Test(dependsOnMethods="test30Prep05EnableRHNConfigMgt",groups = { "MonitoringWithProxy" })
	public void test31InstallProxy(){
		
		virtProxyServer = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, virtLabel01+"_"+IRhnBase.SYSTEM_HOSTNAME02, true);
		task_RhnBase.goToSystemSDC(virtProxyServer);
		task_Proxy.installProxy(true,"RHN Proxy v5.1");

		task_TestPrep.command_runRhnCheckInForeground(virtProxyServer, false);
		rh.waitForStatus("Completed", "Deploy config files to system scheduled by admin", true);
		task_RhnBase.goToSystemSDC(virtProxyServer);
		Assert.assertTrue(task_RhnBase.checkIfProxyInstalled(virtProxyServer));
	}

	@Test(dependsOnMethods="test31InstallProxy",groups = { "MonitoringWithProxy" })
	public void test32Prep04(){
		virtProxyServer = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, virtLabel01+"_"+IRhnBase.SYSTEM_HOSTNAME02, true);
		virtProxyClient = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, virtLabel02+"_"+IRhnBase.SYSTEM_HOSTNAME02, false);
		String proxyCert = "wget http://"+virtProxyServer+"/pub/RHN-ORG-TRUSTED-SSL-CERT";
		task_TestPrep.command_generic("echo ","\""+ proxyCert+ "\" > /root/getCommand" , virtProxyClient, false);
		//"root@"+system +" echo \"/usr/sbin/rhn_check -vvv\" > /tmp/autoRhnCheck"
		task_TestPrep.command_generic("at "," now -f /root/getCommand" , virtProxyClient, false);
	}


	@Test(dependsOnMethods="test32Prep04",groups = { "MonitoringWithProxy" })
	public void test33RegisterViaProxy(){
		virtProxyServer = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, virtLabel01+"_"+IRhnBase.SYSTEM_HOSTNAME02, true);
		virtProxyClient = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, virtLabel02+"_"+IRhnBase.SYSTEM_HOSTNAME02, false);
		task_TestPrep.command_rpm(" -e rhnmd", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.registerSystem(virtProxyClient, virtProxyClient,
				"--serverUrl=https://"+virtProxyServer+"/XMLRPC --sslCACert=/root/RHN-ORG-TRUSTED-SSL-CERT  --force",
				IRhnBase.USER, IRhnBase.PASSWORD, true, false);
	}

	@Test(dependsOnMethods="test33RegisterViaProxy",groups = { "MonitoringWithProxy" })
	public void test34PrepEnableRHNConfigMgt(){
		virtProxyClient = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, virtLabel02+"_"+IRhnBase.SYSTEM_HOSTNAME02, true);
		task_TestPrep.enableRHNConfigManag(virtProxyClient, false, false);
		task_TestPrep.enableMonitoringEntitlement(virtProxyClient, true);
	}

	@Test(dependsOnMethods="test34PrepEnableRHNConfigMgt",groups = { "MonitoringWithProxy" })
	public void test35EnableRHNMD(){
		virtProxyClient = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, virtLabel02+"_"+IRhnBase.SYSTEM_HOSTNAME02, true);
		task_Sdc.installPackage(virtProxyClient, "rhnmd");
		task_TestPrep.command_generic("chkconfig ", "rhnmd on", virtProxyClient, false);
		task_TestPrep.command_generic("/etc/init.d/rhnmd ", "start", virtProxyClient, false);
		task_TestPrep.command_generic("/etc/init.d/iptables ", "stop", virtProxyClient, false);
	}

	
	@Test(dependsOnMethods="test35EnableRHNMD",groups = { "MonitoringWithProxy" })
	public void test37Prep01SetupKey(){
		String scout_key;
		virtProxyClient = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, virtLabel02+"_"+IRhnBase.SYSTEM_HOSTNAME02, true);
		scout_key = task_Monitoring.monitoring_getSSHPublicKey(false, false);
		task_TestPrep.command_generic("echo ", scout_key+ " >> /opt/nocpulse/.ssh/authorized_keys", virtProxyClient, "root", true);
		//rb.command_generic("cmown ", " -R nocpulse:nocpulse /opt/nocpulse/.ssh", IRhnBase.SYSTEM_HOSTNAME02, "root", true);
		task_TestPrep.command_generic("/etc/init.d/rhnmd ", "restart", virtProxyClient, false);
	}

	@Test(dependsOnMethods="test37Prep01SetupKey",groups = { "MonitoringWithProxy" })
	public void test38Prep02SetupKey(){
		//IF PROXY IS INSTALLED
		String scout_key2;
		
		virtProxyClient = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, virtLabel02+"_"+IRhnBase.SYSTEM_HOSTNAME02, true);
		scout_key2 = task_Monitoring.monitoring_getSSHPublicKey(false, true);
		//rb.command_generic("mkdir ", "-p /opt/nocpulse/.ssh",virtProxyClient,"root",true);
		task_TestPrep.command_generic("echo ", scout_key2+ " >> /opt/nocpulse/.ssh/authorized_keys", virtProxyClient, "root", true);
		//rb.command_generic("echo ", scout_key2+ " >> /home/nocpulse/.ssh/authorized_keys", virtGuest02, "root", true);
		task_TestPrep.command_generic("/etc/init.d/rhnmd ", "restart", virtProxyClient, false);
	}
	
	@Test(dependsOnMethods="test38Prep02SetupKey",groups = { "MonitoringWithProxy" })
	public void test38Prep03SetupKeyOnProxy(){
		String scout_key;
		virtProxyServer = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, virtLabel01+"_"+IRhnBase.SYSTEM_HOSTNAME02, true);
		scout_key = task_Monitoring.monitoring_getSSHPublicKey(false, false);
		task_TestPrep.command_generic("echo ", scout_key+ " >> /home/nocpulse/.ssh/authorized_keys", virtProxyServer, "root", true);
		//rb.command_generic("mkdir ", "-p /opt/nocpulse/.ssh",virtProxyServer,"root",true);
		//rb.command_generic("chown ", " -R nocpulse:nocpulse /opt/nocpulse/.ssh", IRhnBase.SYSTEM_HOSTNAME02, "root", true);
		task_TestPrep.command_generic("/etc/init.d/MonitoringScout ", "restart", virtProxyServer, false);
		task_TestPrep.command_generic("/etc/init.d/rhn-proxy ", "restart", virtProxyServer, false);
	}

	@Test(dependsOnMethods="test38Prep03SetupKeyOnProxy",groups = { "MonitoringWithProxy" })
	public void test39PushScoutConfig(){
		
		task_Monitoring.pushMonitoringScoutConfig(true,true);
		//test img /html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table[2]/tbody/tr/td[2]/img
	}

	@Test(dependsOnMethods="test39PushScoutConfig",groups = { "MonitoringWithProxy" })
	public void test40CreateProbeSuite(){
		
		task_Monitoring.monitoring_createProbeSuite(true, "autotestMon01");
	}

	@Test(dependsOnMethods="test40CreateProbeSuite",groups = { "MonitoringWithProxy" })
	public void test41AddProbeToSuite00(){
		
		task_Monitoring.monitoring_addProbesToSuite_NetworkPing(true, "autotestMon01");
	}

	@Test(dependsOnMethods="test41AddProbeToSuite00",groups = { "MonitoringWithProxy" })
	public void test42AddProbeToSuite01(){
		
		task_Monitoring.monitoring_addProbesToSuite_LinuxMemory(true, "autotestMon01");
	}

	@Test(dependsOnMethods="test42AddProbeToSuite01",groups = { "MonitoringWithProxy" })
	public void test43AddSystemToSuite(){
		virtProxyClient = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, virtLabel02+"_"+IRhnBase.SYSTEM_HOSTNAME02, true);
		task_Monitoring.monitoring_addSystemToProbeSuite(false, "autotestMon01", virtProxyClient);
	}

	@Test(dependsOnMethods="test43AddSystemToSuite",groups = { "MonitoringWithProxy" })
	public void test44PushScoutConfig(){
		
		task_Monitoring.pushMonitoringScoutConfig(true,true);
	}

	@Test(dependsOnMethods="test44PushScoutConfig",groups = { "MonitoringWithProxy" })
	public void test45CheckMemoryProbeStatus(){
		
		virtProxyServer = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, virtLabel01+"_"+IRhnBase.SYSTEM_HOSTNAME02, true);
		virtProxyClient = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, virtLabel02+"_"+IRhnBase.SYSTEM_HOSTNAME02, false);
		task_RhnBase.goToSystemSDC(virtProxyClient);
		task_Monitoring.monitoring_checkProbeStatus(virtProxyServer, "Linux: Memory Usage", "CRITICAL, RAM free", "WARNING, RAM free");

	}

	@Test(dependsOnMethods="test45CheckMemoryProbeStatus",groups = { "MonitoringWithProxy" })
	public void test46CheckPingProbeStatus(){
		
		virtProxyServer = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, virtLabel01+"_"+IRhnBase.SYSTEM_HOSTNAME02, true);
		virtProxyClient = task_KickStart.getVirtGuest_HostName(IRhnBase.SYSTEM_HOSTNAME02, virtLabel02+"_"+IRhnBase.SYSTEM_HOSTNAME02, false);
		task_RhnBase.goToSystemSDC(virtProxyClient);
		task_Monitoring.monitoring_checkProbeStatus(virtProxyServer, "Network Services: Ping", "OK, Round-trip avg", "OK, Round-trip avg");
	}


*/



}
