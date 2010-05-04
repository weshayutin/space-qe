package com.redhat.rhn.harness.regression.satellite531;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

/**
 * 
 * @see Errata: https://errata.devel.redhat.com/errata/info/9051
 * @see Bugzilla: https://bugzilla.redhat.com/show_bug.cgi?id=508771
 * @author gkhachik
 *
 */
public class BZ_508771 extends SeleniumSetup{
	protected RhnHelper rh = new RhnHelper();
	public static final String datePattern = "MM/dd/yyyy";
	
	@BeforeClass(groups = { "setup" })
	public void test00_preparing(){
		task_RhnBase.OpenAndLogIn();
		task_TestPrep.removeAllProfilesOfASystem(IRhnBase.SYSTEM_HOSTNAME01);
		task_TestPrep.registerSystemToSatellite(
				IRhnBase.SYSTEM_HOSTNAME01, true, false);
		task_RhnBase.SignOut();
	}
	
	@Test(groups = { "bz-508771" })
	public void test01_sortErratasByDateForSystem() throws ParseException{
		task_RhnBase.OpenAndLogIn();
		task_RhnBase.goToSystem(IRhnBase.SYSTEM_HOSTNAME01);
		rh.clickLink("link="+IRhnBase.SYSTEM_HOSTNAME01, true);
		
		rh.clickLink("xpath=//ul[@class='content-nav-rowone']/li[2]/a[text()='Software']", "Software", true);
		rh.clickLink("xpath=//ul[@class='content-nav-rowtwo']/li[1]/a[text()='Errata']", "Errata", true);
		assertTrue(rh.isElementPresent("xpath=//table[@class='list']/thead/tr[1]/th[@class='descSort']",false));
		
		// sort ascending
		if(sel.isElementPresent("xpath=//table[@class='list']/thead/tr[1]/th[@class='descSort']")){
			sel.clickAndWait("xpath=//table[@class='list']/thead/tr[1]/th[@class='descSort']/a");
		}
		
		int errataCount = NumberFormat.getInstance().parse(sel.getText("id=list_total").trim()).intValue();
		int erratasInPage = NumberFormat.getInstance().parse(sel.getText("id=list_max").trim()).intValue();
		int pagesCount = errataCount / erratasInPage;
		if(errataCount%erratasInPage > 0) pagesCount++;
		Date curDate=null, prevDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
		for(int i=0;i<pagesCount;i++){
			for(int j=0;j<erratasInPage;j++){
				// for the last page there could be less elements than the erratasInPage has, but it's ok
				// as we are using the isElementPresent method...
				if(sel.isElementPresent("xpath=//table[@class='list']/tbody/tr["+(j+1)+"]/td[6]")){
					try{
						prevDate = curDate;	
						curDate = formatter.parse(sel.getText("xpath=//table[@class='list']/tbody/tr["+(j+1)+"]/td[6]").trim());
					}catch(Exception ex){
						continue;
					}
					if(prevDate!=null && curDate!=null){
						assertTrue(prevDate.before(curDate) || prevDate.compareTo(curDate)==0);
					}
				}
			}
			if(sel.isElementPresent("xpath=//input[@type='image' and @alt='Next Page']"))
				sel.clickAndWait("xpath=//input[@type='image' and @alt='Next Page']");
		}
		task_RhnBase.SignOut();
	}

	@Test(dependsOnMethods="test01_sortErratasByDateForSystem", 
			groups = { "bz-508771" })
	public void test02_sortErratasByDateForChannel() throws ParseException{
		task_RhnBase.OpenAndLogIn();
		page_Channels.open();
		rh.clickLink("link=Red Hat Channels", true);
		rh.clickLink("link="+IRhnBase.RHN_CHANNEL01, true);
		rh.clickLink("xpath=//ul[@class='content-nav-rowone']/li[2]/a[text()='Errata']", "Errata", true);
		
		assertTrue(rh.isElementPresent("xpath=//table[@class='list']/thead/tr[1]/th[@class='descSort']",false));
		
		// sort ascending
		if(sel.isElementPresent("xpath=//table[@class='list']/thead/tr[1]/th[@class='descSort']")){
			sel.clickAndWait("xpath=//table[@class='list']/thead/tr[1]/th[@class='descSort']/a");
		}
		
		int errataCount = NumberFormat.getInstance().parse(sel.getText("id=list_total").trim()).intValue();
		int erratasInPage = NumberFormat.getInstance().parse(sel.getText("id=list_max").trim()).intValue();
		int pagesCount = errataCount / erratasInPage;
		if(errataCount%erratasInPage > 0) pagesCount++;
		Date curDate=null, prevDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
		for(int i=0;i<pagesCount;i++){
			for(int j=0;j<erratasInPage;j++){
				// for the last page there could be less elements than the erratasInPage has, but it's ok
				// as we are using the isElementPresent method...
				if(sel.isElementPresent("xpath=//table[@class='list']/tbody/tr["+(j+1)+"]/td[6]")){
					try{
						prevDate = curDate;	
						curDate = formatter.parse(sel.getText("xpath=//table[@class='list']/tbody/tr["+(j+1)+"]/td[6]").trim());
					}catch(Exception ex){
						continue;
					}
					if(prevDate!=null && curDate!=null){
						assertTrue(prevDate.before(curDate) || prevDate.compareTo(curDate)==0);
					}
				}
			}
			if(sel.isElementPresent("xpath=//input[@type='image' and @alt='Next Page']"))
				sel.clickAndWait("xpath=//input[@type='image' and @alt='Next Page']");
		}
		task_RhnBase.SignOut();
	}
		
}
