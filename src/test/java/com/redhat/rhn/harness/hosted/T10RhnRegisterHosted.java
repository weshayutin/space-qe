package com.redhat.rhn.harness.hosted;

import java.util.Random;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Hosted.tasks.RhnBase;
import com.redhat.rhn.harness.common.Hosted.tasks.RhnRegister;

public class T10RhnRegisterHosted extends RhnRegister{


	RhnHelper rh = new RhnHelper();
	RhnBase rb = new RhnBase();


	private String SYSTEM = IRhnBase.SYSTEM_HOSTNAME01;
	private static String RhnReg_basic = "rhnreg_ks --email=autosat@redhat.com --username="+IRhnBase.USER+ "  --password="+ IRhnBase.PASSWORD +" --force";
	private static String RhnReg_basic01 = "rhnreg_ks --email=autosat@redhat.com --username="+IRhnBase.USER+ "  --password="+ IRhnBase.PASSWORD;
	private static String RhnReg_basic02 = "rhnreg_ks --email=autosat@redhat.com";
	private static String SERVER_REG = "http://xmlrpc."+HarnessConfiguration.RHN_HOST + "/XMLRPC";
	private static String SERVER_REG_SEC = "https://xmlrpc."+HarnessConfiguration.RHN_HOST + "/XMLRPC";
	private int rand;

	public void testPrep01(){
		
		task_TestPrep.unregisterAllProfilesOfSystem(SYSTEM, true);
	}
	public void testPrep02(){
		
		task_TestPrep.unregisterAllProfilesOfSystem(IRhnBase.TESTPREFIX, true);
	}

	public void testPrepRemoveServerURL01(){
		
		rb.modifyServerParent(SYSTEM, true, "serverURL="+SERVER_REG);
	}

	/*public void testRegisterSystemWithRHN01(){
		int n = 1000;
		Random generator = new Random();
		rand = generator.nextInt(n);
		
		task_TestPrep.registerSystem(SYSTEM,RhnReg_basic02 +" --username=autoRand"+rand + " --password="+IRhnBase.PASSWORD+" --force", false, true);
		rb.clickSignOut();
		loginWithNewUser(SYSTEM,"autoRand"+rand , IRhnBase.PASSWORD);
	}*/

	/*public void testNewLogin(){
		
		log.info("rand ="+rand);
		loginWithNewUser(SYSTEM,"autoRand"+rand , "dog8code");
	}*/

	public void testRegisterSystemWithRHN02(){
		
		task_TestPrep.registerSystem(SYSTEM,RhnReg_basic, true, true);
	}

	public void testUnregisterSystem(){
		
		task_TestPrep.unregisterAllProfilesOfSystem(SYSTEM, true);
	}
	public void testRegisterSystemWithRHN03(){
		
		task_TestPrep.registerSystem(SYSTEM,RhnReg_basic01, false, true);
	}

//	public void testUnregisterGUISystem(){
//		
//		task_TestPrep.unregisterAllProfilesOfSystemWithWebQA("AUTO_GUI_TEST_01");
//	}
//
//	public void testRhnRegisterGUI(){
//		
//		//runRhnRegisterGUI("http://xmlrpc.rhn.webqa.redhat.com/XMLRPC","whayutin","redhat","AUTO_GUI_TEST_01");
//		runRhnRegisterGUI(SERVER_REG,USER,PASSWORD,"AUTO_GUI_TEST_01");
//	}
//
//	public void testCheckGUIRegistration(){
//		
//		checkRegisteredSystem("AUTO_GUI_TEST_01", true);
//	}




}
