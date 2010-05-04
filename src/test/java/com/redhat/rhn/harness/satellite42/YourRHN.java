package com.redhat.rhn.harness.satellite42;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

@Test(groups="tests")
public class YourRHN extends SeleniumSetup{

	protected RhnHelper rh = new RhnHelper();
	
	//YourRHN in 5.3 is now "Overview" in the Satellite webui

	@BeforeClass(groups="testplan-YourRHN")
	public void test01GetServerInfo(){
		task_RhnBase.OpenAndLogIn();
		log.fine("REDHAT-RELEASE");
		task_TestPrep.command_generic("cat", "/etc/redhat-release", IRhnBase.SERVER_HOSTNAME, true);
		log.fine("UNAME INFO");
		task_TestPrep.command_generic("uname", "-a", IRhnBase.SERVER_HOSTNAME, true);
		
	}

	@Test(groups="testplan-YourRHN")
	public void test01_FrontPage(){
		task_YourRhn.RhnAcceptance();
	}

	@Test(groups="testplan-YourRHN")
	public void test02_ChangePaginationSetting(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.changePaginationSettings("10", false);
	}

	@Test(groups="testplan-YourRHN")
	public void test01YourAccount01(){
		task_YourRhn.updateYourAccount_Position("head banger");
	}

	@Test(groups="testplan-YourRHN")
	public void test02YourAccount02(){
		task_YourRhn.populateYourAccountAddress("1111 initial lane", "919 555 5555", "Raleigh", "NC", "27133");
	}

	@Test(groups="testplan-YourRHN")
	public void test04YourAccount04(){
		task_YourRhn.updateEmailAddress("whayutin@redhat.com");
	}

	@Test(groups="testplan-YourRHN")
	public void test05RegisterSystemWithRHN(){
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, true);
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_YourRhn.checkEntitlements(IRhnBase.SYSTEM_HOSTNAME01, true);
	}
	
	@Test(groups="testplan-YourRHN")
	public void test07EntitlementsLinks(){
		task_YourRhn.checkChannelEntitlementsLink(true);
	}

	@Test(groups="testplan-YourRHN")
	public void test08CreateUser(){		
		task_RhnBase.OpenAndLogIn();
		task_YourRhn.createNonAdminUser(true,"autoFirst","autoLast","auto","redhat.com","334 deadwood drive","Raleigh","NC","27615", "919 555 5555");
	}

	

	@Test(groups="testplan-YourRHN")
	public void test10RegisterMultipleSystemsWithRHN(){
		task_TestPrep.removeAllSystemProfiles(true);
		log.fine("system name ="+IRhnBase.SYSTEM_HOSTNAME01);
		task_TestPrep.registerMultipleProfiles(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.TESTPREFIX, 11,IRhnBase.RHN_SAT_REG_CMD, false, false, false);	
	}
	
	
	@AfterClass(groups="testplan-YourRHN")
	public void test03PaginationSettings(){
		//rb.OpenAndLogIn();
		task_TestPrep.changePaginationSettings("5", true);
		task_TestPrep.removeAllSystemProfiles(false);
	}
	
	






}
