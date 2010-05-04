package com.redhat.rhn.harness.common;

import java.io.File;

import com.redhat.qe.tools.SCPTools;

public class SCP extends SeleniumSetup{
	/**
	 * Sends file via SCP to root user on remote system
	 * @param hostname hostname of system
	 * @param source location of file to send on local system
	 * @param dest location for file to land on remote system
	 */
	public void sendFile(String hostname,
			String source,
			String dest){
		SCPTools scp = new SCPTools(hostname,
				"root",
				new File(hc.SSH_PRIVATE_KEY_LOC),
				hc.SSH_KEY_PASS);
		scp.sendFile(source, dest);
	}
	
	/**
	 * Sends file via SCP to specified user on remote system
	 * @param hostname hostname of system
	 * @param user username to send file to
	 * @param source location of file to send on local system
	 * @param dest location for file to land on remote system
	 */
	public void sendFile(String hostname,
			String user,
			String source,
			String dest){
		SCPTools scp = new SCPTools(hostname,
				user,
				new File(hc.SSH_PRIVATE_KEY_LOC),
				hc.SSH_KEY_PASS);
		scp.sendFile(source, dest);
	}
	
	/**
	 * Retrieves file via SCP as root user on remote system
	 * @param hostname hostname of system
	 * @param remoteFile location of file to retrieve on remote system
	 * @param target location for file to land on local system
	 */
	public void getFile(String hostname,
			String remoteFile,
			String target){
		SCPTools scp = new SCPTools(hostname,
				"root",
				new File(hc.SSH_PRIVATE_KEY_LOC),
				hc.SSH_KEY_PASS);
		scp.getFile(remoteFile, target);
	}
	
	/**
	 * Retrieves file via SCP as specified user on remote system
	 * @param hostname hostname of system
	 * @param user user to retrieve file as
	 * @param remoteFile location of file to retrieve on remote system
	 * @param target location for file to land on local system
	 */
	public void getFile(String hostname,
			String user,
			String remoteFile,
			String target){
		SCPTools scp = new SCPTools(hostname,
				user,
				new File(hc.SSH_PRIVATE_KEY_LOC),
				hc.SSH_KEY_PASS);
		scp.getFile(remoteFile, target);
	}
}
