package com.redhat.rhn.harness.Sat42.pages;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.thoughtworks.selenium.SeleniumException;

/**
 *  Collection of objects (links,buttons,etc..) from the RHN Satellite System Details Page
 *  or SDC Page.  There are a lot of various functions housed in the SDC.
 *
 * @author whayutin
 *
 */
public class SDCPage extends RhnPage {

	RhnHelper rh = new RhnHelper();

	public static String XPATH_CFG_MANG_SYSTEM_FILE="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table";
	public static String XPATH_LIST_PACKAGES="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[3]";
	public static String XPATH_VIRT_GUEST_LIST="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]/tbody";///tr/td[2]";
													///html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]/tbody/tr/td[2]


	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SYSTEMS_PAGE;
    }

	public void clickLink_Configuation(){
        rh.clickLink("xpath=//a[contains(@href, '/rhn/systems/details/configuration/Overview.do?sid=')]","Configuration",true);
    }
	
	public void clickLink_CustomInfo(){
        rh.clickLink("xpath=//a[contains(@href, '/network/systems/details/custominfo/index.pxt')]","Custom Info",true);
    }
	
	public void clickLink_CustomInfo_createNewValue(){
        rh.clickLink("xpath=//a[contains(@href, '/network/systems/details/custominfo/new_value.pxt?sid=')]","create new value",true);
    }
	
	public void clickLink_CustomInfo_deleteValue(){
        rh.clickLink("xpath=//a[contains(@href, '/network/systems/details/custominfo/remove_value_conf.pxt')]","create new value",true);
    }
	
	public void clickLink_CustomInfo_deleteKey(){
		rh.clickLink("delete key", true);
	}
	
	 public void setTxt_CustomInfoKey_value(String txt){
		  sel.setText("value", txt);
	 }
	 
	public void clickLink_Details(){
		rh.clickLink("Details", true);
	}
	
	public void clickLink_Hardware(){
		rh.clickLink("Hardware", true);
	}

	public void clickLink_Provisioning(){
		rh.clickLink("Provisioning", true);
	}

	public void clickLink_Virtualization_Provisioning(){
        rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[2]/div/div[2]/ul/li[2]/a",true);
    }

	public void clickLink_Virtualization(){
		rh.clickLink("Virtualization", true);
	}

	public void clickLink_SessionStatus(){
		rh.clickLink("Session Status", true);
		//Session Status
	}

	public void clickLink_Pending(){
		rh.clickLink("Pending", true);
	}

	public void clickLink_Software(){
		rh.clickLink("Software", true);
	}

	public void clickLink_ListRemove(){
		rh.clickLink("List / Remove", true);
	}

	public void clickLink_Packages(){
		rh.clickLink("Packages", true);
	}
	
	public void clickLink_Patches(){
		rh.clickLink("Patches", true);
	}
	
	public void clickLink_PatchClusters(){
		rh.clickLink("Patch Clusters", true);
	}

	public void clickLink_Upgrade(){
		rh.clickLink("Upgrade", true);
	}

	public void clickLink_Groups(){
		rh.clickLink("Groups",true);
	}
	public void clickLink_Groups_ListLeave(){
		rh.clickLink("list/leave",true);
	}
	public void clickLink_Groups_Join(){
		rh.clickLink("Join",true);
	}

	public void clickLink_Install(){
		rh.clickLink("Install", true);
	}

	public void clickLink_Verify(){
		rh.clickLink("Verify", true);
	}

	public void clickLink_Profiles(){
		rh.clickLink("Profiles", true);
	}
	
	public void clickLink_Monitoring_InMenu(){
		rh.clickLink("xpath=//td[2]/div[2]/ul/li[5]/a", "Monitoring", true);
		//511					 /html/body/div/div[5]/table/tbody/tr/td[2]/div[3]/ul/li[2]/a
		//td[2]/div[2]/ul/li[3]/a //422 external
	}

	public void clickLink_Errata(){
		rh.clickLink("xpath=//a[contains(@href, '/rhn/systems/details/ErrataList.do?sid=')]", "Errata",true);
		///rhn/systems/details/ErrataList.do?sid=1000010003
	}

	public void clickLink_SnapShotTags(){
		rh.clickLink("Snapshot Tags", true);
	}
	
	public void clickLink_CreateNewNote(){
		rh.clickLink("xpath=//a[contains(@href, '/network/systems/details/notes/edit.pxt?sid=')]", "create new note",true);
	}
	
	public void setTxt_Note_subject(String subject){
		sel.setText("xpath=//tr/td[2]/form/table/tbody/tr/td/input", "Subject:", subject);
	}
	
	public void setTxt_Note_Details(String details){
		sel.setText("xpath=//tr/td[2]/form/table/tbody/tr[2]/td/textarea", "Details:", details);
	}
	
	public void clickButton_NoteCreate(){
		 rh.clickButton("xpath=//input[@value='Create']","Create",true);
	}

	public void clickLink_SnapShots(){
		rh.clickLink("Snapshots", true);
	}

	public void clickLink_CreateNewSystemTag(){
		rh.clickLink("create new system tag", true);
	}

	public void setTxt_TagName(String txt){
		sel.setText("tag", txt);
	}

	public void clickButton_TagCurrentSnapshot(){
        rh.clickButton("xpath=//input[@value='Tag Current Snapshot']",true);
    }

	public void clickButton_Virt_ApplyAction(){
        rh.clickButton("xpath=//input[@value='Apply Action']",true);
    }

	public void clickButton_UpdatePackageList(){
        rh.clickButton("xpath=//input[@value='Update Package List']",true);
    }

	public void clickButton_UpgradePackage(){
        rh.clickButton("xpath=//input[@value='Upgrade Packages']",true);
    }

	public void clickButton_VerifySelectedPackages(){
        rh.clickButton("xpath=//input[@value='Verify Selected Packages']",true);
    }
	
	public void clickButton_CustomInfo_UpdateKey(){
        rh.clickButton("xpath=//input[@value='Update Key']",true);
    }

	public void clickButton_Groups_JoinSelectedGroups(){
        rh.clickButton("xpath=//input[@value='Join Selected Groups']",true);
    }

	public void clickButton_InstallSelectedPackages(){
        rh.clickButton("xpath=//input[@value='Install Selected Packages']",true);
    }

	public void clickButton_ApplyErrata(){
        rh.clickButton("xpath=//input[@value='Apply Errata']",true);
    }

	public void clickButton_CreateSystemProfile(){
        rh.clickButton("xpath=//input[@value='Create System Profile']",true);
    }

	public void clickButton_CreateProfile(){
        rh.clickButton("xpath=//input[@value='Create Profile']",true);
    }

	public void clickButton_Confirm(){
        rh.clickButton("xpath=//input[@value='Confirm']",true);
    }
	
	public void clickButton_Delete(){
        rh.clickButton("xpath=//input[@value='Delete']",true);
    }
	
	public void clickButton_ChangeSubscriptions(){
        rh.clickButton("xpath=//input[@value='Change Subscriptions']",true);
    }
	
	public void clickButton_InstallPatchCluster(){
		rh.clickButton("xpath=//input[@value='Install Patch Cluster']",true);
	}
	public void clickButton_SyncSystemPackages(String system){
		rh.clickButton("xpath=//input[contains(@value, 'Sync Packages To')]", true);
				// \\'"+system+"\\'']", true);
	}
	public void clickButton_ScheduleSync(){
		rh.clickButton("xpath=//input[@value='Schedule Sync']",true);
	}

	public void clickButton_Manage(){
        rh.clickLink("Manage", true);
    }

	public void clickButton_ConfirmUpgrade(){
        rh.clickButton("xpath=//input[@value='Confirm Upgrade']",true);
    }

	public void clickButton_Compare(){
        rh.clickButton("xpath=//input[@value='Compare']",true);
    }

	public void clickButton_RollbackToSnapshot(){
        rh.clickButton("xpath=//input[@value='Rollback to Snapshot']",true);
    }


	public void clickLink_Schedule(){
		rh.clickLink("xpath=//a[contains(@href, '/rhn/systems/details/kickstart/ScheduleWizard.do?sid=')]","Schedule", true);
	}

	public void clickLink_ViewModifyFiles(){
		rh.clickLink("View/Modify Files", true);
	}
	public void clickLink_AddFiles(){
		rh.clickLink("Add Files", true);
	}
	public void clickLink_AlterChannelSubscriptions(){
		rh.clickLink("Alter Channel Subscriptions", true);
	}
	
	public void select_BaseChannel(String channelname){
		//cheating here, but picking by order this is the
		// first listed child channel
		//rh.checkRadioButton("xpath=//ul/li/ul/li/input",channelname, true);
		
		rh.selectComboBoxItem("name=new_base_channel_id", channelname, false);
	}
	
	public void check_ChildChannel02(String channelname){
		
		rh.selectComboBoxItem("name=new_base_channel_id", channelname, false);
	}

	public void clickLink_DeployAllFiles(){
		rh.clickLink("link=Deploy all managed config files", true);
	}
	
	public void clickLink_DeployFiles(){
		rh.clickLink("Deploy Files", true);
	}
	public void clickLink_CompareFiles(){
		rh.clickLink("Compare Files", true);
	}
	public void clickLink_ManageConfigChannels(){
		rh.clickLink("Manage Configuration Channels", true);
	}

	public void clickLink_ListUnsubscribeFromChannels(){
		rh.clickLink("List/Unsubscribe from Channels", true);
	}
	public void clickLink_SubscribeToChannels(){
		rh.clickLink("Subscribe to Channels", true);
	}
	public void clickLink_ViewModifyRankings(){
		rh.clickLink("View/Modify Rankings", true);
	}

	public void clickLink_LocallyManagedFiles(){
		rh.clickLink("Locally-Managed Files", true);
	}
	public void clickLink_EditFile(){
		rh.clickLink("Edit", true);
	}

	public void setTxt_SDCFilterBy(String txt){
		/*try{
			//sel.setText("list_104153177_filterval", txt);
			sel.setText("xpath=//form/div/table/tbody/tr/td/input[2]", txt);
		}
		catch(SeleniumException se){
			sel.setText("filter_string", txt);
		}*/
		assertTrue(rh.isTextPresent("Filter by"));
    	String locator = "xpath=//td[starts-with(normalize-space(.),'Filter')]/input[@type='text']";
    	sel.setText(locator, "Filter by :", txt);
    	
		
	}

	public void setTxt_ProfileName(String txt){
		sel.setText("name", txt);
	}
	
	public void setTxt_FacilityAddress(String txt){
		sel.setText("address1", txt);
	}
	
	public void setTxt_FacilityCity(String txt){
		sel.setText("city", txt);
	}
	
	public void setTxt_FacilityState(String txt){
		sel.setText("state", txt);
	}
	
	public void setTxt_FacilityBuilding(String txt){
		sel.setText("building", txt);
	}
	
	public void setTxt_FacilityRoom(String txt){
		sel.setText("room", txt);
	}
	
	public void setTxt_FacilityRack(String txt){
		sel.setText("rack", txt);
	}



	public void setTxt_ProfileDescription(String txt){
		sel.setText("description",txt);
	}

	public void setTxt_Virtualization_GuestName(String txt){
		sel.setText("guestName",txt);
	}

	public void setTxt_Virtualization_MemoryAllocation(String txt){
		sel.setText("memoryAllocation",txt);
	}

	public void setTxt_Virtualization_VirtualCPUS(String txt){
		sel.setText("virtualCpus",txt);
	}
	
	public void setTxt_Virtualization_Storage(String txt){
		sel.setText("localStorageMegabytes",txt);
	}

	public void virtSelectRHNProxy(String item){
		rh.selectComboBoxItem("proxyHost",item, false);
	}

	public void select_ProfileName(String item){
		rh.selectComboBoxItem("profile", item, false);
	}
	
	public void select_SystemProfileName(String item){
		rh.selectComboBoxItem("server", item, false);
	}

	public void clickButton_FilterGo(){
		if(rh.isElementPresent("alt=Filter", false)){
			rh.clickButton("alt=Filter", true);
		}
		else{
			rh.clickButton("xpath=//input[@type='submit' and @value='Go']",true);
		}
    }
	public void check_FirstItemInList(){
		rh.checkRadioButton("name=items_selected",true);
	    }
	public void check_FirstItemInList1(){
		rh.checkRadioButton("list_104153177_1",true);
	    }

	public void clickLink_CompareAllManagedFilesToSystem(){
		rh.clickLink("Compare all managed files to system", true);
	}

	public void clickLink_DifferencesExist(){
		rh.clickLink("Differences exist", true);
	}

	public void clickLink_Events(){
		rh.clickLink("Events",true);
	}
	public void clickLink_ErrataUpdateScheduled(){
		rh.clickLink("Errata Update scheduled by admin",true);
	}
	public void clickLink_History(){
		rh.clickLink("History",true);
	}
	public void clickLink_ShowDiffHistory(){
		rh.clickLink("Show differences between profiled config files and deployed config files scheduled by*",true);
	}

	public void clickLink_ViewDetailsSystemComparison(){
        rh.clickLink("xpath=//a[contains(@href, '/network/systems/details/history/event.pxt?hid=')]",true);
        log.info("<li> Click Link: View Details, System Comparison");
    }

	public void clickButton_CopyLatestToSystemChannel(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Copy Latest to System Channel']",true);
    }

	public void clickButton_Reschedule(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Reschedule']",true);
    }

	public void clickButton_ModifyBaseChannel(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Modify Base Channel']",true);
    }

	public String getEventDetails(){
		String details = "";
		details=(sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/table/tbody/tr[2]/td"));
		return details;
	}

	public void clickButton_CopyLatestToCentralChannel(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Copy Latest to Central Channel']",true);
    }
	public void clickButton_ScheduleDeploy(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Schedule Deploy']",true);
    }
	public void clickButton_DeleteFiles(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Delete Files']",true);
    }
	public void clickButton_RemovePackages(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Remove Packages']",true);
    }

	 public void select_File_Checkbox(String file){
		   rh.selectItemInRow(XPATH_CFG_MANG_SYSTEM_FILE, file);
	 }
	 public void select_Software_Base_Channel(String channel){
			rh.selectComboBoxItem("system_base_channel", channel, false);
	 }

	 public void select_Virt_GuestAction_Start(){
			rh.selectComboBoxItem("guestAction", "Start Systems", false);
	 }
	 public void select_Virt_GuestAction_Delete(){
			rh.selectComboBoxItem("guestAction", "Delete Systems", false);
	 }

	 public void select_Virt_GuestAction_Suspend(){
			rh.selectComboBoxItem("guestAction", "Suspend Systems", false);
	 }
	 public void select_Virt_GuestAction_Resume(){
			rh.selectComboBoxItem("guestAction", "Resume Systems", false);
	 }
	 public void select_Virt_GuestAction_Shutdown(){
			rh.selectComboBoxItem("guestAction", "Shutdown Systems", false);
	 }

	 public String getTxt_VirtGuest_Hostname(){
		 return (sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]/tbody/tr/td[3]/a"));
	 }

	 public String getTxt_VirtGuest_Web_Status(){
		 return (sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]/tbody/tr/td[5]"));
	 }


	 public void clickLink_EditInRow(String file){
			//rh.clickLink("View Comparison",true);
		 	rh.clickLink_InRow(XPATH_CFG_MANG_SYSTEM_FILE, file, 3);
		 	System.out.println("break");
		}
	 
	 public void clickButton_ModifyBaseSoftwareChannel(){
	      throw new SeleniumException("wrong version of page");
	    }

}
