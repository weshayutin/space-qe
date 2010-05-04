package com.redhat.rhn.harness.common.Sat42.tasks;

import java.util.Random;

import org.testng.Assert;

import com.redhat.rhn.harness.Sat42.pages.ConfigurationPage;
import com.redhat.rhn.harness.Sat42.pages.RhnCommon;
import com.redhat.rhn.harness.Sat42.pages.SatelliteSystemsPage;
import com.redhat.rhn.harness.Sat42.pages.UsersPage;
import com.redhat.rhn.harness.Sat42.pages.YourRhnPage;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.baseInterface.IYourRhn;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;

/**
 * Older tasks, not used really
 * 
 * @author whayutin
 * 
 */
public class YourRhn extends SeleniumSetup { //implements IYourRhn {

	protected RhnHelper rh = new RhnHelper();
	
	
	
	
	


	protected static String NonAdminUser;
	protected static String PASSWORD = HarnessConfiguration.RHN_PASS;

	String[] portlets = new String[6];
	Object[] portletCheckBox = new Object[6];

	public boolean openUsersPage() {
		page_Users.open();
		// rh.isTextPresent("Active Users");
		return rh.isTextPresent("Active Users");
	}

	public void openGroupsPage() {
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_SystemGroups();
	}

	public void RhnAcceptance() {
		portlets[0] = "Tasks";
		portlets[1] = "Most Critical Systems";
		portlets[2] = "System Groups";
		portlets[3] = "Relevant Security Errata";
		portlets[4] = "Inactive Systems";
		portlets[5] = "Recently Scheduled Actions";
		

		/*
		 * portletCheckBox[0] = "Tasks"; portletCheckBox[1] = "Most Critical
		 * Systems"; portletCheckBox[2] = "System Groups"; portletCheckBox[3] =
		 * "Relevant Security Errata"; portletCheckBox[4] = "Inactive Systems";
		 * portletCheckBox[5] = "Recently Scheduled Actions"; portletCheckBox[6] =
		 * "Recently Registered Systems";
		 */

		task_RhnBase.OpenAndLogIn();
		page_YourRhn.clickLink_YourPreferences();
		page_YourRhn.checkBox_Tasks(false);
		page_YourRhn.checkBox_MostCriticalSystems(false);
		page_YourRhn.checkBox_SystemGroups(false);
		page_YourRhn.checkBox_RelevantSecurityErrata(false);
		page_YourRhn.checkBox_InactiveSystems(false);
		page_YourRhn.checkBox_RecentlyScheduledActions(false);


		page_YourRhn.clickButton_SavePreferences();
		Assert.assertTrue(rh.isTextPresent("Preferences modified"));
		page_YourRhn.open();

		for (int i = 0; i < portlets.length; i++) {
			Assert.assertTrue(rh.isTextNotPresent(portlets[i]));
		}

		page_YourRhn.open();
		page_YourRhn.clickLink_YourPreferences();
		page_YourRhn.checkBox_Tasks(true);
		page_YourRhn.checkBox_MostCriticalSystems(true);
		page_YourRhn.checkBox_SystemGroups(true);
		page_YourRhn.checkBox_RelevantSecurityErrata(true);
		page_YourRhn.checkBox_InactiveSystems(true);
		page_YourRhn.checkBox_RecentlyScheduledActions(true);
	

		page_YourRhn.clickButton_SavePreferences();
		Assert.assertTrue(rh.isTextPresent("Preferences modified"));
		page_YourRhn.open();

		for (int i = 0; i < portlets.length; i++) {
			Assert.assertTrue(rh.isTextPresent(portlets[i]));
		}

	}

	public void updateYourAccount_Position(String position) {
		task_RhnBase.OpenAndLogIn();
		page_YourRhn.clickLink_YourAccount();
		page_YourRhn.setTxt_Position(position);
		page_YourRhn.clickButton_Update();
		Assert.assertTrue(rh.isTextPresent("User information updated"));
	}

