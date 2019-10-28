package com.techelevator.model;

import java.time.LocalDate;

public interface ReservationDAO {

	/*
	 * Return a specific reservation by reservation ID. 
	 * 
	 * Legacy
	 */
	public Reservation getReservation(int id);

	/*
	 * Insert a new reservation into the reservation table and return the new
	 * reservation ID.
	 */
	public int makeReservation(int siteId, LocalDate from, LocalDate to, String resName);

	/*
	 * Check if a site is available during the desired time. Return true if
	 * available false if not. But it's looking like this method won't be getting
	 * used after all now that I know just exactly what "TOP 5" is supposed to mean
	 * (was I supposed to guess from context somehow?).
	 * 
	 * Legacy
	 */
	public boolean checkAvailabilityOfSite(int id, LocalDate from, LocalDate to);

}