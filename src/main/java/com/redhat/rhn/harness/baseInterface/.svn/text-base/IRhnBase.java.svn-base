package com.redhat.rhn.harness.baseInterface;

import com.redhat.rhn.harness.common.HarnessConfiguration;

public interface IRhnBase
						// the public interface for most items is deprecrated
						/*extends
							IActivationKeys,
							ISearch,
							IChannels,
							IAuthentication,
							IConfigManagement,
							IErrata,
							IKickStart,
							IRhnRegister,
							ISdcSoftware,
							ITestPrep,
							IYourRhn,
							IRhnCommon,
							IUserRoles,
							IMonitoring*/

							{
	
	//CHANGE TO ENUMERATED TYPES	   
	
	public static final String SPACEWALK_ACTIVATION_KEY_NAME = "space_key";
	public static final String SPACEWALK_ACTIVATION_KEY_NUMBER = "99999";
	public static final String SPACEWALK_ACTIVATION_KEY_NUMBER_ORG = "1-99999";
	
	public static final String RUN_RHN_BVT = HarnessConfiguration.RHN_BVT;

	/**
	 * Test profile prefix name, used w/ modified profile names
	 * Usually used when more than one test profile is needed
	 */
	public static final String TESTPREFIX = HarnessConfiguration.RHN_TEST_PREFIX;
	/**
	 * Modified profile name 01
	 * Usually used when more than one test profile is needed
	 */
	public static final String TESTSYSTEM01 = TESTPREFIX+"__A00A001";
	/**
	 * Modified profile name 02
	 * Usually used when more than one test profile is needed
	 */
	public static final String TESTSYSTEM02 = TESTPREFIX+"__A00B002";

	/**
	*Test Profile where profile name = hostname of a solaris 10 box
	*A Solaris 10 BOX
	*/
	public static final String SYSTEM_SOL10 = HarnessConfiguration.RHN_SYSTEM_SOL10;
	
	/**
	*Test Profile where profile name = hostname of a solaris 9 box
	*A Solaris 9 BOX
	*/
	public static final String SYSTEM_SOL9 = HarnessConfiguration.RHN_SYSTEM_SOL9;
	
	/**
	*Test Profile where profile name = hostname of a solaris 9 box
	*A Solaris 8 BOX
	*/
	public static final String SYSTEM_SOL8 = HarnessConfiguration.RHN_SYSTEM_SOL8;
	
	/**
	*Test Profile where profile name = hostname 01
	*A RHEL 4 BOX
	*/
	public static final String SYSTEM_HOSTNAME01 = HarnessConfiguration.RHN_SYSTEM01;

	/**
	 * public key for the localhost listed in localhost.properites
	 */
	public static final String SSH_PUBLIC_KEY = HarnessConfiguration.SSH_PUBLIC_KEY;

	/**
	*Test Profile where profile name = hostname 02
	*A RHEL 5 BOX
	*/
	public static final String SYSTEM_HOSTNAME02 = HarnessConfiguration.RHN_SYSTEM02;
	
	/**
	*Test Profile where profile name = hostname 03
	*A X86_64 BOX
	*/
	public static final String SYSTEM_HOSTNAME03 = HarnessConfiguration.RHN_SYSTEM03;
	
	/**
	*Test Profile where profile name = loadtest
	*A x86 rhel 4 only
	*/
	public static final String SYSTEM_HOSTNAME_LOADTEST = HarnessConfiguration.RHN_SYSTEM_LOADTEST;
	
	public static final String SYSTEM_HOSTNAME_ANOTHER_SATELLITE = HarnessConfiguration.RHN_SYSTEM_ANOTHER_SAT;

	
	/**
	 *Test Profile where profile name = hostname 04
	 *A Custom Distribution, most likely CentOS or Fedora
	 */
	public static final String SYSTEM_HOSTNAME04 = HarnessConfiguration.RHN_SYSTEM04;
	
	/**
	 * RHN Login User Name
	 */
	public static final String USER = HarnessConfiguration.RHN_USER;
	/**
	 * RHN Login User Password
	 */
	public static final String PASSWORD = HarnessConfiguration.RHN_PASS;

	/**
	 * Provides the --serverUrl= for satellite registration
	 */
	public static final String RHN_REG_SAT_URL = " --serverUrl=http://"+HarnessConfiguration.RHN_HOST+"/XMLRPC";

	/**
	*satellite registration command, use w/ satellite servers only
	*/
	public static final String RHN_SAT_REG_CMD = "rhnreg_ks " + " --username="+USER+ " --password="+PASSWORD+
	" --serverUrl=http://"+HarnessConfiguration.RHN_HOST+"/XMLRPC" + " --email="+HarnessConfiguration.RHN_EMAIL + " --force";
	
	/**
	*spacewalk registration command, use w/ spacewalk servers only
	*/
	public static final String SPACEWALK_REG_CMD = "rhnreg_ks " + " --username="+USER+ " --password="+PASSWORD+
	" --serverUrl=http://"+HarnessConfiguration.RHN_HOST+"/XMLRPC" + " --email="+HarnessConfiguration.RHN_EMAIL + " "+ HarnessConfiguration.SPACEWALK_ACTIVATIONKEY +" --force";

	/**
	 * hosted registration command, use w/ hosted environment only
	 */
	public static final String RHN_REG_CMD = "rhnreg_ks " + " --username="+USER+ " --password="+PASSWORD+
	" --serverUrl=http://xmlrpc."+HarnessConfiguration.RHN_HOST+"/XMLRPC" + " --email="+HarnessConfiguration.RHN_EMAIL + " --force";

	/**
	 *hostname of the satellite server or hosted server
	 */
	public static final String SERVER_HOSTNAME = HarnessConfiguration.RHN_HOST;


	/**
	 * User defined channel name in locahost properties 01
	 */
	public static final String RHN_CHANNEL01 = HarnessConfiguration.RHN_CHANNEL01;

	/**
	 * User defined channel name in locahost properties 02
	 */
	public static final String RHN_CHANNEL02 = HarnessConfiguration.RHN_CHANNEL02;
	
	/**
	 * Defines if install is spacewalk, read in from localhost.properties
	 * 0 = rhn
	 * 1 = spacewalk
	 */
	public static final int SPACEWALK = HarnessConfiguration.SPACEWALK;

	/**
	 * Constant to select Quick Search Packages
	 */
	public static int QUICK_SEARCH_PACKAGES =  1001;

	/**
	 * Constant to select Quick Search Systems
	 */
	public static int QUICK_SEARCH_SYSTEMS  =  1002;

	/**
	 * Constant to select Quick Search Errata
	 */
	public static int QUICK_SEARCH_ERRATA =  1003;

	/**
	 * Constant to select Advanced Search
	 */
	public static int ADVANCED_SEARCH_PACKAGES =  1001;

	/**
	 * Constant to select Advanced Search Systems
	 */
	public static int ADVANCED_SEARCH_SYSTEMS  =  1002;

	/**
	 * Constant to select Advanced Search Errata
	 */
	public static int ADVANCED_SEARCH_ERRATA =  1003;

	/**
	 * Quick Search Field check error
	 * Used when a user inputs illegal characters into quick search
	 */
	public static final String ERROR_MSG_SEARCH_FIELD_CHECK="Search strings must contain only letters, numbers, plus symbols, hyphens and dashes.";
	public static final String ERROR_MSG_SEARCH_FIELD_CHECK42="Search strings must contain only letters, numbers, hyphens and dashes.";

	  //ADVANCED SYSTEM SEARCH
	   public static int SEARCH_FieldToSearch_NameDescription = 1004;

	   public static int SEARCH_FieldToSearch_ID = 1005;

	   public static int SEARCH_FieldToSearch_CustomInfo = 1006;

	   public static int SEARCH_FieldToSearch_SnapShotTag = 1007;

	   public static int SEARCH_FieldToSearch_DaysSinceLastCheckin = 1008;

	   public static int SEARCH_FieldToSearch_DaysSinceFirstRegistered = 1009;

	   public static int SEARCH_FieldToSearch_CpuModel = 1010;

	   public static int SEARCH_FieldToSearch_CpuLessThan = 1011;

	   public static int SEARCH_FieldToSearch_CpuGreaterThan = 1012;

	   public static int SEARCH_FieldToSearch_RamLessThan = 1013;

	   public static int SEARCH_FieldToSearch_RamGreaterThan = 1014;

	   public static int SEARCH_FieldToSearch_HD_Description = 1015;

	   public static int SEARCH_FieldToSearch_HD_Driver = 1016;

	   public static int SEARCH_FieldToSearch_HD_DeviceID = 1017;

	   public static int SEARCH_FieldToSearch_NETWORK_IP = 1018;

	   public static int SEARCH_FieldToSearch_NETWORK_Hostname = 1019;

	   public static int SEARCH_FieldToSearch_SystemSoftware_Installed = 1020;

	   public static int SEARCH_FieldToSearch_SystemSoftware_Needed = 1021;
	   
	   public static int SEARCH_FieldToSearch_NumberOfCpu_LESSTHAN = 1022;
	   
	   public static int SEARCH_FieldToSearch_NumberOfCpu_GREATERTHAN = 1022;
	   
	   public static int SEARCH_FieldToSearch_DMI_System = 1023;
	   
	   public static int SEARCH_FieldToSearch_DMI_Bios = 1024;
	   
	   public static int SEARCH_FieldToSearch_DMI_AssetTag = 1025;
	   
	   public static int SEARCH_FieldToSearch_Location_Address = 1026;
	   
	   public static int SEARCH_FieldToSearch_Location_Building = 1027;
	   
	   public static int SEARCH_FieldToSearch_Location_Room = 1028;
	   
	   public static int SEARCH_FieldToSearch_Location_Rack = 1029;
	   
	   public static int SEARCH_FieldToSearch_Details_RunningKernel = 1030;
	   
	   public static int SEARCH_FieldToSearch_CpuNumberGreaterThan = 1031;

	   public static int SEARCH_FieldToSearch_CpuNumberLessThan= 1032;
	   
	   public static int SEARCH_FieldToSearch_Packages_Installed= 1033;
	   
	   public static int SEARCH_FieldToSearch_Packages_Needed= 1034;
	   



	 //ADVANCED ERRATA SEARCH
	   public static int SEARCH_Errata_Synopsis = 1050;

	   public static int SEARCH_Errata_ErratumAdvisory = 1051;

	   public static int SEARCH_Errata_PackageName = 1052;
	   
	   public static int SEARCH_Errata_AllFields = 1053;
	   
	   public static int SEARCH_Errata_CVE = 1054;
	   
	 
	 //ADVANCED DOCUMENTATION SEARCH 
	   public static int SEARCH_Documentation_ContentAndTitle = 1055;
	   
	   public static int SEARCH_Documentation_FreeForm = 1056;

	   public static int SEARCH_Documentation_Content = 1057;
	   
	   public static int SEARCH_Documentation_Title = 1058;
	   
	   public static int SEARCH_Documentation_Title_German = 1059;
	   
	   
	 //ADVANCED PACKAGE SEARCH
	   public static int SEARCH_Package_NameSummary = 1060;

	   public static int SEARCH_Package_Name = 1061;


	 //System Entitlments

	   public static int ENTITLEMENT_BASE = 101;

	   public static int ENTITLEMENT_MONITORING = 102;

	   public static int ENTITLEMENT_PROVISIONING = 103;

	   public static int ENTITLEMENT_VIRT = 104;

	   public static int ENTITLEMENT_VIRT_PLATFORM = 105;


	 //Software Channel Entitlements
	   public static int CHANNEL_RHN_TOOLS 			= 201;
	   public static int CHANNEL_RHN_PROXY 			= 202;
	   public static int CHANNEL_RHEL_CORE_SERVER	= 203;
	   public static int CHANNEL_RHEL_CORE_CLIENT	= 204;
	   public static int CHANNEL_RHEL_VIRT			= 205;


	 //RHN Errors

	   public static int ERROR_ISE = 10;
	   public static int ERROR_PageNotFound = 11;
	   public static int ERROR_NO_ERROR_FOUND = 12;


	   //SYSTEM INFO

	   public static int SYSTEM_DETAIL_HOSTNAME			= 300;
	   public static int SYSTEM_DETAIL_IP_ADDRESS 		= 301;
	   public static int SYSTEM_DETAIL_KERNEL 			= 302;
	   public static int SYSTEM_DETAIL_RHN_SYSTEM_ID	= 303;
	   public static int SYSTEM_DETAIL_RHN_TIME_REGISTERED	= 304;
	   public static int SYSTEM_DETAIL_RHN_TIME_CHECKED_IN	= 305;
	   public static int SYSTEM_DETAIL_RHN_TIME_LAST_BOOTED	= 306;
	   public static int SYSTEM_DETAIL_LOCK_STATUS	= 307;
	   public static int SYSTEM_DETAIL_ENTITLEMENTS	= 308;
	   public static int SYSTEM_DETAIL_DESCRIPTION	= 309;
	   public static int SYSTEM_DETAIL_LOCATION	= 310;
	   public static int SYSTEM_DETAIL_NOTIFICATIONS	= 311;
	   public static int SYSTEM_DETAIL_AUTO_ERRATA_UPDATE	= 312;
	   public static int SYSTEM_DETAIL_SYSTEM_NAME	= 313;

	   //Virt guest actions
	   public static int VIRT_GUEST_START = 400;
	   public static int VIRT_GUEST_STOP = 401;
	   public static int VIRT_GUEST_DESTROY = 402;

	   //Multi-Org
	   public static int ORG_ENTITLEMENT_TOTAL = 450;
	   public static int ORG_ENTITLEMENT_AVAILABLE = 451;
	   public static int ORG_ENTITLEMENT_USAGE = 452;

	   //User Roles

	   public static int USER_ROLE_ACTIVATION = 500;
	   public static int USER_ROLE_CHANNEL = 501;
	   public static int USER_ROLE_SYSTEM_GROUP = 502;
	   public static int USER_ROLE_CONFIGURATION = 503;
	   public static int USER_ROLE_ORG_ADMIN = 504;
	   public static int USER_ROLE_MONITORING = 505;
	   public static int USER_ROLE_SAT_ADMIN = 506;
	   
	   //Errata Management
	    public final int ERRATA_TYPE_BUGFIX = 1;
		public final int ERRATA_TYPE_ENHANCE = 2;
		public final int ERRATA_TYPE_SECURITY = 3;
		
	   //Channel Sharing Setting
		public final int CHANNEL_SHARE_PRIVATE = 480;
		public final int CHANNEL_SHARE_PROTECTED = 481;
		public final int CHANNEL_SHARE_PUBLIC = 482;
		public final int CHANNEL_SHARE_ALL_USERS = 483;
		public final int CHANNEL_SHARE_SELECT_USERS = 484;

		/**
		 * Defines if we should run NEVRA tests
		 * 0 = NO (default)
		 * 1 = YES
		 */
		public static final int ENABLE_NEVRA_TESTING = HarnessConfiguration.ENABLE_NEVRA_TESTING;
		public static final String NEVRA_CUSTOM_BASE_CHANNEL_NAME = HarnessConfiguration.NEVRA_CUSTOM_BASE_CHANNEL_NAME;
		public static final String NEVRA_CUSTOM_BASE_CHANNEL_LABEL = HarnessConfiguration.NEVRA_CUSTOM_BASE_CHANNEL_LABEL;
		public static final String NEVRA_CUSTOM_CHILD_CHANNEL_NAME = HarnessConfiguration.NEVRA_CUSTOM_CHILD_CHANNEL_NAME;
		public static final String NEVRA_CUSTOM_CHILD_CHANNEL_LABEL = HarnessConfiguration.NEVRA_CUSTOM_CHILD_CHANNEL_LABEL;
		public static final String NEVRA_CUSTOM_KS_TREE_PATH01 = HarnessConfiguration.NEVRA_CUSTOM_KS_TREE_PATH01;
		public static final String NEVRA_CUSTOM_TOOLS_PACKAGES_URL = HarnessConfiguration.NEVRA_CUSTOM_TOOLS_PACKAGES_URL;

}
