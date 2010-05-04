package com.redhat.rhn.harness.baseInterface;

public interface IUserRoles {




	/**
	 * Navigates to specific user
	 * @param user
	 */
	public void goToUser(String user);

	/**
	 * Edits the role for a user
	 * @param user
	 * @param role
	 * @param check
	 * Use IRhnBase.USER* for role
	 */
	public void UserEditRole(String user, int role, boolean check);

	/**
	 * Creates a system group
	 * @param systemGroupName
	 */
	public void groupCreate(String systemGroupName);

	/**
	 * Add a system profile to a system group
	 * @param system
	 * @param systemGroup
	 */
	public void addSystemToSystemGroup(String system, String systemGroup);
	
	/**
	 * Deletes a system group
	 * @param systemGroupName
	 */
	public void groupDelete(String systemGroupName);
	
	/**
	 * Delete a user
	 * @param user
	 */
	public void deleteUser(String user);
	
	/**
	 * Deletes all users in RHN Satellite for the org that is logged into.
	 */
	public void deleteAllUsers();

}
