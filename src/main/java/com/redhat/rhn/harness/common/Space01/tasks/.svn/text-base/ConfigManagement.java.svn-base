package com.redhat.rhn.harness.common.Space01.tasks;

import java.io.IOException;

import org.testng.Assert;

import com.redhat.qe.tools.ExecCommands;
import com.redhat.rhn.harness.baseInterface.IConfigManagement;
import com.redhat.rhn.harness.baseInterface.IRhnBase;



public class ConfigManagement extends com.redhat.rhn.harness.common.Sat51.tasks.ConfigManagement implements IConfigManagement{

	
	
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
				exec.submitCommandToLocal("ssh", "root@"+system +" rhn_check");
				//rhn-actions-control --enable-all
				result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" cat "+fileSingle);
				log.info("result ="+ result);
				//Assert.assertTrue(result.equalsIgnoreCase(localOverrideTxt));
				Assert.assertTrue(result.matches(".*"+localOverrideTxt));
			} catch(IOException ioe){
				log.info("command failed");
			}
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
        page_Configuration.check_ConfigFileList();
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
			exec.submitCommandToLocal("ssh", "root@"+system +" rhn_check ");
			//rhn-actions-control --enable-all
			result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" ls -la "+file01);
			log.info("result = "+ result);
			Assert.assertTrue(result.matches(".*"+file01));
			result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" ls -la "+file02);
			log.info("result = "+ result);
			Assert.assertTrue(result.matches(".*"+file02));
			result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" ls -la "+file03);
			log.info("result = "+ result);
			Assert.assertTrue(result.matches(".*"+file03));
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
			page_Configuration.clickLink_ConfigurationChannels();
		}
		
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
	     //sdc.clickLink_EditInRow(localFile[i]);\
	     page_RhnCommon.setTxt_FilterBy(localFile[i]);
	     page_RhnCommon.clickButton_Filter_Go();
	     page_RhnCommon.clickLink_GeneralLink("Edit");
	     
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
				exec.submitCommandToLocal("ssh", "root@"+system +" rhn_check ");
				//rhn-actions-control --enable-all
				result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" cat "+file01);
				log.info("result = "+ result);
				Assert.assertTrue(result.matches(".*"+localOverrideTxt));
				result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" cat "+file02);
				log.info("result = "+ result);
				Assert.assertTrue(result.matches(".*"+localOverrideTxt));
				result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" cat "+file03);
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
		 task_TestPrep.command_generic("echo ", " asdf >> "+file01, IRhnBase.SYSTEM_HOSTNAME01, true);
		 task_Search.goToSystem(system);
	     page_RhnCommon.clickLink_SystemName(system);
	     page_SDC.clickLink_Configuation();
	     page_SDC.clickLink_CompareAllManagedFilesToSystem();
	     page_Configuration.clickButton_Dispatch();
	     Assert.assertTrue(rh.isTextPresent("4 files scheduled for comparison."));

	     ExecCommands exec = new ExecCommands();
			try{
				String result = "";
				exec.submitCommandToLocal("ssh", "root@"+system +" rhn_check ");
			} catch(IOException ioe){
				log.info("command failed");
			}

			page_SDC.clickLink_Configuation();
			//sdc.clickLink_ViewDetailsSystemComparison();
			page_SDC.clickLink_Events();
			page_SDC.clickLink_History();
			page_SDC.clickLink_ShowDiffHistory();
			Assert.assertTrue(rh.isTextPresent("This action's status is: Completed."));
			Assert.assertTrue(rh.isTextPresent("Client execution returned \"Files successfully diffed\" (code 0)"));
			//Assert.assertFalse(rh.isElementPresent("link=Differences exist", true));
			Assert.assertTrue(rh.isTextPresent("Differences exist"));
			page_RhnCommon.clickLink_GeneralLink("Differences exist");
			Assert.assertTrue(rh.isTextPresent("Diff:"));
			
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
	    //page_SDC.check_FirstItemInList1();
	    page_RhnCommon.check_FirstItemInList();
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
	    page_RhnCommon.setTxt_FilterBy(fileSDCSingle);
	    page_RhnCommon.clickButton_Filter_Go();
	    page_SDC.clickLink_EditFile();
	    //page_SDC.clickLink_EditInRow(fileSDCSingle);
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
			exec.submitCommandToLocal("ssh", "root@"+system +" rhn_check ");
			//rhn-actions-control --enable-all
			result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" ls -la "+fileSDCSingle);
			log.info("result = "+ result);
			Assert.assertTrue(result.matches(".*"+fileSDCSingle));
			result=exec.submitCommandToLocalWithReturn(false, "ssh", "root@"+system +" cat "+fileSDCSingle);
			log.info("result = "+ result);
			Assert.assertTrue(result.matches(".*"+fileTxt));

		} catch(IOException ioe){
			log.info("command failed");
		}
	}

	public void subscribeSystemToChannel(String channel,String system, boolean removeExisting){
		task_RhnBase.OpenAndLogIn();
        page_Configuration.open();

        if(removeExisting){
            goToChannel(channel, false);
        	deleteMyChannel(channel);
            page_Configuration.clickCreateNewConfigChannel();
            page_Configuration.setNewConfigChannelName(channel);
            page_Configuration.setNewConfigChannelLabel(channel);
            page_Configuration.setNewConfigChannelDescription(channel);
            page_Configuration.clickButton_CreateConfigChannel();        	
        }
        
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
	}

	private void deleteMyChannel(String channel){
		if(rh.isTextPresent(channel)){
			page_Configuration.clickLink_DeleteChannel();
			page_Configuration.clickButton_DeleteConfigChannel();
			page_Configuration.clickLink_ConfigurationChannels();
		}
	}

	






}
