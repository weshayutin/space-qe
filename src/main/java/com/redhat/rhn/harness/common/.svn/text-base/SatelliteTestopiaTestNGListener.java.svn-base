package com.redhat.rhn.harness.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.xmlrpc.XmlRpcException;
import org.testng.ITestContext;
import org.testng.ITestResult;

import testopia.API.Build;
import testopia.API.Environment;
import testopia.API.Product;
import testopia.API.Session;
import testopia.API.TestCase;
import testopia.API.TestCaseRun;
import testopia.API.TestPlan;
import testopia.API.TestopiaException;

import com.redhat.qe.auto.testopia.TestProcedureHandler;
import com.redhat.qe.auto.testopia.TestopiaTestNGListener;
import com.redhat.rhn.harness.common.HarnessConfiguration;

public class SatelliteTestopiaTestNGListener extends TestopiaTestNGListener{
	
	protected List<String> getTestPlanNamesFromGroupAnnotations(ITestResult result){
		List<String> groups = Arrays.asList(result.getMethod().getGroups());
		List<String> testplans= new ArrayList<String>();
		for (String group: groups){
			if (group.startsWith(TESTNG_TESTPLAN_MARKER)) {
				String testplan = group.split(TESTNG_TESTPLAN_MARKER)[1];
				testplans.add(testplan);
			}
		}
		return testplans;
	}
	
	@Override
	protected List<Integer> getTestPlansFromGroupAnnotations(ITestResult result){
		List<String> groups = Arrays.asList(result.getMethod().getGroups());
		List<Integer> testplans= new ArrayList<Integer>();
		for (String group: groups){
			if (group.startsWith(TESTNG_TESTPLAN_MARKER)) {
				String testplan = group.split(TESTNG_TESTPLAN_MARKER)[1];
				log.finer("Found test plan: " + testplan);
				try{
					Integer testplanid = new TestPlan(session, testplan).getId();
					log.finer("with plan ID: " + testplanid);
					testplans.add(testplanid);
				}
				catch(Exception e){
					log.finer("Test plan \"" + testplan + "\" not found on Testopia, skipping...");
					continue;
				}
			}
		}
		return testplans;
	}

	@Override
	protected void retrieveContext() throws XmlRpcException{
		product = new Product(session, System.getProperty("testopia.testrun.product"));
		//System.out.println("testplan, in sattestnglist = "+HarnessConfiguration.TESTOPIA_TESTPLAN);
		testplan = new TestPlan(session, HarnessConfiguration.TESTOPIA_TESTPLAN);
		
		String buildNm;
		try{
			String satHostname = HarnessConfiguration.RHN_HOST;
			URL satUrl = new URL("http://" + satHostname + "/pub/build");
			BufferedReader in = new BufferedReader(new InputStreamReader(satUrl.openStream()));
			buildName = in.readLine();
			in.close();
		}
		catch (Exception e){
			log.log(Level.FINER, "Couldn't find Satellite build, defaulting to " +
					"system default", e);
		}
		
		build = new Build(session, product.getId());
		try {
			build.getBuildIDByName(buildName);
		}
		catch(Exception e){
			log.log(Level.FINER, "Couldn't find build " + buildName + ", creating new.", e);
			build.setName(buildName);
			build.create();
			
		}
		environment = new Environment(session, product.getId(), null);
		Integer envId = environment.getEnvironemntIDByName(environmentName);
		/*HashMap<String,Object> trinst= (HashMap<String, Object>) tr.create();
		TestCaseRun tcr = new TestCaseRun(session,
										  (Integer)trinst.get("run_id"),
										  2948,
										  buildID,
										  envId);
		tcr.create();*/
	}

	
	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestStart(org.testng.ITestResult)
	 */
	@Override
	public void onTestStart(ITestResult result) {
		
		//create new testcaserun
		int iteration = result.getMethod().getCurrentInvocationCount();
		log.finer("Got getCurrentInvocationCount()=" + iteration  + ", total=" + result.getMethod().getInvocationCount());
		String count = "";
		String className = getPackagelessTestClass(result);
		if (iteration > 0) count = new Integer(iteration+1).toString();
		String alias = version + "." + className + "." + result.getMethod().getMethodName() + count;
		String summary = className + "." + result.getMethod().getMethodName() + count;
		try {
			testcase = new TestCase(session, alias);
			//FIXME temporary to fix testcase names
			testcase.setSummary(summary);
			testcase.update();
			
		}catch(Exception e){
			log.log(Level.FINER, "Testcase retrieval failed on '" + summary + "', probably doesn't exist yet.", e);
			try {
				log.info("Creating new testcase: " + alias);
				String testplan = HarnessConfiguration.TESTOPIA_TESTPLAN;
				try{
					testplan = this.getTestPlanNamesFromGroupAnnotations(result).get(0);
				}
				catch (Exception ex){
					log.finer("Test case " + alias + " is not assigned to a known test plan," +
							"assigning to system default: " + TESTOPIA_TESTRUN_TESTPLAN);
				}
				testcase = new TestCase(session, "PROPOSED", "--default--", "P1",
						summary, testplan, TESTOPIA_TESTRUN_PRODUCT);
				testcase.setAlias(alias);
				testcase.setIsAutomated(true);
				testcase.create();
				
				
				
			}
			catch(Exception e2){
				throw new TestopiaException(e2);
			}
		}

		syncComponents(result);
		syncTestPlans(result);

		log.finer("Testrun is " + testrun.getId());
		
			
		testcaserun = new TestCaseRun(session,
							  testrun.getId(),
							  testcase.getId(),
							  build.getId(),
							  environment.getId());
		
		testcaserun.setStatus(TestCaseRun.Statuses.RUNNING);
		try {
			testcaserun.create();
			testrun.addCases(testcaserun.getId());
		}catch(Exception e) {
			throw new TestopiaException(e);
		}
	}
	
	
	protected void loginTestopia() throws XmlRpcException, GeneralSecurityException, IOException{
		TESTOPIA_URL = System.getProperty("testopia.url");
		TESTOPIA_USER = System.getProperty("testopia.login");
		TESTOPIA_PW = System.getProperty("testopia.password");
		TESTOPIA_TESTRUN_PRODUCT = System.getProperty("testopia.testrun.product");
		TESTOPIA_TESTRUN_TESTPLAN = HarnessConfiguration.TESTOPIA_TESTPLAN;
		testcaseOverwrite = (System.getProperty("testopia.testcase.overwrite") == "1");
		//System.out.println("TESTOPIA_TESTRUN_TESTPLAN line 493 ="+TESTOPIA_TESTRUN_TESTPLAN);
		log.finer("Logging in to testopia as " + TESTOPIA_USER);
		session = new Session(TESTOPIA_USER, TESTOPIA_PW, new URL(TESTOPIA_URL));
		session.login();
	}
	
	
	
	
	
}
