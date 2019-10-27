package com.techelevator.model.jdbc;

import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Reservation;
import com.techelevator.model.Site;
import com.techelevator.model.SiteDAO;
import com.techelevator.model.SiteRowMapper;

public class JDBCSiteDAO implements SiteDAO {

	static final String TABLE_NAME = "site", SITE_ID = "site_id", CAMPGROUND_ID = "campground_id",
			SITE_NUMBER = "site_number", MAX_OCCUPANCY = "max_occupancy", ACCESSIBLE = "accessible",
			MAX_RV_LENGTH = "max_rv_length", UTILITIES = "utilities";
	
	static final String[] COLUMN_NAMES = new String[] { SITE_ID, CAMPGROUND_ID, SITE_NUMBER, MAX_OCCUPANCY, ACCESSIBLE, MAX_RV_LENGTH, UTILITIES };

	private JdbcTemplate jdbcT;

	/**
	 * 
	 */
	public JDBCSiteDAO(DataSource ds) {
		jdbcT = new JdbcTemplate(ds);
	}

	@Override
	public List<Site> getAllCampsitesByCampgroundId(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + CAMPGROUND_ID + " = ?";
		return jdbcT.query(sql, new SiteRowMapper(), id);
	}

	@Override
	public Site getSiteBySiteId(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + SITE_ID + " = ?";
		return jdbcT.queryForObject(sql, new SiteRowMapper(), id);
	}
	
	public List<Site> getTopFiveAvailableSitesByCampId(int campId , LocalDate from, LocalDate to) {
		final String RES_DOT_FROM_DATE = JDBCReservationDAO.TABLE_NAME + "." + JDBCReservationDAO.FROM_DATE,
					 RES_DOT_TO_DATE = JDBCReservationDAO.TABLE_NAME + "." + JDBCReservationDAO.TO_DATE,
					 RES_DOT_SITE_ID = JDBCReservationDAO.TABLE_NAME + "." + SITE_ID,
					 RES_DOT_RES_ID = JDBCReservationDAO.TABLE_NAME + "." + JDBCReservationDAO.RESERVATION_ID,
					 SITE_DOT_SITE_ID = TABLE_NAME + "." + SITE_ID,
					 CG_DOT_CG_ID = JDBCCampgroundDAO.TABLE_NAME + "." + CAMPGROUND_ID,
					 SITE_DOT_CG_ID = TABLE_NAME + "." + CAMPGROUND_ID;
		String siteColumns = "";
		for (int i = 0; i < COLUMN_NAMES.length; i++)
			siteColumns = i != COLUMN_NAMES.length - 1 ? 
					siteColumns + TABLE_NAME + "." + COLUMN_NAMES[i] + ", " : 
						siteColumns + TABLE_NAME + "." + COLUMN_NAMES[i];
		// Time to craft this monster of a query:
		String monsterSql = "SELECT " + siteColumns + " FROM " + 
							TABLE_NAME + " LEFT JOIN " +
							JDBCReservationDAO.TABLE_NAME + " ON " +
							RES_DOT_SITE_ID + " = " + SITE_DOT_SITE_ID +
							" WHERE " +	SITE_DOT_CG_ID + " = ? AND " + 
							SITE_DOT_SITE_ID + " NOT IN (SELECT " + SITE_ID +
							" FROM " + JDBCReservationDAO.TABLE_NAME +
							" WHERE " +	RES_DOT_FROM_DATE + " BETWEEN ? AND ?" + 
							" OR " + RES_DOT_TO_DATE + " BETWEEN ? AND ?" +
							" OR ? BETWEEN " + RES_DOT_FROM_DATE + 
							" AND " + RES_DOT_TO_DATE +
							" OR ? BETWEEN " + RES_DOT_FROM_DATE + 
							" AND " + RES_DOT_TO_DATE + ")" +
							" GROUP BY " + SITE_DOT_SITE_ID +
							" ORDER BY COUNT(" + RES_DOT_RES_ID + ") DESC, " +
							SITE_DOT_SITE_ID + " ASC" +
							" LIMIT 5";							
		
		return jdbcT.query(monsterSql, new SiteRowMapper(), campId, from, to, from, to, from, to);
	}

}
