package com.redhat.rhn.harness.common.Sat42.tasks;


import com.redhat.rhn.harness.baseInterface.ISatelliteTools;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;

/**
 * Tasks used mainly for Multi-Org
 * @author whayutin
 *
 */
public class SatelliteTools extends SeleniumSetup { //implements ISatelliteTools{

	protected RhnHelper rh = new RhnHelper();
	
	
	

	public void restartSatellite(){
		page_RhnCommon.clickSatelliteTools();
		page_SatelliteTools.clickLink_SatelliteConfiguration();
		page_SatelliteTools.clickLink_Restart();
		page_SatelliteTools.check_RestartSatellite(true);
		page_SatelliteTools.clickButton_Restart();
		assertTrue(rh.isTextPresent("restarting"));
		log.info("Satellite successfully restarted, waiting 5 minutes...");
		rh.sleepForSeconds(5*60);
		//now, we'll do a quick test to see if the Satellite came back
		page_RhnCommon.clickSatelliteTools();
		page_SatelliteTools.clickLink_SatelliteConfiguration();
		assertTrue(rh.isTextPresent("Configuration"));
	}

	public void createNewOrganization(String orgName, String user,String email, String password, boolean openAndLogin){
		//5.1 method
		throw new SeleniumException("not a 42 method");
	}

	public void goToOrganization(String orgName, boolean openAndLogin){
		//5.1 method
		throw new SeleniumException("not a 42 method");
	}

	public void deleteOrganization(String orgName, boolean openAndLogin){
		//		5.1 method
		throw new SeleniumException("not a 42 method");
	}

	public void updateOrganizationName(String orgName, boolean openAndLogin, String newOrgName){
		//	5.1 method
		throw new SeleniumException("not a 42 method");
	}

	public void updateOrgSystemEntitlements(String orgName, boolean openAndLogin,int entitlement, String count,boolean successful){
		//		5.1 method
		throw new SeleniumException("not a 42 method");
	}

	public void updateOrgSoftwareChannelEntitlements(String orgName, boolean openAndLogin, int channel, String count, boolean successful){
		//		5.1 method
		throw new SeleniumException("not a 42 method");
	}

	public int verifyOrgSystemEntitlementCounts(boolean openAndLogin, int entitlement, int column){
		// 5.1 method
		return 1;
	}
	
	public void deleteAllOrganizations(boolean openAndLogin){
		// 5.1 method spacewalk
		throw new SeleniumException("not a 42 method");
	}
	
	public void modifyOrgTrusts(String trustee, String trustor, boolean trust){
		throw new SeleniumException("not a 42 method");
	}
	
	public String verifyOrgSoftwareEntitlementUsage(boolean openAndLogin, int entitlement){
		throw new SeleniumException("not a 42 method");
	}
	
	public String verifyOrgSystemEntitlementUsage(boolean openAndLogin, int entitlement){
		throw new SeleniumException("not a 42 method");
	}


	public int verifyOrgSoftwareEntitlementCounts(boolean openAndLogin,	int entitlement, int column) {
		throw new SeleniumException("not a 42 method");
	}
	public boolean orgExists(String orgName, boolean openAndLogin){
		throw new SeleniumException("not a 42 method");
	}
}
