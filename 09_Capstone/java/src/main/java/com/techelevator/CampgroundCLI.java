package com.techelevator;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.*;

public class CampgroundCLI {
	
	private static final String DATABASE_NAME = "campground";
	private static final String URL = "jdbc:postgresql://localhost:5432/" + DATABASE_NAME;
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "postgres1";
	

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		
		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}

	public CampgroundCLI(DataSource dataSource) {
		// create your DAOs here
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM campground";
		List<Campground> camps = jdbc.query(sql, new CampgroundRowMapper());
		for (Campground camp : camps) {
			System.out.println(camp.toString() + "   " + camp.getOpenFromInt() + "    " + camp.getOpenToInt());
		}
	}

	public void run() {
		
	}
}
