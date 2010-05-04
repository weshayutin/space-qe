package com.redhat.rhn.harness.common.Sat50.tasks;

import com.redhat.rhn.harness.baseInterface.IKickStart;

public class KickStart extends com.redhat.rhn.harness.common.Sat42.tasks.KickStart implements IKickStart{



	public String get_BareMetal_KickstartUrl(String ksProfile){
		String url = null;
		page_SatelliteSystems.open();
        page_KickStart.open();
        page_KickStart.clickLink_ViewListKickstartProfiles();
        if(rh.isTextPresent(ksProfile)){
        	rh.clickSystemProfileLink(ksProfile);
        }
        page_KickStart.clickLink_BareMetalKickstart();
        url = page_KickStart.getTxt_BareMetalKickstartUrl();
        log.fine("Bare Metal Url = "+url);
		
		
		
		return url;
	}

	


}
