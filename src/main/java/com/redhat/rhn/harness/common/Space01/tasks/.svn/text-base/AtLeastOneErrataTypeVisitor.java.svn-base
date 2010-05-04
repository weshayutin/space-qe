package com.redhat.rhn.harness.common.Space01.tasks;

import com.redhat.rhn.harness.Space01.pages.ErrataDetailsPage;


/**
 * This visitor verifies that a list of errata has at least one of each type. 
 * basically, it stores an errata type and then returns true or false for each errata 
 * if it is that type.  You can use multiple visitors to test multiple types
 * 
 * @author jlsherri
 *
 */
public class AtLeastOneErrataTypeVisitor extends PageVisitor {

	public static final int SECURITY = 0;
	public static final int BUG_FIX = 1;
	public static final int ENHANCE = 2;
	
	private int type;
	
	public AtLeastOneErrataTypeVisitor(int columnIn, int errataType) {
		super(columnIn);
		type = errataType;
	}
	
	

	@Override
	public boolean execute() {
		ErrataDetailsPage edp = new ErrataDetailsPage();
		switch (type) {
			case SECURITY:
				return edp.isSecurityErrata();
			case BUG_FIX:
				return edp.isBugFixErrata();
			case ENHANCE:
				return edp.isEnhancementErrata();
	
			default:
				return false;
		}
	}

	





	
}
