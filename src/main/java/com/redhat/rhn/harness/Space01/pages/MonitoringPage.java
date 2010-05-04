package com.redhat.rhn.harness.Space01.pages;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class MonitoringPage extends com.redhat.rhn.harness.Sat51.pages.MonitoringPage{

	RhnHelper rh = new RhnHelper();

	public void clickLink_RHN_Monitoring_Scout(){
        if(rh.isElementPresent("link=RHN Satellite Monitoring Scout", false))
        	rh.clickLink("RHN Satellite Monitoring Scout",true);
        else{ // in this case: it's an upgraded Satellite - scout name is "old"
        	rh.clickLink("RHN Monitoring Scout",true);
        }
    }
}
