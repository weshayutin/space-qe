package com.redhat.rhn.harness.baseInterface;

public interface IActivationKeys {
	
/*	*//**
	 * Create an Activation key for rhn registrations
	 * @param name	Name of key
	 * @param key	The actual number combination of key itself 
	 * @param usageLimit
	 * @param monitoring
	 * @param provisioning
	 * @param virt
	 * @param virt_Platform
	 * @param unvDefault  	Universal Default, there can be only one
	 *//*
	public void createActivationKey(String name,
						String key,
						String usageLimit,
						boolean monitoring,
						boolean provisioning,
						boolean virt,
						boolean virt_Platform,
						boolean unvDefault);
	
	*//**
	 * Create an Activation key with a set base channel for rhn
	 * registrations
	 * @param name Name of key
	 * @param key The actual number combination of key itself
	 * @param basechan The name of the base channel
	 * @param usageLimit Usage limit, leave blank for unlimited
	 * @param monitoring
	 * @param provisioning
	 * @param virt
	 * @param virt_Platform
	 * @param unvDefault Universal Default, there can be only one
	 *//*
	public void createActivationKeyWithBaseChannel(String name,
												   String key,
												   String basechan,
												   String usageLimit,
												   boolean monitoring,
												   boolean provisioning,
												   boolean virt,
												   boolean virt_Platform,
												   boolean unvDefault);
	*//**
	 * Satellite only
	 * Adds a package to be installed during a registration w/ an Activation key
	 * @param key
	 * @param pkg
	 *//*
	public void addPackageToKey(String key, String pkg);
	
	*//**
	 * Checks if specified system is registered with the specified Activation key
	 * @param system
	 * @param key
	 * @return
	 *//*
	public boolean isSystemActivated(String system,String key);

	
	public void createActivationKeyWithOSAD(String name,String key, String usageLimit);
	
	*//**
	 * Delete Activation key
	 * @param name
	 * @param openAndLogin TODO
	 *//*
	public void deleteActivationKey(String name, boolean openAndLogin);
	
	*//**
	 * Navigates to a specific Activation key
	 * @param key
	 *//*
	public void goToKey(String key);
	
	*//**
	 * creates activation key w/ channel option
	 * @param name
	 * @param key
	 * @param usageLimit
	 * @param channel
	 * @param monitoring
	 * @param provisioning
	 * @param virt
	 * @param virt_Platform
	 * @param unvDefault
	 *//*
	public void createActivationKey(String name,String key, String usageLimit, String channel,
			boolean monitoring, boolean provisioning,
			boolean virt, boolean virt_Platform,boolean unvDefault);*/
	
}
