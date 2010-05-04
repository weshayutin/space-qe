package com.redhat.rhn.harness.hosted;

import org.testng.Assert;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Hosted.tasks.RhnBase;
import com.redhat.rhn.harness.common.Hosted.tasks.YourRhn;

public class T12RolesAndPermissions extends YourRhn{
	
	public static final String ORG_ADMIN_USERID = "aowensgit";
	public static final String ORG_ADMIN_PASSWORD = "redhat";
	
	public static final String CHANNEL_ADMIN_USERID = "jmcdonaggit";
	public static final String CHANNEL_ADMIN_PASSWORD = "redhat";
	
	public static final String SYSTEM_ADMIN_USERID = "aowens071130";
	public static final String SYSTEM_ADMIN_PASSWORD = "redhat";
	
	public static final String NORMAL_USER_USERID = "fmerendagit";
	public static final String NORMAL_USER_PASSWORD = "redhat";
	
	public static final String CONFIG_ADMIN_USERID = "jcantongit";
	public static final String CONFIG_ADMIN_PASSWORD = "redhat";
	
	RhnHelper rh = new RhnHelper();
	RhnBase rb = new RhnBase();
	
	public void test01OrgAdmin01(){
		
		rb.OpenAndLogIn(ORG_ADMIN_USERID, ORG_ADMIN_PASSWORD);
		Assert.assertTrue(rh.isElementPresent("link=Users", true));  //Check if User Menu is listed
		Assert.assertTrue(rh.isElementPresent("link=Configuration", true));  //Check if Configuration Menu is listed
	}
	
	public void test01OrgAdmin02(){
		
		rb.OpenAndLogIn(ORG_ADMIN_USERID, ORG_ADMIN_PASSWORD);
		Assert.assertTrue(rh.isElementPresent("link=Systems", true));  //Check if Systems Menu is listed
		rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/systems/Overview.do", true, "Systems"); //open systems overview page
		if (rh.isElementPresent("xpath=//html/body/div/div[5]/table/tbody/tr/td/div/ul", true)){
			log.info("The System overview left side menu is listed on the Systems Overview page");
		}
		if (rh.isElementPresent("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]", true)) {
			log.info("The System Details are listed on the Systems Overview page");
		}
	}
	
	public void test02ChannelAdmin01(){
		
		rb.OpenAndLogIn(CHANNEL_ADMIN_USERID, CHANNEL_ADMIN_PASSWORD);
		Assert.assertFalse(rh.isElementPresent("link=User", true));  //User Menu shoud not listed
		Assert.assertFalse(rh.isElementPresent("link=Configuration", true));  //Configuration Menu shoud not listed
	}
	
	public void test02ChannelAdmin02(){
		
		rb.OpenAndLogIn(CHANNEL_ADMIN_USERID, CHANNEL_ADMIN_PASSWORD);
		Assert.assertTrue(rh.isElementPresent("link=Channels", true));  //Check if Channels Menu is listed
		rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/software/channels/All.do", true, "Channels");
		if (rh.isElementPresent("xpath=//html/body/div/div[5]/table/tbody/tr/td/div/ul", true)){
			log.info("The Channels left side menu is listed on the Channels page");
		}
		if (rh.isElementPresent("xpath=//html/body/div/div[5]/table/tbody/tr/td/div/ul/li[7]", true)) {
			log.info("Manage Software Channels is listed on the Channels page");
		}
	}
	
	public void test03SystemAdmin01(){
		
		rb.OpenAndLogIn(SYSTEM_ADMIN_USERID, SYSTEM_ADMIN_PASSWORD);
		Assert.assertFalse(rh.isElementPresent("link=User", true));  //Check if User Menu is listed
		Assert.assertTrue(rh.isElementPresent("link=Channels", true));  //Channels Menu should be listed
		rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/software/channels/All.do", true, "Channels"); //open Channels page
		Assert.assertFalse(rh.isTextPresent("Manage Software Channels"));  //Check if "Manage Software Channels" link is listed
	}
	
	public void test03SystemAdmin02(){
		int numberItems;
		
		rb.OpenAndLogIn(SYSTEM_ADMIN_USERID, SYSTEM_ADMIN_PASSWORD);

		/*rb.goToSystem("jcr-test");
		rb.goToSystem("dhcp77-209.rhndev.redhat.com");
		rb.goToSystem("rbgh-host"); */
		
		
		numberItems=rb.totalySystemsInList();
		log.info("The number of systems =" +numberItems);

		openGroupsPage();
		Assert.assertTrue(rh.isElementPresent("link=bowes' group", true));  //Check if bowes' group link is listed
	}
	
	public void test04NormaUserl01(){
		
		rb.OpenAndLogIn(NORMAL_USER_USERID, NORMAL_USER_PASSWORD);
		Assert.assertFalse(rh.isElementPresent("link=User", true));  //User Menu should not listed
		Assert.assertFalse(rh.isElementPresent("link=Configuration", true));  //Configuration Menu should not listed
		Assert.assertTrue(rh.isElementPresent("link=Systems", true));  //Check if Systems Menu is listed
		rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/systems/Overview.do", true, "Systems"); //open Systems page
		Assert.assertTrue(rh.isTextPresent("No systems"));  //Check if "No systems" text is listed
	}
		
	public void test05ConfigAdmin01(){
		
		rb.OpenAndLogIn(CONFIG_ADMIN_USERID, CONFIG_ADMIN_PASSWORD);
		Assert.assertFalse(rh.isElementPresent("link=User", true));  //User Menu should not be listed
		Assert.assertTrue(rh.isElementPresent("link=Configuration", true));  //Configuration Menu should be listed
		Assert.assertTrue(rh.isElementPresent("link=Channels", true));  //Channels Menu should be listed
		rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/software/channels/All.do", true, "Channels"); //open Channels page
		Assert.assertFalse(rh.isTextPresent("Manage Software Channels"));  //Check if "Manage Software Channels" link is listed
	}

}