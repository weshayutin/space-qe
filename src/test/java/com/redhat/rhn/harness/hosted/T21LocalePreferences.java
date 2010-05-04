package com.redhat.rhn.harness.hosted;

import org.testng.Assert;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Hosted.tasks.RhnBase;
import com.redhat.rhn.harness.common.Hosted.tasks.YourRhn;

public class T21LocalePreferences extends YourRhn{
	
	public static final String ORG_ADMIN_USERID = "jachengit";
	public static final String ORG_ADMIN_PASSWORD = "redhat";
	
	public static final String CHANNEL_ADMIN_USERID = "jchen011808";
	public static final String CHANNEL_ADMIN_PASSWORD = "redhat";
	
	// the following variables need to point to the ones that you want to test out
	public static final String BUENOS_AIRES_TIMEZONE_LABEL = "(GMT-0200) Brazil";
	public static final String DEFAULT_TIMEZONE_VALUE = "(GMT-0500) United States (Eastern)";
	public static final String BUENOS_AIRES_TIMEZONE_VALUE = "value=7031";
	public static final String LANGUAGE = "xpath=//input[@value='fr']";
	public static final String DEFAULT = "xpath=//input[@value='none']";
	public static final String SUBMIT_BUTTON = "xpath=//input[@type='submit' and @value='Save Preferences']";
	public static final String FRENCH_SUBMIT_BUTTON = "xpath=//input[@type='submit' and @value='Enregistrer les préférences']";
	public static final String LANGUAGE_TRIGGER = "votre";
	
	RhnHelper rh = new RhnHelper();
	RhnBase rb = new RhnBase();
	
	public void test01OrgAdmin01(){
		
		rb.OpenAndLogIn(ORG_ADMIN_USERID, ORG_ADMIN_PASSWORD);
		//open Locale Preferences page
		rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/account/LocalePreferences.do", true,"Locale Preferences");
		rh.selectComboBoxItem("timezone", BUENOS_AIRES_TIMEZONE_VALUE, false);
		rh.clickButton(SUBMIT_BUTTON, true);

		Assert.assertTrue(rh.isTextPresent(BUENOS_AIRES_TIMEZONE_LABEL));
		log.info("The time zone" + BUENOS_AIRES_TIMEZONE_LABEL + " exist");
		
		rh.selectComboBoxItem("timezone", DEFAULT_TIMEZONE_VALUE, false);
		rh.clickButton(SUBMIT_BUTTON, true);
	}
	
	public void testLanguageSettings(){
		boolean success=false;
		
		rb.OpenAndLogIn(ORG_ADMIN_USERID, ORG_ADMIN_PASSWORD);
		rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/account/LocalePreferences.do", true,"Locale Preferences");
		rh.checkRadioButton(LANGUAGE, true);
		rh.clickButton(SUBMIT_BUTTON, true);
		success = rh.isTextPresent(LANGUAGE_TRIGGER);
		rh.checkRadioButton(DEFAULT, true);
		rh.clickButton(FRENCH_SUBMIT_BUTTON, true);
		Assert.assertTrue(success);
		log.info("The language " + LANGUAGE + " works.");
	}
}