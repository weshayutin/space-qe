package com.redhat.rhn.harness.common.Sat51.tasks;


import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.baseInterface.ISatelliteTools;
import com.thoughtworks.selenium.SeleniumException;

public class SatelliteTools extends com.redhat.rhn.harness.common.Sat50.tasks.SatelliteTools implements ISatelliteTools{
	

	
	public void createNewOrganization(String orgName, String user,String email, String password, boolean openAndLogin){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_SatelliteTools.open();
		page_SatelliteTools.clickLink_Organizations();
		page_SatelliteTools.clickLink_CreateNewOrganization();
		page_SatelliteTools.setTxt_OrganizationName(orgName);
		page_SatelliteTools.setTxt_Organization_Login(user);
		page_SatelliteTools.setTxt_Organization_Email(email);
		page_SatelliteTools.setTxt_Organization_DesiredPassword(password);
		page_SatelliteTools.setTxt_Organization_ConfirmPassowrd(password);
		page_SatelliteTools.setTxt_Organization_FirstName("auto");
		page_SatelliteTools.setTxt_Organization_LastName("man");
		page_SatelliteTools.clickButton_CreateOrganization();
	}
	
	public void goToOrganization(String orgName, boolean openAndLogin){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_SatelliteTools.open();
		page_SatelliteTools.clickLink_Organizations();
		page_RhnCommon.setTxt_FilterBy(orgName);
		page_RhnCommon.clickButton_Filter_Go();
		rh.clickLink("link="+orgName, true);
	}
	
	public void deleteOrganization(String orgName, boolean openAndLogin){
		goToOrganization(orgName, openAndLogin);
		page_SatelliteTools.clickLink_DeleteOrganization();
		page_SatelliteTools.clickButton_DeleteOrganization();
	}
	
	public void updateOrganizationName(String orgName, boolean openAndLogin, String newOrgName){
		goToOrganization(orgName, openAndLogin);
		page_SatelliteTools.setTxt_OrganizationName(newOrgName);
		page_SatelliteTools.clickButton_UpdateOrganization();
		//Organization westest01asdfas was successfully updated.
	}
	
	public void updateOrgSystemEntitlements(String orgName, boolean openAndLogin,int entitlement, String count,boolean successful){
		goToOrganization(orgName, openAndLogin);
		page_SatelliteTools.clickLink_Subscriptions();
		page_SatelliteTools.clickLink_Org_SystemEntitlments();
		switch (entitlement){
			case IRhnBase.ENTITLEMENT_BASE: 
			page_SatelliteTools.setTxt_Organization_SystemEntitlement_Base(count);break;
			
			case IRhnBase.ENTITLEMENT_MONITORING:
			page_SatelliteTools.setTxt_Organization_SystemEntitlement_Monitoring(count);break;
			
			case IRhnBase.ENTITLEMENT_PROVISIONING:
			page_SatelliteTools.setTxt_Organization_SystemEntitlement_Provisioning(count);break;
			
			case IRhnBase.ENTITLEMENT_VIRT:
			page_SatelliteTools.setTxt_Organization_SystemEntitlement_Virt(count);break;
			
			case IRhnBase.ENTITLEMENT_VIRT_PLATFORM:
			page_SatelliteTools.setTxt_Organization_SystemEntitlement_VirtPlatform(count);break;
			
			default: log.info("Invalid Entitlement Selection");
		
		}
		
		page_SatelliteTools.clickButton_UpdateOrganization();
		if(successful)
			Assert.assertTrue(rh.isTextPresent("Entitlements successfully updated."));
	}
	
	public void updateOrgSoftwareChannelEntitlements(String orgName, boolean openAndLogin, int channel, String count, boolean successful){
		String channelName = null;
		goToOrganization(orgName, openAndLogin);
		page_SatelliteTools.clickLink_Subscriptions();
		page_SatelliteTools.clickLink_Org_SoftwareChannelEntitlments();
		switch (channel){
			case IRhnBase.CHANNEL_RHEL_CORE_SERVER:
				channelName="Red Hat Enterprise Linux (core server)";
				log.info("Setting "+ count + "entitlements to "+channelName);
				page_SatelliteTools.setTxt_Organization_SoftwareChannelEntitlement(channelName,count);
				break;			
			case IRhnBase.CHANNEL_RHN_TOOLS:
				channelName="Red Hat Network Tools for Red Hat Enterprise Linux";
				log.info("Setting "+ count + "entitlements to "+channelName);
				page_SatelliteTools.setTxt_Organization_SoftwareChannelEntitlement(channelName,count);
				break;			
			case IRhnBase.CHANNEL_RHN_PROXY:
				channelName="Red Hat Network Proxy";
				log.info("Setting "+ count + "entitlements to "+channelName);
				page_SatelliteTools.setTxt_Organization_SoftwareChannelEntitlement(channelName,count);
				break;			
			case IRhnBase.CHANNEL_RHEL_VIRT:
				channelName="RHEL Virtualization";
				log.info("Setting "+ count + "entitlements to "+channelName);
				page_SatelliteTools.setTxt_Organization_SoftwareChannelEntitlement(channelName,count);
				break;			
			default: log.info("Error: Invalid Channel Selection");
		}
		
		page_SatelliteTools.clickButton_UpdateOrganization();
		Assert.assertTrue(rh.isTextPresent("Entitlements successfully updated."));
	}
	
