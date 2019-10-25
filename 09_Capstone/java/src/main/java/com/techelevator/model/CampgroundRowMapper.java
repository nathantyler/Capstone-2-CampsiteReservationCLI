package com.techelevator.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CampgroundRowMapper implements RowMapper<Campground> {

	@Override
	public Campground mapRow(ResultSet rs, int rowNum) throws SQLException {
		Campground campG = new Campground();
		campG.setCampgroundId(rs.getInt("campground_id"));
		campG.setParkId(rs.getInt("park_id"));
		campG.setName(rs.getString("name"));
		campG.setOpenFrom(rs.getString("open_from_mm"));
		campG.setOpenFromInt(rs.getInt("open_from_mm"));
		campG.setOpenTo(rs.getString("open_to_mm"));
		campG.setOpenToInt(rs.getInt("open_to_mm"));
		campG.setDailyFee(rs.getObject("daily_fee").toString());
		return campG;
	}

}
