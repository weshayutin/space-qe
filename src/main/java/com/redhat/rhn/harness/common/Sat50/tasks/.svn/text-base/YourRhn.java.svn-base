package com.redhat.rhn.harness.common.Sat50.tasks;

import java.util.Random;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IYourRhn;
import com.redhat.rhn.harness.common.HarnessConfiguration;

public class YourRhn  extends com.redhat.rhn.harness.common.Sat42.tasks.YourRhn implements IYourRhn {


	

	String[] portlets = new String[7];
	Object[] portletCheckBox = new Object[7];


	public void RhnAcceptance() {
		portlets[0] = "Tasks";
		portlets[1] = "Most Critical Systems";
		portlets[2] = "System Groups";
		portlets[3] = "Relevant Security Errata";
		portlets[4] = "Inactive Systems";
		portlets[5] = "Recently Scheduled Actions";
		portlets[6] = "Recently Registered Systems";
		

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
		page_YourRhn.checkBox_RecentlyRegisteredSystems(false);


		page_YourRhn.clickButton_SavePreferences();
		Assert.assertTrue(rh.isTextPresent("Preferences modified"));
		page_YourRhn.open();

		for (int i = 0; i < portlets.length; i++) {
			log.fine(portlets[i]+ " should not be present");
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
		page_YourRhn.checkBox_RecentlyRegisteredSystems(true);
	

		page_YourRhn.clickButton_SavePreferences();
		Assert.assertTrue(rh.isTextPresent("Preferences modified"));
		page_YourRhn.open();
		rh.sleepForSeconds(3);
		for (int i = 0; i < portlets.length; i++) {
			log.fine(portlets[i]+ " should be present");
			Assert.assertTrue(rh.isTextPresent(portlets[i]));
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
		//rc.setTxt_FilterByUserName(NonAdminUser);
		page_RhnCommon.setTxt_FilterBy(NonAdminUser);
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
	
	
	public void updateYourAccountAddress(String address, String phone, String city, String state, String zip){
		String[] addressInfo = new String[5];
		addressInfo[0]=address;
		addressInfo[1]=phone;
		addressInfo[2]=city;
		addressInfo[3]=state;
		addressInfo[4]=zip;

		task_RhnBase.OpenAndLogIn();
		page_YourRhn.clickLink_YourAccount();
		page_YourRhn.clickLink_YourAddress();
		page_YourRhn.clickLink_EditThisAddress();
		Assert.assertTrue(rh.isTextPresent("Please enter your information in the form provided below. Entries marked with an asterisk (*) are required."));

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



}
