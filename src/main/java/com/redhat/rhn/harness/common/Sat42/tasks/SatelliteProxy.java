package com.redhat.rhn.harness.common.Sat42.tasks;


import com.redhat.rhn.harness.Sat42.pages.*;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.baseInterface.ISatelliteProxy;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;

/**
 * Tasks used to setup rhn proxy
 * @author whayutin
 *
 */
public class SatelliteProxy extends SeleniumSetup { // implements ISatelliteProxy {


	RhnHelper rh = new RhnHelper();
	
	
	
	



	public void installProxy(boolean enable_monitoring, String proxyVersion){
		if(rh.isTextPresent("Connection")){
			page_Proxy.clickLink_SDCProxy1();
		}
		else{
			page_Proxy.clickLink_SDCProxy();
		}
		//pp.selectProxyLevel("RHN Proxy v5.0");
		page_Proxy.selectProxyLevel(proxyVersion);
		page_Proxy.clickButton_ActivateProxy();
		page_Proxy.clickLink_Continue();
		page_Proxy.clickLink_IAgree();
		if(enable_monitoring){
			page_Proxy.checkEnableMonitoringProxy(true);
			page_Proxy.clickButton_continue();
		}
		else{
			page_Proxy.clickButton_continue();
		}
	
		if(rh.isTextPresent("Remove Conflicting Packages")){
			page_Proxy.clickButton_continue();
		}
		
		if(rh.isElementPresent("xpath=//input[@name='traceback_mail']", true)){
			page_Proxy.setTxt_AdminEmailAddress(HarnessConfiguration.RHN_EMAIL);
			page_Proxy.clickButton_continue();
		}
	
		if(rh.isTextPresent("Remove Conflicting Packages")){
			page_Proxy.clickButton_continue();
		}
		
		if(rh.isElementPresent("xpath=//input[@name='traceback_mail']", true)){
		page_Proxy.setTxt_AdminEmailAddress(HarnessConfiguration.RHN_EMAIL);
		page_Proxy.clickButton_continue();
		}
		
		if(rh.isTextPresent("Skip SSL Configuration")){
			throw new SeleniumException("SSL is not configured on this satellite");
		}
		
		if(rh.isTextPresent("Enter your CA cert")){
			page_Proxy.setTxt_CACertPassword(IRhnBase.PASSWORD);
			page_Proxy.setTxt_Organization("Red Hat");
			page_Proxy.setTxt_OrganizationUnit("RHEN");
			page_Proxy.setTxt_EmailAddress(HarnessConfiguration.RHN_EMAIL);
			page_Proxy.setTxt_City("Raleigh");
			page_Proxy.setTxt_State("NC");
			page_Proxy.clickButton_continue();
			assertFalse(rh.isTextPresent("Could not Generate SSL server cert"));
				if(enable_monitoring){
					page_Proxy.clickButton_continue();
				}
		}
		
	}

	public boolean checkIfProxyInstalled(String system){
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_AllSystems();
		page_SatelliteSystems.clickLink_ProxyLeftMenu();
		rh.clickSystemProfileLink(system);
		if(rh.isTextPresent("Connection")){
			page_Proxy.clickLink_SDCProxy1();
		}
		else{
			page_Proxy.clickLink_SDCProxy();
		}
		if(rh.isTextPresent("This machine is currently a licensed RHN Proxy")){
			return true;
		}
		else{
			log.fine("FAILED! Proxy install has failed");
			return false;
		}

	}

	public boolean checkProxyConnections(String client){
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_AllSystems();
		page_SatelliteSystems.clickLink_ProxyLeftMenu();
		rh.clickSystemProfileLink(IRhnBase.SYSTEM_HOSTNAME01);
		page_Proxy.clickLink_SDCProxy1();
		page_Proxy.clickLink_SystemUsingProxy();
		if(rh.isTextPresent(client)){
			return true;
		}
		else{
			return false;
		}
		
	}
		
	public void deactivateProxy(){
		if(rh.isTextPresent("Connection")){
			page_Proxy.clickLink_SDCProxy1();
		}
		else{
			page_Proxy.clickLink_SDCProxy();
		}
		page_Proxy.clickButton_DeactivateProxy();
		page_Proxy.clickButton_continue();
		page_Proxy.clickButton_continue();
		
	}
		
	






	

}
