package com.redhat.rhn.harness.baseInterface;

public interface IRhnCommon {
	
	
    public static final String XPATH_SEARCH_RESULT_TOTAL2_SAT_5="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table[3]/tbody/tr/td[2]/strong[3]";
	
    
    //public static final String XPATH_SEARCH_RESULT_TOTAL_SAT_51="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/div/table/tbody/tr/td[2]/strong[3]";
    //public static final String XPATH_SEARCH_RESULT_TOTAL_SAT_51="xpath=//table/tbody/tr/td[2]/strong[3]";
    public static final String XPATH_SEARCH_RESULT_TOTAL_SAT_51="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table/tbody/tr/td[2]/strong[3]";
    public static final String XPATH_SEARCH_RESULT_TOTAL_SAT_42="xpath=//html/body/div/div[4]/table/tbody/tr/td[2]/form[2]/table/tbody/tr/td[2]/strong[3]";
    //in sat 42 if the search results only begin w/ one letter the alphebet directory is not displayed
    public static final String XPATH_SEARCH_RESULT_TOTAL_SAT_42_b="xpath=//html/body/div/div[4]/table/tbody/tr/td[2]/form/table/tbody/tr/td[2]/strong[3]";
	/**
	 * Can only be called once during a test, unless browser is closed
	 * Opens a browser and logs into rhn with the credentials specified 
	 * in the localhost properties file
	 **/
	public void OpenAndLogIn();
	
	/**
	 * Can only be called onced during a test, unless the browser is closed
	 * Opens a browser and logs into rhn is the user and passwd specified
	 */
	public void OpenAndLogIn(String user,String password);
	
	/**
	 * Does not login.. but opens inititial page.
	 * Used for the initial login of the satellite when no
	 * admin user has been created yet.
	 */
	public void JustOpen();
	
	/**
	 * Logs into rhn w/ the specified user and passwd
	 * @param user
	 * @param password
	 */
	public void LogIn(String user,String password);
	
	/**
	 * Clicks the link "Sign Out"
	 */
	public void clickSignOut();
	
	/**
	 * Opens the logout.do page
	 */
	public void clickSignOut01();
	
	/**
	 * Clicks the systems link in the menu on the left side of rhn
	 */
	public void clickSideMenuSystems();
	
	/**
	 * Clicks the main Errata link 
	 */
	public void clickErrata();
	
	/**
	 * Clicks the main Systems link
	 */
	public void clickSystems();
	
	/**
	 * Clicks the main Channels link
	 */
	public void clickChannels();
	
	/**
	 * Clicks the main Configuration link
	 */
	public void clickConfiguration();
	
	/**
	 * Clicks the main Schedule link
	 */
	public void clickSchedule();
	
	/**
	 * Clicks the main Users link
	 */
	public void clickUsers();
	
	/**
	 * Finds and clicks a link w/ the specified system name
	 * @param system
	 */
	public void clickLink_SystemName(String system);
	
	/**
	 * A Generic method to click one off links
	 * Example rc.clickLink_GeneralLink("weirdFooName"); 
	 * Example rc.clickLink.GeneralLink("032323224423");
	 * @param link
	 */
	public void clickLink_GeneralLink(String link);
	
	/**
	 * Checks the first item in a list
	 * This is useful after filtering a list and one items is left in the list
	 */
	public void check_FirstItemInList();
	
	/**
	 * Selects all items on a given page, does NOT select all items
	 * Checks the select all check box
	 * 
	 */
	public void check_SelectAll_CheckBox();
	
	public String getCurrentUserId();
	
	/**
	 * Clicks the Search submit button
	 */
	public void clickQuickSearchSubmit();
	
	/**
	 * Sets a value for quick search
	 * @param txt
	 */
	public void setQuickSearchText(String txt);
	
	/**
	 * Selects packages quick search
	 */
	public void selectQuickSearchPackages();
	
	/**
	 * Selects errata quick search
	 */
	public void selectQuickSearchErrata();
	
	/**
	 * Selects system quick search
	 */
	public void selectQuickSearchSystems();
	
	/**
	 * Sets the value for filtering
	 * @param txt
	 */
	public void setTxt_FilterBy(String txt);
	
	/**
	 * For non admim users, filters by user in the Users Page
	 * @param txt
	 */
	public void setTxt_FilterByUserName(String txt);
	
	/**
	 * Clicks the "Filter" "Go" submit button
	 */
	public void clickButton_Filter_Go();
	
	/**
	 * In rhn lists, clicks next page
	 */
	public void clickButton_PaginateNext();
	
	/**
	 * In rhn lists, clicks last page
	 */
	public void clickButton_PaginateLast();
	
	/**
	 * In rhn lists, clicks first page
	 */
	public void clickButton_PaginateFirst();
	
	/**
	 * In rhn lists, clicks previous page
	 */
	public void clickButton_PaginatePrevious();
	
	/**
	 * Clicks the "All Systems" link on the left menu
	 */
	public void clickLink_AllSystems();
	
	/**
	 * Clicks button update list
	 */
	public void clickButton_UpdateList();
	
	/**
	 * Clicks the select all button, and really selects all items in the list
	 */
	public void clickButton_SelectAll();
	
	/**
	 * Unselects all items from a list
	 */
	public void clickButton_UnselectAll();
	
	 public void checkpage();

}
