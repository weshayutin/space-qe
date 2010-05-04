package com.redhat.rhn.harness.Sat42.pages;

import java.util.Calendar;
import java.util.Locale;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;


public class ErrataSearchPage extends  RhnPage {

	protected RhnHelper rh = new RhnHelper();
	
	
	   public String getLocation(){
	        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_ERRATA_SEARCH;
	    }

	
		
		
}
