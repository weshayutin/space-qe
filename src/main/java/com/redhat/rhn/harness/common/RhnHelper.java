package com.redhat.rhn.harness.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.redhat.qe.auto.selenium.ExtendedSelenium;
import com.redhat.qe.auto.selenium.MyLevel;
import com.redhat.rhn.harness.Sat42.pages.SatelliteSystemsPage;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.thoughtworks.selenium.SeleniumException;


//public class RhnHelper extends gis.harness.common.SeleniumSetup{
public class RhnHelper extends SeleniumSetup {
	public String testName = null;

	protected static Logger log = Logger.getLogger(RhnHelper.class.getName());

		 
		 //ExtendedSelenium.getInstance();

	public static ExtendedSelenium sel = null;

	public static void setSel(ExtendedSelenium sel){
		RhnHelper.sel = sel;
	}


	public int getNumberOfPopUps(){
		String s = HarnessConfiguration.NUMBEROFPOP;
		int i = Integer.parseInt(s.trim());
		return i;
	}

    //TEST SYSTEMS
    public String getTestSystem01Name(){
    	return HarnessConfiguration.RHN_SYSTEM01;
    }

    public String getTestSystem02Name(){
    	return HarnessConfiguration.RHN_SYSTEM02;
    }

    /**
     * Waits for the page to finsish loading.
     * 99% you do NOT need to use this method
     * So do NOT use it unless you are sure you need it.
     * @param timeout
     */
    /**
     * wait for the page to finish loading
     * This is built into selenium and our helper classes so 
     * do NOT use this unless you know you need it
     */
    public void waitForPageToLoad(String timeout){
    	try{
    		sel.waitForPageToLoad(timeout);
    	}
    	catch(SeleniumException se){
    		log.info("DEBUG in second timeout");
    		sel.waitForPageToLoad(timeout);
    	}
    }
    
    /**
     * Stops the selenium instance, and will stop a test
     */
    public void stopSelenium(){
    	log.info("sel instance = " + sel);
		sel = ExtendedSelenium.getInstance();
		sel.stop();
		//ExtendedSelenium.killInstance();
    }
    
    
	/**
	 * When a page has a column of checkboxes, in order to interact with them
	 * you need to use the text that appears next to the checkbox.  This method
	 * generates a locator for that checkbox.
	 * @param text The text next to the checkbox you want to locate.
	 * @return a locator for the checkbox.
	 */
	public String checkboxNextToText(String text){
		return  "//tr[td[starts-with(normalize-space(.),'" + text + "')]]//input[@type='checkbox']";
	}
	
	public String imageNextToText(String text, String imageURLPartial){
		return  "//tr[td[starts-with(normalize-space(.),'" + text + "')]]//img[contains(@src,'" + imageURLPartial + "')]";
	}
	
	public String textboxNextToText(String text){
		return  "//tr[td[starts-with(normalize-space(.),'" + text + "')]]//*[self::input[@type='text' or @type='password'] or self::textarea]";
	} 
	
	public String radioNextToLabel(String label){
		return "//input[@type='radio' and following-sibling::label[normalize-space(.)='" + label + "']]";

	}
	
	public String linkNextToText(String text, String linkText){
		return  "//tr[td[starts-with(normalize-space(.),'" + text + "')]]//a[normalize-space(.)='" + linkText + "']";
	}
	
	public String linkInResourceBreadcrumbTrail(String resourceName, String breadcrumbLink){
		return  "//span[normalize-space(.)='"+resourceName+"']/../a[normalize-space(.)='"+breadcrumbLink+"']";
	}

    /**
     * Clicks an html link
     * @param link
     * @param waitForPage
     * 
     * Example: "link=mylink"
     * Try to use clickLink(String,String,boolean) instead
     */
	public void clickLink(String link, boolean waitForPage){
		try{
		//sel.highlight(link); already highlighted by next call to click(...)
		sel.click(link);}
		catch(SeleniumException se){
			//sel.highlight("link="+link); already highlighted by next call to click(...)
			if(!link.startsWith("link=")){
				log.finer("link '"+ link+"', not found, trying link="+link);
				sel.click("link="+link);
			}
		}
		if (waitForPage) waitForPageToLoad(HarnessConfiguration.TIMEOUT);
	}

