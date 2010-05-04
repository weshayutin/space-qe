package com.redhat.rhn.harness.satellite42;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

@Test(groups="tests")
public class UserRoles extends SeleniumSetup {

	protected RhnHelper rh = new RhnHelper();
	
	 
	protected static String groupName = "autoGroup";
    protected static String ActivationRole = "userActivationRole";
	protected static String ChannelRole = "userChannelRole";
	protected static String SysGroupRole = "userSysGroupRole";
	protected static String ConfigAdminRole = "userConfigAdminRole";
	protected static String OrgAdminRole = "userOrgAdminRole";
	protected static String MonitorRole = "userMonitorRole";
	protected static String SatAdminRole = "userSatAdminRole"; //5.1
  


/*
      User — Also known as a System Group User, this is the standard role associated with any newly created user. This person may be granted access to manage system groups and software channels. The systems must be in system groups to which the user has permissions for them to be manageable or even visible. Remember, however, all globally subscribable channels may be used by anyone.

      Activation Key Administrator — This role is designed to manage your organization's collection of activation keys. This person can create, modify, and delete any key within your overarching account.
      certificate authority — This role has complete access to the software channels and related associations within your organization. It requires RHN Satellite Server or RHN Proxy Server. This person may change the base channels of systems, make channels globally subscribable, and create entirely new channels.

      Configuration Administrator — This role enables the user to manage the configuration of systems in the organization using either the RHN website or the Red Hat Network Configuration Manager.

      Monitoring Administrator — This role allows for the scheduling of probes and oversight of other Monitoring infrastructure. This role is available only on Monitoring-enabled RHN Satellite Server version 3.6 or later.

      Organization Administrator — This role can perform any function available within Red Hat Network. As the master account for your organization, the person holding this role can alter the privileges of all other accounts, as well as conduct any of the tasks available to the other roles. Like the other roles, multiple Organization Administrators may exist.

      System Group Administrator — This role is one step below Organization Administrator in that it has complete authority over the systems and system groups to which it is granted access. This person can create new system groups, delete any assigned systems groups, add systems to groups, and manage user access to groups.

      */


