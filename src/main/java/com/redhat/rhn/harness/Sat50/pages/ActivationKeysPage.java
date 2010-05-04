package com.redhat.rhn.harness.Sat50.pages;

import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class ActivationKeysPage extends com.redhat.rhn.harness.Sat42.pages.ActivationKeysPage {

	RhnHelper rh = new RhnHelper();

	public String getLocation() {
		 return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_ACTIVATIONKEY_PAGE;
	}



}
