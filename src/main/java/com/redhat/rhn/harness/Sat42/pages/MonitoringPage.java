package com.redhat.rhn.harness.Sat42.pages;

import com.redhat.rhn.common.pages.RhnPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.thoughtworks.selenium.SeleniumException;

public class MonitoringPage extends RhnPage{

	RhnHelper rh = new RhnHelper();

	public String getLocation(){
        return "https://"+HarnessConfiguration.RHN_HOST + "/rhn/monitoring/ProbeList.do";
    }

	public void clickLink_Status(){
        rh.clickLink("link=Status",true);
    }

	public void clickLink_Notification(){
        rh.clickLink("link=Notification",true);
    }

	//ProbeSuites
	public void clickLink_ProbeSuites(){
        rh.clickLink("link=Probe Suites",true);
    }

	//Scout Config Push
	public void clickLink_ScoutConfigPush(){
        rh.clickLink("link=Scout Config Push",true);
    }
	
	

	//General Config
	public void clickLink_GeneralConfig(){
        rh.clickLink("link=General Config",true);
    }
	
	public void clickButton_PushScoutConfigs(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Push Scout Configs']", true);
	}
	
	public void clickLink_RHN_Monitoring_Scout(){
        rh.clickLink("link=RHN Monitoring Scout",true);
    }
	
	public void clickLink_RHN_Proxy_Scout(){
        rh.clickLink("link=RHN Proxy*",true);
    }
	
	public String getTxt_RHNMD_public_key_for_Scout(){
		String key = null;
		key = sel.getText("xpath=//tbody/tr/td[2]/pre");
		log.info("key is = "+ key);
		return key;
	}
	
	public void clickLink_CreateNewProbeSuite(){
        rh.clickLink("link=create new probe suite",true);
		//rh.clickLink("link=xpath=//html/body/div/div[5]/table/tbody/tr/td[2]/div/div/span/a","create new probe suite",true);
        ///html/body/div/div[5]/table/tbody/tr/td[2]/div/div/span/a
    }
	
	public void clickLink_CreateNewProbe(){
        rh.clickLink("link=create new probe",true);

    }
	

	public void setTxt_Name(String txt){
		sel.setText("name=suite_name", txt);
    }
	
	public void setTxt_Description(String txt){
		sel.setText("name=description", txt);
    }
	
	public void clickButton_CreateProbeSuite(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Create Probe Suite']", true);
	}

	public void clickLink_Probes(){
        rh.clickLink("link=Probes",true);
    }
	
	public void select_Command_Group(String groupName, boolean waitForPage){
		rh.selectComboBoxItem("command_group", groupName, waitForPage);
	}
	
	public void select_Command(String command){
		rh.selectComboBoxItem("command", command , true);
	}
	
	public void select_ProbeCheckInterval(){
		rh.selectComboBoxItem("notification_interval_min", "1 minute" , false);
	}
	
	public void select_NotificationInterval(){
		rh.selectComboBoxItem("check_interval_min", "1 minute" , false);
	}
	
	public void check_Probe_Notifications(boolean checkIt){
		rh.checkRadioButton("name=notification", checkIt);
	}
	
	public void clickButton_CreateProbe(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Create probe']", true);
	}
	
	public void clickButton_DeleteProbeSuite(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Delete Probe Suites']", true);
	}
	
	
	public void clickButton_AddSystemsToProbeSuite(){
		rh.clickButton("xpath=//input[@type='submit' and @value='Add systems to probe suite']", true);
	}
	
	
	public void clickLink_Add_Systems_to_Probe_Suite(){
        rh.clickLink("link=add systems to probe suite",true);
    }
	
	public void clickLink_Systems(){
        rh.clickLink("xpath=//div[2]/ul/li[3]/a","Systems",true);
    }
	
	
	//MEMORY
	public void setTxt_WarnIfBelow(String txt){
		sel.setText("name=param_warn", txt);
    }
	
	public void setTxt_CriticalIfBelow(String txt){
		sel.setText("name=param_critical", txt);
    }
	//MEMORY
	
	//NETWORK PING
	public void setTxt_CriticalIfAbove(String txt){
		sel.setText("name=param_critical_time", txt);
    }
	
