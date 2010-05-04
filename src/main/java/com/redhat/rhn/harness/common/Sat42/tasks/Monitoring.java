package com.redhat.rhn.harness.common.Sat42.tasks;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;

public class Monitoring extends SeleniumSetup {// implements IMonitoring{


	protected RhnHelper rh = new RhnHelper();
	

	public void enableMonitoring(boolean openAndLogin){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_SatelliteTools.open();
		page_SatelliteTools.clickLink_SatelliteConfiguration();
		//stp.clickLink_Monitoring();
		page_SatelliteTools.check_EnableMonitoring(true);
		page_SatelliteTools.clickButton_Update();
		Assert.assertTrue(rh.isTextPresent("The RHN Satellite must be restarted to reflect these changes."));
	}

	public void enableMonitoringScout(boolean openAndLogin){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_SatelliteTools.open();
		page_SatelliteTools.clickLink_SatelliteConfiguration();
		page_SatelliteTools.clickLink_Monitoring();
		page_SatelliteTools.check_EnableMonitoringScout(true);
		page_SatelliteTools.clickButton_UpdateConfig();
		if(rh.isTextPresent("Configuration updated, Monitoring services restarted."))
			log.fine("Scout enabled for the first time");
		if(rh.isTextPresent("No changes made."))
			log.info("Scout was previously enabled");
	}
	
	public void pushMonitoringScoutConfig(boolean openAndLogin, boolean waitForComplete){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_Monitoring.open();
		page_Monitoring.clickLink_ScoutConfigPush();
		//rc.check_FirstItemInList();
		try{
			page_RhnCommon.clickButton_SelectAll_img();
			}
			catch(SeleniumException se){
				page_RhnCommon.clickButton_SelectAll();
			}
		page_Monitoring.clickButton_PushScoutConfigs();
		Assert.assertTrue(rh.isTextPresent("Config Push Initiated"));
		Assert.assertTrue(rh.isTextPresent("Request Pending"));
		if(waitForComplete){
			int count = 0;
			while(rh.isTextPresent("Request Pending")){
				count++;
				rh.sleepForSeconds(5);
				log.info("LOOP: checking for config scout push completion");
				page_Monitoring.open();
				page_Monitoring.clickLink_ScoutConfigPush();
				if(count >= 15){
					log.info("waited 400 seconds for status to move from pending");
					break;
				}
			}
		}
		assertTrue(rh.isTextNotPresent("Request Pending"));
	}
	
	public String monitoring_getSSHPublicKey(boolean openAndLogin,boolean proxy){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}

