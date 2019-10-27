package com.techelevator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.*;
import com.techelevator.model.jdbc.*;


public class CampgroundCLI {

	private static final String DATABASE_NAME = "campground";
	private static final String URL = "jdbc:postgresql://localhost:5432/" + DATABASE_NAME;
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "postgres1";
	private Menu menu;
	
	private ParkDAO pDao;
	private CampgroundDAO cgDao;
	private SiteDAO sDao;
	private ReservationDAO rD;

	private static final String MAIN_MENU_OPTION_PARKS = "View Parks";
//	private static final String MAIN_MENU_OPTION_CAMPGROUNDS = "Campgrounds";
//	private static final String MAIN_MENU_OPTION_SITES = "Sites";
//	private static final String MAIN_MENU_OPTION_RESERVATIONS = "Reservations";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTION_PARKS,
			/* MAIN_MENU_OPTION_CAMPGROUNDS, MAIN_MENU_OPTION_SITES, */ MAIN_MENU_OPTION_EXIT };

	private static final String MENU_OPTION_RETURN_TO_MAIN = "Return to main menu";

	private static final String DEPT_MENU_OPTION_ALL_DEPARTMENTS = "Show park information";
	private static final String DEPT_MENU_OPTION_SEARCH_BY_NAME = "Department search by name";
	private static final String DEPT_MENU_OPTION_DEPARTMENT_EMPLOYEES = "Show department employees";
	private static final String DEPT_MENU_OPTION_ADD_DEPARTMENT = "Add a new department";
	private static final String DEPT_MENU_OPTION_UPDATE_NAME = "Update department name";
	private static final String[] DEPARTMENT_MENU_OPTIONS = new String[] { DEPT_MENU_OPTION_ALL_DEPARTMENTS,
			DEPT_MENU_OPTION_SEARCH_BY_NAME, DEPT_MENU_OPTION_DEPARTMENT_EMPLOYEES, DEPT_MENU_OPTION_ADD_DEPARTMENT,
			DEPT_MENU_OPTION_UPDATE_NAME, MENU_OPTION_RETURN_TO_MAIN };


	public static void main(String[] args) {
		CampgroundCLI application = new CampgroundCLI();
		application.run();
	}

	public CampgroundCLI() {
		this.menu = new Menu(System.in, System.out);
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);

		//CampgroundCLI application = new CampgroundCLI(dataSource);
		// create your DAOs here
		LocalDate from = LocalDate.of(1999, 12, 31), to = LocalDate.of(2001, 9, 11);
		
		sDao = new JDBCSiteDAO(dataSource);
		List<Site> sites = sDao.getTopFiveAvailableSitesByCampId(7, from, to);
		for (Site site : sites)
			System.out.println(site.toString());

	}

	public void run() {

	}
}
