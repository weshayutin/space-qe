package com.redhat.rhn.harness.common.Space01.tasks;

import com.redhat.rhn.harness.Space01.pages.ErrataDetailsPage;


/**
 * This vistior starts on the errata details page, and verify's that the errata contains packages
 * with a certain name (or at least part of a certain name) 
 * 
 * @author jlsherri
 *
 */
public class ErrataCVEVisitor extends PageVisitor {

	private String cve;
	
	public ErrataCVEVisitor(int columnIn, String cveIn) {
		super(columnIn);
		cve = cveIn;
	}
	
	

	@Override
	public boolean execute() {
		ErrataDetailsPage edp = new ErrataDetailsPage();
		assertTrue(edp.hasCVE(cve));
		return false;
	}






	



	
}
