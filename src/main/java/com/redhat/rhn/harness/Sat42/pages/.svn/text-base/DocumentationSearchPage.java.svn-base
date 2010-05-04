package com.redhat.rhn.harness.Sat42.pages;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;


public class DocumentationSearchPage extends RhnPage{
	
	RhnHelper rh = new RhnHelper();
	
	private static String  CONTENT_AND_TITLE = "Content & Title";
	private static String  FREE_FORM = "Free Form";
	private static String  CONTENT = "Content";
	private static String  TITLE = "Title";
	private static String  TITLE_GERMAN = "Titel";

	
	public static final int ERRATA_SEARCH_RESULT_COLUMN = 2;
	
	public String getLocation(){
		return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_DOCUMENTATION_SEARCH;
	}

	public void setTxt_SearchText(String txt){
		sel.setText("xpath=//tr/td[2]/form/div/div/table/tbody/tr/td/input", txt);
	}	
		
	public void select_SearchFor(int item) {
		rh.selectComboBoxItem("view_mode", findSearchForString(item), false);
	}		
 		
	public void clickButton_Search(){
		rh.clickButton("xpath=//input[@type='image' and @name='Search!']",true);
	}
		
	public void click_HelpLink(){
		rh.clickLink("xpath=//a[contains(@href, 'rhn/help/index.do')]","Help", true);
	}
	
	public void click_LanguageGuides(String lang){
		String link = "xpath=//a[contains(@href, '" + lang + "/index.jsp')]";
		rh.clickLink(link,"Language Guide", true);
	}
	
	public void click_RefernceGuide(){
		rh.clickLink("xpath=//a[contains(@href, '/rhn/help/dispatcher/reference_guide')]",
				"Reference Guide", true);
	}
	
	public void click_DocSearchLink(){
		rh.clickLink("xpath=//a[contains(@href, '/rhn/help/Search.do')]","Search", true);
	}
	   
	
	private String findSearchForString(int type) {
		switch(type) {
		case IRhnBase.SEARCH_Documentation_ContentAndTitle: 
			return CONTENT_AND_TITLE;
		case IRhnBase.SEARCH_Documentation_FreeForm: 
			return FREE_FORM;
		case IRhnBase.SEARCH_Documentation_Content: 
			return CONTENT;
		case IRhnBase.SEARCH_Documentation_Title: 
			return TITLE;
		case IRhnBase.SEARCH_Documentation_Title_German: 
			return TITLE_GERMAN;					
		}
		log.severe("Invalid Search field");
		return "";			
	}

	public WebList getList() {
		return WebList.getList();
	}
	
}

