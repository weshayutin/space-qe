package com.redhat.rhn.harness.Sat42.pages;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.redhat.qe.auto.selenium.ExtendedSelenium;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.thoughtworks.selenium.SeleniumException;


/**
 * A Collection of common links and methods that are not specific to just one page or task in RHN
 * @author whayutin
 *
 */
//public class RhnCommon extends RhnPage implements com.redhat.rhn.harness.baseInterface.IRhnCommon {
public class RhnCommon extends SeleniumSetup implements com.redhat.rhn.harness.baseInterface.IRhnCommon {

	protected static ExtendedSelenium sel = null;
    protected String hostname;
    protected static RhnHelper rh = new RhnHelper();
	protected static Logger log = Logger.getLogger(RhnCommon.class.getName());
	
	
	
	/*@Override
	public String getLocation(){
        return HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_YOUR_RHN_PAGE;
    }*/


	public static void setSel(ExtendedSelenium sel){
		RhnCommon.sel = sel;
	}
	
   // public abstract String getLocation();

    /*
     *
     *
     */
	
	private String getSatelliteReleaseVersion(){
		String version = null;
		try{
			version = sel.getText("xpath=//a[contains(@href, '/rhn/help/release-notes/satellite/index.jsp')]");
		}
		catch(Exception e){
			log.info("RHN/Satellite release version not found, assuming 4.2");
			return "4.2";
		}
		return version;
	}
	
	public void openExternalLink(String link) {
		try{
			sel.open(link);
		} catch(Exception e){
			log.log(Level.WARNING, "Error opening external URL", e);
		}
	}
	
	
    public void OpenAndLogIn(){
    	
        page_YourRhn.openAndClosePopUps();
        log.info("Sat Release Version ="+getSatelliteReleaseVersion());
    	if(sel.isElementPresent("username")){
    		page_YourRhn.loginRHN();
    		//sel.waitForPageToLoad(HarnessConfiguration.TIMEOUT);
        }
    	else{
    		clickSignOut();
    		page_YourRhn.loginRHN();
    		//sel.waitForPageToLoad(HarnessConfiguration.TIMEOUT);
    	}
        sel.setSpeed(HarnessConfiguration.SELENIUM_SPEED);
    }

    public void JustOpen(){
    	
        page_Install.openAndClosePopUps();
        log.finer("Sat Release Version = "+getSatelliteReleaseVersion());
        if(rh.isTextPresent("Login:")){
        	log.finer("page ready");
        }
        sel.setSpeed(HarnessConfiguration.SELENIUM_SPEED);
    }


    public void OpenAndLogIn(String user,String password){
    	
        page_YourRhn.openAndClosePopUps();
        log.info("Sat Release Version = "+getSatelliteReleaseVersion());
        if(sel.isElementPresent("username")){
    		page_YourRhn.loginRHN(user,password);
    		//sel.waitForPageToLoad(HarnessConfiguration.TIMEOUT);
        }
    	else{
    		clickSignOut();
        	page_YourRhn.loginRHN(user,password);
        	//sel.waitForPageToLoad(HarnessConfiguration.TIMEOUT);
        }
        sel.setSpeed(HarnessConfiguration.SELENIUM_SPEED);
    }

    public void LogIn(String user,String password){
    	if(sel.isElementPresent("username")){
    		page_YourRhn.loginRHN(user,password);
    		//sel.waitForPageToLoad(HarnessConfiguration.TIMEOUT);
        }
    	else{
    		clickSignOut();
        	page_YourRhn.loginRHN(user,password);
        	//sel.waitForPageToLoad(HarnessConfiguration.TIMEOUT);
        }
        sel.waitForPageToLoad(HarnessConfiguration.TIMEOUT);

    }
    //TEST SYSTEMS
/*    public String getTestSystem01Name(){
    	return HarnessConfiguration.RHN_SYSTEM01;
    }

    public String getTestSystem02Name(){
    	return HarnessConfiguration.RHN_SYSTEM02;
    }

    public String getFakeTestSystem01Name(){
    	return HarnessConfiguration.RHN_FAKE01;
    }

    public String getFakeTestSystem02Name(){
    	return HarnessConfiguration.RHN_FAKE02;
    }*/
//  TEST SYSTEMS

    public void clickSignOut(){
    		rh.clickLink("link=Sign Out", true);
    }
    
    public void checkpage(){
    	rh.clickLink("link=Lock system","check Page",true);
    }
    
    public void SignOut(){
		clickSignOut();
    }
    
    public void clickSignOut01(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/Logout.do",true, "Logout");
    }

