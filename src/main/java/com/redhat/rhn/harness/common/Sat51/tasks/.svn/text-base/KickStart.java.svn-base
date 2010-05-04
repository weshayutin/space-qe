package com.redhat.rhn.harness.common.Sat51.tasks;

import com.redhat.rhn.harness.baseInterface.IKickStart;

public class KickStart extends com.redhat.rhn.harness.common.Sat50.tasks.KickStart implements IKickStart{

	
/*
	String kickstartLabel = "automationKickstart";
	String channel = "Red Hat Enterprise Linux (v. 5 for 32-bit x86)";
	String tree = "ks-rhel-i386-server-5";
	String treeURL = "http://fjs-0-13.rhndev.redhat.com/rhn/kickstart/ks-rhel-i386-server-5";
	String rootpwd = "dog8code";*/

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


}
