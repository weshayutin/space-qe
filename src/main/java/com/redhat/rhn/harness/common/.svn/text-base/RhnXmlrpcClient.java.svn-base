package com.redhat.rhn.harness.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

public class RhnXmlrpcClient {
	private String sessionKey;
	private XmlRpcClient client;
	
	public RhnXmlrpcClient() throws MalformedURLException, XmlRpcException{
		this.getXMLRPCClient();
	}
	
	public Object authedExec(String methodname, Object ... params) throws XmlRpcException{
		List<Object> paramList = Arrays.asList(params);
		paramList.add(0, sessionKey);
		return client.execute(methodname, paramList);
	}
	public Object authedExecNoParams(String methodname) throws XmlRpcException{
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(0, sessionKey);
		return client.execute(methodname, paramList);
	}
	
	public Object execute(String methodname, Object ... params) throws XmlRpcException{
		return client.execute(methodname, Arrays.asList(params));
	}
	
	public Object executeNoParams(String methodname) throws XmlRpcException{
		return client.execute(methodname, new ArrayList<Object>());
	}
	
	private void getXMLRPCClient() throws MalformedURLException, XmlRpcException{
		client = null;
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL("http://"+HarnessConfiguration.getProperty("rhn.host")+"/rpc/api"));

		client = new XmlRpcClient();
		client.setConfig(config);

		HttpClient httpClient = new HttpClient();
		XmlRpcCommonsTransportFactory fac = new XmlRpcCommonsTransportFactory(
				client);
		fac.setHttpClient(httpClient);
		client.setTransportFactory(fac);
		
		String username = HarnessConfiguration.getProperty("rhn.user");
		String password = HarnessConfiguration.getProperty("rhn.pass");
		
		sessionKey = (String) this.execute("auth.login", username, password);
	}
}