	@BeforeClass(groups = { "setup" })
	public void test02Prep02(){	
		task_TestPrep.removeAllSystemProfiles(true);
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, true);
	}


	// Activation Key Administrator
	@Test(groups = { "testplan-User_Roles_and_Groups" }, alwaysRun=true)
	public void test10_createUser_Activation(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.createUserCustom(ActivationRole,"autoFirst","autoLast","auto","redhat.com");
	}

	@Test(dependsOnMethods="test10_createUser_Activation",groups = { "testplan-User_Roles_and_Groups" })
	public void test11_verifyUser_Activation(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToUser(ActivationRole);
	}
	
	@Test(dependsOnMethods="test11_verifyUser_Activation",groups = { "testplan-User_Roles_and_Groups" })
	public void test12_addRole_toUserActivation(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.UserEditRole(ActivationRole, IRhnBase.USER_ROLE_ACTIVATION, true);
	}

	@Test(dependsOnMethods="test12_addRole_toUserActivation",groups = { "testplan-User_Roles_and_Groups" })
	public void test13_activationKeyRole_negativeTest(){
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

	@Test(dependsOnMethods="test13_activationKeyRole_negativeTest",groups = { "testplan-User_Roles_and_Groups" })
	public void test14_activationKeyRole_createActivationKey(){
		task_RhnBase.OpenAndLogIn(ActivationRole, IRhnBase.PASSWORD);
		task_RhnBase.createActivationKey("testkey", "939393", "5", false, true, false, false, false);
	}


   // Channel Administrator
	@Test(dependsOnMethods="test14_activationKeyRole_createActivationKey",groups = { "testplan-User_Roles_and_Groups" }, alwaysRun=true)
	public void test20_createUser_Channel(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.createUserCustom(ChannelRole,"autoFirst","autoLast","auto","redhat.com");
	}

	@Test(dependsOnMethods="test20_createUser_Channel",groups = { "testplan-User_Roles_and_Groups" })
	public void test21_addRoleToUser_Channel(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.UserEditRole(ChannelRole, IRhnBase.USER_ROLE_CHANNEL, true);
	}

	@Test(dependsOnMethods="test21_addRoleToUser_Channel",groups = { "testplan-User_Roles_and_Groups" })
	public void test22_channelRole_negativeTest(){
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

	@Test(dependsOnMethods="test22_channelRole_negativeTest",groups = { "testplan-User_Roles_and_Groups" })
	public void test23_channelRole_quickSearch(){
		task_RhnBase.OpenAndLogIn(ChannelRole, IRhnBase.PASSWORD);
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "bash", false, true);
	}

	@Test(dependsOnMethods="test23_channelRole_quickSearch",groups = { "testplan-User_Roles_and_Groups" })
	public void test25_channelRole_createClonedChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.deleteCustomChannel("autorole");
		task_Channels.createChannelClone(IRhnBase.RHN_CHANNEL01, "autorole","autorole", 0);
	}

	// System Group Administrator
	@Test(dependsOnMethods="test25_channelRole_createClonedChannel",groups = { "testplan-User_Roles_and_Groups" }, alwaysRun=true)
	public void test30_createUser_SystemGroup(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.createUserCustom(SysGroupRole,"autoFirst","autoLast","auto","redhat.com");
		log.info("break");
	}

	@Test(dependsOnMethods="test30_createUser_SystemGroup",groups = { "testplan-User_Roles_and_Groups" })
	public void test31_addRoleToUser_SystemGroup(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.UserEditRole(SysGroupRole, IRhnBase.USER_ROLE_SYSTEM_GROUP, true);
	}

	@Test(dependsOnMethods="test31_addRoleToUser_SystemGroup",groups = { "testplan-User_Roles_and_Groups" })
	public void test32_systemGroup_negativeTest(){
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

	@Test(dependsOnMethods="test32_systemGroup_negativeTest",groups = { "testplan-User_Roles_and_Groups" })
	public void test33_SysGroupRole_createGroup(){
		int n = 1000;
		Random generator = new Random();
		int rand = generator.nextInt(n);
		task_RhnBase.OpenAndLogIn(SysGroupRole, IRhnBase.PASSWORD);
		//rb.groupDelete(groupName);
		groupName+=rand;
		task_RhnBase.groupDelete(groupName);
		task_RhnBase.groupCreate(groupName);
	}

	
	// Configuration Administrator
	@Test(dependsOnMethods="test33_SysGroupRole_createGroup",groups = { "testplan-User_Roles_and_Groups" }, alwaysRun=true)
	public void test40_createUser_ConfigAdmin(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.createUserCustom(ConfigAdminRole,"autoFirst","autoLast","auto","redhat.com");
	}

	@Test(dependsOnMethods="test40_createUser_ConfigAdmin",groups = { "testplan-User_Roles_and_Groups" })
	public void test41_addRoleToUser_ConfigAdmin(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.UserEditRole(ConfigAdminRole, IRhnBase.USER_ROLE_CONFIGURATION, true);
	}

	@Test(dependsOnMethods="test41_addRoleToUser_ConfigAdmin",groups = { "testplan-User_Roles_and_Groups" })
	public void test42_ConfigAdminRole_negativeTest(){
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

	@Test(dependsOnMethods="test42_ConfigAdminRole_negativeTest",groups = { "testplan-User_Roles_and_Groups" })
	public void test43_ConfigAdminRole_createConfigChannel(){
		task_RhnBase.OpenAndLogIn(ConfigAdminRole, IRhnBase.PASSWORD);
		task_RhnBase.CreateNewConfigChannel("autotest", false, true, true);
	}

	
	// Monitoring Administrator
	@Test(dependsOnMethods="test43_ConfigAdminRole_createConfigChannel",groups = { "testplan-User_Roles_and_Groups" }, alwaysRun=true)
	public void test50_createUser_MonAdmin(){
		int n = 1000;
		Random generator = new Random();
		int rand = generator.nextInt(n);
		log.info("rand =" + rand);
		OrgAdminRole += rand;
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.createUserCustom(MonitorRole,"autoFirst","autoLast","auto","redhat.com");
	}

	@Test(dependsOnMethods="test50_createUser_MonAdmin",groups = { "testplan-User_Roles_and_Groups" })
	public void test51_addRoleToUser_MonAdmin(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.UserEditRole(MonitorRole, IRhnBase.USER_ROLE_MONITORING, true);
	}
	// Monitoring Administrator //TODO SKIPPING THE MONITORING ADMINISTRATOR VERIFICATIOBN TESTS FOR NOW
	
	
	// Organization Administrator  (Administrative Role)
	@Test(dependsOnMethods="test51_addRoleToUser_MonAdmin",groups = { "testplan-User_Roles_and_Groups" }, alwaysRun=true)
	public void test60_createUser_OrgAdmin(){
		int n = 1000;
		Random generator = new Random();
		int rand = generator.nextInt(n);
		log.info("rand =" + rand);
		OrgAdminRole += rand;
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.createUserCustom(OrgAdminRole,"autoFirst","autoLast","auto","redhat.com");
	}

	@Test(dependsOnMethods="test60_createUser_OrgAdmin",groups = { "testplan-User_Roles_and_Groups" })
	public void test61_addRoleToUser_OrgAdmin(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.UserEditRole(OrgAdminRole, IRhnBase.USER_ROLE_ORG_ADMIN, true);
	}

	@Test(dependsOnMethods="test61_addRoleToUser_OrgAdmin",groups = { "testplan-User_Roles_and_Groups" })
	public void test62_OrgAdmin_negativeTest(){
		task_RhnBase.OpenAndLogIn(OrgAdminRole, IRhnBase.PASSWORD);
		Assert.assertTrue(rh.isTextNotPresent("Satellite Tools"));
		Assert.assertTrue(rh.isTextPresent("Users"));
	}

	@Test(dependsOnMethods="test62_OrgAdmin_negativeTest",groups = { "testplan-User_Roles_and_Groups" })
	public void test63_OrgAdmin_addSystemToSystemGroup(){
		task_RhnBase.OpenAndLogIn(OrgAdminRole, IRhnBase.PASSWORD);
		task_RhnBase.goToSystem(IRhnBase.SYSTEM_HOSTNAME01);
		task_RhnBase.addSystemToSystemGroup(IRhnBase.SYSTEM_HOSTNAME01, groupName);
	}



}
