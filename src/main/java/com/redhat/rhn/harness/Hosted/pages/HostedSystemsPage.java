package com.redhat.rhn.harness.Hosted.pages;

import java.util.logging.Level;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.thoughtworks.selenium.SeleniumException;



public class HostedSystemsPage extends RhnPage {

	   RhnHelper rh = new RhnHelper();

	   public static String XPATH_ALL_SYSTEMS="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]";
	   public static final String VIRTGUESTTABLEID = "xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]";

	
	   public String getLocation(){
	        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SYSTEMS_PAGE;
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

	   /*public void clickAllSystemsLink(){
	        sel.click("link=All");
	        sel.waitForPageToLoad(HarnessConfiguration.TIMEOUT);
	    }*/
	   public void clickLink_AllSystems(){
		   rh.clickLink("link=All", true);
	   }

	   public void clickLink_Virtualization(){
			rh.clickLink("link=Virtualization", true);
	   }

	   public void clickLink_VirtualizationProvisioning(){
			rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[2]/div/div[2]/ul/li[2]/a", true);
	   }

	   public void clickLink_Kickstart(){
		   rh.clickLink("link=Kickstart", true);
		   //sel.waitForPageToLoad(HarnessConfiguration.TIMEOUT);
	   }

	   public void clickLink_Overview(){
		   rh.clickLink("link=Overview", true);
	   }

	   public void clickLink_SystemsLeft(){
		   rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/systems/SystemList.do", true, "System_Leftside");
	   }

	   public void clickLink_Details(){
		   rh.clickLink("link=Details", true);
	   }

	   public void clickLink_DeleteSystem(){
		   rh.clickLink("link=delete system", true);
	   }

	   public void clickButton_DeleteProfile(){
		   rh.clickButton("xpath=//input[@type='submit' and @value='Delete Profile']", true);
	   }

	   public void clickLink_Proxy(){
		   rh.clickLink("link=Proxy", true);
	   }

	   public void clickLink_VirtualSystems(){
		   rh.clickLink("link=Virtual Systems",true);
	    }

	   public void clickLink_SystemGroups(){
		   rh.clickLink("link=System Groups",true);
	    }

	   public void clickLink_AdvancedSearch(){
	        rh.clickLink("link=Advanced Search",true);
	    }

	   public void check_InvertSearchResults(){
	        rh.checkRadioButton("name=invert",true);
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

	   public void clickButton_ApplyGuestAction(){
		   rh.clickButton("xpath=//input[@type='submit' and @value='Apply Action']", true);
	   }

	   public void clickButton_ApplyGuestChanges(){
		   rh.clickButton("xpath=//input[@type='submit' and @value='Apply Changes']", true);
	   }

	   public void clickButton_ApplyGuestActionConfirm(){
		   rh.clickButton("xpath=//input[@type='submit' and @value='Confirm']", true);
	   }

	   public void select_FieldToSearch_NameDescription(){
		   try{
		   rh.selectComboBoxItem("name=view_mode", "value=search_simple",false);
		   }
		   catch(SeleniumException se){
			   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_name_and_description",false);
		   }
	   }

	   public void select_FieldToSearch_ID(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_id",false);
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
		   rh.selectComboBoxItem("name=view_mode", "value=search_cpu_model",false);
	   }

	   public void select_FieldToSearch_CpuLessThan(){
		   rh.selectComboBoxItem("name=view_mode", "label=CPU MHz Less Than",false);
	   }

	   public void select_FieldToSearch_CpuGreaterThan(){
		  // rh.selectComboBoxItem("name=view_mode", "value=search_cpu_mhz_gt",false);
		   rh.selectComboBoxItem("name=view_mode", "label=CPU Mhz Greater Than",false);
	   }

	   public void select_FieldToSearch_RamLessThan(){
		   rh.selectComboBoxItem("name=view_mode", "label=RAM Less Than",false);
	   }

	   public void select_FieldToSearch_RamGreaterThan(){
		   rh.selectComboBoxItem("name=view_mode", "label=RAM Greater Than",false);
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
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_hostname",false);
	   }

	   public void select_FieldToSearch_NetworkInfo_IP(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_ip",false);
	   }

	   public void select_FieldToSearch_Packages_Installed(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_installed_packages",false);
	   }

	   public void select_FieldToSearch_Packages_Needed(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_needed_packages",false);
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
	   public void select_FieldToSearch_Synopsis(){
		   try{
		   rh.selectComboBoxItem("name=view_mode", "value=simple_errata_search",false);
		   }
		   catch(SeleniumException se){
			   rh.selectComboBoxItem("name=view_mode", "value=errata_search_by_synopsis",false);
		   }
	   }
	   public void select_FieldToSearch_ErratumAdvisory(){
		   try{
		   rh.selectComboBoxItem("name=view_mode", "label=Erratum Advisory (ex: RHSA-2002:130)",false);
		   }
		   catch(SeleniumException se){
			   rh.selectComboBoxItem("name=view_mode", "label=errata_search_by_advisory",false);
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

	   public void select_PackageSearch_NameSummary(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_name_and_summary",false);
	   }

	   public void select_PackageSearch_Name(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_name",false);
	   }

	   public void select_VirtGuestSettingToModify(String selection){
		   rh.selectComboBoxItem("name=guestSettingToModify","label="+selection,false);
	   }



	   public String getSatSystem_ID(){
		   String id = null;
		   id=sel.getTable("class=details.0.0");
		   log.fine("system id =" +id);
		   return id;
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

	   public String getSatSystem_Registered(){
		   return sel.getTable("class=details.4.1");
	   }
	   public String getSatSystem_CheckedIn(){
		   return sel.getTable("class=details.5.1");
	   }

	   public String getSystem_ID(){
		   String id = null;
		   clickLink_Details();
		   id=sel.getTable("class=details.3.1");
		   log.fine("system id =" +id);
		   return id;
	   }


	   public String getHostSystem_Hostname(){
		   return sel.getTable("class=details.0.1");
	   }

	   public String getHostSystem_IpAddress(){
		   return sel.getTable("class=details.1.1");
	   }

	   public String getHostSystem_Kernel(){
		   return sel.getTable("class=details.2.1");
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

	   public void checkBox_AcceptTerms(boolean check){
			rh.checkRadioButton("xpath=//input[@name='accepted']",check);
	   }
	   public void clickButton_Continue(){
		   rh.clickButton("xpath=//input[@type='submit' and @value='Continue']", true);
	   }

	   public void clickButton_Next(){
		   rh.clickButton("xpath=//input[@type='submit' and @value='Next']", true);
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

	   
}
