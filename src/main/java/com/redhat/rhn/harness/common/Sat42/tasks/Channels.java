package com.redhat.rhn.harness.common.Sat42.tasks;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;

import com.redhat.qe.tools.ExecCommands;
import com.redhat.rhn.harness.Sat42.pages.ChannelsPage;
import com.redhat.rhn.harness.Sat42.pages.RhnCommon;

import com.redhat.rhn.harness.baseInterface.IChannels;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;

/**
 * Tasks used to create, clone, delete and push packages into custom channels
 * @author whayutin
 *
 */
public class Channels extends SeleniumSetup { // implements IChannels{

	
	protected RhnHelper rh = new RhnHelper();
	

	
	
	
	
	protected static String channelError01 = "Invalid channel label 'autoChannel01' - must be at least 6 characters long, begin with a letter, and contain only lowercase letters, digits, '-', '_', and '.'";
	protected static String autochannel01 = "autochannel01";
	

	public void verifyChannelExists(String channel, boolean exists){
		page_RhnCommon.clickChannels();
		Assert.assertTrue(rh.isTextPresent("Software Channels Overview"));
		page_Channels.click_AllChannels();
		page_RhnCommon.setTxt_FilterBy(channel);
		page_RhnCommon.clickButton_Filter_Go();
		if(exists){
		Assert.assertTrue(rh.isTextPresent(channel));
		rh.clickLink("link="+channel, true);
		Assert.assertTrue(rh.isTextPresent(channel));

		page_RhnCommon.clickChannels();
		page_Channels.click_RelevantTab();
		page_RhnCommon.setTxt_FilterBy(channel);
		page_RhnCommon.clickButton_Filter_Go();
		rh.clickLink("link="+channel, true);
		Assert.assertTrue(rh.isTextPresent(channel));
		}
		if(!exists){
			Assert.assertTrue(rh.isTextNotPresent(channel)); //test
		}

	}

	public void createChannelClone(String origChannel, String clonedChannelName,String clonedChannelLabel, int errata ){
		page_Channels.open();
		page_Channels.click_ManageSoftwareChannels();
		page_Channels.click_CloneChannel();
		Assert.assertTrue(rh.isTextPresent("Clone Channel"));
		//the select box can not be selected from selenium
		page_Channels.select_CloneFrom(origChannel);

		//errata 0 = Current (all errata)
		//errata 1 = Original (no errata)
		//errata 2 = Select
		if(errata == 0)
			page_Channels.CheckRadio_ChannelAllErrata(true);
		if(errata == 1)
			page_Channels.CheckRadio_ChannelNoErrata(true);
		if(errata == 2)
			page_Channels.CheckRadio_SelectErrata(true);

		page_Channels.clickButton_CreateChannel();

		if(errata == 0)
			Assert.assertTrue(rh.isTextPresent("Current state of the channel"));
		if(errata == 1)
			Assert.assertTrue(rh.isTextPresent("Original channel with no updates"));
		if(errata == 2)
			Assert.assertTrue(rh.isTextPresent("Select errata"));

		page_Channels.setTxt_ChannelName(clonedChannelName);
		page_Channels.setTxt_ChannelLabel(clonedChannelLabel);
		page_Channels.clickButton_CreateChannel_longWait();
		Assert.assertTrue(rh.isTextPresent("has been cloned as"));
	}
	
	public void deleteAllCustomChannels(){
		//stub method in 5.1

		}
	

	public void deleteCustomChannel(String clonedChannelName){
		page_RhnCommon.clickChannels();
		page_Channels.click_ManageSoftwareChannels();
		if(rh.isTextPresent(clonedChannelName)){
			rh.clickLink("link="+clonedChannelName, true);
			page_Channels.click_DeleteSoftwareChannel();
			page_Channels.clickButton_DeleteChannel();
			if(rh.isTextPresent("You cannot delete")){
				rh.clickLink("link=here", "here", true);
				page_RhnCommon.clickButton_SSM_Clear();
				page_RhnCommon.clickButton_SelectAll();
				page_RhnCommon.clickButton_SSM_Manage();
				page_SSM.clickLink_ChannelMemberships();
				
				
				page_SSM.clickLink_BaseChannelAlteration();
				page_SSM.select_DesiredBaseChannel("Default RH Base Channel");
				page_SSM.clickButton_ChangeBaseChannels();
				
		

				page_RhnCommon.clickChannels();
				page_Channels.click_ManageSoftwareChannels();
				rh.clickLink("link="+clonedChannelName, true);
				page_Channels.click_DeleteSoftwareChannel();
				page_Channels.clickButton_DeleteChannel();
				}
		}
	}

	protected void goToManageSoftwareChannels(){
		page_RhnCommon.clickChannels();
		page_Channels.click_ManageSoftwareChannels();
	}

