package com.redhat.rhn.harness.gold.Space01;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.qe.auto.selenium.MyLevel;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;


public class UserRoles extends com.redhat.rhn.harness.Space01.UserRoles {
	
	public static final String SYSTEM_TEMP_UNAVAIL = "temporarily unavailable";
	public static final long SYSTEM_GROUP_PAGE_LOAD_TIMEOUT_THRESHOLD = 120000;
	
	@BeforeClass(groups = { "setup" })
	public void test02Prep02(){	
		task_TestPrep.removeAllProfilesOfASystem(IRhnBase.SYSTEM_HOSTNAME01);
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_SAT_REG_CMD, true, true);
	}
	
	/**
	 * Necessity of the test is based on the existing bug:
	 * https://bugzilla.redhat.com/show_bug.cgi?id=525575<br>
	 * Checking that there is not a long page loading progress and an exception 
	 * in the opening of the "rhn/systems/SystemList.do" page.<br>
	 *  Check conditions:<br>
	 *  &nbsp;&nbsp; - page loading in 2 min.<br>
	 *  &nbsp;&nbsp; - no text present "temporarily unavailable".
	 */
	@Test(groups = { "testplan-User_Roles_and_Groups" })
	public void test70_systems_checkPageLoadTimeout(){

		long time_fix = (new Date()).getTime();
		long duration_systemGroups;
		long backupWaitForPageTimeout = Long.parseLong(sel.getTimeout());
		String thePage = "https://"+HarnessConfiguration.RHN_HOST + 
		HarnessConfiguration.RHN_SYSTEMS_PAGE;

		task_RhnBase.OpenAndLogIn();
		try{
			log.log(MyLevel.ACTION, "Backup waitForPageTimeout ["+backupWaitForPageTimeout+"]");
			sel.setTimeout(Long.toString((backupWaitForPageTimeout+
					SYSTEM_GROUP_PAGE_LOAD_TIMEOUT_THRESHOLD)));
			log.log(MyLevel.ACTION, "Set new waitForPageTimeout ["+sel.getTimeout()+"]");
			sel.open(thePage);
			duration_systemGroups = (new Date()).getTime() - time_fix;
			log.log(MyLevel.ACTION, "Opened URL \'"+thePage+"\' in ["+duration_systemGroups+"] ms");
			// assertions
			Assert.assertTrue(duration_systemGroups<=SYSTEM_GROUP_PAGE_LOAD_TIMEOUT_THRESHOLD);
			Assert.assertTrue(rh.isTextNotPresent(SYSTEM_TEMP_UNAVAIL));
		}catch(Throwable t){
			Assert.fail("open url \'"+thePage+"\' falied with exception.", t);
		}finally{
			sel.setTimeout(Long.toString(backupWaitForPageTimeout));
			log.log(MyLevel.ACTION, "Restore waitForPageTimeout ["+sel.getTimeout()+"]");
		}
	}
		
	/**
	 * Necessity of the test is based on the existing bug:
	 * https://bugzilla.redhat.com/show_bug.cgi?id=525575<br>
	 * Checking that there is not a long page loading progress and an exception 
	 * in the opening of the "rhn/systems/SystemGroupList.do" page.<br>
	 *  Check conditions:<br>
	 *  &nbsp;&nbsp; - page loading in 2 min.<br>
	 *  &nbsp;&nbsp; - no text present "temporarily unavailable".
	 */
	@Test(dependsOnMethods="test70_systems_checkPageLoadTimeout", groups = { "testplan-User_Roles_and_Groups" })
	public void test71_systemGroup_checkPageLoadTimeout(){

		long time_fix = (new Date()).getTime();
		long duration_systemGroups;
		long backupWaitForPageTimeout = Long.parseLong(sel.getTimeout());
		String thePage = "https://"+HarnessConfiguration.RHN_HOST + 
		HarnessConfiguration.RHN_SYSTEMS_GROUP_PAGE;

		task_RhnBase.OpenAndLogIn();
		try{
			log.log(MyLevel.ACTION, "Backup waitForPageTimeout ["+backupWaitForPageTimeout+"]");
			sel.setTimeout(Long.toString((backupWaitForPageTimeout+
					SYSTEM_GROUP_PAGE_LOAD_TIMEOUT_THRESHOLD)));
			log.log(MyLevel.ACTION, "Set new waitForPageTimeout ["+sel.getTimeout()+"]");
			sel.open(thePage);
			duration_systemGroups = (new Date()).getTime() - time_fix;
			log.log(MyLevel.ACTION, "Opened URL \'"+thePage+"\' in ["+duration_systemGroups+"] ms");
			// assertions
			Assert.assertTrue(duration_systemGroups<=SYSTEM_GROUP_PAGE_LOAD_TIMEOUT_THRESHOLD);
			Assert.assertTrue(rh.isTextNotPresent(SYSTEM_TEMP_UNAVAIL));
		}catch(Throwable t){
			Assert.fail("open url \'"+thePage+"\' falied with exception.", t);
		}finally{
			sel.setTimeout(Long.toString(backupWaitForPageTimeout));
			log.log(MyLevel.ACTION, "Restore waitForPageTimeout ["+sel.getTimeout()+"]");
		}
	}
	
}
