package com.redhat.rhn.harness.common.Sat51.tasks;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IPagination;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;

public class Pagination extends com.redhat.rhn.harness.common.Sat50.tasks.Pagination implements IPagination{


	
	public void packageSearchPagination(){
		//rc.OpenAndLogIn();
		task_TestPrep.changePaginationSettings("5", false);
		page_SatelliteSystems.open();
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "rhn", false, true);
		task_Search.searchForResult("rhn-kickstart", task_Search.totalInList());
		rh.checkForErrors();
		Assert.assertTrue(rh.isTextPresent("32"));
		
	}
	
	public void systemSearchPagination(boolean createSystems,
			String profileprefix) {
		 if(createSystems) {
			task_TestPrep.registerMultipleProfiles(rh.getTestSystem01Name(),
					profileprefix, 6, HarnessConfiguration.RHN_SAT_REG_CMD,
					false, true, false);
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
		task_Search.searchForResult(profileprefix+ "0006", task_Search.totalInList());
		rh.checkForErrors();
		Assert.assertTrue(rh.isTextPresent("006"));
		rh.goBack();
		page_SatelliteSystems.clickButton_PaginateLast();
		Assert.assertTrue(rh.isTextPresent("006"));
		rh.goBack();
		page_SatelliteSystems.clickButton_PaginateFirst();
		rh.checkForErrors();
		Assert.assertTrue(rh.isTextPresent("001"));

	}
	
	
	

}
