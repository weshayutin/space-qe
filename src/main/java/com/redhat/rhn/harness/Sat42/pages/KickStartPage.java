package com.redhat.rhn.harness.Sat42.pages;

import java.util.logging.Level;

import org.apache.commons.logging.Log;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

/**
 * Collection of objects (links,buttons,etc..) used when creating and modifying RHN Kickstarts
 * @author whayutin, ssalevan
 */
public class KickStartPage extends RhnPage{

	/**
	  * Instance variable containing RhnHelper instance
	  */
	 protected RhnHelper rh = new RhnHelper();

	 public static String XPATH_KICKSTART_STATUS="xpath=//div[@id='content']/table/tbody/tr/td[@class='page-content']/table[@class='list']";
	 //public static String SDC_KickStart_Status = "xpath=//tbody/tr/td[2]/table[2]/tbody/tr/td/table/tbody/tr[2]/td";
	 //public static String SDC_KickStart_lastPackage = "xpath=//table/tbody/tr/td[2]/table[2]/tbody/tr/td/table/tbody/tr[4]/td";
	// public static String SDC_KickStart_lastPackage = "xpath=//tbody/tr/td/table/tbody/tr[4]/td";
	 							///html/body/div/div[2]/div/table/tbody/tr/td[2]/div[4]/table/tbody/tr/td/table/tbody/tr[4]/td
	//SDC_KickStart_lastPackage = "xpath=//html/body/div/div[2]/div/table/tbody/tr/td[2]/div[4]/table/tbody/tr/td/table/tbody/tr[4]/td";
	 public static String SDC_KickStart_totalNumberPackages = "//td[preceding-sibling::th[normalize-space(.)='Total Packages Requested:']]";
	 public static String SDC_KickStart_Status = "//td[preceding-sibling::th[normalize-space(.)='Description:']]";
	 public static String SDC_KickStart_lastPackage = "//td[preceding-sibling::th[normalize-space(.)='Last File Requested:']]";

	 public String PARA_VIRTUALIZED_GUEST = "Para-Virtualized Guest";
	 public String PARA_VIRTUALIZED_HOST = "Para-Virtualized Host";
	 
	 /**
	  * Returns Kickstart page location as a string
	  */
	 public String getLocation(){
			return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_KICKSTART_PAGE;
     }

	 /**
	  * Clicks 'create new distribution' link on Distributions page
	  */
	 public void clickLink_CreateNewKickstartDistribution(){
		 	rh.clickLink("link=create new distribution",true);
	 }

	 /**
	  * Clicks 'Create a New Kickstart Profile' link on Kickstart page
	  */
	 public void clickLink_CreateNewKickstartProfile(){
			rh.clickLink("link=Create a New Kickstart Profile",true);
	 }
	 
	 public void clickLink_CancelKickstart(){
			rh.clickLink("link=Cancel Kickstart",true);
	 }

	 /**
	  * Clicks 'create new kickstart profile' link on Kickstart page
	  */
	 public void openLink_CreateNewKickstartProfile(){
	    	rh.openLink(HarnessConfiguration.RHN_HOST +"/rhn/kickstart/CreateProfileWizard.do",true,"create new kickstart profile");
	 }

	 /**
	  * Clicks 'Distributions' link on Kickstart page
	  */
	 public void clickLink_Distributions(){
		 	rh.clickLink("link=Distributions", true);
	 }

	 /**
	  * Clicks 'delete kickstart' link on a singular Kickstart Profile page
	  */
	 public void clickLink_DeleteKickstart(){
			rh.clickLink("link=delete kickstart",true);
	 }
	 
	 public void clickLink_KickstartDetails(){
			rh.clickLink("link=Kickstart Details",true);
	 }
	 
	 /**
	  * Clicks 'Scripts' link on a singular Kickstart Profile page
	  */
	 public void clickLink_Scripts(){
			rh.clickLink("link=Scripts",true);
	 }
	 
	 public void clickLink_Profiles(){
		 rh.clickLink("link=Profiles", true);
	 }

	 public void clickLink_SystemDetails(){
			rh.clickLink("link=System Details",true);
	 }

	 public void clickLink_Troubleshooting(){
		 rh.clickLink("link=Troubleshooting", true);
	 }
	 
	 public void clickLink_OperatingSystem(){
		 rh.clickLink("link=Operating System", true);
	 }

	 public void clickLink_ActivationKeys(){
			rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[2]/ul/li[4]/a",true);
			///html/body/div/div[5]/table/tbody/tr/td[2]/div[2]/ul/li[4]/a
	 }

	 /**
	  * Clicks 'add new kickstart script' link on scripts page of a singular Kickstart Profile page
	  */
	 public void clickLink_AddNewKickstartScript(){
		        rh.clickButton("xpath=//img[@alt='add new kickstart script']",true);
	 }

	 /**
	  * Clicks 'View a List of Kickstart Profiles' link on Kickstart page
	  */
	 public void clickLink_ViewListKickstartProfiles(){
			rh.clickLink("link=View a List of Kickstart Profiles",true);
	 }
	 
