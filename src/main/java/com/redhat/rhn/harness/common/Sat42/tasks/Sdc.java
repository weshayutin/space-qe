package com.redhat.rhn.harness.common.Sat42.tasks;

import java.util.Random;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.baseInterface.ISdcSoftware;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;

/**
 * Tasks used to install,delete rpm packages from the webui Also has snapshots
 * 
 * @author whayutin
 * 
 */
public class Sdc extends SeleniumSetup { // implements ISdcSoftware {

	public RhnHelper rh = new RhnHelper();
	/*public 
	public 
	public */
	
	/*
	
	public ConfigurationPage cp = HarnessConfiguration.configurationPage;
	
	public SDCPage sdc =  HarnessConfiguration.sdcPage;*/
	
	
	public static String XPATH_SDC_SOFTWARE = "xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]";

	public static String XPATH_PKG_INSTALL = "xpath=//html/body/div/div[2]/div/table/tbody/tr/td[2]/form/table[2]";
	
	public static String XPATH_PKG_VERIFY = "xpath=//html/body/div/div[2]/div/table/tbody/tr/td[2]/form/table[2]";
	
	String snapshotTagBase = "automationBase";

	public void prepUp2dateFile(String system) {
		
		task_TestPrep.disablePkgSkipListForUp2date(system, true);
		
	}

	public void prepRhnYumPlugin(String system) {
		// turns off GPG checking
		task_TestPrep.disableYumGPGCheck(system, true);
	}
	
	public void alterBaseChannelSubscriptions(boolean openAndLogin, String system, String channel){
		if(openAndLogin){
		task_RhnBase.OpenAndLogIn();
		}
		page_SatelliteSystems.open();
		task_Search.goToSystem(system); 
		page_RhnCommon.clickLink_GeneralLink(system);
		page_SDC.clickLink_AlterChannelSubscriptions();
		page_SDC.select_BaseChannel(channel);
		page_SDC.clickButton_Confirm();
		rh.isTextPresent("If you confirm the modifications");
		page_SDC.clickButton_ModifyBaseSoftwareChannel();
		assertTrue(rh.isTextPresent("System's Base Channel has been updated."));
	}
	
