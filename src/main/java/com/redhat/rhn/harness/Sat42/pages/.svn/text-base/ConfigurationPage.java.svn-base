package com.redhat.rhn.harness.Sat42.pages;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.thoughtworks.selenium.SeleniumException;

/**
 * Collection of objects (links,buttons,etc..) used with RHN Configuration Channels and Files,
 *
 */
public class ConfigurationPage extends RhnPage {

	protected RhnHelper rh = new RhnHelper();

	public static String XPATH_CFG_MANG_TARGET_SYSTEM="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[3]/form/table[2]";


	public static String XPATH_CFG_MANG_HOSTED_TARGET_SYSTEM="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[3]/form/table[2]";


	public static String XPATH_CFG_MANG_CENT_CHANNEL_FILE="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[3]/form/table[2]";


	public static String XPATH_CFG_MANG_CENT_CHANNEL_COMPARE="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]";


	public static String XPATH_CFG_MANG_HOSTED_CENT_CHANNEL_COMPARE="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]";


	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_CONFIGURATION_PAGE;
    }

	public void clickLink_Overview(){
        rh.clickLink("link=Overview",true);
    }

	public void clickLink_ChannelOverview(){
        rh.clickLink("xpath=//a[contains(@href, '/rhn/configuration/ChannelOverview.do?ccid=')]",true);
    }

	public void clickLink_DeleteChannel(){
        rh.clickImage("alt=delete channel","delete channel",true);
    }

	public void clickLink_ConfigurationChannels(){
		rh.clickLink("link=Configuration Channels", true);
	}

	public void clickLink_ConfigurationFiles(){
        rh.clickLink("link=Configuration Files",true);
    }


	public void clickLink_QuotaForFileStorage(){
        rh.clickLink("link=Quota for File Storage",true);
    }

	public void clickViewSystemsWithManagedConfigurationFiles(){
	    	rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/configuration/system/LocalConfigChannelList.do",true, "Local Config Channel List");
    }

	public void clickViewAllManagedConfigurationFiles(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/configuration/file/GlobalConfigFileList.do",true,"Global Config File List");
	}

	public void clickViewAllManagedConfigurationChannels(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/configuration/GlobalConfigChannelList.do",true,"Global Config Channel List");
	}


	public void clickCreateANewConfigurationChannel(){
		rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/configuration/ChannelCreate.do?editing=true", true, "Create New Config Channel");
	}

	public void clickEnableConfigurationManagementOnSystems(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/configuration/system/TargetSystems.do",true, "Enable Configuration Management on Systems");
	}

	public void clickCreateNewConfigChannel(){
		rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/configuration/ChannelCreate.do?editing=true",true, "Create New Config Channel");
	}

	public void setNewConfigChannelName(String txt){
    	//sel.type("cofName", txt);
		sel.setText("cofName", txt);
    }

	public void setNewConfigChannelLabel(String txt){
    	//sel.type("cofLabel", txt);
		sel.setText("cofLabel", txt);
    }

	public void setNewConfigChannelDescription(String txt){
    	//sel.type("cofDescription", txt);
		sel.setText("cofDescription", txt);
    }

	/*public void clickButton_CreateConfigChannel(){
		//Assert.assertTrue(sel.isElementPresent("xpath=//input[@name='checkall']"));
		//input[@type='submit']
        sel.click("xpath=//input[@type='submit' and @value='Create Config Channel']");
        sel.waitForPageToLoad(HarnessConfiguration.TIMEOUT);
    }*/
	public void clickButton_CreateConfigChannel(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Create Config Channel']", true);
	}

	public void clickButton_EnableRHNConfigurationManagement(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Enable RHN Configuration Management']", true);
	}

	public void clickButton_DeployAllFiles(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Deploy All Files']", true);
	}

	public void clickButton_DeployFiles(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Deploy Files']", true);
	}

	public void clickButton_DeploySelectedFiles(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Deploy Selected Files']", true);
	}

	public void clickLink_ChannelName(String channel){
		rh.clickLink("link="+channel,true);
	}

	public void clickLink_AddFiles(){
		rh.clickLink("link=Add Files",true);
	}

	public void clickLink_ListRemoveFiles(){
		rh.clickLink("link=List/Remove Files",true);
	}

	public void clickLink_DeployFiles(){
		rh.clickLink("link=Deploy Files",true);
	}

	public void clickLink_Systems(){
		rh.clickLink("link=Systems",true);
	}

	public void clickLink_EditProperties(){
		rh.clickLink("link=Edit Properties",true);
	}

	public void setTxt_FileToUpload(String txt){
		sel.setText("xpath=//input[@name='cffUpload']", txt);
    }
	public void setTxt_FileNamePath(String txt){
		sel.setText("xpath=//input[@name='cffPath']", txt);
    }

	public void clickButton_UploadConfigurationFile(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Upload Configuration File']",true);
    }

	public void clickButton_UpdateConfigurationFile(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Update Configuration File']",true);
    }

	public void clickButton_ImportConfigurationFile(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Import Configuration File(s)']",true);
    }



	public void check_ConfigFileList(){
		rh.checkRadioButton("name=items_selected",true);
	}

	public void uncheck_ConfigFileList(){
		rh.checkRadioButton("name=items_selected",false);
	}

	public void clickButton_RemoveSelectedFiles(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Remove Selected Files']",true);
    }

	public void clickButton_CopyToSystems(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Copy to Systems']",true);
    }

	public void clickButton_CopytoChannels(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Copy to Channels']",true);
    }

	public void clickLink_SystemsForConfigChannel(){
        //rh.clickButton("xpath=//a[@contains(@href,'/rhn/configuration/channel/ChannelSystems.do?ccid=')]/@class",true);
		rh.clickButton("xpath=//a[contains(@href, '/rhn/configuration/channel/ChannelSystems.do?')]",true);
    }

	public void clickLink_TargetSystems(){
		rh.clickLink("link=Target Systems",true);
	}
	public void clickLink_DeployFile(){
		rh.clickLink("link=Deploy File",true);
	}

	public void clickLink_View(){
		rh.clickLink("link=View",true);
	}

	public void clickLink_SubscribedSystems(){
		rh.clickLink("link=Subscribed Systems",true);
	}

	/*public boolean is_ListRemoveLinkPresent(){
		return(sel.isElementPresent("link=List/Remove Files"));
	}*/
	public boolean is_ListRemoveLinkPresent(){
		return(rh.isElementPresent("link=List/Remove Files", true));
	}

	public void clickLink_UploadFile(){
		rh.clickLink("link=Upload File",true);
	}


	public void clickLink_ImportFiles(){
		rh.clickLink("link=Import Files",true);
	}
	public void clickLink_DeployAllConfigFilesToSelectedSystem(){
		rh.clickLink("link=Deploy all configuration files to selected subscribed systems",true);
	}

	public void clickLink_DeploySelectedConfigFilesToSelectedSystem(){
		rh.clickLink("link=Deploy selected configuration files to selected subscribed systems",true);
	}



	public void clickLink_CreateFile(){
		rh.clickLink("link=Create File",true);
	}

	public void clickLink_Compare(){
		rh.clickLink("link=Compare",true);
	}
	public void clickLink_OtherCopies(){
		rh.clickLink("link=Other Copies",true);
	}


	public void clickLink_OnlyChangedLines(){
		rh.clickLink("link=Only Changed Lines",true);
	}

	/*public void setTxt_FilterByFileName(String txt){
    	sel.type("xpath=//input[@name='filter_string']", txt);
    }*/
	public void setTxt_FilterByFileName(String txt){
		sel.setText("xpath=//input[@name='filter_string']", txt);
	}


	public void clickButton_Go(){
		rh.clickImage("xpath=//input[@type='image' and @value='filter']","Go",true);
	}

	/*public void checkRadio_TextFile(){
		sel.check("xpath=//input[@value='text']");
	}*/
	public void checkRadio_TextFile(){
		try{
		rh.checkRadioButton("xpath=//input[@value='text']",true);
		}
		catch(SeleniumException nfe){
			rh.checkRadioButton("xpath=//input[@value='false' and @name='binary']",true);
		}
	}

	public void checkRadio_Directory(){
		rh.checkRadioButton("xpath=//input[@value='directory']",true);
	}

	public void checkRadio_BinaryFile(){
		try{
		rh.checkRadioButton("xpath=//input[@value='binary']",true);
		}
		catch(SeleniumException nfe){
			rh.checkRadioButton("xpath=//input[@value='true' and @name='binary']",true);
		}
	}

	public void setTxt_FileContents(String txt){
    	//sel.type("name=contents", txt);
		sel.setText("name=contents", txt);
    }

	public void setTxt_FilePermissionsMode(String txt){
		sel.setText("name=cffPermissions", txt);
    }

	public void clickButton_CreateConfigurationFile(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Create Configuration File']",true);
    }
	public void clickButton_SubscribeSystems(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Subscribe systems']",true);
		//sel.clickAndWait("document.forms[1].dispatch[2]");
		//log.info("clicked button subscribe");
    }
	
	public void clickButton_ScheduleDeploy(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Schedule Deploy']",true);
	}
	
	public void clickButton_ScheduleFileDeployment(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Schedule File Deployment']",true);
    }
	public void clickButton_ConfirmAndDeployFile(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Confirm and Deploy File']",true);
    }
	public void clickButton_ConfirmAndDeployToSelectedSystems(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Confirm & Deploy to Selected Systems']",true);
    }

	public void clickButton_DeployFilesToSelectedSystems(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Deploy Files to Selected Systems']",true);
    }

	public void clickButton_ConfirmAndDeployMultipleFiles(){
      //  rh.clickButton("xpath=//input[@type='submit' and @name='dispatch']",true);
		  rh.clickButton("dom=document.forms[1].dispatch[3]",true);
    }
	public void clickButton_ConfirmAndDeployMultipleFiles1(){
	      //  rh.clickButton("xpath=//input[@type='submit' and @name='dispatch']",true);
			  rh.clickButton("dispatch",true);
	    }
	public void clickButton_Dispatch(){
	      //  rh.clickButton("xpath=//input[@type='submit' and @name='dispatch']",true);
			  rh.clickButton("dispatch",true);
	    }

	public void clickButton_UnsubscribeSystems(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Unsubscribe systems']",true);
    }


	public void clickLink_EnableConfigMangOnSystems(){
		rh.clickLink("link=Enable Configuration Management on Systems", true);
	}

	public void clickButton_DeleteConfigChannel(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Delete Config Channel']",true);
    }

	/**
	 * This selects a file in a list of files in a config channel
	 *
	 * @param file
	 */
	 public void select_File_Checkbox(String file){
		   rh.selectItemInRow(XPATH_CFG_MANG_CENT_CHANNEL_FILE, file);
	   }

	 public void select_Enable_CfgManagementTarget_Checkbox(String system, boolean hosted){
		 if(hosted)
			 rh.selectItemInRow(XPATH_CFG_MANG_HOSTED_TARGET_SYSTEM, system);
		 else
			 rh.selectItemInRow(XPATH_CFG_MANG_TARGET_SYSTEM, system);
	   }

	 /*public void clickLink_ViewComparison(){
			rh.clickLink("link=View Comparison",true);
		}*/

	 public void clickLink_ViewComparison(String channel, boolean hosted){
			//rh.clickLink("link=View Comparison",true);
		 if(hosted)
			 rh.clickLink_InRow(XPATH_CFG_MANG_HOSTED_CENT_CHANNEL_COMPARE, channel, 4);
		 else
		 	rh.clickLink_InRow(XPATH_CFG_MANG_CENT_CHANNEL_COMPARE, channel, 4);
		}

	 public String getText_ConfigMange_Status(String system, boolean hosted){
		 String status;
		 if(hosted)
			 status = (rh.getTableData(XPATH_CFG_MANG_HOSTED_TARGET_SYSTEM, system, 3));
		 else
			 status = (rh.getTableData(XPATH_CFG_MANG_TARGET_SYSTEM, system, 3));
		 return status;
	 }
	 
	 public void clickLink_Managed_Systems(){
		throw new SeleniumException("wrong page version");
		}

		public void clickLink_Target_Systems(){
			throw new SeleniumException("wrong page version");
		}

}