		page_Monitoring.open();
		page_Monitoring.clickLink_ScoutConfigPush();
		if(proxy){
			page_Monitoring.clickLink_RHN_Proxy_Scout();
		}
		else{
			page_Monitoring.clickLink_RHN_Monitoring_Scout();
		}
		String mykey = page_Monitoring.getTxt_RHNMD_public_key_for_Scout();
		log.info("mykey = "+mykey);
		return mykey;
	}
	
	
	
	
	
	public void monitoring_createProbeSuite(boolean openAndLogin, String probeName){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_Monitoring.open();
		page_Monitoring.clickLink_ProbeSuites();
		page_Monitoring.clickLink_CreateNewProbeSuite();
		page_Monitoring.setTxt_Name(probeName);
		page_Monitoring.setTxt_Description("autoProbe");
		page_Monitoring.clickButton_CreateProbeSuite();
		Assert.assertTrue(rh.isTextPresent("Probe Suite "+probeName+" created"));	
	}
	
	private void createProbe_1(boolean openAndLogin, String probeSuite){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_Monitoring.open();
		page_Monitoring.clickLink_ProbeSuites();
		page_RhnCommon.clickLink_GeneralLink(probeSuite);
		page_Monitoring.clickLink_Probes();
		page_Monitoring.clickLink_CreateNewProbe();
	}
	
	
	public void monitoring_addProbesToSuite_LinuxMemory(boolean openAndLogin,String probeSuite){
		createProbe_1(openAndLogin, probeSuite);
		page_Monitoring.select_Command_Group("Linux",true);
		page_Monitoring.select_Command("Memory Usage");
		page_Monitoring.select_ProbeCheckInterval();
		page_Monitoring.check_Probe_Notifications(true);
		page_Monitoring.select_NotificationInterval();
		page_Monitoring.setTxt_WarnIfBelow("2000");
		page_Monitoring.setTxt_CriticalIfBelow("1000");
		page_Monitoring.clickButton_CreateProbe();	
	}
	
	public void monitoring_addProbesToSuite_NetworkPing(boolean openAndLogin,String probeSuite){
		createProbe_1(openAndLogin, probeSuite);
		page_Monitoring.select_Command_Group("Network Services",true);
		page_Monitoring.select_Command("Ping");
		page_Monitoring.select_ProbeCheckInterval();
		page_Monitoring.check_Probe_Notifications(true);
		page_Monitoring.select_NotificationInterval();
		page_Monitoring.setTxt_CriticalIfAbove("5");
		page_Monitoring.setTxt_WarnIfAbove("1");
		page_Monitoring.setTxt_CriticalIfAboveLoss("5");
		page_Monitoring.setTxt_WarnIfAboveLoss("1");
		page_Monitoring.clickButton_CreateProbe();	
	}
	
	public void monitoring_addProbesToSuite_ApacheProcesses(boolean openAndLogin,String probeSuite,String system){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.command_generic("/etc/init.d/httpd ", "stop", system, true);
		task_TestPrep.command_generic("yum", " clean all",system, true);
		if(task_Sdc.listPackage(system, "httpd")){
			task_Sdc.removePackage(system, "httpd");
			task_TestPrep.command_generic("rm", " -Rf /etc/httpd/conf",system, true);
			task_TestPrep.command_generic("mkdir", " /etc/httpd/conf",system, true);
		}
		if(!task_Sdc.listPackage(system, "httpd")){
			task_Sdc.installPackage(system, "httpd");
		}
		//
		
		if(task_TestPrep.command_check_RHEL_Version(IRhnBase.SYSTEM_HOSTNAME01) == 4){
			task_TestPrep.command_scp("./src/main/resources/httpd.conf.rhel4", "/etc/httpd/conf/", IRhnBase.SYSTEM_HOSTNAME01, "root", true);
			task_TestPrep.command_generic("mv ", "/etc/httpd/conf/httpd.conf.rhel4 /etc/httpd/conf/httpd.conf",system, true);
		}
		if(task_TestPrep.command_check_RHEL_Version(IRhnBase.SYSTEM_HOSTNAME01) == 5){
			task_TestPrep.command_scp("./src/main/resources/httpd.conf.rhel5", "/etc/httpd/conf/", IRhnBase.SYSTEM_HOSTNAME01, "root", true);
			task_TestPrep.command_generic("mv ", "/etc/httpd/conf/httpd.conf.rhel5 /etc/httpd/conf/httpd.conf",system, true);
		}
		assertTrue(task_TestPrep.command_start_RHEL_Service(IRhnBase.SYSTEM_HOSTNAME01, "httpd"));
		//task_TestPrep.command_generic("touch ", "/var/www/html/server-status", system, true);
		createProbe_1(false, probeSuite);
		page_Monitoring.select_Command_Group("Apache",true);
		page_Monitoring.select_Command("Processes");
		page_Monitoring.select_ProbeCheckInterval();
		page_Monitoring.check_Probe_Notifications(true);
		page_Monitoring.select_NotificationInterval();
		page_Monitoring.setTxt_ApacheChild_CriticalIfAbove("1");
		page_Monitoring.setTxt_ApacheChild_WarnIfAbove("0");
		page_Monitoring.setTxt_ApacheSlot_CriticalIfAboveLoss("1");
		page_Monitoring.setTxt_ApacheSlot_WarnIfAboveLoss("0");
		page_Monitoring.clickButton_CreateProbe();	
	}
	
	public void monitoring_addProbesToSuite_NetworkServices_SMTP(boolean openAndLogin,String probeSuite,String system){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.command_generic("/etc/init.d/sendmail ", "stop", system, true);
		task_TestPrep.command_rpm("  -e sendmail sendmail-cf sendmail-doc --nodeps", system, true);
		if(!task_Sdc.listPackage(system, "postfix")){
			task_Sdc.installPackage(system, "postfix");
		}
		// /etc/postfix/main.cf
		//add inet_interfaces = all

		createProbe_1(false, probeSuite);
		page_Monitoring.select_Command_Group("Network Services",true);
		page_Monitoring.select_Command("Mail Transfer (SMTP)");
		page_Monitoring.select_ProbeCheckInterval();
		page_Monitoring.check_Probe_Notifications(true);
		page_Monitoring.select_NotificationInterval();
		page_Monitoring.clickButton_CreateProbe();
		
	}
	
	public void monitoring_addProbesToSuite_NetworkServices_DNS_Lookup(boolean openAndLogin,String probeSuite){
		createProbe_1(openAndLogin, probeSuite);
		page_Monitoring.select_Command_Group("Network Services",false);
		page_Monitoring.select_Command("DNS Lookup");
		page_Monitoring.select_ProbeCheckInterval();
		page_Monitoring.check_Probe_Notifications(true);
		page_Monitoring.select_NotificationInterval();		
		page_Monitoring.setTxt_CriticalIfAbove("5");
		page_Monitoring.setTxt_WarnIfAbove("1");
		page_Monitoring.clickButton_CreateProbe();	
	}
	
	public void monitoring_addProbesToSuite_NetworkServices_FTP(boolean openAndLogin,String probeSuite,String system){
		task_RhnBase.OpenAndLogIn();
		if(!task_Sdc.listPackage(system, "vsftpd")){
			task_Sdc.installPackage(system, "vsftpd");
		}
		task_TestPrep.command_generic("/etc/init.d/vsftpd ", "start", system, true);

		createProbe_1(false, probeSuite);
		page_Monitoring.select_Command_Group("Network Services",true);
		page_Monitoring.select_Command("FTP");
		page_Monitoring.select_ProbeCheckInterval();
		page_Monitoring.check_Probe_Notifications(true);
		page_Monitoring.select_NotificationInterval();
		page_Monitoring.setTxt_Username("ftp");
		page_Monitoring.setTxt_Password("asdf");
		page_Monitoring.setTxt_Password_Confirm("asdf");
		page_Monitoring.clickButton_CreateProbe();	
		
	}
	
	public void monitoring_addProbesToSuite_NetworkServices_Ping(boolean openAndLogin,String probeSuite){
		createProbe_1(openAndLogin, probeSuite);
		page_Monitoring.select_Command_Group("Network Services",false);
		page_Monitoring.select_Command("Ping");
		page_Monitoring.select_ProbeCheckInterval();
		page_Monitoring.check_Probe_Notifications(true);
		page_Monitoring.select_NotificationInterval();
		page_Monitoring.clickButton_CreateProbe();	
	}
	
	public void monitoring_addProbesToSuite_NetworkServices_Remote_Ping(boolean openAndLogin,String probeSuite){
		createProbe_1(openAndLogin, probeSuite);
		page_Monitoring.select_Command_Group("Network Services",false);
		page_Monitoring.select_Command("Remote Ping");
		page_Monitoring.select_ProbeCheckInterval();
		page_Monitoring.check_Probe_Notifications(true);
		page_Monitoring.select_NotificationInterval();
		page_Monitoring.setTxt_Remote_IP_Address(IRhnBase.SERVER_HOSTNAME);
		page_Monitoring.clickButton_CreateProbe();	
	}
	
	public void monitoring_addProbesToSuite_NetworkServices_RPC(boolean openAndLogin,String probeSuite,String system){
		task_RhnBase.OpenAndLogIn();
		if(!task_Sdc.listPackage(system, "nfs-utils")){
			task_Sdc.installPackage(system, "nfs-utils");
		}
		task_TestPrep.command_generic("echo ", " /var/www/html/ *(ro,sync) > /etc/exports", system, true);
		task_TestPrep.command_generic("exportfs ", " -a", system, true);
		task_TestPrep.command_generic("/etc/init.d/nfsd ", "restart", system, true);
		task_TestPrep.command_generic("showmount ", " -e", system, true);

		createProbe_1(false, probeSuite);
		page_Monitoring.select_Command_Group("Network Services",true);
		page_Monitoring.select_Command("RPC Service");
		page_Monitoring.select_ProbeCheckInterval();
		page_Monitoring.check_Probe_Notifications(true);
		page_Monitoring.select_NotificationInterval();
		page_Monitoring.clickButton_CreateProbe();	
		
	}
	
	public void monitoring_addProbesToSuite_NetworkServices_HTTPS(boolean openAndLogin,String probeSuite,String system){
		task_RhnBase.OpenAndLogIn();
		createProbe_1(false, probeSuite);
		try{
		page_Monitoring.select_Command_Group("Network Services",true);
		}
		catch (SeleniumException se){
			page_Monitoring.select_Command_Group("Network Services",false);
		}
		page_Monitoring.select_Command("Secure Web server (HTTPS)");

		
		page_Monitoring.select_ProbeCheckInterval();
		page_Monitoring.check_Probe_Notifications(true);
		page_Monitoring.select_NotificationInterval();
		page_Monitoring.setTxt_URL_PATH("/rhn/Login.do");
		page_Monitoring.setTxt_Username("admin");
		page_Monitoring.setTxt_Password("dog8code");
		page_Monitoring.setTxt_Password_Confirm("dog8code");

		page_Monitoring.clickButton_CreateProbe();	
		
	}
	
	
	
	public void monitoring_addProbesToSuite_LinuxLoad(boolean openAndLogin,String probeSuite){
		createProbe_1(openAndLogin, probeSuite);
		page_Monitoring.select_Command_Group("Linux",true);
		page_Monitoring.select_Command("Load");
		page_Monitoring.select_ProbeCheckInterval();
		page_Monitoring.check_Probe_Notifications(true);
		page_Monitoring.select_NotificationInterval();	
		page_Monitoring.setTxt_LinuxLoad_Critical("0.01", "1");
		page_Monitoring.setTxt_LinuxLoad_Warn("0.00", "1");
		page_Monitoring.setTxt_LinuxLoad_Critical("0.01", "5");
		page_Monitoring.setTxt_LinuxLoad_Warn("0.00", "5");
		page_Monitoring.setTxt_LinuxLoad_Critical("0.01", "15");
		page_Monitoring.setTxt_LinuxLoad_Warn("0.00", "15");
		page_Monitoring.clickButton_CreateProbe();	
	}
	
	public void monitoring_addProbesToSuite_LinuxVirtualMemory(boolean openAndLogin,String probeSuite){
		createProbe_1(openAndLogin, probeSuite);
		page_Monitoring.select_Command_Group("Linux",true);
		page_Monitoring.select_Command("Virtual Memory");
		page_Monitoring.select_ProbeCheckInterval();
		page_Monitoring.check_Probe_Notifications(true);
		page_Monitoring.select_NotificationInterval();
		
		page_Monitoring.setTxt_WarnIfBelow("99");
		page_Monitoring.setTxt_CriticalIfBelow("50");
		page_Monitoring.clickButton_CreateProbe();	
	}
	
	public void monitoring_addProbesToSuite_TCP_Check_Port80(boolean openAndLogin,String probeSuite){
		createProbe_1(openAndLogin, probeSuite);
		page_Monitoring.select_Command_Group("General",true);
		/*//rh.waitForPageToLoad("5");
		rh.sleepForSeconds(5);*/
		page_Monitoring.select_Command("TCP Check");
		page_Monitoring.select_ProbeCheckInterval();
		page_Monitoring.check_Probe_Notifications(true);
		page_Monitoring.select_NotificationInterval();
		page_Monitoring.setTxt_TCP_CHECK_Port("80");
		page_Monitoring.setTxt_TCP_CHECK_SEND("GET HEAD\\n");
		page_Monitoring.setTxt_TCP_CHECK_EXPECT("Apache");
		page_Monitoring.setTxt_Critical("5");
		page_Monitoring.setTxt_Warning("3");
		page_Monitoring.clickButton_CreateProbe();	
	}
	
	
	public void monitoring_addProbesToSuite_Linux_ProcessCount(boolean openAndLogin,String probeSuite){
		createProbe_1(openAndLogin, probeSuite);
		page_Monitoring.select_Command_Group("Linux",true);
		page_Monitoring.select_Command("Process Count Total");
		page_Monitoring.select_ProbeCheckInterval();
		page_Monitoring.check_Probe_Notifications(true);
		page_Monitoring.select_NotificationInterval();
		page_Monitoring.setTxt_Critical("700");
		page_Monitoring.setTxt_Warn("100");
		page_Monitoring.clickButton_CreateProbe();	
	}
	
	public void monitoring_addProbesToSuite_Oracle_TNS_Ping(boolean openAndLogin,String probeSuite){
		createProbe_1(openAndLogin, probeSuite);
		page_Monitoring.select_Command_Group("Oracle",true);
		page_Monitoring.select_Command("TNS Ping");
		page_Monitoring.select_ProbeCheckInterval();
		page_Monitoring.check_Probe_Notifications(true);
		page_Monitoring.select_NotificationInterval();
		page_Monitoring.setTxt_Critical_Latency("3");
		page_Monitoring.setTxt_Warning_Latency("1");
		page_Monitoring.clickButton_CreateProbe();	
	}
	
	public void monitoring_addProbesToSuite_Oracle_Availability(boolean openAndLogin,String probeSuite){
		createProbe_1(openAndLogin, probeSuite);
		page_Monitoring.select_Command_Group("Oracle",true);
		page_Monitoring.select_Command("Availability");
		page_Monitoring.select_ProbeCheckInterval();
		page_Monitoring.check_Probe_Notifications(true);
		page_Monitoring.select_NotificationInterval();
		page_Monitoring.setTxt_Oracle_SID("rhnsat");
		page_Monitoring.setTxt_Oracle_user("rhnsat");
		page_Monitoring.setTxt_Oracle_passwd("rhnsat");
		page_Monitoring.setTxt_Oracle_passwd_confirm("rhnsat");
		page_Monitoring.clickButton_CreateProbe();	
	}
	
	public void monitoring_addProbesToSuite_Oracle_Tablespace_Usage(boolean openAndLogin,String probeSuite){
		createProbe_1(openAndLogin, probeSuite);
		page_Monitoring.select_Command_Group("Oracle",true);
		page_Monitoring.select_Command("Tablespace Usage");
		page_Monitoring.select_ProbeCheckInterval();
		page_Monitoring.check_Probe_Notifications(true);
		page_Monitoring.select_NotificationInterval();
		page_Monitoring.setTxt_Oracle_SID("rhnsat");
		page_Monitoring.setTxt_Oracle_user("rhnsat");
		page_Monitoring.setTxt_Oracle_passwd("rhnsat");
		page_Monitoring.setTxt_Oracle_passwd_confirm("rhnsat");
		page_Monitoring.clickButton_CreateProbe();	
	}
	
	
	public void monitoring_addProbesToSuite_MySQL_DatabaseAccessibility(boolean openAndLogin,String probeSuite, String system){
		task_RhnBase.OpenAndLogIn();
		if(!task_Sdc.listPackage(system, "mysql")){
			task_Sdc.installPackage(system, "mysql");
		}
		if(!task_Sdc.listPackage(system, "mysql-server")){
			task_Sdc.removePackage(system, "mysql-server");
			task_Sdc.installPackage(system, "mysql-server");
		}
		log.info("mysql client must be installed on the satellite server for this probe to work");
		task_TestPrep.command_generic("yum ", " -y install mysql", IRhnBase.SERVER_HOSTNAME, true);
		task_TestPrep.command_generic("up2date ", " mysql", IRhnBase.SERVER_HOSTNAME, true);
		task_TestPrep.command_generic("/etc/init.d/mysqld ", "start", system, true);
		
		ssh.executeViaSSH(system,"/bin/echo \"CREATE DATABASE test \\g \" > /tmp/automysql.sql");
		ssh.executeViaSSH(system,"/bin/echo \"USE test  \" >> /tmp/automysql.sql");
		ssh.executeViaSSH(system,"/bin/echo \"GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, INDEX, ALTER, CREATE TEMPORARY " +
				"TABLES, LOCK TABLES ON test.*" +
				" TO 'sattest'@'%' IDENTIFIED BY 'dog8code'; \\g  \" >> /tmp/automysql.sql");
		
		
		ssh.executeViaSSH(system,"/bin/echo \"mysql -u root < /tmp/automysql.sql \" > /tmp/autoCreateMySQL");
		ssh.executeViaSSH(system, "at now -f /tmp/autoCreateMySQL");
		ssh.executeViaSSH(system, "/bin/touch /tmp/FINISHED_create_mysql_db");
		
		createProbe_1(false, probeSuite);
		page_Monitoring.select_Command_Group("MySQL",true);
		page_Monitoring.select_Command("Database Accessibility");
		page_Monitoring.select_ProbeCheckInterval();
		page_Monitoring.check_Probe_Notifications(true);
		page_Monitoring.select_NotificationInterval();
		page_Monitoring.setTxt_Mysql_DB_Name("test");
		page_Monitoring.setTxt_User("sattest");
		page_Monitoring.setTxt_Password("dog8code");
		page_Monitoring.setTxt_Password_Confirm("dog8code");
		page_Monitoring.clickButton_CreateProbe();	
		
	}
	
	public void monitoring_delete_ProbesToSuite(boolean openAndLogin){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_Monitoring.open();
		page_Monitoring.clickLink_ProbeSuites();
		if(rh.isTextNotPresent("No Probe Suites defined.")){
		page_RhnCommon.clickButton_SelectAll();
		page_Monitoring.clickButton_DeleteProbeSuite();
		page_Monitoring.clickButton_DeleteProbeSuite();
		}
		else{
			log.info("no probe suites found");
		}
		
	}
	
	public void monitoring_addSystemToProbeSuite(boolean openAndLogin,String probeSuite,String system){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_Monitoring.open();
		page_Monitoring.clickLink_ProbeSuites();
		page_RhnCommon.clickLink_GeneralLink(probeSuite);
		page_Monitoring.clickLink_Systems();
		page_Monitoring.clickLink_Add_Systems_to_Probe_Suite();
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInList();
		page_Monitoring.clickButton_AddSystemsToProbeSuite();
		Assert.assertTrue(rh.isTextPresent("Systems added to Probe Suite"));	
	}
	
	public void monitoring_checkProbeStatus(String system, String probe, String status1, String status2){
		page_SDC.clickLink_Monitoring_InMenu();
		page_RhnCommon.clickLink_GeneralLink(probe);
		int count = 0;
		while(rh.isTextPresent("PENDING, Awaiting Update")){
			count ++;
			rh.sleepForSeconds(30);
			log.info("LOOP: waiting on probe to update status");
			page_SDC.clickLink_Software();
			page_SDC.clickLink_Events();
			page_SDC.clickLink_Monitoring_InMenu();
			page_RhnCommon.clickLink_GeneralLink(probe);
			if(count >= 20){
				log.info("waited 600 seconds for status to move from pending");
				break;
			}
		}
		
		if(rh.isTextPresent(status1))
			Assert.assertTrue(rh.isTextPresent(status1));
		else
			Assert.assertTrue(rh.isTextPresent(status2));
		
	}
	
	
	

}
