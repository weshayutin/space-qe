package com.redhat.rhn.harness.common.Sat42.tasks;

import org.testng.Assert;

import com.redhat.rhn.harness.Sat42.pages.*;
import com.redhat.rhn.harness.baseInterface.IActivationKeys;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;


/**
 * Tasks used to create activation keys
 * @author whayutin
 *
 */
public class ActivationKeys extends SeleniumSetup { //implements IActivationKeys{


	
	protected RhnHelper rh = new RhnHelper();

	//public final String AK_XPATH = "xpath=/html/body/div/div[5]/table/tbody/tr/td[2]/form/table";
	public final String AK_XPATH = "xpath=/html/body/div/div[2]/div/table/tbody/tr/td[2]/form/table[2]";

	public void createActivationKeyWithOSAD(String name,String key, String usageLimit){
		page_ActivationKeys.open();
		page_ActivationKeys.clickLink_CreateNewKey();
		page_ActivationKeys.setTxt_Description(name);
		page_ActivationKeys.setTxt_Key(key);
		page_ActivationKeys.setTxt_UsageLimit(usageLimit);
		page_ActivationKeys.checkBox_Provisioning(true);
		//akp.checkBox_UniversalDefault(false);
		page_ActivationKeys.clickButton_CreateKey();

		page_ActivationKeys.clickLink_ChildChannels();
		try{
			log.info("Selecting tools channels");
			sel.addSelection("token_child_channels", "Red Hat Network Tools for RHEL AS (v.4 for x86)");
			sel.addSelection("token_child_channels", "Red Hat Network Tools for RHEL Server (v.5 32-bit x86)");
		}
		catch(SeleniumException se){
			log.info("Selecting child channels failed");
		}
		page_ActivationKeys.clickButton_UpdateActivationKey();

		page_ActivationKeys.clickLink_Packages();
		page_ActivationKeys.setTxt_EnterPackageNames("osad");
		page_ActivationKeys.clickButton_UpdateActivationKey();
	}



	protected void createKey01(){
		task_RhnBase.OpenAndLogIn();
		page_ActivationKeys.open();
		page_ActivationKeys.clickLink_CreateNewKey();
		page_ActivationKeys.setTxt_Description("test02");
		page_ActivationKeys.setTxt_Key("123401");
		page_ActivationKeys.setTxt_UsageLimit("5");
		page_ActivationKeys.checkBox_Monitoring(true);
		page_ActivationKeys.checkBox_Provisioning(true);
		page_ActivationKeys.checkBox_Virtualization(true);
		page_ActivationKeys.select_UniversalDefault("Yes, use this token for all normal registrations");
		page_ActivationKeys.clickButton_CreateKey();
		Assert.assertTrue(rh.isTextPresent("has been created."));
		Assert.assertTrue(rh.isTextPresent("test"));

		//A key with that label already exists
		//A key with that label already exists
	}

	public void createActivationKey(String name,String key, String usageLimit,
									boolean monitoring, boolean provisioning,
									boolean virt, boolean virt_Platform,boolean unvDefault){

		page_ActivationKeys.open();
		page_ActivationKeys.clickLink_CreateNewKey();
		page_ActivationKeys.setTxt_Description(name);
		page_ActivationKeys.setTxt_Key(key);
		page_ActivationKeys.setTxt_UsageLimit(usageLimit);
		page_ActivationKeys.checkBox_Monitoring(monitoring);
		page_ActivationKeys.checkBox_Provisioning(provisioning);

		if(unvDefault)
			page_ActivationKeys.select_UniversalDefault("Yes, use this token for all normal registrations");
		else
			page_ActivationKeys.select_UniversalDefault("No");
		page_ActivationKeys.clickButton_CreateKey();
	}
	
	
	
	public void createActivationKeyWithBaseChannel(String name,String key, String basechan, 
												   String usageLimit, boolean monitoring, boolean provisioning,
												   boolean virt, boolean virt_Platform,boolean unvDefault){
		page_ActivationKeys.open();
		page_ActivationKeys.clickLink_CreateNewKey();
		page_ActivationKeys.setTxt_Description(name);
		page_ActivationKeys.setTxt_Key(key);
		page_ActivationKeys.setTxt_UsageLimit(usageLimit);
		page_ActivationKeys.select_BaseChannel(basechan);
		page_ActivationKeys.checkBox_Monitoring(monitoring);
		page_ActivationKeys.checkBox_Provisioning(provisioning);

		if(unvDefault)
			page_ActivationKeys.select_UniversalDefault("Yes, use this token for all normal registrations");
		else
			page_ActivationKeys.select_UniversalDefault("No");
		page_ActivationKeys.clickButton_CreateKey();
	}	

