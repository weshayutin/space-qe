package com.redhat.rhn.harness.common.octokick;

public class ODistribution {
	private String symName;
	private String RHNName;
	private String KSTree;
	private String architecture;
	private String satChannel;
	private boolean latest;
	private String virtHost;
	private String virtGuest;
	private boolean onSatellite;
	private OSatSync myThread;


	public ODistribution(String RHNNm, String KSTr, String arch,
						 String symName, boolean latest, String satchannel, String virtHost,String virtGuest){
		this.RHNName = RHNNm;
		this.KSTree = KSTr;
		this.architecture = arch;
		this.symName = symName;
		this.latest = latest;
		this.virtHost = virtHost;
		this.virtGuest = virtGuest;
		this.onSatellite = false;
		this.satChannel = satchannel;
	}

	public String getSatChannel(){
		return this.satChannel;
	}

	public OSatSync getSyncThread(){
		return this.myThread;
	}

	public void setSyncThread(OSatSync mythread){
		this.myThread = mythread;
	}

	public String getArchitecture(){
		return this.architecture;
	}

	public boolean isSyncChecked(){
		return this.onSatellite;
	}

	public void setSyncChecked(){
		this.onSatellite = true;
	}

	public String getRHNName(){
		return this.RHNName;
	}

	public String getKSTree(){
		return this.KSTree;
	}

	public String getSymName(){
		return this.symName;
	}

	public boolean isLatest(){
		return this.latest;
	}
	
	public String isVirtHost(){
		return this.virtHost;
	}
	
	public String isVirtGuest(){
		return this.virtGuest;
	}

}
