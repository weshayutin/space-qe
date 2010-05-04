package com.redhat.rhn.harness.Sat42.pages;

import java.util.logging.Level;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.thoughtworks.selenium.SeleniumException;

/**
 * Collection of objects (links,buttons,etc..) from the RHN Satellite Systems page, not the hosted env systems page.
 * @author whayutin
 *
 */
public class SatelliteSystemsPage extends RhnPage {

	RhnHelper rh = new RhnHelper();
	public static String XPATH_ALL_SYSTEMS="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]";
	   public static final String VIRTGUESTTABLEID = "xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]";


	   public String getLocation(){
	        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SYSTEMS_PAGE;
	    }

	   public void clickLink_Overview(){
		   rh.clickLink("link=Overview", true);
	   }
	   
	   public void clickLink_SystemsLeft(){
		   rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/systems/SystemList.do", true, "System_Leftside");
	   }
	   
	 
	 

	   
	   
	   /*public void clickAllSystemsLink(){
	        sel.click("link=All");
	        sel.waitForPageToLoad(HarnessConfiguration.TIMEOUT);
	    }*/
	   public void clickLink_AllSystems(){
		   rh.clickLink("All", true);
	   }

	   public void clickLink_ProxyLeftMenu(){
		   rh.clickLink("Proxy", true);
	   }

	   public void clickLink_DeleteSystem(){
		   rh.clickLink("link=delete system", true);
	   }

	   public void clickButton_DeleteProfile(){
		   rh.clickButton("xpath=//input[@type='submit' and @value='Delete Profile']", true);
	   }

	   public void clickButton_Group_AddSystem(){
		   rh.clickButton("xpath=//input[@type='submit' and @value='Add Systems']", true);
	   }

	   public void clickLink_VirtualSystems(){
		   rh.clickLink("link=Virtual Systems",true);
	    }

	   public void clickLink_AdvancedSearch(){
	        rh.clickLink("link=Advanced Search",true);
	    }

	   public void clickLink_OutOfDateSystemsLink(){
	        rh.clickLink("link=Out of Date",true);
	    }

	   public void clickLink_UnentitledSystems(){
	        rh.clickLink("link=Unentitled",true);
	    }

	   public void clickLink_InactiveSystems(){
	        rh.clickLink("link=Inactive",true);
	    }

	   public void clickLink_RecentlyRegisteredSystems(){
	        rh.clickLink("link=Recently Registered",true);
	    }

	   public void setText_SearchFor(String txt){
	        sel.setText("xpath=//input[@type='text' and @maxlength='36']",txt);
	    }

	   public void clickButton_Search(){
	        rh.clickLink("Search!",true);
	    }

	   public void clickLink_SystemGroups(){
		   //rh.clickLink("System Groups",true);
		   ///html/body/div/div[5]/table/tbody/tr/td/div/ul/li[11]/a
		   //rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td/div/ul/li[11]/a", true);
		 //  log.info(HarnessConfiguration.RHN_HOST+"/rhn/systems/SystemGroupList.do");
		   rh.openLink(HarnessConfiguration.RHN_HOST+"/rhn/systems/SystemGroupList.do", true, "System Groups");
	   }

	   public void clickLink_Group_TargetSystems(){
		   //rh.clickLink("Target Systems",true);
		   ///html/body/div/div[5]/table/tbody/tr/td[2]/div[2]/ul/li[3]/a
		   rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[2]/ul/li[3]/a", "Target Systems", true);
	   }
	   
	   public void clickLink_Group_Admins(){
		   //rh.clickLink("Target Systems",true);
		   ///html/body/div/div[5]/table/tbody/tr/td[2]/div[2]/ul/li[3]/a
		   rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[2]/ul/li[5]/a", "Admins", true);
	   }

	   public void clickLink_CreateNewGroup(){
		  // rh.clickLink("create new group", true);
		   ///network/systems/groups/create.pxt
		   rh.openLink(HarnessConfiguration.RHN_HOST+"/network/systems/groups/create.pxt", true, "create new group");
	   }
	   
	   public void clickLink_CustomSystemInfo(){
		   		try{
		   			rh.clickLink("xpath=//a[contains(@href, '/rhn/systems/customdata/CustomDataList.do)]", "Custom System Info",true);
		   		}catch(SeleniumException se){
		   			rh.clickLink("Custom System Info",true);
		   		}
		   		
	   }
	   
