package com.techelevator.model;

import java.time.LocalDate;

public class Reservation {

	private int reservationId;
	private int siteId;
	private String name;
	private LocalDate fromDate;
	private LocalDate toDate;
	private LocalDate createDate;
	
	/**
	 * @param siteId
	 * @param name
	 * @param fromDate
	 * @param toDate
	 * @param createDate
	 */
	public Reservation(int siteId, String name, LocalDate fromDate, LocalDate toDate, LocalDate createDate) {
		this.siteId = siteId;
		this.name = name;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.createDate = createDate;
	}
	/**
	 * @return the reservationId
	 */
	public int getReservationId() {
		return reservationId;
	}
	/**
	 * @return the siteId
	 */
	public int getSiteId() {
		return siteId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the fromDate
	 */
	public LocalDate getFromDate() {
		return fromDate;
	}
	/**
	 * @return the toDate
	 */
	public LocalDate getToDate() {
		return toDate;
	}
	/**
	 * @return the createDate
	 */
	public LocalDate getCreateDate() {
		return createDate;
	}
	/**
	 * @param reservationId the reservationId to set
	 */
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public String toString() {
		return reservationId + "   " + siteId + "   " + name + "   " + fromDate + "   " + toDate + "   " + createDate;
	}
	

}