	public void deleteActivationKey(String name, boolean openAndLogin){
		if(openAndLogin){
			task_RhnBase.OpenAndLogIn();
		}
		page_ActivationKeys.open();
		while(rh.isTextPresent(name)){
			page_RhnCommon.setTxt_FilterBy(name);
			page_RhnCommon.clickButton_Filter_Go();
			if(rh.isTextPresent(name)){
				page_RhnCommon.clickLink_GeneralLink(name);
				page_ActivationKeys.clickLink_DeleteKey();
				page_ActivationKeys.clickButton_Delete();
				Assert.assertTrue(rh.isTextPresent("has been deleted"));
			}
			page_ActivationKeys.open();
			//Assert.assertTrue(rh.isTextNotPresent(name));
		}
	}

	public void goToKey(String key){
		page_ActivationKeys.open();
		page_RhnCommon.setTxt_FilterBy(key);
		page_RhnCommon.clickButton_Filter_Go();
		if(rh.isTextPresent(key)){
			page_RhnCommon.clickLink_GeneralLink(key);
		}
	}
	
	public String getActivationKey_ID(String keyName){
		String id = null;
		page_ActivationKeys.open();
		page_RhnCommon.setTxt_FilterBy(keyName);
		page_RhnCommon.clickButton_Filter_Go();
		if(rh.isTextPresent(keyName)){
			id = rh.getText("xpath=//tbody/tr/td[2]/form/table[2]/tbody/tr/td[3]");
			log.info("Activation key id = "+id);
		}
		else {
			log.info("Activation key not found");
		}
		
		return id;
		
		
	}

	public void addProvisioningToKey(String key){
		task_RhnBase.OpenAndLogIn();
		goToKey(key);
		//Assert.assertTrue(rh.isTextNotPresent("Packages"));
		Assert.assertFalse(rh.isElementPresent("link=Packages", true));
	//	Assert.assertTrue(rh.isTextNotPresent("Configuration"));
		page_ActivationKeys.checkBox_Provisioning(true);
		page_ActivationKeys.clickButton_UpdateActivationKey();
		Assert.assertTrue(rh.isElementPresent("link=Packages", true));
		Assert.assertTrue(rh.isElementPresent("link=Configuration", true));
	}


	public void addPackageToKey(String key, String pkg){
		goToKey(key);
		page_ActivationKeys.clickLink_Packages();
		page_ActivationKeys.setTxt_EnterPackageNames(pkg);
		page_ActivationKeys.clickButton_UpdateActivationKey();
		Assert.assertTrue(rh.isTextPresent("Activation Key "+key+" has been modified."));
	}
	
	public void addChildChannelToKey(String key, String childChannel){
		goToKey(key);
		page_ActivationKeys.clickLink_ChildChannels();
		page_ActivationKeys.selectChildChannel(childChannel);
		page_ActivationKeys.clickButton_UpdateKey();
		Assert.assertTrue(rh.isTextPresent("Activation Key "+key+" has been modified."));
	}
	
	public void addChildChannelToKey(String key, String childChannel01, String childChannel02, String childChannel03){
		goToKey(key);
		page_ActivationKeys.clickLink_ChildChannels();
		
		sel.addSelection("token_child_channels", childChannel01);
		sel.addSelection("token_child_channels", childChannel02);
		sel.addSelection("token_child_channels", childChannel03);
		
		/*page_ActivationKeys.selectChildChannel(childChannel01);
		page_ActivationKeys.selectChildChannel(childChannel02);
		page_ActivationKeys.selectChildChannel(childChannel03);*/
		
		page_ActivationKeys.clickButton_UpdateKey();
		Assert.assertTrue(rh.isTextPresent("Activation Key "+key+" has been modified."));
	}
	
	public boolean isSystemActivated(String system,String key){
		boolean activated = false;
		page_ActivationKeys.open();
		goToKey(key);
		page_ActivationKeys.clickLink_ActivatedSystem();
		if(rh.isTextPresent(system))
			activated=true;
		else
			activated=false;
		return activated;
	}
	
	public void createActivationKey(String name,String key, String usageLimit, String channel,
			boolean monitoring, boolean provisioning,
			boolean virt, boolean virt_Platform,boolean unvDefault){
		throw new SeleniumException("wrong version");
	}

	public String getAKFromKeyName(String keyName){
		page_ActivationKeys.open();
		page_RhnCommon.setTxt_FilterBy(keyName);
		page_RhnCommon.clickButton_Filter_Go();		
		String ak = 
			rh.getTableData(
					AK_XPATH,
					keyName, 3);
		log.info("Activation Key is : " + ak);
		return ak;
	}

	/**
	 * @since Space01
	 * @param channel
	 * @param key
	 */
	public void addConfigChannelToKey(String key,String channel){
		throw new SeleniumException("wrong version");
	}
		
}
