package com.redhat.rhn.harness.Sat42.pages;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.thoughtworks.selenium.SeleniumException;

/**
 * Collection of objects (links,buttons,etc..) used when setting up a RHN Proxy
 * @author whayutin
 *
 */
public class ProxyPage extends RhnPage{
         /**
           * Instance variable containing RhnHelper instance
           */
         RhnHelper rh = new RhnHelper();

        /**
          * Returns Proxy page location as a string
          */
         public String getLocation(){
                return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_PROXY_PAGE;
     }

         public void select_ProxyVersion(String proxyVersion){
            rh.selectComboBoxItem("name=proxy_version", proxyVersion, false);
         }

         public void select_Country(String country){
                rh.selectComboBoxItem("name=country", country, false);
         }

         public void clickButton_ActivateProxy(){
                 rh.clickButton("xpath=//input[@value='Activate Proxy']",true);
         }

         public void clickButton_Continue(){
                 rh.clickButton("xpath=//input[@value='Continue']",true);
         }

         public void clickLink_Continue(){
                 rh.clickLink("link=Continue",true);
         }

         public void clickLink_IAgree(){
                 rh.clickLink("link=I agree",true);
         }

         public void setAdminEmailAddress(String txt){
                 sel.setText("traceback_mail", txt);
         }

         public void setRHNProxyHostname(String txt){
                 sel.setText("hostname", txt);
         }

         public void setCACertPassword(String txt){
                 sel.setText("ca_cert_password",txt);
         }

         public void setOrganization(String txt){
                 sel.setText("org", txt);
         }

         public void setOrganizationUnit(String txt){
                 sel.setText("org-unit", txt);
         }

         public void setEmailAddress(String txt){
                 sel.setText("email", txt);
         }

         public void setCity(String txt){
                 sel.setText("city", txt);
         }

         public void setState(String txt){
                 sel.setText("state", txt);
         }

         public void setRHNParentServer(String txt){
                 sel.setText("rhn_parent", txt);
         }

         public void setHTTPProxy(String txt){
                 sel.setText("http_proxy", txt);
         }

         public void setCertExpiration(String txt){
                 sel.setText("cert-expiration", txt);
         }

         public void setHTTPProxyUsername(String txt){
                 sel.setText("http_proxy_username", txt);
         }

         public void setHTTPProxyPassword(String txt){
                 sel.setText("http_proxy_password", txt);
         }

         public void setEnableSSL(){
                 rh.checkRadioButton("xpath=//input[@value='enable_ssl']",true);
         }

         public void setEnablePush(){
                 rh.checkRadioButton("xpath=//input[@value='enable_osad']", true);
         }

         public void disableEnableSSL(){
                 rh.checkRadioButton("xpath=//input[@value='enable_ssl']",false);
         }
         
         public void clickLink_SDCProxy(){
        	 throw new SeleniumException("wrong page version");
         }
     	
     	public void clickLink_SDCProxy1(){
     		throw new SeleniumException("wrong page version");
         }
     	
     	public void selectProxyLevel(String level){
    		throw new SeleniumException("wrong page version");
    	}
     	
     	 public void checkEnableMonitoringProxy(boolean check){
     		throw new SeleniumException("wrong page version");
 		}
     	 

 	 public void clickButton_continue(){
 		throw new SeleniumException("wrong page version");
 	 }
 	 
 	 public void clickButton_DeactivateProxy(){
 		throw new SeleniumException("wrong page version");
 	 }

 	 public void setTxt_AdminEmailAddress(String txt){
 		throw new SeleniumException("wrong page version");
 	 }

 	 public void setTxt_RHN_ProxyHostname(String txt){
 		throw new SeleniumException("wrong page version");
 	 }
 	 public void setTxt_HTTP_ProxyServer(String txt){
 		throw new SeleniumException("wrong page version");
 	 }
 	 public void setTxt_HTTP_ProxyUsername(String txt){
 		throw new SeleniumException("wrong page version");
 	 }
 	 public void setTxt_HTTP_ProxyPassword(String txt){
 		throw new SeleniumException("wrong page version");
 	 }
 	 public void checkEnableSSL(boolean check){
 		throw new SeleniumException("wrong page version");
 	 }
 	 public void checkEnablePush(boolean check){
 		throw new SeleniumException("wrong page version");
 	 }
 	 public void setTxt_CACertPassword(String txt){
 		throw new SeleniumException("wrong page version");
 		 
 	 }
 	 public void setTxt_Organization(String txt){
 		throw new SeleniumException("wrong page version");
 	 }
 	 public void setTxt_OrganizationUnit(String txt){
 		throw new SeleniumException("wrong page version");
 	 }
 	 public void setTxt_EmailAddress(String txt){
 		throw new SeleniumException("wrong page version");
 	 }
 	 public void setTxt_City(String txt){
 		throw new SeleniumException("wrong page version");
 	 }
 	 public void setTxt_State(String txt){
 		throw new SeleniumException("wrong page version");
 	 }
 	 
 	public void clickLink_SystemUsingProxy(){
 		throw new SeleniumException("wrong page version");
    }
 	



}
