package com.redhat.rhn.harness.common.Sat42.tasks;

import java.io.IOException;
import java.util.logging.Logger;

import org.testng.Assert;

import com.redhat.qe.tools.ExecCommands;
import com.redhat.rhn.harness.Sat42.pages.*;
import com.redhat.rhn.harness.baseInterface.IConfigManagement;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;

/**
 * Tasks used with RHN Configuration Management, central and local config mang.
 * @author whayutin
 *
 */
public class ConfigManagement extends SeleniumSetup { // implements IConfigManagement{

	public static String AutoChannelName = "01RhnAuto";
	public static String AutoChannelLabel = "01RhnAutoLabel";
	public static String AutoChannelDesc = "01RhnAutoLabel";

	public static String AutoImportChannelName = "ImportRhnAuto";
	public static String AutoImportChannelLabel = "ImportRhnAutoLabel";
	public static String AutoImportChannelDesc = "ImportRhnAutoLabel";
	public static String file01="/tmp/testMultDeploy01";
    public static String file02="/tmp/uploadfile01-import";
    public static String file03="/tmp/testMultDeploy02";
    public static String fileSingle="/tmp/testSingleFile";
    public static String fileSDCSingle="/tmp/testSDCSingleFile";
	public static Logger log = Logger.getLogger(ConfigManagement.class.getName());


	protected RhnHelper rh = new RhnHelper();
	
	
	

	
	
	
	
	




	public void DeleteConfigChannel(String channel, boolean openAndLogin){
		if(goToChannel(channel, openAndLogin))
    		deleteMyChannel(channel);
	}


	public void CreateNewConfigChannel(String channel, boolean openAndLogin, boolean deleteOld, boolean successful){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}

