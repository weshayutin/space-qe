package com.redhat.rhn.harness.Hosted.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class SystemProvisionPage extends com.redhat.rhn.harness.Sat42.pages.SystemProvisionPage {

	RhnHelper rh = new RhnHelper();

	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SYSTEMS_PAGE;
    }
}
