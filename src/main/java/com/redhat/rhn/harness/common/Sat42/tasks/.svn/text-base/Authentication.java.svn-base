package com.redhat.rhn.harness.common.Sat42.tasks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.Assert;

import com.redhat.rhn.harness.Sat42.pages.RhnCommon;
import com.redhat.rhn.harness.baseInterface.IAuthentication;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

/**
 * Tasks used to negative test the rhn login page
 * @author whayutin
 *
 */
public class Authentication extends SeleniumSetup {//implements IAuthentication{

	
	
	protected RhnHelper rh = new RhnHelper();
	
	protected static String USER = HarnessConfiguration.RHN_USER;
	protected static String PASSWORD = HarnessConfiguration.RHN_PASS;
	protected String loginError = "Either the password or username is incorrect.";
	protected String resourceDir = "/var/tmp/rhn/src/main/resources/";

	public void loginIncorrect(String user, String password){
		task_RhnBase.OpenAndLogIn(user, password);
		Assert.assertTrue(rh.isTextPresent(loginError));
	}


	public void getURLStrings(String system){
		String filename = resourceDir+"sat01.out";
		String myUrl = "";
		int fileSize = 0;
		task_TestPrep.getSatelliteUrls(system, true);
		fileSize = rh.getFileSize(filename);
		log.info("File Size = " + fileSize);

		task_RhnBase.OpenAndLogIn();
		page_RhnCommon.clickSignOut();

		for(int i=1; i <= fileSize; i++){
		//for(int i=1; i <= 5; i++){
			log.info("line # " +i);
			myUrl = (rh.getFileLine(filename, i));
			rh.openCompleteLink(myUrl, true);
			Assert.assertFalse(rh.isElementPresent("link=Sign Out", true));
		}
	}

	public void getBasicUserURL(String system){
		String filename = resourceDir+"sat01.out";
		String myUrl = "";
		String user = "";
		int fileSize = 0;
		task_TestPrep.getSatelliteUrls(system, true);
		fileSize = rh.getFileSize(filename);
		log.info("File Size = " + fileSize);

		user = task_YourRhn.createNonAdminUser(true,"autoFirst","autoLast","auto","redhat.com","334 deadwood drive","Raleigh","NC","27615", "919 555 5555");
		page_RhnCommon.clickSignOut();
		page_RhnCommon.LogIn(user,PASSWORD);

		for(int i=1; i <= fileSize; i++){
		//for(int i=1; i <= 2; i++){
			log.info("line # " +i);
			myUrl = (rh.getFileLine(filename, i));
			rh.openCompleteLink(myUrl, true);

			if(rh.isTextPresent("Please Sign In")){
				sel.stop();
				//FIXME
				//setUp();
				user = task_YourRhn.createNonAdminUser(true,"autoFirst","autoLast","auto","redhat.com","334 deadwood drive","Raleigh","NC","27615", "919 555 5555");
				page_RhnCommon.clickSignOut();
				page_RhnCommon.LogIn(user,PASSWORD);
				rh.openCompleteLink(myUrl, true);
			}


			if(rh.isTextPresent("Permission Error")){
				try {
			        BufferedWriter out = new BufferedWriter(new FileWriter(resourceDir+"BasicUserDenied", true));
			        out.newLine();//(myUrl);
			        out.write(myUrl);
			        out.close();
			    } catch (IOException ioe) {
			    	log.info("writing to the file failed");
			    }
			}

			if(rh.isTextPresent("Internal Server Error")){
				try {
			        BufferedWriter out = new BufferedWriter(new FileWriter(resourceDir+"BasicUserISE", true));
			        out.newLine();//(myUrl);
			        out.write(myUrl);
			        out.close();
			    } catch (IOException ioe) {
			    	log.info("writing to the file failed");
			    }
			}

			if(rh.isTextPresent("Page Request Error")){
				try {
			        BufferedWriter out = new BufferedWriter(new FileWriter(resourceDir+"BasicUserPageRequestError", true));
			        out.newLine();//(myUrl);
			        out.write(myUrl);
			        out.close();
			    } catch (IOException ioe) {
			    	log.info("writing to the file failed");
			    }
			}
			if(rh.isTextPresent("You do not have the appropriate permission")){
				try {
			        BufferedWriter out = new BufferedWriter(new FileWriter(resourceDir+"BasicUserSomethingNaughty", true));
			        out.newLine();//(myUrl);
			        out.write(myUrl);
			        out.close();
			    } catch (IOException ioe) {
			    	log.info("writing to the file failed");
			    }
			}
			else{
				try {
			        BufferedWriter out = new BufferedWriter(new FileWriter(resourceDir+"BasicUserAllowed", true));
			        out.newLine();
			        out.write(myUrl);
			        out.close();
			    } catch (IOException ioe) {
			    	log.info("writing to the file failed");
			    }
			}
		}
	}




}
