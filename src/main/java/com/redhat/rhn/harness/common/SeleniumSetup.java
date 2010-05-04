package com.redhat.rhn.harness.common;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import junit.framework.TestCase;

import org.apache.xmlrpc.XmlRpcException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.redhat.qe.auto.instantiate.VersionedInstantiator;
import com.redhat.qe.auto.selenium.ExtendedSelenium;
import com.redhat.qe.tools.ExecCommands;
import com.redhat.rhn.common.pages.Page;
import com.redhat.rhn.harness.Sat42.pages.ActivationKeysPage;
import com.redhat.rhn.harness.Sat42.pages.ChannelsPage;
import com.redhat.rhn.harness.Sat42.pages.ConfigurationPage;
import com.redhat.rhn.harness.Sat42.pages.DocumentationSearchPage;
import com.redhat.rhn.harness.Sat42.pages.ErrataPage;
import com.redhat.rhn.harness.Sat42.pages.ErrataSearchPage;
import com.redhat.rhn.harness.Sat42.pages.InstallPage;
import com.redhat.rhn.harness.Sat42.pages.KickStartPage;
import com.redhat.rhn.harness.Sat42.pages.MonitoringPage;
import com.redhat.rhn.harness.Sat42.pages.ProxyPage;
import com.redhat.rhn.harness.Sat42.pages.RhnCommon;
import com.redhat.rhn.harness.Sat42.pages.SDCPage;
import com.redhat.rhn.harness.Sat42.pages.SSMPage;
import com.redhat.rhn.harness.Sat42.pages.SatelliteSystemsPage;
import com.redhat.rhn.harness.Sat42.pages.SatelliteToolsPage;
import com.redhat.rhn.harness.Sat42.pages.SchedulePage;
import com.redhat.rhn.harness.Sat42.pages.SystemProvisionPage;
import com.redhat.rhn.harness.Sat42.pages.UsersPage;
import com.redhat.rhn.harness.Sat42.pages.YourRhnPage;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.Sat42.tasks.ActivationKeys;
import com.redhat.rhn.harness.common.Sat42.tasks.Authentication;
import com.redhat.rhn.harness.common.Sat42.tasks.BVT_Tasks;
import com.redhat.rhn.harness.common.Sat42.tasks.Channels;
import com.redhat.rhn.harness.common.Sat42.tasks.ConfigManagement;
import com.redhat.rhn.harness.common.Sat42.tasks.ErrataManagement;
import com.redhat.rhn.harness.common.Sat42.tasks.KickStart;
import com.redhat.rhn.harness.common.Sat42.tasks.Monitoring;
import com.redhat.rhn.harness.common.Sat42.tasks.Pagination;
import com.redhat.rhn.harness.common.Sat42.tasks.Performance;
import com.redhat.rhn.harness.common.Sat42.tasks.RhnBase;
import com.redhat.rhn.harness.common.Sat42.tasks.RhnRegister;
import com.redhat.rhn.harness.common.Sat42.tasks.SSM;
import com.redhat.rhn.harness.common.Sat42.tasks.SatelliteAdvancedSearch01;
import com.redhat.rhn.harness.common.Sat42.tasks.SatelliteProxy;
import com.redhat.rhn.harness.common.Sat42.tasks.SatelliteSync;
import com.redhat.rhn.harness.common.Sat42.tasks.SatelliteTools;
import com.redhat.rhn.harness.common.Sat42.tasks.Sdc;
import com.redhat.rhn.harness.common.Sat42.tasks.Search;
import com.redhat.rhn.harness.common.Sat42.tasks.Solaris;
import com.redhat.rhn.harness.common.Sat42.tasks.TestPrep;
import com.redhat.rhn.harness.common.Sat42.tasks.UserRoles;
import com.redhat.rhn.harness.common.Sat42.tasks.Virtualization;
import com.redhat.rhn.harness.common.Sat42.tasks.YourRhn;
import com.redhat.rhn.harness.common.octokick.OKickStart;

public class SeleniumSetup extends TestCase {
	
	protected static ExtendedSelenium sel = null;
	protected static Logger log = Logger.getLogger(SeleniumSetup.class.getName());
	ExecCommands ex = new ExecCommands();
	public static SSH ssh = new SSH();
	public static SCP scp = new SCP();
	protected HarnessConfiguration hc = new HarnessConfiguration();
	
	
	//version class loader
	protected static VersionedInstantiator instantiator;
	protected static VersionedInstantiator taskInstantiator;
	
