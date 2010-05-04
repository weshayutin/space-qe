	package com.redhat.rhn.harness.common.octokick;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

import org.testng.Assert;

import com.redhat.qe.tools.ExecCommands;
import com.redhat.rhn.harness.Sat42.pages.KickStartPage;
import com.redhat.rhn.harness.Sat42.pages.RhnCommon;
import com.redhat.rhn.harness.Sat42.pages.SDCPage;
import com.redhat.rhn.harness.Sat42.pages.SatelliteSystemsPage;
import com.redhat.rhn.harness.Sat42.pages.SchedulePage;
import com.redhat.rhn.harness.Sat42.pages.SystemProvisionPage;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.redhat.rhn.harness.common.Sat42.tasks.ActivationKeys;
import com.redhat.rhn.harness.common.Sat42.tasks.RhnBase;
import com.redhat.rhn.harness.common.Sat42.tasks.Search;
import com.redhat.rhn.harness.common.Sat42.tasks.TestPrep;
import com.thoughtworks.selenium.SeleniumException;

public class OKickStart extends SeleniumSetup{
	public static final int PKG_COMPLETE = 2;
	public static final int PKG_FAIL = 1;
	public static final int PKG_INPROGRESS = 0;
	
	public RhnHelper rh = new RhnHelper();
	
	

	//RhnBase rb = new RhnBase();
	/**
	 * Instance variable holding master root password
	 */
	private String rootpwd;
	/**
	 * Instance variable holding master kickstart label (representing test name)
	 */
	private String kickstartLabel;

	public OKickStart(){
		return;
	}

	public OKickStart(String rootpwd, String testName){
		this.rootpwd = rootpwd;
		this.kickstartLabel = testName;
	}

	private String createKickStartProfilePartOne(String system,
													String channel,
													String tree,
													String virtHost,
													String virtGuest,
													boolean staticProfileName){
		int n = 1000000;
		Random generator = new Random();
		int rand = generator.nextInt(n);
		String kickstartLabel = tree+"-"+Integer.toString(rand);
		if(staticProfileName){
			kickstartLabel="automation";
		}
		
		page_SatelliteSystems.open();
		task_Search.goToSystem(system);
		if(rh.isTextNotPresent(system) && virtGuest.contentEquals("false")){
			task_TestPrep.registerSystem(system,IRhnBase.RHN_SAT_REG_CMD, true, true);
		}
		task_TestPrep.command_updateSystemProfile(system);
		task_TestPrep.enableProvisioning(system, false);
		if((virtGuest.contentEquals("true")) || (virtHost.contentEquals("true"))){
			task_TestPrep.enableVirtualizationPlatform(system, true);
			rh.isTextPresent("Since you added a Virtualization entitlement to the system");
			task_TestPrep.command_runRhnCheckInForeground(system, true);
		}
		page_KickStart.open();
		page_KickStart.clickLink_ViewListKickstartProfiles();
		page_KickStart.openLink_CreateNewKickstartProfile();
		page_KickStart.setTxt_KickstartProfileLabel(kickstartLabel);
		page_KickStart.select_BaseChannel(channel,true);
		page_KickStart.select_KickstartableTree(tree);
		if((virtGuest.contentEquals("true")))
			page_KickStart.select_ParavirtualizedGuestOption();
		if((virtHost.contentEquals("true")))
			page_KickStart.select_ParavirtualizedHostOption();
		return kickstartLabel;
	}
	
