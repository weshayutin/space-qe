package com.redhat.rhn.harness.Sat50.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class YourRhnPage extends com.redhat.rhn.harness.Sat42.pages.YourRhnPage {
	static RhnHelper rh = new RhnHelper();

    public String getLocation(){
        return HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_YOUR_RHN_PAGE;
    }

    public void open(){
    	rh.openLink(HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_YOUR_RHN_PAGE,true, "Your Rhn");
    }

}
