package com.redhat.rhn.harness.sandbox;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;

public class PopUpTests extends com.redhat.rhn.harness.common.Sat42.tasks.Sandbox{


	RhnHelper rh = new RhnHelper();
	
	

	/*public void testUnregisterAllSystemFromWebQA(){
		
		westest01();
	}*/
	
	public void testStartVirtGuest(){
		
		
		log.info("NAME="+(task_KickStart.getVirtGuest_HostName("rlx-2-12.rhndev.redhat.com", "ks-rhel-i386-server-5-896263_rlx-2-12.rhndev.redhat.com", true)));
		task_KickStart.virtGuest_Action("rlx-2-12.rhndev.redhat.com", "ks-rhel-i386-server-5-896263_rlx-2-12.rhndev.redhat.com", true, true, IRhnBase.VIRT_GUEST_START);
	}

	public void testSubmitCommands(){
		
		task_TestPrep.command_test("hostname", " ", IRhnBase.SYSTEM_HOSTNAME01, false);
		
	}
	
	
}
