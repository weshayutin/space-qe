package com.redhat.rhn.harness.baseInterface;

public interface IChannels {
	
	/**
	 * Verifies specified channel exists
	 * @param channel
	 * @param exists TODO
	 *//*
	public void verifyChannelExists(String channel, boolean exists);
	
	*//**
	 * Deletes all custom channels
	 *//*
	public void deleteAllCustomChannels();
	
	*//**
	 * Creates a cloned copy of a channel
	 * errata 0 = Current (all errata)
	 * errata 1 = Original (no errata)
 	 * errata 2 = Select 
	 * @param origChannel
	 * @param clonedChannelName
	 * @param clonedChannelLabel
	 * @param errata
	 *//*
	public void createChannelClone(String origChannel, String clonedChannelName,String clonedChannelLabel, int errata );
	
	*//**
	 * Delete a cloned channel w/ no systems subscribed to it
	 * @param clonedChannelName
	 *//*
	public void deleteCustomChannel(String clonedChannelName);
	
	*//**
	 * Copy the testRPM to /tmp on the server and rhnpush that rpm to a given channel
	 * @param channel
	 * @param packageName
	 * @param packagePath TODO
	 * @param server TODO
	 * @param user TODO
	 * @param passwd TODO
	 *//*
	public void rhnPushPackageToChannel(String channel, String packageName, String packagePath, String server, String user, String passwd);
	
	*//**
	 * Creates a custom channel that's empty with a defined architecture
	 * @param channelName name of channel
	 * @param architecture architecture of channel (e.g. i386, x86_64, etc.)
	 *//*
	public void createCustomChannelWithArchitecture(String channelName,
													String architecture);
	
	*//**
	 * Creates a child channel that's empty with a defined architecture
	 * @param channelName name of channel
	 * @param parentChannel name of parent channel
	 * @param architecture architecture of channel (e.g. i386, x86_64, etc.)
	 *//*
	public void createCustomChannelWithParent(String channelName,
			  								  String parentChannel,
			  								  String architecture);
	
	
	*//**
	 * Creates a custom channel thats empty
	 * @param channelName
	 *//*
	public void createCustomChannel(String channelName);
	
	*//**
	 * Verifys a rpm package is in a given channel
	 * @param Channel
	 * @param Package
	 *//*
	public void verifyPackageInChannel(String Channel, String Package);
	
	*//**
	 * Create a custom channel w/ multiorgII channel sharing options
	 * @param channelName
	 * @param orgChannelSharingSetting
	 * Use, IRhnBase.CHANNEL_SHARE as options
	 * @param orgChannelUserRestriction
	 * Use, IRhnBase.CHANNEL_SHARE as options
	 * @param parentChannel TODO
	 *//*
	public void createCustomChannel(String channelName, int orgChannelSharingSetting, int orgChannelUserRestriction, String parentChannel);
	*/
}
