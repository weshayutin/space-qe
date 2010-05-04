package com.redhat.rhn.harness.Sat42.pages;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.thoughtworks.selenium.SeleniumException;

/**
 *  * Collection of objects (links,buttons,etc..) from the RHN Satellite SDC Page, specifically the provisioning page in the SDC
 *  This is mainly used for kickstarts
 * @author whayutin
 *
 */
public class SystemProvisionPage extends RhnPage {

	RhnHelper rh = new RhnHelper();

	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SYSTEMS_PAGE;
    }


	public void check_FirstSystemInList(){
		//sel.check("name=items_selected");
		rh.checkRadioButton("name=items_selected",true);
	}

	public void setTxt_FilterByFileName(String txt){
		sel.setText("xpath=//input[@name='filter_string']", txt);
	}

	/*public void clickButton_Go(){
		rh.clickImage("xpath=//input[@type='image' and @value='filter']",true);
	}*/

	 public void clickLink_SystemName(String system){
			rh.clickLink("link="+system,true);
		}

	 public void clickSideMenuSystems(){
	    	rh.openLink(HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SYSTEMS_PAGE,true,"Systems");
	    }

	 public void clickLink_EditSystemProperties(){
		    try{
			rh.clickLink("link=Edit These Properties",true);
		    }
		    catch(SeleniumException se){
		    	rh.clickLink("link=Edit these properties",true);
		    }
		}

	 public void setText_ProfileName(String name){
		 	try{
		 		sel.setText("name=system_name",name);
		 	}
		 	catch(SeleniumException se){
		 		sel.setText("name=name",name);
		 	}
		}

	 public void check_Provisioning(){
			 try{
				rh.checkRadioButton("name=provisioning_entitled",true);
			 }
			 catch (SeleniumException se){
				 ///html/body/div/div[4]/table/tbody/tr/td[2]/form/table/tbody/tr[3]/td/input[2]
				 //rh.checkRadioButton("value=provisioning_entitled");
				 rh.selectItem("provisioning","xpath=//html/body/div/div[4]/table/tbody/tr/td[2]/form/table/tbody/tr[3]/td/input[2]");
			 }
		}
	 
	 public void check_Monitoring(){
			 rh.checkRadioButton("xpath=//input[@type='checkbox' and @value='monitoring_entitled']", true);
	}

	 public void check_VirtualizationPlatform(){
		 try{
			rh.checkRadioButton("name=virtualization_host_platform",true);
		 }
		 catch (SeleniumException se){
			 ///html/body/div/div[4]/table/tbody/tr/td[2]/form/table/tbody/tr[3]/td/input[2]
			 //rh.checkRadioButton("value=provisioning_entitled");
			 rh.selectItem("virtualization platform","xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table/tbody/tr[3]/td/input[4]");
		 }
	}

	 public void clickButton_UpdateProperties(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Update Properties']",true);
	    }

	 public void clickLink_Configuration(){
			rh.clickLink("link=Configuration",true);
		}

	 public void clickEnableConfigurationManagementOnSystems(){
	    	rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/configuration/system/TargetSystems.do",true,"Target Systems");
		}
	 //then click first check box

	/* public void clickLink_ScheduleDeployAction(){
			rh.clickLink("link=Schedule Deploy Action",true);
		}
	 public void clickLink_SubscribeToChanel(){
			rh.clickLink("link=Subscribe to channels",true);
		}

	 //then click fist check box

	 public void clickButton_Continue(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Continue']",true);
	    }

	 public void clickButton_Update(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Update']",true);
	    }*/



	//sideMenuSystems in rhncommon

}
