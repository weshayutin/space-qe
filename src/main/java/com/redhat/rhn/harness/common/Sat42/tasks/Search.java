package com.redhat.rhn.harness.common.Sat42.tasks;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;

import org.testng.Assert;

import com.redhat.rhn.harness.Sat42.pages.ChannelsPage;
import com.redhat.rhn.harness.Sat42.pages.RhnCommon;
import com.redhat.rhn.harness.Sat42.pages.SatelliteSystemsPage;
import com.redhat.rhn.harness.Space01.pages.DocumentationSearchPage;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.baseInterface.IRhnCommon;
import com.redhat.rhn.harness.baseInterface.ISearch;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;

/**
 * Tasks used from simple, not advanced search
 * 
 * @author whayutin
 * 
 */
public class Search extends SeleniumSetup { // implements ISearch {

	public RhnHelper rh = new RhnHelper();
	
	
	
	public DecimalFormat pattern = new DecimalFormat("###,##0");
	
	
	
	

	public int totalySystemsInList() {
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_Overview();
		page_SatelliteSystems.clickLink_SystemsLeft();
		page_SatelliteSystems.clickLink_AllSystems();
		int i = Integer
				.valueOf(rh
						.getTotalItemsInList("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[3]/tbody/tr/td[2]/strong[3]"));
		return i;

	}


	public int totalInList() {
		Number numItems = 0;
		int numberOfItems = 0;
		String x = IRhnCommon.XPATH_SEARCH_RESULT_TOTAL_SAT_42;
		String y = IRhnCommon.XPATH_SEARCH_RESULT_TOTAL_SAT_42_b;
		// String y = IRhnCommon.XPATH_SEARCH_RESULT_TOTAL2;

		// rhn can have 1,234 items in a list.. we must parse the comma
		try {
			if (rh.isElementPresent(x, false)){
				numItems = pattern.parse(rh.getTotalItemsInList(x));
				// log.info("DEBUG: x = " +numItems);
				numberOfItems = numItems.intValue();
			}
			if(numberOfItems == 0){
				if (rh.isElementPresent(y, false)){
					numItems = pattern.parse(rh.getTotalItemsInList(y));
					// log.info("DEBUG: x = " +numItems);
					numberOfItems = numItems.intValue();
				}
			}

			} catch (ParseException pe) {
				log.log(Level.INFO, "There was a failure parsing the number of items in the list", pe);
				pe.printStackTrace();
			}
			return numberOfItems;
			// numItems = Integer.parseInt(rh.getTotalItemsInList(x));
	}
	
