package com.techelevator.model;

import java.time.LocalDate;
import java.util.List;

public class Park implements ParkDAO{

	private String parkName;
	private String parkLocation;
	private LocalDate date;
	private long area;
	private long visitors;
	private String parkDescription;
	private long park_ID;
	
	
	 /* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	  * The above satisfies requirement 1a= A park includes an id, name, location,
	  *  established date, area, annual visitor count, and description. 
	  *  
	  *  Below are the getters and setters  
	  */

	public String getName() {
		return parkName;
	}
	public void setName(String parkName) {
		this.parkName = parkName;
	}
	public String getLocation() {
		return parkLocation;
	}
	public void setLocation(String parkLocation) {
		this.parkLocation = parkLocation;
	}
	public LocalDate getEstDate() {
		return date;
	}
	public void setEstDate(LocalDate date) {
		this.date = date;
	}
	public long getArea() {
		return area;
	}
	public void setArea(long area) {
		this.area = area;
	}
	public long getVisitors() {
		return visitors;
	}
	public void setVisitors(long visitors) {
		this.visitors = visitors;
	}
	public String getDescription() {
		return parkDescription;
	}
	public void setDescription(String parkDescription) {
		this.parkDescription = parkDescription;
	}
	public long getId() {
		return park_ID;
	}
	public void setId(long park_ID) {
		this.park_ID = park_ID;
	}
	
	@Override
	
	//To print out message of park names
	public String toString() {
		return parkName +" National Park" ;
	}
	
	
	//3 methods need to written 
	
	@Override
	public List<Park> getAllParks() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Park> getParkInfo(long choice) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Park> getAllCampgroundsByPark_ID() {
		// TODO Auto-generated method stub
		return null;
	}
}