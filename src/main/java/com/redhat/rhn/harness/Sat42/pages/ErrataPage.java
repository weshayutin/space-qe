package com.redhat.rhn.harness.Sat42.pages;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

/**
 * Collection of objects (links,buttons,etc..) used when creating and modifying Errata
 * @author whayutin
 *
 */
public class ErrataPage extends RhnPage{

	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + HarnessConfiguration.RHN_SYSTEMS_PAGE;
    }

	RhnHelper rh = new RhnHelper();

	public void click_ErrataLink(){
        rh.clickLink("link=Errata",true);
    }

	public void click_Packages(){
        rh.clickLink("link=Packages",true);
    }

	public void click_Channels(){
        //rh.clickLink("link=Channels",true);
	    //rh.clickLink("xpath=//a[contains(@href, '/rhn/errata/manage/Channels.do)]", "Channels",true);
			rh.clickLink("xpath=//a[contains(@href, '/rhn/errata/manage/Channels.do')]", "Channels",true);
    }
		
	public void click_Details(){
        rh.clickLink("link=Details",true);
    }

	public void click_AddPackages(){
        rh.clickLink("link=Add Packages",true);
    }

	public void click_DeletePackages(){
		rh.clickLink("link=List / Remove Packages",true);
	}
	
	public void click_CreateNewErratum(){
        rh.clickLink("link=create new erratum",true);
    }

	 public void click_RelevantLink(){
	        rh.clickLink("link=Relevant",true);
	    }

	 public void click_AllErrataLink(){
	        rh.clickLink("link=All",true);
	    }
	 public void click_ManageErrataLink(){
	        rh.clickLink("link=Manage Errata",true);
	    }
	 public void click_CloneErrataLink(){
         rh.clickLink("link=Clone Errata",true);
     }

	 public void click_PublishedErrataLink(){
	        rh.clickLink("link=Published",true);
	    }
	 public void click_UnpublishedErrataLink(){
	        rh.clickLink("link=Unpublished",true);
	    }
	 public void clickCreateNewErratum(){
			rh.openLink(HarnessConfiguration.RHN_HOST + "/rhn/errata/manage/Create.do",true, "Create Errata");

	    }

	 public void setNewErrataSynopsis(String txt){

			sel.setText("synopsis", txt);
	    }
	 public void setNewErrataAdvisory(String txt){

			sel.setText("advisoryName", txt);
	    }
	 public void setNewErrataAdvisoryRel(String txt){

			sel.setText("advisoryRelease", txt);
	    }
	 public void selectNewErrataTypeBugFix(){
			rh. selectComboBoxItem("advisoryType", "value=Bug Fix Advisory", false);
	 }

	 public void selectNewErrataTypeProdEnhance(){

			rh. selectComboBoxItem("advisoryType", "value=Product Enhancement Advisory", false);

	 }
	 public void selectNewErrataTypeSecurityAdv(){

			rh. selectComboBoxItem("advisoryType", "value=Security Advisory", false);
	 }

	 public void selectErrataViewChannel(String channel){
			rh.selectComboBoxItem("view_channel", "value="+channel, false);
	 }

	 public void setNewErrataProduct(String txt){
			sel.setText("product", txt);
	    }

	 public void setNewErrataTopic(String txt){

			sel.setText("topic", txt);
	    }
	 public void setNewErrataDescription(String txt){

			sel.setText("description", txt);

	    }
	 public void setNewErrataSolution(String txt){

			sel.setText("solution", txt);
	    }
	 public void clickButton_CreateNewErratum(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Create Errata']",true);

	    }
	 public void clickButton_DeleteErratum(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Delete Errata']",true);

	    }
	 public void clickButton_SelectAllErrata(){
	        //rh.clickButton("xpath=//input[@type='submit' and @value='Select All']",true);
		    rh.clickImage("alt=Select All", "Select All", true);
	    }

	 public void clickButton_View(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='View']",true);
	    }

	 public void clickButton_AddPackages(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Add Packages']",true);
	    }
	 
	 public void clickButton_DeletePackages(){
		 rh.clickButton("xpath=//input[@type='submit' and @value='Remove Packages']",true);
	 }
	 
	 public void clickButton_UpdateChannels(){
		 rh.clickButton("xpath=//input[@type='submit' and @value='Update Channels']",true);
	 }

	 public void clickButton_ConfirmDelete(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Confirm']",true);

	    }

		public void check_List(){
			//sel.check("name=items_selected");
			//rh.checkRadioButton("name=items_selected",true);
			rh.checkRadioButton("name=checkall",true);
		}

		public void uncheck_List(){
			//sel.uncheck("name=items_selected");
			//rh.checkRadioButton("name=items_selected",false);
			rh.checkRadioButton("name=checkall",false);
		}
	 public void clickButton_PublishNewErratum(){
	        rh.clickButton("xpath=//input[@type='submit' and @value='Publish Errata']",true);

	    }



}