	public boolean searchForResult(String result, int totalInList){
		boolean resultfound = false;
		int numPages;
		int mod;
		int numItemsPerPage = Integer.valueOf(page_YourRhn.entriesPerPage);
		
		
		numPages = totalInList / numItemsPerPage;
		mod = totalInList % numItemsPerPage;
		if(mod > 0)
			numPages++;

		if(rh.isTextPresent("No results found.")){
			log.info("No Results Found");
			resultfound = false;
		}
		else{
			log.info("Paginate through list of "+totalInList+ " items, looking for item: "+ result);
			if(rh.isElementPresent("name=First", true)){
				rh.clickLink("name=First",true);
				log.info("Click Link:  First Page");
			}
			if(rh.isTextPresent(result)){
				resultfound = true;
				}
			else{
				for(int i=1;i< numPages; i++){
					if(rh.isElementPresent("name=Next", true)){
							rh.clickLink("name=Next",true);
							rh.waitForPageToLoad(HarnessConfiguration.TIMEOUT);
							log.info("Click Link: Next Page");
							log.info(" Now on page "+ (i + 1));
					if(rh.isTextPresent(result)){
						resultfound = true;
						i = numPages;
						return resultfound;
						}
					}
				}
				if(!rh.isTextPresent(result))
					log.fine("Search result "+result +" not found");
					resultfound = false;
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
	

	public boolean goToSystem(String system) {
		boolean found = false;
		page_SatelliteSystems.open();
		page_RhnCommon.clickLink_AllSystems();
		if(rh.isTextNotPresent("No systems.")){
			page_RhnCommon.setTxt_FilterBy(system);
			page_RhnCommon.clickButton_Filter_Go();
		}
		if(rh.isTextPresent(system)){
			found = true;
		}
		else{
			found = false;
		}
		return found;
	}

	public void goToSystemSDC(String system) {
		goToSystem(system);
		page_RhnCommon.clickLink_SystemName(system);
		Assert.assertTrue(rh.isTextPresent(system));
	}

	public boolean findSearchResult(String result) {
		// see also rhn help searchForResult
		boolean found;
		found = false;

		if (rh.isTextPresent(result))
			found = true;
		else
			while (rh.isElementPresent("name=Next", true)) {
				if (rh.isTextPresent(result)) {
					found = true;
					page_RhnCommon.clickButton_PaginateNext();
				}
			}

		return found;
	}

	/*
	 * public void quickSearchPackages(String packageName, boolean testResults){
	 * rc.selectQuickSearchPackages(); rc.setQuickSearchText(packageName);
	 * rc.clickQuickSearchSubmit(); if(testResults)
	 * //Assert.assertTrue(rh.isTextPresent(packageName));
	 * rh.searchForResult(packageName, totalInList()); }
	 */

	public void verifyListForSatellite() {
		// this is the check-all box, proving atleast two results are present
		Assert.assertTrue(sel
				.isElementPresent("xpath=//input[@name='checkall']"));
		Assert.assertTrue(sel
				.isElementPresent("xpath=//input[@value='1000010000']"));
		Assert.assertTrue(sel
				.isElementPresent("xpath=//input[@value='1000010002']"));
	}

	public void verifyListForHosted(String system1) {
		String element1 = "\"xpath=//input[@value=\'" + system1 + "\']\"";
		log.info("element should look like the followin \"xpath=//input[@value='1000010002']\"");
		log.info("element =" + element1);
		// Assert.assertTrue(sel.isElementPresent("xpath=//input[@name='checkall']"));
		// Assert.assertTrue(rh.isTextPresent(system1));
		searchForResult(system1, totalInList());
		System.out.println("break");
	}

	public void verifySystemName(String system) {
		// Assert.assertTrue(rh.isTextPresent(system));
		assertTrue(searchForResult(system, totalInList()));
	}

	public void advancedSearch(int quickSearchType, int fieldToSearch,
			String searchValue, boolean openAndLogin, boolean testResults) {
		if (openAndLogin) {
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
		if (testResults)
			Assert.assertTrue(findSearchResult(searchValue));
		// rh.searchForResult(searchValue, totalInList)
	}

	private void advancedSearchPackages(int fieldToSearch, String searchValue) {
		page_SatelliteSystems.setText_SearchFor(searchValue);
		switch (fieldToSearch) {
		case IRhnBase.SEARCH_Package_Name:
			page_SatelliteSystems.select_PackageSearch_Name();
			break;

		case IRhnBase.SEARCH_Package_NameSummary:
			page_SatelliteSystems.select_PackageSearch_NameSummary();
			break;

		default:
			log.info("Invalid Search Type");
			break;
		}
	}

	private void advancedSearchErrataSelect(int fieldToSearch,
			String searchValue) {
		page_SatelliteSystems.setText_SearchFor(searchValue);
		switch (fieldToSearch) {
		case IRhnBase.SEARCH_Errata_Synopsis:
			page_SatelliteSystems.select_FieldToSearch_Synopsis();
			break;

		case IRhnBase.SEARCH_Errata_PackageName:
			page_SatelliteSystems.select_FieldToSearch_ErrataPackageName();
			break;

		case IRhnBase.SEARCH_Errata_ErratumAdvisory:
			page_SatelliteSystems.select_FieldToSearch_ErratumAdvisory();
			break;
		default:
			log.info("Invalid Search Type");
			break;
		}
	}

	protected void advancedSearchSystemSelect(int fieldToSearch, String searchValue){
		page_SatelliteSystems.setText_SearchFor(searchValue);
		switch (fieldToSearch){
		case IRhnBase.SEARCH_FieldToSearch_CpuGreaterThan:
			page_SatelliteSystems.select_FieldToSearch_CpuGreaterThan(); break;

		case IRhnBase.SEARCH_FieldToSearch_CpuLessThan:
			page_SatelliteSystems.select_FieldToSearch_CpuLessThan(); break;

		case IRhnBase.SEARCH_FieldToSearch_RamGreaterThan:
			page_SatelliteSystems.select_FieldToSearch_RamGreaterThan(); break;

		case IRhnBase.SEARCH_FieldToSearch_RamLessThan:
			page_SatelliteSystems.select_FieldToSearch_RamLessThan(); break;

		case IRhnBase.SEARCH_FieldToSearch_CpuModel:
			page_SatelliteSystems.select_FieldToSearch_CpuModel(); break;

		case IRhnBase.SEARCH_FieldToSearch_CustomInfo:
			page_SatelliteSystems.select_FieldToSearch_CustomInfo(); break;

		case IRhnBase.SEARCH_FieldToSearch_DaysSinceFirstRegistered:
			page_SatelliteSystems.select_FieldToSearch_DaysSinceFirstRegistered(); break;

		case IRhnBase.SEARCH_FieldToSearch_DaysSinceLastCheckin:
			page_SatelliteSystems.select_FieldToSearch_DaysSinceLastCheckin(); break;

		case IRhnBase.SEARCH_FieldToSearch_HD_Description:
			page_SatelliteSystems.select_FieldToSearch_HD_Description(); break;

		case IRhnBase.SEARCH_FieldToSearch_HD_DeviceID:
			page_SatelliteSystems.select_FieldToSearch_HD_DeviceID(); break;

		case IRhnBase.SEARCH_FieldToSearch_HD_Driver:
			page_SatelliteSystems.select_FieldToSearch_HD_Driver(); break;

		case IRhnBase.SEARCH_FieldToSearch_ID:
			page_SatelliteSystems.select_FieldToSearch_ID(); break;

		case IRhnBase.SEARCH_FieldToSearch_NameDescription:
			page_SatelliteSystems.select_FieldToSearch_NameDescription(); break;

		case IRhnBase.SEARCH_FieldToSearch_NETWORK_Hostname:
			page_SatelliteSystems.select_FieldToSearch_NetworkInfo_Hostname(); break;

		case IRhnBase.SEARCH_FieldToSearch_NETWORK_IP:
			page_SatelliteSystems.select_FieldToSearch_NetworkInfo_IP(); break;

		case IRhnBase.SEARCH_FieldToSearch_SystemSoftware_Installed:
			page_SatelliteSystems.select_FieldToSearch_Packages_Installed(); break;

		case IRhnBase.SEARCH_FieldToSearch_SystemSoftware_Needed:
			page_SatelliteSystems.select_FieldToSearch_Packages_Needed(); break;
		
		case IRhnBase.SEARCH_FieldToSearch_DMI_System:
			page_SatelliteSystems.select_FieldToSearch_DMI_System();break;
		
		case IRhnBase.SEARCH_FieldToSearch_DMI_Bios:
			page_SatelliteSystems.select_FieldToSearch_DMI_Bios();break;
			
		case IRhnBase.SEARCH_FieldToSearch_DMI_AssetTag:
			page_SatelliteSystems.select_FieldToSearch_DMI_AssetTag();break;
			
		case IRhnBase.SEARCH_FieldToSearch_Location_Address:
			page_SatelliteSystems.select_FieldToSearch_Location_Address();break;
		
		case IRhnBase.SEARCH_FieldToSearch_Location_Building:
			page_SatelliteSystems.select_FieldToSearch_Location_Building();break;
			
		case IRhnBase.SEARCH_FieldToSearch_Location_Rack:
			page_SatelliteSystems.select_FieldToSearch_Location_Rack();break;
			
		case IRhnBase.SEARCH_FieldToSearch_Location_Room:
			page_SatelliteSystems.select_FieldToSearch_Location_Room();break;
		
		case IRhnBase.SEARCH_FieldToSearch_Details_RunningKernel:
			page_SatelliteSystems.select_FieldToSearch_Details_Running_Kernel();break;
			
		case IRhnBase.SEARCH_FieldToSearch_CpuNumberGreaterThan:
			page_SatelliteSystems.select_FieldToSearch_CpuGreaterThan();break;
		
		case IRhnBase.SEARCH_FieldToSearch_CpuNumberLessThan:
			page_SatelliteSystems.select_FieldToSearch_CpuLessThan();break;
			
		case IRhnBase.SEARCH_FieldToSearch_Packages_Installed:
			page_SatelliteSystems.select_FieldToSearch_Packages_Installed();break;
			
		case IRhnBase.SEARCH_FieldToSearch_Packages_Needed:
			page_SatelliteSystems.select_FieldToSearch_Packages_Needed();break;
		
		case IRhnBase.SEARCH_FieldToSearch_SnapShotTag:
			page_SatelliteSystems.select_FieldToSearch_SnapShotTag();break;
			
		default:
			throw new SeleniumException("search type not implemented");

		}

	}

	public String getSystemID(String system, boolean hosted,boolean searchForSystem) {
		String id = null;
		if (searchForSystem) {
			this.goToSystem(system);
		}
		if(hosted){
		Assert.assertTrue(rh.isTextPresent(system));
		id = page_SatelliteSystems.getSatSystem_ID();
		page_SatelliteSystems.open();
		log.info("system id = " + id);
		}
		if(!hosted){
			Assert.assertTrue(rh.isTextPresent(system));
			id = page_SatelliteSystems.getSatSystem_ID();
			log.info("system id = " + id);
			page_SatelliteSystems.open();
			
		}
		return id;
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

	public String getSystemInfo(String system, int info, boolean searchForSystem) {
		String detail = null;

		if (searchForSystem) {
			goToSystem(system);
			Assert.assertTrue(rh.isTextPresent(system));
			rh.clickSystemProfileLink(system);
		}

		if (info == IRhnBase.SYSTEM_DETAIL_RHN_SYSTEM_ID) {
			detail = page_SatelliteSystems.getSatSystem_ID();
			log.info("system id = " + detail);
		}
		if (info == IRhnBase.SYSTEM_DETAIL_HOSTNAME) {
			detail = page_SatelliteSystems.getSatSystem_Hostname();
			log.info("system hostname = " + detail);
		}
		if (info == IRhnBase.SYSTEM_DETAIL_KERNEL) {
			detail = page_SatelliteSystems.getSatSystem_Kernel();
			log.info("system kernel = " + detail);
		}
		if (info == IRhnBase.SYSTEM_DETAIL_IP_ADDRESS) {
			detail = page_SatelliteSystems.getSatSystem_IpAddress();
			log.info("system ip address = " + detail);
		}
		if (info == IRhnBase.SYSTEM_DETAIL_RHN_TIME_REGISTERED) {
			detail = page_SatelliteSystems.getSatSystem_Registered();
			log.info("system registered at = " + detail);
		}
		if (info == IRhnBase.SYSTEM_DETAIL_RHN_TIME_CHECKED_IN) {
			detail = page_SatelliteSystems.getSatSystem_CheckedIn();
			log.info("system checked in at = " + detail);
		}
		if (info == IRhnBase.SYSTEM_DETAIL_RHN_TIME_LAST_BOOTED) {
			detail = page_SatelliteSystems.getSatSystem_CheckedIn();
			log.info("system last booted at = " + detail);
		}
		if (info == IRhnBase.SYSTEM_DETAIL_LOCK_STATUS) {
			detail = page_SatelliteSystems.getSatSystem_LockStatus();
			log.info("system lock status = " + detail);
		}
		if (info == IRhnBase.SYSTEM_DETAIL_ENTITLEMENTS) {
			detail = page_SatelliteSystems.getSatSystem_Entitlements();
			log.info("system entitlements = " + detail);
		}
		if (info == IRhnBase.SYSTEM_DETAIL_NOTIFICATIONS) {
			detail = page_SatelliteSystems.getSatSystem_Notifications();
			log.info("system notifications = " + detail);
		}
		if (info == IRhnBase.SYSTEM_DETAIL_AUTO_ERRATA_UPDATE) {
			detail = page_SatelliteSystems.getSatSystem_AutoErrataUpdate();
			log.info("system auto errata update = " + detail);
		}
		if (info == IRhnBase.SYSTEM_DETAIL_SYSTEM_NAME) {
			detail = page_SatelliteSystems.getSatSystem_BUG_SystemName();
			log.info("system system name = " + detail);
		}
		if (info == IRhnBase.SYSTEM_DETAIL_DESCRIPTION) {
			detail = page_SatelliteSystems.getSatSystem_Description();
			log.info("system detail description = " + detail);
		}
		if (info == IRhnBase.SYSTEM_DETAIL_LOCATION) {
			detail = page_SatelliteSystems.getSatSystem_Location();
			log.info("system location = " + detail);
		}

		return detail;
	}

	//@Deprecated
	private void confirmSearchResultForSystem(String details, String system) {
		boolean pass = false;
		if ((details == "unknown") && (rh.isTextPresent("No systems.")))
			log.info("details =" + details);
		pass = false;

		if (rh.isTextPresent(system))
			log.info("details =" + details);
		pass = true;

		if (!pass)
			throw new AssertionError("the search for " + details
					+ " failed to return the correct response");
	}

	public void printSystemDetailsSat(String system) {
		task_RhnBase.OpenAndLogIn();
		page_RhnCommon.clickSideMenuSystems();

		page_SatelliteSystems.clickLink_AdvancedSearch();
		page_SatelliteSystems.setText_SearchFor(system);
		page_SatelliteSystems.clickButton_Search();
		Assert.assertTrue(rh.isTextPresent(system));
		rh.clickLink("link=" + system,true);
		// syspage.clickLink_Details();
		log.info("ID =" + page_SatelliteSystems.getSatSystem_ID());
		log.info("Hostname =" + page_SatelliteSystems.getSatSystem_Hostname());
		log.info("IP =" + page_SatelliteSystems.getSatSystem_IpAddress());
		log.info("Kernel =" + page_SatelliteSystems.getSatSystem_Kernel());
		log.info("Registered =" + page_SatelliteSystems.getSatSystem_Registered());
		log.info("Checked In =" + page_SatelliteSystems.getSatSystem_CheckedIn());

	}

	// Methods for Documentation Search
	public void DisplayDocumentationSearchPage() {
		page_DocSearch.click_HelpLink();
		page_DocSearch.click_DocSearchLink();
		Assert.assertTrue(rh.isTextPresent("Document Search"));
		Assert.assertTrue(rh.isTextPresent("Specify your search criteria below."));
	}
	
	public void DisplayDocumentationSearchPage_TasksDocumentation() {
		page_DocSearch.click_HelpLink();
		page_DocSearch.click_DocSearchLink();
		Assert.assertTrue(rh.isTextPresent("Document Search"));
		Assert.assertTrue(rh.isTextPresent("Specify your search criteria below."));
	}
	

}
