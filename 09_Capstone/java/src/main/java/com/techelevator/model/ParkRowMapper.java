package com.techelevator.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ParkRowMapper implements RowMapper<Park> {

	@Override
	public Park mapRow(ResultSet rs, int rowNum) throws SQLException {
		Park park = new Park();
		park.setParkId(rs.getInt("park_id"));
		park.setName(rs.getString("name"));
		park.setLocation(rs.getString("location"));
		park.setEstablishDate(rs.getDate("establish_date").toLocalDate());
		park.setArea(rs.getInt("area"));
		park.setVisitors(rs.getInt("visitors"));
		park.setDescription(rs.getString("description"));
		return park;
	}

}
