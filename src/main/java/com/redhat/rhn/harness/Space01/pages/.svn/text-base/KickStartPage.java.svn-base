package com.redhat.rhn.harness.Space01.pages;

public class KickStartPage extends com.redhat.rhn.harness.Sat52.pages.KickStartPage {
	
	public KickStartPage() {
		PARA_VIRTUALIZED_HOST = "Xen "+PARA_VIRTUALIZED_HOST;
		PARA_VIRTUALIZED_GUEST = "Xen "+PARA_VIRTUALIZED_GUEST;
		//SDC_KickStart_Status = "//td[preceding-sibling::th[normalize-space(.)='Description:']]";
		//SDC_KickStart_lastPackage = "xpath=//html/body/div/div[2]/div/table/tbody/tr/td[2]/div[4]/table/tbody/tr/td/table/tbody/tr[4]/td";
		//SDC_KickStart_lastPackage = "//td[preceding-sibling::th[normalize-space(.)='Last File Requested:']]";
		
	}
	
	 public void clickLink_ActivationKeys(){
			rh.clickLink("//div[@class='content-nav']/ul[@class='content-nav-rowone']/li/a[normalize-space(.)='Activation Keys']",true);
	 }
	 
	 /**
	  * Checks "Active" box on profile edit page
	  * @param check
	  */
	 public void checkBox_ActiveProfile(boolean check){
		rh.checkRadioButton("xpath=//input[@name='active']",check);
	 }
	 
	 /**
	  * Checks "Log Custom Post Scripts" box on profile edit page
	  * @param check
	  */
	 public void checkBox_LogCustomPostScripts(boolean check){
		rh.checkRadioButton("xpath=//input[@name='post_log']", check);
	 }
	 
	 /**
	  * Checks "Log Custom Pre Scripts" box on profile edit page
	  * @param check
	  */
	 public void checkBox_LogCustomPreScripts(boolean check){
		 rh.checkRadioButton("xpath=//input[@name='pre_log']", check);
	 }
	 
	 /**
	  * Checks "Preserve ks.cfg" box on profile edit page
	  * @param check
	  */
	 public void checkBox_PreserveKsCfg(boolean check){
		 rh.checkRadioButton("xpath=//input[@name='ksCfg']", check);
	 }
	 
	 /**
	  * Clicks any button marked "Create"
	  */
	 public void clickButton_Create(){
		 rh.clickButton("xpath=//input[@value='Create']",true);
	 }
	 
	 /**
	  * Clicks "Create Cobbbler Systemn Record" button on kickstart
	  * scheduling page
	  */
	 public String clickButton_CreateCobblerSystemRecord(){
		 rh.clickButton("xpath=//input[@value='Create Cobbler System Record']",true);
		 String alertTxt = rh.getTxt_AlertBanner();
		 String[] alert_split = alertTxt.split(":");
		 String profile_0 = alert_split[0].split(" ")[alert_split[0].split(" ").length - 1];
		 String profile_1 = alert_split[1];
		 String profile_2 = alert_split[2].split(" ")[0];
		 String koanProfile = profile_0 + ":" + profile_1 + ":" + profile_2;
		 return koanProfile;
	 }
	 
	 /**
	  * Clicks "Bare Metal Kickstart" link on kickstart file edit page
	  */
	 public void clickLink_BareMetalKickstart(){
	 	rh.clickLink("link=Bare Metal Kickstart",true);
	 }
	 
	 /**
	  * Clicks "Upload New Kickstart File" link on main Kickstart page
	  */
	 public void clickLink_UploadNewKickstartFile(){
	 	rh.clickLink("link=upload new kickstart file",true);
	 }
	 
	 /**
	  * Clicks "Variables" link on kickstart file edit page
	  */
	 public void clickLink_Variables(){
	 	rh.clickLink("link=Variables",true);
	 }
	 
	 public void clickLink_Partitioning(){
			rh.clickLink("link=Partitioning",true);
	 }
	 
	
	 @Override
	 public String getTxt_BareMetalKickstartUrl(){
		 return sel.getText("/html/body/div/div[2]/div/table/tbody/tr/td[2]/div[3]/p[3]");
	 }
	 
	 /**
	  * Returns Active status of the topmost Kickstart profile
	  * @return active/inactive status as a boolean
	  */
	 public boolean get_ProfileActiveStatus(){
		 return sel.isElementPresent("xpath=//html/body/div/div[2]/div/table/tbody/tr/td[2]/div[2]/form/table[2]/tbody/tr/td[2]/img[@alt='Active']");
	 }
	 
	 /**
	  * Returns RHN Satellite Managed status of the topmost Kickstart profile
	  * @return managed/unmanaged status as a boolean
	  */
	 public boolean get_RHNSatelliteManagedStatus(){
		 return sel.isElementPresent("xpath=//html/body/div/div[2]/div/table/tbody/tr/td[2]/div[2]/form/table[2]/tbody/tr/td[4]/img[@src='/img/rhn-listicon-checked.gif'])");
	 }
	 
	 /**
	  * Sets File Contents text field on kickstart file creation page
	  * @param txt scripting contents to set
	  */
	 public void setTxt_FileContents(String txt){
		if(rh.isElementPresent("xpath=//input[@class='toggle_contents']", true))
			rh.checkRadioButton("xpath=//input[@class='toggle_contents']", false);
		sel.setText("contents", txt);
	 }

	 /**
	  * Sets Kernel Options text field on kickstart profile edit page
	  * @param txt scripting contents to set
	  */
	 public void setTxt_KernelOptions(String txt){
		sel.setText("kernel_options", txt); 
	 }
	 
	 /**
	  * Sets Post Kernel Options text field on kickstart profile edit page
	  * @param txt scripting contents to set
	  */
	 public void setTxt_PostKernelOptions(String txt){
		sel.setText("post_kernel_options", txt); 
	 }
	 
	 public void setTxt_ScriptingContents(String txt){
		 // there was a BIG issue with Selenium to recognize a text area element within iframe.
		 // Had to implement such a solution, gkhachik.
		 sel.selectFrame("frame_contents");
		 sel.selectFrame("relative=top");
		 rh.sleepForSeconds(2);
		 sel.waitForElement("id=edit_area_toggle_checkbox_contents", "60000");
		 sel.uncheck("id=edit_area_toggle_checkbox_contents");
		 rh.sleepForSeconds(2);
		 sel.click("xpath=//textarea[@id='contents']");
		 rh.sleepForSeconds(2);
		 sel.type("xpath=//textarea[@id='contents']", txt);
	 }

	 public void setTxt_PartitionDetails(String txt){
			sel.setText("partitions", txt);
	 }
	 
	 public void clickButton_UpdatePartitions(){
		    rh.clickButton("xpath=//input[@value='Update Partitions']", true);
	 }
	 
	 public void clickButton_UpdatePackages(){
		    rh.clickButton("xpath=//input[@value='Update Packages']", true);
	 }
	 	 
	 public void referToKsProfile(String profileName){
			page_KickStart.open();
			page_KickStart.clickLink_Profiles();
			page_RhnCommon.setTxt_FilterBy(profileName);
			page_RhnCommon.clickButton_Filter_Go();
			page_RhnCommon.clickLink_GeneralLink(profileName);		 
	 }

	 public void clickLink_Software(){
			rh.clickLink("link=Software",true);
	 }
	 public void clickLink_PackageGroups(){
			rh.clickLink("link=Package Groups",true);
	 }
	 
}
