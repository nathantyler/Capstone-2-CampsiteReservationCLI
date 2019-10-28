/**
 * 
 */
package com.techelevator.model.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.*;



public class JDBCParkDAO implements ParkDAO {	
	
	static final String TABLE_NAME     = "park",
						PARK_ID        = "park_id",
						NAME           = "name",
						LOCATION       = "location",
						ESTABLISH_DATE = "establish_date",
						AREA           = "area",
						VISITORS       = "visitors",
						DESCRIPTION    = "description";		
		
	private JdbcTemplate jdbcT;

	/**
	 * 
	 */
	public JDBCParkDAO(DataSource ds) {
		jdbcT = new JdbcTemplate(ds);
	}

	@Override
	public List<Park> getAllParksAlphabetically() {
		String sql = "SELECT * FROM " + TABLE_NAME + 
					 " ORDER BY " + NAME + " ASC";
		return jdbcT.query(sql, new ParkRowMapper());
	}

	@Override
	public Park getParkById(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME +
					 " WHERE " + PARK_ID + " = ?";
		return jdbcT.queryForObject(sql, new ParkRowMapper(), id);
	}
	
	

}