	public void populateYourAccountAddress(String address, String phone,
			String city, String state, String zip) {
		// neccessary for sat
		String[] addressInfo = new String[5];
		addressInfo[0] = address;
		addressInfo[1] = phone;
		addressInfo[2] = city;
		addressInfo[3] = state;
		addressInfo[4] = zip;

		task_RhnBase.OpenAndLogIn();
		page_YourRhn.clickLink_YourAccount();
		page_YourRhn.clickLink_YourAddress();
		page_YourRhn.clickLink_EditThisAddress();
		Assert
				.assertTrue(rh
						.isTextPresent("Please enter your information in the form provided below. Entries marked with an asterisk (*) are required."));

		page_YourRhn.setTxt_Address(address);
		page_YourRhn.setTxt_Phone(phone);
		page_YourRhn.setTxt_City(city);
		page_YourRhn.setTxt_State(state);
		page_YourRhn.setTxt_Zip(zip);

		page_YourRhn.clickButton_Update();

		Assert.assertTrue(rh.isTextPresent("Address changed"));

	}

	public void updateYourAccountAddress(String address, String phone,
			String city, String state, String zip) {
		String[] addressInfo = new String[5];
		addressInfo[0] = address;
		addressInfo[1] = phone;
		addressInfo[2] = city;
		addressInfo[3] = state;
		addressInfo[4] = zip;

		task_RhnBase.OpenAndLogIn();
		page_YourRhn.clickLink_YourAccount();
		page_YourRhn.clickLink_YourAddress();
		page_YourRhn.clickLink_EditThisAddress();
		Assert
				.assertTrue(rh
						.isTextPresent("Please enter your information in the form provided below. Entries marked with an asterisk (*) are required."));

		page_YourRhn.setTxt_Address("");
		page_YourRhn.clickButton_Update();
		Assert.assertTrue(rh.isTextPresent("Street Address is required."));
		page_YourRhn.setTxt_Address(address);
		page_YourRhn.clickButton_Update();
		Assert.assertTrue(rh.isTextPresent("Address changed"));
		page_YourRhn.clickLink_EditThisAddress();

		page_YourRhn.setTxt_Phone("");
		page_YourRhn.clickButton_Update();
		Assert.assertTrue(rh.isTextPresent("Phone is required."));
		page_YourRhn.setTxt_Phone(phone);
		page_YourRhn.clickButton_Update();
		Assert.assertTrue(rh.isTextPresent("Address changed"));
		page_YourRhn.clickLink_EditThisAddress();

		page_YourRhn.setTxt_City("");
		page_YourRhn.clickButton_Update();
		Assert.assertTrue(rh.isTextPresent("City is required."));
		page_YourRhn.setTxt_City(city);
		page_YourRhn.clickButton_Update();
		Assert.assertTrue(rh.isTextPresent("Address changed"));
		page_YourRhn.clickLink_EditThisAddress();

		page_YourRhn.setTxt_State("");
		page_YourRhn.clickButton_Update();
		Assert.assertTrue(rh.isTextPresent("State/Province is required."));
		page_YourRhn.setTxt_State(state);
		page_YourRhn.clickButton_Update();
		Assert.assertTrue(rh.isTextPresent("Address changed"));
		page_YourRhn.clickLink_EditThisAddress();

		page_YourRhn.setTxt_Zip("");
		page_YourRhn.clickButton_Update();
		Assert.assertTrue(rh.isTextPresent("Zip / Postal Code is required."));
		page_YourRhn.setTxt_Zip(zip);
		page_YourRhn.clickButton_Update();
		Assert.assertTrue(rh.isTextPresent("Address changed"));
		page_YourRhn.clickLink_EditThisAddress();

	}

	public void updateYourAccountHosted(String position, String firstname,
			String lastname) {
		String[] addressInfo = new String[5];

		task_RhnBase.OpenAndLogIn();
		page_YourRhn.clickLink_YourAccount();
		Assert
				.assertTrue(rh
						.isTextPresent("Please enter your information in the form provided below. Entries marked with an asterisk (*) are required."));

		page_YourRhn.setTxt_FirstName("");
		page_YourRhn.clickButton_Update();
		Assert.assertTrue(rh.isTextPresent("First Name is required."));

		page_YourRhn.setTxt_LastName("");
		page_YourRhn.clickButton_Update();
		Assert.assertTrue(rh.isTextPresent("Last Name is required."));

		page_YourRhn.setTxt_FirstName(firstname);
		page_YourRhn.setTxt_LastName(lastname);
		page_YourRhn.setTxt_Position(position);
		page_YourRhn.clickButton_Update();

		Assert.assertTrue(rh.isTextPresent("User information updated"));

	}

