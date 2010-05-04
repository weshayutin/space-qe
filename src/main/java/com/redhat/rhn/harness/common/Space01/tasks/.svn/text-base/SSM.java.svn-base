package com.redhat.rhn.harness.common.Space01.tasks;


public class SSM extends com.redhat.rhn.harness.common.Sat42.tasks.SSM {

	public void SSM_InstallPackageWithArch(String Channel,String Package,String arch){
		page_SSM.open();
		page_SSM.clickLink_TopBar_Packages();
		page_SSM.clickLink_Install();
		page_RhnCommon.clickLink_GeneralLink(Channel);
		page_RhnCommon.setTxt_FilterBy(Package);
		page_RhnCommon.clickButton_Filter_Go();
		//rh.selectItemInRow(XPATH_PKG_INSTALL, arch);
		page_RhnCommon.check_SelectAll_CheckBox();
		page_SSM.clickButton_InstallSelectedPackages();
		page_SSM.clickButton_Confirm();
		assertTrue(rh.isTextPresent("scheduled"));
	}
	
}
