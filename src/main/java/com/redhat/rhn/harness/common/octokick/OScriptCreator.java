package com.redhat.rhn.harness.common.octokick;

import com.redhat.rhn.harness.common.SeleniumSetup;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.qe.auto.selenium.ExtendedSelenium;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class OScriptCreator extends SeleniumSetup{
	
	RhnHelper rh = new RhnHelper();
	
		
	private String filename;
	private String arch;
	private Object[] searchterms;
	private Object[] versions;
	private String octoScript;
	
	public OScriptCreator(String filename){
		this.filename = filename;
		this.arch = "NoData";
		this.searchterms = null;
		this.versions = null;
	}
	
	public OScriptCreator(String arch, Object[] searchterms, Object[] versions, String filename){
		this.arch = arch;
		this.searchterms = searchterms;
		this.versions = versions;
		this.filename = filename;
		this.octoScript = "";
	}
		
	public void buildScript(){
		ArrayList<ArrayList> childChannels = new ArrayList<ArrayList>();
		ArrayList<String> baseChannels = new ArrayList<String>();
		
		task_RhnBase.OpenAndLogIn();
		page_KickStart.open();
		page_KickStart.clickLink_ViewListKickstartProfiles();
		page_KickStart.openLink_CreateNewKickstartProfile();
		String[] base = ExtendedSelenium.getInstance().getSelectOptions("name=currentChannelId");
		for(int i=0;i<base.length;i++){
			if(base[i].contains("Red Hat") 
					&& !base[i].contains("Clone"))
				baseChannels.add(base[i]);
		}
		for(int i=0;i<baseChannels.size();i++){
			ArrayList<String> nodeChannels = new ArrayList<String>();
			page_KickStart.select_BaseChannel(baseChannels.get(i),true);
			try{
			    String[] subs = ExtendedSelenium.getInstance().getSelectOptions("name=kstreeId");
			    for(int j=0;j<subs.length;j++){
			    	if(subs[j].length()>3)
			    		nodeChannels.add(subs[j]);
			    }
			    childChannels.add(nodeChannels);
			}
			catch (Exception e){childChannels.add(nodeChannels);}
		}
		for(int i=0;i<baseChannels.size();i++){
			for(int j=0;j<childChannels.get(i).size();j++){
				if(this.searchterms == null)
					printEntry((String)childChannels.get(i).get(j),
							   (String)baseChannels.get(i),
							   (String)childChannels.get(i).get(j));
				else if(searchMatches((String)childChannels.get(i).get(j)))
					printEntry((String)childChannels.get(i).get(j),
							   (String)baseChannels.get(i),
							   (String)childChannels.get(i).get(j));
			}
		}
		this.tearDown();
		
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(this.filename));
			out.write(octoScript);
			out.close();
			System.out.println(octoScript);
		}
		catch(Exception e){
			System.out.println("OctoScript write failed!");
		}
	}
	
	private boolean searchMatches(String distribution){
		if(distribution.contains(this.arch))
			for(int i=0;i<this.searchterms.length;i++)
				if(distribution.contains((String)this.searchterms[i]))
					for(int j=0;j<this.versions.length;j++)
						if(distribution.contains((String)this.versions[j]))
							return true;
		return false;
	}
	
	private void printEntry(String name, String basechannel, String kstree){
		this.octoScript += "Name=\""+name+"\"\n";
		this.octoScript += "BaseChannel=\""+basechannel+"\"\n";
		this.octoScript += "KSTree=\""+kstree+"\"\n";
		this.octoScript += "Architecture=\""+this.arch+"\"\n";
		this.octoScript += "SatChannel=\"NoData\"\n";
		this.octoScript += "VirtHost=\"false\"\n";
		this.octoScript += "VirtGuest=\"false\"\n\n";
	}
	
	/*public void testButt(){
		//sel.start();
		System.out.println("I am printing on a line");
		rc.OpenAndLogIn();
		this.buildScript();
		//OScriptCreator myCreator = new OScriptCreator("rickastley");
	}*/
	
	public static void main(String[] args){
		ArrayList<String> searchArray = new ArrayList<String>();
		//searchArray.add("client");
		searchArray.add("server");
		searchArray.add("as");
		searchArray.add("es");
		searchArray.add("ws");
		//searchArray.add("desktop");
		
		ArrayList<String> versionArray = new ArrayList<String>();
		versionArray.add("5-u2");
		versionArray.add("5-u3");
		versionArray.add("4-u7");
		versionArray.add("4-u6");
		//versionArray.add("4-u5");
		//versionArray.add("3-u9");
		

		OScriptCreator myCreator = new OScriptCreator("s390x",
				                                      searchArray.toArray(),
				                                      versionArray.toArray(),
				                                      "/tmp/octoscript");
		myCreator.buildScript();
	}
}