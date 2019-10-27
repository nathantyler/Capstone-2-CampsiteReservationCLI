package com.techelevator.model;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDAO {
	// Access all reservations by campground ID.
	public List<Reservation> getAllReservationsByCGId(int id);

	// Return a specific reservation by reservation ID.
	public Reservation getReservation(int id);

	// Insert a new reservation into the reservation table and return the new
	// reservation ID.
	public int makeReservation(int siteId, LocalDate from, LocalDate to, String resName);

	// Check if a site is available during the desired time. Return true if
	// available
	// false if not.
	public boolean checkAvailabilityOfSite(int id, LocalDate from, LocalDate to);

}