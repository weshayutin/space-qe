package com.redhat.rhn.harness.Space01.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class ErrataPage extends com.redhat.rhn.harness.Sat51.pages.ErrataPage{

	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + "/rhn/errata/Overview.do";
    }

	RhnHelper rh = new RhnHelper();
	
	
	
}
