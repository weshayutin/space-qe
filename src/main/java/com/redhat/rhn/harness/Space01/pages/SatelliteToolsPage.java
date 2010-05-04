package com.redhat.rhn.harness.Space01.pages;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class SatelliteToolsPage extends com.redhat.rhn.harness.Sat51.pages.SatelliteToolsPage{
	
	RhnHelper rh = new RhnHelper();
	
	public String getLocation() {
		 return "https://"+IRhnBase.SERVER_HOSTNAME + "/rhn/admin/multiorg/Organizations.do";
	}
	
	
	public void clickLink_Admin(){
		rh.clickLink("xpath=//a[contains(@href,'/rhn/admin/multiorg/Organizations.do')]","Admin",true);
	}
	
	public void clickLink_Trusts(){
		rh.clickLink("xpath=//a[contains(@href,'/rhn/admin/multiorg/OrgTrusts.do')]","Trusts",true);
	}
	
	 public void checkBox_Trust(boolean check){
			rh.checkRadioButton("xpath=//input[@type='checkbox']",check);
	 }
	 
	 public void clickButton_ModifyTrusts(){
	        rh.clickButton("xpath=//input[@type='submit' and @name='confirm']",true);
	 }
	
	 public void clickLink_SatelliteConfiguration(){
		 	if(rh.isElementPresent("link=RHN Satellite Configuration", true))
		 		rh.clickLink("link=RHN Satellite Configuration",true);
		 	else if(rh.isElementPresent("link=Spacewalk Configuration", true))
		 		rh.clickLink("link=Spacewalk Configuration",true);
		}
	
		public void setTxt_Organization_SoftwareChannelEntitlement(String Channel, String number){
			int  iTotalSoftwareEntitlements = new Integer(sel.getText("id=list_total")).intValue();
			int cntPerPage = new Integer(sel.getText("id=list_max")).intValue();			
			int pagesCount = iTotalSoftwareEntitlements/cntPerPage;
			if(iTotalSoftwareEntitlements%cntPerPage>0){
				pagesCount++;
			}
			for(int i=0;i<pagesCount;i++){
				for (int j=1; j<(cntPerPage+1); j++){
					if(sel.getText("xpath=//form/table/tbody/tr["+j+"]/td/a").equalsIgnoreCase(Channel)){
						sel.setText("xpath=//form/table/tbody/tr["+j+"]/td[4]/input", number);
						return; // did the job - return~
					}
				}
				sel.clickAndWait("xpath=//input[@type='image' and @alt='Next Page']");
			}
		}
	
	
		
		
		
}
