package com.redhat.rhn.harness.common.Space01.tasks;

import java.util.logging.Logger;

import com.redhat.rhn.harness.common.RhnHelper;
import com.redhat.rhn.harness.common.SeleniumSetup;

import junit.framework.TestCase;

/**
 * This is a page visitor class.  It is used in association with Search.visitEachItem 
 *    to 
 * @author jlsherri
 *
 */
public abstract class PageVisitor extends TestCase{

	private int column;
	private boolean atLeastOneTrue = false;
	protected static Logger log = Logger.getLogger(SeleniumSetup.class.getName());
	
	protected RhnHelper rh = new RhnHelper();


	public PageVisitor(int columnIn) {
		this.column = columnIn;
	}
	
	public int getColumn() {
		return column;
	}
	
	public boolean isTrueAtLeastOnce() {
		return atLeastOneTrue;
	}

	public void setAtLeastOneTrue(boolean atLeastOneTrue) {
		this.atLeastOneTrue = atLeastOneTrue;
	}
	
	public abstract boolean execute();
	
}
