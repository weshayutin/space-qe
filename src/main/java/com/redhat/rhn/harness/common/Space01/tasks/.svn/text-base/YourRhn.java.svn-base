package com.redhat.rhn.harness.common.Space01.tasks;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.baseInterface.IYourRhn;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.thoughtworks.selenium.SeleniumException;

public class YourRhn  extends com.redhat.rhn.harness.common.Sat51.tasks.YourRhn implements IYourRhn {

	
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
		String link="Overview";
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
		Assert.assertTrue(rh.isTextPresent("Page Not Found"));

	}
	
	public int verifyEntitlementCounts(boolean openAndLogin, int entitlement,
			boolean consumed) {
		String count = null;
		String entitlementName = null;
		if (openAndLogin)
			task_RhnBase.OpenAndLogIn();
		page_YourRhn.clickLink_SystemEntitlements();
		switch (entitlement) {
		case IRhnBase.ENTITLEMENT_BASE:
			entitlementName = "System Base";
			if (consumed)
				count = page_YourRhn.getBaseEntitlement_Mang_Consumed();
			else
				count = page_YourRhn.getBaseEntitlement_Mang_Total();
			break;

		case IRhnBase.ENTITLEMENT_PROVISIONING:
			entitlementName = "System Provisioning";
			if (consumed)
				count = page_YourRhn.getBaseEntitlement_Provisioning_Consumed();
			else
				count = page_YourRhn.getBaseEntitlement_Provisioning_Total();
			break;

		default:
			log.info("Invalid Selection Type");
			/*
			 * if(count == null) throw new Exception("Entitlement Selection was
			 * invalid");
			 */
			break;

		}

		// s.replace("systems", "");
		count = count.replaceAll("total", "");
		count = count.replaceAll("systems", "");
		count = count.replaceAll("system", "");
		log.info(entitlementName + " Entitlements found = " + count);
		int i = Integer.valueOf(count.trim());
		return i;
	}
	
	public boolean checkForSharedChannel(String channel, String org){
		boolean found=false;
		page_YourRhn.open();
		page_YourRhn.clickLink_OrganizationalTrusts();
		if(rh.isTextPresent("No Organizations.")){
			return false;
		}
		page_RhnCommon.setTxt_FilterBy(org);
		page_RhnCommon.clickButton_Filter_Go();
		rh.clickLink("link="+org,true);
		page_YourRhn.clickLink_OrganizationalTrusts_Channels_Provided();
		//page_RhnCommon.setTxt_FilterBy(channel);
		//page_RhnCommon.clickButton_Filter_Go(); //currently busted.. bug open
		if((rh.isTextPresent(channel))){
			//rh.clickLink(channel, true); THIS FUNCTION WAS REMOVED
			found = true;
		}
		else{
			found = false;
		}
		
		
		
		return found;
	}
	
	


}