	private String createKickStartProfilePartOne(
												String system,
												String channel,
												String tree,
												String ksLabel,
												String virtHost,
												String virtGuest,
												boolean staticProfileName){
					int n = 1000000;
					Random generator = new Random();
					int rand = generator.nextInt(n);
					String kickstartLabel = tree+"-"+Integer.toString(rand);
					if(staticProfileName)
					kickstartLabel=ksLabel;
					
					task_TestPrep.enableProvisioning(system, false);
					if((virtGuest.contentEquals("true")) || (virtHost.contentEquals("true"))){
					task_TestPrep.enableVirtualizationPlatform(system, true);
					rh.isTextPresent("Since you added a Virtualization entitlement to the system");
					task_TestPrep.command_runRhnCheckInForeground(system, true);
					}
					page_KickStart.open();
					page_KickStart.clickLink_ViewListKickstartProfiles();
					page_KickStart.openLink_CreateNewKickstartProfile();
					page_KickStart.setTxt_KickstartProfileLabel(kickstartLabel);
					page_KickStart.select_BaseChannel(channel,true);
					
					page_KickStart.select_KickstartableTree(tree);
					if(virtGuest.contentEquals("true")) page_KickStart.select_ParavirtualizedGuestOption();
					if(virtHost.contentEquals("true")) page_KickStart.select_ParavirtualizedHostOption();
					return kickstartLabel;
					}
	
	

	public String testCreateKickStartProfile(String system,
									  	     String channel,
									  	     String tree,
									  	     String ksLabel,
									  	     String sshKey,
									  	     String virtHost,
									  	     String virtGuest, boolean staticProfileName){
		String kickstartLabel;

		page_SatelliteSystems.open();
		 if(HarnessConfiguration.RHN_OSAD_ENABLED == 1){
	        	task_ActivationKeys.createActivationKeyWithOSAD("kickkey", "auto123", "500");
	        }
		page_SatelliteSystems.open();
		log.info("virtHost ="+virtHost);
		log.info("virtGuest ="+virtGuest);
		if(staticProfileName){
			kickstartLabel=(createKickStartProfilePartOne(system, channel, tree,ksLabel,virtHost,virtGuest, true));
		}
		else{
			kickstartLabel=(createKickStartProfilePartOne(system, channel, tree,virtHost,virtGuest, false));
		}


        page_KickStart.clickButton_Next();
        page_KickStart.clickButton_Next();
        page_KickStart.setTxt_RootPassword(IRhnBase.PASSWORD);
        page_KickStart.setTxt_RootPasswordConfirm(IRhnBase.PASSWORD);
        page_KickStart.clickButton_Finish();
        
        this.addScriptsToKickstartProfile(sshKey);

        return kickstartLabel;
	}

	public void addScriptsToKickstartProfile(String sshKey){
		page_KickStart.clickLink_SystemDetails();
		if(HarnessConfiguration.SATELLITE_VERSION.equalsIgnoreCase("5.3")){
			log.info("sat5.3 has link option removed from profile");
		}
		else{
			page_KickStart.setTxt_NetworkInterface("link");
		}
        
        page_KickStart.clickButton_UpdateSystemDetails();
        Assert.assertTrue(rh.isTextPresent("Kickstart profile options updated successfully."));

        page_KickStart.clickLink_Scripts();
        page_KickStart.clickLink_AddNewKickstartScript();
        page_KickStart.setTxt_ScriptingLanguage("/bin/bash");
        page_KickStart.setTxt_ScriptingContents("(cd /tmp; wget -O fix-promise.sh http://ks.rhndev.redhat.com/dmraid/fix-promise.sh; bash fix-promise.sh) >> /tmp/autokick.log");
        page_KickStart.select_ScriptExecutionTime("Post Script");
        page_KickStart.clickButton_UpdateKickstart();
        Assert.assertTrue(rh.isTextPresent("Kickstart Script successfully updated."));

        page_KickStart.clickLink_Scripts();
        page_KickStart.clickLink_AddNewKickstartScript();
        page_KickStart.setTxt_ScriptingLanguage("/bin/bash");
        page_KickStart.setTxt_ScriptingContents("(mkdir /root/.ssh; echo \""+sshKey+"\" >> /root/.ssh/authorized_keys) >> /tmp/autokick.log");
        page_KickStart.select_ScriptExecutionTime("Post Script");
        page_KickStart.clickButton_UpdateKickstart();
        Assert.assertTrue(rh.isTextPresent("Kickstart Script successfully updated."));
        page_KickStart.clickLink_SystemDetails();
        page_KickStart.clickLink_Troubleshooting();
        String kerneltxt = (HarnessConfiguration.RHN_KERNEL_PARAM1 + " " + HarnessConfiguration.RHN_KERNEL_PARAM2);
        page_KickStart.setTxt_TroubleshootingKernelParameters(kerneltxt);
        page_KickStart.clickButton_UpdateOptions();


        if(HarnessConfiguration.RHN_OSAD_ENABLED == 1){
        	page_KickStart.clickLink_Scripts();
        	page_KickStart.clickLink_ActivationKeys();
        	rh.isTextPresent("incompatible activation");
        	page_RhnCommon.check_FirstItemInList();
        	page_KickStart.clickButton_UpdateActivationKeys();

        	page_KickStart.clickLink_Scripts();
            page_KickStart.clickLink_AddNewKickstartScript();
            page_KickStart.setTxt_ScriptingLanguage("/bin/bash");
            page_KickStart.setTxt_ScriptingContents("/usr/sbin/up2date-nox --nosig osad");
            page_KickStart.select_ScriptExecutionTime("Post Script");
            page_KickStart.clickButton_UpdateKickstart();

            page_KickStart.clickLink_Scripts();
            page_KickStart.clickLink_AddNewKickstartScript();
            page_KickStart.setTxt_ScriptingLanguage("/bin/bash");
            page_KickStart.setTxt_ScriptingContents("/usr/bin/perl -p -e 's/gpgcheck = 1/gpgcheck = 0/' -i /etc/yum/pluginconf.d/rhnplugin.conf; /usr/bin/yum -y install osad");
            page_KickStart.select_ScriptExecutionTime("Post Script");
            page_KickStart.clickButton_UpdateKickstart();
        }
	}

