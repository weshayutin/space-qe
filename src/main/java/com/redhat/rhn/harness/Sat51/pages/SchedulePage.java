package com.redhat.rhn.harness.Sat51.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class SchedulePage extends com.redhat.rhn.harness.Sat50.pages.SchedulePage{


	RhnHelper rh = new RhnHelper();

	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SCHEDULE_PAGE;
    }

	

}