    public void clickYourRHN(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_YOUR_RHN_PAGE,true,"Your Rhn");
    }

    public void clickSideMenuSystems(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SYSTEMS_PAGE,true,"Systems");
    }

    public void clickErrata(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_ERRATA_PAGE,true,"Errata");
    }

	public void clickButton_SSM_Manage(){
        rh.clickLink("link=Manage", true);
    }

	public void clickButton_SSM_Clear(){
        rh.clickLink("link=Clear", true);
    }

    public void clickSystems(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SYSTEMS_PAGE,true,"Systems");
    }
    public void clickChannels(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_CHANNELS_PAGE,true,"Channels");
    }

    public void clickConfiguration(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_CONFIGURATION_PAGE,true,"Configuration");
    }

    public void clickSchedule(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SCHEDULE_PAGE,true,"Schedule");
    }

    public void clickUsers(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_USERS_PAGE,true,"Users");
    }
    
    public void clickSatelliteTools(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SATELLITE_TOOLS, true, "Satellite Tools");
    }

    public void clickLink_SystemName(String system){
		rh.clickLink("link="+system,true);
	}
    public void clickLink_YourAccount(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/account/UserDetails.do",true,"Your Account");
	}

    public void clickLink_GeneralLink(String link){
		rh.clickLink(link,true);
	}
    
    /**
     * This needs to be reimplemented, locator should find a checkbox/radio button w/ associated text
     */
    public void check_FirstItemInListSDC(){
    	rh.checkRadioButton("xpath=//html/body/div/div[2]/div/table/tbody/tr/td[2]/div[4]/form/table[2]/tbody/tr/td/input", true);
    	
    }
    
    /**
     * This needs to be reimplemented, locator should find a checkbox/radio button w/ associated text
     */
    public void check_FirstItemInList(){
    	if(rh.isElementPresent("name=items_selected", true))
    		rh.checkRadioButton("name=items_selected","first item in list",true);
    	else if(rh.isElementPresent("xpath=//input[@type='checkbox']", true))
    		rh.checkRadioButton("xpath=//input[@type='checkbox']", "first item in list", true);	
	}
    
    /**
     * This needs to be reimplemented, locator should find a checkbox/radio button w/ associated text
     */
    public void uncheck_FirstItemInList(){
    	if(rh.isElementPresent("name=items_selected", true))
    		rh.checkRadioButton("name=items_selected","first item in list",false);
    	else if(rh.isElementPresent("xpath=//input[@type='checkbox']", true))
    		rh.checkRadioButton("xpath=//input[@type='checkbox']", "first item in list", false);	
	}

   /* public void check_FirstItemInSDCList(){
		rh.checkRadioButton("name=list_104153177_1");
	}*/

    public void check_SelectAll_CheckBox(){
	rh.checkRadioButton("name=checkall",true);
    }
    
    public void check_SDC_SoftwareErrata_SelectAll_CheckBox(){
    	rh.checkRadioButton("xpath=//form/table[2]/thead/tr/th/input","Check Select All in the Page",true);
     }



    public String getCurrentUserId(){
    	String id = null;
    	System.out.println(sel.getValue("account"));

    	return id;
    }


    public void clickQuickSearchSubmit(){
        rh.clickImage("image-1","Search",true);
    }

    public void setQuickSearchText(String txt){
    	sel.setText("search_string", txt);
    }

    public void selectQuickSearchPackages(){
    	rh.selectComboBoxItem("search_type", "value=packages",false);
    }

    public void selectQuickSearchErrata(){
    	rh.selectComboBoxItem("search_type", "value=errata",false);
    }

    public void selectQuickSearchSystems(){
    	rh.selectComboBoxItem("search_type", "value=systems",false);
    }

    public void setTxt_FilterBy_notry(String txt){
    	sel.setText("xpath=//input[@name='filter_string']", txt);
    }

    public void setTxt_FilterBy(String txt){
    	rh.isTextPresent("Filter by System");
    	//rh.sleepForSeconds(3);
         if(rh.isElementPresent("xpath=//input[@name='filter_string']", true)){
    		sel.setText("xpath=//input[@name='filter_string']", "Filter by", txt);
    	}
        else if(rh.isElementPresent("xpath=//input[@name='filter_value']", true)){
     		sel.setText("xpath=//input[@name='filter_value']", "Filter by", txt);
     	}
        else if(rh.isElementPresent("xpath=//table/tbody/tr/td/input[2]", true)){
    		sel.setText("xpath=//table/tbody/tr/td/input[2]", "Filter by", txt);
    	}
    	else if(rh.isElementPresent("xpath=//input[@size='10']", true)){
    		sel.setText("xpath=//input[@size='10']", "Filter by", txt);
    	}
    	else if(rh.isElementPresent("xpath=//input[@name='filter_string']", true)){
    		sel.setText("xpath=//input[@name='filter_string']", "Filter by", txt);
    	}
    	else if(rh.isTextPresent("No systems.")){
    		log.info("No systems found, so there is no filter box");
			log.info("filter box was not found, check RhnCommon.java, setTxt_FilterBy");
			
    	}
    	else{
    		throw new SeleniumException("txt box not found");
    	}
    	
    	
    	
    	/*try{
    		log.fine("first try, list_1680466951_filterval");
    		//sel.setText("xpath=//input[@size='10']","Filter By", txt);
    		// sel.setText("xpath=//table/tbody/tr/td/input[2]","Filter By", txt);
    		 sel.setText("xpath=//input[@name='list_1680466951_filterval']","Filter By", txt);
    		//sel.typeKeys("xpath=//table/tbody/tr/td/input[2]", txt);
    	}
    	catch(Exception SeleniumException ){
    		try{
    			log.fine("second try, xpath=//input[@name='filter_value']");
    		sel.setText("xpath=//input[@name='filter_value']","Filter By", txt);
    		}
    		catch(SeleniumException nfe){
    			try{
    				log.fine("third try, xpath=//input[@size='10'] ");
    				//sel.setText("xpath=//input[@size='10']","Filter By", txt);
    				sel.setText("xpath=//table/tbody/tr/td/input[2]","Filter By", txt);
    			}
    			catch(Exception ee){
    				sel.setText("xpath=//input[@name='filter_string']","Filter By", txt);
    			if(rh.isTextPresent("No systems."))
    				log.info("No systems found, so there is no filter box");
    				log.info("filter box was not found, check RhnCommon.java, setTxt_FilterBy");
    				throw new SeleniumException(SeleniumException);
    			}
*/
    	
	}

    public void setTxt_FilterByUserName(String txt){
		//sel.setText("xpath=//input[@name=filter_string]", txt);
    	sel.setText("name=filter_string", txt);
	}



    public void clickButton_Filter_Go(){
    	if(rh.isElementPresent("xpath=//input[@type='image' and @value='filter']",false))
    		rh.clickImage("xpath=//input[@type='image' and @value='filter']","Go",true);
    	if(rh.isElementPresent("xpath=//input[@type='submit' and @value='Go']",false))
    		rh.clickImage("xpath=//input[@type='submit' and @value='Go']","Go",true);
    	if(rh.isElementPresent("xpath=//input[@type='image' and @alt='Filter']",false))
    			rh.clickImage("xpath=//input[@type='image' and @alt='Filter']","Go",true);
	}



    public void clickButton_PaginateNext(){
    	try{
    	rh.clickLink("name=Next", true);
    	}
    	catch(SeleniumException se){
    		rh.clickLink("name=xpath//html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/div[2]/table/tbody/tr/td[2]/input", true);
    	}
    }

    public void clickButton_PaginateLast(){
    	rh.clickLink("name=Last", true);
    }

    public void clickButton_PaginateFirst(){
    	rh.clickLink("name=First", true);
    }

    public void clickButton_PaginatePrevious(){
    	rh.clickLink("name=Prev", true);
    }

    public void clickLink_AllSystems(){
		   rh.clickLink("link=All", true);
	   }
    
    public void clickButton_Update(){
    	rh.clickButton("xpath=//input[@type='submit' and @value='Update']",true);
    }
    
    public void clickButton_UpdateList(){
    	rh.clickButton("xpath=//input[@type='submit' and @value='Update List']",true);
    }
    
    public void clickButton_SelectAll(){
    	rh.clickButton("xpath=//input[@type='submit' and @value='Select All']",true);
    }
    
    public String clickButton_SelectAll(boolean txt){
    	//oh thats ugly :)
    	if(txt){}
    	return "xpath=//input[@type='submit' and @value='Select All']";
    }
    
    public void clickButton_SelectAll_img(){
    	rh.clickImage("alt=Select All", "Select All", true);
    }

    public void clickButton_Confirm(){
    	rh.clickButton("xpath=//input[@type='submit' and @value='Confirm']",true);
    }
    public boolean existsButton_Confirm(){
    	return rh.isElementPresent("xpath=//input[@type='submit' and @value='Confirm']",true);
    }



    public void clickButton_Continue(){
    	rh.clickButton("xpath=//input[@type='submit' and @value='Continue']",true);
    }

    public void clickButton_Submit(){
    	rh.clickButton("xpath=//input[@type='submit' and @value='Submit']",true);
    }

    public void clickButton_UnselectAll(){
    	rh.clickButton("xpath=//input[@type='submit' and @value='Unselect All']",true);
    }

   public String XPATH_SEARCH_RESULT_TOTAL="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table[4]/tbody/tr/td[2]/strong[3]";
   public String XPATH_SEARCH_RESULT_TOTAL2="xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table[3]/tbody/tr/td[2]/strong[3]";




   // return selenium locators  Not sure where to put this.  I would like to see it in a class called GuiLocators or something like that. (7/9/09 jsefler)
   public String checkboxInTableRowContainingText(String text) {
	   return "//tr/td[normalize-space(.)='"+text+"']/..//input[@type='checkbox']";
   }


}
