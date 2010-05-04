package com.redhat.rhn.harness.satellite42;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

@Test(groups="tests")
public class SatelliteProxy extends SeleniumSetup{

	protected RhnHelper rh = new RhnHelper();
	
	
	
	String proxyVersion = "RHN Proxy v5.3";
	String proxyVersionOld = "RHN Proxy v5.1";

	@BeforeClass(groups = { "setup" })
	public void test01Prep01(){
		
		task_TestPrep.unregisterAllProfilesOfSystem(rh.getTestSystem01Name(), true);
		task_TestPrep.removeRHNConfigFilesFromSystem(IRhnBase.SYSTEM_HOSTNAME01);
		task_Channels.deleteCustomChannel("proxycustomchanel01");
		task_TestPrep.command_rpm(" -e squid --nodeps", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_rpm(" -e httpd --nodeps", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_rpm(" -e system-config-httpd --nodeps", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_rpm(" -e httpd-devel --nodeps", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_rpm(" -e httpd-suexec --nodeps", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_rpm(" -e rhns-proxy-management --nodeps", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_rpm(" -e rhns-proxy-tools --nodeps", IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, false);
		task_TestPrep.enableRHNConfigManag(IRhnBase.SYSTEM_HOSTNAME01, false, false);
		
		
	}


	@Test(groups = "testplan-SatelliteProxy")
	public void test04_installProxy(){
		
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_Proxy.installProxy(false, proxyVersion);
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME01, false);
		rh.waitForStatus("Completed", "Deploy config files to system scheduled by admin", true, IRhnBase.SYSTEM_HOSTNAME01, 60);
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		Assert.assertTrue(task_Proxy.checkIfProxyInstalled(IRhnBase.SYSTEM_HOSTNAME01));
	}

	@Test(dependsOnMethods="test04_installProxy",groups = { "testplan-SatelliteProxy" })
	public void test05_getCertOnClient(){
		String rmcommand = "rm -Rf /root/RHN-ORG-TRUSTED-SSL-CERT";
		String proxyCert = "wget http://"+IRhnBase.SYSTEM_HOSTNAME01+"/pub/RHN-ORG-TRUSTED-SSL-CERT";
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.command_generic("/etc/init.d/iptables "," stop" , IRhnBase.SYSTEM_HOSTNAME01, false);
		task_TestPrep.command_generic("rm ","-Rf /root/RHN-ORG*" , IRhnBase.SYSTEM_HOSTNAME02, false);
		task_TestPrep.command_generic("echo ","\""+ proxyCert+ "\" > /root/getCommand" , IRhnBase.SYSTEM_HOSTNAME02, false);
		task_TestPrep.command_generic("at "," now -f /root/getCommand" , IRhnBase.SYSTEM_HOSTNAME02, false);

	}

	@Test(dependsOnMethods="test05_getCertOnClient",groups = { "testplan-SatelliteProxy" })
	public void test06_registerClient_viaProxy(){
		task_TestPrep.command_rpm(" -e zsh zsh-html", IRhnBase.SYSTEM_HOSTNAME02, true);
		task_TestPrep.registerSystemCustom(IRhnBase.SYSTEM_HOSTNAME02, IRhnBase.SYSTEM_HOSTNAME02,
				"--serverUrl=https://"+IRhnBase.SYSTEM_HOSTNAME01+"/XMLRPC --sslCACert=/root/RHN-ORG-TRUSTED-SSL-CERT  --force",
				IRhnBase.USER, IRhnBase.PASSWORD, true, true);
	}

	@Test(dependsOnMethods="test06_registerClient_viaProxy",groups = { "testplan-SatelliteProxy" })
	public void test07_verifyProxyConnection(){
		task_RhnBase.OpenAndLogIn();
		Assert.assertTrue(task_Proxy.checkProxyConnections(IRhnBase.SYSTEM_HOSTNAME02));
	}
	
	
	@Test(dependsOnMethods="test07_verifyProxyConnection",groups = { "testplan-SatelliteProxy" })
	public void test09_takeSnapShot(){
		task_Sdc.takeSnapShot(IRhnBase.SYSTEM_HOSTNAME02, true);

	}

	@Test(dependsOnMethods="test09_takeSnapShot",groups = { "testplan-SatelliteProxy" })
	public void test10_installPackage_viaProxy(){
		task_RhnBase.OpenAndLogIn();
		task_Sdc.installPackage(IRhnBase.SYSTEM_HOSTNAME02,"zsh");
	}

	@Test(dependsOnMethods="test10_installPackage_viaProxy",groups = { "testplan-SatelliteProxy" })
	public void test11_checkProxyLogs(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.command_generic("tail ","  /var/log/squid/access.log" , IRhnBase.SYSTEM_HOSTNAME01, true);
	}
	
	@Test(dependsOnMethods="test11_checkProxyLogs",groups = { "testplan-SatelliteProxy" })
	public void test12_createCustomChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.createCustomChannel("proxycustomchanel01");
	}
	
	@Test(dependsOnMethods="test12_createCustomChannel",groups = { "testplan-SatelliteProxy" })
	public void test13_rhnPushPackage_viaProxy(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.rhnPushPackageToChannel("proxycustomchanel01", "testAutoFile-1-1.0.i386.rpm", "./src/main/resources/", IRhnBase.SYSTEM_HOSTNAME01, IRhnBase.USER, IRhnBase.PASSWORD);
	}
	
	@Test(dependsOnMethods="test13_rhnPushPackage_viaProxy",groups = { "testplan-SatelliteProxy" })
	public void test14_verifyPackageInCustomChannel(){
		task_RhnBase.OpenAndLogIn();
		task_Channels.verifyPackageInChannel("proxycustomchanel01","testAutoFile-1-1.0" );
		Assert.assertTrue(rh.isTextPresent("RHN test automation, test rpm file"));
		
	}
	
	@Test(dependsOnMethods="test14_verifyPackageInCustomChannel",groups = { "testplan-SatelliteProxy" })
	public void test15DeactivateProxy(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystemSDC(IRhnBase.SYSTEM_HOSTNAME01);
		task_Proxy.deactivateProxy();
		task_TestPrep.command_runRhnCheckInForeground(IRhnBase.SYSTEM_HOSTNAME01,true);
	}


}
