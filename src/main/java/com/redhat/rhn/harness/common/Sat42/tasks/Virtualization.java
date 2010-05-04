package com.redhat.rhn.harness.common.Sat42.tasks;

import java.io.IOException;
import java.util.Random;

import org.testng.Assert;

import com.redhat.qe.tools.ExecCommands;
import com.redhat.rhn.harness.Sat42.pages.KickStartPage;
import com.redhat.rhn.harness.Sat42.pages.RhnCommon;
import com.redhat.rhn.harness.Sat42.pages.SDCPage;
import com.redhat.rhn.harness.Sat42.pages.SatelliteSystemsPage;
import com.redhat.rhn.harness.Sat42.pages.SchedulePage;
import com.redhat.rhn.harness.Sat42.pages.SystemProvisionPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.redhat.rhn.harness.common.octokick.OKickStart;

/**
 * Tasks used to kickstart pvhosts, guests, and start,stop guests
 * 
 * @author whayutin
 * 
 */
public class Virtualization extends SeleniumSetup {

	protected RhnHelper rh = new RhnHelper();
	
	

	protected OKickStart ok = new OKickStart();
	
	
	
	
	
	

	public static final int SSH_CHECKDELAYSECS = 30;
	public static final int SSH_CHECKPAUSESECS = 60;
	public static final int SSH_TIMELIMITMINS = 45;
	public static final int KS_TIMELIMITMINS = 45;
	public static final String VIRTGUESTTABLEID = "xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table[2]";
	public static final int GUESTACTIONDELAYMINS = 10;

	public static final int MEMORYMARGINOFERROR = 3;
	public static final int VIRTCPUMARGINOFERROR = 0;

	public String[] getListOfGuests(String system) {
		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_SystemProvisioning.clickLink_SystemName(system);
		page_SatelliteSystems.clickLink_Virtualization();
		String[] list = rh.getLineofCells(VIRTGUESTTABLEID, 2, false);
		return list;
	}

	public void checkGuestSDCChannelEntitlements(String guest, String system) {
		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_SystemProvisioning.clickLink_SystemName(system);
		page_SatelliteSystems.clickLink_Virtualization();
		page_RhnCommon.setTxt_FilterBy(guest);
		page_RhnCommon.clickButton_Filter_Go();
		page_SatelliteSystems.clickLink_VirtGuestSDC();
		page_SDC.clickLink_AlterChannelSubscriptions();
		Assert.assertFalse(sel.isTextPresent("Internal Server Error"));
	}

	public boolean deleteGuests(String system, String[] guests) {
		return this.doActionUponSystems(system, guests, 0);
	}

	public boolean startGuests(String system, String[] guests) {
		return this.doActionUponSystems(system, guests, 1);
	}

	public boolean suspendGuests(String system, String[] guests) {
		return this.doActionUponSystems(system, guests, 2);
	}

	public boolean resumeGuests(String system, String[] guests) {
		return this.doActionUponSystems(system, guests, 3);
	}

	public boolean restartGuests(String system, String[] guests) {
		return this.doActionUponSystems(system, guests, 4);
	}

	public boolean shutdownGuests(String system, String[] guests) {
		return this.doActionUponSystems(system, guests, 5);
	}

	public boolean adjustMemAllotment(String system, String[] guests,
			String allotment) {
		return this.adjustMemoryOrCPUAllotment(system, guests, allotment, true);
	}

	public boolean adjustCPUAllotment(String system, String[] guests,
			String allotment) {
		return this
				.adjustMemoryOrCPUAllotment(system, guests, allotment, false);
	}

	public boolean checkUp2dateLogForTracebacks(String system) {
		ExecCommands exec = new ExecCommands();
		try {
			log.info("Checking up2date log for tracebacks on " + system);
			String u2dlogtxt = exec.submitCommandToLocalWithReturn(true, "ssh",
					"root@" + system + " tail /var/log/up2date");
			if (u2dlogtxt.contains("CommunicationError"))
				return false;
		} catch (Exception e) {
			log.info("Command failure");
			return false;
		}
		return true;
	}