	public void rhnPushPackageToChannel(String channel, String packageName, String packagePath, String server, String user, String passwd){
		rhnPushPackageToChannel(channel, packageName, packagePath, server, user, passwd, "/tmp");
	}
	public void rhnPushPackageToChannel(String channel, String packageName, String packagePath, String server, String user, String passwd, String tmpDir){
		rhnPushPackageToChannel(channel, packageName, packagePath, server, user, passwd, tmpDir, false);
	}
	public void rhnPushPackageToChannel(String channel, String packageName, String packagePath, String server, String user, String passwd, String tmpDir, boolean force){
		ExecCommands exec = new ExecCommands();
		try{
			String mkdirCommand = "mkdir -p " + tmpDir;
			exec.submitCommandToLocalWithReturn(true, "ssh ", "root@"+IRhnBase.SERVER_HOSTNAME +" " + mkdirCommand);
		} catch(IOException ioe){
			log.info("command failed");
		}
		try{
			exec.submitCommandToLocalWithReturn(true, "scp "+packagePath+packageName, " root@"+IRhnBase.SERVER_HOSTNAME +":" + tmpDir);
		} catch(IOException ioe){
			log.info("command failed");
		}
		String rhnpushCommand = "rhnpush -c "+channel+ " --nosig "+" -u "+user+ " -p "+passwd+ " --server "+server+ " " + tmpDir + "/" +packageName;
		if (force) {
			rhnpushCommand+= " --force;";
		}
		try{
			exec.submitCommandToLocalWithReturn(true, "ssh ", "root@"+IRhnBase.SERVER_HOSTNAME +" "+rhnpushCommand);
		} catch(IOException ioe){
			log.info("command failed");
		}
	}
	public ArrayList<String> rhnPushPackageToChannelViaRsh(String hostname, String patchloc, String patchname, String channel){
		ExecCommands exec = new ExecCommands();
		try{
			exec.submitCommandToLocalWithReturn(true, "rcp "+patchloc+patchname, " root@"+hostname +":/tmp/");
		} catch(IOException ioe){
			log.info("command failed");
			fail();
		}
		
		//We use solaris2mpm to coax patches and patch clusters into a form (mpm) that Satellite can understand 
		String mpmCreateCommand = "solaris2mpm /tmp/" + patchname;
		
		String mpmResults = "";
		mpmResults = exec.rcpAndExecuteViaRsh(hostname, mpmCreateCommand);
		
		//Next, we figure out what mpm files were produced via solaris2mpm
		assertTrue(mpmResults.contains("Writing")); //mpm files were written
		String [] mpmOut = mpmResults.split("\n");
		ArrayList <String> mpmFilenames = new ArrayList<String>();
		
		for(int i=0; i<mpmOut.length; i++)
			if (mpmOut[i].contains("Writing")){
				System.out.println(mpmOut[i]);
			    mpmFilenames.add(mpmOut[i].split(" ")[1]); //add new mpm filename to list
			}
		//then push each mpm file
		for(int i=0; i<mpmFilenames.size(); i++){
			String mpmPackage = mpmFilenames.get(i);
			String rhnpushCommand = "rhnpush --force -vvvvv -c "+channel+ " --nosig "+" -u "+IRhnBase.USER+ " -p "+
			IRhnBase.PASSWORD + " --server "+ IRhnBase.SERVER_HOSTNAME + " " + mpmPackage;
			exec.rcpAndExecuteViaRsh(hostname, rhnpushCommand);
		}
		
		return mpmFilenames;
	}

	public void createCustomChannel(String channelName){
		page_RhnCommon.clickChannels();
		page_Channels.click_ManageSoftwareChannels();
		page_Channels.click_createNewChannel();
		page_Channels.setTxt_ChannelName(channelName);
		page_Channels.setTxt_ChannelLabel(channelName);
		page_Channels.setTxt_ChannelSummary(channelName);
		page_Channels.clickButton_CreateChannel();
		Assert.assertTrue(rh.isTextPresent("Channel "+channelName+" created."));
	}
	
	public void createCustomChannelWithArchitecture(String channelName,
													String architecture){
		int bob = 3;
		page_RhnCommon.clickChannels();
		page_Channels.click_ManageSoftwareChannels();
		page_Channels.click_createNewChannel();
		page_Channels.setTxt_ChannelName(channelName);
		page_Channels.setTxt_ChannelLabel(channelName);
		page_Channels.select_Architecture(architecture);
		page_Channels.setTxt_ChannelSummary(channelName);
		page_Channels.clickButton_CreateChannel();
		Assert.assertTrue(rh.isTextPresent("Channel "+channelName+" created."));
	}
	