	   public void clickLink_CustomSystemInfo_CreateNewKey(){
		   try{
		   rh.clickLink("xpath=//a[contains(@href, '/rhn/systems/customdata/CreateCustomKey.do)]", "create new key",true);
		   }
		   catch(SeleniumException se){
			   rh.clickLink("create new key", true);
		   }
	   }
	   
	   public void setText_CustomKey_KeyLabel(String txt){
	        sel.setText("xpath=//input[@type='text' and @name='label']",txt);
	    }
	   
	   public void setText_CustomKey_KeyDescription(String txt){
	       // sel.setText("xpath=//input[@name='description']",txt);
		   sel.setText("name=description", txt);
	    }
	   
	   public void clickButton_CustomInfo_CreateKey(){
		   rh.clickButton("xpath=//input[@type='submit' and @value='Create Key']", true);
	   }


	   public void setText_SystemGroup_Name(String txt){
	        sel.setText("xpath=//input[@type='text' and @name='name']",txt);
	    }

	   public void setText_SystemGroup_Description(String txt){
	        //sel.setText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table/tbody/tr[2]/td/textarea",txt);
		   sel.setText("xpath=//tr[2]/td/textarea","Description",txt);
		   
	    }

	   public void clickButton_CreateGroup(){
		   rh.clickButton("xpath=//input[@type='submit' and @value='Create Group']", true);
	   }


	   public void select_FieldToSearch_NameDescription(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_simple",false);
	   }



	   public void select_FieldToSearch_ID(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_id",false);
	   }

	   public void select_FieldToSearch_CustomInfo(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_custom_info",false);
	   }

	   public void select_FieldToSearch_SnapShotTag(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_snapshot_tag",false);
	   }

	   public void select_FieldToSearch_DaysSinceLastCheckin(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_checkin",false);
	   }

	   public void select_FieldToSearch_DaysSinceFirstRegistered(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_registered",false);
	   }

	   public void select_FieldToSearch_CpuModel(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_cpu_model",false);
	   }
	   
	   public void select_FieldToSearch_NumberOfCpuLessThan(){
		  throw new SeleniumException("wrong page version");
	   }
	   
	   public void select_FieldToSearch_NumberOfCpuGreaterThan(){
		   throw new SeleniumException("wrong page version");
	   }

	   public void select_FieldToSearch_CpuLessThan(){
		   rh.selectComboBoxItem("name=view_mode", "label=CPU MHz less than",false);
	   }

	   public void select_FieldToSearch_CpuGreaterThan(){
		   rh.selectComboBoxItem("name=view_mode", "label=CPU MHz greater than",false);
		   //rh.selectComboBoxItem("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/div/div/table/tbody/tr[2]/td/select/optgroup[8]/option[3]",false);
	   }

	   public void select_FieldToSearch_RamLessThan(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_ram_lt",false);
	   }

	   public void select_FieldToSearch_RamGreaterThan(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_ram_gt",false);
	   }

	   public void select_FieldToSearch_HD_Description(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_hwdevice_description",false);

	   }

	   public void select_FieldToSearch_HD_Driver(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_hwdevice_driver",false);
	   }

	   public void select_FieldToSearch_HD_DeviceID(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_hwdevice_device_id",false);
	   }

	   public void select_FieldToSearch_HD_VendorID(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_hwdevice_vendor_id",false);
	   }

	   public void select_FieldToSearch_DMI_System(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_dmi_system",false);
	   }

	   public void select_FieldToSearch_DMI_Bios(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_dmi_bios",false);
	   }

	   public void select_FieldToSearch_DMI_AssetTag(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_dmi_asset",false);
	   }

	   public void select_FieldToSearch_NetworkInfo_Hostname(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_hostname",false);
	   }

	   public void select_FieldToSearch_NetworkInfo_IP(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_ip",false);
	   }

	   public void select_FieldToSearch_Packages_Installed(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_installed_packages",false);
	   }

	   public void select_FieldToSearch_Packages_Needed(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_needed_packages",false);
	   }

	   public void select_FieldToSearch_Location_Address(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_location_address",false);
	   }
	   public void select_FieldToSearch_Location_Building(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_location_building",false);
	   }
	   public void select_FieldToSearch_Location_Room(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_location_room",false);
	   }
	   public void select_FieldToSearch_Location_Rack(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_location_rack",false);
	   }
	   
	   public void select_FieldToSearch_Details_Running_Kernel(){
		   //new in 5.3 satellite
		  // rh.selectComboBoxItem("name=view_mode", "value=systemsearch_running_kernel",false);
		   throw new SeleniumException("wrong page version");
	   }

