package com.redhat.rhn.harness.satellite42;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.redhat.rhn.harness.common.Sat42.tasks.RhnBase;

@Test(groups="tests")
public class RhnRegister extends SeleniumSetup{


	protected RhnHelper rh = new RhnHelper();
	
	

	public static String RhnReg_basic_noforce = "rhnreg_ks --username="+IRhnBase.USER+ "  --password="+ IRhnBase.PASSWORD;
	public static String RhnReg_basic_force = "rhnreg_ks --username="+IRhnBase.USER+ "  --password="+ IRhnBase.PASSWORD +" --force";
	public static String RhnReg_basic_url_force = "rhnreg_ks --username="+IRhnBase.USER+ "  --password="+ IRhnBase.PASSWORD + " --serverUrl=http://"+HarnessConfiguration.RHN_HOST+"/XMLRPC" + " --force";
	public static String SERVER_REG = "http://"+HarnessConfiguration.RHN_HOST + "/XMLRPC";

	@BeforeClass(groups = { "setup" })
	public void test00Prep(){
		task_TestPrep.removeAllSystemProfiles(true);
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, true);  // to make sure the client has been registered at least once
		task_TestPrep.removeAllSystemProfiles(true);
	}
	
	@Test(groups = { "testplan-Registration-Satellite" })
	public void test01_registerSystem_withoutServerURLInUp2date(){
		task_TestPrep.modifyServerParent(IRhnBase.SYSTEM_HOSTNAME01, true, "serverURL=;");  // remove the serverURL from the clients config up2date file
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,RhnReg_basic_force, false, true); // should fail since config up2date file has no serverURL
	}

	@Test(dependsOnMethods="test01_registerSystem_withoutServerURLInUp2date",groups = { "testplan-Registration-Satellite" })
	public void test02_registerSystem_withServerURLOption(){
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,RhnReg_basic_url_force, true, true); // should pass since serverURL option is specified
	}
	
	@Test(dependsOnMethods="test02_registerSystem_withServerURLOption",groups = { "testplan-Registration-Satellite" })
	public void test03_registerSystem_withServerURLInUp2date(){
		task_TestPrep.modifyServerParent(IRhnBase.SYSTEM_HOSTNAME01, true, "serverURL="+SERVER_REG);  // append the serverURL to the clients config up2date file
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,RhnReg_basic_force, true, true); // should pass since config up2date file has a serverURL
	}
	
	@Test(dependsOnMethods="test03_registerSystem_withServerURLInUp2date",groups = { "testplan-Registration-Satellite" })
	public void test04_registerSystem_withNoForce(){
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,RhnReg_basic_noforce, false, true); // should fail since system was already registered in prior test
	}
	
	@Test(dependsOnMethods="test04_registerSystem_withNoForce",groups = { "testplan-Registration-Satellite" })
	public void test05_registerSystem_withForce(){
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,RhnReg_basic_force, true, true); // should pass since config up2date file has a serverURL and we are using force to override prior registration
	}
	
	
	/*public void test09UnregisterGUISystem(){
		
		task_TestPrep.unregisterAllProfilesOfSystem("AUTO_GUI_TEST_01");
	}

	public void test10RhnRegisterGUI() throws Exception{
		
		log.info("dogtail and rhn_register do not work together");
		throw new Exception();
		//runRhnRegisterGUI(SERVER_REG,USER,PASSWORD,"AUTO_GUI_TEST_01");
	}

	public void test11CheckGUIRegistration(){
		
		log.info("dogtail and rhn_register do not work together"); 
		checkRegisteredSystem("AUTO_GUI_TEST_01", true);
	}*/



}