	//PAGES
	public static ActivationKeysPage page_ActivationKeys = null;
	public static SatelliteSystemsPage page_SatelliteSystems = null;
	public static SystemProvisionPage page_SystemProvisioning = null;
	public static ConfigurationPage page_Configuration = null;
	public static SchedulePage page_Schedule = null;
	public static SDCPage page_SDC = null;
	public static RhnCommon page_RhnCommon = null;
	public static InstallPage page_Install = null;
	//public static SystemSetManagerPage page_SSM = null;
	public static ChannelsPage page_Channels = null;
	public static ErrataPage page_Errata = null;
	public static DocumentationSearchPage page_DocSearch = null; 
	public static KickStartPage page_KickStart = null;
	public static MonitoringPage page_Monitoring = null;
	public static SatelliteToolsPage page_SatelliteTools = null;
	public static ProxyPage page_Proxy = null;
	public static YourRhnPage page_YourRhn = null;
	public static ErrataSearchPage page_ErrataSearch = null;
	/*public static Java2ndGenList java2ndGenList = null;
	public static WebList webList = null;*/
	public static SSMPage page_SSM = null;
	public static UsersPage page_Users = null;
	//PAGES
	
	//TASKS
	public static ActivationKeys task_ActivationKeys = null;
	public static Authentication task_Authentication = null;
	public static BVT_Tasks task_BVT = null;
	public static Channels task_Channels = null;
	public static ConfigManagement task_ConfigMang = null;
	public static ErrataManagement task_ErrataMang = null;
	public static KickStart task_KickStart = null;
	public static Monitoring task_Monitoring = null;
	public static OKickStart task_OctoKick = null;
	public static Pagination task_Pagination = null;
	public static RhnBase task_RhnBase = null;
	public static RhnRegister task_RhnRegister = null;
	public static SatelliteAdvancedSearch01 task_SearchAdvanced = null;
	public static SatelliteProxy task_Proxy = null;
	public static SatelliteTools task_SatelliteTools = null;
	public static Sdc task_Sdc = null;
	public static Search task_Search = null;
	public static Solaris task_Solaris = null;
	public static SSM task_SSM = null;
	public static TestPrep task_TestPrep = null;
	public static UserRoles task_UserRoles = null;
	public static Virtualization  task_Virt = null;
	public static YourRhn task_YourRhn = null;
	public static OKickStart okick = null;
	public static Performance task_Perf = null;
	public static SatelliteSync task_SatSync = null;
	
	

