package com.redhat.rhn.harness.common.Sat51.tasks;

import java.text.ParseException;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.baseInterface.ISearch;
import com.thoughtworks.selenium.SeleniumException;

public class Search extends com.redhat.rhn.harness.common.Sat50.tasks.Search implements ISearch{

	

	 final String XPATH_PACKAGE_SEARCH_RESULT_TOTAL="xpath=//tr/td[2]/form[2]/div/table/tbody/tr/td[2]/strong[3]";
	 final String XPATH_ERRATA_SEARCH_RESULT_TOTAL="xpath=//tr/td[2]/form[2]/table/tbody/tr/td[2]/strong[3]";
	 final String XPATH_SYSTEM_SEARCH_RESULT_TOTAL ="xpath=//tr/td[2]/form[2]/table/tbody/tr/td[2]/strong[3]";
	 final String XPATH_SYSTEM_SEARCH_RESULT_TOTAL_SECOND = "xpath=//tr/td[2]/form[2]/div/table/tbody/tr/td[2]/strong[3]";									
	 final String XPATH_PAGINATE_PACKAGE_NEXT = "xpath=//tr/td[2]/form[2]/div/table/tbody/tr/td[2]/input";

	
	 public boolean searchForResult(String result, int totalInList){
		    boolean resultfound = false;
			int numPages;
			int mod;
			int numItemsPerPage = Integer.valueOf(page_YourRhn.entriesPerPage);
			numPages = totalInList / numItemsPerPage;
			mod = totalInList % numItemsPerPage;
			
			if(totalInList == 1){
				log.info("INFO: only one item found");
				resultfound = true;
			}
			
			if(mod > 0)
				numPages++;

			if(rh.isTextPresent("No results found.",false)){
				log.info("No Results Found");
				resultfound = false;
			}
			else{
				log.info("Paginate through list of "+totalInList+ " items, looking for item: "+ result);
				if(rh.isElementPresent("name=First", false)){
					rh.clickLink("name=First",true);
					log.info("Click Link:  First Page");
				}
				if(rh.isElementPresent("alt=First Page", false)){
					rh.clickLink("alt=First Page",true);
					log.info("Click Link:  First Page");
				}
				if(rh.isTextPresent(result,false)){
					log.info("Search result "+result +" found");
					resultfound = true;
					}
				else{
					for(int i=1;i< numPages; i++){
						if(rh.isElementPresent("name=Next", false)){
								rh.clickLink("name=Next",true);
						}
						if(rh.isElementPresent("alt=Next Page", false)){
									rh.clickLink("alt=Next Page",true);
						}		
						log.info(" Now on page "+ (i + 1));
						if(rh.isTextPresent(result,false)){
							log.info("Search result "+result +" found");
							resultfound = true;
							i = numPages;
							}
						else{
							log.info("Search result "+result +" not found");
							}	
						}
					if(!rh.isTextPresent(result,false)){
						log.info("Search result "+result +" not found");
						resultfound = false;
						}
					}
				}
				return resultfound;
	 		}
			
	 
	 
	public boolean quickSearch(int quickSearchType, String searchValue, boolean openAndLogin, boolean testResults){
		boolean found=false;
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		switch (quickSearchType) {
		case IRhnBase.QUICK_SEARCH_SYSTEMS:
			page_RhnCommon.selectQuickSearchSystems(); break;
		case IRhnBase.QUICK_SEARCH_PACKAGES:
			page_RhnCommon.selectQuickSearchPackages(); break;
		case IRhnBase.QUICK_SEARCH_ERRATA:
			page_RhnCommon.selectQuickSearchErrata(); break;
		default:
			log.info("Invalid Search Type");
		break;
		}
		page_RhnCommon.setQuickSearchText(searchValue);
		page_RhnCommon.clickQuickSearchSubmit();

		if(testResults){
        	 found = searchForResult(searchValue, totalInList());
		}
		else{
			found = false;
		}
		return found;
  }

