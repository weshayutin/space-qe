package com.redhat.rhn.harness.hosted;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Hosted.tasks.RhnBase;
import com.redhat.rhn.harness.common.Hosted.tasks.RhnRegister;

public class T14RhnUp2dateHosted extends RhnRegister{

	RhnHelper rh = new RhnHelper();
	RhnBase rb = new RhnBase();
	private static final String cmdProfile="-p ";
	private static final String cmdList="--list ";
	private static final String cmdShowall="--showall ";
	private static final String cmdInstall="--install logwatch";
	private static final String rpmPack="logwatch";
	private static final String rpmRemove="rpm -e logwatch";
	
	private String SYSTEM_RHEL4 = IRhnBase.SYSTEM_HOSTNAME01;
	//private String SYSTEM_RHEL5 = IRhnBase.SYSTEM_HOSTNAME_RHEL5;
	private static String SERVER_REG_SEC = "https://xmlrpc."+HarnessConfiguration.RHN_HOST + "/XMLRPC";

    /* modify serverURL in the /etc/sysconfig/rhn/up2date file
	   to <rhn.host> that was specified in the localhost-settings.properties */
	public void testRhel4PrepRemoveServerURL01(){
		
 		rb.modifyServerParent(SYSTEM_RHEL4, true, "serverURL=" + SERVER_REG_SEC);
	}

	//test up2date -p command
	public void testRhel4Up2dateProfile02(){
		
		task_TestPrep.command_up2date(cmdProfile, SYSTEM_RHEL4, true);
	}
	
	//test up2date --list command
	public void testRhel4Up2dateList03(){
		
		task_TestPrep.command_up2date(cmdList, SYSTEM_RHEL4, true);
	}

	//test up2date --showall command
	public void testRhel4Up2dateShowall04(){
		
		task_TestPrep.command_up2date(cmdShowall, SYSTEM_RHEL4, true);
	}

	//test up2date --install command
	public void testRhel4Up2dateInstall05(){
		
		boolean result;
		// check to see if the rpmPack was installed
		result = rb.runRPM_Query_Command(SYSTEM_RHEL4, rpmPack);
		if (result){
			// remove rpmPacks
			task_TestPrep.command_rpmCommandWithReturn(SYSTEM_RHEL4, rpmRemove);
			// up2date --install rpmPack
			task_TestPrep.command_up2date(cmdInstall, SYSTEM_RHEL4, true);
		}
		else{
			// up2date --install rpmPack
			task_TestPrep.command_up2date(cmdInstall, SYSTEM_RHEL4, true);			
		}
	}
}