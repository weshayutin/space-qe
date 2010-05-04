package com.redhat.rhn.harness.common.Sat51.tasks;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IYourRhn;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.thoughtworks.selenium.SeleniumException;

public class YourRhn  extends com.redhat.rhn.harness.common.Sat50.tasks.YourRhn implements IYourRhn {



	String[] portlets = new String[7];
	Object[] portletCheckBox = new Object[7];
	
	

	public void checkLinksForNonAdminUsers(boolean satellite,String firstName, String lastName,String email, String address, String city,
			String state, String zip, String phone){

		/*int n = 1000;
		Random generator = new Random();
		int rand = generator.nextInt(n);
		log.info("rand ="+rand);*/
		//NonAdminUser =HarnessConfiguration.RHN_USER_NONADMIN+rand;

		task_RhnBase.OpenAndLogIn();
		NonAdminUser = createNonAdminUser(satellite, firstName, lastName, email, "redhat.com", address, city, state, zip, phone);
		log.info("User =" +NonAdminUser);

		String link01="Subscription Management";
		String link02="Users";
		String link="Your RHN";
		page_RhnCommon.clickSignOut();
		page_RhnCommon.LogIn(NonAdminUser,HarnessConfiguration.RHN_PASS);

		if(!satellite){
			Assert.assertTrue(rh.isTextPresent("Terms and Conditions"));
			Assert.assertTrue(rh.isTextPresent("Red Hat Network Site Terms"));
			Assert.assertTrue(rh.isTextPresent("Export Control Agreement"));
			page_Users.clickButton_Continue();
			Assert.assertTrue(rh.isTextPresent("Terms and Conditions"));
			Assert.assertTrue(rh.isTextPresent("Red Hat Network Site Terms"));
			Assert.assertTrue(rh.isTextPresent("Export Control Agreement"));
			page_Users.checkBox_ConsentToTerms(true);
			page_Users.clickButton_Continue();
			}

		if(rh.isTextPresent("Please Correct Account Information")){
			//rh.screenShot("hosted", "accountError.png");
			throw new SeleniumException("Account Information was incorrect please fix");
			//hsp.clickButton_Next();
		}
		Assert.assertTrue(rh.isTextPresent(link));
		Assert.assertTrue(rh.isTextNotPresent(link01));
		Assert.assertTrue(rh.isTextNotPresent(link02));
		page_RhnCommon.clickSystems();

		rh.openLink(HarnessConfiguration.RHN_HOST+"/rhn/channels/software/Entitlements.do", true, "Entitlements");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST+"/rhn/systems/SystemEntitlements.do", true, "System Entitlements");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST+"/rhn/systems/SystemEntitlements.do", true, "System Entitlements");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST+"/rhn/channels/software/EntitlementsSubmit.do", true, "Probe List");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST+"/rhn/configuration/ChannelCreate.do", true, "Probe Suites");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST+"/rhn/errata/details/ErrataConfirm.do", true, "Notification Methods");
		Assert.assertTrue(rh.isTextPresent("Page Request Error"));

		rh.openLink(HarnessConfiguration.RHN_HOST+"/rhn/kickstart/KickstartDetailsEdit.do", true, "Monitoring Config");
		Assert.assertTrue(rh.isTextPresent("Stop it."));

		rh.openLink(HarnessConfiguration.RHN_HOST+"/rhn/admin/config/GeneralConfig.do", true, "General Config");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST+"/rhn/account/PurchaseHistory.do", true, "Purchase History");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

	}
	
	


}
