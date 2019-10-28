package com.techelevator.model;

import java.time.LocalDate;
import java.util.List;

public interface SiteDAO {
	/*
	 * We may not need this.
	 * 
	 * And we did not...
	 * 
	 * Legacy
	 */
	public List<Site> getAllCampsitesByCampgroundId(int id);

	/*
	 * Return a specific site by site ID.
	 * 
	 * Legacy
	 * 
	 */
	public Site getSiteBySiteId(int id);

	/*
	 * I was finally able to make this method work by way of a very long and
	 * complicated query.
	 */
	public List<Site> getTopFiveAvailableSitesByCampId(int campId, LocalDate from, LocalDate to);

}