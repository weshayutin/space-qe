package com.redhat.rhn.harness.Sat51.pages;

import com.thoughtworks.selenium.SeleniumException;


public class RhnCommon extends com.redhat.rhn.harness.Sat50.pages.RhnCommon {

	
    
    public void clickButton_PaginateNext(){
    	try{
    	rh.clickLink("alt=Next", true);
    	}
    	catch(SeleniumException se){
    		rh.clickLink("xpath=//img[@type='image' and @alt='Next']", true);
    		//						/html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table/tbody/tr/td[3]/input[3]
    								///html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table/tbody/tr/td[3]/input[3]
    								///html/body/div/div[5]/table/tbody/tr/td[2]/form[2]/table[3]/tbody/tr/td[3]/input[3]
    	}
    }

   
    public void check_SelectAll_CheckBox(){
    	rh.checkRadioButton("name=checkall",true);
        }
    
    
    public void setTxt_FilterBy(String txt){
    	assertTrue(rh.isTextPresent("Filter by"));
    	String locator = "xpath=//td[starts-with(normalize-space(.),'Filter')]/input[@type='text']";
    	//this is generic for systems, packages, and other
         if(rh.isElementPresent(locator, true)){
    		sel.setText(locator, "Filter by :", txt);
    	}
    	else{
    		super.setTxt_FilterBy(txt);
    	}
	}







}
