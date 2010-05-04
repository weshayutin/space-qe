package com.redhat.rhn.harness.hosted;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Hosted.tasks.RhnBase;
import com.redhat.rhn.harness.common.Hosted.tasks.RhnRegister;

public class T15RhnYumHosted extends RhnRegister{

	RhnHelper rh = new RhnHelper();
	RhnBase rb = new RhnBase();
	private static final String yumUpdateArg=" update -y ";
	private static final String yumRemoveArg=" remove -y ";
	private static final String yumInstallArg=" install -y ";
	private static final String yumRepoList5_2Arg=" repolist ";
	private static final String rhnProfileSyncCmd="rhn-profile-sync";
	private static final String yumRepoList5_1Cmd="echo";
	private static final String yumRepoList5_1Arg="'repo list' | yum shell";
	private static final String rpmPack="logwatch";
	
	//private String SYSTEM_RHEL4 = IRhnBase.SYSTEM_HOSTNAME_RHEL4;
	private String SYSTEM_RHEL5 = IRhnBase.SYSTEM_HOSTNAME02;
	private static String SERVER_REG_SEC = "https://xmlrpc."+HarnessConfiguration.RHN_HOST + "/XMLRPC";

    /* modify serverURL in the /etc/sysconfig/rhn/up2date file
	   to <rhn.host> that was specified in the localhost-settings.properties */
	public void testRhel5PrepRemoveServerURL01(){
		
 		rb.modifyServerParent(SYSTEM_RHEL5, true, "serverURL=" + SERVER_REG_SEC);
	}

	//test "yum update" command
	public void testRhel5YumUpdate02(){
		
		rb.command_yum(yumUpdateArg, SYSTEM_RHEL5, true);
	}
	
	//test "yum remove" command
	public void testRhel5YumRemove03(){
		
		boolean result;
		// check to see if the rpmPack was installed
		result = rb.runRPM_Query_Command(SYSTEM_RHEL5, rpmPack);
		if (result){
		// remove rpmPack
		  rb.command_yum(yumRemoveArg + rpmPack, SYSTEM_RHEL5, true);
		}
	}

	//test "yum install" command
	public void testRhel5YumInstall04(){
		
		rb.command_yum(yumInstallArg + rpmPack, SYSTEM_RHEL5, true);
	}
	
	//test "yum repolist" command
	public void testRhel5YumRepolist(){
		
		rb.command_generic(yumRepoList5_1Cmd, yumRepoList5_1Arg, SYSTEM_RHEL5, true); // if the system is running RHEL 5.1 or lower
		//rb.command_yum(yumRepolist5_2Arg, SYSTEM_RHEL5, true);  // if the system is running RHEL 5.2 or above
	}
	
	//This test belongs in this file because even though it doesn't call yum 
	// directly it is testing yum functionality.
	//test rhn-profile-sync
	public void testRhnProfileSync(){
		
		rb.command_generic(rhnProfileSyncCmd, "", SYSTEM_RHEL5, true);
	}
		

}