package com.redhat.rhn.harness.common.Sat42.tasks;

import org.testng.Assert;

import com.redhat.rhn.harness.Sat42.pages.RhnCommon;
import com.redhat.rhn.harness.Sat42.pages.SatelliteSystemsPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

/**
 * Tasks used to run Advanced System, Package, and Errata Search
 * 
 * @author whayutin
 * 
 */
public class SatelliteAdvancedSearch01 extends SeleniumSetup {
	
	RhnHelper rh = new RhnHelper();
	
	
	

	protected static final String system01 = null;

	protected String getTestSystem01Name() {
		return HarnessConfiguration.RHN_SYSTEM01;
	}

	protected String getTestSystem02Name() {
		return HarnessConfiguration.RHN_SYSTEM02;
	}

	protected String getFakeTestSystem01Name() {
		return HarnessConfiguration.RHN_FAKE01;
	}

	protected String getFakeTestSystem02Name() {
		return HarnessConfiguration.RHN_FAKE02;
	}

	/*
	 * protected void advancedSearchSystems001(String system){ String id = "";
	 * rc.OpenAndLogIn(); rc.clickSideMenuSystems();
	 * 
	 * sp.clickLink_AdvancedSearch(); sp.setText_SearchFor(system);
	 * sp.clickButton_Search(); Assert.assertTrue(rh.isTextPresent(system));
	 * if(rh.isElementPresent("name=items_selected")){ log.info("More than one
	 * result returned"); rh.clickLink("link="+system, true); } //
	 * sp.clickLink_Details(); log.info("ID ="+sp.getHostSystem_ID()); id =
	 * sp.getHostSystem_ID(); sp.clickLink_AdvancedSearch();
	 * sp.select_FieldToSearch_ID(); sp.setText_SearchFor(id);
	 * sp.clickButton_Search(); Assert.assertTrue(rh.isTextPresent(system));
	 * 
	 * System.out.println("break"); }
	 */
	protected void advancedSearchSystems002(String system) {
		String id = "";
		task_RhnBase.OpenAndLogIn();
		page_RhnCommon.clickSideMenuSystems();

		page_SatelliteSystems.clickLink_AdvancedSearch();
		page_SatelliteSystems.select_FieldToSearch_ID();
		page_SatelliteSystems.setText_SearchFor("10");
		page_SatelliteSystems.clickButton_Search();
		Assert.assertTrue(rh.isTextPresent(system));
		if (rh.isElementPresent("name=items_selected", true)) {
			log.info("More than one result returned");
			rh.clickLink("link=" + system, true);
		}

		log.info("ID =" + page_SatelliteSystems.getHostSystem_ID());
		id = page_SatelliteSystems.getHostSystem_ID();
		System.out.println("break");

	}

	public String getSystemID(String system, boolean hosted) {
		String id = null;
		page_SatelliteSystems.open();
		page_RhnCommon.clickSideMenuSystems();
		page_SatelliteSystems.clickLink_AdvancedSearch();
		page_SatelliteSystems.setText_SearchFor(system);
		page_SatelliteSystems.clickButton_Search();
		Assert.assertTrue(rh.isTextPresent(system));
		rh.clickLink("link=" + system, true);
		if (hosted)
			id = page_SatelliteSystems.getHostSystem_ID();
		else
			id = page_SatelliteSystems.getSatSystem_ID();
		page_SatelliteSystems.open();
		log.info("system id = " + id);
		return id;
	}

