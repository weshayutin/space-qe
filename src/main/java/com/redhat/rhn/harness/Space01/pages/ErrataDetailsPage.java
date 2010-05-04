package com.redhat.rhn.harness.Space01.pages;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;


public class ErrataDetailsPage extends  RhnPage {

	RhnHelper rh = new RhnHelper();
	
	
	   public String getLocation(){
	        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_ERRATA_DETAILS;
	    }

	   public String getIssueDate() {
		   return rh.getText("xpath=//tr/td[2]/table/tbody/tr/td");
	   }	   
	   
	   public boolean isBugFixErrata() {
		   return rh.isElementPresent("xpath=//img[@alt='Bug Fix']", true);
	   }
	   
	   public boolean isSecurityErrata() {
		   return rh.isElementPresent("xpath=//img[@alt='Security']", true);
	   }
	   
	   public boolean isEnhancementErrata() {
		   return rh.isElementPresent("xpath=//img[@alt='Enhancement']", true);
	   }
	   
	   public boolean hasPackage(String packageName) {
		   return rh.isTextPresent(packageName);
	   }
	   
	   public boolean hasCVE(String cve) {
		   return rh.isTextPresent(cve);
	   }	   
	   
	   public void clickPackageLink() {
		   rh.clickLink("xpath=//html/body/div[2]/div/table/tbody/tr/td[2]/div[2]/ul/li[2]/a", true);
	   }
	   
}
