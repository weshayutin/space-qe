package com.redhat.rhn.harness.common.octokick;
import java.util.logging.Logger;

import com.redhat.qe.tools.ExecCommands;

public class OSatSync extends Thread{
	private String satHostname;
	private String channel;
	protected static Logger log = Logger.getLogger(OSatSync.class.getName());

	
	public OSatSync(String satHostname2, String satChannel) {
		this.satHostname = satHostname;
		this.channel = channel;
		//this.myLogger = logObj;
		// TODO Auto-generated constructor stub
		//not used but should fix
	}
	
	
	public void run(){
		ExecCommands exec = new ExecCommands();
		String retVal = "";
		try{
			log.info("DEBUG: Performing sat sync, channel ="+this.channel);
			retVal = exec.submitCommandToLocalWithReturn(true, "ssh", "root@"+this.satHostname+" satellite-sync -c" + this.channel +" > /dev/null 2>&1 &");
		}
		catch(Exception e){
			log.info("Munged satellite sync on "+this.satHostname+" to "+this.channel);
		}
		log.info("SatSync output: "+retVal);
	}
}
