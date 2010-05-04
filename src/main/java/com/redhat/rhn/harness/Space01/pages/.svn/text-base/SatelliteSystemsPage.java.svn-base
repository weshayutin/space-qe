package com.redhat.rhn.harness.Space01.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.thoughtworks.selenium.SeleniumException;


public class SatelliteSystemsPage extends  com.redhat.rhn.harness.Sat51.pages.SatelliteSystemsPage {

	RhnHelper rh = new RhnHelper();


	   public String getLocation(){
	        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SYSTEMS_PAGE;
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
	   public void select_FieldToSearch_ID(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_id",false);
	   }

	   public void select_FieldToSearch_CustomInfo(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_custom_info",false);
	   }

	   public void select_FieldToSearch_SnapShotTag(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_snapshot_tag",false);
	   }

	   public void select_FieldToSearch_Details_Running_Kernel(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_running_kernel",false);
	   }
	   public void select_FieldToSearch_NetworkInfo_Hostname(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_hostname",false);
	   }
	   public void select_FieldToSearch_NetworkInfo_IP(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_ip",false);
	   }
	   
	   public void select_FieldToSearch_NumberOfCpuLessThan(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_num_of_cpus_lt",false);
		   }
		   
		   public void select_FieldToSearch_NumberOfCpuGreaterThan(){
			   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_num_of_cpus_gt",false);
		   }
		   
		   public void setText_CustomKey_KeyLabel(String txt){
		        sel.setText("xpath=//input[@type='text' and @name='label']",txt);
		    }
		   
		   public void setText_CustomKey_KeyDescription(String txt){
		       // sel.setText("xpath=//input[@name='description']",txt);
			   sel.setText("name=description", txt);
		    }
		   
		   
		   public void select_FieldToSearchPackage_NameOnly(){
			   rh.selectComboBoxItem("name=view_mode", "value=search_name",false);
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
		   
		   public void select_FieldToSearch_NameDescription(){
			   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_name_and_description",false);
		   }

		    public void clickButton_JoinSelectedGroups(){
		    	rh.clickButton("xpath=//input[@type='submit' and @value='Join Selected Groups']", true);
		    }

}
