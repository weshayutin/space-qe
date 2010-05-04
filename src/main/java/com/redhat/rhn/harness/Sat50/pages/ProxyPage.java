package com.redhat.rhn.harness.Sat50.pages;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.RhnHelper;

public class ProxyPage extends com.redhat.rhn.harness.Sat42.pages.ProxyPage{

	RhnHelper rh = new RhnHelper();
	
	
	public String fieldError1="Please respond to all required fields.";
	
	public String getLocation(){
        return "http://google.com";
    }

	public void clickLink_SDCProxy(){
        //rh.clickLink("link=Proxy",true);
		rh.clickLink("xpath=//tr/td[2]/div[2]/div/div[2]/ul/li[4]/a",true);
    }
	
	public void clickLink_SDCProxy1(){
        //rh.clickLink("link=Proxy",true);
		rh.clickLink("xpath=//tr/td[2]/div[2]/div/div[2]/ul/li[5]/a",true);
    }

	public void clickLink_SystemUsingProxy(){
        rh.clickLink("link=Systems Using Proxy",true);
    }

	public void selectProxyLevel(String level){
		rh.selectComboBoxItem("name=proxy_version", level, false);
		//suggested level = RHN Proxy v5.0 or RHN Proxy v5.1
	}

	 public void clickButton_ActivateProxy(){
	        rh.clickButton("xpath=//input[@name='activate_proxy']",true);
	 }

	 public void clickLink_Continue(){
	        rh.clickLink("link=Continue",true);
	    }

	 public void clickLink_IAgree(){
	        rh.clickLink("link=I agree",true);
	    }

	 public void checkEnableMonitoringProxy(boolean check){
			rh.checkRadioButton("xpath=//input[@name='enable_monitoring']",check);
		}

	 public void clickButton_Continue(){
	        rh.clickButton("xpath=//input[@name='Continue']",true);
	 }
	 public void clickButton_continue(){
	        rh.clickButton("xpath=//input[@name='continue']",true);
	 }
	 
	 public void clickButton_DeactivateProxy(){
	        rh.clickButton("xpath=//input[@value='Deactivate Proxy']",true);
	 }

	 public void setTxt_AdminEmailAddress(String txt){
			sel.setText("xpath=//input[@name='traceback_mail']", txt);
			
	 }

	 public void setTxt_RHN_ProxyHostname(String txt){
		 sel.setText("xpath=//input[@name='hostname']", txt);
	 }
	 public void setTxt_HTTP_ProxyServer(String txt){
		 sel.setText("xpath=//input[@name='rhn_parent']", txt);
	 }
	 public void setTxt_HTTP_ProxyUsername(String txt){
		 sel.setText("xpath=//input[@name='http_proxy_username']", txt);
	 }
	 public void setTxt_HTTP_ProxyPassword(String txt){
		 sel.setText("xpath=//input[@name='http_proxy_password']", txt);
	 }
	 public void checkEnableSSL(boolean check){
			rh.checkRadioButton("xpath=//input[@name='enable_ssl']",check);
	 }
	 public void checkEnablePush(boolean check){
			rh.checkRadioButton("xpath=//input[@name='enable_osad']",check);
	 }
	 public void setTxt_CACertPassword(String txt){
		 sel.setText("xpath=//input[@name='ca_cert_password']", txt);
		 
	 }
	 public void setTxt_Organization(String txt){
		 sel.setText("xpath=//input[@name='org']", txt);
	 }
	 public void setTxt_OrganizationUnit(String txt){
		 sel.setText("xpath=//input[@name='org-unit']", txt);
	 }
	 public void setTxt_EmailAddress(String txt){
		 sel.setText("xpath=//input[@name='email']", txt);
	 }
	 public void setTxt_City(String txt){
		 sel.setText("xpath=//input[@name='city']", txt);
	 }
	 public void setTxt_State(String txt){
		 sel.setText("xpath=//input[@name='state']", txt);
	 }
	 public void select_Country(String country){
	    	rh.selectComboBoxItem("name=country", country, false);
	    	//suggest "United States"
	 }
}
