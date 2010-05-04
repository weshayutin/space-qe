package com.redhat.rhn.harness.satellite50;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Sat42.tasks.RhnBase;

public class SuitePreUpgrade extends com.redhat.rhn.harness.common.Sat50.tasks.TestPrep{
	
	RhnHelper rh = new RhnHelper();
	
	
	protected static String channelError01 = "Invalid channel label 'autoChannel01' - must be at least 6 characters long, begin with a letter, and contain only lowercase letters, digits, '-', '_', and '.'";
	protected static String autoclonechannel01 = "autoclonechannel01";
	protected static String customChannel01 = "autocustomchannel01";
	protected static String autochannel01 = "autochannel01";
	public static String groupName = "autoGroup";
	protected static String ActivationRole = "userActivationRole";
	protected static String ChannelRole = "userChannelRole";
	protected static String SysGroupRole = "userSysGroupRole";
	protected static String ConfigAdminRole = "userConfigAdminRole";
	protected static String OrgAdminRole = "userOrgAdminRole";
	protected static String MonitorRole = "userMonitorRole";
	protected static String SatAdminRole = "userSatAdminRole"; 

	
	
	public void test01Prep04(){
		
		task_RhnBase.OpenAndLogIn();
		task_Channels.deleteAllCustomChannels();
		//rb.DeleteAllErrata(false);	
	}

	
	public void test01Prep05(){
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, true);
	}

	public void test01VerifyChannel(){
		
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyChannelExists(IRhnBase.RHN_CHANNEL01, true);
	}

	public void test02VerifyChannel(){
		
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyChannelExists(IRhnBase.RHN_CHANNEL02, true);
	}

	public void test03CreateClonedChannel(){
		
		task_RhnBase.OpenAndLogIn();
		task_Channels.createChannelClone(IRhnBase.RHN_CHANNEL01, autochannel01,autochannel01, 0);
	}

	public void test04RhnPushPackage01(){
		
		task_RhnBase.OpenAndLogIn();
		task_Channels.rhnPushPackageToChannel(autochannel01, "testAutoFile-1-1.0.i386.rpm", "./src/main/resources/", IRhnBase.SERVER_HOSTNAME, IRhnBase.USER, IRhnBase.PASSWORD);
	}

	public void test05DVerifyClonedChannel(){
		
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyChannelExists(autochannel01, true);
	}


	public void test10CreateCustomChannel(){
		
		task_RhnBase.OpenAndLogIn();
		task_Channels.createCustomChannel(customChannel01);
		task_Channels.verifyChannelExists(customChannel01, true);
	}
	
	public void test11RhnPushPackage02(){
		
		task_RhnBase.OpenAndLogIn();
		task_Channels.rhnPushPackageToChannel(customChannel01, "testAutoFile-2-1.0.i386.rpm", "./src/main/resources/", IRhnBase.SERVER_HOSTNAME, IRhnBase.USER, IRhnBase.PASSWORD);
	}
	
	public void test20CreateUser_Activation(){
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.createUserCustom(ActivationRole,"autoFirst","autoLast","auto","redhat.com");
	}

	public void test21VerifyUser(){
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToUser(ActivationRole);
	}

	public void test22AddRoleToUserActivation(){
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.UserEditRole(ActivationRole, IRhnBase.USER_ROLE_ACTIVATION, true);
	}

	public void test23ActivationKeyRole01(){
		
		task_RhnBase.OpenAndLogIn(ActivationRole, IRhnBase.PASSWORD);
		try{
		task_RhnBase.goToSystem(IRhnBase.SYSTEM_HOSTNAME01);
		}
		catch(Exception e){
			Assert.assertTrue(rh.isTextPresent("No systems."));
		}
		Assert.assertTrue(rh.isTextNotPresent("Satellite Tools"));
		Assert.assertTrue(!rh.isElementPresent("link=Monitoring", true));
		Assert.assertTrue(rh.isTextNotPresent("Users"));
	}

	public void test24ActivationKeyRole02(){
		
		task_RhnBase.OpenAndLogIn(ActivationRole, IRhnBase.PASSWORD);
		task_RhnBase.createActivationKey("testkey", "939393", "5", false, true, false, false, false);
	}
   //ACTIVATION

   //CHANNEL
	public void test25CreateUser_Channel(){
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.createUserCustom(ChannelRole,"autoFirst","autoLast","auto","redhat.com");
	}

	public void test26AddRoleToUserChannel(){
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.UserEditRole(ChannelRole, IRhnBase.USER_ROLE_CHANNEL, true);
	}

	public void test27ChannelRole01(){
		
		task_RhnBase.OpenAndLogIn(ChannelRole, IRhnBase.PASSWORD);
		try{
		task_RhnBase.goToSystem(IRhnBase.SYSTEM_HOSTNAME01);
		}
		catch(Exception e){
			Assert.assertTrue(rh.isTextPresent("No systems."));
		}
		Assert.assertTrue(rh.isTextNotPresent("Satellite Tools"));
		Assert.assertTrue(!rh.isElementPresent("link=Monitoring", true));
		Assert.assertTrue(rh.isTextNotPresent("Users"));
	}

	public void test28ChannelRole02(){
		
		task_RhnBase.OpenAndLogIn(ChannelRole, IRhnBase.PASSWORD);
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "bash", false, true);
	}

	public void test29CreateClonedChannel(){
		
		task_RhnBase.OpenAndLogIn();
		task_Channels.deleteCustomChannel("autorole");
		task_Channels.createChannelClone(IRhnBase.RHN_CHANNEL01, "autorole","autorole", 0);
	}

	//CHANNEL

	public void test31CreateUser_SystemGroup(){
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.createUserCustom(SysGroupRole,"autoFirst","autoLast","auto","redhat.com");
		
	}

	public void test32AddRoleToUserSystemGroup(){
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.UserEditRole(SysGroupRole, IRhnBase.USER_ROLE_SYSTEM_GROUP, true);
	}

	public void test33SysGroupRole01(){
		
		task_RhnBase.OpenAndLogIn(SysGroupRole, IRhnBase.PASSWORD);
		try{
		task_RhnBase.goToSystem(IRhnBase.SYSTEM_HOSTNAME01);
		}
		catch(Exception e){
			Assert.assertTrue(rh.isTextPresent("No systems."));
		}
		Assert.assertTrue(rh.isTextNotPresent("Satellite Tools"));
		Assert.assertTrue(!rh.isElementPresent("link=Monitoring", true));
		Assert.assertTrue(rh.isTextNotPresent("Users"));
	}

	public void test34SysGroupRole02(){
		//start here
		task_RhnBase.OpenAndLogIn(SysGroupRole, IRhnBase.PASSWORD);
		task_RhnBase.groupDelete(groupName);
		task_RhnBase.groupCreate(groupName);
	}

	//Config Admin

	public void test35CreateUser_ConfigAdmin(){
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.createUserCustom(ConfigAdminRole,"autoFirst","autoLast","auto","redhat.com");
	}

	public void test36AddRoleToUserConfigAdmin(){
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.UserEditRole(ConfigAdminRole, IRhnBase.USER_ROLE_CONFIGURATION, true);
	}

	public void test37ConfigAdminRole01(){
		
		task_RhnBase.OpenAndLogIn(ConfigAdminRole, IRhnBase.PASSWORD);
		try{
		task_RhnBase.goToSystem(IRhnBase.SYSTEM_HOSTNAME01);
		}
		catch(Exception e){
			Assert.assertTrue(rh.isTextPresent("No systems."));
		}
		Assert.assertTrue(rh.isTextNotPresent("Satellite Tools"));
		Assert.assertTrue(!rh.isElementPresent("link=Monitoring", true));
		Assert.assertTrue(rh.isTextNotPresent("Users"));
	}

