package com.redhat.rhn.harness.Sat51.pages;

import com.redhat.rhn.harness.baseInterface.IRhnBase;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;

public class SatelliteToolsPage extends com.redhat.rhn.harness.Sat50.pages.SatelliteToolsPage{
	
	RhnHelper rh = new RhnHelper();
	
	public String getLocation() {
		 return "https://"+IRhnBase.SERVER_HOSTNAME + "/rhn/admin/multiorg/Organizations.do";
	}
	
	public void clickLink_Organizations(){
		rh.clickLink("link=Organizations",true);
	}
	
	public String getOrgID(){
		String id = rh.getText("xpath=//table/tbody/tr/td[2]/form/table/tbody/tr[2]/td/");
		return id;
	}
	
	public void clickLink_UsersLeftMenu(){
		rh.openLink(IRhnBase.SERVER_HOSTNAME+"/rhn/admin/multiorg/Users.do", true, "SatTools_Users");
	}
	
	public void clickLink_SubscriptionsLeftMenu(){
		rh.openLink(IRhnBase.SERVER_HOSTNAME+"/rhn/admin/multiorg/SoftwareEntitlements.do", true, "SatTools_Users");
	}
	
	public void clickLink_Org_SystemEntitlments(){
		rh.clickLink("link=System Entitlements",true);
	}
	
	public void clickLink_Org_SoftwareChannelEntitlments(){
		rh.clickLink("link=Software Channel Entitlements",true);
		//rh.clickLink("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div[2]/div/div[2]/ul/li[2]/a",true);
	}
	
	public void clickLink_CreateNewOrganization(){
		rh.clickLink("link=create new organization",true);
	}
	
	public void setTxt_OrganizationName(String txt){
		sel.setText("xpath=//input[@name='orgName']", txt);
    }
	
	public void setTxt_Organization_Login(String txt){
		sel.setText("xpath=//input[@name='login']", txt);
    }
	public void setTxt_Organization_Email(String txt){
		sel.setText("xpath=//input[@name='email']", txt);
    }
	public void setTxt_Organization_DesiredPassword(String txt){
		sel.setText("xpath=//input[@name='desiredpassword']", txt);
    }
	public void setTxt_Organization_ConfirmPassowrd(String txt){
		sel.setText("xpath=//input[@name='desiredpasswordConfirm']", txt);
    }
	
	public void setTxt_Organization_FirstName(String txt){
		sel.setText("xpath=//input[@name='firstNames']", txt);
    }
	public void setTxt_Organization_LastName(String txt){
		sel.setText("xpath=//input[@name='lastName']", txt);
    }
	public void clickButton_CreateOrganization(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Create Organization']",true);
    }
	
	public void clickButton_UpdateOrganization(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Update Organization']",true);
    }
	
	public void clickButton_DeleteOrganization(){
        rh.clickButton("xpath=//input[@type='submit' and @value='Delete Organization']",true);
    }
	
	public void clickLink_DeleteOrganization(){
		rh.clickLink("link=delete organization",true);
	}
	
	 public String getOrgEntitlement_Mangement(int column){
	    	//try{
		    String result = "null";
		    if(column == IRhnBase.ORG_ENTITLEMENT_TOTAL)
		    	result = sel.getText("xpath=//tr/td[2]/form/table/tbody/tr/td[2]");
		    	log.fine("Looking at Total");
	    	/*}
	    	catch(Exception e){
	    	return sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/table/tbody/tr/td[2]");	
	    	}*/
		    if(column == IRhnBase.ORG_ENTITLEMENT_AVAILABLE)
		    	result = sel.getText("xpath=//tr/td[2]/form/table/tbody/tr/td[3]");
		    	log.fine("Looking at Available");
		    
		    if(column == IRhnBase.ORG_ENTITLEMENT_USAGE)
		    	result = sel.getText("xpath=//tr/td[2]/form/table/tbody/tr/td[4]");
		    	log.fine("Looking at Usage");
		    return result;
	    }
	 
	 public String getOrgEntitlement_Provisioning(int column){
		    String result = "null";
		    if(column == IRhnBase.ORG_ENTITLEMENT_TOTAL)
		    	result = sel.getText("xpath=//tr/td[2]/form/table/tbody/tr[3]/td[2]");
	
		    if(column == IRhnBase.ORG_ENTITLEMENT_AVAILABLE)
		    	result = sel.getText("xpath=//tr/td[2]/form/table/tbody/tr[3]/td[3]");
		    
		    if(column == IRhnBase.ORG_ENTITLEMENT_USAGE)
		    	result = sel.getText("xpath=//tr/td[2]/form/table/tbody/tr[3]/td[4]");
		    
		    return result;
	    }
	
	 
	 public String getOrgSoftwareChannelEntitlement(int column){
		    String result = "null";
		    if(column == IRhnBase.ORG_ENTITLEMENT_TOTAL)
		    	result = sel.getText("xpath=//tr/td[2]/form/table/tbody/tr/td[2]");
		    if(column == IRhnBase.ORG_ENTITLEMENT_AVAILABLE)
		    	result = sel.getText("xpath=//tr/td[2]/form/table/tbody/tr/td[3]");
		    if(column == IRhnBase.ORG_ENTITLEMENT_USAGE)
		    	result = sel.getText("xpath=//tr/td[2]/form/table/tbody/tr/td[4]");
		    return result;
	    }

	 public void clickLink_Subscriptions(){
			rh.clickLink("xpath=//tr/td[2]/div[2]/ul/li[3]/a","Subscriptions",true);
		}

		public void setTxt_Organization_SystemEntitlement_Base(String txt){
			sel.setText("xpath=//input[@name='enterprise_entitled']", txt);
	    }

		public void setTxt_Organization_SystemEntitlement_Monitoring(String txt){
			sel.setText("xpath=//input[@name='monitoring_entitled']", txt);
	    }

		public void setTxt_Organization_SystemEntitlement_Provisioning(String txt){
			sel.setText("xpath=//input[@name='provisioning_entitled']", txt);
	    }

		public void setTxt_Organization_SystemEntitlement_Virt(String txt){
			sel.setText("xpath=//input[@name='virtualization_host']", txt);
	    }

		public void setTxt_Organization_SystemEntitlement_VirtPlatform(String txt){
			sel.setText("xpath=//input[@name='virtualization_host_platform']", txt);
	    }

		public void setTxt_Organization_SoftwareChannelEntitlement(String Channel, String number){
			for (int i=1; i<21; i++){
				//System.out.println(sel.getText("xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/form/div/table/tbody/tr["+i+"]/td"));
				if(sel.getText("xpath=//form/table/tbody/tr["+i+"]/td/a").equalsIgnoreCase(Channel)){
					sel.setText("xpath=//form/table/tbody/tr["+i+"]/td[4]/input", number);

					break;
				}
				//System.out.println("break");
			}
		}
		
		public void clickLink_Monitoring(){
			rh.clickLink("xpath=//td[2]/div[3]/ul/li[2]/a","Monitoring",true);
							    //html/body/div/div[5]/table/tbody/tr/td[2]/div[3]/ul/li[2]/a
		}

		
		
		
}