	public int verifyOrgSystemEntitlementCounts(boolean openAndLogin, int entitlement, int column){
		String count = null;
		String entitlementName = null;
		if(openAndLogin)
			task_RhnBase.OpenAndLogIn();
		page_SatelliteTools.open();
		page_SatelliteTools.clickLink_SubscriptionsLeftMenu();
		page_SatelliteTools.clickLink_Org_SystemEntitlments();
		//stp.click
		switch (entitlement){
			case IRhnBase.ENTITLEMENT_BASE:
				entitlementName = "Management Base";
				count = page_SatelliteTools.getOrgEntitlement_Mangement(column);
				log.info(entitlementName+ " entitlements found = "+ count);
				break;
				
			case IRhnBase.ENTITLEMENT_PROVISIONING:
				entitlementName = "Provisioning";
				count = page_SatelliteTools.getOrgEntitlement_Provisioning(column);
				log.info(entitlementName+ " entitlements found = "+ count);
						
			default: log.info("Invalid Selection Type"); 
			/*if(count == null)
				throw new Exception("Entitlement Selection was invalid");*/
			break;
		
		}
		
		//s.replace("systems", "");
		/*count = count.replaceAll("total", "");
		count = count.replaceAll("systems", "");
		count = count.replaceAll("system", "");
		*/
		int i = Integer.valueOf(count.trim());
		return i;
	}
	
	
	public String verifyOrgSystemEntitlementUsage(boolean openAndLogin, int entitlement){
		String count = null;
		String entitlementName = null;
		if(openAndLogin)
			task_RhnBase.OpenAndLogIn();
		page_SatelliteTools.open();
		page_SatelliteTools.clickLink_SubscriptionsLeftMenu();
		page_SatelliteTools.clickLink_Org_SystemEntitlments();
		//stp.click
		switch (entitlement){
			case IRhnBase.ENTITLEMENT_BASE:
				entitlementName = "Management Base";
				count = page_SatelliteTools.getOrgEntitlement_Mangement(IRhnBase.ORG_ENTITLEMENT_USAGE);
				log.info(entitlementName+ " entitlements found = "+ count);
				break;
				
			case IRhnBase.ENTITLEMENT_PROVISIONING:
				entitlementName = "Provisioning";
				count = page_SatelliteTools.getOrgEntitlement_Provisioning(IRhnBase.ORG_ENTITLEMENT_USAGE);
				log.info(entitlementName+ " entitlements found = "+ count);
						
			default: log.info("Invalid Selection Type"); 
			/*if(count == null)
				throw new Exception("Entitlement Selection was invalid");*/
			break;
		
		}
		
		return count.trim();
	
	}
	
	
	public int verifyOrgSoftwareEntitlementCounts(boolean openAndLogin, int entitlement, int column){
		String count = null;
		String entitlementName = null;
		if(openAndLogin)
			task_RhnBase.OpenAndLogIn();
		page_SatelliteTools.open();
		page_SatelliteTools.clickLink_SubscriptionsLeftMenu();
		page_SatelliteTools.clickLink_Org_SoftwareChannelEntitlments();
		//stp.click
		switch (entitlement){
			case IRhnBase.CHANNEL_RHEL_CORE_SERVER:
				entitlementName = "Red Hat Enterprise Linux (core server)";
				page_RhnCommon.setTxt_FilterBy(entitlementName);
				page_RhnCommon.clickButton_Filter_Go();
				count = page_SatelliteTools.getOrgSoftwareChannelEntitlement(column);
				log.info(entitlementName+ " entitlements found = "+ count);
				break;
				
		
						
			default: log.info("Invalid Selection Type"); 
			break;
		
		}
		
		int i = Integer.valueOf(count.trim());
		return i;
	}
	
	public String verifyOrgSoftwareEntitlementUsage(boolean openAndLogin, int entitlement){
		String count = null;
		String entitlementName = null;
		if(openAndLogin)
			task_RhnBase.OpenAndLogIn();
		page_SatelliteTools.open();
		page_SatelliteTools.clickLink_SubscriptionsLeftMenu();
		page_SatelliteTools.clickLink_Org_SoftwareChannelEntitlments();
		switch (entitlement){
		case IRhnBase.CHANNEL_RHEL_CORE_SERVER:
			entitlementName = "Red Hat Enterprise Linux (core server)";
			page_RhnCommon.setTxt_FilterBy(entitlementName);
			page_RhnCommon.clickButton_Filter_Go();
			count = page_SatelliteTools.getOrgSoftwareChannelEntitlement(IRhnBase.ORG_ENTITLEMENT_USAGE);
			log.info(entitlementName+ " entitlements found = "+ count);
			break;
			
			default: log.info("Invalid Selection Type"); 
			break;
		}
		return count.trim();
	}
	

	public void deleteAllOrganizations(boolean openAndLogin){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_SatelliteTools.open();
		page_SatelliteTools.clickLink_Organizations();
		int i = 0;
		String orgLocator, orgILocator = "xpath=//tr/td[2]/div[2]/form/table/tbody/tr[I]/td/a";
		orgLocator = orgILocator.replace("I", Integer.toString(++i));
		while(rh.isElementPresent(orgLocator, false)){
			String org = rh.getText(orgLocator);
			rh.clickLink(orgLocator,org,true);
			try{
				page_SatelliteTools.clickLink_DeleteOrganization();
				page_SatelliteTools.clickButton_DeleteOrganization();
				assertTrue(rh.checkForErrors() == IRhnBase.ERROR_NO_ERROR_FOUND);
				log.info("deleted org: "+org);
			}
			catch(SeleniumException se){
				log.info("delete link not found, org '"+org+"' may be the sat cert org");
				orgLocator = orgILocator.replace("I", Integer.toString(++i));
			}
			
			page_SatelliteTools.open();
			page_SatelliteTools.clickLink_Organizations();
		}

	}
	
	
	
	
}
