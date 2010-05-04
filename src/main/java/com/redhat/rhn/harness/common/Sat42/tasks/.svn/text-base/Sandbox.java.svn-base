package com.redhat.rhn.harness.common.Sat42.tasks;


import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

/**
 * Just a playpen or test area
 * @author whayutin
 *
 */
public class Sandbox extends SeleniumSetup{

	
	RhnHelper rh = new RhnHelper();
	
	



	/*public void westest01(){
		sel.open("http://popuptest.com");
		sel.waitForPageToLoad(HarnessConfiguration.TIMEOUT);
		System.out.println("window title ="+ sel.getTitle());

		String titles[] = sel.getAllWindowTitles();
		titles = sel.getAllWindowTitles();
		System.out.println("1 The number of windows="+ titles.length);

		sp.clickLink_ComeAndGo();
		sel.selectWindow("");
		//sel.waitForPopUp(" PopupTest 2 - test your popup killer software", "6000");
		sel.waitForPageToLoad(HarnessConfiguration.TIMEOUT);
		sel.selectWindow("");

		String popups[] = sel.getAllWindowTitles();
		popups = sel.getAllWindowTitles();
		System.out.println("2 The number of windows ="+ popups.length);

		for(int i=0;i<popups.length;i++){
			System.out.println("window Popups= "+popups[i]);
		}

		}

	public void westest02(){
		sel.open("http://dev71.qa.atl2.redhat.com:7080");
		sel.waitForPageToLoad(HarnessConfiguration.TIMEOUT);
		sel.open("http://dev71.qa.atl2.redhat.com:7080/Login.do");
		sel.waitForPageToLoad(HarnessConfiguration.TIMEOUT);
		sel.isTextPresent("Login:");
		sp.setTxt_JbossUser("asdf");
		sp.setTxt_JbossPass("asfasdf");
		sp.clickButton_JbossLogin();
	}
	
	
	
	
	*/
/*	public void testSetup(){
		task_RhnBase.OpenAndLogIn();
		task_Perf.runClientPerformance_ClientTests(IRhnBase.SYSTEM_HOSTNAME01,true,true,"10", true);
	}
	
	
	
	public void testperf(){
		task_RhnBase.OpenAndLogIn();
		task_Perf.runClientPerformance_ClientTests(IRhnBase.SYSTEM_HOSTNAME01,true,true,"10", true);
	}
	
	public void testThreadPool(){
		task_RhnBase.OpenAndLogIn();
		task_Perf.runThreadPool(IRhnBase.SYSTEM_HOSTNAME01, "2", true, "2", IRhnBase.USER, IRhnBase.PASSWORD, true, "2","2", true);
	}
	
	public void setupThreadPoolonSystem(){
		task_RhnBase.OpenAndLogIn();
		task_Perf.setupThreadPool(IRhnBase.SYSTEM_HOSTNAME01);
	}*/
	
	public void createMonProbes(){
			String Suite = "autotestMon01";
			task_RhnBase.OpenAndLogIn();
			task_TestPrep.command_check_RHEL_Version(IRhnBase.SYSTEM_HOSTNAME01);
				//assertTrue(task_TestPrep.command_start_RHEL_Service(IRhnBase.SYSTEM_HOSTNAME01, "httpd"));
				//task_Monitoring.monitoring_addProbesToSuite_ApacheProcesses(false, Suite, IRhnBase.SYSTEM_HOSTNAME01 );
/*				task_Monitoring.monitoring_addProbesToSuite_Linux_ProcessCount(false, Suite);
				task_Monitoring.monitoring_addProbesToSuite_LinuxLoad(false, Suite);
				task_Monitoring.monitoring_addProbesToSuite_LinuxMemory(false, Suite);
				task_Monitoring.monitoring_addProbesToSuite_LinuxVirtualMemory(false, Suite);*/
				//task_Monitoring.monitoring_addProbesToSuite_MySQL_DatabaseAccessibility(false, Suite, IRhnBase.SYSTEM_HOSTNAME01);
				//task_Monitoring.monitoring_addProbesToSuite_NetworkPing(false, Suite);
				//task_Monitoring.monitoring_addProbesToSuite_NetworkServices_DNS_Lookup(false, Suite);
				//task_Monitoring.monitoring_addProbesToSuite_NetworkServices_FTP(false, Suite,IRhnBase.SYSTEM_HOSTNAME01);
				//task_Monitoring.monitoring_addProbesToSuite_NetworkServices_HTTPS(false, Suite,IRhnBase.SERVER_HOSTNAME);
				//task_Monitoring.monitoring_addProbesToSuite_NetworkServices_Ping(false, Suite);
				//task_Monitoring.monitoring_addProbesToSuite_NetworkServices_Remote_Ping(false, Suite);
				//task_Monitoring.monitoring_addProbesToSuite_NetworkServices_RPC(false, Suite,IRhnBase.SYSTEM_HOSTNAME01);
				//task_Monitoring.monitoring_addProbesToSuite_NetworkServices_SMTP(false, Suite,IRhnBase.SYSTEM_HOSTNAME01);
				//task_Monitoring.monitoring_addProbesToSuite_TCP_Check_Port80(false, Suite);
					
		
	}
	
	
	public void testForJeff(){
		task_RhnBase.OpenAndLogIn("org1auto", "dog8code");
		task_Sdc.alterChildChannelSubscriptions(false, "prof01", "child1");
		
	}
	
	/*public void test00Prep_ClearSELinux(){
		log.fine("Clearing SELinux logs");
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.command_clear_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME);
		
	}*/
	
	/*public void testSELinux(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.command_clear_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME);
		assertTrue(task_TestPrep.command_check_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME));
	}
	
	public void testClearSELinux(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.command_clear_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME);
		//assertTrue(task_TestPrep.command_check_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME));
	}
	*/
	
	/*public void wget(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.command_getSatelliteSSLCert(IRhnBase.SERVER_HOSTNAME, IRhnBase.SYSTEM_HOSTNAME01);
		//assertTrue(task_TestPrep.command_check_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME));
	}*/
	
	/*public void westest03(){
		task_RhnBase.OpenAndLogIn();
		//task_Channels.createChannelClone(IRhnBase.RHN_CHANNEL01, "wesleydavid", "wesleydavid", 1);
		task_Perf.runClientLoadSuite(IRhnBase.SYSTEM_HOSTNAME01, true);		
	}
	
	public void westest04(){
		task_RhnBase.OpenAndLogIn();
		//task_Channels.createChannelClone(IRhnBase.RHN_CHANNEL01, "wesleydavid", "wesleydavid", 1);
		task_Perf.runClientTests(IRhnBase.SYSTEM_HOSTNAME01, true);		
	}*/




}