	 public void clickLink_BareMetalKickstart(){
			rh.clickLink("link=Bare Metal Kickstart",true);
	 }
	 
	 public String getTxt_BareMetalKickstartUrl(){
		 //tested on 5.1.1
		 return sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[3]/p[3]");
	 }

	 /**
	  * Sets Kickstart Profile Label text field on Kickstart Profile Creation page to supplied string
	  * @param txt text to set Kickstart Profile Label to
	  */
	 public void setTxt_KickstartProfileLabel(String txt){
			sel.setText("xpath=//input[@name='kickstartLabel']", txt);
	 }

	 /**
	  * Sets Distribution Label text field on Distribution Creation page to supplied string
	  * @param txt text to set Kickstart Distribution Label to
	  */
	 public void setTxt_DistributionLabel(String txt){
		 	sel.setText("xpath=//input[@name='label']", txt);
	 }

	 /**
	  * Sets External Location (url) text field on Distribution Creation page to supplied string
	  * @param txt text to set External Location URL to
	  */
	 public void setTxt_ExternalLocationUrl(String txt){
		 	sel.setText("xpath=//input[@name='basepath']", txt);
	 }

	 public void setTxt_TroubleshootingKernelParameters(String txt){
		 	sel.setText("xpath=//input[@name='kernelParams']", txt);
	 }
	 
	 public void setTxt_NetworkInterface(String txt){
		 //sel.setText("xpath=//input[@name='dhcpNetworkIf']", txt);
		 String locator="xpath=//input[@name='dhcpNetworkIf']";
		 if (sel.isElementPresent(locator, Level.FINE)) {
			 sel.setText(locator, txt);
		 } else {
			 // TODO
			 log.info("TODO IGNORING CALL TO KickstartPage.setTxt_NetworkInterface("+txt+")");
		 }
	 }

	 /**
	  * Sets Scripting Language text field on script creation page of a singular Kickstart Profile page
	  * @param txt scripting language to set
	  */
	 public void setTxt_ScriptingLanguage(String txt){
			sel.setText("xpath=//input[@name='language']", txt);
	 }

	 /**
	  * Sets Scripting Contents text field on script creation page of a singular Kickstart Profile page
	  * @param txt scripting contents to set
	  */
	 public void setTxt_ScriptingContents(String txt){
		sel.setText("contents", txt);
	 }
	 
	 public String getSelectedBaseChannel(){
		 return rh.getSelectedLabelFromComboBox("name=currentChannelId");
	 }

	 /**
	  * Sets Base Channel combo box on Kickstart Profile Creation page
	  * @param channel channel to set Base Channel combo box to
	 * @param waitForPage TODO
	  */
	 public void select_BaseChannel(String channel, boolean waitForPage){
	    	rh.selectComboBoxItem("name=currentChannelId", channel, waitForPage);
	    	//example Red Hat Enterprise Linux AS (v. 4 for 32-bit x86)
	 }
	 
	 public void select_DistributionBaseChannel(String channel, boolean waitForPage){
	    	rh.selectComboBoxItem("name=channelid", channel, waitForPage);
	    	//example Red Hat Enterprise Linux AS (v. 4 for 32-bit x86)
	 }
	 
	 public void select_KickStart_RPM(String rpm, boolean waitForPage){
	    	rh.selectComboBoxItem("name=bootimage", rpm, waitForPage);
	    	//example ks-redhat-advanced-server-i386-qu3
	 }
	 
	 public void select_KickStart_InstallerGeneration(String distribution, boolean waitForPage){
	    	rh.selectComboBoxItem("name=installtype", distribution, waitForPage);
	    	//example Red Hat Enterprise Linux 5
	 }

	 /**
	  * Sets Script Execution Time combo box on script creation page of singular Kickstart Profile page
	  * @param time String containing Script Execution Time
	  */
	 public void select_ScriptExecutionTime(String time){
	    	rh.selectComboBoxItem("name=type",time, false);
	    	//example Red Hat Enterprise Linux AS (v. 4 for 32-bit x86)
	 }

	 /**
	  * Sets Base Channel combo box on Distribution creation page
	  * @param channel String containing Base Kickstart Channel
	  */
	 public void select_BaseChannelforDistribution(String channel){
		 	rh.selectComboBoxItem("name=channelid",channel, false);
	 }

	 public void select_ParavirtualizedGuestOption(){
		 select_VirtualizationType(PARA_VIRTUALIZED_GUEST);
	 }

	 public void select_ParavirtualizedHostOption(){
		 select_VirtualizationType(PARA_VIRTUALIZED_HOST);
	 }

	 /**
	  * Sets Kickstartable Tree combo box on Kickstart Profile Creation page
	  * @param tree Kickstartable Tree to use
	  */
	 public void select_KickstartableTree(String tree){
		 	log.info("DEBUG, TREE= "+tree);
	    	rh.selectComboBoxItem("name=kstreeId", tree, false);
	    	//example ks-rhel-i386-as-4
	 }

