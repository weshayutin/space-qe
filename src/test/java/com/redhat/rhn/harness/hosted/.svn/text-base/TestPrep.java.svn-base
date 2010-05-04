package com.redhat.rhn.harness.hosted;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;

public class TestPrep extends com.redhat.rhn.harness.common.Hosted.tasks.TestPrep{
	RhnHelper rh = new RhnHelper();

	/*public void testRemoteExecCommands(){
		
		rh.remoteExecCommands(rh.getTestSystem01Name());
	}*/

	public void testUnregisterSystemFromWebQA(){
		
		unregisterSystem(rh.getTestSystem01Name(), true);

	}

	public void testUnregisterAllSystemFromWebQA(){
		
		unregisterAllProfilesOfSystem(rh.getTestSystem01Name(), true);
	}

	public void testRemoveRHNConfigFiles(){
		
		removeRHNConfigFilesFromSystem(null);

	}

	public void testRegisterSystemWithRHN(String system){
		
		registerSystem(system,IRhnBase.RHN_REG_CMD, true, true);
	}

	public void testRegisterMultipleSystemsWithRHN(){
		
		registerMultipleProfiles(rh.getTestSystem01Name(),"auto", 2,IRhnBase.RHN_REG_CMD, true, true, true);
	}


/*	public void testEnableAndUnScheduleRHNConfigMgt(){
		
		enableAndUnScheduleRHNConfigManag(rh.getTestSystem01Name());

	}*/

	public void testPrepEnableRHNConfigMgt(){
		
		enableRHNConfigManag(rh.getTestSystem01Name(), false, true);

	}


	public void testPrepGetIso(){
		
		command_wget("http://vault.rhndev.redhat.com/satellite-isos/satellite-4.1/rhn-satellite-4.1.5-17-redhat-linux-as-i386-3.iso");

	}


}
