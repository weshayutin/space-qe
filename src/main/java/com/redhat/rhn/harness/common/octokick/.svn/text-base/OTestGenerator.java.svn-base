package com.redhat.rhn.harness.common.octokick;

import java.util.LinkedList;

public class OTestGenerator {
	/**
	 * Generates a LinkedList of OPaths from a LinkedList of distributions
	 * and a LinkedList of architectures
	 * @param distros LinkedList<ODistribution> containing distributions
	 * @param arches LinkedList<String> containing architecture strings
	 * @return LinkedList<OPath> containing all testing paths
	 */
	public LinkedList<OPath> generatePaths(LinkedList<ODistribution> distros,
										   LinkedList<String> arches){
		LinkedList<OPath> pathList = new LinkedList<OPath>();
		for(int i=0;i<arches.size();i++){
			LinkedList<ODistribution> tempList = new LinkedList<ODistribution>();
			for(int j=0;j<distros.size();j++){
				if(distros.get(j).getArchitecture().contains(arches.get(i)))
					tempList.add(distros.get(j));
			}
			this.addPaths(pathList,tempList);
		}
		return pathList;
	}

	/**
	 * Generates all path permutations for a given list of ODistributions and
	 * appends them to the list of OPaths given as an argument
	 * @param toAdd LinkedList<OPath> containing a list of OPaths
	 * @param distros LinkedList<ODistribution> containing a list of ODistributions
	 */
	private void addPaths(LinkedList<OPath> toAdd,
			              LinkedList<ODistribution> distros){
		LinkedList<ODistribution> latestReleases = this.getLatest(distros);
		for(int i=0;i<distros.size();i++){
			for(int j=0;j<latestReleases.size();j++){
				//if(j!=i){
					toAdd.add(this.buildPath(distros.get(i), latestReleases.get(j)));
				//}
			}
		}
	}

	private LinkedList<ODistribution> getLatest(LinkedList<ODistribution> distros){
		LinkedList<ODistribution> tempList = new LinkedList<ODistribution>();
		for(int i=0;i<distros.size();i++)
			if(distros.get(i).isLatest())
				tempList.add(distros.get(i));
		return tempList;
	}

	/**
	 * Builds a path using the following path connotation:
	 *
	 * <Distribution 1> -> <Distribution 2> -> <Distribution 1>
	 *
	 * out of supplied ODistribution arguments and returns it.
	 *
	 * @param bookend 'bookend' ODistribution
	 * @param center ODistribution in center of path
	 * @return OPath object representing fully-formed OPath
	 */
	private OPath buildPath(ODistribution bookend, ODistribution center){
		OPath tempPath = new OPath();
		tempPath.addDistribution(bookend);
		tempPath.addDistribution(center);
		//tempPath.addDistribution(bookend);
		return tempPath;
	}
}
