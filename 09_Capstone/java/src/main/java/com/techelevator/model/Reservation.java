package com.techelevator.model;

import java.time.LocalDate;
import java.util.List;

public class Reservation implements ReservationDAO {

	 private long reservation_ID;
	 private long site_ID;
	 private String name;
	 private LocalDate fromDate;
	 private LocalDate toDate;
	 private LocalDate createDate;
	 
	 
	public long getReservation_ID() {
		return reservation_ID;
	}
	public void setReservation_ID(long reservation_ID) {
		this.reservation_ID = reservation_ID;
	}
	public long getSite_ID() {
		return site_ID;
	}
	public void setSiteId(long site_ID) {
		this.site_ID = site_ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	
	
	
	//3 methods need to be written
	@Override
	public List<Reservation> getAllReservations(long campId, LocalDate fromDate, LocalDate toDate) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setReservation(long site_ID, LocalDate fromDate, LocalDate toDate, String name) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Reservation> getConfirmId(String name, LocalDate fromDate) {
		// TODO Auto-generated method stub
		return null;
	}
	 
	}