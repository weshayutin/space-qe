package com.redhat.rhn.harness.common.Sat42.tasks;

import java.util.List;

import org.testng.Assert;

import com.redhat.rhn.harness.Sat42.pages.*;
import com.redhat.rhn.harness.baseInterface.ISatelliteSync;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;

public class SatelliteSync extends SeleniumSetup { // implements ISatelliteSync {
	
	public void sync(String exportLocation, List<String> channels) {
		log.info("SatelliteSync::sync() called");
		String cmd = "satellite-sync -m " + exportLocation + " ";
		for (String chan : channels) {
			cmd += "-c " + chan + " ";
		}
		String[] ret = ssh.executeViaSSHWithReturn(task_RhnBase.SERVER_HOSTNAME, cmd);
		assertFalse(ret[0].contains("ERROR"));
		
	}
	
	public void copyExportToSat(String sourceFile, String destPath) {
		scp.sendFile(task_RhnBase.SERVER_HOSTNAME, sourceFile, destPath);
		
		int index = sourceFile.lastIndexOf("/");
		String fname = sourceFile.substring(index+1);
		String[] ret = ssh.executeViaSSHWithReturn(task_RhnBase.SERVER_HOSTNAME, 
					"cd " + destPath + " && tar xvfj " + fname + ";");
		assertFalse(ret[1].contains("Cannot open: No such file or directory"));
	}
}
