package com.redhat.rhn.harness.common.Sat42.tasks;

import org.testng.Assert;

import com.redhat.rhn.harness.Sat42.pages.KickStartPage;
import com.redhat.rhn.harness.Sat42.pages.RhnCommon;
import com.redhat.rhn.harness.Sat42.pages.SDCPage;
import com.redhat.rhn.harness.Sat42.pages.SatelliteSystemsPage;
import com.redhat.rhn.harness.Sat42.pages.SchedulePage;
import com.redhat.rhn.harness.baseInterface.IKickStart;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;

/**
 * Tasks used to create kickstart profiles and initiate kickstarts
 * Includeds virt guests too.
 * @author whayutin
 *
 */
public class KickStart extends SeleniumSetup { //implements IKickStart{

	protected RhnHelper rh = new RhnHelper();
	
	
	
	
	
	
	
	
	
	


	String kickstartLabel = "automationKickstart";
	String channel = "Red Hat Enterprise Linux (v. 5 for 32-bit x86)";
	String tree = "ks-rhel-i386-server-5";
	String treeURL = "http://fjs-0-13.rhndev.redhat.com/rhn/kickstart/ks-rhel-i386-server-5";
	String rootpwd = "dog8code";

/*	protected void CreateKickStartProfile(String system){
		
		rc.OpenAndLogIn();
        syspage.open();
        qs.goToSystemMethod(system);
        rc.clickLink_SystemName(system);
		CreateKickStartProfile_start(system);
	}*/

	public void DeleteKickstartProfile(String profileName, boolean openAndLogin){
		if(openAndLogin){
		task_RhnBase.OpenAndLogIn();
		}
        page_SatelliteSystems.open();
        page_KickStart.open();
        page_KickStart.clickLink_ViewListKickstartProfiles();
        if(rh.isTextPresent(profileName)){
        	//rh.clickLink(profileName,true);
        	rh.clickSystemProfileLink(profileName);
        	page_KickStart.clickLink_DeleteKickstart();
        	page_KickStart.clickButton_DeleteKickstart();
        }
	}

	public void AddActivationKeyToKickstartProfile(String profileName){
		
		task_RhnBase.OpenAndLogIn();
        page_SatelliteSystems.open();
        page_KickStart.open();
        page_KickStart.clickLink_ViewListKickstartProfiles();
        if(rh.isTextPresent(profileName)){
        	rh.clickSystemProfileLink(profileName);
        	page_KickStart.clickLink_ActivationKeys();
        	page_RhnCommon.check_FirstItemInList();
        	page_KickStart.clickButton_UpdateActivationKeys();
        }
	}


	/**
	 * Takes in a string representing kickstarted machine's ip/hostname
	 * and returns a boolean True if the machine has completed Kickstart
	 * and a boolean False if not.
	 *
	 * @param system String representing kickstarted system
	 * @return boolean representing completion of kickstart process
	 */
	protected boolean kickstart_Status(String system){
		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_Kickstart();
		String myStatus = page_KickStart.getText_SystemKickstart_Status(system);
		// xpath=//div[@id='content']/table/tbody/tr/td[@class='page-content']/table[@class='list']
		// xpath=//div[@style='clear: both; padding-top: 30px;']/table[@class='list']

		//System.out.println(rh.getTableData("xpath=//div[@id='content']/table/tbody/tr/td[@class='page-content']/table[@class='list']", "dhcp230.rhndev.redhat.com", 4));
		//System.out.println(rh.getTableData("xpath=//div[@id='content']/table/tbody/tr/td[@class='page-content']/table[@class='list']", "fjs-0-06.rhndev.redhat.com", 4));
		if(myStatus !=""){
			if(myStatus.contains("complete")){
				log.info("<li>Kickstart has completed, waiting for machine to open SSH ports.");
				return true;
			}
			log.info("<li>Kickstart status: "+myStatus);
			return false;
		}
		log.info("<li>System not yet kickstarted.");
		return false;

		//POTENTIAL STATUS MESSAGE (incomplete)
		//The system has been restarted in order to begin the kickstart process.
		 //The system has downloaded the kickstart configuraton file from RHN.
		//Kickstart complete
	}

