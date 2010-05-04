package com.redhat.rhn.harness.common.Space01.tasks;

import java.util.Calendar;

import com.redhat.rhn.harness.Space01.pages.ErrataDetailsPage;

public class ErrataDateVerifyVisitor extends PageVisitor {

	private Calendar start;
	private Calendar end;
	
	
	public ErrataDateVerifyVisitor(int columnIn) {
		super(columnIn);
	}
	
	

	@Override
	public boolean execute() {
		ErrataDetailsPage edp = new ErrataDetailsPage();
		String date = edp.getIssueDate();
		verifyDate(date, start, end);
		return false;
	}

	
	private void verifyDate(String date, Calendar before, Calendar after) {

		String[] dates = date.split("/");
		
		int month = Integer.parseInt(dates[0]);
		int day = Integer.parseInt(dates[1]);
		int year = Integer.parseInt("20" + dates[2]);
		
	
		Calendar cal = (Calendar) before.clone();
		cal.set(Calendar.MONTH, month -1);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.YEAR, year);
		
		assertTrue(cal.compareTo(before) >= 0) ;
		assertTrue(cal.compareTo(after) <= 0) ;
	}



	public void setStart(Calendar start) {
		this.start = start;
	}



	public void setEnd(Calendar end) {
		this.end = end;
	}

	
}
