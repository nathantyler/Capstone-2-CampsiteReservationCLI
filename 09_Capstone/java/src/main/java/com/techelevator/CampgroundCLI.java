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
		
		/* This is just a test of the to see that the RowMapper classes.
		 * This will not be staying like this for long.
		 * (Especially since the RowMappers have been confirmed to work). 
		 *  */
		
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM campground";
		List<Campground> camps = jdbc.query(sql, new CampgroundRowMapper());
		for (Campground camp : camps) {
			System.out.println(camp.toString() + "   " + camp.getOpenFromInt() + "    " + camp.getOpenToInt());
		}
		System.out.println("\n\n");
		sql = "SELECT * FROM park";
		List<Park> parks = jdbc.query(sql, new ParkRowMapper());
		for (Park park : parks) {
			System.out.println(park.toString());
		}
		
		System.out.println("\n\n");
		sql = "SELECT * FROM reservation";
		List<Reservation> reses = jdbc.query(sql, new ReservationRowMapper());
		for (Reservation res : reses) {
			System.out.println(res.toString());
		}
		
		System.out.println("\n\n");
		sql = "SELECT * FROM site";
		List<Site> sites = jdbc.query(sql, new SiteRowMapper());
		for (Site site : sites) {
			System.out.println(site.toString());
		}
	}

	public void run() {
		
	}
}