	protected boolean kickstart_Status(String system, String autoKS){
		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_Kickstart();
		String myStatus = page_KickStart.getText_SystemKickstart_Status(system, autoKS);
		// xpath=//div[@id='content']/table/tbody/tr/td[@class='page-content']/table[@class='list']
		// xpath=//div[@style='clear: both; padding-top: 30px;']/table[@class='list']

		//System.out.println(rh.getTableData("xpath=//div[@id='content']/table/tbody/tr/td[@class='page-content']/table[@class='list']", "dhcp230.rhndev.redhat.com", 4));
		//System.out.println(rh.getTableData("xpath=//div[@id='content']/table/tbody/tr/td[@class='page-content']/table[@class='list']", "fjs-0-06.rhndev.redhat.com", 4));
		if(myStatus !=""){
			if(myStatus.contains("complete")){
				log.info("<li>Kickstart has completed, waiting for machine to open SSH ports.");
				return true;
			}
			log.info("<li>Kickstart status: "+myStatus);
			return false;
		}
		log.info("<li>System not yet kickstarted.");
		return false;

		//POTENTIAL STATUS MESSAGE (incomplete)
		//The system has been restarted in order to begin the kickstart process.
		 //The system has downloaded the kickstart configuraton file from RHN.
		//Kickstart complete
	}


	public void kickstartWaitforKickstartCompletion(String system, String kickstartProfileName, boolean openAndLogin){
		
		String myStatus;
		if(openAndLogin){
		task_RhnBase.OpenAndLogIn();
		}
		task_Search.goToSystem(system);
	    page_RhnCommon.clickLink_SystemName(system);
	    page_SDC.clickLink_Provisioning();
        page_SDC.clickLink_SessionStatus();
        Assert.assertTrue(rh.isTextPresent(kickstartProfileName));

		log.info("STATUS_FROM_SDC myStatus = "+page_KickStart.getText_SDC_Kickstart_Status());
		while(!page_KickStart.getText_SDC_Kickstart_Status().equalsIgnoreCase("Kickstart complete.")){
	        if(rh.isTextPresent("Kickstart failed")){
	        	Assert.assertTrue(rh.isTextNotPresent("Kickstart failed"));
	        	break;
	        }
	        log.info("waiting for kickstart: Status = " + page_KickStart.getText_SDC_Kickstart_Status());
	    	rh.sleepForSeconds(240);
	    	task_Search.goToSystem(system);
	 	    page_RhnCommon.clickLink_SystemName(system);
	 	    page_SDC.clickLink_Provisioning();
	        page_SDC.clickLink_SessionStatus();
	     }
		log.info("Kickstart is complete");
	}




	public void virtGuest_Action(String system,String guestLabel,boolean openAndLogin, boolean runRhnCheck, int action){
			
			if(openAndLogin)
				task_RhnBase.OpenAndLogIn();
			 log.info("DEBUG: Starting Virt Guest");
	         page_SatelliteSystems.open();
			 task_Search.goToSystem(system);
			 page_RhnCommon.clickLink_SystemName(system);
			 page_SDC.clickLink_Virtualization();
			 Assert.assertTrue(rh.isTextPresent("Hosted Virtual Systems"));
			 page_RhnCommon.setTxt_FilterBy_notry(guestLabel);
			 page_RhnCommon.clickButton_Filter_Go();
			 Assert.assertTrue(rh.isTextPresent(guestLabel));
			 //log.info("WebUI reports guest system is "+ sdc.getTxt_VirtGuest_Web_Status());
			 page_RhnCommon.check_FirstItemInList();
			// rh.selectItemInRow(sdc.XPATH_VIRT_GUEST_LIST,guestLabel); not working for this table yet
			 if(action == IRhnBase.VIRT_GUEST_START)
				 page_SDC.select_Virt_GuestAction_Start();
			 if(action == IRhnBase.VIRT_GUEST_STOP)
				 page_SDC.select_Virt_GuestAction_Shutdown();
			 if(action == IRhnBase.VIRT_GUEST_DESTROY)
				 page_SDC.select_Virt_GuestAction_Delete();
			 page_SDC.clickButton_Virt_ApplyAction();
			 page_SDC.clickButton_Confirm();
			 if(runRhnCheck){
				
				 task_TestPrep.command_xmList(system, true);
				// tp.command_generic("ps", "ef | grep rhn_check", system, true);//shouldnt have to do this
				// tp.command_generic("killall", "-9 rhn_check", system, true);//shouldnt have to do this
				 task_TestPrep.command_runRhnCheckWithAT(system, false);
				 task_TestPrep.command_xmList(system, true);
				 page_SatelliteSystems.open();
				 task_Search.goToSystem(system);
				 page_RhnCommon.clickLink_SystemName(system);
				 page_SDC.clickLink_Virtualization();
				 task_TestPrep.command_xmList(system, true);
				 task_TestPrep.command_tailLog(system, "/var/log/xen/xend.log");
			 }
			 //rh.waitForStatus("This action's status is: Completed.", "Package Install scheduled*", true);
			 if(action == IRhnBase.VIRT_GUEST_START)
				 rh.waitForStatus("Completed.", "Starts up a Xen domain. scheduled by*", true, system, 5);

			 if(action == IRhnBase.VIRT_GUEST_STOP)
				 rh.waitForStatus("Completed.", "Shuts down a Xen domain. scheduled by*", true, system, 5);


	}

