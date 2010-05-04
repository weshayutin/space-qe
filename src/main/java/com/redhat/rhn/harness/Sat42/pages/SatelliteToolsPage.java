package com.redhat.rhn.harness.Sat42.pages;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.thoughtworks.selenium.SeleniumException;

/**
 * Collection of objects (links,buttons,etc..) on the Satellite Tools Page
 * @author whayutin
 *
 */
public class SatelliteToolsPage extends RhnPage{

	RhnHelper rh = new RhnHelper();

	public String getLocation() {
		 return "https://"+IRhnBase.SERVER_HOSTNAME + HarnessConfiguration.RHN_SATELLITE_TOOLS;
	}

	/**
	 *
	 */

	public void clickLink_TaskEngineStatus(){
		rh.clickLink("link=Task Engine Status",true);
	}

	public void clickLink_SatelliteConfiguration(){
		rh.clickLink("link=Satellite Configuration",true);
	}


	public void clickLink_Details(){
		rh.clickLink("link=Details",true);
	}

	public void clickLink_Users(){
		rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[2]/ul/li[2]/a","Users",true);
	}

	public void clickLink_Monitoring(){
		//rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td/div/ul/li[4]/a","Monitoring",true);
		rh.clickLink("xpath=//a[contains(@href, '/rhn/admin/config/MonitoringConfig.do')]", "Monitoring", true);
	}
	
	public void clickLink_Restart(){
		rh.clickLink("link=Restart", true);
	}

	public void check_EnableMonitoringScout(boolean check){
		rh.checkRadioButton("name=is_monitoring_scout",check);
	}

	public void check_EnableMonitoring(boolean check){
		rh.checkRadioButton("name=web|is_monitoring_backend",check);
	}
	
	public void check_EnableSolarisSupport(boolean check){
		rh.checkRadioButton("name=web|enable_solaris_support", check);
	}
	
	public void check_RestartSatellite(boolean check){
		rh.checkRadioButton("name=restart", check);
	}

	public void clickButton_Update(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Update']",true);
    }

	public void clickButton_UpdateConfig(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Update Config']",true);
    }
	
	public void clickButton_Restart(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Restart']", true);
	}
	
	
	
	
	
	
	//LINKS FOUND IN NON-42 SATS
	
	public void clickLink_Organizations(){
		throw new SeleniumException("wrong page version");
	}
	
	public void clickLink_SubscriptionsLeftMenu(){
		throw new SeleniumException("wrong page version");
	}
	
	public void clickLink_Org_SystemEntitlments(){
		throw new SeleniumException("wrong page version");
	}
	
	public void clickLink_Org_SoftwareChannelEntitlments(){
		throw new SeleniumException("wrong page version");
		//rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[2]/div/div[2]/ul/li[2]/a",true);
	}
	
	public void clickLink_CreateNewOrganization(){
		throw new SeleniumException("wrong page version");
	}
	
	public void setTxt_OrganizationName(String txt){
		throw new SeleniumException("wrong page version");
    }
	
	public void setTxt_Organization_Login(String txt){
		throw new SeleniumException("wrong page version");
    }
	public void setTxt_Organization_Email(String txt){
		throw new SeleniumException("wrong page version");
    }
	public void setTxt_Organization_DesiredPassword(String txt){
		throw new SeleniumException("wrong page version");
    }
	public void setTxt_Organization_ConfirmPassowrd(String txt){
		throw new SeleniumException("wrong page version");
    }
	
	public void setTxt_Organization_FirstName(String txt){
		throw new SeleniumException("wrong page version");
    }
	public void setTxt_Organization_LastName(String txt){
		throw new SeleniumException("wrong page version");
    }
	public void clickButton_CreateOrganization(){
		throw new SeleniumException("wrong page version");
    }
	
	public void clickButton_UpdateOrganization(){
		throw new SeleniumException("wrong page version");
    }
	
	public void clickButton_DeleteOrganization(){
		throw new SeleniumException("wrong page version");
    }
	
	public void clickLink_DeleteOrganization(){
		throw new SeleniumException("wrong page version");
	}
	
	public void clickLink_Subscriptions(){
		throw new SeleniumException("wrong page version");
	}

	public void setTxt_Organization_SystemEntitlement_Base(String txt){
		throw new SeleniumException("wrong page version");
    }

	public void setTxt_Organization_SystemEntitlement_Monitoring(String txt){
		throw new SeleniumException("wrong page version");
    }

	public void setTxt_Organization_SystemEntitlement_Provisioning(String txt){
		throw new SeleniumException("wrong page version");
    }

	public void setTxt_Organization_SystemEntitlement_Virt(String txt){
		throw new SeleniumException("wrong page version");
    }

	public void setTxt_Organization_SystemEntitlement_VirtPlatform(String txt){
		throw new SeleniumException("wrong page version");
    }
	
	public void setTxt_Organization_SoftwareChannelEntitlement(String Channel, String number){
		throw new SeleniumException("wrong page version");
	}
	
	 public String getOrgEntitlement_Mangement(int column){
		 throw new SeleniumException("wrong page version");
	    }
	 
	 public String getOrgEntitlement_Provisioning(int column){
		 throw new SeleniumException("wrong page version");
	    }
	
	 
	 public String getOrgSoftwareChannelEntitlement(int column){
		 throw new SeleniumException("wrong page version");
	    }
	 

		public void clickLink_Admin(){
			 throw new SeleniumException("wrong page version");
		}
		
		public void clickLink_Trusts(){
			 throw new SeleniumException("wrong page version");
		}
		
		 public void checkBox_Trust(boolean check){
			 throw new SeleniumException("wrong page version");
		 }
		 
		 public void clickButton_ModifyTrusts(){
			 throw new SeleniumException("wrong page version");
		 }




















}
