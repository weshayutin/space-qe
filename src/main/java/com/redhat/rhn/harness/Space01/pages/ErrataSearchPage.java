package com.redhat.rhn.harness.Space01.pages;

import java.util.Calendar;
import java.util.Locale;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;


public class ErrataSearchPage extends com.redhat.rhn.harness.Sat42.pages.ErrataSearchPage {

	RhnHelper rh = new RhnHelper();
	
	private static String  BY_ALL_FIELDS = "All Fields ";
	private static String  BY_ADVISORY = "Erratum Advisory (ex: RHBA-2007:0113)";
	private static String  BY_PACKAGE_NAME = "Package Name (ex: kernel)";
	private static String  BY_CVE = "CVE (ex: CVE-2006-4535)";
	
	public static final int ERRATA_SEARCH_RESULT_COLUMN = 2;
	
	   public String getLocation(){
	        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_ERRATA_SEARCH;
	    }

	   public void setTxt_SearchText(String txt){
//		   sel.setText("xpath=//tr/td[2]/form/div/div/table/tbody/tr/td/input", txt);
		   sel.setText("xpath=//th[normalize-space(.)='Search For:']/..//input[@name='search_string']", txt);
	   	}	

		public void select_SearchFor(int item) {
			rh.selectComboBoxItem("view_mode", findSearchForString(item), false);
		}
		
		
		public void checkBox_BugFix(boolean check){
			rh.checkRadioButton("xpath=//input[@name='errata_type_bug']",check);
		}

		public void checkBox_Security(boolean check){
			rh.checkRadioButton("xpath=//input[@name='errata_type_security']",check);
		}
		
		public void checkBox_Enhancement(boolean check){
			rh.checkRadioButton("xpath=//input[@name='errata_type_enhancement']",check);
		}		
		
		/**
		 * You don't actually need to call this if you're calling select_StartDate or select_EndDate
		 * @param check checked or not
		 */		
		public void checkBox_DateSearch(boolean check){
			rh.checkRadioButton("xpath=//input[@name='optionIssueDateSearch']",check);
		}				

		public void select_StartDate(Calendar cal){
			if (cal == null) {
				return;
			}
			this.select_Date(true, cal);			
		}		
		
		public void select_EndDate(Calendar cal){
			if (cal == null) {
				return;
			}
			this.select_Date(false, cal);			
		}		
		
		private void select_Date(boolean start, Calendar cal) {
			checkBox_DateSearch(true);
			
			String pre;
			
			if (start) {
				pre = "start";
			}
			else {
				pre = "end";
			}
			
			String monthStr = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
			String minString = String.valueOf(cal.get(Calendar.MINUTE));
			if ("0".equals(minString)) {
				minString = "00";
			}
			
			rh.selectComboBoxItem(pre + "_month", monthStr, false);
			rh.selectComboBoxItem(pre + "_day", String.valueOf(cal.get(Calendar.DAY_OF_MONTH)), false);
			rh.selectComboBoxItem(pre + "_year", String.valueOf(cal.get(Calendar.YEAR)), false);
			
			int hour = cal.get(Calendar.HOUR) == 0 ? 12 : cal.get(Calendar.HOUR);
			rh.selectComboBoxItem(pre + "_hour", String.valueOf(hour), false);
			
			rh.selectComboBoxItem(pre + "_minute", minString, false);
			String amPm = "PM";
			if (Calendar.AM == cal.get(Calendar.AM_PM)) {
				amPm = "AM";
			}
			rh.selectComboBoxItem(pre + "_am_pm", amPm, false);			
		}
		
		public void clickButton_Search(){
//	        rh.clickButton("xpath=//tr/td[2]/form/div/div/table/tbody/tr/td/input[2]","Search",true);
	        rh.clickButton("xpath=//th[normalize-space(.)='Search For:']/..//input[@value='Search']","Search",true);
	    }
		
		private String findSearchForString(int type) {
			switch(type) {
				case IRhnBase.SEARCH_Errata_AllFields: 
					return BY_ALL_FIELDS;
				case IRhnBase.SEARCH_Errata_ErratumAdvisory: 
					return BY_ADVISORY;
				case IRhnBase.SEARCH_Errata_PackageName: 
					return BY_PACKAGE_NAME;
				case IRhnBase.SEARCH_Errata_CVE: 
					return BY_CVE;					
			}
			log.severe("Invalid Search field");
			return "";
			
		}
		
		public WebList getList() {
			return WebList.getList();
		}
		
		
		
}
