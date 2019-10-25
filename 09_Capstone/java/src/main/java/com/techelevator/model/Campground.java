package com.techelevator.model;

import java.math.BigDecimal;

public class Campground {

	// I had to change some of these variable names
	// as I know they were gonna leave to confusion down the line
	// I try to keep them as close to as possible to the database column names
	// The underscores were just an "off-convention" thing.
	private int campgroundId;
	private int parkId;
	private String name;
	private String openFrom;
	private String openTo;
	private BigDecimal dailyFee;
	private int openFromInt;
	private int openToInt;

	/*
	 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ The above satisfies requirement 2a=
	 * A campground includes an id, name, open month, closing month, and a daily
	 * fee.
	 * 
	 * Below are the getters and setters
	 * 
	 */

	// In case we need to print out messages using strings and bigdecimal
	/**
	 * @return the campgroundId
	 */
	public int getCampgroundId() {
		return campgroundId;
	}

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
	 * @return the openFrom
	 */
	public String getOpenFrom() {
		return openFrom;
	}

	/**
	 * @return the openTo
	 */
	public String getOpenTo() {
		return openTo;
	}

	/**
	 * @return the dailyFee
	 */
	public BigDecimal getDailyFee() {
		return dailyFee;
	}

	/**
	 * @param campgroundId the campgroundId to set
	 */
	public void setCampgroundId(int campgroundId) {
		this.campgroundId = campgroundId;
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
	 * @param openFrom the openFrom to set
	 */
	public void setOpenFrom(String openFrom) {
		this.openFrom = openFrom;
	}

	/**
	 * @param openTo the openTo to set
	 */
	public void setOpenTo(String openTo) {
		this.openTo = openTo;
	}

	/**
	 * @param dailyFee the dailyFee to set
	 */
	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee.setScale(2);
	}

	// The column daliy_fee in the database uses the Postgres datatype "money".
	// Java doesn't have a direct analog. As such, passing the dailyFee as String is
	// probably going to be helpful.
	public void setDailyFee(String dailyFee) {
		this.dailyFee = new BigDecimal(dailyFee);
	}

	/**
	 * @return the openFromInt
	 */
	public int getOpenFromInt() {
		return openFromInt;
	}

	/**
	 * @return the openToInt
	 */
	public int getOpenToInt() {
		return openToInt;
	}

	/**
	 * @param openFromInt the openFromInt to set
	 */
	public void setOpenFromInt(int openFromInt) {
		this.openFromInt = openFromInt;
	}

	/**
	 * @param openToInt the openToInt to set
	 */
	public void setOpenToInt(int openToInt) {
		this.openToInt = openToInt;
	}

	/**
	 * @param Set openFromInt from a String
	 */
//	public void setOpenFromInt(String str) {
//		try {
//			this.openFromInt = Integer.parseInt(str);
//		} catch (NumberFormatException e) {
//			this.openFromInt = 0;
//		}
//	}

	/**
	 * @param set openToInt from a String
	 */
//	public void setOpenToInt(String str) {
//		try {
//			this.openFromInt = Integer.parseInt(str);
//		} catch (NumberFormatException e) {
//			this.openFromInt = 0;
//		}
//	}

	/**
	 * @param Set openFromInt openFrom
	 */
//	public void setOpenFromInt() {
//		try {
//			this.openFromInt = Integer.parseInt(openFrom);
//		} catch (NumberFormatException e) {
//			this.openFromInt = 0;
//		}
//	}

	/**
	 * @param set openToInt from a openTo
	 */
//	public void setOpenToInt() {
//		try {
//			this.openFromInt = Integer.parseInt(openTo);
//		} catch (NumberFormatException e) {
//			this.openFromInt = 0;
//		}
//	}

	@Override
	public String toString() {
		return name + "   " + openFrom + "    " + openTo + "    " + dailyFee;
	}

}