	/**
	 * Adds a Post-Script running in chroot to Kickstart
	 * @param interperter interpreter to use, example "/bin/bash", if blank defaults to "/bin/bash"
	 * @param customScript desired script, example "(cd /tmp; wget something; text.sh;) >> /tmp/foo.log"
	 */
	public void addCustomScriptToKickstartProfile(String interpreter, String customScript) {
		if ("".compareTo(interpreter) == 0) {
			interpreter = "/bin/bash";
		}
		page_KickStart.clickLink_Scripts();
        page_KickStart.clickLink_AddNewKickstartScript();
        page_KickStart.setTxt_ScriptingLanguage(interpreter);
        page_KickStart.setTxt_ScriptingContents(customScript);
        page_KickStart.select_ScriptExecutionTime("Post Script");
        page_KickStart.clickButton_UpdateKickstart();
        Assert.assertTrue(rh.isTextPresent("Kickstart Script successfully updated."));
	}
	
	/**
	 * 
	 * @param childChannel label of child channel
	 * @param check true = check, false = uncheck
	 */
	public void setChildChannelInKickstartProfile(boolean check) {
		page_KickStart.check_ChildChannel(check);
		page_KickStart.clickButton_UpdateKickstart();
		Assert.assertTrue(rh.isTextPresent("Kickstart Operating System selection successfully updated."));
	}
	
	private String kickstart_Status_fromSDC_part1(String system,
												String autoKS,
												String randLabel){
		page_SatelliteSystems.open();
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_SystemProvisioning.clickLink_SystemName(system);
		page_SDC.clickLink_Provisioning();
		page_SDC.clickLink_SessionStatus();
		String myStatus = page_KickStart.getText_SDC_Kickstart_Status();
		log.info("DEBUG:STATUS_FROM_SDC myStatus = "+myStatus);
		return myStatus;

	}

