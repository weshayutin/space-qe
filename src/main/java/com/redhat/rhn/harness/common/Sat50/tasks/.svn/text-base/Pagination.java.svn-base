package com.redhat.rhn.harness.common.Sat50.tasks;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IPagination;
import com.redhat.rhn.harness.baseInterface.IRhnBase;

public class Pagination extends com.redhat.rhn.harness.common.Sat42.tasks.Pagination implements IPagination{

	

	public void packageSearchPagination(){
		//rc.OpenAndLogIn();
		task_TestPrep.changePaginationSettings("5", true);
		page_SatelliteSystems.open();
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "rhn", false, true);
		Assert.assertTrue(rh.isTextPresent("rhn-applet"));
		page_RhnCommon.clickButton_PaginateNext();
		assertTrue(rh.checkForErrors() == IRhnBase.ERROR_NO_ERROR_FOUND);
		Assert.assertTrue(rh.isTextPresent("rhn"));
	}

}
