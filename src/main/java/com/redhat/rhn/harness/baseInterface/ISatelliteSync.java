package com.redhat.rhn.harness.baseInterface;

import java.util.List;

public interface ISatelliteSync {
	
	/**
	 * Performs a satellite sync 
	 * 
	 * @param exportLocation directory containing the export
	 * @param channels list of channels to sync
	 */
	public void sync(String exportLocation, List<String> channels);
	
	/**
	 * Copy an export to the satellite under test
	 * @param sourceFile file in .tar.bz2 format containing a satellite export 
	 * @param destPath destination path
	 * 
	 */
	public void copyExportToSat(String sourceFile, String destPath);
}