	/**
	 * Clicks a html link
	 * @param link
	 * @param linkName, the name that will be logged
	 * @param waitForPage
	 * 
	 * Example: rh.clickLink("xpath=//a[contains(@href, '/rhn/systems/ssm/config/Deploy.do')]", "Deploy",true);
	 */
	public void clickLink(String link,String linkName, boolean waitForPage){
		//sel.highlight(link); already highlighted by next call to click(...)
		sel.click(link,linkName);
		link=link.replace("link=", "");
		//log.info("Click Link: '"+link);
		if (waitForPage) waitForPageToLoad(HarnessConfiguration.TIMEOUT);
	}

	/**
	 * Click the link for a system profile, bit of a shortcut
	 * @param system
	 * 
	 * Example clickLink("rlx-2-04.rhndev.redhat.com");
	 */
	public void clickSystemProfileLink(String system){
		//sel.highlight("link="+system);
		clickLink("link="+system, true);
	}
	
	/**
	 * Open a partial URL link
	 * This prepends the http:// or https:// and the server name
	 * @param link
	 * @param waitForPage
	 * @param linkName
	 * 
	 * Example: /rnn/Monitoring.do
	 */
	public void openLink(String link, boolean waitForPage,String linkName){
		sel.open(HarnessConfiguration.PROTOCOL+link);
		//log.log(MyLevel.ACTION, "Opened link: " + link);
		//as of rc.9.2 the open command has a built in wait
		//log.info("Open Link: '"+linkName);
		//if(waitForPage)
       // waitForPageToLoad(HarnessConfiguration.TIMEOUT);
	}

	/**
	 * Open a complete URL
	 * @param link
	 * @param waitForPage
	 * 
	 * Example: http://server/link
	 */
	public void openCompleteLink(String link, boolean waitForPage){
		sel.open(link);
		//as of rc.9.2 the open command has a built in wait
		//log.info("Open Link: '"+link);
		//if(waitForPage)
       // waitForPageToLoad(HarnessConfiguration.TIMEOUT);
	}

	/**
	 * Returns the displayed txt of a particular link
	 * @param link
	 * @return
	 */
	public String getText(String link){
		//log.info("Get Text: "+link);
		sel.highlight(link);
		return sel.getText(link);
	}
	
	/**
	 * Click a button
	 * @param link
	 * @param waitForPage
	 * 
	 * Example: rh.clickButton("xpath=//input[@type='submit' and @value='Add Packages']",true);
	 * Use clickButton(String,String,boolean) instead
	 */
	public void clickButton(String link, boolean waitForPage){
		//sel.highlight(link);
		sel.click(link);
		//String string = link;
		//string=string.replace("xpath=//input[@type='submit' and @value='", "");
		if(waitForPage)
        waitForPageToLoad(HarnessConfiguration.TIMEOUT);
		//log.log(MyLevel.ACTION, "Clicked button: " + link);
	}
	
	/**
	 * Click a button
	 * @param link
	 * @param waitForPage
	 * 
	 * Example: rh.clickButton("xpath=//input[@type='submit' and @value='Add Packages']","Add Packages",true);
	 */
	public void clickButton(String link,String humanReadableName, boolean waitForPage){
		//sel.highlight(link);
		sel.click(link);
		//String string = link;
		//string=string.replace("xpath=//input[@type='submit' and @value='", "");
		if(waitForPage)
        waitForPageToLoad(HarnessConfiguration.TIMEOUT);
		log.log(MyLevel.ACTION, "Clicked button: " + link);
	}
		