	public void alterChildChannelSubscriptions(boolean openAndLogin, String system, String childChannel){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
			}
			page_SatelliteSystems.open();
			task_Search.goToSystem(system); 
			page_RhnCommon.clickLink_GeneralLink(system);
			page_SDC.clickLink_AlterChannelSubscriptions();
			while(rh.isElementPresent("xpath=//input[@id='unchecked' and @name='child_channel']", true)){
				sel.check("xpath=//input[@id='unchecked' and @name='child_channel']");
				page_SDC.clickButton_ChangeSubscriptions();
				assertTrue(rh.isTextPresent("Child channel subscriptions updated."));
				page_SatelliteSystems.open();
				task_Search.goToSystem(system); 
				page_RhnCommon.clickLink_GeneralLink(system);
				page_SDC.clickLink_AlterChannelSubscriptions();
			}
		
	}
	
	
	
	public boolean check_SDC_Channel(boolean openAndLogin, String system, String channel){
		boolean found;
		if(openAndLogin){
		task_RhnBase.OpenAndLogIn();
		}
		page_SatelliteSystems.open();
		task_Search.goToSystem(system); 
		page_RhnCommon.clickLink_GeneralLink(system);
		if(rh.isTextPresent(channel)){
			found = true;
		}
		else{
			found = false;
		}
		return found;		
	}

	public void rhnPluginFix(String system) {
		task_RhnBase.OpenAndLogIn();
		
		page_SatelliteSystems.open();

		task_Search.goToSystem(system);
		page_RhnCommon.clickLink_GeneralLink(system);
		page_SDC.clickLink_Software();
		page_SDC.clickLink_Packages();
		page_SDC.clickLink_Upgrade();
		if (rh.isTextPresent("No package updates.")) {
			log.info("No package updates.");
		} else {
			page_SDC.setTxt_SDCFilterBy("rhn");
			page_SDC.clickButton_FilterGo();
			if (rh.isTextPresent("yum-rhn-plugin"))
				select_File_Checkbox("yum-rhn-plugin");
			page_SDC.clickButton_UpgradePackage();

			if (rh.isElementPresent("xpath=//input[@value='Confirm']", true))
				page_SDC.clickButton_Confirm();
			if (rh.isElementPresent("xpath=//input[@value='Confirm Upgrade']", true))
				page_SDC.clickButton_ConfirmUpgrade();

			log.info("ssh root@" + system + " rhn_check ");
			task_TestPrep.command_runRhnCheckWithAT(system, true);
			rh.waitForStatus("This action's status is: Completed.",
					"Package Install scheduled*", false, system, 5);
			// tsk_testPrep.command_tailLog(system, "/var/log/up2date");
		}

	}

	public void takeSnapShot(String system, boolean openAndLogin) {
		if (openAndLogin) {
			task_RhnBase.OpenAndLogIn();
		}
		page_SatelliteSystems.open();

		task_TestPrep.enableProvisioning(system, false);
		page_SDC.clickLink_Provisioning();
		page_SDC.clickLink_SnapShotTags();
		if (rh.isTextNotPresent(snapshotTagBase)) {
			page_SDC.clickLink_CreateNewSystemTag();
			page_SDC.setTxt_TagName(snapshotTagBase);
			page_SDC.clickButton_TagCurrentSnapshot();
		}
		Assert.assertTrue(rh.isTextPresent(snapshotTagBase));
	}
	
	public void takeSnapShot(String snapShotName, String system, boolean openAndLogin) {
		if (openAndLogin) {
			task_RhnBase.OpenAndLogIn();
		}
		page_SatelliteSystems.open();

		task_TestPrep.enableProvisioning(system, false);
		page_SDC.clickLink_Provisioning();
		page_SDC.clickLink_SnapShotTags();
		if (rh.isTextNotPresent(snapShotName)) {
			page_SDC.clickLink_CreateNewSystemTag();
			page_SDC.setTxt_TagName(snapShotName);
			page_SDC.clickButton_TagCurrentSnapshot();
		}
		Assert.assertTrue(rh.isTextPresent(snapShotName));
	}

	public void editCostumInfo(String system, String key, String value, boolean openAndLogin){
		if (openAndLogin) {
			task_RhnBase.OpenAndLogIn();
		}
		page_SatelliteSystems.open();

		task_TestPrep.enableProvisioning(system, false);
		page_SDC.clickLink_CustomInfo();
		page_SDC.clickLink_CustomInfo_createNewValue();
		rh.clickLink(key, true);
		page_SDC.setTxt_CustomInfoKey_value(value);
		page_SDC.clickButton_CustomInfo_UpdateKey();
		assertTrue(rh.isTextPresent("Value for "+key+" changed."));
	}
	
	public void installPackage(String system) {
		task_RhnBase.OpenAndLogIn();
		installPackage(system, "squid");
		rh.waitForStatus("This action's status is: Completed.",
				"Package Install scheduled*", true, system, 10);
		page_SDC.clickLink_Software();
		page_SDC.clickLink_Packages();
		page_SDC.clickLink_ListRemove();
		page_SDC.setTxt_SDCFilterBy("squid");
		page_SDC.clickButton_FilterGo();
		// tsk_testPrep.command_tailLog(system, "/var/log/up2date");
		Assert.assertTrue(rh.isTextPresent("squid"));

	}

	public void installPackage(String system, String pkg) {
		page_SatelliteSystems.open();
		task_Search.goToSystem(system);
		page_RhnCommon.clickLink_GeneralLink(system);
		page_SDC.clickLink_Software();
		page_SDC.clickLink_Packages();
		page_SDC.clickLink_Install();
		page_SDC.setTxt_SDCFilterBy(pkg);
		page_SDC.clickButton_FilterGo();
		//select_File_Checkbox(pkg);
		page_RhnCommon.check_FirstItemInList();
		page_SDC.clickButton_InstallSelectedPackages();
		page_SDC.clickButton_Confirm();
		
		task_TestPrep.command_runRhnCheckInForeground(system, true);
		// tsk_testPrep.command_tailLog(system, "/var/log/up2date");
		rh.waitForStatus("This action's status is: Completed.",
				"Package Install scheduled*", true, system, 20);
	}
	
	public void installPackageWithArch(String system, String pkg, String arch){
		throw new SeleniumException("method not implemented in this release");
	}
	
	public void selectPackageWithArch(String system, String pkg, String arch){
		throw new SeleniumException("method not implemented in this release");
	}
	
	public void removePackageWithArch(String system, String pkg, String arch) {
			throw new SeleniumException("method not implemented in this release");
			
	}

	public void installErrata(String system, String errataName,
			String latestPackage, String installedPackage,
			String packageShortName) {
		page_SatelliteSystems.open();
		task_Search.goToSystem(system);
		page_RhnCommon.clickLink_GeneralLink(system);
		page_SDC.clickLink_Software();
		page_SDC.clickLink_Packages();
		page_SDC.clickLink_Upgrade();
		page_SDC.setTxt_SDCFilterBy(packageShortName);
		page_SDC.clickButton_FilterGo();
		// Assert.assertTrue(rh.isTextPresent(errataName));
		Assert.assertTrue(rh.isTextPresent(latestPackage));
		// select_File_Checkbox(latestPackage);
		page_RhnCommon.check_FirstItemInList();
		page_SDC.clickButton_UpgradePackage();
		page_SDC.clickButton_ConfirmUpgrade();
		task_TestPrep.command_runRhnCheckWithAT(system, true);
		// tsk_testPrep.command_tailLog(system, "/var/log/up2date");
		rh.waitForStatus("This action's status is: Completed.",
				"Package Install scheduled*", true, system, 20);
	}

	public void removePackage(String system, String pkg) {
		page_SatelliteSystems.open();
		task_Search.goToSystem(system);
		page_RhnCommon.clickLink_GeneralLink(system);
		page_SDC.clickLink_Software();
		page_SDC.clickLink_Packages();
		page_SDC.clickLink_ListRemove();
		page_SDC.setTxt_SDCFilterBy(pkg);
		page_SDC.clickButton_FilterGo();
		if (rh.isTextPresent(pkg)) {
			//select_File_Checkbox(pkg);
			page_RhnCommon.check_SelectAll_CheckBox();
			page_SDC.clickButton_RemovePackages();
			page_SDC.clickButton_Confirm();
			task_TestPrep.command_runRhnCheckWithAT(system, true);
			// tsk_testPrep.command_tailLog(system, "/var/log/up2date");
			rh.waitForStatus("This action's status is: Completed.",
					"Package Removal scheduled*", true, system, 20);
		}
	}

	public boolean listPackage(String system, String pkg) {
		boolean installed = false;
		boolean rhn = false;
		boolean local = false;
		page_SatelliteSystems.open();
		task_Search.goToSystem(system);
		page_RhnCommon.clickLink_GeneralLink(system);
		page_SDC.clickLink_Software();
		page_SDC.clickLink_Packages();
		page_SDC.clickLink_ListRemove();
		page_SDC.setTxt_SDCFilterBy(pkg);
		page_SDC.clickButton_FilterGo();
		//page_SDC.setTxt_SDCFilterBy("");
		if (rh.isTextPresent(pkg)) {
			rhn = true;
		}
		if ((task_TestPrep.command_rpmQuery(system, pkg))) {
			local = true;
		}
		if (rhn && local)
			installed = true;

		return installed;
	}

	public void listRemovePackages(String system, boolean openAndLogin) {
		//
		// Package Removal scheduled by admin
		if(openAndLogin){
		task_RhnBase.OpenAndLogIn();
		}
		page_SatelliteSystems.open();
		task_Search.goToSystem(system);
		page_RhnCommon.clickLink_GeneralLink(system);
		page_SDC.clickLink_Software();
		page_SDC.clickLink_Packages();
		page_SDC.clickLink_ListRemove();
		page_SDC.setTxt_SDCFilterBy("bash");
		page_SDC.clickButton_FilterGo();
		Assert.assertTrue(rh.isTextPresent("bash-"));
		page_SDC.setTxt_SDCFilterBy("redhat-release");
		page_SDC.clickButton_FilterGo();
		Assert.assertTrue(rh.isTextPresent("redhat-release-"));
		page_SDC.setTxt_SDCFilterBy("zsh");
		page_SDC.clickButton_FilterGo();

		if (rh.isTextNotPresent("zsh")) {
			page_SDC.clickLink_Software();
			page_SDC.clickLink_Packages();
			page_SDC.clickLink_Install();
			page_SDC.setTxt_SDCFilterBy("zsh");
			page_SDC.clickButton_FilterGo();
			page_RhnCommon.check_FirstItemInList();
			page_SDC.clickButton_InstallSelectedPackages();
			page_SDC.clickButton_Confirm();
			task_TestPrep.command_runRhnCheckWithAT(system, true);
			rh.waitForStatus("This action's status is: Completed.",
					"Package Install scheduled*", true, system, 20);
			page_SDC.clickLink_Software();
			page_SDC.clickLink_Packages();
			page_SDC.clickLink_ListRemove();
			page_SDC.setTxt_SDCFilterBy("zsh");
			page_SDC.clickButton_FilterGo();

		}

		Assert.assertTrue(task_TestPrep.command_rpmQuery(system, "zsh"));
		//pg_rhnCommon.check_FirstItemInList();
		page_RhnCommon.check_SelectAll_CheckBox();
		page_SDC.clickButton_RemovePackages();
		page_SDC.clickButton_Confirm();
		task_TestPrep.command_runRhnCheckWithAT(system, true);
		rh.waitForStatus("This action's status is: Completed.",
				"Package Removal scheduled*", true, system, 20);
		Assert.assertFalse(task_TestPrep.command_rpmQuery(system, "zsh"));
		// tsk_testPrep.command_tailLog(system, "/var/log/up2date");

		task_Search.goToSystem(system);
		page_RhnCommon.clickLink_GeneralLink(system);
		page_SDC.clickLink_Software();
		page_SDC.clickLink_Packages();
		page_SDC.clickLink_ListRemove();
		page_SDC.setTxt_SDCFilterBy("zsh");
		page_SDC.clickButton_FilterGo();
		task_TestPrep.sleepForSeconds(3);
		Assert.assertFalse(rh.isTextPresent("zsh-"));
		log.info("PASS");

	}

	public void updateErrataAndPackages(String system, boolean updateAllAvailable) {
		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();

		task_TestPrep.enableProvisioning(system, false);
		page_SDC.clickLink_Provisioning();
		page_SDC.clickLink_SnapShotTags();
		if (rh.isTextNotPresent(snapshotTagBase)) {
			page_SDC.clickLink_CreateNewSystemTag();
			page_SDC.setTxt_TagName(snapshotTagBase);
			page_SDC.clickButton_TagCurrentSnapshot();
		}
		Assert.assertTrue(rh.isTextPresent(snapshotTagBase));

		page_SDC.clickLink_Software();
		page_SDC.clickLink_Errata();
		if (rh.isTextNotPresent("No Errata Relevant to Your Systems")) {
			if(updateAllAvailable){
				page_RhnCommon.clickButton_SelectAll();
			}
			else{
				log.info("Selecting ONLY the first page of Errata Updates");
				page_RhnCommon.check_SDC_SoftwareErrata_SelectAll_CheckBox();
			}
			page_SDC.clickButton_ApplyErrata();
			page_SDC.clickButton_Confirm();

			task_TestPrep.command_runRhnCheckWithAT(system, true);
			
				rh.waitForStatus("This action's status is: Completed.",
						"Errata Update scheduled by "+IRhnBase.USER, false, system, 60);
				rh.waitForStatus("This action's status is: Completed.",
						"Errata Update scheduled by "+IRhnBase.USER, true, system, 60);

			page_SDC.clickLink_Software();
			page_SDC.clickLink_Upgrade();
			if (rh.isTextNotPresent("No package")) {
				if(updateAllAvailable){
					page_RhnCommon.clickButton_SelectAll();
				}
				else{
					log.info("Selecting ONLY the first page of Package Updates");
					page_RhnCommon.check_SelectAll_CheckBox();
				}
				page_SDC.clickButton_UpgradePackage();
				if (rh.isElementPresent("xpath=//input[@value='Confirm']", true))
					page_SDC.clickButton_Confirm();
				if (rh.isElementPresent("xpath=//input[@value='Confirm Upgrade']", true))
					page_SDC.clickButton_ConfirmUpgrade();

				task_TestPrep.command_runRhnCheckWithAT(system, true);
				rh.waitForStatus("This action's status is: Completed.",	"Package Install scheduled*", false, system, 60);
				rh.waitForStatus("This action's status is: Completed.",	"Package Install scheduled*", true, system, 60);
					

				// tsk_testPrep.command_tailLog(system, "/var/log/up2date");
			}

				if(updateAllAvailable){
					page_SatelliteSystems.open();
					task_Search.goToSystem(system);
					page_RhnCommon.clickLink_GeneralLink(system);
					page_SDC.clickLink_Software();
					page_SDC.clickLink_Errata();
					Assert.assertTrue(rh.isTextPresent("No Errata Relevant to Your Systems"));
		
					page_SDC.clickLink_Software();
					page_SDC.clickLink_Upgrade();
					Assert.assertTrue(rh.isTextPresent("Upgradable Packages"));
					Assert.assertTrue(rh.isTextPresent("No package updates."));
				}
			} else {
				throw new SeleniumException("NO ERRATA UPDATES FOUND, ERRATA NOT TESTED");
			}
			
		

	}
	
	public boolean checkSystemForErrataUpdates(String system){
		boolean foundErrata = false;
		page_SatelliteSystems.open();
		task_Search.goToSystem(system);
		page_RhnCommon.clickLink_GeneralLink(system);
		page_SDC.clickLink_Software();
		page_SDC.clickLink_Errata();
		if(rh.isTextPresent("No Errata Relevant to Your Systems")){
			foundErrata = false;
		}
		else{
			foundErrata = true;
		}
		
		return foundErrata;
	}
	
	public boolean checkSystemForPackageUpdates(String system){
		boolean foundPackageUpdates = false;
		page_SatelliteSystems.open();
		task_Search.goToSystem(system);
		page_RhnCommon.clickLink_GeneralLink(system);
		page_SDC.clickLink_Software();
		page_SDC.clickLink_Upgrade();
		Assert.assertTrue(rh.isTextPresent("Upgradable Packages"));
		if(rh.isTextPresent("No package updates.")){
			foundPackageUpdates = false;
		}
		else{
			foundPackageUpdates = true;
		}
		
		return foundPackageUpdates;
	}

	public void rollBackPackages(String system, boolean openAndLogin) {
		String newSnapShot = "autoSnapTwo";
		if (openAndLogin) {
			task_RhnBase.OpenAndLogIn();
		}
		takeSnapShot(system, false);
		//installPackage(system, "zsh");
		
		page_SatelliteSystems.open();
		task_Search.goToSystem(system);
		page_RhnCommon.clickLink_GeneralLink(system);
		page_SDC.clickLink_Provisioning();
		page_SDC.clickLink_SnapShotTags();
		page_RhnCommon.setTxt_FilterBy(snapshotTagBase);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.clickLink_GeneralLink(snapshotTagBase);
		page_SDC.clickButton_RollbackToSnapshot();
		Assert.assertTrue(rh.isTextPresent("Channels and groups changed."));
		Assert.assertTrue(rh.isTextPresent("Package delta scheduled."));

		
		task_TestPrep.command_runRhnCheckWithAT(system, true);
		rh.waitForStatus("This action's status is: Completed.",
				"Package Synchronization scheduled*", true, system, 60);
		assertTrue(rh.isTextPresent("code 0"));
		task_TestPrep.screenShot();

	}

	public void select_File_Checkbox(String file) {
		rh.selectItemInRow(XPATH_SDC_SOFTWARE, file);
	}

	public void verifyPackages(String system) {
		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		task_Search.goToSystem(system);
		page_RhnCommon.clickLink_GeneralLink(system);
		page_SDC.clickLink_Software();
		page_SDC.clickLink_Verify();
		page_RhnCommon.clickButton_SelectAll();
		page_SDC.clickButton_VerifySelectedPackages();
		page_SDC.clickButton_Confirm();
		task_TestPrep.command_runRhnCheckWithAT(system, true);
		rh.waitForStatus("This action's status is: Completed.",
				"Verify deployed packages*", true, system, 20);

		if (rh.isTextPresent("Completed")) {
			page_RhnCommon.clickLink_GeneralLink("verify results");
			Assert.assertTrue(rh.isTextPresent("verification"));
		}
	}
	
	public void verifyPackagesWithArch(String system, String pkg, String arch) {
		throw new SeleniumException("method not implemented in this release");
	}

	public String createProfile(String system) {
		int n = 100;
		Random generator = new Random();
		int rand = generator.nextInt(n);
		String Profile = "profile" + rand;

		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		task_Search.goToSystem(system);
		page_RhnCommon.clickLink_GeneralLink(system);
		page_SDC.clickLink_Software();
		page_SDC.clickLink_Packages();
		page_SDC.clickLink_Profiles();
		page_SDC.clickButton_CreateSystemProfile();
		page_SDC.setTxt_ProfileDescription("test");
		page_SDC.clickButton_CreateProfile();
		Assert.assertTrue(rh.isTextPresent("name is required."));
		page_SDC.setTxt_ProfileName(Profile);
		page_SDC.setTxt_ProfileDescription("test");
		page_SDC.clickButton_CreateProfile();
		Assert.assertTrue(rh.isTextPresent("Profile " + Profile
				+ " successfully created*"));
		return Profile;
	}

	private void createProfile_part01(String profile_name) {
		page_SDC.clickButton_CreateSystemProfile();
		// Assert.assertTrue(rh.isTextPresent("name is required."));
		page_SDC.setTxt_ProfileName(profile_name);
		page_SDC.setTxt_ProfileDescription("test");
		page_SDC.clickButton_CreateProfile();
		Assert.assertTrue(rh.isTextPresent("Profile " + profile_name
				+ " successfully created*"));

	}
	
	public void syncTwoSystemPackageProfiles(String systemA,
			String systemB) {
		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		task_Search.goToSystem(systemA);
		page_RhnCommon.clickLink_GeneralLink(systemA);
		page_SDC.clickLink_Software();
		page_SDC.clickLink_Packages();
		page_SDC.clickLink_Profiles();
		page_SDC.select_SystemProfileName(systemB);
		page_SDC.clickButton_Compare();
		page_RhnCommon.clickButton_SelectAll();
		page_SDC.clickButton_SyncSystemPackages(systemB);
		page_SDC.clickButton_ScheduleSync();
		
		task_TestPrep.command_runRhnCheckWithAT(systemA,true);
	    rh.sleepForSeconds(5);
	    task_TestPrep.command_tailLog(systemA, "/var/log/up2date");
	    rh.waitForStatus("This action's status is: Completed.", "Package Synchronization scheduled*", true, systemA, 5);
	}
	
	public void compareTwoSystemPackageProfiles(String systemA,
			String systemB) {
		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		task_Search.goToSystem(systemA);
		page_RhnCommon.clickLink_GeneralLink(systemA);
		page_SDC.clickLink_Software();
		page_SDC.clickLink_Packages();
		page_SDC.clickLink_Profiles();
		page_SDC.select_SystemProfileName(systemB);
		page_SDC.clickButton_Compare();
		Assert.assertTrue(rh.isTextPresent("No differences."));
	}

	public void createProfileAndCompare(String system) {
		int n = 100;
		Random generator = new Random();
		int rand = generator.nextInt(n);
		String Profile = "profile" + rand;

		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		task_Search.goToSystem(system);
		page_RhnCommon.clickLink_GeneralLink(system);
		page_SDC.clickLink_Software();
		page_SDC.clickLink_Packages();
		page_SDC.clickLink_Profiles();
		createProfile_part01(Profile);
		page_SDC.select_ProfileName(Profile);
		page_SDC.clickButton_Compare();
		Assert.assertTrue(rh.isTextPresent("No differences."));
		installPackage(system, "httpd");
		page_SatelliteSystems.open();
		task_Search.goToSystem(system);
		page_RhnCommon.clickLink_GeneralLink(system);
		page_SDC.clickLink_Software();
		page_SDC.clickLink_Packages();
		page_SDC.clickLink_Profiles();
		page_SDC.select_ProfileName(Profile);
		page_SDC.clickButton_Compare();
		Assert.assertTrue(rh.isTextNotPresent("No differences"));
		page_RhnCommon.setTxt_FilterBy("httpd");
		page_RhnCommon.clickButton_Filter_Go();
		Assert.assertTrue(rh.isTextPresent("httpd"));
		Assert.assertTrue(rh.isTextPresent("This system only"));

	}

	public void changeBaseChannel(String system, String channel) {
    	page_SatelliteSystems.open();
        //tsk_search.goToSystem(system);
        page_RhnCommon.clickLink_GeneralLink(system);
		page_SDC.clickLink_AlterChannelSubscriptions();
		page_SDC.select_Software_Base_Channel(channel);
		page_SDC.clickButton_Confirm();
		page_SDC.clickButton_ModifyBaseSoftwareChannel();
		Assert.assertTrue(rh.isTextPresent("System's Base Channel has been updated."));     
	}
	
	public void createSystemNote(String system, String noteContents) {
		page_SatelliteSystems.open();
		task_Search.goToSystem(system);
		page_RhnCommon.clickLink_GeneralLink(system);
		
	}

}
