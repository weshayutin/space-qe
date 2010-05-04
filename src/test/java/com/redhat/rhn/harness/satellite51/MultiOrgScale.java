package com.redhat.rhn.harness.satellite51;

import java.util.Random;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.redhat.rhn.harness.common.Sat42.tasks.RhnBase;
import com.redhat.rhn.harness.common.Sat42.tasks.SatelliteTools;

public class MultiOrgScale extends SeleniumSetup {


	private int rand;

	protected RhnHelper rh = new RhnHelper();
	
	

	protected String org2 = "autoOrg01";
	protected String org2user = "autoOrgUser02";
	protected String org2email = "automOrgUser01@redhat.com";
	protected String org2paswd = "dog8code";
	protected int TOTAL_SAT_BASE_ENTITLEMENTS = 0;

	protected int shughesZBigTotal_system = 123450;
	protected int shughesZBigTotal_core = 22000;
	protected int number;
	int bvt_total = 20000;
	int non_bvt_total = 100;


/*	public void test00Prep01(){
		
		rb.removeAllSystemProfiles(true);
	}*/
	
	public void test02CreateNewOrg(){
		int n = 1000;
		Random generator = new Random();
		task_RhnBase.OpenAndLogIn();
		
		for(int i=0;i <101;i++){
			rand = generator.nextInt(n);
			int myRand = rand;
			task_RhnBase.createNewOrganization(org2+myRand, org2user+myRand, org2email, org2paswd, false);
			task_SatelliteTools.updateOrgSystemEntitlements(org2+rand, false, IRhnBase.ENTITLEMENT_BASE, "50", false);
			//rb.updateOrgSystemEntitlements(org2+rand, false, IRhnBase.ENTITLEMENT_PROVISIONING, "50", false);
			task_SatelliteTools.updateOrgSoftwareChannelEntitlements(org2+rand, false, IRhnBase.CHANNEL_RHEL_CORE_SERVER, "50", false);
			
			for(int x=1;x<11;x++){
				//task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01, " --profilename="+x, true);
				//task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01, "--activationkey=33-12345 --profilename="+x+ " --force", org2user+rand, "dog8code", true);
				task_TestPrep.registerSystemNOGUI(IRhnBase.SYSTEM_HOSTNAME01, "auto"+x, " --force", org2user+myRand, "dog8code");
			}
		}
	}
	
	/*public void test05GrantSystemEntitlementsBase(){
		
		rb.updateOrgSystemEntitlements(org2, true, IRhnBase.ENTITLEMENT_BASE, "10", true);
		//rh.checkForErrors();
	}*/
}
