package com.redhat.rhn.harness.common.Space01.tasks;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.ISdcSoftware;

public class Sdc extends com.redhat.rhn.harness.common.Sat51.tasks.Sdc implements ISdcSoftware{

	
	
	public static String XPATH_PKG_INSTALL = "xpath=//html/body/div/div[2]/div/table/tbody/tr/td[2]/form/table[2]";
	public static String XPATH_SDC_SOFTWARE="xpath=//html/body/div[2]/div/table/tbody/tr/td[2]/form/table[2]";
													///html/body/div[2]/div/table/tbody/tr/td[2]/form/table[2]						

	String snapshotTagBase = "automationBase";
	
	public void installPackage(String system) {
		task_RhnBase.OpenAndLogIn(); 
		installPackage(system, "squid");
		rh.waitForStatus("This action's status is: Completed.",
				"Package Install scheduled*", true, system, 5);
		page_SDC.clickLink_Software();  
		page_SDC.clickLink_Packages();
		page_SDC.clickLink_ListRemove();
		page_SDC.setTxt_SDCFilterBy("squid");
		page_SDC.clickButton_FilterGo();
		// tp.command_tailLog(system, "/var/log/up2date");
		Assert.assertTrue(rh.isTextPresent("squid"));

	}

	
	
	public void installPackage(String system, String pkg){
		page_SatelliteSystems.open();
        task_Search.goToSystem(system);
        page_RhnCommon.clickLink_GeneralLink(system);
	    page_SDC.clickLink_Software();
	    page_SDC.clickLink_Packages();
	    page_SDC.clickLink_Install();
	    page_SDC.setTxt_SDCFilterBy(pkg);
	    page_SDC.clickButton_FilterGo();
	    if(rh.isTextPresent("The list below is not filtered")){
	    	page_RhnCommon.clickButton_SelectAll();
	    }
	    else{
	    	//select_File_Checkbox(pkg);
	    	page_RhnCommon.check_FirstItemInList();
	    }
	    page_SDC.clickButton_InstallSelectedPackages();
	    page_SDC.clickButton_Confirm();
	    //task_TestPrep.command_runRhnCheckWithAT(system,true);
	    task_TestPrep.command_runRhnCheckInForeground(system, true);
	    rh.sleepForSeconds(5);
	    task_TestPrep.command_tailLog(system, "/var/log/up2date");
	    rh.waitForStatus("This action's status is: Completed.", "Package Install scheduled*", true, system, 5);
	}
	
	public void installPackageWithArch(String system, String pkg, String arch){
		page_SatelliteSystems.open();
        task_Search.goToSystem(system);
        page_RhnCommon.clickLink_GeneralLink(system);
	    page_SDC.clickLink_Software();
	    page_SDC.clickLink_Packages();
	    page_SDC.clickLink_Install();
	    page_SDC.setTxt_SDCFilterBy(pkg);
	    page_SDC.clickButton_FilterGo();
	    if(rh.isTextPresent("The list below is not filtered")){
	    	page_RhnCommon.clickButton_SelectAll();
	    }
	    else{
	    	String locator = "//tr[td[starts-with(normalize-space(.),'"+arch+"')]]//input[@type='checkbox']";
	    	String humanReadableName = sel.getText(locator);
	    	rh.checkRadioButton(locator,humanReadableName+" "+arch, true);
	    	
	    }
	    page_SDC.clickButton_InstallSelectedPackages();
	    page_SDC.clickButton_Confirm();
	    //task_TestPrep.command_runRhnCheckWithAT(system,true);
	    task_TestPrep.command_runRhnCheckInForeground(system, true);
	    rh.sleepForSeconds(5);
	    task_TestPrep.command_tailLog(system, "/var/log/up2date");
	    rh.waitForStatus("This action's status is: Completed.", "Package Install scheduled*", true, system, 5);
	}
	
	
	public void removePackageWithArch(String system, String pkg, String arch) {
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
			String locator = "//tr[td[starts-with(normalize-space(.),'"+arch+"')]]//input[@type='checkbox']";
			String humanReadableName = sel.getText(locator);
	    	rh.checkRadioButton(locator,humanReadableName + " " + arch, true);
			page_SDC.clickButton_RemovePackages();
			//TODO Verify that we are on page with "Confirm Package Removal "
			//TODO Verify that the package and its arch are listed in a row (7/2/09 current bug does not display an arch)
			page_SDC.clickButton_Confirm();
			task_TestPrep.command_runRhnCheckInForeground(system, true);
			// tsk_testPrep.command_tailLog(system, "/var/log/up2date");
			rh.waitForStatus("This action's status is: Completed.",
					"Package Removal scheduled*", true, system, 20);
			String out = ssh.executeViaSSHWithReturn(system,
					"rpm -q --qf \"%{NAME}-%{VERSION}-%{RELEASE}-%{ARCH}\n\" " + pkg)[0];
			assertFalse(out.contains(arch));
		}
	}
	
	public void selectPackageWithArch(String system, String pkg, String arch){
		page_SatelliteSystems.open();
        task_Search.goToSystem(system);
        page_RhnCommon.clickLink_GeneralLink(system);
	    page_SDC.clickLink_Software();
	    page_SDC.clickLink_Packages();
	    page_SDC.clickLink_ListRemove();
	    page_SDC.setTxt_SDCFilterBy(pkg);
	    page_SDC.clickButton_FilterGo();
	    
	    //rh.clickLink_InRow(XPATH_PKG_INSTALL, arch, 2);
	    String locator = "xpath=//tr[td[starts-with(normalize-space(.),'"+arch+"')]]//a";
	    String humanReadableName = sel.getText(locator)+" "+arch;
	    sel.click(locator, humanReadableName, true);
		assertTrue(rh.isTextPresent(arch));
	}
	
	public void verifyPackagesWithArch(String system, String pkg, String arch) {
		page_SatelliteSystems.open();
		task_Search.goToSystem(system);
		page_RhnCommon.clickLink_GeneralLink(system);
		page_SDC.clickLink_Software();
		page_SDC.clickLink_Verify();
		page_SDC.setTxt_SDCFilterBy(pkg);
		page_SDC.clickButton_FilterGo();
		String locator = "//tr[td[starts-with(normalize-space(.),'"+arch+"')]]//input[@type='checkbox']";
    	rh.checkRadioButton(locator, true);
		page_SDC.clickButton_VerifySelectedPackages();
		page_SDC.clickButton_Confirm();
		task_TestPrep.command_runRhnCheckInForeground(system, true);
		rh.waitForStatus("This action's status is: Completed.",
				"Verify deployed packages*", true, system, 20);

		if (rh.isTextPresent("Completed")) {
			page_RhnCommon.clickLink_GeneralLink("verify results");
			Assert.assertTrue(rh.isTextPresent("verification"));
		}
	}
	
	public void select_File_Checkbox(String file) {
		rh.selectItemInRow(XPATH_SDC_SOFTWARE, file);
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
		page_SDC.clickButton_Confirm();
		//task_TestPrep.command_runRhnCheckWithAT(system, true);
		task_TestPrep.command_runRhnCheckInForeground(system, true);
		// tsk_testPrep.command_tailLog(system, "/var/log/up2date");
		rh.waitForStatus("This action's status is: Completed.",
				"Package Install scheduled*", true, system, 20);
	}
	
	



}
