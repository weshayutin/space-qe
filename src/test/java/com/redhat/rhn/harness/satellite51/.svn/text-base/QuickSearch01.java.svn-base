package com.redhat.rhn.harness.satellite51;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;

@Test(groups="tests")
public class QuickSearch01 extends com.redhat.rhn.harness.satellite50.QuickSearch01{
	

	@Test(groups="testplan-SearchSystem")
	public void test15_QuickSearch_Systems_bufferOverRun(){
		task_Search.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS, "$%^&*$%^&*$%^&*$%^&*$%^&*$%^&*$%^&*$%^&*$%^&*", true, false);
		Assert.assertTrue(rh.isTextPresent("No results found."));
	}

	

	}

