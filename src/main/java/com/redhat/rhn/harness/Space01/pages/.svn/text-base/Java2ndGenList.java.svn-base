package com.redhat.rhn.harness.Space01.pages;

import java.text.ParseException;

import com.redhat.rhn.harness.common.RhnHelper;

public class Java2ndGenList extends WebList {

	
	protected Java2ndGenList() {
		
	}
	
	
	@Override
	public int clickCellLink(int row, int col) {
		String rowString = "";
		if (row > 1) {
			rowString = "[" + (row) + "]";
		}
		String colString = "";
		if (col > 1) {
			colString = "[" + (col) + "]";
		}
		
		rh.clickLink("xpath=//table[@class='list']/tbody/tr" + rowString + "/td" + colString + "/a", true);
		
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCurrentPage() {
		int min = Integer.parseInt(rh.getText("xpath=//strong[@id='list_min']"));
		return  min/getPageSize() + 1;
	}

	@Override
	public int getTotalCount() {
		String totalXpath = "xpath=//strong[@id='list_total']";
		if (!rh.isElementPresent(totalXpath, false)) {
			return 0;
		}
		return Integer.parseInt(rh.getText(totalXpath));
	}

	@Override
	public int getTotalPages() {
		int total = getTotalCount();
		int pages = 0;
		int mod = 0;
		pages =  total/getPageSize();
		mod = total % getPageSize();
		
		if(mod > 0){
			pages ++;
		}
		return pages;
	}
	

	@Override
	public void firstPage() {
		if (getCurrentPage() == 1) {
			return;
		}
		rh.clickButton("xpath=//input[@alt='First Page']", true);
	}
	
	@Override
	public void lastPage() {
		if (getCurrentPage() == getTotalPages()) {
			return;
		}
		rh.clickButton("xpath=//input[@alt='Last Page']", true);
	}

	@Override
	public void nextPage() {
		rh.clickButton("xpath=//input[@alt='Next Page']", true);
	}

	@Override
	public void prevPage() {
		rh.clickButton("xpath=//input[@alt='Previous Page']", true);
	}
	
}
