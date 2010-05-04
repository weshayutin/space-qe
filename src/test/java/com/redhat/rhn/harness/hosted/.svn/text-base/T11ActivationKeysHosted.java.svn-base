package com.redhat.rhn.harness.hosted;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Hosted.tasks.ActivationKeys;
import com.redhat.rhn.harness.common.Hosted.tasks.RhnBase;

public class T11ActivationKeysHosted extends ActivationKeys{

	RhnHelper rh = new RhnHelper();
	RhnBase rb1 = new RhnBase();
	
	public static String RhnReg_basic01 = "rhnreg_ks --IRhnBase.USERname="+IRhnBase.USER+ "  --IRhnBase.PASSWORD="+ IRhnBase.PASSWORD;
	public static String SERVER_REG = "http://"+HarnessConfiguration.RHN_HOST + "/XMLRPC";




	public void test01Prep01(){
		
		deleteActivationKey("auto", true);
	}

	public void test02Prep02(){
		
		rb1.modifyServerParent(IRhnBase.SYSTEM_HOSTNAME01, true, "serverURL="+SERVER_REG);
	}

	public void test03CreateKey01(){
		
		rb1.OpenAndLogIn();
		createActivationKey("autotest01", "12345", "5", false, false, false, false, false);
	}

	public void test04DeleteKey(){
		
		deleteActivationKey("autotest01", true);
	}

	public void test05CreateKey02(){
		
		rb1.OpenAndLogIn();
		createActivationKey("autotest02", "12345", "5", false, false, false, false, false);
	}

	public void test06UpdateKey01(){
		
		addProvisioningToKey("autotest02");
	}

	public void test07CreateKey03(){
		
		rb1.OpenAndLogIn();
		createActivationKey("autotest03", "123456", "1", false, true, false, false, false);
	}

	public void test08ActivationKey01(){
		
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,"prof01","--activationkey=123456  --force",IRhnBase.USER, IRhnBase.PASSWORD,true, true);
		isSystemActivated(IRhnBase.SYSTEM_HOSTNAME01, "autotest03");
	}

	public void test09ActivationKey02(){
		
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,"prof02","--activationkey=123456  --force",IRhnBase.USER, IRhnBase.PASSWORD,false, true);
	}

	public void test10ActivationKey03(){
		
		rb1.OpenAndLogIn();
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.SYSTEM_HOSTNAME01," --force",IRhnBase.USER, IRhnBase.PASSWORD,true, false);
		rb1.removePackage(IRhnBase.SYSTEM_HOSTNAME01, "zsh");
		addPackageToKey("autotest03", "zsh");
		task_TestPrep.unregisterSystem("prof01", false);
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.SYSTEM_HOSTNAME01,"--activationkey=123456  --force",IRhnBase.USER, IRhnBase.PASSWORD,true, false);
		Assert.assertTrue(rb1.listPackage(IRhnBase.SYSTEM_HOSTNAME01, "zsh"));
	}

	public void test11ActivationKey04(){
		
		rb1.OpenAndLogIn();
		rb1.removePackage(IRhnBase.SYSTEM_HOSTNAME01, "zsh");
		addPackageToKey("autotest03", "zsh\nscreen\nhttpd\n");
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.SYSTEM_HOSTNAME01,"--activationkey=123456  --force",IRhnBase.USER, IRhnBase.PASSWORD,true, false);
		Assert.assertTrue(rb1.listPackage(IRhnBase.SYSTEM_HOSTNAME01, "zsh"));
		Assert.assertTrue(rb1.listPackage(IRhnBase.SYSTEM_HOSTNAME01, "screen"));
		Assert.assertTrue(rb1.listPackage(IRhnBase.SYSTEM_HOSTNAME01, "httpd"));
	}

	
	//add custom chanel to key.
}