	   public String getSatSystem_ID(){
		   //this is only for 4.2
		   String id = null;
		   id=sel.getTable("class=details.0.1");
		   return id;
	   }
	   
	   public void select_FieldToSearch_ErratumAdvisory(){
		   try{
		   rh.selectComboBoxItem("name=view_mode", "label=Erratum Advisory (ex: RHSA-2002:130)",false);
		   }
		   catch(SeleniumException se){
			   rh.selectComboBoxItem("name=view_mode", "label=errata_search_by_advisory",false);
		   }
	   }
	   
	   public void select_PackageSearch_Name(){
		   rh.selectComboBoxItem("name=view_mode", "value=package_search_by_name",false);
	   }
	   
	   public void select_PackageSearch_NameSummary(){
		   rh.selectComboBoxItem("name=view_mode", "value=simple_package_search",false);
	   }
	   
	   public void select_FieldToSearch_Synopsis(){
		   try{
		   rh.selectComboBoxItem("name=view_mode", "value=simple_errata_search",false);
		   }
		   catch(SeleniumException se){
			   rh.selectComboBoxItem("name=view_mode", "value=errata_search_by_synopsis",false);
		   }
	   }
	   
	   public void select_FieldToSearch_ErrataPackageName(){
		   try{
		   rh.selectComboBoxItem("name=view_mode", "label=Package Name (ex: apache)",false);
		   }
		   catch(SeleniumException se){
			   rh.selectComboBoxItem("name=view_mode", "label=errata_search_by_package_name",false);
		   }
	   }

	   public String getSatSystem_Hostname(){
		   return sel.getTable("class=details.1.1");
	   }

	   public String getSatSystem_IpAddress(){
		   return sel.getTable("class=details.2.1");
	   }

	   public String getSatSystem_Kernel(){
		   return sel.getTable("class=details.3.1");
	   }

	   public String getSatSystem_LockStatus(){
		   return sel.getTable("class=details.4.1");
	   }

	   public String getSatSystem_CheckedIn(){
		   return sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[5]/table/tbody/tr/td");
	   }
	   public String getSatSystem_Registered(){
		   return sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[5]/table/tbody/tr[2]/td");
	   }
	   public String getSatSystem_LastBooted(){
		   return sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[5]/table/tbody/tr[3]/td");
	   }
	   public String getSatSystem_Entitlements(){
		   return sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[6]/table/tbody/tr[1]/td/");
	   }

	   public String getSatSystem_Notifications(){
		   return sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[6]/table/tbody/tr[2]/td/");
	   }
	   public String getSatSystem_AutoErrataUpdate(){
		   return sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[6]/table/tbody/tr[3]/td/");
	   }
	   public String getSatSystem_BUG_SystemName(){
		   return sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[6]/table/tbody/tr[4]/td/");
	   }
	   public String getSatSystem_Description(){
		   return sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[6]/table/tbody/tr[5]/td/");
	   }
	   public String getSatSystem_Location(){
		   return sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[6]/table/tbody/tr[6]/td/");
	   }



	   public String getHostSystem_ID(){
		   String id = null;
		   id=sel.getTable("class=details.3.1");
		   log.fine("system id =" +id);
		   return id;
	   }


	   public String getSystem_Hostname(){
		   return sel.getTable("class=details.0.1");
	   }

	   public String getSystem_IpAddress(){
		   return sel.getTable("class=details.1.1");
	   }

	   public String getSystem_Kernel(){
		   return sel.getTable("class=details.2.1");
	   }
	   //////////////////////////////////////////////////////////////////////////
	   
	   public void clickLink_Kickstart(){
		   rh.clickLink("link=Kickstart", true);
		   //sel.waitForPageToLoad(HarnessConfiguration.TIMEOUT);
	   }
	   
	   public String getText_SystemEntitlement(String system){
			 String entitlement;
			 entitlement = (rh.getTableData(XPATH_ALL_SYSTEMS, "fjs-0-06.rhndev.redhat.com", 7));
			                               //xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]
			 return entitlement;
		 }
	   
	   public void select_Checkbox(String system){
		   rh.selectItemInRow(XPATH_ALL_SYSTEMS, system);
	   }
	   
	  

	   public void clickLink_Virtualization(){
			rh.clickLink("Virtualization", true);
	   }

	   public void clickLink_VirtualizationProvisioning(){
			rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[2]/div/div[2]/ul/li[2]/a", true);
	   }

