package com.redhat.rhn.harness.hosted;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.Hosted.tasks.Pagination;
import com.redhat.rhn.harness.common.Hosted.tasks.RhnBase;

public class T07PaginationHosted extends Pagination {

	RhnHelper rh = new RhnHelper();
	RhnBase rb = new RhnBase();

	public void testPrep01(){
		
		task_TestPrep.unregisterAllProfilesOfSystem(IRhnBase.SYSTEM_HOSTNAME01, true);
	}

	public void testPrep02(){
		
		task_TestPrep.unregisterAllProfilesOfSystem(IRhnBase.TESTPREFIX, true);
	}

	public void testSystemSearchPagination(){
		
		systemSearchPagination(true, IRhnBase.TESTPREFIX);
	}

	public void testPackageSearchPagination(){
		
		packageSearchPagination();
	}



}
