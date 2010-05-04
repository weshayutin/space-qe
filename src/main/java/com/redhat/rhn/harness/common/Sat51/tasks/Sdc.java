package com.redhat.rhn.harness.common.Sat51.tasks;

import com.redhat.rhn.harness.baseInterface.ISdcSoftware;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.Sat42.tasks.Search;
import com.redhat.rhn.harness.common.Sat42.tasks.TestPrep;

public class Sdc extends com.redhat.rhn.harness.common.Sat50.tasks.Sdc implements ISdcSoftware{

	
	public void installPackage(String system, String pkg){
		page_SatelliteSystems.open();
		

        task_Search.goToSystem(system);
        page_RhnCommon.clickLink_GeneralLink(system);  
	     page_SDC.clickLink_Software(); 
	     page_SDC.clickLink_Packages();
	     page_SDC.clickLink_Install();
	     page_SDC.setTxt_SDCFilterBy(pkg);
	     page_SDC.clickButton_FilterGo();
	    if(rh.isTextPresent("The list below is not filtered")){
	    	page_RhnCommon.clickButton_SelectAll();
	    }
	    else{
	    	select_File_Checkbox(pkg);
	    }
	     page_SDC.clickButton_InstallSelectedPackages();
	     page_SDC.clickButton_Confirm();
	    task_TestPrep.command_runRhnCheckInForeground(system, true);
	    rh.sleepForSeconds(5);
	    task_TestPrep.command_tailLog(system, "/var/log/up2date");
	    rh.waitForStatus("This action's status is: Completed.", "Package Install scheduled*", true, system, 5);
	}

	
	public void removePackage(String system, String pkg){

		
		page_SatelliteSystems.open();
        task_Search.goToSystem(system);
        page_RhnCommon.clickLink_GeneralLink(system);
	     page_SDC.clickLink_Software();
	     page_SDC.clickLink_Packages();
	     page_SDC.clickLink_ListRemove();
	     page_SDC.setTxt_SDCFilterBy(pkg);
	     page_SDC.clickButton_FilterGo();
	    if(rh.isTextPresent(pkg)){
		    //select_File_Checkbox(pkg);
	    	page_RhnCommon.check_FirstItemInList();
		     page_SDC.clickButton_RemovePackages();
		     page_SDC.clickButton_Confirm();
		    task_TestPrep.command_runRhnCheckInForeground(system, true);
		  //  tp.command_tailLog(system, "/var/log/up2date");
		    rh.waitForStatus("This action's status is: Completed.", "Package Removal scheduled*", true, system, 5);
	    }
	    else{
	    	log.info(pkg+ " package NOT found");
	    }
	}
	
	

	



}