	private boolean adjustMemoryOrCPUAllotment(String system, String[] guests,
			String allotment, boolean isMemory) {
		boolean isSuccessful = true;
		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_SystemProvisioning.clickLink_SystemName(system);
		page_SatelliteSystems.clickLink_Virtualization();
		for (int i = 0; i < guests.length; i++) {
			try {
				page_RhnCommon.setTxt_FilterBy(guests[i]);
				page_RhnCommon.clickButton_Filter_Go();
				page_RhnCommon.check_FirstItemInList();
				if (isMemory) {
					page_SatelliteSystems.select_VirtGuestSettingToModify("Memory");
					page_SatelliteSystems.setTxt_VirtGuestSettingValue(allotment);
				} else {
					page_SatelliteSystems.select_VirtGuestSettingToModify("Virtual CPU");
					page_SatelliteSystems.setTxt_VirtGuestSettingValue(allotment);
				}
				page_SatelliteSystems.clickButton_ApplyGuestChanges();
				Assert.assertFalse(sel.isTextPresent("Internal Server Error"));
				page_SatelliteSystems.clickButton_ApplyGuestActionConfirm();
				page_RhnCommon.setTxt_FilterBy(guests[i]);
				page_RhnCommon.clickButton_Filter_Go();
				page_RhnCommon.check_FirstItemInList();
				page_SatelliteSystems.select_VirtAction("Restart Systems");
				page_SatelliteSystems.clickButton_ApplyGuestAction();
				page_SatelliteSystems.clickButton_ApplyGuestActionConfirm();
			} catch (Exception e) {
				isSuccessful = false;
			}
		}
		// sel.stop(); //hammertime
		Assert.assertTrue(isSuccessful);
		if (allotment == "" || !isSuccessful)
			return isSuccessful;
		task_TestPrep.command_runRhnCheckWithAT(system, true);
		this.sleepForSeconds(GUESTACTIONDELAYMINS * 60);
		for (int i = 0; i < guests.length; i++) {
			try {
				task_RhnBase.OpenAndLogIn();
				page_SatelliteSystems.open();
				page_RhnCommon.setTxt_FilterBy(system);
				page_RhnCommon.clickButton_Filter_Go();
				page_SystemProvisioning.clickLink_SystemName(system);
				page_SatelliteSystems.clickLink_Virtualization();
				int rhnReportedAllotment = Integer.parseInt(page_SatelliteSystems
						.getGuestAllotment(guests[i], isMemory).split(" ")[0]);
				int desiredAllotment = Integer.parseInt(allotment);
				if (isMemory
						&& ((rhnReportedAllotment > (desiredAllotment + MEMORYMARGINOFERROR)) || (rhnReportedAllotment < (desiredAllotment - MEMORYMARGINOFERROR))))
					isSuccessful = false;
				if (!isMemory
						&& ((rhnReportedAllotment > (desiredAllotment + VIRTCPUMARGINOFERROR)) || (rhnReportedAllotment < (desiredAllotment - VIRTCPUMARGINOFERROR))))
					isSuccessful = false;
			} catch (Exception e) {
				isSuccessful = false;
			}
		}
		Assert.assertTrue(isSuccessful);
		return isSuccessful;
	}

	private boolean doActionUponSystems(String system, String[] guests,
			int actionnum) {
		boolean isSuccessful = true;
		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_SystemProvisioning.clickLink_SystemName(system);
		page_SatelliteSystems.clickLink_Virtualization();
		for (int i = 0; i < guests.length; i++) {
			try {
				page_RhnCommon.setTxt_FilterBy(guests[i]);
				page_RhnCommon.clickButton_Filter_Go();
				page_RhnCommon.check_FirstItemInList();
				switch (actionnum) {
				case 0: // delete
					page_SatelliteSystems.select_VirtAction("Delete Systems");
					break;
				case 1: // start
					page_SatelliteSystems.select_VirtAction("Start Systems");
					break;
				case 2: // suspend
					page_SatelliteSystems.select_VirtAction("Suspend Systems");
					break;
				case 3: // resume
					page_SatelliteSystems.select_VirtAction("Resume Systems");
					break;
				case 4: // restart
					page_SatelliteSystems.select_VirtAction("Restart Systems");
					break;
				case 5: // shutdown
					page_SatelliteSystems.select_VirtAction("Shutdown Systems");
					break;
				}
				page_SatelliteSystems.clickButton_ApplyGuestAction();
				Assert.assertFalse(sel.isTextPresent("Internal Server Error"));
				page_SatelliteSystems.clickButton_ApplyGuestActionConfirm();
			} catch (Exception e) {
				isSuccessful = false;
			}
		}
		if (!isSuccessful)
			return isSuccessful;
		task_TestPrep.command_runRhnCheckWithAT(system, true);
		this.sleepForSeconds(GUESTACTIONDELAYMINS * 60);
		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_SystemProvisioning.clickLink_SystemName(system);
		page_SatelliteSystems.clickLink_Virtualization();
		for (int i = 0; i < guests.length; i++) {
			try {
				String status = page_SatelliteSystems.getGuestStatus(guests[i]);
				switch (actionnum) {
				case 0:
					if (status.length() > 1)
						isSuccessful = false;
					break;
				case 1:
					if (!status.contains("Running"))
						isSuccessful = false;
					break;
				case 2:
					if (!status.contains("Paused"))
						isSuccessful = false;
					break;
				case 3:
					if (!status.contains("Running"))
						isSuccessful = false;
					break;
				case 4:
					if (!status.contains("Running"))
						isSuccessful = false;
					break;
				case 5:
					if (!status.contains("Stopped"))
						isSuccessful = false;
					break;
				}
				if (sel.isTextPresent("Internal Server Error"))
					isSuccessful = false;
			} catch (Exception e) {
				isSuccessful = false;
			}
		}
		return isSuccessful;
	}
	
