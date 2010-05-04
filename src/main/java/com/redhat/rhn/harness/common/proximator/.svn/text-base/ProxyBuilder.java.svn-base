package com.redhat.rhn.harness.common.proximator;

import java.io.IOException;

import com.redhat.qe.tools.ExecCommands;
import com.redhat.rhn.harness.Sat42.pages.ProxyPage;
import com.redhat.rhn.harness.Sat42.pages.RhnCommon;
import com.redhat.rhn.harness.Sat42.pages.SatelliteSystemsPage;
import com.redhat.rhn.harness.common.HarnessConfiguration;
import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;
import com.redhat.rhn.harness.common.Sat42.tasks.TestPrep;

public class ProxyBuilder extends SeleniumSetup {

        RhnHelper rh = new RhnHelper();
        
       // HostedSystemsPage syspage = new HostedSystemsPage();
        
        
        ProxyScriptParser ps = new ProxyScriptParser();
        

        public boolean checkProxyVersion(String proxyNum){
                String mySystem = ps.getProperty("Proxy_"+proxyNum+"_Hostname");
                page_SatelliteSystems.open();
                page_SatelliteSystems.clickLink_AllSystems();
                if(rh.isTextNotPresent("No systems.")){
                        page_RhnCommon.setTxt_FilterBy(mySystem);
                        page_RhnCommon.clickButton_Filter_Go();
                }
                if(rh.isTextPresent(mySystem)){
                        page_RhnCommon.clickLink_SystemName(mySystem);
                        page_SatelliteSystems.clickLink_Proxy();
                        page_Proxy.select_ProxyVersion(ps.getProperty("Proxy_"+proxyNum+"_Version"));
                        page_Proxy.clickButton_ActivateProxy();
                        if(rh.isTextPresent("This machine is currently a licensed")){
                                if(rh.isTextPresent(ps.getProperty("Proxy_"+proxyNum+"_VersionNum")))
                                        return true;
                        }
                        return false;
                }
                return false;
        }

        public boolean systemRegistered(String proxyNum){
                String mySystem = ps.getProperty("Proxy_"+proxyNum+"_Hostname");
                page_SatelliteSystems.open();
                page_SatelliteSystems.clickLink_AllSystems();
                if(rh.isTextNotPresent("No systems.")){
                        page_RhnCommon.setTxt_FilterBy(mySystem);
                        page_RhnCommon.clickButton_Filter_Go();
                }
                return rh.isTextPresent(mySystem);
        }

        public String satRegCommandFactory(){
                return "rhnreg_ks " + " --username="+HarnessConfiguration.RHN_USER+ " --password="+HarnessConfiguration.RHN_PASS+
        " --serverUrl=http://"+HarnessConfiguration.RHN_HOST+"/XMLRPC" + " --email="+HarnessConfiguration.RHN_EMAIL + " --force";
        }

        public void testProxy(){
                ProxyBuilder test = new ProxyBuilder();
                test.rotateProxies();
        }

        public boolean rotateProxies(){
                task_RhnBase.OpenAndLogIn();
                boolean successState = true;
                boolean rotateAllProxies = ps.getPropertyAsInt("Rotate_All_Proxies", 0)==1;
                for(int i=0;i<ps.getPropertyAsInt("Total_Proxies", 0);i++){
                        String proxyNum = String.valueOf(i+1);
                        String mySystem = ps.getProperty("Proxy_"+proxyNum+"_Hostname");
                        if(rotateAllProxies || !this.checkProxyVersion(proxyNum)){
                                if(!systemRegistered(proxyNum))
                                        task_TestPrep.registerSystem(mySystem, satRegCommandFactory(), true, true);
                                successState = buildProxy(proxyNum);
                        }
                }
                return successState;
        }

        public boolean buildProxy(String proxyNum){
                String pfx = "Proxy_"+proxyNum+"_";
                String mySystem = ps.getProperty(pfx+"Hostname");
                task_TestPrep.enableRHNConfigManag(mySystem, false, true);
                ExecCommands exec = new ExecCommands();
                try{
                        exec.submitCommandToLocalWithReturn(true, "ssh", "root@"+mySystem+" rhn_check -vvv");
                }
                catch(IOException ioe){
                        log.info("command failed");
                        return false;
                }
                page_SatelliteSystems.open();
                page_SatelliteSystems.clickLink_AllSystems();
                if(rh.isTextNotPresent("No systems.")){
                page_RhnCommon.setTxt_FilterBy(mySystem);
                page_RhnCommon.clickButton_Filter_Go();
                }
                if(rh.isTextPresent(mySystem)){
                        page_RhnCommon.clickLink_SystemName(mySystem);
                        page_SatelliteSystems.clickLink_Proxy();
                        page_Proxy.select_ProxyVersion(ps.getProperty(pfx+"Version"));
                        page_Proxy.clickButton_ActivateProxy();
                        page_Proxy.clickLink_Continue();
                        page_Proxy.clickLink_IAgree();
                        page_Proxy.setEmailAddress(ps.getProperty(pfx+"EmailAddress"));
                        page_Proxy.setRHNProxyHostname(ps.getProperty(pfx+"Hostname"));
                        page_Proxy.setHTTPProxy(ps.getProperty(pfx+"HTTPProxyServer", ""));
                        page_Proxy.setHTTPProxyUsername(ps.getProperty(pfx+"HTTPProxyPassword",""));
                        page_Proxy.setHTTPProxyPassword(ps.getProperty(pfx+"HTTPProxyUsername", ""));
                        if(ps.getPropertyAsInt(pfx+"EnableSSL",1) == 1)
                                page_Proxy.setEnableSSL();
                        if(ps.getPropertyAsInt(pfx+"EnablePush",1) == 1)
                                page_Proxy.setEnablePush();
                        page_Proxy.clickButton_Continue();
                        page_Proxy.setCACertPassword(ps.getProperty(pfx+"CACertPassword","dog8code"));
                        page_Proxy.setOrganization(ps.getProperty(pfx+"Organization","RHN"));
                        page_Proxy.setOrganizationUnit(ps.getProperty(pfx+"OrganizationalUnit",""));
                        page_Proxy.setEmailAddress(ps.getProperty(pfx+"EmailAddress"));
                        page_Proxy.setCity(ps.getProperty(pfx+"City", "Raleigh"));
                        page_Proxy.setState(ps.getProperty(pfx+"State","NC"));
                        page_Proxy.select_Country(ps.getProperty(pfx+"Country","United States"));
                        page_Proxy.setCertExpiration(ps.getProperty(pfx+"CertExpiration", "5"));
                        page_Proxy.clickButton_Continue();
                        try{
                                exec.submitCommandToLocalWithReturn(true, "ssh", "root@"+mySystem+" rhn_check -vvv");
                        }
                        catch(IOException ioe){
                                log.info("command failed");
                                return false;
                        }
                        return true;
                }
                return false;
        }
}
