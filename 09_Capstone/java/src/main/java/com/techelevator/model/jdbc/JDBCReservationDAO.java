package com.techelevator.model.jdbc;

import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Reservation;
import com.techelevator.model.ReservationDAO;
import com.techelevator.model.ReservationRowMapper;

public class JDBCReservationDAO implements ReservationDAO {

	static final String TABLE_NAME = "reservation", RESERVATION_ID = "reservation_id", SITE_ID = "site_id",
			NAME = "name", FROM_DATE = "from_date", TO_DATE = "to_date", CREATE_DATE = "create_date";
	private static final String[] COLUMN_NAMES = new String[] { RESERVATION_ID, SITE_ID, NAME, FROM_DATE, TO_DATE,
			CREATE_DATE };
	private JdbcTemplate jdbcT;

	/**
	 * 
	 */
	public JDBCReservationDAO(DataSource ds) {
		jdbcT = new JdbcTemplate(ds);
	}

	@Override
	public List<Reservation> getAllReservationsByCGId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation getReservation(int id) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + RESERVATION_ID + " = ?";
		return jdbcT.queryForObject(sql, new ReservationRowMapper(), id);
	}

	@Override
	public int makeReservation(int siteId, LocalDate from, LocalDate to, String resName) {
		String columns = "";
		for (int i = 1; i < COLUMN_NAMES.length; i++)
			columns = i == 1 ? COLUMN_NAMES[i] : columns + ", " + COLUMN_NAMES[i];
		columns = "(" + columns + ")";
		String sql = "INSERT INTO " + TABLE_NAME + columns + " VALUES (?, ?, ?, ?, ?)";
		LocalDate now = LocalDate.now();
		jdbcT.update(sql, siteId, resName, from, to, now);
		sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + CREATE_DATE + " = ? AND " + FROM_DATE + " = ? AND " + TO_DATE
				+ " = ? AND " + SITE_ID + " = ?";
		Reservation thisRes = jdbcT.queryForObject(sql, new ReservationRowMapper(), now, from, to, siteId);
		return thisRes.getReservationId();
	}

	// Unfortunately I think this is no longer needed after adding the get top 5 sites by 
	// campground ID and desired dates method that just got added to the Site JDBCDAO.
	// Too bad...
	@Override
	public boolean checkAvailabilityOfSite(int siteId, LocalDate from, LocalDate to) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + SITE_ID + " = ?";
		List<Reservation> siteRes = jdbcT.query(sql, new ReservationRowMapper(), siteId);
		boolean isAvailable = true, badFrom = false, badTo = false, badResFrom = false, badResTo = false;

		for (Reservation res : siteRes) {
			badFrom = res.getFromDate().compareTo(from) <= 0 && res.getToDate().compareTo(from) >= 0;
			badTo = res.getFromDate().compareTo(to) <= 0 && res.getToDate().compareTo(to) >= 0;
			badResFrom = from.compareTo(res.getFromDate()) <= 0 && to.compareTo(res.getFromDate()) >= 0;
			badResTo = from.compareTo(res.getToDate()) <= 0 && to.compareTo(res.getToDate()) >= 0;
			if (badFrom || badTo || badResFrom || badResTo)
				isAvailable = false;
		}
		return isAvailable;
	}
	
	

}