	private boolean kickstart_Status_fromSDC_part2(String randLabel,String myStatus,String lastPackage,String totalNumberPackages){
			if(!myStatus.equals("") && rh.isTextPresent(randLabel)){
			//rh.stopSelenium();
			myStatus.trim();
			if(myStatus.equalsIgnoreCase("Kickstart complete.")){
			//if(rh.isTextPresent("Kickstart complete.")){
				log.info("Kickstart status: "+myStatus);
				log.info("Last File Requested: "+lastPackage);
				log.info("Total Number of Packages: "+totalNumberPackages);
				log.info("Kickstart has completed, waiting for machine to open SSH ports.");
				return true;
			}
			else{
			log.info("Kickstart status: "+myStatus);
			log.info("Last File Requested: "+lastPackage);
			log.info("Total Number of Packages: "+totalNumberPackages);
			rh.sleepForSeconds(60);
			return false;
			}
		}
		//rh.stopSelenium();
		log.info("System not yet kickstarted.");
		return false;

	}

    /**
     * Takes a system name, autoKS label, and randomly-generated KS label
     * and returns status via a boolean true if KS complete or false if
     * not.  Logs status as well.
     * @param system system name on RHN
     * @param autoKS autoKS label
     * @param randLabel randomly-generated KS label
     * @return true or false boolean value, representing kickstart completion state
     */
	public boolean kickstart_Status_fromSDC(String system,
			                                String autoKS,
			                                String randLabel){

		String myStatus = kickstart_Status_fromSDC_part1(system, autoKS, randLabel);
		String lastPackage = page_KickStart.getText_SDC_Kickstart_LastPackage();
		String totalNumberPackages = page_KickStart.getText_SDC_Kickstart_Total_Number_Packages();
		return kickstart_Status_fromSDC_part2(randLabel, myStatus, lastPackage, totalNumberPackages);
	}

	/**
     * Takes a system name, autoKS label, and randomly-generated KS label
     * and returns status via a boolean true if KS complete or false if
     * not.  Logs status as well.
     * @param system system name on RHN
     * @param autoKS autoKS label
     * @param randLabel randomly-generated KS label
     * @return true or false boolean value, representing kickstart completion state
     */
	public boolean kickstart_Status_Virt_Guest_fromSDC(String system,
			                                String autoKS,
			                                String randLabel){

		String myStatus = kickstart_Status_fromSDC_part1(system, autoKS, randLabel);
		String lastPackage = page_KickStart.getText_SDC_Kickstart_LastPackage();
		String totalNumberPackages = page_KickStart.getText_SDC_Kickstart_Total_Number_Packages();
		task_RhnBase.command_xmList(system, true);
		return kickstart_Status_fromSDC_part2(randLabel, myStatus, lastPackage, totalNumberPackages);
	}

	private void goToSystemEvent(String system){
		page_SatelliteSystems.open();
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_SystemProvisioning.clickLink_SystemName(system);
		page_SDC.clickLink_Events();
		page_SDC.clickLink_History();
		rh.clickLink("link=Package Install scheduled by "+HarnessConfiguration.RHN_USER, true);
	}


