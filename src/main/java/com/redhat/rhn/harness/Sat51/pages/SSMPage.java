package com.redhat.rhn.harness.Sat51.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;

public class SSMPage extends com.redhat.rhn.harness.Sat50.pages.SSMPage{
	public void clickLink_TopBar_Packages(){
        rh.openLink(HarnessConfiguration.RHN_HOST+"/rhn/ssm/Packages.do",true,"Packages");
        ///network/systems/ssm/packages/index.pxt
    }
}