	public String getVirtGuestStatusFromRHN(String system, String kslabel,  boolean openAndLogin){
		String status= "";
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_SatelliteSystems.open();
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_SystemProvisioning.clickLink_SystemName(system);
		page_SatelliteSystems.clickLink_Virtualization();
		if(rh.isTextNotPresent("No virtual systems.")){
			page_RhnCommon.setTxt_FilterBy(kslabel);
			page_RhnCommon.clickButton_Filter_Go();
			if(rh.isTextPresent(kslabel,false)){
				status = page_SatelliteSystems.getGuestStatus();	
			}
		}
		else{
			status = "virt guest not found";
		}
	    return status;
	}

	public boolean kickstartHost(String system, String kickstartLabel,
			String autoKS) {
		int ks_ticks = 0;
		int ssh_ticks = 0;
		if (ok.KickStartSystem(system, kickstartLabel, true)) {
			while (!ok.kickstart_Status_fromSDC(system, autoKS, kickstartLabel)) {
				this.sleepForSeconds(60);
				if (ks_ticks++ == KS_TIMELIMITMINS)
					return false;
			}
			while (!ok.checkIfSSHOpen(system, true)) {
				this.sleepForSeconds(60);
				if (ssh_ticks++ == SSH_TIMELIMITMINS)
					return false;
			}
			return true;
		}
		return false;
	}

	public String createParavirtProfile(String system, String channel,
			String tree, String sshKey, String rootpwd, boolean isGuest) {
		int n = 1000000;
		Random generator = new Random();
		int rand = generator.nextInt(n);
		String kickstartLabel = tree + "-pv-" + Integer.toString(rand);
		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		task_TestPrep.enableProvisioning(system, false);
		page_KickStart.open();
		page_KickStart.clickLink_ViewListKickstartProfiles();
		page_KickStart.openLink_CreateNewKickstartProfile();
		page_KickStart.setTxt_KickstartProfileLabel(kickstartLabel);
		page_KickStart.select_BaseChannel(channel, false);
		page_KickStart.select_KickstartableTree(tree);
		if (isGuest)
			page_KickStart.select_ParavirtualizedGuestOption();
		else
			page_KickStart.select_ParavirtualizedHostOption();
		page_KickStart.clickButton_Next();
		page_KickStart.clickButton_Next();
		page_KickStart.setTxt_RootPassword(rootpwd);
		page_KickStart.setTxt_RootPasswordConfirm(rootpwd);
		page_KickStart.clickButton_Finish();
		Assert.assertFalse(rh.isTextPresent("missing packages"));
		page_KickStart.clickLink_Scripts();
		page_KickStart.clickLink_AddNewKickstartScript();
		page_KickStart.setTxt_ScriptingLanguage("/bin/bash");
		page_KickStart
				.setTxt_ScriptingContents("(cd /tmp; wget -O fix-promise.sh http://ks.rhndev.redhat.com/dmraid/fix-promise.sh; bash fix-promise.sh) >> /tmp/autokick.log");
		page_KickStart.select_ScriptExecutionTime("Post Script");
		page_KickStart.clickButton_UpdateKickstart();
		Assert.assertTrue(rh
				.isTextPresent("Kickstart Script successfully updated."));
		page_KickStart.clickLink_Scripts();
		page_KickStart.clickLink_AddNewKickstartScript();
		page_KickStart.setTxt_ScriptingLanguage("/bin/bash");
		page_KickStart.setTxt_ScriptingContents("(mkdir /root/.ssh; echo \"ssh-dss "
				+ sshKey
				+ "\" >> /root/.ssh/authorized_keys) >> /tmp/autokick.log");
		page_KickStart.select_ScriptExecutionTime("Post Script");
		page_KickStart.clickButton_UpdateKickstart();
		Assert.assertTrue(rh
				.isTextPresent("Kickstart Script successfully updated."));
		page_RhnCommon.clickSignOut();
		return kickstartLabel;
	}

	