	public void updateEmailAddress(String email) {
		int n = 100;
		Random generator = new Random();
		int rand = generator.nextInt(n);
		log.info("rand =" + rand);

		task_RhnBase.OpenAndLogIn();
		page_YourRhn.clickLink_YourAccount();
		page_YourRhn.clickLink_ChangeEmail();
		page_YourRhn.setTxt_Email("");
		page_YourRhn.clickButton_Update();
		Assert.assertTrue(rh.isTextPresent("is not valid"));

		page_YourRhn.setTxt_Email("asdf.com");
		page_YourRhn.clickButton_Update();
		Assert.assertTrue(rh
				.isTextPresent("is not valid"));

		page_YourRhn.setTxt_Email(rand + email);
		page_YourRhn.clickButton_Update();
		Assert.assertTrue(rh.isTextPresent("Email Address Updated"));
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

	public void checkEntitlements(String system, boolean satellite) {
		task_RhnBase.OpenAndLogIn();
		page_YourRhn.clickLink_SystemEntitlements();

		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInList();
		page_YourRhn.clickButton_SetToManagementEntitled();
		Assert.assertTrue(rh.isTextPresent("1 system(s) set to Management."));
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInList();
		page_YourRhn.clickButton_Unentitle();
		Assert.assertTrue(rh.isTextPresent("1 system(s) Unentitled."));

		if (!satellite) {
			page_RhnCommon.setTxt_FilterBy(system);
			page_RhnCommon.clickButton_Filter_Go();
			page_RhnCommon.check_FirstItemInList();
			page_YourRhn.clickButton_SetToUpdateEntitled();
			Assert.assertTrue(rh.isTextPresent("1 system(s) set to Update."));
		}

		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInList();
		page_YourRhn.clickButton_SetToManagementEntitled();
		Assert.assertTrue(rh.isTextPresent("1 system(s) set to Management."));

		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInList();
		page_YourRhn.select_AddOnEntitlement("Provisioning");
		page_YourRhn.clickButton_RemoveEntitlement();
		Assert.assertTrue(rh.isTextPresent("Removed Provisioning"));

		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInList();
		page_YourRhn.select_AddOnEntitlement("Provisioning");
		page_YourRhn.clickButton_AddEntitlement();
		Assert.assertTrue(rh.isTextPresent("1 system(s) set to Provisioning."));
	}

	public void checkChannelEntitlementsLink(boolean satellite) {
		task_RhnBase.OpenAndLogIn();
		page_YourRhn.clickLink_SoftwareChannelEntitlements();
		Assert.assertTrue(rh.isTextPresent("The software channels listed below are subscription-based channels to which your organization has paid access."));

		if (!satellite) {
			page_YourRhn.clickLink_PurchaseOrRenew();
			Assert.assertTrue(rh
					.isTextPresent("Renew Subscriptions & Entitlements"));
			Assert.assertTrue(rh
					.isTextPresent("Purchase Additional Subscriptions"));
			page_YourRhn.clickLink_ExpirationDatesAndPurchaseHistory();
			Assert.assertTrue(rh.isTextPresent("View Your Purchase History"));
		}
	}

	/**
	 * 
	 * 
	 * @param satellite
	 * @param firstName
	 * @param lastName
	 * @param emailLast
	 *            TODO
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 * @param phone
	 * @param email
	 *            only supply the first part of the email..
	 * @return the newly created non admin user
	 */
	public String createNonAdminUser(boolean satellite, String firstName,
			String lastName, String emailFirst, String emailLast,
			String address, String city, String state, String zip, String phone) {
		int n = 1000;
		Random generator = new Random();
		int rand = generator.nextInt(n);
		log.info("rand =" + rand);
		NonAdminUser = HarnessConfiguration.RHN_USER_NONADMIN + rand;
		log.info("User =" + NonAdminUser);
		// rc.OpenAndLogIn();
		page_RhnCommon.clickUsers();
		page_RhnCommon.setTxt_FilterByUserName(NonAdminUser);
		page_RhnCommon.clickButton_Filter_Go();
		if (rh.isTextPresent(NonAdminUser))
			log.info("User already exists, test will fail");

		page_Users.clickLink_CreateNewUser();
		page_Users.setTxt_DesiredLogin(NonAdminUser);
		page_Users.setTxt_DesiredPassword(PASSWORD);
		page_Users.setTxt_ConfirmPassword(PASSWORD);

		page_Users.setTxt_FirstName(firstName + rand);
		page_Users.setTxt_LastName(lastName + rand);
		page_Users.setTxt_Email(emailFirst + rand + "@" + emailLast);
		if (!satellite) {
			page_Users.setTxt_Address(address);
			page_Users.setTxt_City(city);
			page_Users.setTxt_State(state);
			page_Users.setTxt_Zip(zip);
			page_Users.setTxt_Phone(phone);
		}
		page_Users.clickButton_CreateLogin();
		// if(rh.isTextPresent("Service Error"));

		return NonAdminUser;
		// some common errors to check for
		/*
		 * Phone is required. Desired Password is required. Desired Login
		 * contains invalid characters. In addition to alphanumeric characters,
		 * '-', '_', '.', and '@' are allowed. Please try again. Confirm
		 * Password is required. Street Address is required. Last Name is
		 * required. First Name is required. Email is required. State/Province
		 * is required. Zip / Postal Code is required. City is required.
		 */
	}

	public void createUserCustom(String login, String firstName,
			String lastName, String emailFirst, String emailLast) {
		int n = 1000;
		Random generator = new Random();
		int rand = generator.nextInt(n);
		log.info("rand =" + rand);

		log.info("User =" + login);
		// rc.OpenAndLogIn();
		page_RhnCommon.clickUsers();
		page_RhnCommon.setTxt_FilterByUserName(login);
		page_RhnCommon.clickButton_Filter_Go();
		if (rh.isTextPresent(login)) {
			rh.clickLink("link=" + login, true);
			page_Users.clickLink_DeleteUser();
			page_Users.clickButton_DeleteUser();

		}
		page_Users.clickLink_CreateNewUser();
		page_Users.setTxt_DesiredLogin(login);
		page_Users.setTxt_DesiredPassword(PASSWORD);
		page_Users.setTxt_ConfirmPassword(PASSWORD);

		page_Users.setTxt_FirstName(firstName + rand);
		page_Users.setTxt_LastName(lastName + rand);
		page_Users.setTxt_Email(emailFirst + rand + "@" + emailLast);

		page_Users.clickButton_CreateLogin();
		Assert.assertTrue(rh.isTextPresent("Account " + login
				+ " created, login information sent to"));
	}

	public void negativeCreateUserPage() {
		task_RhnBase.OpenAndLogIn();
		page_RhnCommon.clickUsers();
		page_Users.clickLink_CreateNewUser();
		page_Users.clickButton_CreateLogin();
		Assert.assertTrue(rh.isTextPresent("Phone is required."));
		Assert.assertTrue(rh.isTextPresent("Desired Password is required."));
		Assert
				.assertTrue(rh
						.isTextPresent("Desired Login contains invalid characters. In addition to alphanumeric characters, '-', '_', '.', and '@' are allowed. Please try again."));
		Assert.assertTrue(rh.isTextPresent("Confirm Password is required."));
		Assert.assertTrue(rh.isTextPresent("Street Address is required."));
		Assert.assertTrue(rh.isTextPresent("Last Name is required."));
		Assert.assertTrue(rh.isTextPresent("First Name is required."));
		Assert.assertTrue(rh.isTextPresent("Email is required."));
		Assert.assertTrue(rh.isTextPresent("State/Province is required."));
		Assert.assertTrue(rh.isTextPresent("Zip / Postal Code is required."));
		Assert.assertTrue(rh.isTextPresent("City is required."));

	}

	public void checkLinksForNonAdminUsers(boolean satellite,
			String firstName, String lastName, String email, String address,
			String city, String state, String zip, String phone) {

		int n = 1000;
		Random generator = new Random();
		int rand = generator.nextInt(n);
		//log.info("rand =" + rand);
		NonAdminUser = HarnessConfiguration.RHN_USER_NONADMIN + rand;
		//log.info("User =" + NonAdminUser);
		task_RhnBase.OpenAndLogIn();
		createNonAdminUser(satellite, firstName, lastName, email, "redhat.com",
				address, city, state, zip, phone);

		String link01 = "Subscription Management";
		String link02 = "Users";
		String link = "Your RHN";
		page_RhnCommon.clickSignOut();
		page_RhnCommon.LogIn(NonAdminUser, HarnessConfiguration.RHN_PASS);

		if (!satellite) {
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

		if (rh.isTextPresent("Please Correct Account Information")) {
			//rh.screenShot("hosted", "accountError.png");
			throw new SeleniumException(
					"Account Information was incorrect please fix");
			// ssp.clickButton_Next();
		}
		Assert.assertTrue(rh.isTextPresent(link));
		Assert.assertTrue(rh.isTextNotPresent(link01));
		Assert.assertTrue(rh.isTextNotPresent(link02));
		page_RhnCommon.clickSystems();

		rh.openLink(HarnessConfiguration.RHN_HOST
				+ "/rhn/channels/software/Entitlements.do", true,
				"Entitlements");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST
				+ "/rhn/systems/SystemEntitlements.do", true,
				"System Entitlements");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST
				+ "/rhn/systems/SystemEntitlements.do", true,
				"System Entitlements");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST
				+ "/rhn/channels/software/EntitlementsSubmit.do", true,
				"Probe List");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST
				+ "/rhn/configuration/ChannelCreate.do", true, "Probe Suites");
		Assert.assertTrue(rh.isTextPresent("Page Not Found"));

		/*
		 * rh.openLink(HarnessConfiguration.RHN_HOST+"/rhn/errata/details/ErrataConfirm.do",
		 * true, "Notification Methods");
		 * Assert.assertTrue(rh.isTextPresent("Permission Error."));
		 */

		rh.openLink(HarnessConfiguration.RHN_HOST
				+ "/rhn/kickstart/KickstartDetailsEdit.do", true,
				"Monitoring Config");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST
				+ "/rhn/admin/config/GeneralConfig.do", true, "General Config");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST
				+ "/rhn/account/PurchaseHistory.do", true, "Purchase History");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

	}

	public void checkLinksForAdminUsersOnSatellite() {

		task_RhnBase.OpenAndLogIn();

		rh.openLink(HarnessConfiguration.RHN_HOST
				+ "/rhn/channels/software/Entitlements.do", true,
		"Entitlements");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST
				+ "/rhn/systems/SystemEntitlements.do", true,
		"System Entitlements");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST
				+ "/rhn/systems/SystemEntitlements.do", true,
		"System Entitlements");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST
				+ "/rhn/monitoring/ProbeList.do", true, "Probe List");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST
				+ "/rhn/monitoring/config/ProbeSuites.do", true,
		"Probe Suites");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST
				+ "/rhn/monitoring/config/notification/Methods.do", true,
		"Notification Methods");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST
				+ "/rhn/admin/config/MonitoringConfig.do", true,
		"Monitoring Config");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST
				+ "/rhn/admin/config/GeneralConfig.do", true, "General Config");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

		rh.openLink(HarnessConfiguration.RHN_HOST
				+ "/rhn/account/PurchaseHistory.do", true, "Purchase History");
		Assert.assertTrue(rh.isTextPresent("Permission Error."));

	}

	public boolean checkForSharedChannel(String channel, String org){
		//this is just a place holder
		throw new SeleniumException("not a 42 method");
	}

}
