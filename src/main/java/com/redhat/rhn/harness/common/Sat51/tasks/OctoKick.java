package com.redhat.rhn.harness.common.Sat51.tasks;

import java.util.LinkedList;

import com.redhat.rhn.harness.common.octokick.OKickStart;
import com.redhat.rhn.harness.common.octokick.OPath;
import com.redhat.rhn.harness.common.octokick.OScriptParser;
import com.redhat.rhn.harness.common.octokick.OSystem;

public class OctoKick extends com.redhat.rhn.harness.common.Sat50.tasks.OctoKick {
	private OScriptParser myParser;
	private OKickStart myKS;
	private boolean isInProgress;
	private static final int MAX_TICKS = 600;
	private static final int INTERCYCLE_DELAYSECS = 20;

	//These are public so that these data structures can be accessed outside
	//of this class directly, perhaps by a web interface or something akin to it
	public LinkedList<OPath> KSPaths;
	public LinkedList<OSystem> KSSystems;
	public boolean isPaused;
	public String ksLabel;

	//RhnLogger rl = new RhnLogger();
	//RhnBase rb = new RhnBase();

	
}
