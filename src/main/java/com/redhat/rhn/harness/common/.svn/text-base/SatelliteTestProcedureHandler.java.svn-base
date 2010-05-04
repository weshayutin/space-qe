package com.redhat.rhn.harness.common;

import java.lang.reflect.Type;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import com.redhat.qe.auto.selenium.MyLevel;
import com.redhat.qe.auto.testopia.ITestProcedureHandler;

/**
 * @author jweiss
 * Class to take in-memory log statements of selenium actions,
 * format them and return them as a big formatted string.
 *
 */
public class SatelliteTestProcedureHandler extends Handler implements ITestProcedureHandler {

	protected StringBuffer sb = new StringBuffer();
	
	public SatelliteTestProcedureHandler() {
 	
		setLevel(MyLevel.ALL);
		setFormatter(new SatelliteTestProcedureFormatter());
    }
	
	@Override
	public void publish(LogRecord record) {
		
		if (isLoggable(record)) 
			sb.append(getFormatter().format(record));
	}
	
	

	@Override
	public void close() throws SecurityException {
		
	}

	@Override
	public void flush() {
		
	}

	public String getLog(){
		return sb.toString();
	}
	
	public void reset(){
		sb = new StringBuffer();
	}
}
