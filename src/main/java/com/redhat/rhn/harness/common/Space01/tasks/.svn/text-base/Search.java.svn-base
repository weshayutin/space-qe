package com.redhat.rhn.harness.common.Space01.tasks;

import org.testng.Assert;

import com.redhat.rhn.harness.Space01.pages.ErrataSearchPage;
import com.redhat.rhn.harness.Space01.pages.WebList;
import com.redhat.rhn.harness.baseInterface.IRhnBase;

public class Search extends com.redhat.rhn.harness.common.Sat51.tasks.Search {//implements ISearch{

	
	ErrataSearchPage esp = new ErrataSearchPage();
	


	public void advancedSearch(int quickSearchType, int fieldToSearch, String searchValue, boolean openAndLogin, boolean testResults){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		switch (quickSearchType) {
		case IRhnBase.ADVANCED_SEARCH_SYSTEMS:
			page_RhnCommon.clickSideMenuSystems();
	        page_SatelliteSystems.clickLink_AdvancedSearch();
	        advancedSearchSystemSelect(fieldToSearch, searchValue);
	        page_SatelliteSystems.clickButton_Search();
	        break;
		case IRhnBase.ADVANCED_SEARCH_PACKAGES:
			page_Channels.open();
			page_Channels.click_PackageSearch();
			advancedSearchPackages(fieldToSearch, searchValue);
			page_SatelliteSystems.clickButton_Search();
			break;
		case IRhnBase.ADVANCED_SEARCH_ERRATA:
			page_RhnCommon.clickErrata();
	        page_SatelliteSystems.clickLink_AdvancedSearch();
	        ErrataSearch es = new ErrataSearch();
	        es.advancedErrataSearch(fieldToSearch, searchValue, true, true, true, null, null, false);
	        break;
		default:
			log.info("Invalid Search Type");
		break;
		}
		if(testResults){
			Assert.assertTrue(findSearchResult(searchValue));
		}
	}
	
	

	/**
	 * Visits each item and executes the visitor. 
	 * If visitor.isAtLeastOneTrue() returns true, then it will ensure
	 * 	that execute() returns true at least once.
	 * @param visitor
	 * @return 
	 */
	public int visitEachItem(PageVisitor visitor) {
		
		boolean atLeastOneTrue = false;
		
		int column = visitor.getColumn();
		WebList list = WebList.getList();
		int total = 0;
		list.firstPage();
		while (true) {
			for (int i = 1; i <= list.getPageSize() && list.getPageSize()*(list.getCurrentPage()-1) + i <= list.getTotalCount(); i++) {
				log.info("Visiting item " + total + " of " + list.getTotalCount());
				total++;
				list.clickCellLink(i, column);
				boolean returned = visitor.execute();
				if (visitor.isTrueAtLeastOnce() && returned) {
					atLeastOneTrue = true;
				}
				rh.goBack();
			}
			log.info("current page = "+ list.getCurrentPage());
			log.info("total page = "+ list.getTotalPages() );
			if (list.getCurrentPage() == list.getTotalPages()) {
				break;
			}
			
			list.nextPage();
		}
		assertEquals(total, list.getTotalCount());
		list.firstPage();
		
		if (visitor.isTrueAtLeastOnce()) {
			assertTrue(atLeastOneTrue);
		}
		
		return total;
	}
	
	 public String getSystemID(String system, boolean hosted,boolean searchForSystem) {
			String id = null;
			if (searchForSystem) {
				goToSystem(system);
			}
			if(hosted){
			Assert.assertTrue(rh.isTextPresent(system));
			rh.clickSystemProfileLink(system);
			id = page_SatelliteSystems.getSatSystem_ID();
			page_SatelliteSystems.open();
			log.info("system id = " + id);
			}
			if(!hosted){
				Assert.assertTrue(rh.isTextPresent(system));
				rh.clickSystemProfileLink(system);
				id = page_SatelliteSystems.getSatSystem_ID();
				log.info("system id = " + id);
				page_SatelliteSystems.open();
				
			}
			return id;
		} 
	 

	 
	
	
/*	 final String XPATH_PACKAGE_SEARCH_RESULT_TOTAL="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/div/table/tbody/tr/td[2]/strong[3]";
	 final String XPATH_ERRATA_SEARCH_RESULT_TOTAL="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table/tbody/tr/td[2]/strong[3]";
	 final String XPATH_SYSTEM_SEARCH_RESULT_TOTAL ="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table/tbody/tr/td[2]/strong[3]";
	 final String XPATH_PAGINATE_PACKAGE_NEXT = "xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/div/table/tbody/tr/td[2]/input";*/



}
