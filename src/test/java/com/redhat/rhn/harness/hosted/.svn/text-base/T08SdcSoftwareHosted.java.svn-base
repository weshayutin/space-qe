package com.redhat.rhn.harness.hosted;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Hosted.tasks.RhnBase;
import com.redhat.rhn.harness.common.Hosted.tasks.SdcSoftware;

public class T08SdcSoftwareHosted extends SdcSoftware{

	RhnHelper rh = new RhnHelper();
	RhnBase rb = new RhnBase();

	static String firstSnapShot = "firstSnapShot";
	

	public void testPrep01(){
		
		task_TestPrep.unregisterAllProfilesOfSystem(rh.getTestSystem01Name(), true);
	}

	public void testPrep02(){
		
		task_TestPrep.registerSystem(rh.getTestSystem01Name(),IRhnBase.RHN_REG_CMD,true, true);

	}

	public void testPrep03(){
		
		prepUp2dateFile(IRhnBase.SYSTEM_HOSTNAME01);
	}

	public void testPrep04(){
		
		prepRhnYumPlugin(IRhnBase.SYSTEM_HOSTNAME01);
	}

	public void testPrep05(){
		
		rhnPluginFix(IRhnBase.SYSTEM_HOSTNAME01);
	}

	public void testTakeSnapShot(){
		
		takeSnapShot(IRhnBase.SYSTEM_HOSTNAME01, true);
	}

	public void testInstallPackage(){
		
		installPackage(IRhnBase.SYSTEM_HOSTNAME01);
	}

	public void testListRemovePackages(){
		
		//this will install the package "screen"
		listRemovePackages(IRhnBase.SYSTEM_HOSTNAME01, true);
	}

	public void testRollBackPackages01(){
		
		rollBackPackages(IRhnBase.SYSTEM_HOSTNAME01, true);
	}

	public void testRollBackPackages02(){
		
		rollBackPackages(IRhnBase.SYSTEM_HOSTNAME01, true);
	}
}
