package com.redhat.rhn.harness.Space01;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Space01.tasks.Sdc;

@Test(groups="tests")
public class MultiArchitecture extends com.redhat.rhn.harness.common.Space01.tasks.MultiArchitecture{
	String pkg = "amanda";
	String channel = "Red Hat Enterprise Linux (v. 5 for 64-bit x86_64)";
	String pkgDir = System.getProperty("user.dir") + "/src/main/resources/multiarchPkgs/";
	String oldi386pkg = "amanda-2.4.4p3-1.i386.rpm";
	String oldx86_64pkg = "amanda-2.4.4p3-1.x86_64.rpm";
	String i386_arch = "i386";
	String x86_64_arch = "x86_64";
	
	RhnHelper rh = new RhnHelper();
	
	
	@BeforeClass(groups = { "setup" })
	public void test_00setup(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.registerSystemToSatellite(IRhnBase.SYSTEM_HOSTNAME03, true, false);
	}
	
//	assertTrue("Package '"+pkg+"' is installed on '"+IRhnBase.SYSTEM_HOSTNAME03+"'.",
//	task_Sdc.listPackage(IRhnBase.SYSTEM_HOSTNAME03,pkg));

	
	
	
	@Test(groups = { "testplan-MultiArchitecture"}, alwaysRun=true)
	public void test_Installi386PackageViaSDC(){
		task_RhnBase.OpenAndLogIn();
		this.cleanPackagesFromSystem(IRhnBase.SYSTEM_HOSTNAME03, pkg);
		task_Sdc.installPackageWithArch(IRhnBase.SYSTEM_HOSTNAME03, pkg, i386_arch);
	}
	
	@Test(dependsOnMethods="test_Installi386PackageViaSDC",groups = { "testplan-MultiArchitecture"})
	public void test_Selecti386PackageViaSDC(){
		task_RhnBase.OpenAndLogIn();
		task_Sdc.selectPackageWithArch(IRhnBase.SYSTEM_HOSTNAME03, pkg, i386_arch);
	}
	
	@Test(dependsOnMethods="test_Selecti386PackageViaSDC",groups = { "testplan-MultiArchitecture"})
	public void test_Verifyi386PackageViaSDC(){
		task_RhnBase.OpenAndLogIn();
		task_Sdc.verifyPackagesWithArch(IRhnBase.SYSTEM_HOSTNAME03,	pkg, i386_arch);
	}
	
	
	
	@Test(dependsOnMethods="test_Verifyi386PackageViaSDC",groups = { "testplan-MultiArchitecture"}, alwaysRun=true)
	public void test_Installx86_64PackageViaSDC(){
		task_RhnBase.OpenAndLogIn();
		this.cleanPackagesFromSystem(IRhnBase.SYSTEM_HOSTNAME03, pkg);
		task_Sdc.installPackageWithArch(IRhnBase.SYSTEM_HOSTNAME03, pkg, x86_64_arch);
	}
	
	@Test(dependsOnMethods="test_Installx86_64PackageViaSDC",groups = { "testplan-MultiArchitecture"})
	public void test_Selectx86_64PackageViaSDC(){
		task_RhnBase.OpenAndLogIn();
		task_Sdc.selectPackageWithArch(IRhnBase.SYSTEM_HOSTNAME03, pkg, x86_64_arch.equals("AMD64")?"x86_64":x86_64_arch);
	}
	
	@Test(dependsOnMethods="test_Selectx86_64PackageViaSDC",groups = { "testplan-MultiArchitecture"})
	public void test_Verifyx86_64PackageViaSDC(){
		task_RhnBase.OpenAndLogIn();
		task_Sdc.verifyPackagesWithArch(IRhnBase.SYSTEM_HOSTNAME03,	pkg, x86_64_arch.equals("AMD64")?"x86_64":x86_64_arch);
	}
	
	
	

	@Test(dependsOnMethods="test_Verifyx86_64PackageViaSDC",groups = { "testplan-MultiArchitecture"}, alwaysRun=true)
	public void test_Removei386PackageViaSDC(){
		task_RhnBase.OpenAndLogIn();
		this.cleanPackagesFromSystem(IRhnBase.SYSTEM_HOSTNAME03, pkg);
		task_Sdc.installPackageWithArch(IRhnBase.SYSTEM_HOSTNAME03, pkg, i386_arch);
		task_Sdc.installPackageWithArch(IRhnBase.SYSTEM_HOSTNAME03, pkg, x86_64_arch);
		// with 2 archs of package pkg installed, the following should only remove 1 of the packages
		task_Sdc.removePackageWithArch(IRhnBase.SYSTEM_HOSTNAME03, pkg, i386_arch);
	}
	