	public void virtGuest_Status(String system,String guestLabel,boolean openAndLogin, String Status){
		
		if(openAndLogin)
			task_RhnBase.OpenAndLogIn();
		 log.info("DEBUG: Starting Virt Guest");
         page_SatelliteSystems.open();


		 for(int x=0;x<5;x++){
			 task_Search.goToSystem(system);
			 page_RhnCommon.clickLink_SystemName(system);
			 page_SDC.clickLink_Virtualization();
			 Assert.assertTrue(rh.isTextPresent("Hosted Virtual Systems"));
			 page_RhnCommon.setTxt_FilterBy_notry(guestLabel);
			 page_RhnCommon.clickButton_Filter_Go();
			 Assert.assertTrue(rh.isTextPresent(guestLabel));
			 if(rh.isTextPresent(Status)){
				 log.info("action is complete");
				 break;
			 }
			 log.info("action is NOT complete, trying again");
		 }
		 Assert.assertTrue(rh.isTextPresent(Status));
	}

	public String getVirtGuest_HostName(String system,String guestLabel,boolean openAndLogin){
	
		String guestName=null;
		if(openAndLogin)
			task_RhnBase.OpenAndLogIn();

         page_SatelliteSystems.open();
		 task_Search.goToSystem(system);
		 page_RhnCommon.clickLink_SystemName(system);
		 page_SDC.clickLink_Virtualization();
		 page_RhnCommon.setTxt_FilterBy_notry(guestLabel);
		 page_RhnCommon.clickButton_Filter_Go();
		 guestName = page_SDC.getTxt_VirtGuest_Hostname();
         return guestName;
	}


	public String get_BareMetal_KickstartUrl(String ksProfile){
		String url = null;
		page_SatelliteSystems.open();
        page_KickStart.open();
        page_KickStart.clickLink_ViewListKickstartProfiles();
        if(rh.isTextPresent(ksProfile)){
        	rh.clickSystemProfileLink(ksProfile);
        }
        page_KickStart.clickLink_BareMetalKickstart();
        url = page_KickStart.getTxt_BareMetalKickstartUrl();
        log.fine("Bare Metal Url = "+url);

		return url;
	}

	
		public void createKickstartDistribution(String label, String externalUrl,String baseChannel, String kickstartRPM, String installerGeneration){
			page_SatelliteSystems.open();
	        page_KickStart.open();
	        page_KickStart.clickLink_Distributions();
	        page_KickStart.clickLink_create_new_distribution();
	        page_KickStart.setTxt_DistributionLabel(label);
	        page_KickStart.setTxt_ExternalLocationUrl(externalUrl);
	        page_KickStart.select_DistributionBaseChannel(baseChannel, true);
	        page_KickStart.select_KickStart_RPM(kickstartRPM, false);
	        page_KickStart.select_KickStart_InstallerGeneration(installerGeneration, false);
	        page_KickStart.clickButton_CreateKickstartDistribution();
		
		}
		
		public void createKickstartDistribution(String label, String externalUrl,String baseChannel, String installerGeneration){
			throw new SeleniumException("check your satellite version, this method is not applicable to this version");
		}
		
		
		
		public void deleteKickstartDistribution(String label){
			page_SatelliteSystems.open();
	        page_KickStart.open();
	        page_KickStart.clickLink_Distributions();
	        if(rh.isTextPresent(label)){
	        	rh.clickLink("link="+label, true);
	        	page_KickStart.clickLink_deletedistribution();
	        	page_KickStart.clickButton_DeleteDistribution();
	        	assertTrue(rh.isTextPresent("Kickstart Distribution deleted successfully."));
	        }
			
		}
		
		



}

	


