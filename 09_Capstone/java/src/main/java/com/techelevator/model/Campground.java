package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class Campground {

	private int campgroundId;
	private int parkId;
	private String name;
	private String openFrom;
	private String openTo;
	private BigDecimal dailyFee;
	private int openFromInt;
	private int openToInt;

	
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


	public boolean CampgroundIsClosed(LocalDate from, LocalDate to) {
		if (openFromInt == 1 && openToInt == 12)
			return false;
		LocalDate campOpen = LocalDate.of(from.getYear(), openFromInt, 1);
		LocalDate campClosed = LocalDate.of(from.getYear(), openToInt, 1);
		return from.isBefore(campOpen) || to.isAfter(campClosed);
	}

	public String getOpenMonthName() {
		return Month.of(openFromInt).getDisplayName(TextStyle.FULL, Locale.US);
	}
	
	public String getClosedMonthName() {
		return Month.of(openToInt).getDisplayName(TextStyle.FULL, Locale.US);
	}

}
