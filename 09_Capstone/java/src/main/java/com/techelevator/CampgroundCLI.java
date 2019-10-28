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

	private static final String MAIN_MENU_OPTION_PARKS = "Parks Info";
	private static final String MAIN_MENU_OPTION_CAMPGROUNDS = "Campground info";
//	private static final String MAIN_MENU_OPTION_SITES = "Sites";
	private static final String MAIN_MENU_OPTION_RESERVATIONS = "Make Reservations";
	private static final String MAIN_MENU_OPTION_EXIT = "End Application";
	private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTION_PARKS,
			MAIN_MENU_OPTION_CAMPGROUNDS, /* MAIN_MENU_OPTION_SITES, */ MAIN_MENU_OPTION_RESERVATIONS,
			MAIN_MENU_OPTION_EXIT };

	
	/*
	 * private static final String DEPT_MENU_OPTION_SEARCH_BY_NAME =
	 * "Department search by name"; private static final String
	 * DEPT_MENU_OPTION_DEPARTMENT_EMPLOYEES = "Show department employees"; private
	 * static final String DEPT_MENU_OPTION_ADD_DEPARTMENT = "Add a new department";
	 * private static final String DEPT_MENU_OPTION_UPDATE_NAME =
	 * "Update department name";
	 */
	private static final String MENU_OPTION_RETURN_TO_MAIN = "Return to Park Selection";
	private static final String PARK_MENU_OPTION_VIEW_CAMPGROUNDS = "View Campgrounds";
	private static final String[] PARK_MENU_OPTIONS = new String[] { PARK_MENU_OPTION_VIEW_CAMPGROUNDS,
			MENU_OPTION_RETURN_TO_MAIN };
	
	private static final String CG_OPTION_SEARCH_RES = "Search for Available Reservation";
	private static final String CG_OPTION_RET_TO_PREV = "Return to Previous Screen";
	private static final String[] CG_MENU_OPTIONS = new String[] { CG_OPTION_SEARCH_RES, /* CG_OPTION_RET_TO_PREV, */
			MENU_OPTION_RETURN_TO_MAIN };

	private ParkDAO pDao;
	private CampgroundDAO cgDao;
	private SiteDAO sDao;
	private ReservationDAO rDao;
	private List<Park> parks;
	private String[] parkNames;
	private List<Campground> camps;

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

		// create your DAOs here
		pDao = new JDBCParkDAO(dataSource);
		cgDao = new JDBCCampgroundDAO(dataSource);
		sDao = new JDBCSiteDAO(dataSource);
		rDao = new JDBCReservationDAO(dataSource);
		parks = pDao.getAllParksAlphabetically();
		parkNames = new String[parks.size() + 1];
		for (int i = 0; i < parks.size(); i++)
			parkNames[i] = parks.get(i).getName();
		parkNames[parkNames.length - 1] = MAIN_MENU_OPTION_EXIT;
//		LocalDate from = LocalDate.of(1999, 12, 31), to = LocalDate.of(2001, 9, 11);
//

//		List<Site> sites = sDao.getTopFiveAvailableSitesByCampId(7, from, to);
//		for (Site site : sites)
//			System.out.println(site.toString());

	}

	private void run() {
		while (true) {
			printHeading("Select a Park for Further Details");
			String choice = (String) menu.getChoiceFromOptions(parkNames);
			if (!choice.equals(MAIN_MENU_OPTION_EXIT)) {
				for (Park park : parks)
					if (choice.equals(park.getName()))
						handlePark(parks.indexOf(park));
				// handleDepartments();
				/*
				 * } else if (choice.equals(MAIN_MENU_OPTION_CAMPGROUNDS)) { //
				 * handleEmployees(); } else if (choice.equals(MAIN_MENU_OPTION_RESERVATIONS)) {
				 * // handleProjects();
				 */
			} else {
				System.exit(0);
			}
		}
	}

	private void handlePark(int index) {
		System.out.println("You chose this park: " + parks.get(index).getName());
		printHeading("Select a Command");
		String choice = (String) menu.getChoiceFromOptions(PARK_MENU_OPTIONS);
		if (choice.equals(PARK_MENU_OPTION_VIEW_CAMPGROUNDS))
			handleCampgrounds(index);
			
	}
	
	private void handleCampgrounds(int index) {
		camps = cgDao.getAllCampgroundsByParkId(parks.get(index).getParkId());
		printHeading("Campgrounds");
		for (Campground camp : camps) 
			System.out.println(camp.toString());
		printHeading("Select a Command");
		String choice = (String) menu.getChoiceFromOptions(CG_MENU_OPTIONS);
		if (choice.equals(CG_OPTION_SEARCH_RES)) {
			handleReservation(index);
		}
	}
	
	private void handleReservation(int index) {
		printHeading("Campgrounds");
		for (Campground camp : camps) 
			System.out.println(camp.toString());
	}

	private void printHeading(String headingText) {
		System.out.println("\n" + headingText);
		for (int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}
}