	private class RHNChecker implements Runnable {
		private String system;

		RHNChecker(String system) {
			this.system = system;
		}

		public void run() {
			ExecCommands exec = new ExecCommands();
			try {
				log.info("<li>Running rhn_check on " + system);
				exec.submitCommandToLocalWithReturn(true, "ssh", "root@"
						+ system + " rhn_check -vvv ");
			} catch (IOException ioe) {
				RuntimeException rte = new RuntimeException();
				rte.initCause(ioe);
				throw rte;
			}
		}
	}

	public void sleepForSeconds(int seconds) {
		rh.sleepForSeconds(seconds);
	}

	/**
	 * Using magic and the power of kludge(tm) creates a fully- or
	 * para-virtualized host on the specified system. Performs these tasks via
	 * everyone's favorite tool, virt-install, which, after a lot of tinkering,
	 * works well for our purposes.
	 * 
	 * @param systemHostname
	 *            hostname of the dom0
	 * @param guestName
	 *            base name you want to give to the guest created
	 * @param RamMegabytes
	 *            amount of RAM as Megabytes
	 * @param virtCPUs
	 *            number of virtual CPUs to allot to guest
	 * @param diskSize
	 *            size of virtual disk (in Gigabytes) to allot to guest
	 * @param kickstartLabel
	 *            label of kickstart profile you're using if using RHN
	 * @param treeURL
	 *            URL of RHEL/Fedora tree
	 * @param bmetalKSURL
	 *            URL of ks.cfg file or bare metal KS
	 * @param onVirginHost
	 *            pass true if system not yet enabled as a virt platform
	 * @param isParaVirt
	 *            pass true if you want the guest to be paravirtualized
	 * @return guestName+random number to differentiate machines between like
	 *         calls
	 */
	public String createVirtGuestOnSystemWithoutRHN(String systemHostname,
			String guestName, String RamMegabytes, String virtCPUs,
			String diskSize, String kickstartLabel, String treeURL,
			String bmetalKSURL, boolean onVirginHost, boolean isParaVirt) {
		task_RhnBase.OpenAndLogIn();
		int n = 1000000;
		Random generator = new Random();
		int rand = generator.nextInt(n);
		String myGuestName = guestName + rand;
		// enable Virtualization
		task_TestPrep.enableVirtualizationPlatform(systemHostname, true);
		if (onVirginHost)
			this.setupHost(systemHostname);
		// Build the munge-tacular virt-install command
		String virtInstallCommand = "virt-install --noautoconsole --nographics";
		if (isParaVirt)
			virtInstallCommand += " -p";
		virtInstallCommand += " -n " + myGuestName;
		virtInstallCommand += " -r " + RamMegabytes;
		virtInstallCommand += " --vcpus=" + virtCPUs;
		virtInstallCommand += " -l '" + treeURL + "'";
		virtInstallCommand += " -f /var/lib/xen/images/" + myGuestName;
		virtInstallCommand += " -s " + diskSize;
		virtInstallCommand += " -x 'ks=" + bmetalKSURL + "'";
		ExecCommands exec = new ExecCommands();
		try {
			exec.submitCommandToLocalWithReturn(true, "ssh", "root@"
					+ systemHostname + " " + virtInstallCommand);
		} catch (IOException ioe) {
			log.info("command failed");
		}
		try {
			while (true) {
				task_RhnBase.OpenAndLogIn();
				page_SatelliteSystems.open();
				page_RhnCommon.setTxt_FilterBy(systemHostname);
				page_RhnCommon.clickButton_Filter_Go();
				page_SystemProvisioning.clickLink_SystemName(systemHostname);
				page_SatelliteSystems.clickLink_Virtualization();
				if (rh.isTextPresent(myGuestName)) {
					Assert.assertFalse(page_SatelliteSystems.getGuestStatus(myGuestName)
							.contains("Stopped"));
					return myGuestName;
				}
				Thread.sleep(1000 * SSH_CHECKPAUSESECS);
			}
		} catch (Exception e) {
			RuntimeException rte = new RuntimeException();
			rte.initCause(e);
			throw rte;

		}
	}

