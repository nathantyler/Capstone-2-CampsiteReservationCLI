package com.techelevator.model;

import java.util.List;

public interface SiteDAO {
	// We may not need this.
	public List<Site> getAllCampsitesByCampgroundId(int id);
	
	// Return a specific site by site ID
	public Site getSiteBySiteId(int id);
	
}