		if(deleteOld){
			DeleteConfigChannel(channel, openAndLogin);
		}
        page_Configuration.open();
        page_Configuration.clickLink_ConfigurationChannels();
        page_Configuration.clickCreateNewConfigChannel();
        page_Configuration.setNewConfigChannelName(channel);
        page_Configuration.setNewConfigChannelLabel(channel);
        page_Configuration.setNewConfigChannelDescription(channel);
        page_Configuration.clickButton_CreateConfigChannel();
        if(successful)
        	Assert.assertTrue(rh.isTextPresent("delete channel"));//assures channel has been created
	}



	public void ConfigChannelErrorMsg01(){
		task_RhnBase.OpenAndLogIn();
        page_Configuration.open();
        page_Configuration.clickLink_ConfigurationChannels();
        page_Configuration.clickCreateNewConfigChannel();
        //cp.setNewConfigChannelName("duplicate");
        page_Configuration.setNewConfigChannelLabel("error01");
        page_Configuration.setNewConfigChannelDescription("error01");
        page_Configuration.clickButton_CreateConfigChannel();
        Assert.assertTrue(rh.isTextPresent("Configuration channel name is required."));

        //Configuration channel label is required.
        page_Configuration.clickLink_ConfigurationChannels();
        page_Configuration.clickCreateNewConfigChannel();
        page_Configuration.setNewConfigChannelName("error01");
        //cp.setNewConfigChannelLabel("error01");
        page_Configuration.setNewConfigChannelDescription("error01");
        page_Configuration.clickButton_CreateConfigChannel();
        Assert.assertTrue(rh.isTextPresent("Configuration channel label is required."));

        //Configuration channel description is required.
        page_Configuration.clickLink_ConfigurationChannels();
        page_Configuration.clickCreateNewConfigChannel();
        page_Configuration.setNewConfigChannelName("error01");
        page_Configuration.setNewConfigChannelLabel("error01");
        //cp.setNewConfigChannelDescription("error01");
        page_Configuration.clickButton_CreateConfigChannel();
        Assert.assertTrue(rh.isTextPresent("Configuration channel description is required."));
        System.out.println("break");
	}

	private void deleteMyChannel(String channel){
		if(rh.isTextPresent(channel)){
			page_Configuration.clickLink_DeleteChannel();
			page_Configuration.clickButton_DeleteConfigChannel();
			page_Configuration.clickLink_ConfigurationChannels();
		}
	}

	public void removeConfigFile(String channel, String file, boolean openAndLogin, boolean successful){
		goToChannel(channel, openAndLogin);
		  if(page_Configuration.is_ListRemoveLinkPresent())
	        	page_Configuration.clickLink_ListRemoveFiles();
	        if(rh.isTextPresent(file)){
	        	page_Configuration.check_ConfigFileList();
	        	page_Configuration.clickButton_RemoveSelectedFiles();
	        	if(successful)
	        		Assert.assertTrue(rh.isTextPresent("1 configuration files were successfully deleted."));
	        }

	}

	public void uploadFilesToChannel(String channel, String file, boolean binary, String path, boolean succesful, int revisionNumber){
		goToChannel(channel, false);
        page_Configuration.clickLink_AddFiles();
        if(binary)
        	page_Configuration.checkRadio_BinaryFile();

        page_Configuration.setTxt_FileToUpload(file);
        page_Configuration.setTxt_FileNamePath(path);
        page_Configuration.clickButton_UploadConfigurationFile();
        if(succesful)
        	Assert.assertTrue(rh.isTextPresent("Revision "+revisionNumber+" of "+ path +" from channel"));

        //upload a file for the next import test

        //Assert.assertTrue(rh.isTextPresent("Revision 1 of "+ path +"-import from channel"));

        //Assert.assertTrue(rh.isTextPresent("A file named \"/tmp/uploadfile01\" already exists in this channel."));

	}

	public void uploadBinaryFilesToChannel(String channel,String file,String path, boolean binary){
		task_RhnBase.OpenAndLogIn();
        page_Configuration.open();
        goToChannel(channel, false);
        if(rh.isTextPresent(channel));
        	deleteMyChannel(channel);
        page_Configuration.clickCreateNewConfigChannel();
        page_Configuration.setNewConfigChannelName(channel);
        page_Configuration.setNewConfigChannelLabel(channel);
        page_Configuration.setNewConfigChannelDescription(channel);
        page_Configuration.clickButton_CreateConfigChannel();


        goToChannel(channel, false);
        if(page_Configuration.is_ListRemoveLinkPresent())
        	page_Configuration.clickLink_ListRemoveFiles();
        if(rh.isTextPresent(file)){
        	page_Configuration.check_ConfigFileList();
        	page_Configuration.clickButton_RemoveSelectedFiles();
        	Assert.assertTrue(rh.isTextPresent("1 configuration files were successfully deleted."));
        }

        page_Configuration.clickLink_AddFiles();
        page_Configuration.checkRadio_BinaryFile();
        page_Configuration.setTxt_FileToUpload(file);
        page_Configuration.setTxt_FileNamePath(path);
        page_Configuration.clickButton_UploadConfigurationFile();
        //Revision 1 of /tmp/rhnTestFile from channel
        Assert.assertTrue(rh.isTextPresent("Revision 1 of "+ path +" from channel"));

        //now check multiple revisions...
        page_Configuration.clickButton_UpdateConfigurationFile();
        Assert.assertTrue(rh.isTextPresent("Revision 2 of "+ path +" from channel"));

        page_Configuration.setTxt_FilePermissionsMode("755");
        page_Configuration.clickButton_UpdateConfigurationFile();
        Assert.assertTrue(rh.isTextPresent("Revision 3 of "+ path +" from channel"));
        Assert.assertTrue(rh.isTextPresent("755"));
	}

	public void updateConfigurationFile(String channel, String file, String updatedTxt, int revision, boolean openAndLogin, boolean binary){
		goToChannel(channel, openAndLogin);
		page_Configuration.clickLink_ListRemoveFiles();
		if(rh.isTextNotPresent("No files found")){
			page_Configuration.setTxt_FilterByFileName(file);
			page_RhnCommon.clickButton_Filter_Go();
			rh.clickLink("link="+file, true);
			if(!binary)
				page_Configuration.setTxt_FileContents(updatedTxt);
	        page_Configuration.clickButton_UpdateConfigurationFile();
	        Assert.assertTrue(rh.isTextPresent("Revision "+revision+" of "+ file +" from channel"));
		}

	}


	public boolean goToChannel(String channel, boolean openAndLogin){
		boolean found;
		if(openAndLogin)
			task_RhnBase.OpenAndLogIn();
		page_Configuration.open();
		page_Configuration.clickLink_ConfigurationChannels();
		if(rh.isTextPresent("No configuration channels")){
			found = false;
		}
		else{
	        page_RhnCommon.setTxt_FilterBy(channel);
	        page_RhnCommon.clickButton_Filter_Go();
	        if(rh.isTextPresent(channel)){
	        	page_Configuration.clickLink_ChannelName(channel);
	        	found = true;
	        }
	        else
	        	found = false;
		}

        return found;
	}



	public void ImportFilesToChannel(String channel,String importFile){
		task_RhnBase.OpenAndLogIn();
        page_Configuration.open();
        goToChannel(channel, false);
        if(rh.isTextPresent(channel));
    		deleteMyChannel(channel);
        page_Configuration.clickCreateNewConfigChannel();
        page_Configuration.setNewConfigChannelName(channel);
        page_Configuration.setNewConfigChannelLabel(channel);
        page_Configuration.setNewConfigChannelDescription(channel);
        page_Configuration.clickButton_CreateConfigChannel();

        goToChannel(channel, false);
        page_Configuration.clickLink_AddFiles();
        page_Configuration.clickLink_ImportFiles();

        page_Configuration.setTxt_FilterByFileName(importFile+"-import");
        page_RhnCommon.clickButton_Filter_Go();
        page_Configuration.check_ConfigFileList();
        page_Configuration.clickButton_ImportConfigurationFile();

        Assert.assertTrue(rh.isTextPresent("Successfully imported file(s)."));
        page_Configuration.setTxt_FilterByFileName(importFile);
        page_RhnCommon.clickButton_Filter_Go();
        Assert.assertTrue(rh.isTextPresent(channel));

        System.out.println("break");
	}

	public void CreateFileInChannel(String channel,String filename,String contents){
		task_RhnBase.OpenAndLogIn();
        page_Configuration.open();

        goToChannel(channel, false);
        if(rh.isTextPresent(channel));
    		deleteMyChannel(channel);
        page_Configuration.clickCreateNewConfigChannel();
        page_Configuration.setNewConfigChannelName(channel);
        page_Configuration.setNewConfigChannelLabel(channel);
        page_Configuration.setNewConfigChannelDescription(channel);
        page_Configuration.clickButton_CreateConfigChannel();

        page_Configuration.clickLink_ConfigurationChannels();
        goToChannel(channel, false);
       // cp.clickLink_ChannelName(channel);
        page_Configuration.clickLink_AddFiles();
        page_Configuration.clickLink_CreateFile();

        page_Configuration.checkRadio_TextFile();
        page_Configuration.setTxt_FileNamePath(filename);
        page_Configuration.setTxt_FileContents(contents);
        page_Configuration.clickButton_CreateConfigurationFile();
        if((rh.isTextPresent("duplicate")))
        	log.info("FAILURE: A duplicate of this file has been found");
        Assert.assertTrue(rh.isTextPresent("Revision 1 of " +filename+" from channel "+channel));
        System.out.println("break");
        // success Revision 1 of /tmp/createdFile01 from channel
        //error createdFile01: Configuration file path names must start with a '/'
	}



	public void CreateFileInChannelWithError(String channel,String filename,String contents){
		task_RhnBase.OpenAndLogIn();
		goToCreateFile(channel);

        page_Configuration.checkRadio_TextFile();
        page_Configuration.setTxt_FileNamePath(filename);
        page_Configuration.setTxt_FileContents(contents);
        page_Configuration.clickButton_CreateConfigurationFile();

        Assert.assertTrue(rh.isTextPresent(filename+": Configuration file path names must start with a '/'"));
        System.out.println("break");
        // success Revision 1 of /tmp/createdFile01 from channel
        //error createdFile01: Configuration file path names must start with a '/'
	}

	private void goToCreateFile(String channel){

        page_Configuration.open();
        goToChannel(channel, false);
        page_Configuration.clickLink_AddFiles();
        page_Configuration.clickLink_CreateFile();
	}

	public void CreateDirectoryInChannel(String channel,String filename){
		task_RhnBase.OpenAndLogIn();

		page_Configuration.open();
		goToChannel(channel, false);
        if(rh.isTextPresent(channel));
    		deleteMyChannel(channel);
        page_Configuration.clickCreateNewConfigChannel();
        page_Configuration.setNewConfigChannelName(channel);
        page_Configuration.setNewConfigChannelLabel(channel);
        page_Configuration.setNewConfigChannelDescription(channel);
        page_Configuration.clickButton_CreateConfigChannel();

		goToCreateFile(channel);

        page_Configuration.checkRadio_Directory();
        page_Configuration.setTxt_FileNamePath(filename);
        //cp.setTxt_FileContents(contents);
        page_Configuration.clickButton_CreateConfigurationFile();

        //Assert.assertTrue(sel.isTextPresent("Revision 1 of " +filename+" from channel " +channel ));
        Assert.assertTrue(rh.isTextPresent("Revision 1 of " +filename+" from channel " +channel ));

        System.out.println("break");
	}



	public void CreateDuplicateDirectoryInChannel(String channel,String filename){
		task_RhnBase.OpenAndLogIn();
		page_Configuration.open();
		goToChannel(channel, false);
        if(rh.isTextPresent(channel));
    		deleteMyChannel(channel);
        page_Configuration.clickCreateNewConfigChannel();
        page_Configuration.setNewConfigChannelName(channel);
        page_Configuration.setNewConfigChannelLabel(channel);
        page_Configuration.setNewConfigChannelDescription(channel);
        page_Configuration.clickButton_CreateConfigChannel();

		goToCreateFile(channel);
        page_Configuration.checkRadio_Directory();
        page_Configuration.setTxt_FileNamePath(filename);
        //cp.setTxt_FileContents(contents);
        page_Configuration.clickButton_CreateConfigurationFile();
        Assert.assertTrue(rh.isTextPresent("Revision 1 of " +filename+" from channel " +channel ));

        goToCreateFile(channel);
        page_Configuration.checkRadio_Directory();
        page_Configuration.setTxt_FileNamePath(filename);
        //cp.setTxt_FileContents(contents);
        page_Configuration.clickButton_CreateConfigurationFile();
        Assert.assertTrue(rh.isTextPresent("A file named \""+filename+"\" already exists in this channel."));

        System.out.println("break");
	}


	public void CreateMultipleRevisionOfDirectoryInChannel(String channel,String filename){
		task_RhnBase.OpenAndLogIn();
		page_Configuration.open();

		goToChannel(channel, false);
		page_Configuration.clickLink_ListRemoveFiles();
		page_RhnCommon.clickLink_GeneralLink(filename);
		page_Configuration.clickButton_UpdateConfigurationFile();
        Assert.assertTrue(rh.isTextPresent("Revision 2 of " +filename+" from channel " +channel ));
        page_Configuration.setTxt_FilePermissionsMode("755");
        page_Configuration.clickButton_UpdateConfigurationFile();
        Assert.assertTrue(rh.isTextPresent("Revision 3 of " +filename+" from channel " +channel ));
        Assert.assertTrue(rh.isTextPresent("755"));
        System.out.println("break");
	}



	public void DeleteAllFilesInChannel(String channel){
		task_RhnBase.OpenAndLogIn();

		page_Configuration.open();
        page_Configuration.clickLink_ConfigurationChannels();
        page_Configuration.clickLink_ChannelName(channel);
        page_Configuration.clickLink_ListRemoveFiles();

	}

	public void subscribeSystemToChannel(String channel,String system){
		task_RhnBase.OpenAndLogIn();
        page_Configuration.open();

        goToChannel(channel, false);
        if(rh.isTextPresent(channel));
    		deleteMyChannel(channel);
        page_Configuration.clickCreateNewConfigChannel();
        page_Configuration.setNewConfigChannelName(channel);
        page_Configuration.setNewConfigChannelLabel(channel);
        page_Configuration.setNewConfigChannelDescription(channel);
        page_Configuration.clickButton_CreateConfigChannel();

        goToChannel(channel, false);
        page_Configuration.clickLink_SystemsForConfigChannel();
        page_Configuration.clickLink_TargetSystems();

        page_RhnCommon.setTxt_FilterBy(system);
        page_RhnCommon.clickButton_Filter_Go();
        page_RhnCommon.check_FirstItemInList();
        page_Configuration.clickButton_SubscribeSystems();
        Assert.assertTrue(rh.isTextPresent("Successfully subscribed 1 system(s)."));
        page_Configuration.clickLink_SubscribedSystems();
        Assert.assertTrue(rh.isTextPresent(system));
        System.out.println("break");
	}

	public void unsubscribeSystemToChannel(String channel,String system){
		task_RhnBase.OpenAndLogIn();
        page_Configuration.open();

        goToChannel(channel, false);
        page_Configuration.clickLink_SystemsForConfigChannel();
        page_Configuration.clickLink_SubscribedSystems();
        page_RhnCommon.check_FirstItemInList();
        page_Configuration.clickButton_UnsubscribeSystems();
        Assert.assertTrue(rh.isTextPresent("Successfully unsubscribed 1 system(s)."));
        page_Configuration.clickLink_TargetSystems();
        Assert.assertTrue(rh.isTextPresent(system));
	}

	public void subscribeSystemDeployFile(String channel,String system){
		String fileTxt = "RHN Rocks";
		task_RhnBase.OpenAndLogIn();
        page_Configuration.open();

        goToChannel(channel, false);
        page_Configuration.clickLink_SystemsForConfigChannel();
        page_Configuration.clickLink_TargetSystems();
        page_RhnCommon.setTxt_FilterBy(system);
        page_RhnCommon.clickButton_Filter_Go();
        page_RhnCommon.check_FirstItemInList();
        page_Configuration.clickButton_SubscribeSystems();
//        Assert.assertTrue(rh.isTextPresent("Successfully subscribed 1 system(s)."));
        page_Configuration.clickLink_SubscribedSystems();
        Assert.assertTrue(rh.isTextPresent(system));

        page_Configuration.clickLink_AddFiles();
        page_Configuration.clickLink_CreateFile();
        page_Configuration.checkRadio_TextFile();
        page_Configuration.setTxt_FileNamePath(fileSingle);
        page_Configuration.setTxt_FileContents(fileTxt);
        page_Configuration.clickButton_CreateConfigurationFile();

        page_Configuration.clickLink_DeployFile();
        page_RhnCommon.setTxt_FilterBy(system);
        page_RhnCommon.clickButton_Filter_Go();
        page_RhnCommon.check_FirstItemInList();
        try{
        page_Configuration.clickButton_DeploySelectedFiles();
        log.info("deploy not found");
        }
        catch(SeleniumException se){
        	page_Configuration.clickButton_ScheduleFileDeployment();
        	log.info("Schedule File Deployment");
        }
       //
        Assert.assertTrue(rh.isTextPresent(system));
        try{
        page_Configuration.clickButton_ConfirmAndDeployFile();
        }
        catch(SeleniumException se){
        	page_RhnCommon.check_FirstItemInList();
            page_Configuration.clickButton_ConfirmAndDeployToSelectedSystems();
            page_Configuration.clickButton_DeployFilesToSelectedSystems();
        }

        Assert.assertTrue(rh.isTextPresent("1 revision-deploy successfully scheduled."));

        ExecCommands exec = new ExecCommands();
		try{
			String result = "";
			task_TestPrep.command_runRhnCheckInForeground(system, true);
			//rhn-actions-control --enable-all
			result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" ls -la "+fileSingle).trim();
			result = result.trim();
			log.info("result = "+ result);
			Assert.assertTrue(result.matches(".*"+fileSingle));
			result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" cat "+fileSingle).trim();
			log.info("result = "+ result);
			Assert.assertTrue(result.matches(".*"+fileTxt));

		} catch(IOException ioe){
			log.info("command failed");
		}
	}

	public void select_CfgMngmt_FileInSDC(String file){
		 try{
			 page_SDC.setTxt_SDCFilterBy(file);
		     //rc.clickButton_Filter_Go();
		     page_SDC.clickButton_FilterGo();}

		 catch(SeleniumException se){
			 log.info("Bug 227700, file filter not found");
		 }

	     page_SDC.select_File_Checkbox(file);
	}

	public void select_CfgMngmt_FileInCentral(String file){
		 try{
			 page_RhnCommon.setTxt_FilterBy(file);
		     //rc.clickButton_Filter_Go();
		     page_SDC.clickButton_FilterGo();}

		 catch(SeleniumException se){
			 log.info("Bug 227700, file filter not found");
		 }

	     //sdc.select_File_Checkbox(file);
		 page_Configuration.select_File_Checkbox(file);
	}



	public void localOverrideSingleDeploy(String channel,String system, boolean hosted){
		
		
		String fileTxt = "RHN Rocks";
		String localOverrideTxt = "SINGLE Local SysAdmins Rocks";
		task_RhnBase.OpenAndLogIn();

		page_Configuration.open();
        goToChannel(channel, false);
        page_Configuration.clickLink_ChannelOverview();
        page_Configuration.clickLink_ListRemoveFiles();
        page_Configuration.setTxt_FilterByFileName(fileSingle);
        page_RhnCommon.clickButton_Filter_Go();
        page_RhnCommon.check_FirstItemInList();
        page_Configuration.clickButton_CopyToSystems();
        Assert.assertTrue(rh.isTextPresent(system));
        page_RhnCommon.clickButton_SelectAll();
        page_Configuration.clickButton_Dispatch();
        Assert.assertTrue(rh.isTextPresent("1 file copied into"));

        page_Configuration.open();
        goToChannel(channel, false);
        page_Configuration.clickLink_ChannelOverview();
        page_Configuration.clickLink_ListRemoveFiles();
        page_Configuration.setTxt_FilterByFileName(fileSingle);
        page_RhnCommon.clickButton_Filter_Go();
        page_Configuration.clickLink_Compare();
        page_Configuration.clickLink_OtherCopies();
        page_Configuration.clickLink_ViewComparison(system, hosted);
        Assert.assertTrue(rh.isTextPresent("View File Comparison"));
        Assert.assertTrue(rh.isTextPresent(fileTxt));
        page_Configuration.clickLink_OnlyChangedLines();
        Assert.assertTrue(rh.isTextNotPresent(fileTxt));

		 task_Search.goToSystem(system);
	     page_RhnCommon.clickLink_SystemName(system);
	     page_SDC.clickLink_Configuation();
	     page_SDC.clickLink_ViewModifyFiles();
	     page_SDC.clickLink_LocallyManagedFiles();

	     select_CfgMngmt_FileInSDC(fileSingle);

	     page_SDC.clickLink_EditFile();
	     page_Configuration.setTxt_FileContents(localOverrideTxt);
	     page_Configuration.clickButton_UpdateConfigurationFile();

	     page_Configuration.open();
	     goToChannel(channel, false);
	     page_Configuration.clickLink_DeploySelectedConfigFilesToSelectedSystem();
	     page_Configuration.setTxt_FilterByFileName(fileSingle);
	     page_RhnCommon.clickButton_Filter_Go();//cp.clickButton_Go();
	     page_RhnCommon.check_FirstItemInList();
	     page_Configuration.clickButton_DeploySelectedFiles();
	     page_RhnCommon.check_FirstItemInList();
	     page_Configuration.clickButton_ConfirmAndDeployToSelectedSystems();//dispatch
	     Assert.assertTrue(rh.isTextPresent("Deploy Configuration Files"));
	     page_Configuration.clickButton_DeployFilesToSelectedSystems();
	     Assert.assertTrue(rh.isTextPresent("1 revision-deploy overridden."));

	     ExecCommands exec = new ExecCommands();
			try{
				String result = "";
				task_TestPrep.command_runRhnCheckInForeground(system, true);
				//rhn-actions-control --enable-all
				result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" cat "+fileSingle).trim();
				result = result.trim();
				log.info("result = "+ result);
				Assert.assertTrue(result.matches(".*"+localOverrideTxt));
			} catch(IOException ioe){
				log.info("command failed");
			}
	}

	public void compareTwoConfigFilesAfterChange(String channel,String system, boolean hosted){
		String localOverrideTxt = "SINGLE Local SysAdmins Rocks";
		String fileTxt = "RHN Rocks";
		task_RhnBase.OpenAndLogIn();

		page_Configuration.open();
        goToChannel(channel, false);
        page_Configuration.clickLink_ChannelOverview();
        page_Configuration.clickLink_ListRemoveFiles();
        select_CfgMngmt_FileInCentral(fileSingle);
        page_Configuration.clickLink_Compare();
        page_Configuration.clickLink_OtherCopies();
        page_Configuration.clickLink_ViewComparison(system, hosted);
        Assert.assertTrue(rh.isTextPresent("Full Comparison"));
        Assert.assertTrue(rh.isTextPresent(fileTxt));
        Assert.assertTrue(rh.isTextPresent(localOverrideTxt));
        Assert.assertTrue(rh.isTextPresent(system));
        Assert.assertTrue(rh.isTextPresent(channel));
	}

	public void deployMultipleFiles(String channel,String system){
		task_RhnBase.OpenAndLogIn();
        page_Configuration.open();

        goToChannel(channel, false);
        page_Configuration.clickLink_AddFiles();
        page_Configuration.clickLink_CreateFile();
        page_Configuration.checkRadio_TextFile();
        page_Configuration.setTxt_FileNamePath(file01);
        page_Configuration.setTxt_FileContents("RHN Rocks");
        page_Configuration.clickButton_CreateConfigurationFile();

        page_Configuration.open();
        goToChannel(channel, false);
        page_Configuration.clickLink_AddFiles();
        page_Configuration.clickLink_ImportFiles();
        /*cp.setTxt_FilterByFileName(file02);
        rc.clickButton_Filter_Go();*/
        //select_CfgMngmt_FileInSDC(file02);
        select_CfgMngmt_FileInCentral(file02);
       // cp.check_ConfigFileList();
        page_Configuration.clickButton_ImportConfigurationFile();
        Assert.assertTrue(rh.isTextPresent("Successfully imported file(s)."));

        page_Configuration.open();
        goToChannel(channel, false);
        page_Configuration.clickLink_AddFiles();
        page_Configuration.setTxt_FileToUpload("/etc/redhat-release");
        page_Configuration.setTxt_FileNamePath(file03);
        page_Configuration.clickButton_UploadConfigurationFile();
        Assert.assertTrue(rh.isTextPresent("Revision 1 of "+ file03 +" from channel"));

        page_Configuration.open();
        goToChannel(channel, false);
        page_Configuration.clickLink_ChannelOverview();
        page_Configuration.clickLink_DeployAllConfigFilesToSelectedSystem();
        Assert.assertTrue(rh.isTextPresent(system));
        page_RhnCommon.check_FirstItemInList();
//        cp.clickButton_ConfirmAndDeployMultipleFiles();//dispatch
        page_Configuration.clickButton_ConfirmAndDeployToSelectedSystems();
        Assert.assertTrue(rh.isTextPresent("Deploy Configuration Files"));
        //cp.clickButton_ConfirmAndDeployMultipleFiles1();//dispatch
        page_Configuration.clickButton_DeployFilesToSelectedSystems();
        Assert.assertTrue(rh.isTextPresent("3 revision-deploys successfully scheduled."));
        Assert.assertTrue(rh.isTextPresent("1 revision-deploy overridden."));

        ExecCommands exec = new ExecCommands();
		try{
			String result = "";
			task_TestPrep.command_runRhnCheckInForeground(system, true);
			//rhn-actions-control --enable-all
			result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" ls -la "+file01).trim();
			log.info("result = "+ result);
			Assert.assertTrue(result.matches(".*"+file01));
			result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" ls -la "+file02).trim();
			log.info("result = "+ result);
			Assert.assertTrue(result.matches(".*"+file02));
			result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" ls -la "+file03).trim();
			log.info("result = "+ result);
			Assert.assertTrue(result.matches(".*"+file03));
		} catch(IOException ioe){
			log.info("command failed");
		}
	}

	public void copyMultipleFilesToSystems(String channel,String system){
		
		task_RhnBase.OpenAndLogIn();
        page_Configuration.open();

        goToChannel(channel, false);
        page_Configuration.clickLink_ChannelOverview();
        page_Configuration.clickLink_ListRemoveFiles();
        page_RhnCommon.clickButton_SelectAll();
        page_Configuration.clickButton_CopyToSystems();
        page_RhnCommon.clickButton_SelectAll();
        page_Configuration.clickButton_Dispatch();

        task_Search.goToSystem(system);
        page_RhnCommon.clickLink_SystemName(system);
        page_SDC.clickLink_Configuation();
        page_SDC.clickLink_ViewModifyFiles();
        page_SDC.clickLink_LocallyManagedFiles();
        Assert.assertTrue(rh.isTextPresent(file01));
        Assert.assertTrue(rh.isTextPresent(file02));
        Assert.assertTrue(rh.isTextPresent(file03));

	}




	public void localOverrideMultipleDeploy(String channel,String system){
		 
		 String localOverrideTxt = "Local SysAdmins Rocks";
		 String[] localFile = new String[3];
		 localFile[0]=file01;
		 localFile[1]=file02;
		 localFile[2]=file03;

		 task_RhnBase.OpenAndLogIn();

		 for (int i=0;i<localFile.length;i++){
		 task_Search.goToSystem(system);
	     page_RhnCommon.clickLink_SystemName(system);
	     page_SDC.clickLink_Configuation();
	     page_SDC.clickLink_ViewModifyFiles();
	     page_SDC.clickLink_LocallyManagedFiles();
	     /*sdc.setTxt_SDCFilterBy(localFile[i]);
	     sdc.clickButton_FilterGo();*/
	     log.info("file = "+localFile[i]);

	     /*select_CfgMngmt_FileInSDC(localFile[i]);
	     sdc.clickLink_EditFile();*/
	     page_SDC.clickLink_EditInRow(localFile[i]);

	     page_Configuration.setTxt_FileContents(localOverrideTxt);
	     page_Configuration.clickButton_UpdateConfigurationFile();
		 }

		    page_Configuration.open();
	        goToChannel(channel, false);
	        page_Configuration.clickLink_ChannelOverview();
	        page_Configuration.clickLink_DeployAllConfigFilesToSelectedSystem();
	        Assert.assertTrue(rh.isTextPresent(system));
	        page_RhnCommon.check_FirstItemInList();
	      //  cp.clickButton_ConfirmAndDeployMultipleFiles();//dispatch
	        page_Configuration.clickButton_ConfirmAndDeployToSelectedSystems();
	        Assert.assertTrue(rh.isTextPresent("Deploy Configuration Files"));
	        //cp.clickButton_ConfirmAndDeployMultipleFiles1();//dispatch
	        page_Configuration.clickButton_DeployFilesToSelectedSystems();
	        Assert.assertTrue(rh.isTextPresent("4 revision-deploys overridden."));

	        ExecCommands exec = new ExecCommands();
			try{
				String result = "";
				task_TestPrep.command_runRhnCheckInForeground(system, true);
				//rhn-actions-control --enable-all
				result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" cat "+file01).trim();
				log.info("result = "+ result);
				Assert.assertTrue(result.matches(".*"+localOverrideTxt));
				result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" cat "+file02).trim();
				log.info("result = "+ result);
				Assert.assertTrue(result.matches(".*"+localOverrideTxt));
				result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" cat "+file03).trim();
				log.info("result = "+ result);
				Assert.assertTrue(result.matches(".*"+localOverrideTxt));
			} catch(IOException ioe){
				log.info("command failed");
			}

	}

	public void sdcCompareMultipleFiles(String channel,String system){
		 
		 String localOverrideTxt = "Local SysAdmins Rocks";
		 String[] localFile = new String[3];
		 localFile[0]=file01;
		 localFile[1]=file02;
		 localFile[2]=file03;

		 task_RhnBase.OpenAndLogIn();
		 task_Search.goToSystem(system);
	     page_RhnCommon.clickLink_SystemName(system);
	     page_SDC.clickLink_Configuation();
	     page_SDC.clickLink_CompareAllManagedFilesToSystem();
	     page_Configuration.clickButton_Dispatch();
	     Assert.assertTrue(rh.isTextPresent("4 files scheduled for comparison."));
		task_TestPrep.command_runRhnCheckInForeground(system, true);
		page_SDC.clickLink_Configuation();
		//sdc.clickLink_ViewDetailsSystemComparison();
		page_SDC.clickLink_Events();
		page_SDC.clickLink_History();
		page_SDC.clickLink_ShowDiffHistory();
		Assert.assertTrue(rh.isTextPresent("This action's status is: Completed."));
		Assert.assertTrue(rh.isTextPresent("Client execution returned \"Files successfully diffed\" (code 0)"));
		Assert.assertFalse(rh.isElementPresent("link=Differences exist", true));
	}


	public void sdcCreateDeleteCreateCopyFile(String channel,String system){
		
		String fileTxt = "SingleSDC File, Rhn Rocks";
		task_RhnBase.OpenAndLogIn();
		task_Search.goToSystem(system);
	    page_RhnCommon.clickLink_SystemName(system);
	    page_SDC.clickLink_Configuation();
	    page_SDC.clickLink_AddFiles();

	    page_Configuration.clickLink_CreateFile();
        page_Configuration.checkRadio_TextFile();
        page_Configuration.setTxt_FileNamePath(fileSDCSingle);
        page_Configuration.setTxt_FileContents(fileTxt);
        page_Configuration.clickButton_CreateConfigurationFile();

        /*sdc.setTxt_SDCFilterBy(fileSDCSingle);
        sdc.clickButton_FilterGo();*/
        select_CfgMngmt_FileInSDC(fileSDCSingle);
        page_RhnCommon.clickButton_SelectAll();
        page_SDC.clickButton_DeleteFiles();
        Assert.assertTrue(rh.isTextPresent("1 file(s) successfully deleted from Sandbox."));

        page_SDC.clickLink_Configuation();
	    page_SDC.clickLink_AddFiles();

	    page_Configuration.clickLink_CreateFile();
        page_Configuration.checkRadio_TextFile();
        page_Configuration.setTxt_FileNamePath(fileSDCSingle);
        page_Configuration.setTxt_FileContents(fileTxt);
        page_Configuration.clickButton_CreateConfigurationFile();

        /*sdc.setTxt_SDCFilterBy(fileSDCSingle);
        //rc.clickButton_Filter_Go();
        sdc.clickButton_FilterGo();*/
        select_CfgMngmt_FileInSDC(fileSDCSingle);
        page_RhnCommon.clickButton_SelectAll();
        page_SDC.clickButton_CopyLatestToSystemChannel();
        Assert.assertTrue(rh.isTextPresent("1 file(s) successfully copied to System Channel"));
	}

	public void sdcScheduleDeployAllFiles(String system){
		task_Search.goToSystem(system);
	    page_RhnCommon.clickLink_SystemName(system);
	    page_SDC.clickLink_Configuation();
	    page_SDC.clickLink_DeployAllFiles();
	    page_Configuration.clickButton_ScheduleDeploy();
	}
	
	public void sdcDeployFile(String channel,String system){
		
		String fileTxt = "SingleSDC File, Rhn Rocks";
		task_RhnBase.OpenAndLogIn();
		task_Search.goToSystem(system);
	    page_RhnCommon.clickLink_SystemName(system);
	    page_SDC.clickLink_Configuation();
	    page_SDC.clickLink_DeployFiles();
	    page_RhnCommon.setTxt_FilterBy(fileSDCSingle);
	    page_RhnCommon.clickButton_Filter_Go();
	    page_SDC.check_FirstItemInList();
	    page_Configuration.clickButton_DeployFiles();
	    page_Configuration.clickButton_Dispatch();
	    Assert.assertTrue(rh.isTextPresent("1 file(s) scheduled for deploy."));

	    ExecCommands exec = new ExecCommands();
		try{
			String result = "";
			task_TestPrep.command_runRhnCheckInForeground(system, true);
			//rhn-actions-control --enable-all
			result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" ls -la "+fileSDCSingle).trim();
			log.info("result = "+ result);
			Assert.assertTrue(result.matches(".*"+fileSDCSingle));
			result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" cat "+fileSDCSingle).trim();
			log.info("result = "+ result);
			Assert.assertTrue(result.matches(".*"+fileTxt));

		} catch(IOException ioe){
			log.info("command failed");
		}
	}

	public void sdcEditReDeployFile(String channel,String system){
		
		String fileTxt = "Updated txt, SingleSDC File, Rhn Rocks";
		task_RhnBase.OpenAndLogIn();
		task_Search.goToSystem(system);
	    page_RhnCommon.clickLink_SystemName(system);
	    page_SDC.clickLink_Configuation();
	    page_SDC.clickLink_ViewModifyFiles();
	    page_SDC.clickLink_LocallyManagedFiles();
	    /*sdc.setTxt_SDCFilterBy(fileSDCSingle);
	    sdc.clickButton_FilterGo();*/
	    select_CfgMngmt_FileInSDC(fileSDCSingle);
	    //rc.check_SelectAll();
	    page_SDC.check_FirstItemInList1();
	    page_SDC.clickButton_CopyLatestToCentralChannel();
	    page_RhnCommon.clickButton_SelectAll();
	    page_Configuration.clickButton_Dispatch();

		task_Search.goToSystem(system);
	    page_RhnCommon.clickLink_SystemName(system);
	    page_SDC.clickLink_Configuation();
	    page_SDC.clickLink_ViewModifyFiles();
	    page_SDC.clickLink_LocallyManagedFiles();
	    //rc.setTxt_FilterBy(fileSDCSingle);
	    /*sdc.setTxt_SDCFilterBy(fileSDCSingle);
	    rc.clickButton_Filter_Go_valueGo();*/
	   /* select_CfgMngmt_FileInSDC(fileSDCSingle);
	    sdc.clickLink_EditFile();*/
	    page_SDC.clickLink_EditInRow(fileSDCSingle);
	    page_Configuration.setTxt_FileContents(fileTxt);
	   // rh.screenShot("SDC_EDIT", "fileSDCSingle");
	    page_Configuration.clickButton_UpdateConfigurationFile();
	    Assert.assertTrue(rh.isTextPresent("of "+ fileSDCSingle +" from channel "));

	    task_Search.goToSystem(system);
	    page_RhnCommon.clickLink_SystemName(system);
	    page_SDC.clickLink_Configuation();
	    page_SDC.clickLink_DeployFiles();
	    page_RhnCommon.setTxt_FilterBy(fileSDCSingle);
	    page_RhnCommon.clickButton_Filter_Go();
	    //sdc.check_FirstItemInList1();
	    page_RhnCommon.check_FirstItemInList();
	    page_Configuration.clickButton_DeployFiles();
	    page_Configuration.clickButton_Dispatch();
	    Assert.assertTrue(rh.isTextPresent("1 file(s) scheduled for deploy."));


	    ExecCommands exec = new ExecCommands();
		try{
			String result = "";
			task_TestPrep.command_runRhnCheckInForeground(system, true);
			//rhn-actions-control --enable-all
			result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" ls -la "+fileSDCSingle).trim();
			log.info("result = "+ result);
			Assert.assertTrue(result.matches(".*"+fileSDCSingle));
			result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" cat "+fileSDCSingle).trim();
			log.info("result = "+ result);
			Assert.assertTrue(result.matches(".*"+fileTxt));

		} catch(IOException ioe){
			log.info("command failed");
		}
	}

	public void sdcManageConfigChannels(String system){
		
		String firstEdit = "first edit";
		String[] channels = new String[3];
		channels[0]="A_Channel";
		channels[1]="B_Channel";
		channels[2]="C_Channel";

		task_RhnBase.OpenAndLogIn();

		page_Configuration.open();
        if(goToChannel("channelSystemTest", false)){
        page_Configuration.clickLink_SystemsForConfigChannel();
        page_Configuration.clickLink_SubscribedSystems();
        if(rh.isTextNotPresent("No systems found.")){
        page_RhnCommon.check_FirstItemInList();
        page_Configuration.clickButton_UnsubscribeSystems();
        //Assert.assertTrue(rh.isTextPresent("Successfully unsubscribed 1 system(s)."));
        	}
        }





        page_Configuration.open();

        for(int i=0; i<channels.length;i++){
        goToChannel(channels[i], false);
        if(rh.isTextPresent(channels[i]));
    		deleteMyChannel(channels[i]);
        page_Configuration.clickCreateNewConfigChannel();
        page_Configuration.setNewConfigChannelName(channels[i]);
        page_Configuration.setNewConfigChannelLabel(channels[i]);
        page_Configuration.setNewConfigChannelDescription(channels[i]);
        page_Configuration.clickButton_CreateConfigChannel();
        Assert.assertTrue(rh.isTextPresent("Channel Properties"));
        page_Configuration.clickLink_AddFiles();
        page_Configuration.clickLink_CreateFile();

        page_Configuration.checkRadio_TextFile();
        page_Configuration.setTxt_FileNamePath("/tmp/"+channels[i]);
        page_Configuration.setTxt_FileContents(firstEdit);
        page_Configuration.clickButton_CreateConfigurationFile();
        Assert.assertTrue(rh.isTextPresent("Revision 1 of"));
        }

        page_Configuration.open();
        for(int i=0; i<channels.length;i++){
        goToChannel(channels[i], false);
        page_Configuration.clickLink_SystemsForConfigChannel();
        page_Configuration.clickLink_TargetSystems();
        page_RhnCommon.setTxt_FilterBy(system);
	    page_RhnCommon.clickButton_Filter_Go();
        page_RhnCommon.check_FirstItemInList();
        page_Configuration.clickButton_SubscribeSystems();
 //       Assert.assertTrue(rh.isTextPresent("Successfully subscribed 1 system(s)."));
        page_Configuration.clickLink_SubscribedSystems();
        Assert.assertTrue(rh.isTextPresent(system));
        }

        page_Configuration.open();
        goToChannel(channels[2], false);
        page_Configuration.clickLink_ChannelOverview();
        page_Configuration.clickLink_ListRemoveFiles();
        page_Configuration.setTxt_FilterByFileName("/tmp/"+channels[2]);
        page_RhnCommon.clickButton_Filter_Go();
        page_RhnCommon.check_FirstItemInList();
        page_Configuration.clickButton_CopytoChannels();
        page_RhnCommon.clickButton_SelectAll();
        page_Configuration.clickButton_Dispatch();

        page_Configuration.open();
        goToChannel(channels[0], false);
        page_Configuration.clickLink_ChannelOverview();
        page_Configuration.clickLink_ListRemoveFiles();
        page_Configuration.setTxt_FilterByFileName("/tmp/"+channels[2]);
        page_RhnCommon.clickButton_Filter_Go();
        page_RhnCommon.check_FirstItemInList();
        page_Configuration.clickLink_View();
        String update = channels[0]+"'s update to /tmp/"+channels[2];
        page_Configuration.setTxt_FileContents(update);
        page_Configuration.clickButton_UpdateConfigurationFile();

        task_Search.goToSystem(system);
	    page_RhnCommon.clickLink_SystemName(system);
	    page_SDC.clickLink_Configuation();
	    page_SDC.clickLink_DeployFiles();
	    page_RhnCommon.setTxt_FilterBy("/tmp/"+channels[0]);
	    page_RhnCommon.clickButton_Filter_Go();
	    Assert.assertTrue(rh.isTextPresent(channels[0]));

	    page_RhnCommon.setTxt_FilterBy("/tmp/"+channels[1]);
	    page_RhnCommon.clickButton_Filter_Go();
	    Assert.assertTrue(rh.isTextPresent(channels[1]));

	    page_RhnCommon.setTxt_FilterBy("/tmp/"+channels[2]);
	    page_RhnCommon.clickButton_Filter_Go();
	    Assert.assertTrue(rh.isTextPresent(channels[0]));

	    page_RhnCommon.setTxt_FilterBy("");
	    page_RhnCommon.clickButton_Filter_Go();
	    page_RhnCommon.clickButton_SelectAll();
	    page_Configuration.clickButton_DeployFiles();
	    page_SDC.clickButton_ScheduleDeploy();

	    ExecCommands exec = new ExecCommands();
		try{
			String result = "";
			task_TestPrep.command_runRhnCheckInForeground(system, true);
			//rhn-actions-control --enable-all
			result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" ls -la /tmp/"+channels[2]).trim();
			log.info("result = "+ result);
			Assert.assertTrue(result.matches(".*"+channels[2]));
			result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" cat /tmp/"+channels[2]).trim();
			log.info("result = "+ result);
			Assert.assertTrue(result.matches(".*"+update));

		} catch(IOException ioe){
			log.info("command failed");
		}


	}

	
	public void deleteAllConfigChannels(boolean openAndLogin){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_Configuration.open();
		page_Configuration.clickLink_ConfigurationChannels();
		while(rh.isElementPresent("xpath=//tr/td[2]/form/table[2]/tbody/tr/td/a",true)){
			log.fine("found a channel, will delete it");
			rh.clickLink("xpath=//tr/td[2]/form/table[2]/tbody/tr/td/a",true);
			page_Configuration.clickLink_DeleteChannel();
			page_Configuration.clickButton_DeleteConfigChannel();
			rh.sleepForSeconds(1);
			rh.sleepForSeconds(1);
			page_Configuration.clickLink_ConfigurationChannels();
		}
		
	}
	
	public void subscribeSystemToChannel(String channel,String system, boolean removeExisting){
		throw new SeleniumException("wrong version of method");
	}

}
