package com.redhat.rhn.harness.common.octokick;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class OScriptParser extends com.redhat.rhn.harness.common.SeleniumSetup{
	private String testName;
	private boolean customSSH;
	private boolean customPaths;
	private String sshKey;
	private String rootpwd;
	private LinkedList<OSystem> sysList;
	private LinkedList<ODistribution> distList;
	private LinkedList<String> arches;

	/**
	 * Returns LinkedList<OSystem> of testing systems
	 * @return LinkedList<OSystem> of testing systems
	 */
	public LinkedList<OSystem> getSysList(){
		return this.sysList;
	}

	public LinkedList<ODistribution> getDistributions(){
		return this.distList;
	}

	public LinkedList<String> getArches(){
		return this.arches;
	}

	public String getSSHKey(){
		return this.sshKey;
	}

	public String getRootPwd(){
		return this.rootpwd;
	}

	public String getTestName(){
		return this.testName;
	}

	public boolean getCustomSSH(){
		return this.customSSH;
	}

	public boolean getCustomPaths(){
		return this.customPaths;
	}

	private String getQuoted(String myLine){
		String[] strList = myLine.split("=");
		char[] myChars = new char[strList[1].lastIndexOf('"')-strList[1].indexOf('"')-1];
		strList[1].getChars(strList[1].indexOf('"')+1,
							strList[1].lastIndexOf('"'),
							myChars, 0);
		return String.valueOf(myChars);
	}

	private boolean getBoolean(String myLine){
		String[] strList = myLine.split("=");
		return (strList[1].trim().contains("1"));
	}

	private void addSystem(String myLine){
		if(myLine.contains("|")){
			String[] strList = myLine.split("\\|");
			sysList.add(new OSystem(strList[0].trim(),strList[1].trim(),strList[2].trim()));
		}
	}
	
	/*
	 * private void addSystem(String myLine){
		if(myLine.contains("|")){
			String[] strList = myLine.split("\\|");
			sysList.add(new OSystem(strList[0].trim(),strList[1].trim(), strList[2].trim()));
		}
	}

	 */

	private void populateArches(){
		for(int i=0;i<sysList.size();i++){
			if(!this.arches.contains(sysList.get(i).architecture))
				this.arches.add(sysList.get(i).architecture);
		}
	}

	private void addDistro(BufferedReader in, String firstLine) throws Exception{
		int foundElements = 0;
		boolean onFirst = true;
		boolean latest = false;
/*		boolean virtHost = false;
		boolean virtGuest = false;*/
		String[] distroElements = new String[7];
		String myLine = firstLine;

		while((foundElements<7) && myLine != null){
			if(!onFirst){
				try{
					myLine=in.readLine();
				}
				catch(IOException e){
					throw new Exception();
				}
			}
			if(myLine.length() == 0) onFirst = false;
			else if(myLine.charAt(0)=='#') onFirst = false; //Line is commented out, ignore
			else if(myLine.contains("#")) //Return comment-less substring if entire line is not commented
				myLine = myLine.substring(0, myLine.indexOf('#'));
			else if(myLine.contains("Name")){
				distroElements[3]=this.getQuoted(myLine);
				if(distroElements[3].contains("Latest"))
					latest = true;
				foundElements++;
				onFirst = false;
			}
			else if(myLine.contains("BaseChannel")){
				distroElements[0]=this.getQuoted(myLine);
				foundElements++;
				onFirst = false;
			}
			else if(myLine.contains("KSTree")){
				distroElements[1]=this.getQuoted(myLine);
				foundElements++;
				onFirst = false;
			}
			else if(myLine.contains("Architecture")){
				distroElements[2]=this.getQuoted(myLine);
				foundElements++;
				onFirst = false;
			}
			else if(myLine.contains("SatChannel")){
				distroElements[4]=this.getQuoted(myLine);
				foundElements++;
				onFirst = false;
			}
			else if(myLine.contains("VirtHost")){
				distroElements[5]=this.getQuoted(myLine);
				foundElements++;
				onFirst = false;
			}
			else if(myLine.contains("VirtGuest")){
				distroElements[6]=this.getQuoted(myLine);
				foundElements++;
				onFirst = false;
			}
			//else if(myLine.contains("#")) onFirst = false;
			else
				break;
		}
		if(foundElements==7)
			distList.add(new ODistribution(distroElements[0],
										   distroElements[1],
									       distroElements[2],
									       distroElements[3],
									       latest,
									       distroElements[4],
									       distroElements[5],
									       distroElements[6]
									       ));
	}

	/**
	 * Constructor to initalize all instance variables with data
	 * from OctoKick script file at specified filename
	 * @param filename filename of OctoKick script file
	 */
	public OScriptParser(String filename) throws Exception{
		sysList = new LinkedList<OSystem>();
		distList = new LinkedList<ODistribution>();
		arches = new LinkedList<String>();
		BufferedReader in = null;
		int parseState = 0;
		try{
			FileReader fis = new FileReader (filename);
			in = new BufferedReader (fis);
			String myLine = in.readLine();
			while (myLine != null){
				if((myLine.length()>2)){
					while(myLine.charAt(0)=='#')
						myLine = in.readLine();

					if(myLine.contains("[General]")){
					//	System.out.println("Parse State = 1");
						parseState = 1;
						myLine = in.readLine();
					}
					else if (myLine.contains("[SSHKey]")){
				//		System.out.println("Parse State = 2");
						parseState = 2;
						myLine = in.readLine();
					}
					else if (myLine.contains("[Systems]")){
				//		System.out.println("Parse State = 3");
						parseState = 3;
						myLine = in.readLine();
						System.out.println("HOSTNAME = "+ myLine);
					}
					else if (myLine.contains("[Distributions]")){
						//System.out.println("Parse State = 4");
						parseState = 4;
						myLine = in.readLine();
					}
					switch(parseState){
						case 1:
							if(myLine.contains("TestName"))
								this.testName = getQuoted(myLine);
							if(myLine.contains("UseCustomSSH"))
								this.customSSH = getBoolean(myLine);
							if(myLine.contains("UseSpecifiedPaths"))
								this.customPaths = getBoolean(myLine);
							if(myLine.contains("RootPwd"))
								this.rootpwd = getQuoted(myLine);
							//if(myLine.contains("SatelliteHostname"))
								//HarnessConfiguration.RHN_HOST = getQuoted(myLine);
							break;
						case 2:
							this.sshKey = myLine;
							parseState = 0;
							break;
						case 3:
							this.addSystem(myLine);
							break;
						case 4:
							this.addDistro(in,myLine);
							break;
					}
				}
				myLine = in.readLine();
			}
			in.close();
		}
		catch(Exception e){
			log.info("error message = "+e.getMessage());
			
			String badSection = "[not in a section]";
			switch(parseState){
				case 1:
					badSection = "[General]";
					break;
				case 2:
					badSection = "[SSHKey]";
					break;
				case 3:
					badSection = "[Systems]";
					break;
				case 4:
					badSection = "[Distributions]";
					break;
			}
			for(int i=0; i<20; i++){
			//this is hard to see in the logs, so I'm making it annoyingly verbose
			
			log.info("<li>CHECK YOUR OCTOSCRIPT CFG FILE, Error encountered while reading "+badSection+ "section of "+
					    "OctoKick script file.");
			}
			throw new Exception();
		}
		this.populateArches();
	}
}