	public int totalInList() {
		Number numItems = 0;
		int numberOfItems = 0;
		String x = XPATH_PACKAGE_SEARCH_RESULT_TOTAL;
		String y = XPATH_ERRATA_SEARCH_RESULT_TOTAL;
		String z = XPATH_SYSTEM_SEARCH_RESULT_TOTAL_SECOND;
		// rhn can have 1,234 items in a list.. we must parse the comma
		try {
			if (rh.isElementPresent(x, false)){
				numItems = pattern.parse(rh.getTotalItemsInList(x));
				//log.info("DEBUG: x = " +numItems);
				numberOfItems = numItems.intValue();
			}
			if (rh.isElementPresent(y, false)){
				numItems = pattern.parse(rh.getTotalItemsInList(y));
				numberOfItems = numItems.intValue();
				//log.info("DEBUG: y = " +numberOfItems);
			}
			if (rh.isElementPresent(z, false)){
				numItems = pattern.parse(rh.getTotalItemsInList(z));
				numberOfItems = numItems.intValue();
			}			
			if (rh.isTextPresent("delete system")){
				numberOfItems = 1;
			}
		} catch (ParseException pe) {
			log.info("There was a failure parsing the number of items in the list");
			pe.printStackTrace();
		}
		return numberOfItems;
	}



	public void advancedSearch(int quickSearchType, int fieldToSearch, String searchValue, boolean openAndLogin, boolean testResults){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		switch (quickSearchType) {
		case IRhnBase.ADVANCED_SEARCH_SYSTEMS:
			page_RhnCommon.clickSideMenuSystems();
	        page_SatelliteSystems.clickLink_AdvancedSearch();
	        advancedSearchSystemSelect(fieldToSearch, searchValue);
	        break;
		case IRhnBase.ADVANCED_SEARCH_PACKAGES:
			page_RhnCommon.clickChannels();
			page_Channels.click_PackageSearch();
			advancedSearchPackages(fieldToSearch, searchValue);
			break;
		case IRhnBase.ADVANCED_SEARCH_ERRATA:
			page_RhnCommon.clickErrata();
	        page_SatelliteSystems.clickLink_AdvancedSearch();
	        advancedSearchErrataSelect(fieldToSearch, searchValue);
	        break;
		default:
			log.info("Invalid Search Type");
		break;
		}
		page_SatelliteSystems.clickButton_Search();
		if(testResults)
			Assert.assertTrue(findSearchResult(searchValue));
			//rh.searchForResult(searchValue, totalInList)
	}

	


	


	protected void advancedSearchErrataSelect(int fieldToSearch, String searchValue){
		page_SatelliteSystems.setText_SearchFor(searchValue);
		switch(fieldToSearch){
		case IRhnBase.SEARCH_Errata_Synopsis:
			page_SatelliteSystems.select_FieldToSearch_Synopsis();break;

		case IRhnBase.SEARCH_Errata_PackageName:
			page_SatelliteSystems.select_FieldToSearch_ErrataPackageName();break;

		case IRhnBase.SEARCH_Errata_ErratumAdvisory:
			page_SatelliteSystems.select_FieldToSearch_ErratumAdvisory();break;
		default:
			log.info("Invalid Search Type");
		break;
		}
	}

	protected void advancedSearchPackages(int fieldToSearch, String searchValue){
		page_SatelliteSystems.setText_SearchFor(searchValue);
		switch(fieldToSearch){
		case IRhnBase.SEARCH_Package_Name:
			page_SatelliteSystems.select_FieldToSearchPackage_NameOnly();break;

		case IRhnBase.SEARCH_Package_NameSummary:
			page_SatelliteSystems.select_FieldToSearchPackage_NameAndSummary();break;

		default:
			log.info("Invalid Search Type");break;
		}
	}
	
	
	public String getDetails_System_ID() {
		return page_SatelliteSystems.getSatSystem_ID();
	}

	public String getDetails_System_Hostname() {
		return page_SatelliteSystems.getSatSystem_Hostname();
	}

	public String getDetails_System_IPAddress() {
		return page_SatelliteSystems.getSatSystem_IpAddress();
	}

	public String getDetails_System_KernelLevel() {
		return page_SatelliteSystems.getSatSystem_Kernel();
	}


}