	static {
		//moved here to initialize harness config before any other class
		try {
			initializeLogging();
			initializeTestopia();  //This is causing some problems.. need to talk to Steve
		}	
		catch (Exception e){
			log.log(Level.SEVERE, "Error initializing logging/Testopia handler", e);
			throw new RuntimeException(e);
		}
		
		//set up versioned class loading for tasks
		LinkedHashMap<String, String> versions = new LinkedHashMap<String, String>();
		//first value is whats passed, the second is what is entered into localhost.properties
		versions.put("hosted", "Hosted");
		versions.put("4.2", "Sat42");
		versions.put("5.0", "Sat50");
		versions.put("5.1", "Sat51");
		versions.put("5.2", "Sat52");
		versions.put("5.3", "Space01");
		versions.put("space01", "Space01");
	
		instantiator = new VersionedInstantiator(versions, 4, HarnessConfiguration.SATELLITE_VERSION);
		
		//PAGES
		page_RhnCommon = (RhnCommon)instantiator.getVersionedInstance(RhnCommon.class); 
		page_ActivationKeys = (ActivationKeysPage)instantiator.getVersionedInstance(ActivationKeysPage.class); 
		page_SatelliteSystems = (SatelliteSystemsPage)instantiator.getVersionedInstance(SatelliteSystemsPage.class);
		page_SystemProvisioning = (SystemProvisionPage)instantiator.getVersionedInstance(SystemProvisionPage.class);
		page_Configuration = (ConfigurationPage)instantiator.getVersionedInstance(ConfigurationPage.class);
		page_Schedule = (SchedulePage)instantiator.getVersionedInstance(SchedulePage.class);
		page_SDC = (SDCPage)instantiator.getVersionedInstance(SDCPage.class);
		page_Install = (InstallPage)instantiator.getVersionedInstance(InstallPage.class);
		//page_SSM = (SystemSetManagerPage)instantiator.getVersionedInstance(SystemSetManagerPage.class);
		page_Channels = (ChannelsPage)instantiator.getVersionedInstance(ChannelsPage.class);
		page_Errata = (ErrataPage)instantiator.getVersionedInstance(ErrataPage.class);
		page_DocSearch = (DocumentationSearchPage)instantiator.getVersionedInstance(DocumentationSearchPage.class);
		page_KickStart = (KickStartPage)instantiator.getVersionedInstance(KickStartPage.class);
		page_Monitoring = (MonitoringPage)instantiator.getVersionedInstance(MonitoringPage.class);
		page_SatelliteTools = (SatelliteToolsPage)instantiator.getVersionedInstance(SatelliteToolsPage.class);
		page_Proxy = (ProxyPage)instantiator.getVersionedInstance(ProxyPage.class);
		page_YourRhn = (YourRhnPage)instantiator.getVersionedInstance(YourRhnPage.class);
		page_ErrataSearch = (ErrataSearchPage)instantiator.getVersionedInstance(ErrataSearchPage.class);
		/*java2ndGenList = (Java2ndGenList)instantiator.getVersionedInstance(Java2ndGenList.class);
		webList = (WebList)instantiator.getVersionedInstance(WebList.class);*/
		page_SSM = (SSMPage)instantiator.getVersionedInstance(SSMPage.class);
		page_Users = (UsersPage)instantiator.getVersionedInstance(UsersPage.class);
		//PAGES
		
		taskInstantiator = new VersionedInstantiator(versions, 5, HarnessConfiguration.SATELLITE_VERSION);
		
		//TASKS
		
		task_ActivationKeys = (ActivationKeys)taskInstantiator.getVersionedInstance(ActivationKeys.class);
		task_Authentication = (Authentication)taskInstantiator.getVersionedInstance(Authentication.class);
		task_BVT = (BVT_Tasks)taskInstantiator.getVersionedInstance(BVT_Tasks.class);
		task_Channels = (Channels)taskInstantiator.getVersionedInstance(Channels.class);
		task_ConfigMang = (ConfigManagement)taskInstantiator.getVersionedInstance(ConfigManagement.class);
		task_ErrataMang = (ErrataManagement)taskInstantiator.getVersionedInstance(ErrataManagement.class);
		task_KickStart = (KickStart)taskInstantiator.getVersionedInstance(KickStart.class);
		task_Monitoring = (Monitoring)taskInstantiator.getVersionedInstance(Monitoring.class);
		//octoKick = (OctoKick)taskInstantiator.getVersionedInstance(OctoKick.class);		
		task_Pagination = (Pagination)taskInstantiator.getVersionedInstance(Pagination.class);
		task_RhnRegister = (RhnRegister)taskInstantiator.getVersionedInstance(RhnRegister.class);
		task_Proxy = (SatelliteProxy)taskInstantiator.getVersionedInstance(SatelliteProxy.class);
		task_SatelliteTools = (SatelliteTools)taskInstantiator.getVersionedInstance(SatelliteTools.class);
		task_Sdc = (Sdc)taskInstantiator.getVersionedInstance(Sdc.class);
		task_Solaris = (Solaris)taskInstantiator.getVersionedInstance(Solaris.class);
		task_SSM = (SSM)taskInstantiator.getVersionedInstance(SSM.class);
		task_TestPrep = (TestPrep)taskInstantiator.getVersionedInstance(TestPrep.class);
		task_UserRoles = (UserRoles)taskInstantiator.getVersionedInstance(UserRoles.class);
		task_Virt = (Virtualization)taskInstantiator.getVersionedInstance(Virtualization.class);
		task_YourRhn = (YourRhn)taskInstantiator.getVersionedInstance(YourRhn.class);
		task_Search = (Search)taskInstantiator.getVersionedInstance(Search.class);
		task_Perf = (Performance)taskInstantiator.getVersionedInstance(Performance.class);
		task_SatSync = (SatelliteSync)taskInstantiator.getVersionedInstance(SatelliteSync.class);
		//must  be last
		task_RhnBase = (RhnBase)taskInstantiator.getVersionedInstance(RhnBase.class);
		
		okick = new com.redhat.rhn.harness.common.octokick.OKickStart();
		//TASKS
		
	}
	
	//version loader
	@BeforeSuite(groups="setup")
	public void setup() throws Exception{
		String methodName = null;
		String className = null;
		String methodName1 = null;
		String className1 = null;

		methodName = new Throwable().fillInStackTrace().getStackTrace()[4].getMethodName();
		className = new Throwable().fillInStackTrace().getStackTrace()[4].getClassName();
		if(!methodName.equalsIgnoreCase("main")){
			methodName1 = new Throwable().fillInStackTrace().getStackTrace()[5].getMethodName();
			className1 = new Throwable().fillInStackTrace().getStackTrace()[5].getClassName();
		}
		initializeTestopia();
	/*	try {
			initializeLogging();
			initializeTestopia();  //This is causing some problems.. need to talk to Steve
		}	
		catch (Exception e){
			log.log(Level.SEVERE, "Error initializing logging/Testopia handler", e);
			throw e;
		}*/
		
		/*TestListener listener = null;
		this.createResult().addListener(listener);*/

		
		
		if(className1 != null)
			if(className1.contains("com.redhat.rhn")){	
				//log.info("\n==================== BEGIN TEST:   "+methodName1+"  "+className1+ " ==================================================\n");
			}
			else{
				//log.info("\n==================== BEGIN TEST:   "+methodName+"  "+className+ " ==================================================\n");
			}
		
		try{
			ExtendedSelenium.getInstance();
			/*if running multiple tests w/ junit this is a real problem and you 
			should be aware of selenium start getting called twice.*/
			
			/*w/ testng the old instance in a test is never closed so starting
			a new selenium instance actually creates a second instance. For now
			I'm just going to supress the error, as I may run this in junit or testng*/
			log.log(Level.FINEST,"Selenium already started, not restarting");
			log.log(Level.FINEST,"AUTOMATION BUG!, Login called twice");
		}
		catch(Exception npe){
			log.log(Level.FINEST,"Selenium NOT started, starting a new instance");
		//	log.fine("Browser path: " + HarnessConfiguration.BROWSER_TYPE);
			ExtendedSelenium.newInstance(HarnessConfiguration.SELENIUM_SERVER, HarnessConfiguration.SELENIUM_SERVER_PORT, HarnessConfiguration.BROWSER_TYPE, "http://redhat.com/");
			sel = ExtendedSelenium.getInstance();
			RhnHelper.setSel(sel);
			Page.setSel(sel);
			com.redhat.rhn.harness.Sat42.pages.RhnCommon.setSel(sel);
			sel.start();
		}
		
		
			
	}
	