	public int checkIfSuccessfulPackageInstall(String system){
		int status = PKG_INPROGRESS;
		
		log.info("Checking if RHN Kickstart packages installed correctly.");
	
		goToSystemEvent(system);
		if(rh.isTextPresent("Queued")){
			log.info("Kickstart packages are Queued, rhn_check was not successful");
			status = PKG_FAIL;
		}
		else if(rh.isTextPresent("Completed")){
			log.info("Packages successfully installed, rhn_check was successful");
			status = PKG_COMPLETE;
		}
		else if(rh.isTextNotPresent("Completed")){
			rh.isTextPresent("is: Picked Up");
			//log.info("Kickstart packages are picked up, waiting for package install completion");
			for(int i=0;i<20;i++){
				task_TestPrep.sleepForSeconds(5);
				goToSystemEvent(system);
				if(rh.isTextPresent("Completed")){
					log.info("Packages were installed successfully!");
					status = PKG_COMPLETE;
					break;
				}
				if(rh.isTextPresent("Failed")){
					break;
				}
				
			}	
			
			
			if(rh.isTextPresent("Failed")){
			    log.info("FAILURE: Kickstart Packages NOT Installed");
				log.info(page_SDC.getEventDetails());
				log.info("ERROR: there was a problem installing the RHN Kickstart Packages, check system events");
				log.info("PACKAGES MAY BE UNSIGNED!!!!, checking");
				log.info("Retrying package install");
				log.info(page_SDC.getEventDetails());
				page_SDC.clickButton_Reschedule();
				if(HarnessConfiguration.OCTOKICK_SIGNED_PACKAGES == 0){
					ExecCommands exec = new ExecCommands();
					try{
						exec.submitCommandToLocalWithReturn(true, "ssh", "root@"+system+" rpm -e rhn-kickstart rhn-kickstart-common auto-kickstart*");
						exec.submitCommandToLocalWithReturn(true, "ssh", "root@"+system+" perl -p -e 's/useGPG=1/useGPG=0/' -i /etc/sysconfig/rhn/up2date");
						exec.submitCommandToLocalWithReturn(true, "ssh", "root@"+system+" perl -p -e 's/gpgcheck = 1/gpgcheck = 0/' -i /etc/yum/pluginconf.d/rhnplugin.conf");
						task_TestPrep.command_runRhnCheckInForeground(system, true);
						exec.submitCommandToLocalWithReturn(true, "touch", " /tmp/automation/"+system+"_FAILED_FIRST_INSTALL_PACKAGES_DETECTED");
					}
					catch(IOException ioe){
						log.info("command failed");
					}
				}
			}
		}
		else if(rh.isTextPresent("Completed")){
			status = PKG_COMPLETE;
		}

		return status;
	}

	public String StartVirtGuest_GetName(String system, String KickStartLabel,boolean openAndLogin){
		String name = task_KickStart.getVirtGuest_HostName(system, KickStartLabel, openAndLogin);
		return name;
	}

	public void StopVirtGuest(String system, String KickStartLabel,boolean openAndLogin){
		task_KickStart.virtGuest_Action(system, KickStartLabel, openAndLogin, true, IRhnBase.VIRT_GUEST_STOP);
	}



	/**
	 * Takes in a string representing kickstarted machine's ip/hostname
	 * and returns a boolean True if the machine has completed Kickstart
	 * and a boolean False if not.
	 *
	 * @param system String representing kickstarted system
	 * @param autoKS String representing kickstart tree
	 * @return boolean representing completion of kickstart process
	 */
	public boolean kickstart_Status(String system,
			                        String autoKS,
			                        String randLabel){
		//FIXME
		//rh.stopSelenium();
		//rc.OpenAndLogIn();
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_Kickstart();
		String myStatus = page_KickStart.getText_SystemKickstart_Status(system, autoKS, randLabel, page_SatelliteSystems);
		//log.info("DEBUG:STATUS_FROM_KS_PAGE myStatus = "+myStatus);
		////rh.stopSelenium();
		if(!myStatus.equalsIgnoreCase("")){
			if(myStatus.contains("complete")){
				log.info("Kickstart has completed, waiting for machine to open SSH ports.");
				return true;
			}
			log.info("Kickstart status: "+myStatus);
			return false;
		}
		log.info("System not yet kickstarted.");
		return false;
	}


	public boolean KickStartSystem(String system,
			String kickstartLabel, boolean executeKickStart){
		
		
		page_SatelliteSystems.open();
		task_Search.goToSystem(system);
		if(rh.isTextNotPresent(system)){
			task_TestPrep.registerSystem(system,IRhnBase.RHN_SAT_REG_CMD, true, true);
		}
		ssh.executeViaSSH(system,"up2date -p");
		ssh.executeViaSSH(system,"rhn-profile-sync");
		page_RhnCommon.clickLink_SystemName(system);
		page_SDC.clickLink_Provisioning();
		page_RhnCommon.setTxt_FilterBy(kickstartLabel);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInListSDC();
		page_KickStart.clickButton_ScheduleKickstartAndFinish();
		Assert.assertTrue(rh.isTextPresent("Initiate Kickstart"));
		//   boolean doneFlag = false;
		task_TestPrep.command_remove_localKnownHosts(false);
		task_RhnBase.command_importRPMKeys(system, true);
		//rb.command_rpm(" -qa rhn-kickstart rhn-kickstart-common auto-kickstart*", system, true);
		task_TestPrep.command_rpm(" -e --nodeps rhn-kickstart", system, true);
		task_TestPrep.command_rpm(" -e --nodeps rhn-kickstart-common", system, true);
		task_TestPrep.command_rpm(" -e --nodeps auto-kickstart*", system, true);
		
		
		if(executeKickStart){
			task_TestPrep.command_runRhnCheckInForeground(system, true);
			rh.sleepForSeconds(120);
			task_RhnBase.command_cancelShutdown(system, false);
			// DON NOT REBOOT HERE, need to check package status task_TestPrep.command_rebootSystem(system, false);
		}
		return true;
	}