		/**
		 * Clicks an HTML image
		 * @param locator
		 * @param humanReadableName
		 * @param waitForPage
		 * 
		 * Example: rh.clickImage("xpath=//input[@type='image' and @value='filter']","Go",true);
		 */
		public void clickImage(String locator,String humanReadableName, boolean waitForPage){
			//sel.highlight(locator); already highlighted by next call to click(...)
			sel.click(locator,humanReadableName);
			//String string = locator;
			//string=string.replace("xpath=//input[@type='image' and @value=", "");
			//log.info("Clicked Image: '"+string+"'");
			if(waitForPage)
		        waitForPageToLoad(HarnessConfiguration.TIMEOUT);
			log.log(MyLevel.ACTION, "Clicked image: " + locator);
		}
	/**
	 * Check or unchech a checkbox item
	 * @param locator
	 * @param check
	 * 
	 * Example: rh.checkRadioButton("name=items_selected",false);
	 */
	public void checkRadioButton(String locator,boolean check){
		if(check){
			sel.check(locator);
			log.log(MyLevel.ACTION, "Checked radio button: " + locator);
		}
		else{
			sel.uncheck(locator);
			log.log(MyLevel.ACTION, "Unchecked radio button: " + locator);
		}
	}
	
	/**
	 * Check or uncheck a checkbox item
	 * @param locator
	 * @param check
	 * 
	 * Example: rh.checkRadioButton("name=items_selected","config file list",false);
	 */
	public void checkRadioButton(String locator,String humanReadableName, boolean check){
		if(check){
			sel.check(locator);
			//log.fine("Checked: '"+humanReadableName+"'");
			log.log(MyLevel.ACTION, "Checked: '"+humanReadableName+"'");
		}
		else{
			sel.uncheck(locator);
			//log.fine("Unchecked: '"+humanReadableName+"'");
			log.log(MyLevel.ACTION, "Unchecked: '"+humanReadableName+"'");
		}
	}
	
	/**
	 * Selects an item from a combox box
	 * @param comboBox
	 * @param value
	 * @param waitForPage
	 * 
	 * If the given item is already selected, we will not reselect
	 * Please be very carefull about setting the wait for page, only set to yes 
	 * if you know the selection will refresh the page
	 * 
	 * Example rh.selectComboBoxItem("token_base_channel", item, false);
	 * Please use String comboBox,String humanReadableName, String value, boolean waitForPage
	 */
	public void selectComboBoxItem(String comboBox, String value, boolean waitForPage){
		//this can be used for value or label
		//if(sel.getSelectedLabel(comboBox).compareTo(value) != 0){
		//if(!sel.getSelectedLabel(comboBox).toLowerCase().trim().contains(value.toLowerCase().trim())){
		String selection = new String();
		try {
			selection = sel.getSelectedLabel(comboBox).toLowerCase().trim();
		} 
		catch (SeleniumException se) {
			selection = " ";
		}
		
		if(!selection.contentEquals(value.toLowerCase().trim())){	
			//sel.highlight(comboBox);  already highlighted by next call to select(...)
			sel.select(comboBox, value);
			if(waitForPage) {
				waitForPageToLoad(HarnessConfiguration.TIMEOUT);
			}
		}
		else {
			log.info("Selection from "+ comboBox +": '"+ value +"' is already selected");
		}
	}	
	
	public String getSelectedLabelFromComboBox(String comboBox){
		sel.highlight(comboBox);
		return sel.getSelectedLabel(comboBox);
	}
	
	/**
	 * Selects an item from a combox box
	 * @param comboBox
	 * @param humanReadableName  name used for logging
	 * @param value
	 * @param waitForPage
	 * 
	 * If the given item is already selected, we will not reselect
	 * Please be very carefull about setting the wait for page, only set to yes 
	 * if you know the selection will refresh the page
	 * 
	 * Example rh.selectComboBoxItem("token_base_channel", item, false);
	 * Please use String comboBox,String humanReadableName, String value, boolean waitForPage
	 */
	public void selectComboBoxItem(String comboBox,String humanReadableName, String value, boolean waitForPage){
		//this can be used for value or label
		if(!sel.getSelectedLabel(comboBox).toLowerCase().trim().contains(value.toLowerCase().trim())){
			// sel.highlight(comboBox);  already highlighted by next call to select(...)
			sel.select(comboBox, value);
			/*String stringCombo = comboBox;
			String stringValue = value;
			stringValue=stringValue.replace("value=", "");
			log.info("Selected the value: '"+ humanReadableName +"' from combox box: '" + stringCombo+"'");*/
			if(waitForPage)
				waitForPageToLoad(HarnessConfiguration.TIMEOUT);
		}
		else
			log.info("Value ("+value+") already selected in ComboBox, not selecting");
	}

