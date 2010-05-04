package com.redhat.rhn.harness.baseInterface;

public interface ISatelliteTools {
	
	/**
	 * Deletes all multi-org organizations
	 * @param openAndLogin
	 */
	public void deleteAllOrganizations(boolean openAndLogin);
	
	/**
	 * Modify the trusts of an organization one at a time
	 * @param trustee  the receiving org, 
	 * @param trustor  the org sharing content, the one you check
	 * @param trust    true/false
	 */
	public void modifyOrgTrusts(String trustee, String trustor, boolean trust);
	
	/**
	 * 
	 * @param openAndLogin
	 * @param entitlement
	 * @return
	 */
	public String verifyOrgSoftwareEntitlementUsage(boolean openAndLogin, int entitlement);
	
	
/*	*//**
	 * 
	 * @param openAndLogin
	 * @param entitlement
	 * @return
	 *//*
	public String verifyOrgSoftwareEntitlementUsage(boolean openAndLogin, int entitlement, int column);
	
	*/
}
