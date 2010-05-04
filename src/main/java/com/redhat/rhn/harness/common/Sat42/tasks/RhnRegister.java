package com.redhat.rhn.harness.common.Sat42.tasks;

import java.io.IOException;

import org.testng.Assert;

import com.redhat.qe.tools.ExecCommands;
import com.redhat.rhn.harness.Sat42.pages.RhnCommon;
import com.redhat.rhn.harness.Sat42.pages.SatelliteSystemsPage;
import com.redhat.rhn.harness.baseInterface.IRhnRegister;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

/**
 * Look in TestPrep for a more complete collection of Registration tools
 * 
 * @author whayutin
 * 
 */
public class RhnRegister extends SeleniumSetup { //implements IRhnRegister {

	
	
	RhnHelper rh = new RhnHelper();

	

	
	

	/*protected void loginWithNewUser(String system, String username,
			String password) {
		int n = 1000;
		Random generator = new Random();
		int rand = generator.nextInt(n);

		rc.LogIn(username, password);
		// Assert.assertTrue(rh.isTextPresent("Terms and Conditions"));
		// Assert.assertTrue(rh.isTextPresent("By proceeding, I acknowledge that
		// I have read the above agreements and consent to their terms."));
		try {
			sp.clickButton_Continue();
			Assert
					.assertTrue(rh
							.isTextPresent("You must accept the terms and conditions before continuing."));
			sp.checkBox_AcceptTerms(true);
			sp.clickButton_Continue();
		} catch (SeleniumException nfe) {
			log.info("LICENSING TERMS WERE NOT PRESENTED");
		}

		up.setTxt_FirstName("autoFirst" + rand);
		up.setTxt_LastName("autoLast" + rand);
		// up.setTxt_Email("auto"+rand+"@.redhat.com");
		up.setTxt_Address("2522 deadwood dr");
		up.setTxt_City("Denver");
		// up.setTxt_State("co");
		up.select_State("Colorado");
		up.setTxt_Zip("802333");
		up.setTxt_Phone("919 555 5555");
		up.clickButton_Finish();
		
		 * Assert.assertTrue(rh.isTextPresent("You are logged in as"));
		 * Assert.assertTrue(rh.isTextPresent("autosat@redhat.com"));
		 

		satp.open();
		satp.clickLink_AllSystems();
		Assert.assertTrue(rh.isTextPresent("No systems"));

	}*/

	public void runRhnRegisterGUI(String server, String user, String password,
			String profilename) {
		String path = " /var/tmp/rhn/src/test/java/com/redhat/rhn/harness/dogtail/";
		ExecCommands exec = new ExecCommands();
		try {
			// exec.submitCommandToLocal("ls", "/tmp");
			exec.submitCommandToLocalWithReturn(true, "chmod +x", path
					+ "RhnRegister02.py");
			// rhn-actions-control --enable-all
			exec.submitCommandToLocalWithReturn(true,
					path + "RhnRegister02.py", " -s" + server + " -u" + user
							+ " -p" + password + " -c" + profilename);
		} catch (IOException ioe) {
			log.info("command failed");
		}
	}

	protected void checkRegisteredSystem(String system, boolean successful) {
		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		page_SatelliteSystems.clickLink_AllSystems();
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		if (successful)
			Assert.assertTrue(rh.isTextPresent(system));
		else
			Assert.assertFalse(rh.isTextPresent(system));
	}

}
