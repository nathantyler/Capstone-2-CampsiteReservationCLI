package com.techelevator.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SiteRowMapper implements RowMapper<Site> {

	@Override
	public Site mapRow(ResultSet rs, int rowNum) throws SQLException {
		Site site = new Site();
		site.setSiteId(rs.getInt("site_id"));
		site.setCampgroundId(rs.getInt("campground_id"));
		site.setSiteNumber(rs.getInt("site_number"));
		site.setMaxOccupancy(rs.getInt("max_occupancy"));
		site.setAccessible(rs.getBoolean("accessible"));
		site.setMaxRVLength(rs.getInt("max_rv_length"));
		site.setUtilities(rs.getBoolean("utilities"));
		return site;
	}

}
