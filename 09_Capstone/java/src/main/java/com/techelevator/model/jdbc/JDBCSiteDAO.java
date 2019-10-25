package com.techelevator.model.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Site;
import com.techelevator.model.SiteDAO;
import com.techelevator.model.SiteRowMapper;

public class JDBCSiteDAO implements SiteDAO {
	
	
	static final String TABLE_NAME     = "site",
						SITE_ID        = "site_id",
						CAMPGROUND_ID  = "campground_id",
						SITE_NUMBER    = "site_number",
						MAX_OCCUPANCY  = "max_occupancy",
						ACCESSIBLE     = "accessible",
						MAX_RV_LENGTH  = "max_rv_length",
						UTILITIES      = "utilities";		
		
	private JdbcTemplate jdbcT;

	/**
	 * 
	 */
	public JDBCSiteDAO(DataSource ds) {
		jdbcT = new JdbcTemplate(ds);
	}

	@Override
	public List<Site> getAllCampsitesByCampgroundId(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + 
					 " WHERE " + CAMPGROUND_ID + " = ?"; 
		return jdbcT.query(sql, new SiteRowMapper(), id);
	}

	@Override
	public Site getSiteBySiteId(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + 
				 	 " WHERE " + SITE_ID + " = ?"; 
		return jdbcT.queryForObject(sql, new SiteRowMapper(), id);
	}

}