	public String[] getAllComboBoxLabels(String comboBox){
		ArrayList<String> labels = new ArrayList<String>();
		sel.highlight(comboBox);
		return (String[])labels.toArray();
	}
	
	public void selectBoxItem(String txtBox, String value, boolean waitForPage){
		//sel.highlight(txtBox);  already highlighted by next call to select(...)
		sel.select(txtBox, value);
		/*String stringCombo = txtBox;
		String stringValue = value;
		stringValue=stringValue.replace("value=", "");
		log.info("Selected the value: '"+ stringValue +"' from combox box: '" + stringCombo+"'");*/
		if(waitForPage)
        waitForPageToLoad(HarnessConfiguration.TIMEOUT);
	}


	public boolean isElementPresent(String element, boolean logResult){
		Level level = Level.FINER;
		if (logResult) level = Level.INFO;
		if(sel.isElementPresent(element,level)){
			sel.highlight(element);
			return true;
		}
		else{
			return false;
		}
	}

// Create a txt present for blue sat info boxes
	public boolean isTextPresent(String txt){
		if(sel.isTextPresent(txt)){
		log.info("Found text: '"+txt+"'");
		//sel.highlight(txt);
		return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isTextPresent(String txt,boolean logResults){
		if(sel.isTextPresent(txt, logResults? Level.INFO : Level.FINER)){
			return true;
		}
		else{
			return false;
		}
	}


	public boolean isTextNotPresent(String txt){
		if(sel.isTextPresent(txt)){
		return false;
		}
		else{
			return true;
		}
	}

	public int isTextPresentSpecial(String txt){
		int result = 4;
		try{
			if(sel.isTextPresent(txt)){
			result = 0;
			return result;
			}
			else{
				result = 1;
				return result;
			}
		} catch(SeleniumException se){
			log.info("is test present caught a null expecption");
			result = 3;
			return result;
		}
	}

	public String getTxt_AlertBanner(){
		return sel.getText("xpath=//div[@class='site-info']");
	}

	//@Deprecated
	private int[] getBounds(String tableID){
		int[] xyBounds = new int[2];
		String bigAssString;
		//Get X bounds
		for(xyBounds[0]=1;true;xyBounds[0]++){
			try{
				bigAssString = tableID+"/tbody/tr[1]/td["+String.valueOf(xyBounds[0])+"]";
				sel.getText(bigAssString);
			}
			catch (SeleniumException e){
				break;
			}
		}
		//Get Y bounds
		for(xyBounds[1]=1;true;xyBounds[1]++){
			try{
				sel.getText(tableID+"/tbody/tr["+String.valueOf(xyBounds[1])+"]/td[1]");
			}
			catch (SeleniumException e){
				break;
			}
		}
		return xyBounds;
	}

	public String[] getLineofCells(String tableID,
								   int columnOrRowNumber,
								   boolean isRow){
		LinkedList<String> cellList = new LinkedList<String>();
		for(int i=1;true;i++){
			try{
				if(isRow)
					cellList.add(sel.getText(tableID+"/tbody/tr["+String.valueOf(columnOrRowNumber)+"]/td["+String.valueOf(i)+"]").trim());
				else
					cellList.add(sel.getText(tableID+"/tbody/tr["+String.valueOf(i)+"]/td["+String.valueOf(columnOrRowNumber)+"]").trim());
			}
			catch (SeleniumException e){
				return cellList.toArray(new String[cellList.size()]);
			}
		}
	}

	/**
	 * Returns a String representing the contents of the selected column (td)
	 * of row of a specified table which contains the specified search value.
	 *
	 * @param tableID Table Locator string representing table identifier
	 * @param searchVal String representing value to search for in each row
	 * @param tdNum integer representing column number
	 * @return selected td contents
	 */
	
	public String getTableData(String tableID, String searchVal, int tdNum){
		String compVal,retVal;
		int[] myBounds = this.getBounds(tableID);
		log.info("getTableData("+ tableID + ", " + searchVal + ", " + tdNum + 
				":  bounds = {" + myBounds[0] + ", " + myBounds[1] + "}");
		for(int y=1;y<myBounds[1];y++){
				for(int x=1;x<myBounds[0];x++){
					String xpath = tableID+"//tr["+y+"]/td["+x+"]";
					compVal = sel.getText(xpath);
					log.info("sel.getText(" + xpath + ") = " + compVal);
					if(compVal.contains(searchVal)){
						xpath = tableID+"//tr["+y+"]/td["+tdNum+"]";
						retVal = sel.getText(xpath);
						log.info("sel.getText(" + xpath + ") = " + retVal);
						return retVal;
					}
				}
		}
		log.fine("Looked for: '"+searchVal+"' in list but didn't find it.");
		return "";
	}

	//@Deprecated
	public String getTableDataPlus(String tableID, String[] searchVal, int tdNum){
		String compVal,retVal;
		int[] myBounds = this.getBounds(tableID);
		for(int y=1;y<myBounds[1];y++){
				int x;
				int i=0;
				for(x=1;x<myBounds[0];x++){
					compVal = sel.getText(tableID+"//tr["+y+"]/td["+x+"]");
					for(int j=0;j<searchVal.length;j++)
						if(compVal.contains(searchVal[j]))
							i++;
				}
				if(i==searchVal.length){
					retVal = sel.getText(tableID+"//tr["+y+"]/td["+tdNum+"]");
					return retVal;
				}
		}
		log.fine("Looked for: '"+searchVal+"' in list but didn't find it.");
		return "";
	}

	public String getKSTableData(String tableID,
			                     String[] searchVal,
			                     int tdNum,
			                     SatelliteSystemsPage syspage,
			                     String kslabel){
		String compVal,retVal;
		int[] myBounds = this.getBounds(tableID);
		for(int y=1;y<myBounds[1];y++){
				int x;
				int i=0;
				for(x=1;x<myBounds[0];x++){
					compVal = sel.getText(tableID+"//tr["+y+"]/td["+x+"]");
					for(int j=0;j<searchVal.length;j++)
						if(compVal.contains(searchVal[j]))
							i++;
				}
				if(i==searchVal.length){
					sel.click(tableID+"//tr["+y+"]/td["+tdNum+"]/a");
					waitForPageToLoad(HarnessConfiguration.TIMEOUT);
					if(sel.isTextPresent(kslabel)){
					    retVal = sel.getText("xpath=//div/div[5]/table/tbody/tr/td[2]/table[2]/tbody/tr/td/table/tbody/tr[2]/td");
					    return retVal;
					}
					syspage.open();
					syspage.clickLink_Kickstart();
				}
		}
		log.fine("Looked for: '"+searchVal+"' in list but didn't find it.");
		return "";
	}

	/**
	 * Selects a checkbox using its xpath location
	 *
	 * @param xpath xpath location of the checkbox
	 */
	public void selectItem(String name,String xpath){
		sel.highlight(xpath);
		sel.check(xpath);
		log.info("Selected Check Box: '"+name+"'");
	}




	/**
	 * Selects a checkbox on the same line as the searchVal
	 *
	 * @param tableID Table Locator string representing table identifier
	 * @param searchVal String representing value to search for in each row
	 */
	public void selectItemInRow(String tableID, String searchVal){
		String compVal = "";
		int[] myBounds = this.getBounds(tableID);
		for(int y=1;y<myBounds[1];y++){
				for(int x=1;x<myBounds[0];x++){
					compVal = sel.getText(tableID+"//tr["+y+"]/td["+x+"]");
					if(compVal.contains(searchVal)){
						sel.highlight(tableID+"//tr["+y+"]/td[1]/input[@type='checkbox']");
						sel.check(tableID+"//tr["+y+"]/td[1]/input[@type='checkbox']");
						log.fine(" Found: '"+searchVal+"' in list.");
						return;
					}
				}
		}
		log.fine(" Looked for: '"+searchVal+"' in list but didn't find it.");
	}

	public void clickLink_InRow(String tableID, String searchVal, int column){
		String compVal, txt;
		int[] myBounds = this.getBounds(tableID);
		for(int y=1;y<myBounds[1];y++){
				for(int x=1;x<myBounds[0];x++){
					compVal = sel.getText(tableID+"//tr["+y+"]/td["+x+"]");
					if(compVal.contains(searchVal)){
						txt=(sel.getText(tableID+"//tr["+y+"]/td["+column+"]/a"));
						sel.highlight(tableID+"//tr["+y+"]/td["+column+"]/a");
						sel.click(tableID+"//tr["+y+"]/td["+column+"]/a");
						log.info("Click Link: '"+txt+ " for system "+compVal+"'");
						waitForPageToLoad(HarnessConfiguration.TIMEOUT);
						return; //not sure why its not exiting here.. but its not.
					}
				}
		}
		log.fine("Looked for: '"+searchVal+"' in list but didn't find it.");
	}


	public String getTotalItemsInList(String xpath){
		String totalItems;
		totalItems ="";
		totalItems = sel.getText(xpath);
		//System.out.println("DEBUG: total Items = "+totalItems);
		return totalItems;
	}


	@DeleteMe
	public void goBack(){
		sel.goBack();
		waitForPageToLoad(HarnessConfiguration.TIMEOUT);
	}

	/*public void beginTest(){
		String methodName = null;
		String className = null;

		methodName = new Throwable().fillInStackTrace().getStackTrace()[1].getMethodName();
		className = new Throwable().fillInStackTrace().getStackTrace()[1].getClassName();

		try{
			BufferedWriter out = new BufferedWriter(new FileWriter("/tmp/testname"));
			BufferedWriter out1 = new BufferedWriter(new FileWriter("/tmp/class"));
	        out.write(methodName);
	        out.close();
	        out1.write(className);
	        out1.close();
	    } catch (IOException e) {
	    }


		log.info("==================== BEGIN TEST:   "+methodName+"  "+className+ " ================ <ol>");
	}*/






	public int checkForErrors(){
		int result = 0;

		if(isTextPresent("Internal Server Error")){
			 result = IRhnBase.ERROR_ISE;
		}

		if(isTextPresent("Page Not Found")){
			result = IRhnBase.ERROR_PageNotFound;
		}
		
		//RED ALERTS From Satellite can be found w/ 
		//if <div class="local-alert"> <ul> <li> message 
		

		if(result == 0){
			result = IRhnBase.ERROR_NO_ERROR_FOUND;
		}

			return result;

	}

	public String getTime(){
		DateFormat fmt = new SimpleDateFormat("MMdd-HH:mm:ss:SSS");
		   String now = fmt.format(new Date());
		   return now;
	}

	public boolean removeAllProfiles(){
		boolean remove = false;
		if(HarnessConfiguration.RHN_REMOVE_PROFILES.equals("true")){
			remove = true;
			log.info("debug remove = true");
		}
		return remove;
	}


	/**
	 * waitFor can be used to make selenium wait for a link to appear.
	 * This will normally be used for long page rendering times.
	 *
	 * This particular method only looks for a html link
	 * @param element
	 * @param seconds
	 */
	public void waitFor(String element, int seconds){
		int period = seconds * 1000;
		String waitPeriod = String.valueOf(period);
		sel.waitForCondition("selenium.isElementPresent(\"link="+element+"\");", waitPeriod);
	}


	public void waitForStatus(String status, String link, boolean history, String system, int sleepInterval){
		
		int x = 0;
		if(!history){
			page_Schedule.clickLink_Details();
	   	 	page_SDC.clickLink_Events();
	   	 	
			while(!sel.isTextPresent("No pending events")){
					
					isTextPresent(link);
					page_RhnCommon.clickLink_GeneralLink(link);
					 if(sel.isTextPresent("Queued"))
						 log.info("The requested action is queued");
		    		 if(sel.isTextPresent("Picked Up."))
		    			 log.info("The requested action is running");
		    		 if(sel.isTextPresent("Completed."))
		    			 log.info("The requested action is Completed");
		    		 if(sel.isTextPresent("Failed")){
		    			 	log.info("The requested action has failed");
				    		 return;
			    		 }
		    		 task_TestPrep.command_tailLog(system, "/var/log/up2date");
		    		 task_TestPrep.sleepForSeconds(sleepInterval);
		    		 page_Schedule.clickLink_Details();
		    	   	 page_SDC.clickLink_Events();
		    	     x++;
		    	   	 log.info("attemted "+ x + " times out of 30 possible attempts");
		    	   	 if(x > 30){
		    	   		 log.info("Checked progress 30 times, w/o success.. bailing out");
		    	   		 return;
		    	   	 }

				}
			}
			if(history){

				page_Schedule.clickLink_Details();
		   	 	page_SDC.clickLink_Events();

				   	 while(!sel.isTextPresent("No pending events")){
				   		 if(isTextPresent(link)){
							page_RhnCommon.clickLink_GeneralLink(link);
							 if(sel.isTextPresent("Queued"))
								 log.info("The requested action is queued");
				    		 if(sel.isTextPresent("Picked Up."))
					    		 log.info("The requested action is running");
				    		 if(sel.isTextPresent("Completed."))
				    			 log.info("The requested action is Completed");
				    		 if(sel.isTextPresent("Failed")){
					    			 log.info("The pending action has failed");
						    		 return;
					    		 }
				   		 }
				   		 	 task_TestPrep.command_tailLog(system, "/var/log/up2date");
				   		     task_TestPrep.sleepForSeconds(sleepInterval);
				    		 page_Schedule.clickLink_Details();
				    	   	 page_SDC.clickLink_Events();
				    	   	 x++;
				    	     log.info("attemted "+ x + "times out of 30 possible attempts");
				    	   	 if(x > 30){
				    	   		 log.info("Checked progress 30 times, w/o success.. bailing out");
				    	   		 break;
				    	   	 }
				   	 }
				   	 			page_SDC.clickLink_History();
				   	 			isTextPresent(link);
								page_RhnCommon.clickLink_GeneralLink(link);
								while(!sel.isTextPresent("Completed.")){
								 if(sel.isTextPresent("Queued"))
									 log.info("The requested action is queued");
					    		 if(sel.isTextPresent("Picked Up."))
						    		 log.info("The requested action is running");
					    		 if(sel.isTextPresent("Completed.")){
					    			 log.info("The requested action is Completed");
					    		 }

					    		 if(sel.isTextPresent("Failed")){
						    			 log.info("The requested action has failed");
						    			 return;
						    		 }
					    		 task_TestPrep.command_tailLog(system, "/var/log/up2date");
					    		 task_TestPrep.sleepForSeconds(sleepInterval);
					    		 page_Schedule.clickLink_Details();
					    	   	 page_SDC.clickLink_Events();
					    	   	 page_SDC.clickLink_History();
					    	   	 page_RhnCommon.clickLink_GeneralLink(link);
					    	   	 x++;
					    	   	 log.info("attemted "+ x + " times out of 30 possible attempts");
					    	   	 if(x > 30){
					    	   		 log.info("Checked progress 30 times, w/o success.. bailing out");
					    	   		 return;
					    	   	 }
								}
								log.info("Task Completed: "+link);
								
			}
	}


	@SuppressWarnings("unchecked")
	public int getFileSize(String myfile){
		int size = 0;
		try{
			BufferedReader in = new BufferedReader(new FileReader(myfile));
		    String content;
		    ArrayList list = new ArrayList();

		    while((content = in.readLine()) != null) {
		      if(list.contains(content)) {
		      } else {
		        list.add(content);
		      }
		    }
		    System.out.println("List Size = " + list.size());
		    size = list.size();
		}catch (IOException ioe) {
			log.info("FAILED");
		}
		return size;
	  }

	@SuppressWarnings("unchecked")
	public String getFileLine(String myfile, int line){

		String myline = "";
		try{
			BufferedReader in = new BufferedReader(new FileReader(myfile));
		    String content;
		    ArrayList list = new ArrayList();

		    while((content = in.readLine()) != null) {
		      if(list.contains(content)) {
		        //System.out.println("Already in list: " + content);
		      } else {
		        //System.out.println("Adding: " + content);
		        list.add(content);
		      }
		    }
		    myline = (String) list.get(line);

		}catch (IOException ioe) {
			log.info("FAILED");
		}
		return myline;
	  }

	
	public void sleepForSeconds(int seconds) {
		log.info("Sleeping for " + seconds + " seconds...");
		sel.sleep(seconds * 1000);
	}











}
