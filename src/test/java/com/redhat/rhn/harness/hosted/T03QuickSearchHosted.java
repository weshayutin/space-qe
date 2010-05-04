package com.redhat.rhn.harness.hosted;

import org.testng.Assert;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Hosted.tasks.RhnBase;
import com.redhat.rhn.harness.common.Hosted.tasks.Search;


public class T03QuickSearchHosted extends Search{
	RhnHelper rh = new RhnHelper();
	RhnBase rb = new RhnBase();
	

/*
	public void testPrep01(){
		
		task_TestPrep.unregisterAllProfilesOfSystem(rh.getTestSystem01Name(), true);
	}
	public void testPrep02(){
		
		task_TestPrep.unregisterAllProfilesOfSystem(IRhnBase.TESTPREFIX, true);
	}

	public void testPrep03(){
		
		rb.registerMultipleProfiles(rh.getTestSystem01Name(),IRhnBase.TESTPREFIX, 2,IRhnBase.RHN_REG_CMD, true, true, true);
	}

	public void testErrata001(){
		
		rb.quickSearch(IRhnBase.QUICK_SEARCH_ERRATA, "ssh", true, false);
		rh.searchForResult("openssh", totalInList());
	    rh.searchForResult("Advisory",totalInList());
	    rh.searchForResult("Type", totalInList());
	    //rh.screenShot("Errata", "quickSearchErrata001");

	}

	public void testErrata002(){
		
		rb.quickSearch(IRhnBase.QUICK_SEARCH_ERRATA, "RHBA", true, false);
		rh.searchForResult("update", totalInList());

	}

	public void testPackages001(){
		
		rb.OpenAndLogIn("aowensgit", "redhat");
		rb.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "bash", true, false);
		rh.searchForResult("The GNU Bourne Again shell (bash)", totalInList());
		rh.searchForResult("bash-doc", totalInList());
		rh.searchForResult("bash2", totalInList());

	}

	public void testPackages002(){
		
		rb.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "glibc", true, false);
		rh.searchForResult("compat-glibc", totalInList());
        rh.searchForResult("compat-glibc-headers", totalInList());
        rh.searchForResult("glibc-common", totalInList());

	}

	public void testPackages003(){
		
		rb.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "shell", true, false);
		rh.searchForResult("zsh", totalInList());
        rh.searchForResult("GNU Bourne Again", totalInList());
	}
	
	public void testPackages004(){
		
		log.info("Test with additional space in front of search tearm");
		rb.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, " bash", true, false);
		rh.searchForResult(IRhnBase.ERROR_MSG_SEARCH_FIELD_CHECK, totalInList());
	}

	public void testPackages005(){
		
		rb.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "gtk+", true, true);

	}

	public void testPackages006(){
		
		rb.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "kernel-hugemem-2.6.9-42.0.8.EL", true, true);
	}
	
	public void test11Packages007(){
		
		rb.quickSearch(IRhnBase.QUICK_SEARCH_PACKAGES, "kernel-hugemem-2.6.9-42.0.8.EL.i686", true, true);
	}


	public void testSystems001(){
		
		rb.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS, "microsoft", true, false);
		rh.searchForResult("No results found.", totalInList());
	}

	public void testSystems002(){
		String id=null;
		
		rb.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS, IRhnBase.SYSTEM_HOSTNAME01, true, true);;
		id=getSystemID(IRhnBase.TESTSYSTEM01, true, true);
		int i = Integer.valueOf(id);
		Assert.assertTrue(i>100);

	}

	public void testSystems003(){
		
		rb.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS, IRhnBase.TESTSYSTEM02, true, true);

	}

	public void test15Systems004(){
		
		rb.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS, "$%^&*$%^&*$%^&*$%^&*$%^&*$%^&*$%^&*$%^&*$%^&*", true, false);
		Assert.assertTrue(rh.isTextPresent("Search string is required."));
	}


	public void testSystems005(){
		
		rb.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS, IRhnBase.TESTPREFIX, true, true);
		verifySystemName(IRhnBase.TESTSYSTEM01);
		verifySystemName(IRhnBase.TESTSYSTEM02);
	}

	public void testSystems006(){
		
		log.info("testing w/ an additional space in front of the query string");
		rb.quickSearch(IRhnBase.QUICK_SEARCH_SYSTEMS, IRhnBase.TESTPREFIX, true, true);
		verifySystemName(IRhnBase.TESTSYSTEM01);
		verifySystemName(IRhnBase.TESTSYSTEM02);

	}*/


	//Assert.assertTrue(sel.isTextPresent(""));

	}

