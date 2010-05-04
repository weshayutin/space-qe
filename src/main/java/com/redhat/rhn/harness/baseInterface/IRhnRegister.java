package com.redhat.rhn.harness.baseInterface;

public interface IRhnRegister {
	
	/**
	 * Calls a DogTail script to test rhn_register gui
	 * @param server
	 * @param user
	 * @param password
	 * @param profilename
	 */
	public void runRhnRegisterGUI(String server,String user, String password, String profilename);

}
