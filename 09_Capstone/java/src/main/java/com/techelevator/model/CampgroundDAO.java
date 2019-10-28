package com.techelevator.model;

import java.util.List;

public interface CampgroundDAO {
	/* The user needs access to all campgrounds of a specific park */
	public List<Campground> getAllCampgroundsByParkId(int id);

	/* Legacy */
	public List<Campground> getAllOpenCampgrounds(int id, int start, int end);

	/* Return a specific Campground by campground ID */
	public Campground getCampgroundByCGId(int id);

}