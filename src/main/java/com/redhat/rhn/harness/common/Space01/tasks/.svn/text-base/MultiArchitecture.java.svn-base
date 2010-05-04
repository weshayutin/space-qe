package com.redhat.rhn.harness.common.Space01.tasks;

import java.util.Random;

import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

public class MultiArchitecture extends SeleniumSetup{
	RhnHelper rh = new RhnHelper();
	public final String AK_XPATH = "xpath=/html/body/div/div[5]/table/tbody/tr/td[2]/form/table";
	
	public boolean cleanPackagesFromSystem(String hostname,
										   String pkg){
		ssh.executeViaSSH(hostname,
				"for i in `rpm -q --qf \"%{NAME}-%{VERSION}-%{RELEASE}.%{ARCH}\n\" "+pkg+"-devel`" +
						";do rpm -e --nodeps $i;done");
		ssh.executeViaSSH(hostname,
				"for i in `rpm -q --qf \"%{NAME}-%{VERSION}-%{RELEASE}.%{ARCH}\n\" "+pkg+"`" +
						";do rpm -e --nodeps $i;done");
		page_RhnCommon.clickSystems();
		task_Search.goToSystem(hostname);
		page_RhnCommon.clickLink_GeneralLink(hostname);
		page_SDC.clickLink_Software();
	    page_SDC.clickLink_Packages();
	    page_SDC.clickButton_UpdatePackageList();
		ssh.executeViaSSH(hostname,
				"rhn_check -vvvvv");
		return true;
	}
	
	public boolean installArchedPackageFromSSM(String hostname,
											   String channel,
											   String pkg,
											   String arch){
		
		page_RhnCommon.clickSystems();
		task_Search.goToSystem(hostname);
		task_RhnBase.clickButton_SelectAll();
		page_RhnCommon.clickButton_SSM_Manage();
		task_SSM.SSM_InstallPackageWithArch(channel, pkg, arch);
		
		task_TestPrep.command_runRhnCheckInForeground(hostname, true);
		rh.sleepForSeconds(5);
		task_TestPrep.command_tailLog(hostname, "/var/log/up2date");
		
		task_Search.goToSystem(hostname);
		page_RhnCommon.clickLink_GeneralLink(hostname);
		rh.waitForStatus("This action's status is: Completed.",
				"Package Install scheduled*", true, hostname, 20);
		
		String out = ssh.executeViaSSHWithReturn(hostname,
				"rpm -q --qf \"%{NAME}-%{VERSION}-%{RELEASE}-%{ARCH}\n\" " + pkg)[0];
		assertTrue(out.contains(arch));
		return true;
	}
	
	public boolean activateSystemWithArchedPackage(String hostname,
												   String pkg,
												   String channel,
												   String arch){
		this.cleanPackagesFromSystem(hostname, pkg);
		Random rand = new Random();
		String keyName = arch + "key-" + rand.nextInt();
		String key = "" + Math.abs(rand.nextInt());
		
		task_ActivationKeys.createActivationKey(keyName,
				key, 
				"", 
				false, 
				true, 
				false, 
				false, 
				false);
		
		String ak = task_ActivationKeys.getAKFromKeyName(keyName);
		task_ActivationKeys.addPackageToKey(keyName, pkg+"."+arch);
		
		task_TestPrep.registerSystemWithActKey(hostname, 
				hostname, 
				ak, 
				true);
		
		//ssh.executeViaSSH(hostname,
		//		"rhnreg_ks --force" 
		//		+ task_RhnBase.RHN_REG_SAT_URL + 
		//		" --activationkey=" + ak);
		
		assertTrue(
				task_ActivationKeys.isSystemActivated(
						hostname,
						keyName));
		
		String out = ssh.executeViaSSHWithReturn(hostname,
				"rpm -q --qf \"%{NAME}-%{VERSION}-%{RELEASE}-%{ARCH}\n\" " + pkg)[0];
		assertTrue(out.contains(arch));
		
		return true;
	}
	
	public boolean removeArchedPackageFromSSM(String hostname,
											  String pkg,
											  String arch){
		page_RhnCommon.clickSystems();
		task_Search.goToSystem(hostname);
		task_RhnBase.clickButton_SelectAll();
		page_RhnCommon.clickButton_SSM_Manage();
		task_SSM.SSM_RemovePackagesWithArch(pkg, arch);
		return true;
	}
}