	@AfterMethod(groups="teardown")
	public void tearDown(){
		String screenShotPath = null;
		InetAddress addr = null;
		String localhost = null;
		String selServer = null; //the selenium server 
		String host = null;
		
		//get performance of server after each test
		log.fine("====Performance on Server and ScreenShot======");
		ssh.executeViaSSHWithReturn(IRhnBase.SERVER_HOSTNAME, "vmstat", Level.FINE);
		
		try {
			addr = InetAddress.getLocalHost();
		} catch (java.net.UnknownHostException e1) {
			e1.printStackTrace();
		}
		localhost = addr.getHostName();
		selServer = HarnessConfiguration.SELENIUM_SERVER;
		
		if(!localhost.equalsIgnoreCase(selServer)){
			host = selServer;
		}
		else{
			host = localhost;
		}
		

		try{
			if(sel == null){
				log.log(Level.FINEST, "sel is null");
				sel = ExtendedSelenium.getInstance();
				}
			log.log(Level.FINEST, "sel instance = " + sel );
			if(HarnessConfiguration.getProperty("enable.screenshots").equalsIgnoreCase("1")){
				try{
					screenShotPath = sel.screenCapture(HarnessConfiguration.RHN_SCREENSHOT_DIR);
					log.info("Captured ScreenShot to http://"+host+"/screenshots"+screenShotPath.substring(HarnessConfiguration.RHN_SCREENSHOT_DIR.length()));
				}
				catch(Exception screencapture){
						log.log(Level.FINE, "capture screenshot failed",screencapture);
				}
				/*try{
					String dirName = HarnessConfiguration.getProperty("selenium.screenshot.dir").trim();//System.getProperty("user.dir") + File.separator+ "screenshots";
					log.fine("Captured ScreenShot to http://"+host+"/screenshots"+screenShotPath.substring(dirName.length()));
					}
					catch(Exception e){
						String dirName = "/tmp";
						log.info("Captured ScreenShot to http://"+host+"/screenshots"+screenShotPath.substring(dirName.length()));
						//log.log(Level.FINE, "moving the screenshot failed, screenshot may be found @ "+screenShotPath);
					}			*/
			}
			log.fine("====Performance on Server and ScreenShot======");
			sel.stop();
			//log.info("SEL STOP CALLED Method");
			}
			catch(Exception e){
				log.log(Level.SEVERE, "command failed", e);
			}
		}
	
	@AfterSuite(groups="teardown")
	public void afterSuite(){
		sel.stop();
		//log.info("SEL STOP CALLED Suite");
	}
		
	
	
	protected static void initializeTestopia() throws MalformedURLException, XmlRpcException{
		//RhnXmlrpcClient myClient = new RhnXmlrpcClient();
		//String version = (String) myClient.executeNoParams("api.systemVersion");
		SatelliteTestopiaTestNGListener.setBuild(HarnessConfiguration.getProperty("testopia.testrun.build"));
		SatelliteTestopiaTestNGListener.setEnvironment(HarnessConfiguration.getProperty("testopia.testrun.environment"));
		SatelliteTestopiaTestNGListener.setVersion("530");
	}
	
	protected static void initializeLogging(){
		try {
			String autoSubdir = System.getProperty("auto.subdir", "");
			String fn = System.getProperty("user.dir") + File.separator + autoSubdir +  File.separator + "log.properties";
			LogManager.getLogManager().readConfiguration(new FileInputStream(fn));
			log.finer("Loaded logger configuration.");
		} catch (Exception e) {
			System.err.println("Failed to load log settings.");
			log.log(Level.WARNING,
					"Unable to read logging settings.  Keeping default.", e);
		}
	}
	
	
}
