package com.redhat.rhn.harness.common.Space01.tasks;

import java.util.Calendar;

import com.redhat.rhn.harness.Space01.pages.ErrataDetailsPage;
import com.redhat.rhn.harness.Space01.pages.ErrataSearchPage;


public class ErrataSearch extends Search {

	
	private int SEARCH_RESULT_COLUMN = ErrataSearchPage.ERRATA_SEARCH_RESULT_COLUMN; 
	
	
	public void advancedErrataSearch( int fieldToSearch, String searchValue, 
			boolean securityErrata, boolean bugFixErrata, boolean enhancementErrata, Calendar start, Calendar end, boolean openAndLogin) {
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		
		page_RhnCommon.clickErrata();
        page_SatelliteSystems.clickLink_AdvancedSearch();
		
		esp.setTxt_SearchText(searchValue);
		esp.checkBox_BugFix(bugFixErrata);
		esp.checkBox_Security(securityErrata);
		esp.checkBox_Enhancement(enhancementErrata);
		esp.checkBox_DateSearch(start != null || end != null);
		esp.select_StartDate(start);
		esp.select_EndDate(end);
		esp.select_SearchFor(fieldToSearch);
		esp.clickButton_Search();
		
		
	}
	
	public int verifyErrataDate(Calendar start, Calendar end) {
		
		ErrataDetailsPage edp = new ErrataDetailsPage();
		
		ErrataDateVerifyVisitor visitor = new ErrataDateVerifyVisitor(SEARCH_RESULT_COLUMN);
		visitor.setEnd(end);
		visitor.setStart(start);
		return visitEachItem(visitor);
		
	}
	
	public int verifyErrataType(boolean security, boolean bugFix, boolean enhancement) {
		ErrataTypeVerifyVisitor visitor = new ErrataTypeVerifyVisitor(SEARCH_RESULT_COLUMN, security, bugFix, enhancement);
		return visitEachItem(visitor);		
	}
	
	public void verifyAtLeastOneType(boolean security, boolean bugFix, boolean enhancement) {
		if (security) {
			AtLeastOneErrataTypeVisitor visitor = new AtLeastOneErrataTypeVisitor(SEARCH_RESULT_COLUMN, AtLeastOneErrataTypeVisitor.SECURITY);
			visitor.setAtLeastOneTrue(true);
			visitEachItem(visitor);
		}
		if (bugFix) {
			AtLeastOneErrataTypeVisitor visitor = new AtLeastOneErrataTypeVisitor(SEARCH_RESULT_COLUMN, AtLeastOneErrataTypeVisitor.BUG_FIX);
			visitor.setAtLeastOneTrue(true);
			visitEachItem(visitor);
		}
		if (enhancement) {
			AtLeastOneErrataTypeVisitor visitor = new AtLeastOneErrataTypeVisitor(SEARCH_RESULT_COLUMN, AtLeastOneErrataTypeVisitor.ENHANCE);
			visitor.setAtLeastOneTrue(true);
			visitEachItem(visitor);
		}
	}
	
	public int verifyErrataHasPackage(String packageName) {
		ErrataPackageVerifyVisitor visitor = new ErrataPackageVerifyVisitor(SEARCH_RESULT_COLUMN, packageName);
		return visitEachItem(visitor);
	}
	
	public int verifyErrataHasCve(String cve) {
		ErrataCVEVisitor visitor = new ErrataCVEVisitor(SEARCH_RESULT_COLUMN, cve);
		return visitEachItem(visitor);
	}
	
	
	
	
}
