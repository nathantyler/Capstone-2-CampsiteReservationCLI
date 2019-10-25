package com.techelevator.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ReservationRowMapper implements RowMapper<Reservation> {

	@Override
	public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
		Reservation res = new Reservation();
		res.setReservationId(rs.getInt("reservation_id"));
		res.setSiteId(rs.getInt("site_id"));
		res.setName(rs.getString("name"));
		res.setFromDate(rs.getDate("from_date").toLocalDate());
		res.setToDate(rs.getDate("to_date").toLocalDate());
		res.setCreateDate(rs.getDate("create_date").toLocalDate());
		return res;
	}

}
