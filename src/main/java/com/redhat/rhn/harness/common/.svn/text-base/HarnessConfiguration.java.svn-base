package com.redhat.rhn.harness.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class HarnessConfiguration {

	private static Properties properties = null;
	
	static String RHN_HOST_tmp;
	static String RHN_USER_tmp;
	static String RHN_PASS_tmp;
	static String RHN_SYSTEM01_tmp;
	static String RHN_SYSTEM02_tmp;
	static String RHN_SYSTEM03_tmp;
	static String SAT_VERSION_tmp;
	static String TEST_NAME_tmp;
	static int SELENIUM_SERVER_PORT_tmp;
	
	static String HUDSON_RHN_HOST;
	static String HUDSON_RHN_USER;
	static String HUDSON_RHN_PASS;
	static String HUDSON_RHN_SYSTEM01;
	static String HUDSON_RHN_SYSTEM02;
	static String HUDSON_RHN_SYSTEM03;
	static String HUDSON_SAT_VERSION;
	static String HUDSON_TEST_NAME;
	static int HUDSON_SELENIUM_SERVER_PORT;
	
	public static String SATELLITE_VERSION;
	
/*	//version class loader
	protected static VersionedInstantiator instantiator;
	protected static VersionedInstantiator taskInstantiator;
	
	//PAGES
	public static ActivationKeysPage activationKeysPage = null;
	public static SatelliteSystemsPage satelliteSystemsPage = null;
	public static SystemProvisionPage systemProvisionPage = null;
	public static ConfigurationPage configurationPage = null;
	public static SchedulePage schedulePage = null;
	public static SDCPage sdcPage = null;
	public static RhnCommon rhnCommon = null;
	public static InstallPage installPage = null;
	public static SystemSetManagerPage systemSetManagerPage = null;
	public static ChannelsPage channelsPage = null;
	public static ErrataPage errataPage = null;
	public static KickStartPage kickStartPage = null;
	public static MonitoringPage monitoringPage = null;
	public static SatelliteToolsPage satelliteToolsPage = null;
	public static ProxyPage proxyPage = null;
	public static YourRhnPage yourRhnPage = null;
	public static ErrataSearchPage errataSearchPage = null;
	public static Java2ndGenList java2ndGenList = null;
	public static WebList webList = null;
	public static SSMPage ssmPage = null;
	public static UsersPage usersPage = null;
	//PAGES
	
	//TASKS
	public static ActivationKeys activationKeys = null;
	public static Authentication authentication = null;
	public static BVT_Tasks bvt_Tasks = null;
	public static Channels channels = null;
	public static ConfigManagement configManagement = null;
	public static ErrataManagement errataManagement = null;
	public static KickStart kickstart = null;
	public static Monitoring monitoring = null;
	public static OctoKick octoKick = null;
	public static Pagination pagination = null;
	public static RhnBase rhnBase = null;
	public static RhnRegister rhnRegister = null;
	public static SatelliteAdvancedSearch01 satelliteAdvancedSearch01 = null;
	public static SatelliteProxy satelliteProxy = null;
	public static SatelliteTools satelliteTools = null;
	public static SdcSoftware sdcSoftware = null;
	public static Search search = null;
	public static Solaris solaris = null;
	public static SSM ssm = null;
	public static TestPrep testPrep = null;
	public static UserRoles userRoles = null;
	public static Virtualization virtualization = null;
	public static YourRhn yourRhn = null;
	
	
	//TASKS
*/
	
	//version class loader
	
	public static String getProperty(String key) {
		return getProperty(key, "");
	}

	public static String getProperty(String key, String defaultValue) {
		if (properties == null) {
			loadProperties();
		}
		return properties.getProperty(key, System
				.getProperty(key, defaultValue)).trim();
	}

	public static int getPropertyAsInt(String key, int defaultValue) {
		int intValue = -1;
		try {
			intValue = Integer.parseInt(getProperty(key, String.valueOf(
					defaultValue).trim()));
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		return intValue;
	}

	private static void loadProperties() {
		properties = new Properties();
		String path = "/"
				+ System.getProperty("harness.environment", "localhost")
				+ "-settings.properties";
		String mydir = System.getProperty("user.dir");
		InputStream in = null;
		try {
			
			if (in == null) {
				File file = new File(mydir + path);
				//System.out.println("file = "+file);
					in = new FileInputStream(file);
			}
			if (in != null) {
				properties.load(in);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ee) {
					ee.printStackTrace();
				}
			}
		}
		
		if (System.getenv("HUDSON_RHN_HOST")!=null || 
				System.getenv("HUDSON_SAT_VERSION")!=null){
			// Detected Hudson configuration, overload the properties there
			HUDSON_SAT_VERSION = System.getenv("HUDSON_SAT_VERSION")==null ? 
					"" : System.getenv("HUDSON_SAT_VERSION");
			HUDSON_RHN_HOST = System.getenv("HUDSON_RHN_HOST")==null ? 
					"" : System.getenv("HUDSON_RHN_HOST");
			HUDSON_RHN_USER = System.getenv("HUDSON_RHN_USER")==null ? 
					"" : System.getenv("HUDSON_RHN_USER");
			HUDSON_RHN_PASS = System.getenv("HUDSON_RHN_PASS")==null ? 
					"" : System.getenv("HUDSON_RHN_PASS");
			HUDSON_RHN_SYSTEM01 = System.getenv("HUDSON_RHN_SYSTEM01")==null ? 
					"" : System.getenv("HUDSON_RHN_SYSTEM01");
			HUDSON_RHN_SYSTEM02 = System.getenv("HUDSON_RHN_SYSTEM02")==null ? 
					"" : System.getenv("HUDSON_RHN_SYSTEM02");
			HUDSON_RHN_SYSTEM03 = System.getenv("HUDSON_RHN_SYSTEM03")==null ? 
					"" : System.getenv("HUDSON_RHN_SYSTEM03");
			HUDSON_SELENIUM_SERVER_PORT = System.getenv("HUDSON_SELENIUM_SERVER_PORT")==null ? 
					4444 : new Integer(System.getenv("HUDSON_SELENIUM_SERVER_PORT")).intValue();
		}else{
			HUDSON_RHN_HOST = getProperty("hudson.rhn.host");
			HUDSON_RHN_USER = getProperty("hudson.rhn.user");
			HUDSON_RHN_PASS = getProperty("hudson.rhn.pass");
			HUDSON_RHN_SYSTEM01 = getProperty("hudson.rhn.system01");
			HUDSON_RHN_SYSTEM02 = getProperty("hudson.rhn.system02");
			HUDSON_RHN_SYSTEM03 = getProperty("hudson.rhn.system03");
			HUDSON_SAT_VERSION = getProperty("hudson.sat.version");
			HUDSON_TEST_NAME = getProperty("hudson.testopia.testrun.testplan");
			HUDSON_SELENIUM_SERVER_PORT = getPropertyAsInt("hudson.selenium.port",4444);			
		}
		
		//System.out.println("HUDSON_RHN_HOST =" + HUDSON_RHN_HOST);
		//System.out.println("HUDSON_RHN_USER =" + HUDSON_RHN_USER);
		//System.out.println("HUDSON_RHN_PASS =" + HUDSON_RHN_PASS);
		
		if (HUDSON_RHN_HOST.isEmpty()){
			 RHN_HOST_tmp = getProperty("rhn.host");
			 RHN_USER_tmp = getProperty("rhn.user");
			 RHN_PASS_tmp = getProperty("rhn.pass");
			 RHN_SYSTEM01_tmp = getProperty("rhn.system01");
			 RHN_SYSTEM02_tmp = getProperty("rhn.system02");
			 RHN_SYSTEM03_tmp = getProperty("rhn.system03");
			 SAT_VERSION_tmp = getProperty("sat.version");
			 TEST_NAME_tmp = getProperty("testopia.testrun.testplan");
			 SELENIUM_SERVER_PORT_tmp = getPropertyAsInt("selenium.port",4444);
			 SATELLITE_VERSION = SAT_VERSION_tmp;
		}
		else{
			 RHN_HOST_tmp = HUDSON_RHN_HOST;
			 RHN_USER_tmp = HUDSON_RHN_USER;
			 RHN_PASS_tmp = HUDSON_RHN_PASS;
			 RHN_SYSTEM01_tmp = HUDSON_RHN_SYSTEM01;
			 RHN_SYSTEM02_tmp = HUDSON_RHN_SYSTEM02;
			 RHN_SYSTEM03_tmp = HUDSON_RHN_SYSTEM03;
			 SAT_VERSION_tmp = HUDSON_SAT_VERSION;
			 TEST_NAME_tmp = HUDSON_TEST_NAME;
			 SELENIUM_SERVER_PORT_tmp = HUDSON_SELENIUM_SERVER_PORT;
			 SATELLITE_VERSION = HUDSON_SAT_VERSION.trim();
		}
		
		if(SATELLITE_VERSION.equalsIgnoreCase("space01")){
			SATELLITE_VERSION = "5.3";
		}
			
		//if ( Double.valueOf(SATELLITE_VERSION.trim()) >= 5.3)
		//	RHN_CHANNELS_PAGE = "/rhn/software/channels/All.do";
		
		
		System.out.println("sat.version ="+ SATELLITE_VERSION);
	}
		//this will work nicely to get the version automatically
		/*RhnXmlrpcClient myClient = new RhnXmlrpcClient();
		String version = (String) myClient.executeNoParams("api.systemVersion");*/
		//adding versioned class loader here.
		
	/*	//set up versioned class loading for tasks
		LinkedHashMap<String, String> versions = new LinkedHashMap<String, String>();
		//first value is whats passed, the second is what is entered into localhost.properties
		versions.put("4.2", "Sat42");
		versions.put("5.0", "Sat50");
		versions.put("5.1", "Sat51");
		versions.put("5.2", "Sat51");
		versions.put("5.3", "Space01");
		versions.put("space01", "Space01");
	
		instantiator = new VersionedInstantiator(versions, 4, SATELLITE_VERSION);
		
		//PAGES
		rhnCommon = (RhnCommon)instantiator.getVersionedInstance(RhnCommon.class); 
		activationKeysPage = (ActivationKeysPage)instantiator.getVersionedInstance(ActivationKeysPage.class); 
		satelliteSystemsPage = (SatelliteSystemsPage)instantiator.getVersionedInstance(SatelliteSystemsPage.class);
		systemProvisionPage = (SystemProvisionPage)instantiator.getVersionedInstance(SystemProvisionPage.class);
		configurationPage = (ConfigurationPage)instantiator.getVersionedInstance(ConfigurationPage.class);
		schedulePage = (SchedulePage)instantiator.getVersionedInstance(SchedulePage.class);
		sdcPage = (SDCPage)instantiator.getVersionedInstance(SDCPage.class);
		installPage = (InstallPage)instantiator.getVersionedInstance(InstallPage.class);
		systemSetManagerPage = (SystemSetManagerPage)instantiator.getVersionedInstance(SystemSetManagerPage.class);
		channelsPage = (ChannelsPage)instantiator.getVersionedInstance(ChannelsPage.class);
		errataPage = (ErrataPage)instantiator.getVersionedInstance(ErrataPage.class);
		kickStartPage = (KickStartPage)instantiator.getVersionedInstance(KickStartPage.class);
		monitoringPage = (MonitoringPage)instantiator.getVersionedInstance(MonitoringPage.class);
		satelliteToolsPage = (SatelliteToolsPage)instantiator.getVersionedInstance(SatelliteToolsPage.class);
		proxyPage = (ProxyPage)instantiator.getVersionedInstance(ProxyPage.class);
		yourRhnPage = (YourRhnPage)instantiator.getVersionedInstance(YourRhnPage.class);
		errataSearchPage = (ErrataSearchPage)instantiator.getVersionedInstance(ErrataSearchPage.class);
		java2ndGenList = (Java2ndGenList)instantiator.getVersionedInstance(Java2ndGenList.class);
		webList = (WebList)instantiator.getVersionedInstance(WebList.class);
		ssmPage = (SSMPage)instantiator.getVersionedInstance(SSMPage.class);
		usersPage = (UsersPage)instantiator.getVersionedInstance(UsersPage.class);
		//PAGES
		
		taskInstantiator = new VersionedInstantiator(versions, 5, SATELLITE_VERSION);
		
		//TASKS
		
		activationKeys = (ActivationKeys)taskInstantiator.getVersionedInstance(ActivationKeys.class);
		authentication = (Authentication)taskInstantiator.getVersionedInstance(Authentication.class);
		bvt_Tasks = (BVT_Tasks)taskInstantiator.getVersionedInstance(BVT_Tasks.class);
		channels = (Channels)taskInstantiator.getVersionedInstance(Channels.class);
		configManagement = (ConfigManagement)taskInstantiator.getVersionedInstance(ConfigManagement.class);
		errataManagement = (ErrataManagement)taskInstantiator.getVersionedInstance(ErrataManagement.class);
		kickstart = (KickStart)taskInstantiator.getVersionedInstance(KickStart.class);
		monitoring = (Monitoring)taskInstantiator.getVersionedInstance(Monitoring.class);
		//octoKick = (OctoKick)taskInstantiator.getVersionedInstance(OctoKick.class);		
		pagination = (Pagination)taskInstantiator.getVersionedInstance(Pagination.class);
		rhnRegister = (RhnRegister)taskInstantiator.getVersionedInstance(RhnRegister.class);
		satelliteProxy = (SatelliteProxy)taskInstantiator.getVersionedInstance(SatelliteProxy.class);
		satelliteTools = (SatelliteTools)taskInstantiator.getVersionedInstance(SatelliteTools.class);
		sdcSoftware = (SdcSoftware)taskInstantiator.getVersionedInstance(SdcSoftware.class);
		solaris = (Solaris)taskInstantiator.getVersionedInstance(Solaris.class);
		ssm = (SSM)taskInstantiator.getVersionedInstance(SSM.class);
		testPrep = (TestPrep)taskInstantiator.getVersionedInstance(TestPrep.class);
		userRoles = (UserRoles)taskInstantiator.getVersionedInstance(UserRoles.class);
		virtualization = (Virtualization)taskInstantiator.getVersionedInstance(Virtualization.class);
		yourRhn = (YourRhn)taskInstantiator.getVersionedInstance(YourRhn.class);
		search = (Search)taskInstantiator.getVersionedInstance(Search.class);
		
		//must  be last
		rhnBase = (RhnBase)taskInstantiator.getVersionedInstance(RhnBase.class);
		*/

	
	
	
	//instantiator = new VersionedInstantiator(versions, 5, getProperty("sat.version"));
	//activationKeysPage = (ActivationKeysPage)instantiator.getVersionedClass(ActivationKeysPage.class); 

	public static final String EVENT_ID_SUBMIT = "_eventId_submit";
	public static final String TIMEOUT = getProperty("timeout", "40000");
	public static final String SELENIUM_SPEED = getProperty("selenium.speed", "10");
	public static final String LONG_TIMEOUT = getProperty("timeout.long",
			"100000");

	public static String SELENIUM_SERVER = getProperty("selenium.server",
			"localhost");
	public static int SELENIUM_SERVER_PORT = SELENIUM_SERVER_PORT_tmp;//getPropertyAsInt("selenium.port",4444);

	
	public static final String RHN_HOST = RHN_HOST_tmp;//getProperty(System.getProperty("hudson.rhn.host"),getProperty("rhn.host"));
	public static final String RHN_USER = RHN_USER_tmp;//getProperty(System.getProperty("hudson.rhn.user"),getProperty("rhn.user"));
	public static final String RHN_PASS = RHN_PASS_tmp;//getProperty(System.getProperty("hudson.rhn.pass"),getProperty("rhn.pass"));
	//RHN_HOST = System.getProperty("rhn.host");

	
	public static final String PROTOCOL = getProperty("rhn.protocol");
	public static final String RHN_EMAIL = getProperty("rhn.email");
	public static final String RHN_WEBQA_ID = getProperty("rhn.webqa.id");
	public static final String RHN_SATELLITE_CERT = getProperty("rhn.satellite.cert");
	public static final String RHN_BVT = getProperty("rhn.bvt");
	public static final String RHN_USER_NONADMIN = getProperty("rhn.nondminuser");
	public static final String RHN_SYSTEM01 = RHN_SYSTEM01_tmp;//getProperty("rhn.system01");
	public static final String RHN_SYSTEM02 = RHN_SYSTEM02_tmp;//getProperty("rhn.system02");
	public static final String RHN_SYSTEM03 = getProperty("rhn.system03");
	public static final String RHN_SYSTEM04 = getProperty("rhn.system04");
	public static final String RHN_SYSTEM_LOADTEST = getProperty("rhn.systemloadtest");
	public static final String RHN_SYSTEM_ANOTHER_SAT = getProperty("rhn.systemanothersatellite");
	public static final String RHN_SYSTEM_SOL10 = getProperty("rhn.system.sol10");
	public static final String RHN_SYSTEM_SOL9 = getProperty("rhn.system.sol9");
	public static final String RHN_SYSTEM_SOL8 = getProperty("rhn.system.sol8");
	public static final String RHN_SYSTEM_SOL10_x86 = getProperty("rhn.system.sol10.x86");
	public static final String RHN_SYSTEM_SOL9_x86 = getProperty("rhn.system.sol9.x86");
	public static final String RHN_SYSTEM_RHEL4 = getProperty("rhn.system.rhel4");
	public static final String RHN_SYSTEM_RHEL5 = getProperty("rhn.system.rhel5");
	public static final String RHN_FAKE01 = getProperty("rhn.fake01");
	public static final String RHN_FAKE02 = getProperty("rhn.fake02");
	public static final String NUMBEROFPOP = getProperty("rhn.pop");
	public static final String RHN_SELINUX = getProperty("rhn.selinux","0");
	public static final String RHN_ENABLE_SCREENSHOT = getProperty("enable.screenshots","0");
	public static final String RHN_SCREENSHOT_DIR = getProperty("selenium.screenshot.dir","/var/www/html/screenshots");
	// public static final String RHN_REG_CMD =
	// getProperty("rhn.registerCommand");

	public static final String RHN_REMOVE_PROFILES = getProperty("rhn.removeAllSystemProfiles");
	public static final String RHN_SAT_REG_CMD = getRHNSAT_REG_CMD();
	public static final String RHN_SCRIPT_DIR = getProperty("rhn.scriptDir");
	public static final String RHN_TEST_PREFIX = getProperty("rhn.testProfilePrefix");
	public static final String RHN_CHANNEL01 = getProperty("rhn.channel01");
	public static final String RHN_CHANNEL02 = getProperty("rhn.channel02");
	public static final String SSH_PUBLIC_KEY = getProperty("ssh.publickey");
	public static final String SSH_PRIVATE_KEY_LOC = getProperty("ssh.privatekeyloc");
	public static final String SSH_KEY_PASS = getProperty("ssh.keypassword");
	public static final String RHN_KERNEL_PARAM1 = getProperty("rhn.kern1");
	public static final String RHN_KERNEL_PARAM2 = getProperty("rhn.kern2");
	public static final int RHN_OSAD_ENABLED = getPropertyAsInt("rhn.osad", 0);
	public static final int SPACEWALK = getPropertyAsInt("spacewalk", 0);
	public static final String SPACEWALK_ACTIVATIONKEY = getProperty("sw.activationkey");
	


	public static final String OCTOKICK_SCRIPTLOC = getProperty("octokick.scriptlocation");
	public static final int OCTOKICK_USESDC = getPropertyAsInt(
			"octokick.scanSDC", 0);
	public static final int OCTOKICK_SIGNED_PACKAGES = getPropertyAsInt(
			"octokick.signedPackages", 0);
	public static final int OCTOKICK_CHECK_SYNC = getPropertyAsInt(
			"octokick.checksync", 0);
	
	public static final String NEVRA_CUSTOM_BASE_CHANNEL_NAME = getProperty("nevra.custom.base_channel.name");
	public static final String NEVRA_CUSTOM_BASE_CHANNEL_LABEL = getProperty("nevra.custom.base_channel.label");
	public static final String NEVRA_CUSTOM_CHILD_CHANNEL_NAME = getProperty("nevra.custom.child_channel.name");
	public static final String NEVRA_CUSTOM_CHILD_CHANNEL_LABEL = getProperty("nevra.custom.child_channel.label");
	public static final String NEVRA_CUSTOM_KS_TREE_PATH01 = getProperty("nevra.custom.ks_tree_path01");
	public static final String NEVRA_CUSTOM_TOOLS_PACKAGES_URL = getProperty("nevra.custom.tools_packages_url");
	public static final int ENABLE_NEVRA_TESTING = getPropertyAsInt("enable.nevra.testing", 0);
	
	public static boolean isNevraTestingEnabled() {
		if (ENABLE_NEVRA_TESTING == 1) {
			return true;
		}
		return false;
	}
	
	public static String POETIC_BASE_URL;
	// Create a symlink on your system if this doesn't exist.
	// 'sudo ln -s <path to firefox-bin> /usr/bin/firefox-bin'
	public static final String BROWSER_TYPE = getProperty("selenium.browser","*chrome");

	// getProperty only handles Strings, thus the following hack:
	/*public static boolean DEBUG;
	static {
		String debug = getProperty("harness.debug", "true");
		if (debug.equals("true")) {
			DEBUG = true;
		} else {
			DEBUG = false;
		}
	}*/

	public static final String RHN_ACCOUNT_DETAILS = RHN_HOST
			+ "/rhn/account/UserDetails.do";
	public static final String RHN_YOUR_RHN_PAGE = "/rhn/YourRhn.do";
	public static final String RHN_SYSTEMS_PAGE = "/rhn/systems/SystemList.do";
	public static final String RHN_SYSTEMS_GROUP_PAGE = "/rhn/systems/SystemGroupList.do";
	public static final String RHN_KICKSTART_PAGE = "/rhn/kickstart/KickstartOverview.do";
	public static final String RHN_ERRATA_PAGE = "/rhn/errata/Overview.do";
	public static String RHN_CHANNELS_PAGE = "/rhn/software/channels/All.do";
	public static final String RHN_ACTIVATIONKEY_PAGE = "/network/account/activation_keys/list.pxt";
	public static final String RHN_SATELLITE_TOOLS = "/internal/satellite/task_engine/index.pxt";
	public static final String RHN_CONFIGURATION_PAGE = "/rhn/configuration/Overview.do";
	public static final String RHN_SCHEDULE_PAGE = "/rhn/schedule/PendingActions.do";
	public static final String RHN_USERS_PAGE = "/rhn/users/ActiveList.do";
	public static final String RHN_CONNECT_PAGE = "/apps/register/connect/";
	public static final String RHN_SIGN_OUT = "/rhn/Logout.do";
	public static final String RHN_PROXY_PAGE = "/network/systems/details/proxy.pxt";
	public static final String RHN_ERRATA_SEARCH = "/rhn/errata/Search.do";
	public static final String RHN_DOCUMENTATION_SEARCH = "/rhn/help/Search.do";
	public static final String RHN_ERRATA_DETAILS = "/rhn/errata/details/Details.do";
	
	public static final String RHN_ERRATA_EDIT_DETAILS = "/rhn/errata/manage/Edit.do";

	public static final String TESTOPIA_TESTPLAN = TEST_NAME_tmp;
	static {
		System.setProperty("testopia.url", getProperty("testopia.url"));
		System.setProperty("testopia.login", getProperty("testopia.login"));
		System.setProperty("testopia.password", getProperty("testopia.password"));
		System.setProperty("testopia.testrun.product", getProperty("testopia.testrun.product"));
		System.setProperty("testopia.testrun.testplan", getProperty("testopia.testrun.testplan",TEST_NAME_tmp));
		System.setProperty("testopia.testcase.overwrite", getProperty("testopia.testcase.overwrite", "1"));
	}


	/*static private HarnessConfiguration _instance = null;

	static public HarnessConfiguration instance() {
		if (null == _instance) {
			_instance = new HarnessConfiguration();
		}
		return _instance;
	}*/

	private static String getHostName() {
		String protocol = "https://";
		String hostname = null;
		try {
			hostname = protocol + InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException un) {
			un.printStackTrace();
		}

		return hostname;
	}


	// rhn.sat_registerCommand
	private static String getRHNSAT_REG_CMD() {
		String url = HarnessConfiguration.RHN_HOST;
		
		if(SATELLITE_VERSION.equalsIgnoreCase("hosted"))
			url = "xmlrpc." + HarnessConfiguration.RHN_HOST;
		String command = "";
		command = "rhnreg_ks " + " --username=" + HarnessConfiguration.RHN_USER
				+ " --password=" + HarnessConfiguration.RHN_PASS
				+ " --serverUrl=http://" + url
				+ "/XMLRPC" + " --email=" + HarnessConfiguration.RHN_EMAIL
				+ " --force";
		
		// System.out.println("DEBUG command="+command);
		return command;
	}

	/*public static Properties getProperties() {
		return properties;
	}*/

	
	
	/*//GETTERS for pages and tasks
	

	public static ActivationKeysPage getActivationKeysPage() {
		return activationKeysPage;
	}

	public static SatelliteSystemsPage getSatelliteSystemsPage() {
		return satelliteSystemsPage;
	}

	public static SystemProvisionPage getSystemProvisionPage() {
		return systemProvisionPage;
	}

	public static ConfigurationPage getConfigurationPage() {
		return configurationPage;
	}

	public static SchedulePage getSchedulePage() {
		return schedulePage;
	}

	public static SDCPage getSdcPage() {
		return sdcPage;
	}

	public static RhnCommon getRhnCommon() {
		return rhnCommon;
	}

	public static InstallPage getInstallPage() {
		return installPage;
	}

	public static SystemSetManagerPage getSystemSetManagerPage() {
		return systemSetManagerPage;
	}

	public static ChannelsPage getChannelsPage() {
		return channelsPage;
	}

	public static ErrataPage getErrataPage() {
		return errataPage;
	}

	public static KickStartPage getKickStartPage() {
		return kickStartPage;
	}

	public static MonitoringPage getMonitoringPage() {
		return monitoringPage;
	}

	public static SatelliteToolsPage getSatelliteToolsPage() {
		return satelliteToolsPage;
	}

	public static ProxyPage getProxyPage() {
		return proxyPage;
	}

	public static YourRhnPage getYourRhnPage() {
		return yourRhnPage;
	}

	public static ErrataSearchPage getErrataSearchPage() {
		return errataSearchPage;
	}

	public static SSMPage getSsmPage() {
		return ssmPage;
	}

	public static UsersPage getUsersPage() {
		return usersPage;
	}

	public static ActivationKeys getActivationKeys() {
		return activationKeys;
	}

	public static Authentication getAuthentication() {
		return authentication;
	}

	public static BVT_Tasks getBvt_Tasks() {
		return bvt_Tasks;
	}

	public static Channels getChannels() {
		return channels;
	}

	public static ConfigManagement getConfigManagement() {
		return configManagement;
	}

	public static ErrataManagement getErrataManagement() {
		return errataManagement;
	}

	public static KickStart getKickstart() {
		return kickstart;
	}

	public static Monitoring getMonitoring() {
		return monitoring;
	}

	public static OctoKick getOctoKick() {
		return octoKick;
	}

	public static Pagination getPagination() {
		return pagination;
	}

	public static RhnBase getRhnBase() {
		return rhnBase;
	}

	public static RhnRegister getRhnRegister() {
		return rhnRegister;
	}

	public static SatelliteAdvancedSearch01 getSatelliteAdvancedSearch01() {
		return satelliteAdvancedSearch01;
	}

	public static SatelliteProxy getSatelliteProxy() {
		return satelliteProxy;
	}

	public static SatelliteTools getSatelliteTools() {
		return satelliteTools;
	}

	public static SdcSoftware getSdcSoftware() {
		return sdcSoftware;
	}

	public static Search getSearch() {
		return search;
	}

	public static Solaris getSolaris() {
		return solaris;
	}

	public static SSM getSsm() {
		return ssm;
	}

	public static TestPrep getTestPrep() {
		return testPrep;
	}

	public static UserRoles getUserRoles() {
		return userRoles;
	}

	public static Virtualization getVirtualization() {
		return virtualization;
	}

	public static YourRhn getYourRhn() {
		return yourRhn;
	}

*/

}