	@Test(dependsOnMethods="test_Removei386PackageViaSDC",groups = { "testplan-MultiArchitecture"}, alwaysRun=true)
	public void test_Removex86_64PackageViaSDC(){
		task_RhnBase.OpenAndLogIn();
		task_Sdc.installPackageWithArch(IRhnBase.SYSTEM_HOSTNAME03, pkg, i386_arch);
		// with x86_64_arch version of package installed in prior testcase, the following should find it and remove it (if not, then the removal of the i386 pkg may have removed too much which is a defect)
		task_Sdc.removePackageWithArch(IRhnBase.SYSTEM_HOSTNAME03, pkg, x86_64_arch);
	}
	
	
		
	
	@Test(dependsOnMethods="test_Removex86_64PackageViaSDC",groups = { "testplan-MultiArchitecture"}, alwaysRun=true)
	public void test_Installi386PackageViaSSM(){
		task_RhnBase.OpenAndLogIn();
		this.cleanPackagesFromSystem(IRhnBase.SYSTEM_HOSTNAME03, pkg);
		this.installArchedPackageFromSSM(IRhnBase.SYSTEM_HOSTNAME03, channel, pkg, i386_arch);
	}
	
	@Test(dependsOnMethods="test_Installi386PackageViaSSM",groups = { "testplan-MultiArchitecture"})
	public void test_Removei386PackageViaSSM(){
		task_RhnBase.OpenAndLogIn();
		this.removeArchedPackageFromSSM(IRhnBase.SYSTEM_HOSTNAME03,	pkg, i386_arch);
	}
	
		
	
	
	@Test(dependsOnMethods="test_Removei386PackageViaSSM",groups = { "testplan-MultiArchitecture"}, alwaysRun=true)
	public void test_ActivationKeyWithi386Packages(){
		task_RhnBase.OpenAndLogIn();
		this.cleanPackagesFromSystem(IRhnBase.SYSTEM_HOSTNAME03, pkg);
		this.activateSystemWithArchedPackage(IRhnBase.SYSTEM_HOSTNAME03, pkg, channel, i386_arch);
		//bug in satellite here
	}

	/*
	@Test(groups = { "testplan-MultiArchitecture"})
	public void test_PreparePackageProfileTest(){
		task_RhnBase.OpenAndLogIn();
		//clean all packages off the systems
		this.cleanPackagesFromSystem(IRhnBase.SYSTEM_HOSTNAME03, pkg);
		this.cleanPackagesFromSystem(task_RhnBase.SYSTEM_HOSTNAME04, pkg);
		
		//establish latest versions of x86_64/i386 packages on system A
		task_Sdc.installPackageWithArch(IRhnBase.SYSTEM_HOSTNAME03, 
				pkg,
				x86_64_arch);
		task_Sdc.installPackageWithArch(IRhnBase.SYSTEM_HOSTNAME03, 
				pkg,
				i386_arch);
		
		//establish older version of i386 package on system B
		scp.sendFile(task_RhnBase.SYSTEM_HOSTNAME04,
				pkgDir+oldi386pkg,
				"/root/");
		ssh.executeViaSSH(task_RhnBase.SYSTEM_HOSTNAME04,
				"rpm -Uvh --oldpackage --force --nodeps /root/"+oldi386pkg);
		
		//downgrade x86_64 package on system A
		scp.sendFile(IRhnBase.SYSTEM_HOSTNAME03,
				pkgDir+oldx86_64pkg,
				"/root/");
		ssh.executeViaSSH(IRhnBase.SYSTEM_HOSTNAME03,
				"rpm -Uvh --oldpackage --force --nodeps /root/"+oldx86_64pkg);
		
		task_TestPrep.enableProvisioning(IRhnBase.SYSTEM_HOSTNAME03,
				true);
		task_TestPrep.enableProvisioning(task_RhnBase.SYSTEM_HOSTNAME04,
				true);
		
		task_Sdc.syncTwoSystemPackageProfiles(IRhnBase.SYSTEM_HOSTNAME03,
				task_RhnBase.SYSTEM_HOSTNAME04);
		task_Sdc.compareTwoSystemPackageProfiles(IRhnBase.SYSTEM_HOSTNAME03,
				task_RhnBase.SYSTEM_HOSTNAME04);
	}*/
}
