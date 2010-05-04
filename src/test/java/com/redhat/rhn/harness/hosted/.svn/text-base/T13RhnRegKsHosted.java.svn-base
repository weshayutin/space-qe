package com.redhat.rhn.harness.hosted;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Hosted.tasks.RhnBase;
import com.redhat.rhn.harness.common.Hosted.tasks.RhnRegister;

public class T13RhnRegKsHosted extends RhnRegister{

	RhnHelper rh = new RhnHelper();
	RhnBase rb = new RhnBase();
	private static final String USER_WAR0086 = "aowensgit";
	private static final String PASS_WAR0086 = "redhat";

	private String SYSTEM_RHEL4 = IRhnBase.SYSTEM_HOSTNAME01;
	private String SYSTEM_RHEL5 = IRhnBase.SYSTEM_HOSTNAME02;
	private static String RhnReg_WAR0086 = "rhnreg_ks --email=autosat@redhat.com --username="+ USER_WAR0086 + " --password="+ PASS_WAR0086 + " --force";
	private static String SERVER_REG_SEC = "https://xmlrpc."+HarnessConfiguration.RHN_HOST + "/XMLRPC";

	public void testRhel4Prep01(){
		
		// unregister all profiles for SYSTEM_RHEL4
		task_TestPrep.unregisterAllProfilesOfSystem(SYSTEM_RHEL4, USER_WAR0086, PASS_WAR0086);
	}
	
	public void testRhel4prepRemoveServerURL02(){
		
		// modify /etc/sysconfig/rhn/up2date to point to rhn.host
		task_TestPrep.modifyServerParent(SYSTEM_RHEL4, true, "serverURL=" + SERVER_REG_SEC);
	}

	public void testRhel4RegisterSystem03(){
		
		// register WAR0086 user to the system
		task_TestPrep.registerSystemCustomUserName(SYSTEM_RHEL4, RhnReg_WAR0086, USER_WAR0086, PASS_WAR0086, true);
	}

	public void testRhel5Prep01(){
		
		// unregister all profiles for SYSTEM_RHEL5
		task_TestPrep.unregisterAllProfilesOfSystem(SYSTEM_RHEL5, USER_WAR0086, PASS_WAR0086);
	}
	
	public void testRhel5prepRemoveServerURL02(){
		
		// modify /etc/sysconfig/rhn/up2date to point to rhn.host
		task_TestPrep.modifyServerParent(SYSTEM_RHEL5, true, "serverURL="+SERVER_REG_SEC);
	}

	public void testRhel5RegisterSystem03(){
		
		// register WAR0086 user to the system
		task_TestPrep.registerSystemCustomUserName(SYSTEM_RHEL5, RhnReg_WAR0086, USER_WAR0086, PASS_WAR0086, true);
	}

}