package com.redhat.rhn.harness.baseInterface;

public interface IYourRhn {

	/**
	 * A very generic test,updates the "position" field in an user account
	 * @param position
	 */
	public void updateYourAccount_Position(String position);

	/**
	 * Creates a nonadmin user, the name is based off the localhost properites file and a random number
	 * @param satellite
	 * @param firstName
	 * @param lastName
	 * @param emailFirst
	 * @param emailLast
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 * @param phone
	 * @return
	 */
	public String createNonAdminUser(boolean satellite,String firstName, String lastName,String emailFirst, String emailLast, String address,
			String city, String state, String zip, String phone);


	/**
	 * Create a non admin custom user
	 * @param login
	 * @param firstName
	 * @param lastName
	 * @param emailFirst
	 * @param emailLast
	 */
	public void createUserCustom(String login,String firstName, String lastName,String emailFirst, String emailLast);
	

	/**
	 * Checks for shared channel existence under YourRhn or Overview page
	 * @param channel
	 * @param org TODO
	 * @return
	 */
	public boolean checkForSharedChannel(String channel, String org);
	
	
}
