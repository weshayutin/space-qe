package com.redhat.rhn.harness.common;

import java.io.File;
import java.util.logging.Level;
import com.redhat.qe.tools.SSHCommandRunner;
import com.redhat.qe.tools.SplitStreamLogger;
import com.thoughtworks.selenium.SeleniumException;

public class SSH extends SeleniumSetup{
	/**
	 * Runs a command via SSH as root, logs all output to
	 * INFO logging level
	 * @param hostname hostname of system
	 * @param command command to execute
	 */
	public void executeViaSSH(String hostname, String command){
		executeViaSSH(hostname,"root", command);
	}
	
	/**
	 * Runs a command via SSH as defined user, logs all output to
	 * INFO logging level
	 * @param hostname hostname of system
	 * @param user user to execute command as
	 * @param command command to execute
	 */
	public void executeViaSSH(String hostname, 
			String user, String command){
		SSHCommandRunner runner = null;
		log.info("SSH: Running '"+command+"' on '"+hostname+"'");
		try{
			runner = new SSHCommandRunner(hostname,
					user,
					new File(hc.SSH_PRIVATE_KEY_LOC),
					hc.SSH_KEY_PASS,
					command);
			runner.run();
			new SplitStreamLogger(runner).log();
			runner.waitFor();
		}
		catch (Exception e){
			log.log(Level.INFO, "SSH command failed:", e);
		}
	}
	
	/**
	 * Runs a command via SSH as root, logs all output to INFO
	 * logging level, returns String[] containing stdout in 0 position
	 * and stderr in 1 position
	 * @param hostname hostname of system
	 * @param command command to execute
	 * @return output as String[], stdout in 0 pos and stderr in 1 pos
	 */
	public String[] executeViaSSHWithReturn(String hostname, String command){
		return executeViaSSHWithReturn(hostname,"root",command);
	}
	
	/**
	 * Runs a command via SSH as specified user, logs all output to INFO
	 * logging level, returns String[] containing stdout in 0 position
	 * and stderr in 1 position
	 * @param hostname hostname of system
	 * @param user user to execute command as
	 * @param command command to execute
	 * @return output as String[], stdout in 0 pos and stderr in 1 pos
	 */
	public String[] executeViaSSHWithReturn(String hostname, 
			String user, String command){
		SSHCommandRunner runner = null;
		SplitStreamLogger logger;

		log.info("SSH: Running '"+command+"' on '"+hostname+"'");
		try{
			runner = new SSHCommandRunner(hostname,
					user,
					new File(hc.SSH_PRIVATE_KEY_LOC),
					hc.SSH_KEY_PASS,
					command);
			runner.run();
			logger = new SplitStreamLogger(runner);
			logger.log();
			runner.waitFor();
		}
		catch (Exception e){
			log.log(Level.INFO, "SSH command failed:", e);
			return failSSH();
		}
		return new String[] {logger.getStdout(), logger.getStderr()};
	}
	
	/**
	 * Runs a command via SSH as root, logs all output to
	 * specified logging level
	 * @param hostname hostname of system
	 * @param command command to execute
	 * @param logLevel logging level to log all output to
	 */
	public void executeViaSSH(String hostname, 
			String command, Level logLevel){
		executeViaSSH(hostname,"root",command,logLevel);
	}
	
	/**
	 * Runs a command via SSH as defined user, logs all output to
	 * specified logging level
	 * @param hostname hostname of system
	 * @param user user to execute command as
	 * @param command command to execute
	 * @param logLevel logging level to log all output to
	 */
	public void executeViaSSH(String hostname, 
			String user, String command, Level logLevel){
		SSHCommandRunner runner = null;
		log.info("SSH: Running '"+command+"' on '"+hostname+"'");
		try{
			runner = new SSHCommandRunner(hostname,
					user,
					new File(hc.SSH_PRIVATE_KEY_LOC),
					hc.SSH_KEY_PASS,
					command);
			runner.run();
			new SplitStreamLogger(runner).log(logLevel, logLevel);
			runner.waitFor();
		}
		catch (Exception e){
			log.log(Level.INFO, "SSH command failed:", e);
		}
	}
	
	/**
	 * Runs a command via SSH as root, logs all output to specified
	 * logging level, returns String[] containing stdout in 0 position
	 * and stderr in 1 position
	 * @param hostname hostname of system
	 * @param command command to execute
	 * @param logLevel logging level to log all output to
	 * @return output as String[], stdout in 0 pos and stderr in 1 pos
	 */
	public String[] executeViaSSHWithReturn(String hostname, 
			String command, Level logLevel){
		return executeViaSSHWithReturn(hostname,"root",command,logLevel);
	}
	
	/**
	 * Runs a command via SSH as specified user, logs all output to specified
	 * logging level, returns String[] containing stdout in 0 position
	 * and stderr in 1 position
	 * @param hostname hostname of system
	 * @param user user to execute command as
	 * @param command command to execute
	 * @param logLevel logging level to log all output to
	 * @return output as String[], stdout in 0 pos and stderr in 1 pos
	 */
	public String[] executeViaSSHWithReturn(String hostname, 
			String user, String command, Level logLevel){
		SSHCommandRunner runner = null;
		SplitStreamLogger logger;

		log.info("SSH: Running '"+command+"' on '"+hostname+"'");
		try{
			runner = new SSHCommandRunner(hostname,
					user,
					new File(hc.SSH_PRIVATE_KEY_LOC),
					hc.SSH_KEY_PASS,
					command);
			runner.run();
			logger = new SplitStreamLogger(runner);
			logger.log(logLevel, logLevel);
			runner.waitFor();
		}
		catch (Exception e){
			log.log(Level.INFO, "SSH command failed:", e);
			return failSSH();
		}
		return new String[] {logger.getStdout(), logger.getStderr()};
	}

	private String[] failSSH(){
		return new String[] {"fail", "fail"};
	}
}