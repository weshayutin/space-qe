package com.redhat.rhn.harness.Sat50.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class UsersPage extends com.redhat.rhn.harness.Sat42.pages.UsersPage {
	RhnHelper rh = new RhnHelper();

	 public String getLocation(){
	        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_USERS_PAGE;
	    }

	public void open(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_USERS_PAGE,true, "Users");
    }

	


}