	private void setupHost(String systemHostname) {
		ExecCommands exec = new ExecCommands();
		try {
			exec.submitCommandToLocalWithReturn(true, "ssh", "root@"
					+ systemHostname + " rhn_check -vvv ");
			exec.submitCommandToLocalWithReturn(true, "ssh", "root@"
					+ systemHostname + " /usr/bin/yum -y install kernel-xen ");
			exec.submitCommandToLocalWithReturn(true, "ssh", "root@"
					+ systemHostname + " init 6 ");
		} catch (IOException ioe) {
			RuntimeException rte = new RuntimeException();
			rte.initCause(ioe);
			throw rte;

		}

		try {
			Thread.sleep(SSH_CHECKPAUSESECS * 1000);
		} catch (Exception e) {
		}

		while (!ok.checkIfSSHOpen(systemHostname, true))
			this.sleepForSeconds(SSH_CHECKDELAYSECS);
	}

	public String createVirtGuestOnSystem(String systemHostname,
			String guestName, String RamMegabytes, String virtCPUs,
			String RHNProxy, String kickstartLabel, boolean onVirginHost) {
		task_RhnBase.OpenAndLogIn();
		int n = 1000000;
		Random generator = new Random();
		int rand = generator.nextInt(n);
		String myGuestName = guestName + rand;

		// enable Virtualization
		task_TestPrep.enableVirtualizationPlatform(systemHostname, true);
		if (onVirginHost)
			this.setupHost(systemHostname);
		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		page_RhnCommon.setTxt_FilterBy(systemHostname);
		page_RhnCommon.clickButton_Filter_Go();
		page_SystemProvisioning.clickLink_SystemName(systemHostname);
		page_SatelliteSystems.clickLink_Virtualization();
		page_SatelliteSystems.clickLink_VirtualizationProvisioning();
		page_SatelliteSystems.setTxt_VirtGuestName(myGuestName);
		page_SatelliteSystems.setTxt_VirtMemoryAllocation(RamMegabytes);
		page_SatelliteSystems.setTxt_VirtVirtualCPUs(virtCPUs);
		if (RHNProxy.length() > 0)
			page_SDC.virtSelectRHNProxy(RHNProxy);
		page_RhnCommon.setTxt_FilterBy(kickstartLabel);
		page_RhnCommon.clickButton_Filter_Go();
		page_RhnCommon.check_FirstItemInList();
		page_SatelliteSystems.clickButton_VirtScheduleKickstartAndFinish();
		Assert.assertTrue(rh.isTextPresent("Initiate Kickstart"));
		log.info("<li>Kickstarting Xen guest; this is a long process.");
		Thread checkThread = new Thread(new RHNChecker(systemHostname));
		checkThread.start();
		while (checkThread.isAlive()) {
			task_RhnBase.OpenAndLogIn();
			page_SatelliteSystems.open();
			page_RhnCommon.setTxt_FilterBy(systemHostname);
			page_RhnCommon.clickButton_Filter_Go();
			page_SystemProvisioning.clickLink_SystemName(systemHostname);
			page_SDC.clickLink_Provisioning();
			page_SDC.clickLink_SessionStatus();
			Assert.assertFalse(sel.isTextPresent("hung"));
			this.sleepForSeconds(60);
		}
		task_RhnBase.OpenAndLogIn();
		page_SatelliteSystems.open();
		page_RhnCommon.setTxt_FilterBy(systemHostname);
		page_RhnCommon.clickButton_Filter_Go();
		page_SystemProvisioning.clickLink_SystemName(systemHostname);
		page_SDC.clickLink_Provisioning();
		page_SDC.clickLink_SessionStatus();
		Assert.assertTrue(sel.isTextPresent("Kickstart complete"));
		return myGuestName;

	}
	
	public void removeAllVirtGuestsFromSystem(String system, boolean openAndLogin){
		String status= null;
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_SatelliteSystems.open();
		page_RhnCommon.setTxt_FilterBy(system);
		page_RhnCommon.clickButton_Filter_Go();
		page_SystemProvisioning.clickLink_SystemName(system);
		page_SatelliteSystems.clickLink_Virtualization();
		if(rh.isTextNotPresent("No virtual systems.")){
			page_RhnCommon.clickButton_SelectAll();
			page_SDC.select_Virt_GuestAction_Delete();
			page_SDC.clickButton_Virt_ApplyAction();
			page_SDC.clickButton_Confirm();
			assertTrue(rh.isTextPresent("No virtual systems."));
		}
		 
	}

}
