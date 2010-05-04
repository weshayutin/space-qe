package com.redhat.rhn.harness.common.Sat42.tasks;

import org.testng.Assert;

import com.redhat.rhn.harness.Sat42.pages.ConfigurationPage;
import com.redhat.rhn.harness.Sat42.pages.RhnCommon;
import com.redhat.rhn.harness.Sat42.pages.SatelliteSystemsPage;
import com.redhat.rhn.harness.Sat42.pages.SchedulePage;
import com.redhat.rhn.harness.Sat42.pages.SystemProvisionPage;
import com.redhat.rhn.harness.baseInterface.IPagination;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;

/**
 * General tasks for paginating through lists in RHN
 * 
 * @author whayutin
 * 
 */
public class Pagination extends SeleniumSetup {// implements IPagination {

	protected RhnHelper rh = new RhnHelper();
	
	
	
	
	
	
	
	

	public void systemSearchPagination(boolean createSystems,
			String profileprefix) {
		 if(createSystems) {
			task_TestPrep.registerMultipleProfiles(rh.getTestSystem01Name(),
					profileprefix, 6, HarnessConfiguration.RHN_SAT_REG_CMD,
					false, true, true);
		}
		/*
		 * else rc.OpenAndLogIn();
		 */

		task_TestPrep.changePaginationSettings("5", false);
		page_SatelliteSystems.open();
		// quickSearch.quickSearchSystems(profileprefix, false);
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS, profileprefix,
				false, true);
		//rc.clickButton_PaginateNext();
		task_Search.searchForResult(profileprefix, task_Search.totalInList());
		rh.checkForErrors();
		Assert.assertTrue(rh.isTextPresent(profileprefix + "0006"));
		rh.goBack();
		page_RhnCommon.clickButton_PaginateLast();
		Assert.assertTrue(rh.isTextPresent(profileprefix + "0006"));

	}

	public void packageSearchPagination() {
		// rc.OpenAndLogIn();
		task_TestPrep.changePaginationSettings("5", true);
		page_SatelliteSystems.open();
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "rhn", false,
				true);
		Assert.assertTrue(rh.isTextPresent("rhnlib"));
		page_RhnCommon.clickButton_PaginateNext();
		rh.checkForErrors();
		Assert.assertTrue(rh.isTextPresent("rhn"));
		Assert.assertTrue(rh.isTextPresent("10"));
	}

	public void systemRow(String system) {
		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_AllSystems();
		log.info(page_SatelliteSystems.getText_SystemEntitlement(system));

	}

	public void selectSystemInRow(String system) {
		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_AllSystems();
		page_SatelliteSystems.select_Checkbox(system);
		page_SatelliteSystems.select_Checkbox("wessatellite");
		System.out.println("break");

	}
	
	

}
