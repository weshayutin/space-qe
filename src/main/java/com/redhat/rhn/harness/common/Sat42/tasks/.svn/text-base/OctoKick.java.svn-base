package com.redhat.rhn.harness.common.Sat42.tasks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;

import com.redhat.qe.tools.ExecCommands;
import com.redhat.rhn.harness.Sat42.pages.RhnCommon;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.redhat.rhn.harness.common.octokick.OKickStart;
import com.redhat.rhn.harness.common.octokick.OPath;
import com.redhat.rhn.harness.common.octokick.OScriptParser;
import com.redhat.rhn.harness.common.octokick.OSystem;
import com.redhat.rhn.harness.common.octokick.OTestGenerator;

/**
 * The new OctoKick, Dont use this class for general kickstarts, see Kickstart.java
 * @author whayutin
 *
 */
public class OctoKick extends SeleniumSetup {
	//package check constants
	public static final int PKG_COMPLETE = 2;
	public static final int PKG_FAIL = 1;
	public static final int PKG_INPROGRESS = 0;
	
	//status log display constants
	static final int CELL_LEN = 30;
	
	private OScriptParser myParser;
	private OKickStart myKS;
	private boolean isInProgress;
	private static final int MAX_TICKS = 600;
	private static final int INTERCYCLE_DELAYSECS = 20;

	//These are public so that these data structures can be accessed outside
	//of this class directly, perhaps by a web interface or something akin to it
	public LinkedList<OPath> KSPaths;
	public LinkedList<OSystem> KSSystems;
	public boolean isPaused;
	public String ksLabel;

	


	private String getTime(){
		DateFormat fmt = new SimpleDateFormat("MMdd-HH:mm:ss:SSS");
		   String now = fmt.format(new Date());
		   return now;
	}

	/*private void logInfo(String txt){
    	String mytime = getTime();

    	//log.info("<li>"+mytime+"     "+txt+"'");
    	log.info(txt)
    	try{
    	BufferedWriter printOut = new BufferedWriter(new FileWriter("/tmp/automation/automation.out",true));
    	printOut.write(mytime+"     "+txt);
    	printOut.newLine();
    	printOut.close();
    	}
    	catch(IOException e){
    	}
    }
*/
	private void printInfo(String format,String txt,boolean formated){
    	//String mytime = getTime();
    	if(formated)
    		System.out.printf(format, txt);
    	else
    		System.out.print(txt);
    	try{
    	
	    	BufferedWriter printOut = new BufferedWriter(new FileWriter("/tmp/selenium-log",true));
	    	printOut.write(txt);
	 
	    	//printOut.newLine();
	    	printOut.close();
    	}
    	catch(IOException e){
    	}
    }

	public OctoKick(){
		try{
			myParser = new OScriptParser(HarnessConfiguration.OCTOKICK_SCRIPTLOC);
		}
		catch(Exception e){
			log.info("OctoKick bailing out...");
			return;
		}
		finishInitialization();
	}

	public static void main(String[] args){
		OctoKick myOcto = new OctoKick();
		myOcto.startOctoTest();
	}

	public OctoKick(String filename){
		try{
			myParser = new OScriptParser(HarnessConfiguration.OCTOKICK_SCRIPTLOC);
		}
		catch(Exception e){
			log.info("OctoKick bailing out...");
			return;
		}
		finishInitialization();
	}


	private void finishInitialization(){
		OTestGenerator myGen = new OTestGenerator();

		myKS = new OKickStart(myParser.getRootPwd(),
							  myParser.getTestName());

		KSSystems = myParser.getSysList();
		//System.out.println("DEBUG: KSSytems = "+ KSSystems);
		KSPaths = myGen.generatePaths(myParser.getDistributions(),
									  myParser.getArches());
		startOctoTest();
	}


	/**
	 * Checks for new Path test conditions and assigns new Paths to Systems
	 * that require them
	 */
	private void updatePaths(){
		for(int i=0;i<KSSystems.size();i++){
			if( (KSSystems.get(i).path == null) || (KSSystems.get(i).path.getStatus()==2) ){
				KSSystems.get(i).path = this.getNextPath(KSSystems.get(i).architecture);
				KSSystems.get(i).path.setSystem(KSSystems.get(i));
			}
		}
	}