	public void setTxt_WarnIfAbove(String txt){
		try{
		sel.setText("name=param_warn_time", txt);
		}
		catch(SeleniumException se){
			sel.setText("name=param_warning_time", txt);
		}
    }
	
	public void setTxt_CriticalIfAboveLoss(String txt){
		sel.setText("name=param_critical_loss", txt);
    }
	
	public void setTxt_WarnIfAboveLoss(String txt){
		sel.setText("name=param_warn_loss", txt);
    }
	
	//NETWORK PING
	
	
	
	//APACHE
	public void setTxt_ApacheChild_CriticalIfAbove(String txt){
		sel.setText("name=param_criticalchild", txt);
    }
	
	public void setTxt_ApacheChild_WarnIfAbove(String txt){
		sel.setText("name=param_warnchild", txt);
    }
	
	public void setTxt_Critical(String txt){
		sel.setText("name=param_critical", txt);
    }
	
	public void setTxt_Warning(String txt){
		sel.setText("name=param_warning", txt);
    }
	
	public void setTxt_Warn(String txt){
		sel.setText("name=param_warn", txt);
    }
	
	
	public void setTxt_ApacheSlot_CriticalIfAboveLoss(String txt){
		sel.setText("name=param_criticalslot", txt);
    }
	
	public void setTxt_ApacheSlot_WarnIfAboveLoss(String txt){
		sel.setText("name=param_warnslot", txt);
    }
	
	//APACHE
	
	//Linux Uptime
	public void setTxt_LinuxLoad_Critical(String txt,String minute){
		sel.setText("name=param_critical"+minute, txt);
    }
	
	public void setTxt_LinuxLoad_Warn(String txt,String minute){
		sel.setText("name=param_warn"+minute, txt);
    }
	//Linux Uptime

	//TCP CHECK
	public void setTxt_TCP_CHECK_SEND(String txt){
		sel.setText("name=param_send", txt);
    }
	
	public void setTxt_TCP_CHECK_EXPECT(String txt){
		sel.setText("name=param_expect", txt);
    }
	
	public void setTxt_TCP_CHECK_Port(String txt){
		sel.setText("name=param_r_port_0", txt);
    }

	//ORACLE
	public void setTxt_Oracle_SID(String txt){
		sel.setText("name=param_ora_sid", txt);
    }
	
	public void setTxt_Oracle_user(String txt){
		sel.setText("name=param_ora_user", txt);
    }
	
	public void setTxt_Oracle_passwd(String txt){
		sel.setText("name=param_ora_password", txt);
    }
	
	public void setTxt_Oracle_passwd_confirm(String txt){
		sel.setText("name=param_ora_password_confirm", txt);
    }
	
	//FTP
	
	public void setTxt_Username(String txt){
		sel.setText("name=param_username", txt);
    }
	
	public void setTxt_Password(String txt){
		try{
		sel.setText("name=param_password", txt);
		}
		catch(SeleniumException se){
			sel.setText("name=param_pass", txt);
		}
    }
	
	public void setTxt_Password_Confirm(String txt){
		try{
		sel.setText("name=param_confirm", txt);
		}
		catch(SeleniumException se){
			try{
			sel.setText("name=param_pass_confirm", txt);
			}
			catch(Exception e){
				sel.setText("name=param_password_confirm", txt);
			}
		}
    }
	
	//Remote Ping
	public void setTxt_Remote_IP_Address(String txt){
		sel.setText("name=param_ip", txt);
    }
	
	//HTTPS
	public void setTxt_URL_PATH(String txt){
		sel.setText("name=param_url", txt);
    }

	//Oracle
	public void setTxt_Critical_Latency(String txt){
		sel.setText("name=param_critical_latency", txt);
    }
	
	public void setTxt_Warning_Latency(String txt){
		sel.setText("name=param_warn_latency", txt);
    }
	
	//MySQL
	
	public void setTxt_User(String txt){
		sel.setText("name=param_user", txt);
    }
	
	public void setTxt_Mysql_DB_Name(String txt){
		sel.setText("name=param_db", txt);
    }
	
	
	
	

}
