package com.techelevator.model;

import java.math.BigDecimal;
import java.util.List;


public class Campground implements CampgroundDAO {

	private long campground_ID;
	private long park_ID;
	private String campGroundName;
	private String openMonth;
	private String closedMonth;
	private BigDecimal dailyFee;

 /* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
  * The above satisfies requirement 2a= A campground includes an id, name,
  *  open month, closing month, and a daily fee. 
  *  
  *  Below are the getters and setters 
  *  
  */
	
	
	public long getCampground_ID() {
		return campground_ID;
	}
	public void setCampgroundId(long campground_ID) {
		this.campground_ID = campground_ID;
	}
	public long getParkId() {
		return park_ID;
	}
	public void setParkId(long park_ID) {
		this.park_ID = park_ID;
	}
	public String getCampGroundName() {
		return campGroundName;
	}
	public void setName(String campGroundName) {
		this.campGroundName = campGroundName;
	}
	public String getOpenMonth() {
		return openMonth;
	}
	public void setOpen(String openMonth) {
		this.openMonth = openMonth;
	}
	public String getClosedMonth() {
		return closedMonth;
	}
	public void setClosedMonth(String closedMonth) {
		this.closedMonth = closedMonth;
	}
	public BigDecimal getDailyFee() {
		
		return dailyFee;
	}
	public void setFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee.setScale(2);
	}
	@Override
	
	//In case we need to print out messages using strings and bigdecimal
	public String toString() {
		return campGroundName +"   "+ openMonth + "    "+ closedMonth+"    "+dailyFee;
	}
	
	// 2 methods need to be written
	@Override
	public List<Campground> getAllCampgrounds(long park_ID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Campground> getCampgroundInfoByPark(long choice) {
		// TODO Auto-generated method stub
		return null;
	}

	}
