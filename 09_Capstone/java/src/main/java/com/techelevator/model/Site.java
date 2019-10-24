package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Site {

	private int siteId;
	private int campgroundId;
	private int siteNumber;
	private int maxOccupancy;
	private boolean accessible;
	private int maxRVLength;
	private boolean utilities;
	/**
	 * @return the siteId
	 */
	public int getSiteId() {
		return siteId;
	}
	/**
	 * @return the campgroundId
	 */
	public int getCampgroundId() {
		return campgroundId;
	}
	/**
	 * @return the siteNumber
	 */
	public int getSiteNumber() {
		return siteNumber;
	}
	/**
	 * @return the maxOccupancy
	 */
	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	/**
	 * @return the accessible
	 */
	public boolean isAccessible() {
		return accessible;
	}
	/**
	 * @return the maxRVLength
	 */
	public int getMaxRVLength() {
		return maxRVLength;
	}
	/**
	 * @return the utilities
	 */
	public boolean isUtilities() {
		return utilities;
	}
	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	/**
	 * @param campgroundId the campgroundId to set
	 */
	public void setCampgroundId(int campgroundId) {
		this.campgroundId = campgroundId;
	}
	/**
	 * @param siteNumber the siteNumber to set
	 */
	public void setSiteNumber(int siteNumber) {
		this.siteNumber = siteNumber;
	}
	/**
	 * @param maxOccupancy the maxOccupancy to set
	 */
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	/**
	 * @param accessible the accessible to set
	 */
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	/**
	 * @param maxRVLength the maxRVLength to set
	 */
	public void setMaxRVLength(int maxRVLength) {
		this.maxRVLength = maxRVLength;
	}
	/**
	 * @param utilities the utilities to set
	 */
	public void setUtilities(boolean utilities) {
		this.utilities = utilities;
	}

}