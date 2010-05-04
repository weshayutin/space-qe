package com.redhat.rhn.common.pages;

import java.util.logging.Logger;

import org.testng.Assert;

import com.redhat.qe.auto.selenium.ExtendedSelenium;
import com.redhat.qe.auto.selenium.MyLevel;
import com.redhat.qe.auto.selenium.PopUpHandler;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.SeleniumSetup;





public abstract class Page extends SeleniumSetup {
	
	protected static Logger log = Logger.getLogger(Page.class.getName());


    protected static ExtendedSelenium sel = null;
    public static void setSel(ExtendedSelenium sel){
		Page.sel = sel;
	}

    protected String hostname;


    public abstract String getLocation();

    public void open() {
    	log.log(MyLevel.ACTION,"open url "+getLocation());
        sel.open(getLocation());
        
       // sel.waitForPageToLoad(HarnessConfiguration.LONG_TIMEOUT);
    }

    public void genericSubmit() {
        sel.click("_eventId_submit");
      //  sel.waitForPageToLoad(HarnessConfiguration.LONG_TIMEOUT);
    }

    public void genericLongSubmit() {
        sel.click("_eventId_submit");
      //  sel.waitForPageToLoad(HarnessConfiguration.LONG_TIMEOUT);
    }


    /**
     * Asserts the browser is at the correct URL and that the response was neither a 403 nor 404
     *
     */
    public void assertLocation() {
        Assert.assertEquals(sel.getLocation(), getLocation());
        Assert.assertFalse(is403());
        Assert.assertFalse(is404());
    }

    public void refresh() {
        sel.refresh();
    }

    public boolean is404() {
        return sel.isTextPresent("Page Not Found (404)");
    }

    public boolean is403() {
        return sel.isTextPresent("Access Forbidden (403)");
    }

    public boolean isGenericSystemErrorMessagePresent() {
        return sel.isTextPresent("A system error has occurred. The system administrator has been notified");
    }

    public String getCookies() {
        return sel.getCookie();
    }

    public void openAndClosePopups() {
        Runnable runpop = new PopUpHandler();
        Thread threadpop = new Thread(runpop);
        threadpop.start();

        sel.open(getLocation());
       // sel.waitForPageToLoad(HarnessConfiguration.TIMEOUT);

        try {
            // wait 5 seconds for thread to die.
            threadpop.join(5000);
        } catch (Exception e) {
            log.info("thread destroy called");
        }
    }    

	public void goBack() {
		sel.goBack();
		sel.waitForPageToLoad(HarnessConfiguration.TIMEOUT);		
	}
    public void assertErrorsExist() {
        Assert.assertTrue(sel.isElementPresent("errors"));
    }

}