	protected void advancedSearchSystems003(String system) {
		String[] details = new String[4];

		task_RhnBase.OpenAndLogIn();
		page_RhnCommon.clickSideMenuSystems();

		page_SatelliteSystems.clickLink_AdvancedSearch();
		page_SatelliteSystems.setText_SearchFor(system);
		page_SatelliteSystems.clickButton_Search();
		Assert.assertTrue(rh.isTextPresent(system));
		// sp.clickLink_Details();
		details[0] = page_SatelliteSystems.getHostSystem_ID();
		details[1] = page_SatelliteSystems.getSystem_Hostname();
		details[2] = page_SatelliteSystems.getSystem_IpAddress();
		details[3] = page_SatelliteSystems.getSystem_Kernel();

		log.info("System ID =" + details[0]);

		page_SatelliteSystems.clickLink_AdvancedSearch();
		page_SatelliteSystems.setText_SearchFor(details[0]);
		page_SatelliteSystems.select_FieldToSearch_ID();
		page_SatelliteSystems.clickButton_Search();
		confirmSearchResult(details[0], system);

		page_SatelliteSystems.clickLink_AdvancedSearch();
		page_SatelliteSystems.setText_SearchFor(details[1]);
		page_SatelliteSystems.select_FieldToSearch_NetworkInfo_Hostname();
		page_SatelliteSystems.clickButton_Search();
		confirmSearchResult(details[1], system);

		page_SatelliteSystems.clickLink_AdvancedSearch();
		page_SatelliteSystems.setText_SearchFor(details[2]);
		page_SatelliteSystems.select_FieldToSearch_NetworkInfo_IP();
		page_SatelliteSystems.clickButton_Search();
		confirmSearchResult(details[2], system);

		page_SatelliteSystems.clickLink_AdvancedSearch();
		page_SatelliteSystems.setText_SearchFor(details[3]);
		page_SatelliteSystems.select_FieldToSearch_HD_Description();
		page_SatelliteSystems.clickButton_Search();
		confirmSearchResult(details[3], system);

		System.out.println("break");
	}

	protected void advancedSearchSystems004(String system, String rhn_package) {
		// search for a package
		task_RhnBase.OpenAndLogIn();
		page_RhnCommon.clickSideMenuSystems();

		page_SatelliteSystems.clickLink_AdvancedSearch();
		page_SatelliteSystems.select_FieldToSearch_Packages_Installed();
		page_SatelliteSystems.setText_SearchFor(rhn_package);
		page_SatelliteSystems.clickButton_Search();
		Assert.assertTrue(rh.isTextPresent(system));
		if (rh.isElementPresent("name=items_selected", true)) {
			log.info("More than one result returned");
			rh.clickLink("link=" + system, true);
		}
	}

	protected void advancedSearchSystems005(String system) {
		// Invert a search for a package
		task_RhnBase.OpenAndLogIn();
		page_RhnCommon.clickSideMenuSystems();

		page_SatelliteSystems.clickLink_AdvancedSearch();
		page_SatelliteSystems.select_FieldToSearch_Packages_Installed();
		page_SatelliteSystems.setText_SearchFor("bash");
		// sp.check_InvertSearchResults();
		page_SatelliteSystems.clickButton_Search();
		Assert.assertTrue(rh.isTextPresent("No results found"));
	}

	public void confirmSearchResult(String details, String system) {
		boolean pass = false;
		if ((details == "unknown") && (rh.isTextPresent("No systems.")))
			log.info("details =" + details);
		pass = false;

		if (rh.isTextPresent(system))
			log.info("details =" + details);
		pass = true;

		if (!pass)
			throw new AssertionError("the search for " + details
					+ " failed to return the correct response");
	}

	protected void printSystemDetailsSat(String system) {
		task_RhnBase.OpenAndLogIn();
		page_RhnCommon.clickSideMenuSystems();

		page_SatelliteSystems.clickLink_AdvancedSearch();
		page_SatelliteSystems.setText_SearchFor(system);
		page_SatelliteSystems.clickButton_Search();
		Assert.assertTrue(rh.isTextPresent(system));
		sel.click("link=" + system);
		// sp.clickLink_Details();
		log.info("ID =" + page_SatelliteSystems.getSatSystem_ID());
		log.info("Hostname =" + page_SatelliteSystems.getSatSystem_Hostname());
		log.info("IP =" + page_SatelliteSystems.getSatSystem_IpAddress());
		log.info("Kernel =" + page_SatelliteSystems.getSatSystem_Kernel());
		log.info("Registered =" + page_SatelliteSystems.getSatSystem_Registered());
		log.info("Checked In =" + page_SatelliteSystems.getSatSystem_CheckedIn());

	}

}