	private OPath getNextPath(String arch){
		for(int i=0;i<KSPaths.size();i++)
			if(KSPaths.get(i).getStatus()==0 &&
			   KSPaths.get(i).getLast().getDistro().getArchitecture().contains(arch)){
				KSPaths.get(i).setStatus(1);
				return KSPaths.get(i);
			}
		return null;
	}

	private void checkKickstartStatus(){
		try{
		for(int i=0;i<KSSystems.size();i++){
			OPath currentPath = KSSystems.get(i).path;
			if(!(currentPath==null)){
			    for(int j=0;j<KSSystems.get(i).path.size();j++){
				    if(currentPath.getCurrent().getStatus()==1){ //kickstart status check state
				    	ksLabel = currentPath.getCurrent().getRandLabel();
					    log.info("ksLabel ="+ksLabel);
				    	boolean isComplete = false;
				    	if(HarnessConfiguration.OCTOKICK_USESDC==1){
				    		log.info("DEBUG: Checking status in SDC, OCTOKICK_USESDC=1");

				    		if(currentPath.getCurrent().getDistro().isVirtGuest().equalsIgnoreCase("true")){
				    			isComplete = myKS.kickstart_Status_Virt_Guest_fromSDC(KSSystems.get(i).hostname,
		                                   									currentPath.getCurrent().getDistro().getKSTree(),
		                                   									currentPath.getCurrent().getRandLabel());
				    		}
				    		else{
				    			isComplete = myKS.kickstart_Status_fromSDC(KSSystems.get(i).hostname,
				    				                                   currentPath.getCurrent().getDistro().getKSTree(),
				    				                                   currentPath.getCurrent().getRandLabel());
				    		}
				    	}
				    	else{
				    		log.info("DEBUG: Checking status in KickstartPage,OCTOKICK_USESDC=0");
				    		isComplete = myKS.kickstart_Status(KSSystems.get(i).hostname,
		    		                                           currentPath.getCurrent().getDistro().getKSTree(),
		    		                                           currentPath.getCurrent().getRandLabel());
				    	}
				    	if(isComplete){
				    		log.info("DEBUG: Kickstart isComplete=true, status=2");
					    	currentPath.getCurrent().setStatus(2); //Then set status to "RHN reports successful, waiting for SSH"
				    	}
					    else{
					    	log.info("DEBUG: Kickstart isComplete=false");
					    	currentPath.getCurrent().tick(); //Tick 'elapsed ticks' timer
					    	log.finer("TICKS ="+currentPath.getCurrent().getTicks());
					    }
					    if(currentPath.getCurrent().getTicks() >= MAX_TICKS){
					    	log.info("DEBUG: System NOT responding");
					    	currentPath.getCurrent().setStatus(4); //If system is not responding, fail test
					    }
				    }
				    
				    else if(currentPath.getCurrent().getStatus()==5){ //kickstart package install state
				    	log.info("Checking kickstrt package pickup status...");
				    	int install_status = myKS.checkIfSuccessfulPackageInstall(KSSystems.get(i).hostname);
				    	if (install_status == PKG_COMPLETE){
				    		currentPath.getCurrent().setStatus(1); //packages installed correctly, move along to kickstart check step
				    	}
				    	if ((install_status == PKG_COMPLETE)  && (currentPath.getCurrent().getDistro().isVirtGuest().equalsIgnoreCase("false"))) {
				    		myKS.rebootSystem(KSSystems.get(i).hostname);
				    	}
			    	    else if (install_status == PKG_FAIL)
				    		currentPath.getCurrent().setStatus(4); //packages failed, test fails as a result
				    	else
				    		currentPath.getCurrent().tick(); //we're still waiting on the packages to install
				    	if(currentPath.getCurrent().getTicks() >= MAX_TICKS)
				    		currentPath.getCurrent().setStatus(4); //packages have not installed for a LONG time, fail test
				    }
				    
				    else if(currentPath.getCurrent().getStatus()==2){

				    	if(currentPath.getCurrent().getDistro().isVirtGuest().equalsIgnoreCase("true")){
				    		//String virtGuestHostName=null;
				    		log.finer("DEBUG: Checking virt client status");
				    		String hostname=KSSystems.get(i).hostname;
				    		log.finer("DEBUG: ksLabel = "+ksLabel);
				    		String virtGuestHostName = myKS.StartVirtGuest_GetName(hostname, ksLabel, false);
				    		log.fine("DEBUG: virtGuestHostname = "+virtGuestHostName);
				    		log.finer("DEBUG: Checking SSH Ports");
					    	
						    	if(myKS.checkIfSSHOpen(virtGuestHostName, false)){
						    		currentPath.getCurrent().setStatus(3); //Set status to "Test Complete" if SSH ports are now open
						    		if(currentPath.hasNext())
						    		    currentPath.getNext();
						    		log.fine("Kickstart Complete: SSH ports open");
						    		log.fine("Kickstart Complete: Stopping xen guest "+ hostname+ "; so next test can start");
						    		myKS.StopVirtGuest(hostname, ksLabel, false);
						    	}
							    else{
							    	currentPath.getCurrent().tick(); //Tick 'elapsed ticks' timer
						    		log.finer("TICKS ="+currentPath.getCurrent().getTicks());
							    	}
							    if(currentPath.getCurrent().getTicks() >= MAX_TICKS){
							    	log.info("DEBUG: System NOT responding #2");
							    	currentPath.getCurrent().setStatus(4); //If system is not responding, fail test
							    	}
					    	}

				    else{

				    	log.finer("DEBUG: Checking SSH Ports");
				    	if(myKS.checkIfSSHOpen(KSSystems.get(i).hostname, true)){
				    		currentPath.getCurrent().setStatus(3); //Set status to "Test Complete" if SSH ports are now open
				    		if(currentPath.hasNext())
				    		    currentPath.getNext();
				    		log.finer("Kickstart Complete: SSH ports open");
				    		
				    	}
					    else{
					    	currentPath.getCurrent().tick(); //Tick 'elapsed ticks' timer
					    	log.finer("TICKS ="+currentPath.getCurrent().getTicks());
					    	}
					    if(currentPath.getCurrent().getTicks() >= MAX_TICKS){
					    	log.info("DEBUG: System NOT responding #2");
					    	currentPath.getCurrent().setStatus(4); //If system is not responding, fail test
					    	}
				    	}
				    }
			    }
			}
		}
		}
		catch (Exception e){
			//sel.stop();
			log.log(Level.INFO,"Exception hit whilst checking KS Status; passing and retrying on next rotation",e);
		}
	}


