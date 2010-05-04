package com.redhat.rhn.harness.regression.satellite531;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.redhat.rhn.harness.Space01.pages.KickStartPage;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;

public class BZ_527724 extends com.redhat.rhn.harness.common.Space01.tasks.KickStart{
	KickStartPage page_KickStart = 
		(KickStartPage)instantiator.getVersionedInstance(KickStartPage.class);
	protected RhnHelper rh = new RhnHelper();
	public final String profileName = "rhel5-bz527724-ks";
	public final String ksTree = "ks-rhel-i386-server-5-u3";
	public final String virtType_None = "None";
	public final String[] packages = {"screen","telnet","zsh","@Virtualization"};
	
	@BeforeClass(groups = { "setup" })
	public void test00_preparing(){
		task_RhnBase.OpenAndLogIn();
		this.DeleteKickstartProfile(profileName, false);
		task_RhnBase.SignOut();
	}
	
	@Test(groups = { "bz-527724" })
	public void test01_createSimpleKsProfile(){
		task_RhnBase.OpenAndLogIn();
		assertTrue(this.createNewKickstartProfile(profileName, 
				IRhnBase.RHN_CHANNEL01, ksTree, virtType_None,
				IRhnBase.PASSWORD, IRhnBase.SSH_PUBLIC_KEY));
		task_RhnBase.SignOut();
	}
	
	@Test(dependsOnMethods="test01_createSimpleKsProfile", 
			groups = { "bz-527724" })
	public void test02_addPackagesToKsProfile(){
		task_RhnBase.OpenAndLogIn();
		appendSoftwarePackages(profileName,packages);
		// TODO - check that packages are in the list.
		task_RhnBase.SignOut();
	}
	
	@Test(dependsOnMethods="test02_addPackagesToKsProfile", 
			groups = { "bz-527724" })
	public void test03_checkAddedPackagesExistence(){
		String selpath_packageList = "xpath=//textarea[@name='packageList']";
		task_RhnBase.OpenAndLogIn();
		this.page_KickStart.open();
		this.page_KickStart.referToKsProfile(profileName);
		this.page_KickStart.clickLink_Software();
		this.page_KickStart.clickLink_PackageGroups();
		String ks_packages = sel.getText(selpath_packageList);
		for(int i=0;i<this.packages.length;i++){
			assertTrue(ks_packages.contains(this.packages[i]));
		}
		task_RhnBase.SignOut();
	}
	
}