	public void createCustomChannelWithParent(String channelName,
											  String parentChannel,
											  String architecture){
		page_RhnCommon.clickChannels();
		page_Channels.click_ManageSoftwareChannels();
		page_Channels.click_createNewChannel();
		page_Channels.select_ParentChannel(parentChannel);
		page_Channels.setTxt_ChannelName(channelName);
		page_Channels.setTxt_ChannelLabel(channelName);
		page_Channels.select_Architecture(architecture);
		page_Channels.setTxt_ChannelSummary(channelName);
		page_Channels.clickButton_CreateChannel();
		Assert.assertTrue(rh.isTextPresent("Channel "+channelName+" created."));
	}

	public void verifyPackageInChannel(String Channel, String Package){
		page_RhnCommon.clickChannels();
		page_Channels.click_ManageSoftwareChannels();
		rh.clickLink("link="+Channel, true);
		page_Channels.click_channelPackages();
		page_Channels.click_listRemovePackages();
		page_RhnCommon.setTxt_FilterBy(Package);
		page_RhnCommon.clickButton_Filter_Go();
		rh.clickLink("link="+Package+"*", true);

	}
	
	public void verifyPatchInChannel(String Channel, String Patch){
		//Strip off '.mpm', as this text is not included in patch descriptors
		String patchString = Patch.replaceAll(".mpm", "");
		page_RhnCommon.clickChannels();
		page_Channels.click_ManageSoftwareChannels();
		rh.clickLink("link="+Channel, true);
		page_Channels.click_channelPatches();
		page_Channels.click_listRemovePatches();
		page_RhnCommon.setTxt_FilterBy(patchString);
		page_RhnCommon.clickButton_Filter_Go();
		rh.clickLink("link="+patchString+"*", true);
	}
	
	public void addSubscriberToChannel(String Channel, String user){
		//created in sat530, should work in previous versions
		page_Channels.open();
		page_Channels.click_ManageSoftwareChannels();
		rh.clickLink(Channel, true);
		page_Channels.click_Subscribers();
		//page_RhnCommon.clickButton_SelectAll();
		page_RhnCommon.check_FirstItemInList();
		page_RhnCommon.clickButton_Update();
		assertTrue(rh.isTextPresent("Channel subscription permissions updated."));
	}
	
	public void addManagerToChannel(String Channel, String user){
		//created in sat530, should work in previous versions
		page_Channels.open();
		page_Channels.click_ManageSoftwareChannels();
		rh.clickLink(Channel, true);
		page_Channels.click_channelManagers();
		page_RhnCommon.clickButton_SelectAll();
		page_RhnCommon.clickButton_Update();
		assertTrue(rh.isTextPresent("Channel subscription permsissions updated."));
	}
	
	/**
	 * Returns the arch of the channel in Satellite (like "IA-32") or null in exceptions. 
	 * @param Channel name of the channel
	 * @return The arch of the channel. E.g. "IA-32"
	 */
	public String getChannelArchByName(String Channel){
		String ret = null;
		page_RhnCommon.clickChannels();
		rh.clickLink("link="+Channel, true);
		if(sel.getText("xpath=//table[@class='details']/tbody/tr[4]/th").equals("Architecture:")){
			ret = sel.getText("xpath=//table[@class='details']/tbody/tr[4]/td");
		}else{
			if(sel.getText("xpath=//table[@class='details']/tbody/tr[8]/th").equals("Architecture:")){
				ret = sel.getText("xpath=//table[@class='details']/tbody/tr[8]/td");
			}
		}
		return ret;
	}
		
	public void createCustomChannel(String channelName, int orgChannelSharingSetting, int orgChannelUserRestriction, String parentChannel){
		//stub only
		throw new SeleniumException("check your satellite version, this method is not appicable to this version");
	}
	
	public void modifyChannelAccessControl(String channelName, int orgChannelSharingSetting,int orgChannelUserRestriction, boolean grantAccess ){
		throw new SeleniumException("check your satellite version, this method is not appicable to this version");
	}
	
	public void verifyFileSystemPathOfPackageInChannel(String Channel, String Package) {
		throw new SeleniumException("check your satellite version, this method is not appicable to this version");
	}
	
	public void verifyMd5sumOfPackageInChannel(String Channel, String Package) {
		throw new SeleniumException("check your satellite version, this method is not appicable to this version");
	}
	
	public void removePackageFromFileSystem(String Channel, String Package) {
		throw new SeleniumException("check your satellite version, this method is not appicable to this version");
	}
	
	public void verifyProviderOfPackageInChannel(String Channel, String Package, String Provider) {
		throw new SeleniumException("check your satellite version, this method is not appicable to this version");
	}
	
	public void verifyNEVRAisEnabled() {
		throw new SeleniumException("check your satellite version, this method is not appicable to this version");
	}
	public void gotoManageCustomChannel(String Channel){
		throw new SeleniumException("check your satellite version, this method is not appicable to this version");
	}
}
