package com.redhat.rhn.harness.satellite50;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.redhat.rhn.harness.common.Sat42.tasks.RhnBase;

public class Monitoring extends SeleniumSetup{

	protected RhnHelper rh = new RhnHelper();
	
	
	




	public void testMon01EnableMonitoring(){
		task_Monitoring.enableMonitoring(true);
		task_TestPrep.command_RestartSatellite(IRhnBase.SERVER_HOSTNAME, false, true, false, false);
	}

	public void testMon02EnableMonitoringScout(){
		
		task_Monitoring.enableMonitoringScout(true);
		task_TestPrep.command_RestartSatellite(IRhnBase.SERVER_HOSTNAME, false, true, false, false);
	}

	public void testMon03Prep01(){
		
		task_TestPrep.removeAllSystemProfiles(true);
	}

	public void testMon03Prep03(){
		
		task_Monitoring.monitoring_delete_ProbesToSuite(true);
		log.info("running rpm commands on "+ IRhnBase.SYSTEM_HOSTNAME01);
		task_TestPrep.removeRHNConfigFilesFromSystem(IRhnBase.SYSTEM_HOSTNAME01);
		//rb.removePackage(IRhnBase.SYSTEM_HOSTNAME01, "rhnmd");
		task_TestPrep.command_rpm("-e --nodeps rhnmd", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.command_rpm("-e --nodeps rhns-proxy-monitoring", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.command_rpm("-e --nodeps NPusers", IRhnBase.SYSTEM_HOSTNAME01, true);
	}

	public void testMon05Prep03(){
		
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, true);
	}


	public void testMon06PrepEnableRHNConfigMgt(){
		
		task_TestPrep.enableRHNConfigManag(IRhnBase.SYSTEM_HOSTNAME01, false, true);
		task_TestPrep.enableMonitoringEntitlement(IRhnBase.SYSTEM_HOSTNAME01, true);
	}


	public void testMon07EnableRHNMD(){
		
		task_RhnBase.OpenAndLogIn();
		task_Sdc.installPackage(IRhnBase.SYSTEM_HOSTNAME01, "rhnmd");
		task_TestPrep.command_generic("chkconfig ", "rhnmd on", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_generic("/etc/init.d/rhnmd ", "start", IRhnBase.SYSTEM_HOSTNAME01, false);
	}

	public void testMon08PushScoutConfig(){
		
		task_Monitoring.pushMonitoringScoutConfig(true,false);
	}


	public void testMon09Prep01SetupKey(){
		String scout_key;
		
		scout_key = task_Monitoring.monitoring_getSSHPublicKey(true, false);
		task_TestPrep.command_generic("echo ", scout_key+ " >> /opt/nocpulse/.ssh/authorized_keys", IRhnBase.SYSTEM_HOSTNAME01, "root", true);
		//rb.command_generic("cmown ", " -R nocpulse:nocpulse /opt/nocpulse/.ssh", IRhnBase.SYSTEM_HOSTNAME01, "root", true);
		task_TestPrep.command_generic("/etc/init.d/rhnmd ", "restart", IRhnBase.SYSTEM_HOSTNAME01, false);
	}



	public void testMon10PushScoutConfig(){
		
		task_Monitoring.pushMonitoringScoutConfig(true,true);
		//test img /html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table[2]/tbody/tr/td[2]/img
	}

	public void testMon11CreateProbeSuite(){
		
		task_Monitoring.monitoring_createProbeSuite(true, "autoTest01");
	}

	public void testMon12AddProbeToSuite00(){
		
		task_Monitoring.monitoring_addProbesToSuite_NetworkPing(true, "autoTest01");
	}

	public void testMon12AddProbeToSuite01(){
		
		task_Monitoring.monitoring_addProbesToSuite_LinuxMemory(true, "autoTest01");
	}

	public void testMon13AddSystemToSuite(){
		
		task_Monitoring.monitoring_addSystemToProbeSuite(true, "autoTest01", IRhnBase.SYSTEM_HOSTNAME01);
	}

	public void testMon14PushScoutConfig(){
		
		task_Monitoring.pushMonitoringScoutConfig(true,true);
	}

	public void testMon15CheckMemoryProbeStatus(){
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_Monitoring.monitoring_checkProbeStatus(IRhnBase.SYSTEM_HOSTNAME01, "Linux: Memory Usage", "CRITICAL, RAM free", "WARNING, RAM free");

	}

	public void testMon16CheckPingProbeStatus(){
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_Monitoring.monitoring_checkProbeStatus(IRhnBase.SYSTEM_HOSTNAME01, "Network Services: Ping", "OK, Round-trip avg", "OK, Round-trip avg");
	}


	//NOW TRY W/ PROXY


	public void testMon20Prep01(){
		
		task_TestPrep.removeAllSystemProfiles(true);
		log.info("asdf");
	}

	public void testMon20Prep03(){
		
		task_Monitoring.monitoring_delete_ProbesToSuite(true);
		log.info("running rpm commands on "+ IRhnBase.SYSTEM_HOSTNAME01);
		task_TestPrep.command_rpm("-e --nodeps rhnmd", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.command_rpm("-e --nodeps rhns-proxy-monitoring", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.command_rpm("-e --nodeps NPusers", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.removeRHNConfigFilesFromSystem(IRhnBase.SYSTEM_HOSTNAME02);
		task_TestPrep.command_rpm("-e --nodeps rhnmd", IRhnBase.SYSTEM_HOSTNAME02, true);
		task_TestPrep.command_rpm("-e --nodeps rhns-proxy-monitoring", IRhnBase.SYSTEM_HOSTNAME02, true);
		task_TestPrep.command_rpm("-e --nodeps NPusers", IRhnBase.SYSTEM_HOSTNAME02, true);
	}



	public void testMon20Prep05EnableRHNConfigMgt(){
		
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, true);
		task_TestPrep.enableRHNConfigManag(IRhnBase.SYSTEM_HOSTNAME01, false, false);

	}

	public void testMon21InstallProxy(){
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_Proxy.installProxy(true,"RHN Proxy v5.0");

		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME01, false);
		rh.waitForStatus("Completed", "Deploy config files to system scheduled by admin", true, IRhnBase.SYSTEM_HOSTNAME01, 5);
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		Assert.assertTrue(task_RhnBase.checkIfProxyInstalled(IRhnBase.SYSTEM_HOSTNAME01));
	}

	public void testMon22Prep04(){
		String proxyCert = "wget http://"+IRhnBase.SYSTEM_HOSTNAME01+"/pub/RHN-ORG-TRUSTED-SSL-CERT";
		
		task_TestPrep.command_generic("echo ","\""+ proxyCert+ "\" > /root/getCommand" , IRhnBase.SYSTEM_HOSTNAME02, false);
		//"root@"+system +" echo \"/usr/sbin/rhn_check -vvv\" > /tmp/autoRhnCheck"
		task_TestPrep.command_generic("at "," now -f /root/getCommand" , IRhnBase.SYSTEM_HOSTNAME02, false);
	}



	public void testMon23RegisterViaProxy(){
		
		task_TestPrep.command_rpm(" -e rhnmd", IRhnBase.SYSTEM_HOSTNAME02, true);
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME02, IRhnBase.SYSTEM_HOSTNAME02,
				"--serverUrl=https://"+IRhnBase.SYSTEM_HOSTNAME01+"/XMLRPC --sslCACert=/root/RHN-ORG-TRUSTED-SSL-CERT  --force",
				IRhnBase.USER, IRhnBase.PASSWORD, true, true);
	}


	public void testMon24PrepEnableRHNConfigMgt(){
		
		task_TestPrep.enableRHNConfigManag(IRhnBase.SYSTEM_HOSTNAME02, false, true);
		task_TestPrep.enableMonitoringEntitlement(IRhnBase.SYSTEM_HOSTNAME02, true);
	}


	public void testMon25EnableRHNMD(){
		
		task_RhnBase.OpenAndLogIn();
		task_Sdc.installPackage(IRhnBase.SYSTEM_HOSTNAME02, "rhnmd");
		task_TestPrep.command_generic("chkconfig ", "rhnmd on", IRhnBase.SYSTEM_HOSTNAME02, false);
		task_TestPrep.command_generic("/etc/init.d/rhnmd ", "start", IRhnBase.SYSTEM_HOSTNAME02, false);
	}

	public void testMon26PushScoutConfig(){
		
		task_Monitoring.pushMonitoringScoutConfig(true,false);
	}


	public void testMon27Prep01SetupKey(){
		String scout_key;
		
		scout_key = task_Monitoring.monitoring_getSSHPublicKey(true, false);
		task_TestPrep.command_generic("echo ", scout_key+ " >> /opt/nocpulse/.ssh/authorized_keys", IRhnBase.SYSTEM_HOSTNAME02, "root", true);
		//rb.command_generic("cmown ", " -R nocpulse:nocpulse /opt/nocpulse/.ssh", IRhnBase.SYSTEM_HOSTNAME02, "root", true);
		task_TestPrep.command_generic("/etc/init.d/rhnmd ", "restart", IRhnBase.SYSTEM_HOSTNAME02, false);
	}

	public void testMon28Prep02SetupKey(){
		//IF PROXY IS INSTALLED
		String scout_key2;
		
		task_RhnBase.OpenAndLogIn();
		scout_key2 = task_Monitoring.monitoring_getSSHPublicKey(false, true);
		task_TestPrep.command_generic("echo ", scout_key2+ " >> /home/nocpulse/.ssh/authorized_keys", IRhnBase.SYSTEM_HOSTNAME02, "root", true);
		task_TestPrep.command_generic("/etc/init.d/rhnmd ", "restart", IRhnBase.SYSTEM_HOSTNAME02, false);
	}

	public void testMon29PushScoutConfig(){
		
		task_Monitoring.pushMonitoringScoutConfig(true,true);
		//test img /html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table[2]/tbody/tr/td[2]/img
	}

	public void testMon30CreateProbeSuite(){
		
		task_Monitoring.monitoring_createProbeSuite(true, "autoTest01");
	}

	public void testMon31AddProbeToSuite00(){
		
		task_Monitoring.monitoring_addProbesToSuite_NetworkPing(true, "autotest01");
	}

	public void testMonMon32AddProbeToSuite01(){
		
		task_Monitoring.monitoring_addProbesToSuite_LinuxMemory(true, "autoTest01");
	}

	public void testMon33AddSystemToSuite(){
		
		task_Monitoring.monitoring_addSystemToProbeSuite(true, "autoTest01", IRhnBase.SYSTEM_HOSTNAME02);
	}

	public void testMon34PushScoutConfig(){
		
		task_Monitoring.pushMonitoringScoutConfig(true,true);
	}

	public void testMon35CheckMemoryProbeStatus(){
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_Monitoring.monitoring_checkProbeStatus(IRhnBase.SYSTEM_HOSTNAME02, "Linux: Memory Usage", "CRITICAL, RAM free", "WARNING, RAM free");

	}

	public void testMon36CheckPingProbeStatus(){
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_Monitoring.monitoring_checkProbeStatus(IRhnBase.SYSTEM_HOSTNAME02, "Network Services: Ping", "OK, Round-trip avg", "OK, Round-trip avg");
	}




}
