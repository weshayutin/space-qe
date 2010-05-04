package com.redhat.rhn.harness.common.octokick;

import java.util.LinkedList;

public class OPath {
	private LinkedList<OTestElement> distroList;
	public int currentPos;
	private int pathStatus;
	private OSystem assignedSys;

	public OPath(){
		distroList = new LinkedList<OTestElement>();
		currentPos = 0;
		pathStatus = 0;
		assignedSys = null;
	}

	public void addDistribution(ODistribution o){
		distroList.add(new OTestElement(o));
	}

	public OTestElement getCurrent(){
		return this.distroList.get(currentPos);
	}

	public OTestElement get(int index){
		return distroList.get(index);
	}

	public int size(){
		return distroList.size();
	}

	public boolean hasNext(){
		return !(this.currentPos+1==this.distroList.size());
	}

	public OTestElement getLast(){
		return distroList.getLast();
	}

	/**
	 * Gets next available test element; if no more exist, returns null
	 * @return next available OTestElement if one exists
	 */
	public OTestElement getNext(){
		if(currentPos > distroList.size() - 1){
			return null;
		}
		return distroList.get(currentPos++);
	}

	/**
	 * Returns path's testing status as an integer value:
	 *
	 * 0 = Untested
	 * 1 = Testing in progress
	 * 2 = Test successful
	 *
	 * @return path's testing status as an int
	 */
	public int getStatus(){
		return this.pathStatus;
	}

	/**
	 * Sets path's testing status to supplied value
	 * @param newStatus path's testing status as an int
	 */
	public void setStatus(int newStatus){
		this.pathStatus = newStatus;
	}

	public void setSystem(OSystem system){
		assignedSys = system;
	}
	
	public OSystem getSystem(){
		return assignedSys;
	}
	
}
