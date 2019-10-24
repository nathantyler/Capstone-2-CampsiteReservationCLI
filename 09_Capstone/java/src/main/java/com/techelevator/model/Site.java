package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Site implements SiteDAO {

	private long site_ID;
	private long campground_ID;
	private long siteNumber;
	private long maxOccupancy;
	private boolean available;
	private boolean handiAccessable;
	private long maxRVLength;
	private boolean utilities;
	private BigDecimal dailyFee;

	public long getSiteId() {
		return site_ID;
	}

	public void setSiteId(long site_ID) {
		this.site_ID = site_ID;
	}

	public long getCampground_ID() {
		return campground_ID;
	}

	public void setCampground_ID(long campground_ID) {
		this.campground_ID = campground_ID;
	}

	public long getSiteNum() {
		return siteNumber;
	}

	public void setSiteNum(long siteNumber) {
		this.siteNumber = siteNumber;
	}

	public long getMaxOccupancy() {
		return maxOccupancy;
	}

	public void setMaxOccupy(long maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	
	//Below is Bonus 3 F
	}
	public boolean isHandiAccessable() {
			return handiAccessable;
	}
	public void setHandiAccessable(boolean handiAccessable) {
		this.handiAccessable = handiAccessable;
	// Above is Bonus 3 F
	}

	public long getMaxRVLength() {
		return maxRVLength;
	}

	public void setMaxRVLength(long maxRVLength) {
		this.maxRVLength = maxRVLength;
	}

	public boolean isUtilities() {
		return utilities;
	}

	public void setUtilities(boolean utilities) {
		this.utilities = utilities;
	}

	public BigDecimal getDailyFee() {
		return dailyFee;
	}

	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee.setScale(2);
	}

	@Override
	public List<Site> getAllSites(long campground_ID, LocalDate fromDate, LocalDate toDate) {
		// TODO Auto-generated method stub
		return null;
	}

}