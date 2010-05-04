package com.redhat.rhn.harness.common;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.XmlRpcException;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Hashtable;

import java.util.logging.LogManager;

public class TestopiaLogger extends SeleniumSetup{
	/**
	 * Instance variable containing XMLRPC client
	 */
	private XmlRpcClient client;
	/**
	 * Instance variable containing XMLRPC config object
	 */
	private XmlRpcClientConfigImpl config;
	
	/**
	 * Instance variable containing current Test Case ID
	 */
	private String currentCaseID;
	
	/**
	 * Instance variable containing current Test Case Run ID
	 */
	private String currentRunID;
	
	/**
	 * Instance variable containing current Test Build
	 */
	private String currentBuild;
	
	/**
	 * Instance variable containing current Test Environment
	 */
	private String currentEnvironment;
	
	/**
	 * Instance variable containing current test log
	 */
	private String currentLog;
	
	/**
	 * Constructor for Testopia logger; initializes instance
	 * variables and initiates XMLRPC connection to Testopia
	 * server, throws an exception if passed a malformed URL
	 * @param server URL to Testopia server
	 * @throws MalformedURLException
	 */
	public TestopiaLogger(String server) throws MalformedURLException{
		config = new XmlRpcClientConfigImpl();
		try{
			config.setServerURL(new URL(server));
		}
		catch (MalformedURLException e){
			log.fine("Malformed URL passed to TestopiaLogger");
			throw e;
		}
		client = new XmlRpcClient();
	    client.setConfig(config);
	}
	
	/**
	 * Acquires the next available test run ID for a given
	 * test case
	 * @param caseID
	 * @return integer representing next available test run ID
	 */
	public int getNextTestRunID(String caseID) throws XmlRpcException{
		Object[] params = new Object[]{caseID};
		Object[] result;
		
		try{
			result = (Object[]) client.execute("Bugzilla.Testopia.Webservice.TestCase.get_case_run_history", params);
		}
		catch (XmlRpcException e){
			log.fine("Encountered an error while gathering testrun IDs!");
			throw e;
		}
		
		int highest = -1;
		for (int i=0;i<result.length;i++){
			Hashtable elem = (Hashtable) result[i];
			int id = (Integer) elem.get("run_id");
			if (id > highest)
			   highest = id;
		}
		return (highest + 1);
	}
	
	private Hashtable buildBasicHash(){
		Hashtable details = new Hashtable();
		details.put("run_id", this.currentRunID);
		details.put("case_id", this.currentCaseID);
		details.put("build", this.currentBuild);
		details.put("environment", this.currentEnvironment);
		return details;
	}
	
	/**
	 * Sets the current test case run object to a "RUNNING" state
	 * and enables 
	 */
	public void startLogging(){
		try{
			Hashtable details = this.buildBasicHash();
			details.put("status", "RUNNING");
			Object[] params = new Object[]{details};
			client.execute("Bugzilla.Testopia.Webservice.TestCaseRun.update", params);
		}
		catch(XmlRpcException e){
			log.info("Failed to start logging process!");
		}
	}
	
	/**
	 * Sets the current test case run object to a state reflecting success
	 * or failure and clears out all related instance variables
	 * @param successful boolean value representing test success/failure
	 */
	public void stopLogging(boolean successful){
		try{
			Hashtable details = this.buildBasicHash();
			if (successful)
				details.put("status", "PASSED");
			else
				details.put("status", "FAILED");
			Object[] params = new Object[]{details};
			client.execute("Bugzilla.Testopia.Webservice.TestCaseRun.update", params);
		}
		catch(XmlRpcException e){
			log.fine("Failed to stop logging process!");
		}
		this.currentBuild = null;
		this.currentCaseID = null;
		this.currentEnvironment = null;
		this.currentLog = null;
		this.currentRunID = null;
	}
	
	/**
	 * Callback method designed to send a new line to the XMLRPC server whenever
	 * a new line of log data is produced by the test automation suite
	 * @param newLine string containing new text to be added to log
	 */
	public void updateLog(String newLine){
		this.currentLog += newLine;
		try{
			Hashtable details = this.buildBasicHash();
			details.put("notes", this.currentLog);
			Object[] params = new Object[]{details};
			client.execute("Bugzilla.Testopia.Webservice.TestCaseRun.update", params);
		}
		catch(XmlRpcException e){
			return;
		}
	}
	
	/**
	 * Creates a fresh TestCaseRun object, sets it to be logged to,
	 * and returns a hashtable containing it
	 * @param caseID string representing test ID number or alias
	 * @param build build of Satellite/Spacewalk
	 * @param environment test environment
	 * @return hashtable representing created TestCaseRun object
	 */
	public Hashtable createTestCaseRun(String caseID,
									   String build,
									   String environment){
		Hashtable result = null;
		this.currentCaseID = caseID;
		this.currentBuild = build;
		this.currentEnvironment = environment;
		try{
			Hashtable details = new Hashtable();
		
			details.put("run_id", this.getNextTestRunID(caseID));
			details.put("case_id", caseID);
			details.put("build", build);
			details.put("environment", environment);
		
			Object[] params = new Object[]{details};
			result = (Hashtable) client.execute("Bugzilla.Testopia.Webservice.TestCaseRun.create", params);
		}
		catch (XmlRpcException e){
			log.info("Test Case Run object creation failed!");
		}
		return result;
	}
}
