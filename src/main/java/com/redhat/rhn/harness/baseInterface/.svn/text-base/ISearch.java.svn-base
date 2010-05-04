package com.redhat.rhn.harness.baseInterface;

public interface ISearch {

	/*public static int QUICK_SEARCH_PACKAGES =  1001;
	public static int QUICK_SEARCH_SYSTEMS  =  1002;
	public static int QUICK_SEARCH_ERRATA =  1003;*/

	/**
	 * Generic Quick Search Method
	 * @param quickSearchType QUICK_SEARCH_PACKAGES,QUICK_SEARCH_SYSTEMS,QUICK_SEARCH_ERRATA
	 * @param searchValue
	 * @param openAndLogin
	 * @param testResults TODO
	 * @return TODO
	 */
	public boolean quickSearch(int quickSearchType, String searchValue, boolean openAndLogin, boolean testResults);

	/**
	 * Gets System ID of a system's profile
	 * The ID is in different locations for satellite and hosted
	 * @param system
	 * @param hosted
	 * @param searchForSystem TODO
	 * @return
	 */
	public String getSystemID(String system,boolean hosted, boolean searchForSystem);

	/**
	 * Get System details from the SDC page
	 * IRhnBase.SYSTEM_DETAILS_(HOSTNAME,IP_ADDRES,KERNEL,SYSTEM_ID)
	 * @param system
	 * @param info
	 * @param searchForSystem
	 * @return
	 */
	public String getSystemInfo(String system, int info, boolean searchForSystem);

	//public void confirmSearchResult(String details, String system);

	/**
	 * Finds the total number of items in an RHN list
	 */
	public int totalInList();

	/**
	 * check the totol number of System links
	 * @return
	 */
	public int totalySystemsInList();

	/**
	 * Search for results, versioned due to pagination
	 * @param result
	 * @param totalInList
	 * @return TODO
	 */
	public boolean searchForResult(String result, int totalInList);


	/**
	 * Goes to all systems and filters by the specified system name
	 *
	 * @param system
	 * @return TODO
	 */
	public boolean goToSystem(String system);

	/**
	 * Goes to system SDC and checks for profile name present
	 * @param system
	 */
	public void goToSystemSDC(String system);

	/**
	 * Looks through a RHN list and paginates through the list
	 * as long as there are more pages.  This is a nice feature
	 * when search for software packages or Errata that might have various
	 * search results.
	 * @param result
	 * @return  boolean found true or false
	 */
	public boolean findSearchResult(String result);

	/**
	 * Performs an advanced search on Systems,Errata or Packages
	 * @param quickSearchType  ADVANCED_SEARCH_SYSTEMS: ADVANCED_SEARCH_ERRATA ADVANCED_SEARCH_PACKAGES
	 * @param fieldToSearch   IRhnBase field name
	 * @param searchValue     the search string you are looking for
	 * @param openAndLogin    boolean open the browser and login?
	 * @param testResults	  check for results?  you may say false and use another method to check results.
	 */
	public void advancedSearch(int quickSearchType, int fieldToSearch, String searchValue, boolean openAndLogin, boolean testResults);



}
