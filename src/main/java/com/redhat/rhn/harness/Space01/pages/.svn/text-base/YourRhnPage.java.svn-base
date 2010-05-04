package com.redhat.rhn.harness.Space01.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class YourRhnPage extends com.redhat.rhn.harness.Sat50.pages.YourRhnPage {
	static RhnHelper rh = new RhnHelper();

    public String getLocation(){
        return HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_YOUR_RHN_PAGE;
    }

    public void open(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_YOUR_RHN_PAGE,true, "Your Rhn");
    }
    
    
    public String getBaseEntitlement_Mang_Total(){
    	try{
    	return sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[5]/tbody/tr[2]/td/strong[3]");
    	}
    	catch(Exception e){
    		return sel.getText("xpath=//tbody/tr/td[2]/form/table/tbody/tr[2]/td/strong[3]");
    								 //html/body/div/div[5]/table/tbody/tr/td[2]/form/table/tbody/tr[2]/td/strong[3]
    	}
    }
    
    public void clickLink_OrganizationalTrusts(){
		rh.clickLink("xpath=//a[contains(@href,'/rhn/multiorg/Organizations.do')]","Organizational Trusts",true);
	}
    
    public void clickLink_OrganizationalTrusts_Details(){
		rh.clickLink("xpath=//a[contains(@href,'/rhn/multiorg/OrgTrustDetails.do')]","Details",true);
	}
    
    public void clickLink_OrganizationalTrusts_Channels_Provided(){
		rh.clickLink("xpath=//a[contains(@href,'/rhn/multiorg/channels/Provided.do')]","Channels Provided",true);
	}
    
    public void clickLink_OrganizationalTrusts_Channels_Consumed(){
		rh.clickLink("xpath=//a[contains(@href,'/rhn/multiorg/channels/Consumed.do')]","Channels Consumed",true);
	}


}
