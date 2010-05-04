package com.redhat.rhn.harness.Hosted.pages;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class SatelliteToolsPage extends com.redhat.rhn.harness.Sat42.pages.SatelliteToolsPage{
	
	RhnHelper rh = new RhnHelper();
	
	public String getLocation() {
		 return "https://"+IRhnBase.SERVER_HOSTNAME + HarnessConfiguration.RHN_SATELLITE_TOOLS;
	}
	
	
	
	


}
