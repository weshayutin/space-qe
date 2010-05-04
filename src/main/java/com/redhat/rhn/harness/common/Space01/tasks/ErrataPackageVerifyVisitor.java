package com.redhat.rhn.harness.common.Space01.tasks;

import com.redhat.rhn.harness.Space01.pages.ErrataDetailsPage;


/**
 * This vistior starts on the errata details page, and verify's that the errata contains packages
 * with a certain name (or at least part of a certain name) 
 * 
 * @author jlsherri
 *
 */
public class ErrataPackageVerifyVisitor extends PageVisitor {

	private String pack;
	
	public ErrataPackageVerifyVisitor(int columnIn, String packageIn) {
		super(columnIn);
		pack = packageIn;
	}
	
	

	@Override
	public boolean execute() {
		ErrataDetailsPage edp = new ErrataDetailsPage();
		//edp.clickPackageLink();
		assertTrue(edp.hasPackage(pack));
		//rh.goBack();
		return false;
	}



	public String getPack() {
		return pack;
	}


	



	
}
