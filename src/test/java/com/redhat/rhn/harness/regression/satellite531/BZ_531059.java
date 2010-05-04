package com.redhat.rhn.harness.regression.satellite531;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;

public class BZ_531059 extends com.redhat.rhn.harness.common.Space01.tasks.KickStart{
	protected RhnHelper rh = new RhnHelper();
	protected static String orgName = "AutoOrg, hello! & bye*";
	protected static String orgAdmin = "autoOrgAdmin01";
	protected static String orgEmail = "dev-null@redhat.com";
	protected static String orgPaswd = IRhnBase.PASSWORD;
	public final String profileName = "rhel5-bz531059-ks";
	public final String ksTree = "ks-rhel-i386-server-5-u3";
	public final String virtType_None = "None";
	
	@BeforeClass(groups = { "setup" })
	public void test00_preparing(){
		task_RhnBase.OpenAndLogIn();
		// cleanup
		if(task_SatelliteTools.orgExists(orgName, false)){
			task_RhnBase.OpenAndLogIn(orgAdmin, orgPaswd);
			this.DeleteKickstartProfile(profileName, false);
			task_RhnBase.SignOut();
			task_RhnBase.OpenAndLogIn();
			task_SatelliteTools.deleteOrganization(orgName, false);
		}
		task_RhnBase.SignOut();
	}
	
	@Test(groups = { "bz-531059" })
	public void test00_createOrgWithNonAlphanumericChars(){
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.createNewOrganization( orgName, orgAdmin, orgEmail, orgPaswd, false);
		Assert.assertTrue(rh.isTextPresent("Organization "+orgName+" created successfully"));
		task_SatelliteTools.updateOrgSystemEntitlements(orgName, false, IRhnBase.ENTITLEMENT_BASE, "10", true);
		task_SatelliteTools.updateOrgSystemEntitlements(orgName, false, IRhnBase.ENTITLEMENT_PROVISIONING, "10", true);
		task_SatelliteTools.updateOrgSoftwareChannelEntitlements(orgName, false, IRhnBase.CHANNEL_RHEL_CORE_SERVER, "10", true);
		task_SatelliteTools.updateOrgSoftwareChannelEntitlements(orgName, false, IRhnBase.CHANNEL_RHN_TOOLS, "10", true);
		task_RhnBase.SignOut();
	}
	
	@Test(dependsOnMethods="test00_createOrgWithNonAlphanumericChars", 
			groups = { "bz-531059" })
	public void test01_createSimpleKsProfileForOrg(){
		task_RhnBase.OpenAndLogIn(orgAdmin, orgPaswd);
		assertTrue(this.createNewKickstartProfile(profileName, 
				IRhnBase.RHN_CHANNEL01, ksTree, virtType_None,
				IRhnBase.PASSWORD, IRhnBase.SSH_PUBLIC_KEY));
		task_RhnBase.SignOut();
	}
	
}
