package com.redhat.rhn.harness.satellite42;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

import org.ho.yaml.YamlDecoder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SolUnit;

@Test(groups="tests")
public class Solaris extends com.redhat.rhn.harness.common.Sat42.tasks.Solaris{
	RhnHelper rh = new RhnHelper();
	
	Random rand = new Random();
	
	//Resource directory inside our project
	final String RESOURCE_DIR = System.getProperty("user.dir") + "/src/main/resources/";
	
	protected static ArrayList<SolUnit> supportMatrix = new ArrayList<SolUnit>();
	
	@BeforeClass(groups = { "setup" })
	public void test00Prep(){
		task_RhnBase.OpenAndLogIn();
		try {
			YamlDecoder dec = new YamlDecoder(
					new FileInputStream(RESOURCE_DIR + "solaris_support.yml"));
			while(true)
				supportMatrix.add(dec.readObjectOfType(SolUnit.class));
		} catch (FileNotFoundException e) {
			log.log(Level.INFO, "Support Matrix load failed:" , e);
			fail();
		} catch (EOFException e) {
			log.log(Level.INFO, "Finished reading support matrix.");
		}
	}
	
	/**
	 * Tests enabling Solaris support and verifies that the
	 * enabling actually occurred
	 */
	@Test(groups="testplan-Solaris")
	public void test01EnableSolarisSupport(){
		task_RhnBase.OpenAndLogIn();
		//this.enableSolarisSupport();
	}
	
	/**
	 * Tests the creation of a base channel to hold Solaris content
	 */
	@Test(dependsOnMethods="test01EnableSolarisSupport", groups="testplan-Solaris")
	public void test02CreateBaseChannels(){
		task_RhnBase.OpenAndLogIn();
		for (SolUnit su: supportMatrix){
			su.setCustomChannel(su.getSolaris() + 
								"-" + su.getArch() + 
								"-channel-" + rand.nextInt());
			task_Channels.createCustomChannelWithArchitecture(
					su.getCustomChannel(),
					su.getRhn_arch());
		}
	}
	
	/**
	 * Tests the creation of activation keys for Solaris boxes
	 */
	@Test(dependsOnMethods="test02CreateBaseChannels", groups="testplan-Solaris")
	public void test03CreateActivationKeys(){
		task_RhnBase.OpenAndLogIn();
		for (SolUnit su: supportMatrix)
			su.setActivationKey(
					this.createSolarisActivationKey(
							su.getCustomChannel()));
	}
	
	/**
	 * Uses the activation keys we just created to register the Solaris boxes
	 * to the Satellite
	 */
	@Test(dependsOnMethods="test03CreateActivationKeys", groups="testplan-Solaris")
	public void test04RegisterSystems(){
		task_RhnBase.OpenAndLogIn();
		for (SolUnit su: supportMatrix)
			this.registerSolarisSystem(su.getHostname(),
				                   	   su.getActivationKey(),
				                   	   su.getCustomChannel());
	}
	
	@Test(dependsOnMethods="test04RegisterSystems", groups="testplan-Solaris")
	public void test05CreateChildChannels(){
		task_RhnBase.OpenAndLogIn();
		for (SolUnit su: supportMatrix){
			su.setCustomChildChannel(su.getSolaris()+
									 "-" + su.getArch() +
									 "-child-" + rand.nextInt());
			task_Channels.createCustomChannelWithParent(
					su.getCustomChildChannel(),
					su.getCustomChannel(),
					su.getRhn_arch());
		}
	}
	
	/**
	 * Pushes patch clusters from a Solaris machine into
	 * newly-created child channels, which the Solaris machines
	 * are then subscibed to 
	 */
	@Test(dependsOnMethods="test05CreateChildChannels", groups="testplan-Solaris")
	public void test06PushPatchClusters(){
		task_RhnBase.OpenAndLogIn();
		for (SolUnit su: supportMatrix){
			su.setClustFNs(
					this.pushSolarisPatch(su.getHostname(),
							RESOURCE_DIR, 
							su.getCluster(),
							su.getCustomChildChannel()));
			//COMPLILATION ERROR HERE  //TO-DO for Steve
		/*	this.verifyPatchesInChannel(su.getClustFNs(),
					su.getCustomChildChannel());*/
		}
	}
	
	//TODO - create single Solaris patches
	/*
	@Test(dependsOnMethods="test06PushPatchClusters", groups="testplan-Solaris")
	public void test07PushPatches(){
		task_RhnBase.OpenAndLogIn();
		for (SolUnit su: supportMatrix){
			su.setPatchFNs(
					this.pushSolarisPatch(su.getHostname(), 
							RESOURCE_DIR,
							su.getPatch(),
							su.getCustomChildChannel()));
			this.verifyPatchesInChannel(su.getPatchFNs(),
					su.getCustomChildChannel());
		}
	}*/
	
	@Test(dependsOnMethods="test06PushPatchClusters", groups="testplan-Solaris")
	public void test08SubscribeToChildChannels(){
		task_RhnBase.OpenAndLogIn();
		
		//COMPLILATION ERROR HERE  //TO-DO for Steve
		/*for (SolUnit su: supportMatrix)
			task_Sdc.subscribeToTopChildChannel(false, 
					su.getHostname());*/
	}
	
	@Test(dependsOnMethods="test08SubscribeToChildChannels", groups="testplan-Solaris")
	public void test09ApplyPatchClusters(){
		task_RhnBase.OpenAndLogIn();
		for (SolUnit su: supportMatrix)
			for (String patch: su.getClustFNs())
				this.applySolarisPatch(su.getHostname(),
					patch);
	}
	
	//TODO - create single Solaris patches
	/*
	@Test(dependsOnMethods="test09ApplyPatchClusters", groups="testplan-Solaris")
	public void test10ApplyPatches(){
		task_RhnBase.OpenAndLogIn();
		for (SolUnit su: supportMatrix)
			for (String patch: su.getPatchFNs())
				this.applySolarisPatch(su.getHostname(),
					patch);
	}*/
	
	@Test(dependsOnMethods="test09ApplyPatchClusters", groups="testplan-Solaris")
	public void test11Up2dateHardware(){
		task_RhnBase.OpenAndLogIn();
		for (SolUnit su: supportMatrix)
			this.testHardwareProfile(su);
	}
	
	@Test(dependsOnMethods="test04RegisterSystems", groups="testplan-Solaris")
	public void test12Up2dateQuirks(){
		task_RhnBase.OpenAndLogIn();
		for (SolUnit su: supportMatrix)
			this.testUp2dateQuirks(su);
	}
	
	@Test(dependsOnMethods="test04RegisterSystems", groups="testplan-Solaris")
	public void test13Up2datePackageTests(){
		task_RhnBase.OpenAndLogIn();
		for (SolUnit su: supportMatrix)
			this.testUp2datePackages(su);
	}
	
	@Test(dependsOnMethods="test04RegisterSystems", groups="testplan-Solaris")
	public void test14ConfigMgmt(){
		task_RhnBase.OpenAndLogIn();
		for (SolUnit su: supportMatrix)
			this.testSolarisConfigMgmt(su.getHostname());
	}

	@AfterClass(groups = { "teardown" })
	public void test20DisableSolarisSupport(){
		task_RhnBase.OpenAndLogIn();
		//this.disableSolarisSupport();
	}
}
