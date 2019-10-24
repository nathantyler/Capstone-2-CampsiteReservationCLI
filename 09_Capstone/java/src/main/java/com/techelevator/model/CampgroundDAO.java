package com.techelevator.model;

import java.util.List;

public interface CampgroundDAO {

	public List<Campground> getAllCampgrounds(long park_ID);
	public List<Campground> getCampgroundInfoByPark(long choice);
	
	}