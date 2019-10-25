package com.techelevator.model;

import java.util.List;

public interface ParkDAO {

	// The user requires access to all parks sorted alphabetically
	public List<Park> getAllParksAlphabetically();

	// The user requires access to a park by its specific ID.
	public Park getParkById(int id);
}