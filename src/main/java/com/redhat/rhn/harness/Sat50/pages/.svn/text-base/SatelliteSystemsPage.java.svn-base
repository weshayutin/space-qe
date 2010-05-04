package com.redhat.rhn.harness.Sat50.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;


public class SatelliteSystemsPage extends  com.redhat.rhn.harness.Sat42.pages.SatelliteSystemsPage {

	RhnHelper rh = new RhnHelper();


	   public String getLocation(){
	        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SYSTEMS_PAGE;
	    }
	   
	   public void select_FieldToSearch_ID(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_id",false);
	   }

	   public String getSatSystem_ID(){
		   String id = null;
//		   id=sel.getTable("class=details.3.1");
		   id = getSystemInfo("RHN Satellite System ID:");
		   return id;
	   }
	   
	   public String getSatSystem_Hostname(){
		   return sel.getTable("class=details.0.1");
	   }

	   public String getSatSystem_IpAddress(){
		   return sel.getTable("class=details.1.1");
	   }

	   public String getSatSystem_Kernel(){
		   return sel.getTable("class=details.2.1");
	   }
	  
	   public void select_FieldToSearch_Packages_Installed(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_installed_packages",false);
	   }
	   
	   public void select_FieldToSearch_Packages_Needed(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_needed_packages",false);
	   }
	   
	   public void select_FieldToSearch_CpuLessThan(){
		   rh.selectComboBoxItem("name=view_mode", "label=CPU MHz Less Than",false);
	   }

	   public void select_FieldToSearch_CpuGreaterThan(){
		   rh.selectComboBoxItem("name=view_mode", "label=CPU MHz Greater Than",false);
		   //rh.selectComboBoxItem("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/div/div/table/tbody/tr[2]/td/select/optgroup[8]/option[3]",false);
	   }
	   
	   public void select_FieldToSearch_RamLessThan(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_ram_lt",false);
	   }

	   public void select_FieldToSearch_RamGreaterThan(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_ram_gt",false);
	   }
	   
	   public void select_PackageSearch_Name(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_name",false);
	   }
	   
	   public void select_PackageSearch_NameSummary(){
		   rh.selectComboBoxItem("name=view_mode", "value=search_name_and_summary",false);
	   }
	   
	   public void select_FieldToSearch_NetworkInfo_Hostname(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_hostname",false);
	   }
	   
	   public void select_FieldToSearch_NetworkInfo_IP(){
		   rh.selectComboBoxItem("name=view_mode", "value=systemsearch_ip",false);
	   }


}
