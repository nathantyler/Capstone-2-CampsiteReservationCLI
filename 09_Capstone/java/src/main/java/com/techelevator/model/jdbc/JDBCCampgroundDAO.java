package com.techelevator.model.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Campground;
import com.techelevator.model.CampgroundDAO;
import com.techelevator.model.CampgroundRowMapper;

public class JDBCCampgroundDAO implements CampgroundDAO {
	
	
	static final String TABLE_NAME     = "campground",
						CAMPGROUND_ID  = "campground_id",
						PARK_ID        = "park_id",
						NAME           = "name",
						OPEN_FROM_MM   = "open_from_mm",
						OPEN_TO_MM     = "open_to_mm",
						DAILY_FEE      = "daily_fee";		
		
	private JdbcTemplate jdbcT;

	/**
	 * 
	 */
	public JDBCCampgroundDAO(DataSource ds) {
		jdbcT = new JdbcTemplate(ds);
	}

	@Override
	public List<Campground> getAllCampgroundsByParkId(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME +
					 " WHERE " + PARK_ID + " = ?";
		return jdbcT.query(sql, new CampgroundRowMapper(), id);
	}

	@Override
	public List<Campground> getAllOpenCampgrounds(int parkId, int start, int end) {
		String sql = "SELECT * FROM " + TABLE_NAME +
				 	 " WHERE CAST (" + OPEN_FROM_MM + " AS INTEGER) <= ? AND " +
				 	 "CAST (" + OPEN_TO_MM + " AS INTEGER) >= ? AND " + 
				 	 PARK_ID + " = ?";
		return jdbcT.query(sql, new CampgroundRowMapper(), start, end, parkId);
	}
	

	@Override
	public Campground getCampgroundByCGId(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME +
				 	 " WHERE " + CAMPGROUND_ID + " = ?";
		return jdbcT.queryForObject(sql, new CampgroundRowMapper(), id);
	}

}
