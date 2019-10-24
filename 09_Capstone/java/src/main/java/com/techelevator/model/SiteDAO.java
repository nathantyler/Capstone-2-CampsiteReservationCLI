package com.techelevator.model;

import java.time.LocalDate;
import java.util.List;

public interface SiteDAO {
	
	public List<Site> getAllSites(long campground_ID,LocalDate fromDate,LocalDate toDate);
}