package com.redhat.rhn.harness.common.Sat42.tasks;

import java.io.IOException;
import java.util.logging.Level;

import com.redhat.qe.tools.ExecCommands;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;

public class Performance extends SeleniumSetup{
	
	protected RhnHelper rh = new RhnHelper();
	
	private void configureClientLoadSuite(String system, boolean runRegister, boolean runUpdate, String num_packs, boolean showlogs){
		//num_packs must be 10,50,100
		ExecCommands exec = new ExecCommands();
		String server = "server = " + IRhnBase.SERVER_HOSTNAME;
		String server2 = "\"server = " + IRhnBase.SERVER_HOSTNAME +"\"";
		String user = "username = "+IRhnBase.USER;
		String passwd = "password = "+IRhnBase.PASSWORD;
		String reg = "0";
		String update = "0";
		String packs = "0";
		
		if(runRegister)
			reg = "run_register_test = 1";
		else
			reg = "run_register_test = 0";
		
		if(runUpdate)
			update = "run_update_test = 1";
		else
			update = "run_update_test = 0";
		
		if(num_packs.equalsIgnoreCase("10"))
			packs = "num_packs = 10";
		else if(num_packs.equalsIgnoreCase("50"))
			packs = "num_packs = 50";
		else if(num_packs.equalsIgnoreCase("100"))
			packs = "num_packs = 100";
		else{
			throw new SeleniumException("Packs must = 10, 50, or 100");
		}
		
		try {
			exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " cp /root/load-testing/metrics/setup.cfg /root/load-testing/metrics/setup.cfg.bak");
			exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " cat /dev/null > /root/load-testing/metrics/setup.cfg");
			exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " echo \"[DEFAULT]\" >>  /root/load-testing/metrics/setup.cfg");
			exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " echo \""+server+"\" >>  /root/load-testing/metrics/setup.cfg");
			exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " echo \""+user+"\" >>  /root/load-testing/metrics/setup.cfg");
			exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " echo \""+passwd+"\" >>  /root/load-testing/metrics/setup.cfg");
			exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " perl -p -e 's/server = .*/"+server2+"/' -i /root/load-testing/ClientTests.py");
			exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " perl -p -e 's/run_register_test .*/"+reg+"/' -i /root/load-testing/ClientTests.py");
			exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " perl -p -e 's/run_update_test .*/"+update+"/' -i /root/load-testing/ClientTests.py");
			exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " perl -p -e 's/num_packs .*/"+packs+"/' -i /root/load-testing/ClientTests.py");
			exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " perl -p -e 's/cls.cleanup.*//' -i /root/load-testing/metrics/clientloadsuite.py");

		
		} catch (IOException ioe) {
			log.log(Level.INFO, "command failed", ioe);
		}
		

	}
	
	public void setupThreadPool(String system){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.command_scp("./src/main/resources/load-testing.tar", "/root", system, "root", true);
		ExecCommands exec = new ExecCommands();
		try{
			exec.submitCommandToLocalWithReturn(true,"ssh","root@"	+ system + " tar -xvf /root/load-testing.tar");
		
		} catch (IOException ioe) {
		log.log(Level.INFO, "command failed", ioe);
		}
		
	}
	
	private void runClientLoadSuite(String system, boolean showlogs){
		ExecCommands exec = new ExecCommands();

		/*try {
			//ssh.executeViaSSHWithReturn(IRhnBase.SYSTEM_HOSTNAME01,	"/root/load-testing/metrics/clientloadsuite.py ");
			exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " /root/load-testing/metrics/clientloadsuite.py");*/
			try {
				exec.submitCommandToLocalWithReturn(false,"ssh", "root@"+ system + " echo \"pushd /root/load-testing/metrics/; /usr/bin/python clientloadsuite.py\" > /tmp/loadTool");
				//log.info("break1");
			} catch (IOException ioe) {
				log.log(Level.INFO, "command failed", ioe);
			}
			try{
				exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+ system + " at now -f /tmp/loadTool");
				log.info("perf tool executed");
			}
			catch (Exception e){
				log.log(Level.INFO, "command failed", e);
			}

	}
	
	private void runClientTests(String system, boolean showlogs){
		ExecCommands exec = new ExecCommands();

			try {
				//exec.submitCommandToLocalWithReturn(false,"ssh", "root@"+ system + " echo \"pushd /root/load-testing/; echo $PWD;/root/load-testing/ClientTests.py\" > /tmp/loadTool");
				exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " cat /dev/null >  /tmp/loadTool");
				exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " echo \"#!/bin/bash\" >>  /tmp/loadTool");
				exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " echo \"cd /root/load-testing\" >>  /tmp/loadTool");
				exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " echo \"/usr/bin/python ClientTests.py\" >>  /tmp/loadTool");
				//log.info("break1");
			} catch (IOException ioe) {
				log.log(Level.INFO, "command failed", ioe);
			}
			try{
				exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+ system + " at now -f /tmp/loadTool");
				log.info("perf tool executed");
			}
			catch (Exception e){
				log.log(Level.INFO, "command failed", e);
			}

	}
	
	/**
	 * Executes dev's client performance tool ThreadPool.py
	 * @param system  The client system which the command is executed
	 * @param threads  Number of Threads, see numSystems
	 * @param runCheck Run rhn_check on set systems
	 * @param numberPackages Number of packages to install on each system
	 * @param user  Username to login
	 * @param password Password to logoin
	 * @param clearExistingIDs Ignore any existing systemIds, and simply re-register the system
	 * @param minutes  The time span in minutes events should occur in
	 * @param numSystem Number of systems to distribute across threads
	 * @param showlogs
	 */
	public void runThreadPool(String system, 
							  String threads,
							  boolean runCheck,
							  String numberPackages,
							  String user,
							  String password,
							  boolean clearExistingIDs,
							  String minutes,
							  String numSystem,
							  boolean showlogs){
		
		threads=" -t"+threads;
		String check = "";
		if(runCheck){
			check=" -k ";
		}
		numberPackages = " -n "+numberPackages;
		//skip dir
		String server = " -u "+IRhnBase.SERVER_HOSTNAME;
		user = " -l "+user;
		password = " -p "+password;
		String clear = "";
		if(clearExistingIDs){
			clear = " -c ";
		}
		minutes = " -m "+minutes;
		numSystem = " -s "+numSystem;
		
		String command = "/usr/bin/python Threadpool.py"+ threads + check + numberPackages + server + user + password + clear + minutes + numSystem;
		log.info("command ="+command);
		
		
		
		ExecCommands exec = new ExecCommands();
			try {
				exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " cat /dev/null >  /tmp/threadPool");
				exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " echo \"#!/bin/bash\" >>  /tmp/threadPool");
				exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " echo \"cd /root/load-testing\" >>  /tmp/threadPool");
				exec.submitCommandToLocalWithReturn(showlogs,"ssh","root@"	+ system + " echo "+ command +" >>  /tmp/threadPool");
				//log.info("break1");
			} catch (IOException ioe) {
				log.log(Level.INFO, "command failed", ioe);
			}
			try{
				exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+ system + " at now -f /tmp/threadPool");
				log.info("threadPool tool executed");
			}
			catch (Exception e){
				log.log(Level.INFO, "command failed", e);
			}

	}
	
	
	public void runClientPerformance_ClientTests(String system, boolean runRegister, boolean runUpdate, String num_packs, boolean showlogs){
		configureClientLoadSuite(system,runRegister,runUpdate,num_packs, showlogs);
		runClientTests(system, showlogs);	
	}
	
	
	
	
}
