package com.redhat.rhn.harness.common;

import java.util.logging.LogRecord;
import java.util.logging.Level;

import com.redhat.rhn.harness.common.LogFormatter;
import com.redhat.qe.auto.selenium.MyLevel;

public class SatelliteTestProcedureFormatter extends LogFormatter{

	@Override
	public String format(LogRecord record) {
		if (!record.getLevel().equals(Level.INFO) || 
		    !record.getLevel().equals(MyLevel.ACTION)) return super.format_clean(record);
		else {
			String throwable = "";
			if (record.getThrown() != null) throwable = throwableToString(record.getThrown())  + "\n";
			return record.getMessage() + "<br>\n" + throwable;
		}
	}
	
}