	   public void clickLink_VirtGuestSDC(){
		    rh.clickLink(VIRTGUESTTABLEID+"/tbody/tr/td[3]/a", true);
	   }

	   public String getGuestAllotment(String guestName,
			   						   boolean isMemory){
		   try{
			   if(isMemory){
				   return rh.getTableData(VIRTGUESTTABLEID,
						   				  guestName,
						   				  6);
			   }
			   else{
				   return rh.getTableData(VIRTGUESTTABLEID,
			   				  			  guestName,
			   				  			  7);
			   }
		   }
		   catch (Exception e){
			   log.log(Level.SEVERE, "Finding guest allotment in guest table failed", e);
			   return "";
		   }
	   }
	   
	   
	   public void setTxt_VirtGuestName(String txt){
			sel.setText("guestName", txt);
		}

		public void setTxt_VirtMemoryAllocation(String txt){
			sel.setText("memoryAllocation",txt);
		}

		public void setTxt_VirtVirtualCPUs(String txt){
			sel.setText("virtualCpus",txt);
		}

		public void setTxt_VirtGuestSettingValue(String txt){
			sel.setText("guestSettingValue",txt);
		}

		public void select_VirtAction(String selection){
			rh.selectComboBoxItem("name=guestAction", "label="+selection,false);
		}

		public void clickButton_VirtScheduleKickstartAndFinish(){
			rh.clickButton("xpath=//input[@type='button' and @value='Schedule Kickstart and Finish']", true);
		}

		public void select_VirtGuestSettingToModify(String selection){
			   rh.selectComboBoxItem("name=guestSettingToModify","label="+selection,false);
		   }
		
		   public void clickButton_ApplyGuestAction(){
			   rh.clickButton("xpath=//input[@type='submit' and @value='Apply Action']", true);
		   }

		   public void clickButton_ApplyGuestChanges(){
			   rh.clickButton("xpath=//input[@type='submit' and @value='Apply Changes']", true);
		   }

		   public void clickButton_ApplyGuestActionConfirm(){
			   rh.clickButton("xpath=//input[@type='submit' and @value='Confirm']", true);
		   }
		   
		   public String getGuestStatus(String guestName){
			   try{
				   return rh.getTableData(VIRTGUESTTABLEID,
						   		   		  guestName,
						   		   		  5);
			   }
			   catch (Exception e){
				   log.log(Level.SEVERE, "Finding guest status in guest table failed", e);
				   return "";
			   }
		   }
		   
		   public void clickLink_Proxy(){
			   rh.clickLink("link=Proxy", true);
		   }
		   
		   public void clickLink_delete_group(){
			   rh.clickLink("xpath=//a[contains(@href, 'delete_confirm.pxt?')]", "delete group",true);
		   }
		   
		   public void clickButton_Confirm_Deletion(){
			   rh.clickButton("xpath=//input[@type='submit' and @value='Confirm Deletion']", true);
		   }

		   
		   public String getGuestStatus(){
			   return sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]/tbody/tr/td[5]");
		   }
		   
	   public void clickButton_PaginateNext(){
		    	rh.clickLink("name=Next", true);
		}
		
		public void clickButton_PaginateLast(){
	    	rh.clickLink("name=Last", true);
	    }

	    public void clickButton_PaginateFirst(){
	    	rh.clickLink("name=First", true);
	    }

	    public void clickButton_PaginatePrevious(){
	    	rh.clickLink("name=Prev", true);
	    }

	    public void select_FieldToSearchPackage_NameOnly(){
			  throw new SeleniumException("wrong page version");
		   }
	    
	    public void select_FieldToSearchPackage_NameAndSummary(){
			   throw new SeleniumException("wrong page version");
		   }
	    public void clickButton_JoinSelectedGroups(){
	    	throw new SeleniumException("wrong page version");
	    }
	    
	    /***
	     * Set to protected to be usable for the inherited classes as well.
	     * @param parameter Parameter like: "Hostname:"; "RHN Satellite System ID:"
	     * @return String containing value for the param. or null.
	     */
	    protected String getSystemInfo(String parameter){
	    	int i = 1;
	    	while(sel.isElementPresent("//table[@class='details']/tbody/tr["+i+"]")){
	    		// there is still element in the table - let's check if it's ours...
	    		if(sel.getText("//table[@class='details']/tbody/tr["+i+"]/th").equals(parameter)){
	    			return sel.getText("//table[@class='details']/tbody/tr["+i+"]/td").trim(); 
	    		}
	    		i++;
	    	}
	    	return null;
	    }



}
