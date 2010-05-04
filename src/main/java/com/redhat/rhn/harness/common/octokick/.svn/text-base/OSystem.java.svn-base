package com.redhat.rhn.harness.common.octokick;

public class OSystem {

	/**
	 * System's hostname
	 */
	public String hostname;

	/**
	 * Instance variable containing functional status
	 */
	private boolean isUp;

	/**
	 * System's architecture
	 */
	public String architecture;
	
	/**
	 * variable, this machines is designated for virt testing
	 */
	public String virt;
	

	/**
	 * System's current Kickstart Path
	 */
	public OPath path;

	/**
	 * Constructor method for an Octokick System
	 * @param host hostname of client system
	 * @param arch architecture of client system
	 * @param virtHost 
	 * @param virtHost 
	 * @param virtHost TODO
	 */
/*	public OSystem(String host, String arch, String virt){
		this.hostname = host;
		this.architecture = arch;
		this.virtHost = virt;
		this.isUp = true;
		this.path = null;
	}*/
	public OSystem(String host, String arch, String virt){
		this.hostname = host;
		this.architecture = arch;
		this.virt = virt;
		this.isUp = true;
		this.path = null;
	}


	/**
	 * Assigns a 'kickstart path' to this system
	 * @param pth kickstart path
	 */
	public void assignPath(OPath pth){
		this.path = pth;
	}

	public void restartLatestFailedTest(){
		this.isUp = true;
		for(int i=0;i<this.path.size();i++){
			if(this.path.get(i).getStatus()==4){
				this.path.get(i).setErrors("");
				this.path.get(i).setStatus(0);
			}
		}
	}

	/**
	 * Marks machine as non-functional ('borked')
	 */
	public void goDown(){
		this.isUp = false;
	}

	/**
	 * Marks machine as functional ('unborked')
	 */
	public void goUp(){
		this.isUp = true;
	}

	/**
	 * Returns functional status of machine
	 * @return functional status as boolean value
	 */
	public boolean isUp(){
		return this.isUp;
	}
}
