package com.redhat.rhn.common.pages;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.redhat.qe.auto.selenium.ExtendedSelenium;
import com.redhat.qe.auto.selenium.PopUpHandler;
import com.redhat.qe.tools.ExecCommands;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.SeleniumSetup;

public abstract class RhnPage extends Page{

	//SeleniumSetup selSetup = new SeleniumSetup();

	protected static Logger log = Logger.getLogger(SeleniumSetup.class.getName());
	
	
	
	
	//westest


    public void openAndClosePopUps(){
        Runnable runpop = new PopUpHandler();
        Thread threadpop = new Thread(runpop);
        Thread threadpop1 = new Thread(runpop);

        try {
			setup();
        }
		catch (Exception e){
			log.log(Level.SEVERE, "Error initializing selenium", e);	
		}
		
		//moved sel.start to SeleniumSetup.java where it should have been to begin w/
		//still need to test w/ popup code, which is one reason I suspect this code was here.
		/*try{
			ExtendedSelenium.getInstance();
			if running multiple tests w/ junit this is a real problem and you 
			should be aware of selenium start getting called twice.
			
			w/ testng the old instance in a test is never closed so starting
			a new selenium instance actually creates a second instance. For now
			I'm just going to supress the error, as I may run this in junit or testng
			log.log(Level.FINEST,"Selenium already started, not restarting");
			log.log(Level.FINEST,"AUTOMATION BUG!, Login called twice");
		}
		catch(Exception npe){
			log.log(Level.FINEST,"Selenium NOT started, starting a new instance");
		//	log.fine("Browser path: " + HarnessConfiguration.BROWSER_TYPE);
			ExtendedSelenium.newInstance(HarnessConfiguration.SELENIUM_SERVER, HarnessConfiguration.SELENIUM_SERVER_PORT, HarnessConfiguration.BROWSER_TYPE, "http://redhat.com/");
			sel = ExtendedSelenium.getInstance();
			RhnHelper.setSel(sel);
			Page.setSel(sel);
			com.redhat.rhn.harness.pages.Sat42.RhnCommon.setSel(sel);
			sel.start();
		}*/
		

		//skips popup helper if rhn.pop = 0
		if(!HarnessConfiguration.getProperty("rhn.pop").equalsIgnoreCase("0")){
        threadpop.start();
		}
        //log.info("<li>"+getTime()+"     Open URL: " +HarnessConfiguration.PROTOCOL + getLocation());
        log.finer("Open URL: " +HarnessConfiguration.PROTOCOL + getLocation());

        final ExtendedSelenium sel = ExtendedSelenium.getInstance();
        try{
        sel.open(HarnessConfiguration.PROTOCOL+getLocation());
        }
        catch(Exception e){
        	log.log(Level.SEVERE, "Error opening initial URL, stopping selenium", e);
        	sel.stop();
        	ExecCommands ex = new ExecCommands();
        	try{
        		if(HarnessConfiguration.BROWSER_TYPE.equalsIgnoreCase("*iehta"))
        			ex.submitCommandToLocal("c:\\PsTools\\pskill", " IEXPLORE");
        		else
        			ex.submitCommandToLocal("killall", " -9 firefox-bin");
        	}
        	catch(IOException io){
        		log.info("command failed");
        	}

        	sel.start();
        	log.log(Level.FINEST, "starting selenium");
			threadpop1.start();
			sel.open(HarnessConfiguration.PROTOCOL+getLocation());
			//log.info("RELEASE VERSION ="+sel.getText("xpath=//a[contains(@href, '/rhn/help/release-notes/satellite/index.jsp')]"));
		
			
        }
      
        log.log(Level.FINEST, "Open URL: " +HarnessConfiguration.PROTOCOL + getLocation());

        //do not remove
        /*String[] windowNames = sel.getAllWindowNames();
        for (String windowName : windowNames) {
        System.out.println("Window Name: " + windowName);
        sel.windowMaximize(windowName);
        }*/
        try{
        //sel.windowMaximize("main");
        	sel.windowMaximize();

        }
        catch(Exception e){
        	log.info("PLEASE READ!!");
        	log.info("run w/ option -multiWindow, ie  java -jar selenium-server.jar -multiWindow");
        	log.info("for full screen mode.");
        	log.info("");
        	log.info("");
        }

        if(sel.isTextPresent("Red Hat")){
	        	log.finest("pop-ups cleared");
	        	try{
	                //wait 5 seconds for thread to die.
	                threadpop.join(10000);
	            }
	                catch(Exception e){
	                log.log(Level.FINEST, "thread destroy called");
	            }
	        }

    }


}
