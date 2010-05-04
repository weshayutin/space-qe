package com.redhat.rhn.harness.Sat42.pages;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.thoughtworks.selenium.SeleniumException;

public class SSMPage extends RhnPage{
	
	protected RhnHelper rh = new RhnHelper();
	
	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + "/network/systems/ssm/index.pxt";
    }
	
	public void clickLink_TopBar_Overview(){
       // rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div/ul/li/a]","Overview",true);
        rh.openLink(HarnessConfiguration.RHN_HOST+ "/network/systems/ssm/index.pxt", true, "Overview");
    }
	
	public void clickLink_TopBar_Systems(){
        rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div/ul/li[2]/a]","Systems",true);
    }
	
	public void clickLink_TopBar_Errata(){
        rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div/ul/li[3]/a]","Errata",true);
    }
	
	public void clickLink_TopBar_Packages(){
        rh.openLink(HarnessConfiguration.RHN_HOST+"/network/systems/ssm/packages/index.pxt",true,"Packages");
        ///network/systems/ssm/packages/index.pxt
    }
	
	public void clickLink_TopBar_Groups(){
        rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div/ul/li[5]/a]","Groups",true);
    }
	
	public void clickLink_TopBar_Channels(){
        rh.openLink(HarnessConfiguration.RHN_HOST+"/rhn/channel/ssm/ChildSubscriptions.do", true, "Channels");
    }
	
	public void clickLink_TopBar_Configuration(){
        rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div/ul/li[7]/a]","Configuration",true);
    }
	
	public void clickLink_TopBar_Provisioning(){
		 rh.openLink(HarnessConfiguration.RHN_HOST+ "/network/systems/ssm/provisioning/kickstart.pxt", true, "Provisioning");
        		
    }
	
	public void clickLink_TopBar_Misc(){
        rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div/ul/li[9]/a]","Misc",true);
    }
	
	public void clickButton_Remove(){
        rh.clickButton("xpath=//input[@value='Remove']",true);
    }
	
	public void clickButton_ApplyErrata(){
        rh.clickButton("xpath=//input[@value='Apply Errata']",true);
    }
	
	public void clickButton_AlterSubscriptions(){
        rh.clickButton("xpath=//input[@value='Alter Subscriptions']",true);
    }
	
	public void clickButton_Confirm(){
        rh.clickButton("xpath=//input[@value='Confirm']",true);
    }
	
	public void clickButton_Continue(){
        rh.clickButton("xpath=//input[@value='Continue']",true);
    }
	
	public void clickLink_InstallNewPackages(){
        rh.clickLink("Install new packages",true);
    }
	
	public void clickLink_UpgradeExistingPackages(){
        rh.clickLink("Upgrade existing packages",true);
    }
	
	public void clickLink_RemoveExistingPackages(){
        rh.clickLink("Remove existing packages",true);
    }
	
	public void clickLink_VerifyExistingPackages(){
        rh.clickLink("Verify existing packages",true);
    }

	public void clickLink_TopBar_Kickstart(){
        rh.clickLink("xpath://html/body/div/div[5]/table/tbody/tr/td[2]/div/div/div[2]/ul/li/a","Kickstart",true);
    }
	
	public void clickLink_TopBar_TagSystem(){
        rh.clickLink("link=Tag Systems",true);
    }
	
	public void clickLink_Rollback(){
        rh.clickLink("link=Rollback",true);
    }
	
	public void clickLink_RemoteCommand(){
        rh.clickLink("link=Remote Command",true);
    }
	
	public void setTxt_TagName(String txt){
		sel.setText("tag", txt);
	}
	
	public void clickButton_TagCurrentSnapshots(){
        rh.clickButton("xpath=//input[@value='Tag Current Snapshots']",true);
    }
	
	 public void select_SSM_BaseChannel_2(String channel){
			rh.selectComboBoxItem("base-for-101", channel, false);
	 }
	 
	 public void select_SSM_BaseChannel_1(String channel){
			rh.selectComboBoxItem("base-for-104", channel, false);
	 }
	 
	 public void clickLink_BaseChannels(){
	        rh.clickLink("link=Base Channels",true);
	    }
	 
	 public void clickLink_ChildChannels(){
	        rh.clickLink("link=Child Channels",true);
	    }
	 
	 public void clickButton_ConfirmSubscriptions(){
	        rh.clickButton("xpath=//input[@value='Confirm Subscriptions']",true);
	    }
	 
	 public void clickLink_Install(){
	        rh.clickLink("link=Install",true);
	    }
	 
	 public void clickLink_Upgrade(){
	        rh.clickLink("link=Upgrade",true);
	    }
	 
	 public void clickLink_Remove(){
	        rh.clickLink("link=Remove",true);
	    }
	 
	 public void clickLink_Verify(){
	        rh.clickLink("link=Verify",true);
	    }
	 
	 public void clickButton_InstallPackages(){
	        rh.clickButton("xpath=//input[@value='Install Packages']",true);
	    }
	 
	 public void clickButton_InstallSelectedPackages(){
	        rh.clickButton("xpath=//input[@value='Install Selected Packages']",true);
	    }
	 
	 public void clickButton_ScheduleUpdates(){
	        rh.clickButton("xpath=//input[@value='Schedule Updates']",true);
	    }
	 
	 public void clickButton_RemovePackages(){
	        rh.clickButton("xpath=//input[@value='Remove Packages']",true);
	    }
	 
	 public void clickButton_ScheduleRemovals(){
	        rh.clickButton("xpath=//input[@value='Schedule Removals']",true);
	    }
	 
	 public void clickButton_VerifyPackage(){
	        rh.clickButton("xpath=//input[@value='Verify Packages']",true);
	    }
	 
	 public void clickButton_ScheduleVerifications(){
	        rh.clickButton("xpath=//input[@value='Schedule Verifications']",true);
	    }


		public void clickLink_Configuation(){
			 rh.clickLink("xpath=//a[contains(@href, '/rhn/systems/ssm/config/Deploy.do')]",true);
	            }



		public void clickLink_DeployFiles(){
			rh.clickLink("link=Deploy Files", true);
		}
		public void clickLink_CompareFiles(){
			rh.clickLink("link=Compare Files", true);
		}
		public void clickLink_SubscribeToChannels(){
			rh.clickLink("link=Subscribe to Channels", true);
		}
		public void clickLink_UnsubscribeFromChannels(){
			rh.clickLink("link=Unsubscribe from Channels", true);
		}
		public void clickLink_EnableConfiguration(){
			rh.clickLink("link=Enable Configuration", true);
		}
		public void clickLink_LocallyManagedFiles(){
			rh.clickLink("link=Locally-Managed Files", true);
		}

		public void clickLink_Delete(){
			rh.clickLink("link=Delete", true);
		}

		public void clickLink_EditFile(){
			rh.clickLink("link=Edit", true);
		}

		public void setTxt_SDCFilterBy(String txt){
			sel.setText("list_104153177_filterval", txt);
		}
		public void check_FirstItemInList(){
			rh.checkRadioButton("name=items_selected",true);
		    }
		public void check_FirstItemInList1(){
			rh.checkRadioButton("list_104153177_1",true);
		    }

		public void clickLink_CompareAllManagedFilesToSystem(){
			rh.clickLink("link=Compare all managed files to system", true);
		}

		public void clickLink_DifferencesExist(){
			rh.clickLink("link=Differences exist", true);
		}

		public void clickLink_ViewDetailsSystemComparison(){
	        rh.clickLink("xpath=//a[contains(@href, '/network/systems/details/history/event.pxt?hid=472425')]",true);
	        log.info("<li> Click Link: View Details, System Comparison");
	    }
		public void clickButton_CopyLatestToSystemConfigChannel(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Copy Latest to System Config Channel']",true);
	    }
		public void clickButton_CopyLatestToCentralConfigChannel(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Copy Latest to Central Config Channel']",true);
	    }

		public void clickButton_Manage(){
			try{
	        rh.clickButton("xpath=//img[@alt='Manage']",true);
			}
			catch(SeleniumException onfe){
				rh.openLink(HarnessConfiguration.RHN_HOST +"/network/systems/ssm/index.pxt", true, "Manage Button");
			}
	    }

		public void clickButton_ConfirmDeletions(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Confirm Deletions']",true);
	    }

		public void clickButton_DeleteFiles(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Delete Files']",true);
	    }


		 public void clickLink_ChannelMemberships(){
				rh.clickLink("link=channel memberships",true);
			}

		 public void clickLink_BaseChannelAlteration(){
				rh.clickLink("link=Base Channel Alteration",true);
			}

		 public void select_DesiredBaseChannel(String channel){
		    	rh.selectComboBoxItem("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table/tbody/tr/td[3]/select","Desired Base Channel", channel, false);
		    	//example Default RH Base Channel
		 }

		 public void clickButton_ChangeBaseChannels(){
		        rh.clickButton("xpath=//input[@value='Change Base Channels']",true);
		 }

		 public void clickButton_ScheduleFileDeploy(){
		        rh.clickButton("xpath=//input[@value='Schedule File Deploy']",true);
		    }
	
			public void clickButton_ConfirmFileDeploy(){
		        rh.clickButton("xpath=//input[@value='Confirm File Deploy']",true);
		    }

	
	
	
	
}
