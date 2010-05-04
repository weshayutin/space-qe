package com.redhat.rhn.harness.Space01;

import java.util.Calendar;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.Space01.pages.ErrataSearchPage;
import com.redhat.rhn.harness.Space01.pages.WebList;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Space01.tasks.ErrataManagement;

@Test(groups="tests")
public class ErrataSearch extends com.redhat.rhn.harness.common.Space01.tasks.ErrataSearch {

	RhnHelper rh = new RhnHelper();
	
	@BeforeMethod(groups = { "testplan-SearchErrata" })
	public void test00Prep_ClearSELinux(){
		log.finer("Clearing SELinux logs");
		page_RhnCommon.JustOpen();
		task_TestPrep.command_clear_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME);
	}


	@AfterMethod(groups = { "teardown" })
	public void test_999_TestSELinux(){
		log.finer("Checking SELinux logs");
		assertTrue(task_TestPrep.command_check_SELinux_AuditLog(IRhnBase.SERVER_HOSTNAME));
		
	}
	
	
    

    ErrataSearchPage page = new ErrataSearchPage();
   
    @Test(groups = { "testplan-SearchErrata" })
	public void test_ErrataSearch_Advistory_advistoryNumber()  {
		advancedErrataSearch(IRhnBase.SEARCH_Errata_ErratumAdvisory, "RHSA-2007:0598", true, false, true, null, null, true);
		assertTrue(task_Search.searchForResult("0598", totalInList()));
		assertTrue(task_Search.searchForResult("RHSA-200", totalInList()));
    }

    @Test(groups="testplan-SearchErrata")
	public void test_ErrataSearch_Package_kernel()  {
		advancedErrataSearch(IRhnBase.SEARCH_Errata_PackageName, "kernel", true, true, true, null, null, true);
		assertTrue(task_Search.searchForResult("RHSA-2008:0519", totalInList()));
		assertFalse(task_Search.searchForResult("RHBA-2008:0783", totalInList()));
	}

    @Test(groups="testplan-SearchErrata")
	public void test_ErrataSearch_CVE_cveNumber() {
		String cve = "CVE-2008-2785";
		advancedErrataSearch(IRhnBase.SEARCH_Errata_CVE, cve, true, true, true, null, null, true);
		assertTrue(task_Search.searchForResult("RHSA-2008:0597", totalInList()));
		verifyErrataHasCve(cve);
	}

    @Test(groups="testplan-SearchErrata")
	public void test_ErrataSearch_bySimpleDateRange() {
		Calendar start = getStartDate(2008, 2, 25);
		Calendar end = getEndDate(2008, 6, 26);
		
		task_TestPrep.changePaginationSettings("5", true);
		advancedErrataSearch(IRhnBase.SEARCH_Errata_PackageName, "kernel", true, true, true, start, end, false);
		verifyNotEmpty();
		verifyErrataDate(start, end);
		
		assertTrue(task_Search.searchForResult("RHSA-2008:0508", totalInList()));

	}
	
    @Test(groups="testplan-SearchErrata")
	public void test_ErrataSearch_byErrataPackageName() {
		task_TestPrep.changePaginationSettings("5", true);
		advancedErrataSearch(IRhnBase.SEARCH_Errata_PackageName, "firefox", true, true, false, null, null, false);
		verifyNotEmpty();
		verifyErrataType(true, true, false);
	}
	
    @Test(groups="testplan-SearchErrata")
	public void test_ErrataSearch_byErrataPackageName_bugFix() {
		task_TestPrep.changePaginationSettings("5", true);
		advancedErrataSearch(IRhnBase.SEARCH_Errata_PackageName, "firefox", false, true, true, null, null, false);
		verifyNotEmpty();
		verifyErrataType(false, true, true);
		verifyAtLeastOneType(false, true, true);
		verifyErrataHasPackage("firefox");
	}	
	
    @Test(groups="testplan-SearchErrata")
	public void test_ErrataSearch_byErrataAllFields_http() {
		task_TestPrep.changePaginationSettings("5", true);
		advancedErrataSearch(IRhnBase.SEARCH_Errata_AllFields, "http", true, true, true, null, null, false);
		verifyErrataHasPackage("http");

	}
	
	
    @Test(groups="testplan-SearchErrata")
	public void test_ErrataSearch_byErrataAllFields_dateRange() {

		Calendar start = getStartDate(2008, 6, 24);
		Calendar end = getEndDate(2008, 6, 25);
		
		task_TestPrep.changePaginationSettings("5", true);
		advancedErrataSearch(IRhnBase.SEARCH_Errata_AllFields, "RHSA-2", true, true, true, start, end, false);
		verifyNotEmpty();
		verifyErrataDate(start, end);
		
		assertTrue(task_Search.searchForResult("RHSA-2008:09", totalInList()));

	}
	
	
	
	/*
	 * not sure if this will actually pass in the future, depends on bug 461167
	 */
    @Test(groups="testplan-SearchErrata")
	public void test_ErrataSearch_byErrataAllFields_customErrataByDateRange() {

		ErrataManagement em = new ErrataManagement();
		
		em.DeleteAllErrata(true);
		String synopis = "autoadv-" + Math.random();
		String advisory = synopis;
		String release = "1";
		String topic = "topic";
		String description = "description";
		String solution  = "solution";
		String product = "product";
		
		task_Channels.createCustomChannel("erratasearchtest");
		em.publishNewErrata(synopis, advisory, release, topic, description, solution, product, IRhnBase.ERRATA_TYPE_ENHANCE);
		rh.sleepForSeconds(180);
		advancedErrataSearch(IRhnBase.SEARCH_Errata_AllFields, synopis, true, false, true, null, null, false);
		assertTrue(task_Search.searchForResult(advisory, totalInList()));
		//assertEquals(totalInList(), 1);
	}
	
	/**
	public void test0_ACES() {
		
		ErrataManagement em = new ErrataManagement();
		em.DeleteAllErrata();
		
		String synopis = "advisory-" + Math.random();
		String advisory = synopis;
		String release = "1";
		String topic = "topic";
		String description = "description";
		String solution  = "solution";
		String product = "product";

		em.publishNewErrata(synopis, advisory, release, topic, description, solution, product, em.ERRATA_TYPE_ENHANCE);
		
		
	}
	**/

	private void verifyNotEmpty() {
		WebList list = WebList.getList();
		assertTrue(list.getTotalCount() > 0);
	}
	

	private Calendar getStartDate(int year, int month, int day) {
		Calendar cal = getDate(year, month, day, 1, 0, 0);
		return cal;
	}
	
	private Calendar getEndDate(int year, int month, int day) {
		Calendar cal = getDate(year, month, day, 24, 59, 59);
		return cal;
	}	
	
	private Calendar getDate(int year, int month, int day, int hour, int minute, int second) {
		Calendar start = Calendar.getInstance();
		
		start.set(Calendar.YEAR, year);
		start.set(Calendar.MONTH, month -1);
		start.set(Calendar.DAY_OF_MONTH, day);
		start.set(Calendar.HOUR_OF_DAY, hour-1);
		start.set(Calendar.MINUTE, minute);
		start.set(Calendar.SECOND, second);
		
		return start;
	}
	

	
}

