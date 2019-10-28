package com.techelevator.model.jdbc;

import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import com.techelevator.model.Park;
import com.techelevator.model.ParkRowMapper;

public class JDBCParkDAOIT {

	private static final long TEST_ID = 193;
	private JdbcTemplate jdbcT;
	private static SingleConnectionDataSource dataSource;
	private static JDBCParkDAO pDao;

	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/park");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}

	@AfterClass
	public static void closeDataSource() {
		dataSource.destroy();
	}

	@Before
	public void setup() {
		String sqlInsertPark = "insert into park (park_id, name, location, establish_date, area, visitors, description) "
				+ "values (?, 'Parkless', 'Delaware', '1901-03-12', 71326, 38103762, 'Nothing there')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertPark, TEST_ID);
		pDao = new JDBCParkDAO(dataSource);
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	/*
	@Test
	public Park getParkById(String TABLE_NAME, String PARK_ID) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + PARK_ID + " = ?";
		return jdbcT.queryForObject(sql, new ParkRowMapper(), PARK_ID);
	}
	*/
}
