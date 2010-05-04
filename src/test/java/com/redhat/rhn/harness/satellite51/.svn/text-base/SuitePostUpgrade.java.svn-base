package com.redhat.rhn.harness.satellite51;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Sat42.tasks.RhnBase;

public class SuitePostUpgrade extends com.redhat.rhn.harness.common.Sat51.tasks.TestPrep{
	
	
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
	
	
	public void test01verifySystemisPresent(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.verifySystemProfile(IRhnBase.SYSTEM_HOSTNAME01);
		assertTrue(rh.isTextPresent(IRhnBase.SYSTEM_HOSTNAME01));	
	}
	
	public void test02VerifyChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyChannelExists(IRhnBase.RHN_CHANNEL01, true);
		task_Channels.verifyChannelExists(IRhnBase.RHN_CHANNEL02, true);
		task_Channels.verifyChannelExists(customChannel01, true);
		task_Channels.verifyChannelExists(autochannel01, true);
	}
	
	public void test03VerifyActivationKey(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToKey("testkey");
		assertTrue(rh.isTextPresent("testkey"));
	}

    public void test04ActivationKeyRole01(){	
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
    
     public void test05ChannelRole01(){
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
     
     public void test06SysGroupRole01(){
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
     
     public void test07ConfigAdminRole01(){
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
     
     public void test08CheckMemoryProbeStatus(){ 		
 		task_RhnBase.OpenAndLogIn();
 		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
 		task_Monitoring.monitoring_checkProbeStatus(IRhnBase.SYSTEM_HOSTNAME01, "Linux: Memory Usage", "CRITICAL, RAM free", "WARNING, RAM free");

 	}

 	public void test09CheckPingProbeStatus(){
 		task_RhnBase.OpenAndLogIn();
 		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
 		task_Monitoring.monitoring_checkProbeStatus(IRhnBase.SYSTEM_HOSTNAME01, "Network Services: Ping", "OK, Round-trip avg", "OK, Round-trip avg");
 	}
	
	
}
