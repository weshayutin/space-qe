package com.redhat.rhn.harness.regression.satellite521;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

@Test(groups="tests")
public class BZ_495583 extends SeleniumSetup{
	// Commenting the class - the current implementation intends presence of Satellite >5.3
	// The one with Satellite 5.2 has another page and complicated to implement.
	
/*
	protected RhnHelper rh = new RhnHelper();
	private static final String ACTIVATION_KEY_NAME = "autotest-satellite521";
	private static final String ACTIVATION_KEY = ""; // leave empty to get generated
	private static final String CONFIG_CHANNEL_PREFIX = 
		"config-channel-satellite521";
	private static final int CONFIG_CHANNELS_CNT = 10;
	private static final int LAST_RANKING_MOVE_UP_CNT = 4;
	private static final int FIRST_RANKING_MOVE_DOWN_CNT = 4;
	
	@BeforeClass(groups = { "setup" })
	public void test00_preparing(){
		task_RhnBase.OpenAndLogIn();
		task_ActivationKeys.deleteActivationKey(ACTIVATION_KEY_NAME, false);
	}
	
	@Test(groups = { "testplan-ActivationKeys-setup" })
	public void test01_createActivationKey(){
		task_RhnBase.OpenAndLogIn();
		task_ActivationKeys.createActivationKey(ACTIVATION_KEY_NAME, 
				ACTIVATION_KEY, "1", false, true, false, false, false);
	}
	
	@Test(dependsOnMethods="test01_createActivationKey", 
			groups = { "testplan-ActivationKeys-setup" })
	public void test02_createConfigChannels(){
		task_RhnBase.OpenAndLogIn();
		for(int i=0;i<CONFIG_CHANNELS_CNT;i++)
			task_ConfigMang.CreateNewConfigChannel(CONFIG_CHANNEL_PREFIX+"-"+(i+1), 
					false, true, true); 
	}
	
	@Test(dependsOnMethods="test02_createConfigChannels", 
			groups = { "testplan-ActivationKeys" })
	public void test03_applyConfigChannels(){
		task_RhnBase.OpenAndLogIn();
		for(int i=0;i<CONFIG_CHANNELS_CNT;i++)
			task_ActivationKeys.addConfigChannelToKey(ACTIVATION_KEY_NAME, 
					CONFIG_CHANNEL_PREFIX+"-"+(i+1));
	}

	@Test(dependsOnMethods="test03_applyConfigChannels", 
			groups = { "testplan-ActivationKeys" })
	public void test04_checkExistenceOfConfigChannels(){
		task_RhnBase.OpenAndLogIn();
		task_ActivationKeys.goToKey(ACTIVATION_KEY_NAME);
		page_ActivationKeys.clickLink_Configuration();
		page_ActivationKeys.clickLink_ListUnsubscribeFromChannels();
		for(int i=0;i<CONFIG_CHANNELS_CNT;i++)
			Assert.assertTrue(rh.isTextPresent(CONFIG_CHANNEL_PREFIX+"-"+(i+1)));
	}
	
	@Test(dependsOnMethods="test04_checkExistenceOfConfigChannels", 
			groups = { "testplan-ActivationKeys" })
	public void test05_checkRankingOrderOfConfigChannels(){
		task_RhnBase.OpenAndLogIn();
		task_ActivationKeys.goToKey(ACTIVATION_KEY_NAME);
		page_ActivationKeys.clickLink_Configuration();
		page_ActivationKeys.clickLink_ViewModifyRankings();
		String currentRankingText;
		// should be sorting in asc order...
		for(int i=0;i<CONFIG_CHANNELS_CNT;i++){
			Assert.assertTrue(rh.isTextPresent(CONFIG_CHANNEL_PREFIX+"-"+(i+1)));
			Assert.assertTrue(rh.isElementPresent("xpath=//select[@id='ranksWidget']/option["+(i+1)+"]", false));
			currentRankingText = sel.getText("xpath=//select[@id='ranksWidget']/option["+(i+1)+"]");
			Assert.assertTrue(currentRankingText.equals(CONFIG_CHANNEL_PREFIX+"-"+(i+1)));
		}
	}
	
	@Test(dependsOnMethods="test05_checkRankingOrderOfConfigChannels", 
			groups = { "testplan-ActivationKeys" })
	public void test06_changeRankingOrder(){
		task_RhnBase.OpenAndLogIn();
		task_ActivationKeys.goToKey(ACTIVATION_KEY_NAME);
		page_ActivationKeys.clickLink_Configuration();
		page_ActivationKeys.clickLink_ViewModifyRankings();
		String currentRankingText;
		// should be sorting in asc order...
		sel.select("xpath=//select[@id='ranksWidget']", "label="+CONFIG_CHANNEL_PREFIX+"-"+CONFIG_CHANNELS_CNT);
		for(int i=0;i<LAST_RANKING_MOVE_UP_CNT;i++)
			page_ActivationKeys.clickImage_ChangeRankingUp();
		currentRankingText = sel.getText("xpath=//select[@id='ranksWidget']/option["+(CONFIG_CHANNELS_CNT-LAST_RANKING_MOVE_UP_CNT)+"]");
		Assert.assertTrue(currentRankingText.equals(CONFIG_CHANNEL_PREFIX+"-"+CONFIG_CHANNELS_CNT));

		sel.select("xpath=//select[@id='ranksWidget']", "label="+CONFIG_CHANNEL_PREFIX+"-"+1);
		for(int i=0;i<FIRST_RANKING_MOVE_DOWN_CNT;i++)
			page_ActivationKeys.clickImage_ChangeRankingDown();
		currentRankingText = sel.getText("xpath=//select[@id='ranksWidget']/option["+(FIRST_RANKING_MOVE_DOWN_CNT+1)+"]");
		Assert.assertTrue(currentRankingText.equals(CONFIG_CHANNEL_PREFIX+"-"+1));
		
		page_ActivationKeys.clickButton_UpdateRankingConfigChannel();		
	}
	
	@Test(dependsOnMethods="test06_changeRankingOrder", 
			groups = { "testplan-ActivationKeys" })
	public void test07_verifyRankingOrder(){
		task_RhnBase.OpenAndLogIn();
		task_ActivationKeys.goToKey(ACTIVATION_KEY_NAME);
		page_ActivationKeys.clickLink_Configuration();
		page_ActivationKeys.clickLink_ViewModifyRankings();
		String currentRankingText;

		sel.select("xpath=//select[@id='ranksWidget']", "label="+CONFIG_CHANNEL_PREFIX+"-"+CONFIG_CHANNELS_CNT);
		currentRankingText = sel.getText("xpath=//select[@id='ranksWidget']/option["+(CONFIG_CHANNELS_CNT-LAST_RANKING_MOVE_UP_CNT)+"]");
		Assert.assertTrue(currentRankingText.equals(CONFIG_CHANNEL_PREFIX+"-"+CONFIG_CHANNELS_CNT));

		sel.select("xpath=//select[@id='ranksWidget']", "label="+CONFIG_CHANNEL_PREFIX+"-"+1);
		currentRankingText = sel.getText("xpath=//select[@id='ranksWidget']/option["+(FIRST_RANKING_MOVE_DOWN_CNT+1)+"]");
		Assert.assertTrue(currentRankingText.equals(CONFIG_CHANNEL_PREFIX+"-"+1));
	}
	
	
*/}
