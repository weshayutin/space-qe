package com.redhat.rhn.harness.common.Sat50.tasks;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.testng.Assert;

import com.redhat.rhn.harness.Sat50.pages.ChannelsPage;
import com.redhat.rhn.harness.Sat50.pages.RhnCommon;
import com.redhat.rhn.harness.Sat50.pages.SatelliteSystemsPage;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.baseInterface.ISearch;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.thoughtworks.selenium.SeleniumException;

public class Search extends com.redhat.rhn.harness.common.Sat42.tasks.Search implements ISearch{



	 final String XPATH_PACKAGE_SEARCH_RESULT_TOTAL="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table[4]/tbody/tr/td[2]/strong[3]";
	 final String XPATH_ERRATA_SEARCH_RESULT_TOTAL="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table/tbody/tr/td[2]/strong[3]";
	 final String XPATH_SYSTEM_SEARCH_RESULT_TOTAL ="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table/tbody/tr/td[2]/strong[3]";


	 public String getSystemID(String system, boolean hosted,boolean searchForSystem) {
			String id = null;
			if (searchForSystem) {
				goToSystem(system);
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
	 
	 
	/*public boolean searchForResult(String result, int totalInList){
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
*/

	  public int totalInList() {
			Number numItems = 0;
			int numberOfItems = 0;
			String x = XPATH_PACKAGE_SEARCH_RESULT_TOTAL;
			String y = XPATH_ERRATA_SEARCH_RESULT_TOTAL;

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
				if (rh.isTextPresent("delete system")){
					numberOfItems = 1;
				}
			} catch (ParseException pe) {
				log.info("There was a failure parsing the number of items in the list");
				pe.printStackTrace();
			}
			return numberOfItems;
			// numItems = Integer.parseInt(rh.getTotalItemsInList(x));
		}

	 

}
