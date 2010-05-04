package com.redhat.rhn.harness.baseInterface;

public interface IKickStart {



/*

	*//**
	 * Start a virt guest
	 * @param system
	 * @param guestLabel
	 * @param openAndLogin
	 * @param runRhnCheck TODO
	 * @param action TODO
	 *//*
	public void virtGuest_Action(String system,String guestLabel,boolean openAndLogin, boolean runRhnCheck, int action);


	*//**
	 * Gets the real hostname of a virt guest system from the kickstart label
	 * @param system
	 * @param guestLabel
	 * @param openAndLogin
	 * @return
	 *//*
	public String getVirtGuest_HostName(String system,String guestLabel,boolean openAndLogin);
	
	*//**
	 * Deletes a kickstart Profile
	 * @param profileName
	 * @param openAndLogin TODO
	 *//*
	public void DeleteKickstartProfile(String profileName, boolean openAndLogin);

	*//**
	 * check status on a running kickstart
	 * @param system
	 * @param kickstartProfileName
	 * @param openAndLogin TODO
	 *//*
	public void kickstartWaitforKickstartCompletion(String system, String kickstartProfileName, boolean openAndLogin);
	
	*//**
	 * Checks the status a virt guest
	 * @param system
	 * @param guestLabel
	 * @param openAndLogin
	 * @param Status
	 *//*
	public void virtGuest_Status(String system,String guestLabel,boolean openAndLogin, String Status);
	
	*//**
	 * Add Activation key to a kickstart profile
	 * @param profileName
	 *//*
	public void AddActivationKeyToKickstartProfile(String profileName);
	
	*//**
	 * Gets the url for the ks profile for a bare metal kickstart from a kickstart profile
	 * @param ksProfile
	 * @return bare metal kickstart url
	 *//*
	public String get_BareMetal_KickstartUrl(String ksProfile);
	
	*//**
	 * Creates a custom kickstart distribution
	 * @param label
	 * @param externalUrl
	 * @param baseChannel
	 * @param kickstartRPM
	 * @param installerGeneration
	 *//*
	public void createKickstartDistribution(String label, String externalUrl,String baseChannel, String kickstartRPM, String installerGeneration);
	
	*//**
	 * Deletes a kickstart distribution
	 * @param label
	 *//*
	public void deleteKickstartDistribution(String label);*/
}
