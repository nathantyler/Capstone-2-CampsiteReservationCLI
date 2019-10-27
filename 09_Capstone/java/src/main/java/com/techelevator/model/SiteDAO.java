package com.techelevator.model;

import java.time.LocalDate;
import java.util.List;

public interface SiteDAO {
	// We may not need this.
	public List<Site> getAllCampsitesByCampgroundId(int id);

	// Return a specific site by site ID
	public Site getSiteBySiteId(int id);
	
	public List<Site> getTopFiveAvailableSitesByCampId(int campId, LocalDate from, LocalDate to);

}