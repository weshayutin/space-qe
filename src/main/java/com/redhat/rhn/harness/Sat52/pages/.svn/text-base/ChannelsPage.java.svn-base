package com.redhat.rhn.harness.Sat52.pages;

import com.redhat.rhn.harness.common.RhnHelper;

public class ChannelsPage extends com.redhat.rhn.harness.Sat51.pages.ChannelsPage {

	RhnHelper rh = new RhnHelper();

	public void setTxt_ChannelName(String txt){
		sel.setText("xpath=//input[@name='new_channel_label']", txt);
    }

	public void select_ParentChannel(String parentChannel){
		rh.selectBoxItem("channel_parent", parentChannel, false);
	}
}