	public void rebootSystem(String system){
		task_TestPrep.command_remove_localKnownHosts(false);
		log.info("Reboot system "+ system);
		task_RhnBase.command_cancelShutdown(system, false);
		task_TestPrep.command_rebootSystem(system, false);
	}
	
	public boolean KickStartVirtSystem(String system,
												String kickstartLabel){
			task_TestPrep.enableProvisioning(system, true);
			task_TestPrep.enableVirtualizationPlatform(system, true);
			
			page_SatelliteSystems.open();
			task_Search.goToSystem(system);
			if(rh.isTextNotPresent(system)){
				return false;
			}
			page_RhnCommon.clickLink_SystemName(system);
			
			page_SDC.clickLink_Virtualization();
			page_SDC.clickLink_Virtualization_Provisioning();
			page_RhnCommon.setTxt_FilterBy(kickstartLabel);
			page_RhnCommon.clickButton_Filter_Go();
			page_RhnCommon.check_FirstItemInList();

			page_SDC.setTxt_Virtualization_GuestName(kickstartLabel+"_"+system);
			page_SDC.setTxt_Virtualization_MemoryAllocation("512");
			page_SDC.setTxt_Virtualization_Storage("4096");
			page_SDC.setTxt_Virtualization_VirtualCPUS("1");
			page_KickStart.clickButton_ScheduleKickstartAndFinish();

			Assert.assertTrue(rh.isTextPresent("Initiate Kickstart"));
			boolean doneFlag = false;
			task_TestPrep.command_remove_localKnownHosts(false);
			task_TestPrep.command_importRPMKeys(system, false);

			task_TestPrep.command_runRhnCheckWithAT(system, true);

			//rh.screenShot("kickstart", "startOfKickstart");
			return true;
		}


	/**
	 * Checks if SSH ports on supplied system hostname are open.  Returns
	 * boolean true if they are and false if a timeout occurs
	 * @param system system hostname
	 * @param checkKickStart TODO
	 * @return boolean value representing success or failure of ssh port check
	 */
	public boolean checkIfSSHOpen(String system, boolean checkKickStart){
		
		try{
			InetAddress myInetAddress =  InetAddress.getByName(system);
			log.info("ping system " +system+" for 120 seconds to test connectivity");
			if(myInetAddress.isReachable(120000)){//120 second timeout
				log.info("success: pinged "+system);
				rh.sleepForSeconds(5); //give ssh a second to come up.
				log.info("SSH to "+system);
				Socket mySocket = new Socket(system,22);
				//Thread.sleep(60000);  //Sometimes SSH will be open but the box will refuse connections.  WTF?!
				rh.sleepForSeconds(6);
				task_TestPrep.command_remove_localKnownHosts(false);
				if(checkKickStart){
				log.info("#############PROOF OF KICKSTART##################");
				log.info("ssh root@"+system+ " "+ "hostname");
				task_TestPrep.command_generic("hostname", "", system, true);
				log.info("REDHAT RELEASE= "+task_TestPrep.getRedHat_Release(system));
				log.info("#############PROOF OF KICKSTART##################");
				}
				return true;
				}
			else{
				log.info(system+ " is not responding to pings, moving on");
				return false;
			}
			
		}
		catch(Exception e){
			return false;
		}
	}

