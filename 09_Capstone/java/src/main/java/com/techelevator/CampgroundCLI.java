package com.techelevator;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

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

	public CampgroundCLI(DataSource datasource) {
		// create your DAOs here
	}

	public void run() {

	}
}
