package com.redhat.rhn.harness.common.Space01.tasks;

import com.redhat.rhn.harness.Space01.pages.ErrataDetailsPage;

public class ErrataTypeVerifyVisitor extends PageVisitor {

	public boolean security;
	public boolean bugFix;
	public boolean enhance;
	
	
	
	public ErrataTypeVerifyVisitor(int columnIn, boolean securityIn, boolean bugfixIn, boolean enhanceIn) {
		super(columnIn);
		security = securityIn;
		bugFix = bugfixIn;
		enhance  = enhanceIn;		
	}
	
	

	@Override
	public boolean execute() {
		ErrataDetailsPage edp = new ErrataDetailsPage();
		
		if (!security) {
			assertTrue(!edp.isSecurityErrata());
		}
		if (!bugFix) {
			assertTrue(!edp.isBugFixErrata());
		}
		if (!enhance) {
			assertTrue(!edp.isEnhancementErrata());
		}
		
		assertTrue(edp.isSecurityErrata() || edp.isBugFixErrata() || edp.isEnhancementErrata());
		
		return false;
		
	}

	





	
}
