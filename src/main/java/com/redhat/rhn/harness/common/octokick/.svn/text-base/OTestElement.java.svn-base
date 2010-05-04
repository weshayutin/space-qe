package com.redhat.rhn.harness.common.octokick;

public class OTestElement {
	private ODistribution distro;
	private int testStatus;
	private String errors;
	private int elapsedTicks;
	private String KSLabel;
	private String randlabel;

	/**
	 * Constructor method which initializes an OTestElement
	 * @param distrib ODistribution to assign to this test element
	 */
	public OTestElement(ODistribution distrib){
		this.distro = this.clone(distrib);
		this.testStatus = 0;
		this.elapsedTicks = 0;
	}

	private ODistribution clone(ODistribution distrib){
		ODistribution myDistrib = new ODistribution(distrib.getRHNName(),
													distrib.getKSTree(),
													distrib.getArchitecture(),
													distrib.getSymName(),
													distrib.isLatest(),
													distrib.getSatChannel(), 
													distrib.isVirtHost(),
													distrib.isVirtGuest());
		return myDistrib;
	}

	public void tick(){
		this.elapsedTicks++;
	}

	public void setKSLabel(String kslabel){
		this.KSLabel = kslabel;
	}

	public void setRandLabel(String randlabel){
		this.randlabel = randlabel;
	}

	public String getKSLabel(){
		return this.KSLabel;
	}

	public int getTicks(){
		return this.elapsedTicks;
	}
	/**
	 * Returns distribution tested with this test element
	 * @return ODistribution object representing test element's current distribution
	 */
	public ODistribution getDistro(){
		return this.distro;
	}

	public String getRandLabel(){
		return this.randlabel;
	}

	/**
	 * Returns any errors the test has encountered
	 * @return String representing testing errors
	 */
	public String getErrors(){
		return errors;
	}

	/**
	 * Returns test status as an integer value:
	 *
	 * -1 = Paused, waiting on Satellite Sync to complete
	 * 0 = Untested
	 * 1 = Testing in progress
	 * 2 = RHN reports successful, waiting for SSH
	 * 3 = Testing complete
	 * 4 = Test failed
	 * 5 = Waiting on packages to be picked up
	 *
	 * @return testing status as an int
	 */
	public int getStatus(){
		return this.testStatus;
	}

	/**
	 * Sets error message variable
	 * @param error error message as a String
	 */
	public void setErrors(String error){
		this.errors = error;
	}

	/**
	 * Sets testing status variable
	 * @param testStat testing status as an int
	 */
	public void setStatus(int testStat){
		this.testStatus = testStat;
	}


}