	private boolean checkBaseChannelandKSTree(ODistribution currentDistro){
		//rh.stopSelenium();
		//rc.OpenAndLogIn();
		log.info("DEBUG: in checkBaseChannel KS Tree");
        page_SatelliteSystems.open();
        page_KickStart.open();
        page_KickStart.clickLink_ViewListKickstartProfiles();
        page_KickStart.openLink_CreateNewKickstartProfile();
        try{
        	
        	log.info("DEBUG: In checkBaseChannel, BASE_CHANNEL ="+currentDistro.getRHNName());
        	log.info("selected base channel = " +page_KickStart.getSelectedBaseChannel());
        	log.info("Current distro =" + currentDistro.getRHNName());
        	if(page_KickStart.getSelectedBaseChannel().equalsIgnoreCase(currentDistro.getRHNName()))
        		page_KickStart.select_BaseChannel(currentDistro.getRHNName(),false);
        	else
        		page_KickStart.select_BaseChannel(currentDistro.getRHNName(),true);
            log.info("DEBUG:  FOUND BaseChannel"+currentDistro.getRHNName());
 
        }
        catch(SeleniumException se){
        	try{
        		log.info("DEBUG 2: In checkBaseChannel, BASE_CHANNEL ="+currentDistro.getRHNName());
        		page_KickStart.select_BaseChannel(currentDistro.getRHNName(),true);
        	}
        	        catch (Exception e){
		        	log.info("DEBUG 3: Base Channel NOT found");
		        	page_KickStart.select_BaseChannel(currentDistro.getRHNName(),true);
		        	//rh.stopSelenium();
		        	return false;
		        }
        }

        try{
        	log.info("DEBUG: In checkBaseChannel TREE ="+currentDistro.getKSTree());
            page_KickStart.select_KickstartableTree(currentDistro.getKSTree());
            log.info("DEBUG: FOUND Tree "+currentDistro.getKSTree());
        }
        catch (Exception se){
        	
        	page_KickStart.select_KickstartableTree(currentDistro.getKSTree());
        	log.info("DEBUG: Tree NOT found "+currentDistro.getKSTree());
        	//rh.stopSelenium();
        	return false;
        	}

        //rh.stopSelenium();
        return true;
	}
	/**
	 * Checks if a kickstart distribution assigned to an OTestElement
	 * has been synced; if so, sets it as 'checked'; otherwise, spawns off
	 * a thread to sync it to the satellite.  If thread is present, checks to
	 * see if it's still alive and, if not alive anymore, checks if distribution synced.
	 * If distribution is synced, sets it as 'checked'.
	 *
	 * @param myElement OTestElement to check
	 * @param satHostname String representation of Satellite hostname
	 */
    public void checkSynced(OTestElement myElement,
    		                String satHostname){
    	ODistribution currentDistro = myElement.getDistro();
    	if(currentDistro.getSyncThread() != null)
    		if(currentDistro.getSyncThread().isAlive())
    			return;
    		else
    			if(this.checkBaseChannelandKSTree(currentDistro)){
    				currentDistro.setSyncChecked();
    				myElement.setStatus(0);
    				return;
    			}
    	if(!this.checkBaseChannelandKSTree(currentDistro)){ //if base channel/ks tree not found
        	currentDistro.setSyncThread(new OSatSync(satHostname, //spawn off a new sync thread
        											 currentDistro.getSatChannel()));
        	currentDistro.getSyncThread().start(); //and start it
        	log.info("DEBUG: SyncThread ID ="+currentDistro.getSyncThread().getId());
        	myElement.setStatus(-1);
    	}
    	else
    		currentDistro.setSyncChecked();
    }
    /**
     * Checks system against supplied version string; if a match, returns true;
     * else, returns false.
     *
     * Currently a placeholder function until I can figure out a better way to
     * do it than in the old Octokick, which was truly a kludge tower
     * @param system system hostname as a string
     * @param version system version as a string
     * @return boolean value representing the success or failure of the version match
     */
	public boolean checkVersion(String system,
							    String version){
		return true;
	}
}
