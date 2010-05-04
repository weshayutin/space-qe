package com.redhat.rhn.harness.Sat51.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.thoughtworks.selenium.SeleniumException;


public class SatelliteSystemsPage extends  com.redhat.rhn.harness.Sat50.pages.SatelliteSystemsPage {

	RhnHelper rh = new RhnHelper();


	   public String getLocation(){
	        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SYSTEMS_PAGE;
	    }

	   public void select_SearchNumberOfDisplayedResults(String number){
	    	rh.selectComboBoxItem("name=*PAGE_SIZE_LABEL", number, false);
	    }

	   public void select_FieldToSearchPackage_NameOnly(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_name",false);
	   }

	   public void select_FieldToSearchPackage_Freeform(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_free_form",false);
	   }

	   public void select_FieldToSearchPackage_NameAndDescription(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_name_and_description",false);
	   }

	   public void select_FieldToSearchPackage_NameAndSummary(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_name_and_summary",false);
	   }

	   public void setText_SearchFor(String txt){
	        sel.setText("xpath=//input[@type='text' and @name='search_string' and @value='']",txt);
	    }
	   
	   public void select_FieldToSearch_NameDescription(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_simple",false);
	   }



	   public void select_FieldToSearch_ID(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_id",false);
	   }

	   public void select_FieldToSearch_CustomInfo(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_custom_info",false);
	   }

	   public void select_FieldToSearch_SnapShotTag(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_snapshot_tag",false);
	   }

	   public void select_FieldToSearch_DaysSinceLastCheckin(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_checkin",false);
	   }

	   public void select_FieldToSearch_DaysSinceFirstRegistered(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_registered",false);
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
		   rh.selectComboBoxItem("name=view_mode", "label=CPU MHz Less Than",false);
	   }

	   public void select_FieldToSearch_CpuGreaterThan(){
//		   rh.selectComboBoxItem("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/div/div/table/tbody/tr[2]/td/select/optgroup[8]/option[3]",false);
		   rh.selectComboBoxItem("name=view_mode", "label=CPU Mhz Greater Than",false);
	   }

	   public void select_FieldToSearch_RamLessThan(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_ram_lt",false);
	   }

	   public void select_FieldToSearch_RamGreaterThan(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_ram_gt",false);
	   }

	   public void select_FieldToSearch_HD_Description(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_hwdevice_description",false);

	   }

	   public void select_FieldToSearch_HD_Driver(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_hwdevice_driver",false);
	   }

	   public void select_FieldToSearch_HD_DeviceID(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_hwdevice_device_id",false);
	   }

	   public void select_FieldToSearch_HD_VendorID(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_hwdevice_vendor_id",false);
	   }

	   public void select_FieldToSearch_DMI_System(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_dmi_system",false);
	   }

	   public void select_FieldToSearch_DMI_Bios(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_dmi_bios",false);
	   }

	   public void select_FieldToSearch_DMI_AssetTag(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_dmi_asset",false);
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
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_location_address",false);
	   }
	   public void select_FieldToSearch_Location_Building(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_location_building",false);
	   }
	   public void select_FieldToSearch_Location_Room(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_location_room",false);
	   }
	   public void select_FieldToSearch_Location_Rack(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_location_rack",false);
	   }
	   
	   public void clickButton_Search(){
//		   rh.clickButton("xpath=//tr/td[2]/form/div/div/table/tbody/tr/td/input[2]", "Search", true);
		   rh.clickButton("xpath=//th[normalize-space(.)='Search For:']/..//input[@value='Search']","Search",true);
	    }

	   public void setText_CustomKey_KeyLabel(String txt){
	        sel.setText("xpath=//input[@type='text' and @name='key_label']",txt);
	    }

	   public void setText_CustomKey_KeyDescription(String txt){
//	       sel.setText("xpath=//input[@name='description']",txt);
		   sel.setText("name=key:description", txt);
	    }

}
