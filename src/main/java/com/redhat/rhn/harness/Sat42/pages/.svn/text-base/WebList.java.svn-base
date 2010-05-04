package com.redhat.rhn.harness.Sat42.pages;
import java.util.NoSuchElementException;

import com.redhat.rhn.harness.common.RhnHelper;

public abstract class WebList {

	//Selenium sel = RhnHelper.sel;
	protected RhnHelper rh = new RhnHelper();
	
	
	
	public  abstract int getTotalCount();
	
	/**
	 * 1 is the first row!
	 * @param item
	 * @return
	 */
	public  abstract int clickCellLink(int row, int col);
	
	/**
	 * Starts counting at 1
	 * @return the current page
	 */
	public abstract int getCurrentPage();
	
	public abstract int getTotalPages();
	
	public abstract void nextPage();
	
	public abstract void prevPage();
	
	public abstract void firstPage();
	
	public abstract void lastPage();
	
	
	public int getPageSize() {
		return 5;
	}
	
	public static WebList getList() {
		
		return new Java2ndGenList();
	}
	
	
	public void clickLink(String name) {
		firstPage();
		
		while (true) {
			if (rh.isTextPresent(name)) {
				rh.clickLink("link=" + name, true);
				return;
			}
			if (getCurrentPage() != getTotalPages()) {
				throw new NoSuchElementException( name + " was not found");
			}
			nextPage();
		}
	}
	
	
	
}
