package com.redhat.rhn.harness.hosted;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Hosted.tasks.RhnBase;

public class T01YourRhn01Hosted extends com.redhat.rhn.harness.common.Hosted.tasks.YourRhn{

	RhnHelper rh = new RhnHelper();
	RhnBase rb = new RhnBase();
	


	public void test01RhnAcceptance01(){
		
		RhnAcceptance();
		
	}

	public void test02YourAccount01(){
		
		updateYourAccount_Position("head banger");
		
	}

	public void test03YourAccount02(){
		
		updateYourAccountHosted("qa", "Joe", "Shmo");
		
	}

/*	public void testYourAccount03(){
		
		updateEmailAddress("wnstb2@yahoo.com");
		
	}*/

	public void test04RegisterSystem(){
		
		task_TestPrep.registerSystem(IRhnBase.SYSTEM_HOSTNAME01,IRhnBase.RHN_REG_CMD,true, true);

	}

	public void test05Entitlements(){
		
		checkEntitlements(IRhnBase.SYSTEM_HOSTNAME01, false);
		
	}

	public void test06EntitlementsLinks(){
		
		checkChannelEntitlementsLink(false);
		
	}

	public void test07CreateUser(){
		
		rb.OpenAndLogIn();
		createNonAdminUser(false,"autoFirst","autoLast","auto","redhat.com","334 deadwood drive","Raleigh","NC","27615", "919 555 5555");
		
	}
	
	public void test08BadEmail(){
		
		rb.OpenAndLogIn();
		createNonAdminUser(false,"autoFirst","autoLast","auto.",".redhat.com","334 deadwood drive","Raleigh","NC","27615", "919 555 5555");
		
	}

	public void test09LinksForNonAdmins(){
		
		checkLinksForNonAdminUsers(false,"autoFirst","autoLast","auto","334 deadwood drive","Raleigh","NC","27615","919 555 5555");
		
	}

/*	public void testEnableRoleForConfigAdmin(){
		
		giveUserConfigMangAuth();
		
	}
	public void testLinksForConfigAdmin(){
		
		checkLinksForConfigAdminUsers();
		
	}*/





}
