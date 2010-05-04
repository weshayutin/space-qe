package com.redhat.rhn.harness.satellite42;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.redhat.rhn.harness.common.Sat42.tasks.RhnBase;

@Test(groups="tests")
public class Authentication extends SeleniumSetup{

	protected RhnHelper rh = new RhnHelper();
	public static String RhnReg_basic01 = "rhnreg_ks --username="+IRhnBase.USER+ "  --password="+ IRhnBase.PASSWORD;
	public static String SERVER_REG = "http://"+IRhnBase.SERVER_HOSTNAME + "/XMLRPC";

	protected String longString = "asasdfasdfaasasdfasdfaasasdfasdfaasasdfasdfaasasdfasdfa" +
						"asasdfasdfaasasdfasdfaasasdfasdfaasasdfasdfaasasdfasdfaasasdfasdfa" +
						"asasdfasdfaasasdfasdfaasasdfasdfaasasdfasdfaasasdfasdfaasasdfasdfa" +
						"asasdfasdfaasasdfasdfaasasdfasdfaasasdfasdfaasasdfasdfaasasdfasdfa" +
						"asasdfasdfaasasdfasdfaasasdfasdfaasasdfasdfaasasdfasdfaasasdfasdfa" +
						"asasdfasdfaasasdfasdfaasasdfasdfaasasdfasdfaasasdfasdfaasasdfasdfa";

	protected String longNumber = "1456789873478765446437876543345676543677654566545"+
					"1456789873478765446437876543345676543677654566545"+
					"1456789873478765446437876543345676543677654566545"+
					"1456789873478765446437876543345676543677654566545"+
					"1456789873478765446437876543345676543677654566545"+
					"1456789873478765446437876543345676543677654566545";

	protected String longSpecial = "@#$%^&*()(*%^$%^&*($%^&*($%^&*($%^&*$%^&*$%^&*$%^&*$%"+
						"@#$%^&*()(*%^$%^&*($%^&*($%^&*($%^&*$%^&*$%^&*$%^&*$%"+
						"@#$%^&*()(*%^$%^&*($%^&*($%^&*($%^&*$%^&*$%^&*$%^&*$%"+
						"@#$%^&*()(*%^$%^&*($%^&*($%^&*($%^&*$%^&*$%^&*$%^&*$%"+
						"@#$%^&*()(*%^$%^&*($%^&*($%^&*($%^&*$%^&*$%^&*$%^&*$%"+
						"@#$%^&*()(*%^$%^&*($%^&*($%^&*($%^&*$%^&*$%^&*$%^&*$%"+
						"@#$%^&*()(*%^$%^&*($%^&*($%^&*($%^&*$%^&*$%^&*$%^&*$%";


	@Test(groups = { "testplan-Authentication-Satellite" })
	public void test01_badUserName(){
		
		task_Authentication.loginIncorrect("asdf", "asdf");
	}

	@Test(groups = { "testplan-Authentication-Satellite" })
	public void test02_badPassword(){
		
		task_Authentication.loginIncorrect(IRhnBase.USER, "asdf");
	}

	@Test(groups = { "testplan-Authentication-Satellite" })
	public void test03_loginSuccessful(){
		
		task_RhnBase.OpenAndLogIn(IRhnBase.USER, IRhnBase.PASSWORD);
		Assert.assertTrue(rh.isElementPresent("link=Sign Out", true));
	}

	@Test(groups = { "testplan-Authentication-Satellite" })
	public void test04_LongPassword_negative(){
		
		task_Authentication.loginIncorrect(IRhnBase.USER, longString);
	}

	@Test(groups = { "testplan-Authentication-Satellite" })
	public void test05_LongUserAndPassword_negative(){
		
		task_Authentication.loginIncorrect(longString,longString);
	}

	@Test(groups = { "testplan-Authentication-Satellite" })
	public void test04_LongPassword_negative02(){
		
		task_Authentication.loginIncorrect(IRhnBase.USER, longNumber);
	}

	@Test(groups = { "testplan-Authentication-Satellite" })
	public void test05_LongUserAndPassword_negative02(){
		
		task_Authentication.loginIncorrect(longNumber,longNumber);
	}

	@Test(groups = { "testplan-Authentication-Satellite" })
	public void test04_LongPassword_negative03(){
		
		task_Authentication.loginIncorrect(IRhnBase.USER, longNumber);
	}

	@Test(groups = { "testplan-Authentication-Satellite" })
	public void test05_LongUserAndPassword_negative03(){
		
		task_Authentication.loginIncorrect(longSpecial,longSpecial);
	}

	@Test(groups = { "testplan-Authentication-Satellite" })
	public void test06_LongUserAndPassword_negative03(){
		
		task_Authentication.loginIncorrect(longString+longSpecial+longNumber+
				longString+longSpecial+longNumber,

				longString+longSpecial+longNumber+
				longString+longSpecial+longNumber);
	}
	
	@Test(groups = { "testplan-Authentication-Satellite" })
	public void test09_LinksForNonAdmins_checkACLS(){	
		task_YourRhn.checkLinksForNonAdminUsers(true,"autoFirst","autoLast","auto","334 deadwood drive","Raleigh","NC","27615","919 555 5555");
	}

/*	public void test07getUrls(){
		
		getURLStrings(IRhnBase.SERVER_HOSTNAME);
	}

	public void test08getUrls(){
		
		getBasicUserURL(IRhnBase.SERVER_HOSTNAME);
	}*/












}