	private void kickstartSystems(){
		this.updatePaths();
		try{
		for(int i=0;i<KSSystems.size();i++){
			if(!(KSSystems.get(i).path == null)){
				//System.out.println("DEBUG: path = "+KSSystems.get(i).path);
			    if(KSSystems.get(i).isUp() &&
			       KSSystems.get(i).path.getCurrent().getStatus()==0){
                    if(KSSystems.get(i).path.getCurrent().getStatus() == 0 ||
                       KSSystems.get(i).path.getCurrent().getStatus() == -1)
                       myKS.checkSynced(KSSystems.get(i).path.getCurrent(),
                    		            HarnessConfiguration.RHN_HOST);
                    if(KSSystems.get(i).path.getCurrent().getDistro().isSyncChecked()){
				        this.kickstartSingleSystem(KSSystems.get(i));
				        System.out.println("DEBUG: KickstartSingleSystem = "+KSSystems.get(i));
				        this.logStatus();
                    }
			    }
			}
		}
		}
		catch (Exception e){
			//sel.stop();
			log.log(Level.INFO, "Exception hit whilst kickstarting system.  Passing and retrying on next rotation",e);
		}
	}

	private void kickstartSingleSystem(OSystem mySystem){
		String kslabel = myKS.testCreateKickStartProfile(mySystem.hostname,
			                                             mySystem.path.getCurrent().getDistro().getRHNName(),
				                                         mySystem.path.getCurrent().getDistro().getKSTree(),
				                                         ksLabel,
				                                         myParser.getSSHKey(),
				                                         mySystem.path.getCurrent().getDistro().isVirtHost(),
				                                         mySystem.path.getCurrent().getDistro().isVirtGuest(), false);
		mySystem.path.getCurrent().setRandLabel(kslabel);

		if(mySystem.path.getCurrent().getDistro().isVirtGuest().equalsIgnoreCase("true")){
			if(!myKS.KickStartVirtSystem(mySystem.hostname,kslabel))
					mySystem.path.getCurrent().setStatus(4);
				else{
					mySystem.path.getCurrent().setStatus(5);
				}
			}

			else{
				if(!myKS.KickStartSystem(mySystem.hostname, kslabel, true))
					mySystem.path.getCurrent().setStatus(4);
				else
					mySystem.path.getCurrent().setStatus(5);
		}
	}

