package com.redhat.rhn.harness.common.Sat51.tasks;



/**
 * These are some basic Solaris functionality tests.  They're designed to be run
 * upon the following environment:
 * - RHN Satellite >= 420
 * - SPARC or x86 machines or a mixture running Solaris 8,9,10
 * - passwordless rshd must be enabled for the root user on each Solaris box
 * @author ssalevan
 */
public class Solaris extends com.redhat.rhn.harness.common.Sat50.tasks.Solaris {
	
	
/*	*//**
	 * Sends a file to a remote machine via RCP
	 * @param hostname hostname of remote machine
	 * @param fileloc location of local file
	 * @param destination destination on remote machine
	 * @return stdout/stderr from command
	 *//*
	protected String rcpFile(String hostname,
			                 String fileloc,
			                 String destination){
		log.info("Copying over the following file via RCP: "+fileloc);
		log.info("To the following destination: "+destination);
		ExecCommands exec = new ExecCommands();
		String output = "";
		try{
			output = exec.submitCommandToLocalWithReturn(true, "rcp "+fileloc, "root@"+hostname+":"+destination);
		}
		catch (IOException ioe) {
			log.info("RCP command failed!");
		}
		return output;
	}
	
	*//**
	 * Enables Solaris support on an RHN Satellite and
	 * verifies that the enabling occurred
	 *//*
	public void enableSolarisSupport(){
		rc.clickSatelliteTools();
		sattpage.clickLink_SatelliteConfiguration();
		sattpage.check_EnableSolarisSupport(true);
		rb.restartSatellite();
		rc.clickSatelliteTools();
		sattpage.clickLink_SatelliteConfiguration();
		assertTrue(sel.isChecked("name=web|enable_solaris_support"));
	}
	
	*//**
	 * Disables Solaris support on an RHN Satellite and
	 * verifies that the disabling occurred
	 *//*
	public void disableSolarisSupport(){
		rc.clickSatelliteTools();
		sattpage.clickLink_SatelliteConfiguration();
		sattpage.check_EnableSolarisSupport(false);
		rb.restartSatellite();
		rc.clickSatelliteTools();
		sattpage.clickLink_SatelliteConfiguration();
		assertFalse(sel.isChecked("name=web|enable_solaris_support"));
	}
	
	*//**
	 * Prepares a Solaris machine to run RHN-related commands through
	 * Brandon's magical Solaris preparation script
	 * NOTE: The machine must have the rshd service enabled and users
	 * must be able to log in without a password to the root account.
	 * To learn how to accomplish this task, refer to Satellite wiki
	 * @param hostname hostname of Solaris machine
	 *//*
	protected void prepareSystem(String hostname,
								 String activationKey,
								 String channel){
		this.rcpFile(hostname,
				     System.getProperty("user.dir", "") + "/src/main/resources/rhn_unix_setup.sh",
				     "/");
		this.rcpFile(hostname,
			     System.getProperty("user.dir", "") + "/src/main/resources/yes",
			     "/usr/bin");
		this.rshCommand(hostname,
				        String.format("sh /rhn_unix_setup.sh %s %s %s %s %s",
				        			  IRhnBase.SERVER_HOSTNAME,
				        			  IRhnBase.USER,
				        			  activationKey,
				        			  IRhnBase.PASSWORD,
				        			  channel));
	}
	
	*//**
	 * Registers a Solaris machine to an RHN Satellite
	 * @param hostname hostname of Solaris machine
	 *//*
	public void registerSolarisSystem(String hostname,
								  	  String activationKey,
								  	  String channel){
		this.prepareSystem(hostname,
				           activationKey,
				           channel);
		assertTrue(rb.isSystemActivated(hostname, activationKey));
	}
	
	*//**
	 * Creates an activation key for a Solaris box, given the name
	 * of a base channel
	 * @param channel name of base channel
	 * @return string containing activation key number
	 *//*
	public String createSolarisActivationKey(String channel){
		String activationKey = "" + Math.abs(rand.nextInt());
		String name = channel;
		
		//the only applicable add-on entitlement for a Solaris box is
		//Provisioning, hence the hard-coding of the following boolean values
		
		ak.createActivationKeyWithBaseChannel(name, 
				                              activationKey,
				                              channel,
				                              "",
				                              false,
				                              true,
				                              false,
				                              false,
				                              false);
		try{
			rb.setTxt_FilterBy(channel);
			rb.clickButton_Filter_Go();
		} catch (Exception e) {}
		finally{
			activationKey = rh.getTableData("xpath=/html/body/div/div[5]/table/tbody/tr/td[2]/form/table", channel, 3);
		}
		return activationKey;
	}
	
	*//**
	 * Pushes an individual patch or cluster from a Solaris
	 * machine into a channel on an RHN Satellite
	 * @param hostname hostname of Solaris machine
	 * @param patchloc location of patch on Solaris machine
	 * @param channel name of channel to push patch into
	 *//*
	public ArrayList<String> pushSolarisPatch(String hostname,
							     			  String patchloc,
							     			  String patchname,
							     			  String channel){
		ArrayList<String> filenames = rb.rhnPushPackageToChannelViaRsh(hostname,
							   			 							   patchloc,
							   			 							   patchname,
							   			 							   channel);
		for (int i=0; i<filenames.size(); i++)
			rb.verifyPatchInChannel(channel,
							  	  	filenames.get(i));
		return filenames;
	}
	
	*//**
	 * Runs the 'showrev -p' command on a Solaris machine and
	 * checks for the presence of the patch ID number supplied
	 * @param hostname hostname of Solaris machine
	 * @param patchname patch ID number
	 * @return boolean value denoting presence of patch on system
	 *//*
	protected boolean patchIsInstalled(String hostname,
									   String patchname,
									   ArrayList<String> patchFNs){
		//Due to the constraints of the showrev tool, we've got
		//to snip out the section from the filename that looks like this,
		//where x represents a decimal integer 0-9: xxxxxx-xx
		Pattern p = Pattern.compile("\\d{6}-\\d{2}");
		if (patchFNs == null){ //we've got a single patch...
			String patchnum = "";
			Matcher m = p.matcher(patchname);
			if(m.find())
				 patchnum = patchname.substring(m.start(), m.end());
			else{
				log.info("Did not find a valid patch number in patch: "+patchname);
				return false;
			}
			String output = this.rshCommand(hostname, 
											"showrev -p");
			return output.contains(patchnum);
		}
		else{ //odds bodkins!  a patch cluster?
			boolean allInstalled = true;
			for (int i=0; i<patchFNs.size(); i++){
				String patchnum = "";
				String currPatchFN = patchFNs.get(i);
				if (currPatchFN.contains("cluster"))
					continue; //we can't detect cluster metapackages with showrev
				Matcher m = p.matcher(currPatchFN);
				if (m.find())
					patchnum = currPatchFN.substring(m.start(), m.end());
				else{
					log.info("Did not find a valid patch number in patch: "+patchname);
					return false;
				}
				String output = this.rshCommand(hostname, 
												"showrev -p");
				if(!output.contains(patchnum)){
					log.info("Patch #"+patchnum+" is not installed!");
					allInstalled = false;
				}
			}
			return allInstalled;
		}
	}
	
	*//**
	 * Applies a patch to a Solaris machine residing on an
	 * RHN Satellite
	 * Code cheerfully ganked from Wes' SdcSoftware class
	 * @param hostname hostname of Solaris machine
	 * @param patch name of patch
	 *//*
	public void applySolarisPatch(String hostname,
							      String patchname,
							      ArrayList<String> patchFNs){
		//knock off the .mpm suffix
		String patch = patchname.replaceAll(".mpm", "");
		//one of the neat things about Satellite is that it reports
		//the names of patches and clusters inconsistently throughout the
		//UI.  henceforth, witness the following ugly, nasty hack:
		patch = patch.split("[.]")[0];
		if (patch.contains("cluster")){
			String [] namecomponents = patch.split("-");
			patch = "";
			for (int i=0; i<namecomponents.length - 2; i++)
				patch += namecomponents[i] + "-";
			//finally, strip off leading dash
			patch = patch.substring(0, patch.length()-1);
		}
		syspage.open();
		qs.goToSystem(hostname);
		rc.clickLink_GeneralLink(hostname);
		sdc.clickLink_Software();
		if(patch.contains("cluster")){
			sdc.clickLink_PatchClusters();
			rh.clickLink("link="+patch, true);
			sdc.clickButton_InstallPatchCluster();
		}
		else{
			sdc.clickLink_Packages();
			sdc.clickLink_Install();
			sdc.setTxt_SDCFilterBy(patch);
			sdc.clickButton_FilterGo();
			rc.check_FirstItemInList();
			sdc.clickButton_InstallSelectedPackages();
			sdc.clickButton_Confirm();
		}
		//apply patch
		ExecCommands exec = new ExecCommands();
		exec.rcpAndExecuteViaRsh(hostname, "rhn_check -vvvvv");
		assertTrue(this.patchIsInstalled(hostname, patch, patchFNs));
	}*/
}
