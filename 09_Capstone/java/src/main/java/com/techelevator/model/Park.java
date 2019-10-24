package com.techelevator.model;

import java.time.LocalDate;
import java.util.List;

public class Park {

	private int parkId;
	private String name;
	private String location;
	private LocalDate establishDate;
	private int area;
	private int visitors;
	private String description;
	
	
	
	 /* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	  * The above satisfies requirement 1a= A park includes an id, name, location,
	  *  established date, area, annual visitor count, and description. 
	  *  
	  *  Below are the getters and setters  
	  */

		
	/**
	 * @return the parkId
	 */
	public int getParkId() {
		return parkId;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}



	/**
	 * @return the establishDate
	 */
	public LocalDate getEstablishDate() {
		return establishDate;
	}



	/**
	 * @return the area
	 */
	public int getArea() {
		return area;
	}



	/**
	 * @return the visitors
	 */
	public int getVisitors() {
		return visitors;
	}



	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * @param parkId the parkId to set
	 */
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}



	/**
	 * @param establishDate the establishDate to set
	 */
	public void setEstablishDate(LocalDate establishDate) {
		this.establishDate = establishDate;
	}



	/**
	 * @param area the area to set
	 */
	public void setArea(int area) {
		this.area = area;
	}



	/**
	 * @param visitors the visitors to set
	 */
	public void setVisitors(int visitors) {
		this.visitors = visitors;
	}



	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	
	//To print out message of park names
	public String toString() {
		return name +" National Park" ;
	}
	
	

}