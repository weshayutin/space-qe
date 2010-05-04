package com.redhat.rhn.harness.Sat52.pages;

import com.redhat.rhn.harness.common.RhnHelper;

public class SDCPage extends com.redhat.rhn.harness.Sat51.pages.SDCPage {

	RhnHelper rh = new RhnHelper();

	public void setTxt_SDCFilterBy(String txt){
		assertTrue(rh.isTextPresent("Filter by"));
    	String locator = "xpath=//td[starts-with(normalize-space(.),'Filter')]/div/input[@type='text']";
    	sel.setText(locator, "Filter by :", txt);
	}

}