	 public void select_VirtualizationType(String type){
		 	log.info("DEBUG, Type= "+type);
	    	rh.selectComboBoxItem("name=virtualizationTypeLabel", type, false);
	    	//example para_guest Para-Virtualized Guest
	 }

	 /**
	  * Sets Autokickstart RPM combo box on Distribution creation page
	  * @param autoks String containing Autokickstart RPM
	  */
	 public void select_AutokickstartRPM(String autoks){
		 	rh.selectComboBoxItem("name=bootimage", autoks, false);
	 }
	 
	 public void check_ChildChannel(boolean check){
		 String locator = "xpath=//html/body/div/div[2]/div/table/tbody/tr/td[2]/div[3]/form/table/tbody/tr[2]/td/input";
		 rh.checkRadioButton(locator, check);
	 }
	 
	 /**
	  * Clicks any button marked 'Next'
	  */
	 public void clickButton_Next(){
	        rh.clickButton("xpath=//input[@value='Next']",true);
	 }
	 
	 public void clickButton_CancelKickStart(){
	        rh.clickButton("xpath=//input[@value='Cancel Kickstart']",true);
	 }

	 /**
	  * Clicks any button marked 'Create Kickstart Distribution'
	  */
	 public void clickButton_CreateKickstartDistribution(){
		 	rh.clickButton("xpath=//input[@value='Create Kickstart Distribution']", true);
	 }

	 /**
	  * Clicks any button marked 'Update Kickstart'
	  */
	 public void clickButton_UpdateKickstart(){
	        rh.clickButton("xpath=//input[@value='Update Kickstart']",true);
	 }
	 
	 public void clickButton_UpdateSystemDetails(){
		    rh.clickButton("xpath=//input[@value='Update System Details']", true);
	 }

	 public void clickButton_UpdateOptions(){
	        rh.clickButton("xpath=//input[@value='Update Options']",true);
	 }

	 public void clickButton_UpdateActivationKeys(){
	        rh.clickButton("xpath=//input[@value='Update Activation Keys']",true);
	 }

	 /**
	  * Clicks any button marked 'Finish'
	  */
	 public void clickButton_Finish(){
	        rh.clickButton("xpath=//input[@value='Finish']",true);
	 }

	 /**
	  * Clicks any button marked 'Schedule Kickstart and Finish'
	  */
	 public void clickButton_ScheduleKickstartAndFinish(){
	        rh.clickButton("xpath=//input[@value='Schedule Kickstart and Finish']",true);
	 }

	 /**
	  * Clicks any button marked 'Delete Kickstart'
	  */
	 public void clickButton_DeleteKickstart(){
	        rh.clickButton("xpath=//input[@value='Delete Kickstart']",true);
	 }

	 /**
	  * Sets Root Password field on Kickstart Profile Creation page
	  * @param txt root password
	  */
	 public void setTxt_RootPassword(String txt){
			sel.setText("xpath=//input[@name='rootPassword']", txt);
	 }

	 /**
	  * Sets Root Password Confirmation field on Kickstart Profile Creation page
	  * @param txt root password...  again...
	  */
	 public void setTxt_RootPasswordConfirm(String txt){
			sel.setText("xpath=//input[@name='rootPasswordConfirm']", txt);
	    }

	 public String getText_SDC_Kickstart_Status(){
		 return (sel.getText(SDC_KickStart_Status));
	 }
	 public String getText_SDC_Kickstart_LastPackage(){
		 return (sel.getText(SDC_KickStart_lastPackage));
	 }
	 public String getText_SDC_Kickstart_Total_Number_Packages(){
		 return (sel.getText(SDC_KickStart_totalNumberPackages));
	 }


	 /**
	  * Gets Kickstart status from Kickstart page
	  * @param system system to check Kickstart status of
	  * @return String representing Kickstart status
	  */
	 public String getText_SystemKickstart_Status(String system){
		 String status;
		 status = (rh.getTableData(XPATH_KICKSTART_STATUS, system, 3));
		 return status;
	 }

	 public String getText_SystemKickstart_Status(String system, String autoKS){
		 String status;
		 String[] mySearchVal = new String[2];
		 mySearchVal[0] = system;
		 mySearchVal[1] = autoKS;
		 status = (rh.getTableDataPlus(XPATH_KICKSTART_STATUS, mySearchVal, 3));
	     return status;
	 }

	 public String getText_SystemKickstart_Status(String system, String autoKS, String randlabel, SatelliteSystemsPage syspage){
		 String status;
		 String[] mySearchVal = new String[2];
		 mySearchVal[0] = system;
		 mySearchVal[1] = autoKS;
		 status = (rh.getKSTableData(XPATH_KICKSTART_STATUS, mySearchVal, 3, syspage, randlabel));
		 return status;
	 }
	 
	 public void clickLink_create_new_distribution(){
			rh.clickLink("link=create new distribution",true);
	 }
	 
	 public void clickLink_deletedistribution(){
			rh.clickLink("link=delete distribution",true);
	 }
	 
	 public void clickButton_DeleteDistribution(){
	        rh.clickButton("xpath=//input[@value='Delete Distribution']",true);
	 }
}