/*	public void test38ConfigAdminRole02(){
		
		rb.OpenAndLogIn(ConfigAdminRole, IRhnBase.PASSWORD);
		rb.CreateNewConfigChannel("autotest", false, false, true);

	}*/
	
public void test40EnableMonitoring(){
		
		task_RhnBase.enableMonitoring(true);
		task_TestPrep.command_RestartSatellite(IRhnBase.SERVER_HOSTNAME, false, true, false, false);
	}

	public void test41EnableMonitoringScout(){
		
		task_RhnBase.enableMonitoringScout(true);
		task_TestPrep.command_RestartSatellite(IRhnBase.SERVER_HOSTNAME, false, true, false, false);
	}

	
	public void test43Prep03(){
		
		task_Monitoring.monitoring_delete_ProbesToSuite(true);
		log.fine("running rpm commands on "+ IRhnBase.SYSTEM_HOSTNAME01);
		task_TestPrep.removeRHNConfigFilesFromSystem(IRhnBase.SYSTEM_HOSTNAME01);
		//rb.removePackage(IRhnBase.SYSTEM_HOSTNAME01, "rhnmd");
		task_TestPrep.command_rpm("-e --nodeps rhnmd", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.command_rpm("-e --nodeps rhns-proxy-monitoring", IRhnBase.SYSTEM_HOSTNAME01, true);
		task_TestPrep.command_rpm("-e --nodeps NPusers", IRhnBase.SYSTEM_HOSTNAME01, true);
	}

	
	public void test45PrepEnableMonitoringEntitlement(){
		task_RhnBase.OpenAndLogIn();
		//rb.enableRHNConfigManag(IRhnBase.SYSTEM_HOSTNAME01, false, true);
		task_TestPrep.enableMonitoringEntitlement(IRhnBase.SYSTEM_HOSTNAME01, true);
	}


	public void test46EnableRHNMD(){
		
		task_RhnBase.OpenAndLogIn();
		task_Sdc.alterBaseChannelSubscriptions(false, IRhnBase.SYSTEM_HOSTNAME01, "Red Hat Network Tools");
		task_Sdc.installPackage(IRhnBase.SYSTEM_HOSTNAME01, "rhnmd");
		task_TestPrep.command_generic("chkconfig ", "rhnmd on", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_generic("/etc/init.d/rhnmd ", "start", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_generic("/etc/init.d/iptables ", "stop", IRhnBase.SYSTEM_HOSTNAME01, false);
	}

	public void test47PushScoutConfig(){
		
		task_Monitoring.pushMonitoringScoutConfig(true,false);
	}


	public void test48Prep01SetupKey(){
		String scout_key;
		task_RhnBase.command_RemoveSSHKnownHosts(IRhnBase.SYSTEM_HOSTNAME01);
		scout_key = task_RhnBase.monitoring_getSSHPublicKey(true, false);	
		task_TestPrep.command_generic("echo ", scout_key+ " >> /opt/nocpulse/.ssh/authorized_keys", IRhnBase.SYSTEM_HOSTNAME01, "root", true);
		//rb.command_generic("cmown ", " -R nocpulse:nocpulse /opt/nocpulse/.ssh", IRhnBase.SYSTEM_HOSTNAME01, "root", true);
		task_TestPrep.command_generic("/etc/init.d/rhnmd ", "restart", IRhnBase.SYSTEM_HOSTNAME01, false);
	}



	public void test49PushScoutConfig(){
		
		task_Monitoring.pushMonitoringScoutConfig(true,true);
		//test img /html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table[2]/tbody/tr/td[2]/img
	}

	public void test50CreateProbeSuite(){
		
		task_Monitoring.monitoring_createProbeSuite(true, "autoTest01");
	}

	public void test51AddProbeToSuite00(){
		
		task_Monitoring.monitoring_addProbesToSuite_NetworkPing(true, "autoTest01");
	}

	public void test52AddProbeToSuite01(){
		
		task_Monitoring.monitoring_addProbesToSuite_LinuxMemory(true, "autoTest01");
	}

	public void test53AddSystemToSuite(){
		
		task_Monitoring.monitoring_addSystemToProbeSuite(true, "autoTest01", IRhnBase.SYSTEM_HOSTNAME01);
	}

	public void test54PushScoutConfig(){
		
		task_Monitoring.pushMonitoringScoutConfig(true,true);
	}

	public void test55CheckMemoryProbeStatus(){
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_Monitoring.monitoring_checkProbeStatus(IRhnBase.SYSTEM_HOSTNAME01, "Linux: Memory Usage", "CRITICAL, RAM free", "WARNING, RAM free");

	}

	public void test56CheckPingProbeStatus(){
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_Monitoring.monitoring_checkProbeStatus(IRhnBase.SYSTEM_HOSTNAME01, "Network Services: Ping", "OK, Round-trip avg", "OK, Round-trip avg");
	}


}
