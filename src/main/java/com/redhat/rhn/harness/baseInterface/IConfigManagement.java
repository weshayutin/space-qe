package com.redhat.rhn.harness.baseInterface;

public interface IConfigManagement {
	
	/**
	 * Deletes the specified Config Mangement Channel
	 * @param channel
	 * @param openAndLogin
	 *//*
	public void DeleteConfigChannel(String channel, boolean openAndLogin);
	
	*//**
	 * Creates the specified Config Mangement Channel
	 * @param channel   The channel name
	 * @param openAndLogin  wether or not to login
	 * @param deleteOld   Checks for previous channel with the same name and deletes it.
	 * @param successful TODO
	 *//*
	public void CreateNewConfigChannel(String channel, boolean openAndLogin, boolean deleteOld, boolean successful);
	
	*//**
	 * Opens Config Management page and navigates to the specified config channel
	 * @param channel
	 * @param openAndLogin
	 * @return  true/false if channel exists 
	 *//*
	public boolean goToChannel(String channel, boolean openAndLogin);
	
	
	*//**
	 * Removes a config management config file from the specified channel in the webui.
	 * @param channel
	 * @param file
	 * @param openAndLogin
	 * @param successful  Checks for successful removal of file
	 * 
	 * <li> 
	 * If the file may not exist, use successful = false, the method will still attempt to delete the file
	 *//*
	public void removeConfigFile(String channel, String file, boolean openAndLogin, boolean successful);
	
	*//**
	 * Uploads a file from the testsystems filesystem into RHN's Config Mangagement to the specificed Config Channel
	 * @param channel
	 * @param file  This is the actual full path the file from the test system
	 * @param binary txt or binary file
	 * @param path  This is the WEBUI full path file name
	 * @param succesful checks for success message
	 * @param revisionNumber 
	 *//*
	public void uploadFilesToChannel(String channel,String file,boolean binary, String path, boolean succesful, int revisionNumber);
	
	*//**
	 * Updates a previously created config management file
	 * @param channel
	 * @param file
	 * @param updatedTxt the repacement txt in the file
	 * @param revision  the next revision, would have to be atleast 2
	 * @param openAndLogin 
	 * @param binary  txt or binary file
	 *//*
	public void updateConfigurationFile(String channel, String file, String updatedTxt, int revision, boolean openAndLogin, boolean binary);

	*//**
	 * Deletes all config channels
	 * @param openAndLogin
	 *//*
	public void deleteAllConfigChannels(boolean openAndLogin);*/
}
