package com.redhat.rhn.harness.regression.satellite531;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.redhat.rhn.harness.Space01.pages.KickStartPage;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;

public class BZ_522526 extends com.redhat.rhn.harness.common.Space01.tasks.KickStart{
	protected RhnHelper rh = new RhnHelper();
	public final String profileName = "rhel5-bz522526-ks";
	public final String ksTree = "ks-rhel-i386-server-5-u3";
	public final String virtType_None = "None";
	public final String customSnippet = "$SNIPPET(\"/spacewalk/1/snippet\")";
	
	@BeforeClass(groups = { "setup" })
	public void test00_preparing(){
		task_RhnBase.OpenAndLogIn();
		this.DeleteKickstartProfile(profileName, false);
		task_RhnBase.SignOut();
	}
	
	@Test(groups = { "bz-522526" })
	public void test01_createSimpleKsProfile(){
		task_RhnBase.OpenAndLogIn();
		assertTrue(this.createNewKickstartProfile(profileName, 
				IRhnBase.RHN_CHANNEL01, ksTree, virtType_None,
				IRhnBase.PASSWORD, IRhnBase.SSH_PUBLIC_KEY));
		task_RhnBase.SignOut();
	}
	
	@Test(dependsOnMethods="test01_createSimpleKsProfile", 
			groups = { "bz-522526" })
	public void test02_addCustomSnippetToPartitioningScript(){
		task_RhnBase.OpenAndLogIn();
		appendTextToPartitionDetails(profileName, customSnippet);
		task_RhnBase.SignOut();
	}
	
}