	private void checkIfDone(){
		for(int i=0;i<this.KSPaths.size();i++){
			int j;
			for(j=0;j<this.KSPaths.get(i).size();j++){
				if(this.KSPaths.get(i).get(j).getStatus()!=3)
					break; //If an OTestElement in the Path reports as something other than "Testing Complete", path incomplete
			}
			if(j==this.KSPaths.get(i).size())
				this.KSPaths.get(i).setStatus(2); //If all tests successful, set Path status to "Testing Complete"
		}
		for(int i=0;i<this.KSPaths.size();i++){
			if(this.KSPaths.get(i).getStatus()!=2)
				return;
		}
		this.isInProgress = false; //All testing complete!
	}

	private String clipName(String name){
		if(name.length() <= CELL_LEN)
			return name;
		else
			return name.substring(0, 30);
	}
	
	private void logStatus(){
		this.printDivider();
		//System.out.printf("|%-80s|\n", "Octokick Status");
		printInfo("|%69s|\n", "OCTOKICK STATUS", true);
		printInfo("|%69s|\n", "?=unknown, @=currently kickstarting, $=success", true);
		printInfo("|%69s|\n", "^=syncing channel, ~=SSH Ports, ɸ=pkg install", true);
		printInfo("|%69s|\n", "GPG CHECK = "+ HarnessConfiguration.OCTOKICK_SIGNED_PACKAGES, true);
		for(int i=0;i<KSPaths.size();i++){
			this.printDivider();
			OPath myPath = KSPaths.get(i);
			if ((myPath.getSystem() != null) && (myPath.getStatus() != 3))
				printInfo("|%69s|\n", "Assigned to: " + myPath.getSystem().hostname, true); 
			switch(myPath.getStatus()){
				case 0:
					//System.out.print("|?|");
					printInfo("","|?|",false);
					break;
				case 1:
					//System.out.print("|@|");
					printInfo("","|@|",false);
					break;
				case 2:
					//System.out.print("|$|");
					printInfo("","|$|",false);
					break;
			}
			for(int j=0;j<myPath.size();j++){
				switch(myPath.get(j).getStatus()){
				    case -1:
					    //System.out.print('^');
				    	printInfo("","|^|",false);
					    break;
				    case 0:
						//System.out.print('?');
				    	printInfo("","|?|",false);
						break;
					case 1:
						//System.out.print('@');
						printInfo("","|@|",false);
						break;
					case 2:
						//System.out.print('~');
						printInfo("","|~|",false);
						break;
					case 3:
						//System.out.print('$');
						printInfo("","|$|",false);
						break;
					case 4:
						//System.out.print('!');
						printInfo("","|!|",false);
						break;
					case 5:
						printInfo("","|ɸ|",false);
						break;
				}
				//System.out.printf("%19s|", myPath.get(j).getDistro().getSymName());
				printInfo("%-30s|",clipName(myPath.get(j).getDistro().getSymName()),true);
			}
			//System.out.print('\n');
			printInfo("","\n",false);
		}
		this.printDivider();
	}

	private void printDivider(){
		printInfo("","+",false);
		for(int i=0;i<69;i++)
			printInfo("","-",false);
		printInfo("","+\n",false);
	}

	public void startOctoTest(){
		task_RhnBase.OpenAndLogIn();
		this.isInProgress = true;
		this.isPaused = false;
		this.rotationalScheduler();
	}
	
	public void clearFirefoxen(){
		//tp.command_kill_Firefox_OnSeleniumServer();
		sel.stop();
		task_TestPrep.sleepForSeconds(2);
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.sleepForSeconds(1);
	}

	private void rotationalScheduler(){	
		while(isInProgress && !isPaused){
			this.logStatus();
			this.kickstartSystems();
			this.checkKickstartStatus();
			this.checkIfDone();
			this.clearFirefoxen();
		}
	}
}
