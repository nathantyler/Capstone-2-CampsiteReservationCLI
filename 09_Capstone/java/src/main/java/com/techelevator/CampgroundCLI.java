package com.techelevator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
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

	private static final String MENU_OPTION_RETURN_TO_MAIN = "Return to Park Selection";
	private static final String PARK_MENU_OPTION_VIEW_CAMPGROUNDS = "View Campgrounds";
	private static final String[] PARK_MENU_OPTIONS = new String[] { PARK_MENU_OPTION_VIEW_CAMPGROUNDS,
			MENU_OPTION_RETURN_TO_MAIN };

	private static final String CG_OPTION_SEARCH_RES = "Search for Available Reservation";
	private static final String CG_OPTION_RET_TO_PREV = "Return to Previous Screen";
	private static final String[] CG_MENU_OPTIONS = new String[] { CG_OPTION_SEARCH_RES, MENU_OPTION_RETURN_TO_MAIN };

	private static final String SEARCH_AGAIN_YES = "Yes";
	private static final String SEARCH_AGAIN_NO = "No";
	private static final String[] SEARCH_AGAIN_OPTIONS = new String[] { SEARCH_AGAIN_YES, SEARCH_AGAIN_NO };

	private ParkDAO pDao;
	private CampgroundDAO cgDao;
	private SiteDAO sDao;
	private ReservationDAO rDao;
	private List<Park> parks;
	private String[] parkNames;
	private List<Campground> camps;
	private static Scanner scan;

	public static void main(String[] args) {
		CampgroundCLI application = new CampgroundCLI();
		scan = new Scanner(System.in);
		application.run();
		scan.close();
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
		System.out.println("");
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
		System.out.println("");
		for (Campground camp : camps)
			System.out.println(camps.indexOf(camp) + 1 + ") " + camp.toString());
		int campNum = grabCampChoice();
		Campground desiredCamp = null;
		LocalDate from = null, to = null;
		if (campNum > 0) {
			desiredCamp = camps.get(campNum - 1);
			System.out.println("What is the desired arrival date? (YYYY/MM/DD)>>");
			from = grabDesiredDate();
			System.out.println("What is the desired departure date? (YYYY/MM/DD)>>");
			to = grabDesiredDate();
			while (to.isBefore(from)) {
				System.out.println("Your departure date is before your arrival date.\n"
						+ "Please enter a new departure date (YYYY/MM/DD)>>");
				to = grabDesiredDate();
			}
			if (desiredCamp.CampgroundIsClosed(from, to)) {
				System.out.println("The campground is closed during your desired stay...\n" + "Starting over.\n");
				handleReservation(index);
			}
			System.out.println(from + "-" + to);
			System.out.println(desiredCamp.toString());
			handleSiteChoice(desiredCamp, from, to, index);
		}

	}

	private void handleSiteChoice(Campground cg, LocalDate from, LocalDate to, int index) {
		List<Site> sites = sDao.getTopFiveAvailableSitesByCampId(cg.getCampgroundId(), from, to);
		if (sites.isEmpty()) {
			System.out.println("There are no camp sites matching your criteria.\n" + "Would you like to search again?");
			String choice = (String) menu.getChoiceFromOptions(SEARCH_AGAIN_OPTIONS);
			if (choice.equals(SEARCH_AGAIN_YES))
				handleReservation(index);
		} else {
			for (Site site : sites)
				System.out.println(sites.indexOf(site) + 1 + ") " + site.toString());
			System.out.println("Which site should be reserved (enter 0 to cancel)>> ");
			int choice = grabSiteChoice(sites.size());
			System.out.println(sites.get(choice - 1).toString());
			if (choice != 0)
				System.out.println("The reservation has been made and the confirmation ID is "
						+ makeReservation(sites.get(choice - 1).getSiteId(), from, to));
		}

	}

	private int makeReservation(int siteId, LocalDate from, LocalDate to) {
		// scan = new Scanner(System.in);
		System.out.println("What name should the reservation be made under? ");
		String name = scan.nextLine();
		return rDao.makeReservation(siteId, from, to, name);
	}

	private int grabSiteChoice(int size) {
//		@SuppressWarnings("resource")
//		Scanner scan = new Scanner(System.in);
		int choiceNum = 0;
		boolean tryAgain = true;
		while (tryAgain) {
			String choice = scan.nextLine();
			try {
				choiceNum = Integer.parseInt(choice);
			} catch (NumberFormatException e) {
				System.out.println("That was not a valid choice. Try again>> ");
			}
			if (0 <= choiceNum && choiceNum <= size)
				tryAgain = false;
			else
				System.out.println("That was not a valid choice. Try again >> ");
		}
		return choiceNum;
	}

	private LocalDate grabDesiredDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/M/d");
		dtf = dtf.withResolverStyle(ResolverStyle.STRICT);
//		@SuppressWarnings("resource")
//		Scanner scan = new Scanner(System.in);
		String userStr = "";
		LocalDate date = null;
		boolean moveOn = false;
		while (!moveOn) {
			userStr = scan.nextLine();
			try {
				date = LocalDate.parse(userStr, dtf);
				if (date.isBefore(LocalDate.now())) {
					System.out.println("The date you entered is before now and therefore invalid.\n"
							+ "Try again (YYYY/MM/DD)>> ");
				} else
					moveOn = true;
			} catch (Exception e) {
				System.out.println(
						"There was something wrong with the date you entered.\n" + "Try again (YYYY/MM/DD)>> ");
			}
		}
		return date;
	}

	private int grabCampChoice() {
		System.out.println("");
		System.out.println("Which campground (enter 0 to cancel)?");
//		@SuppressWarnings("resource")
//		Scanner scan = new Scanner(System.in);
		String userCamp = scan.nextLine();
		int campNum = -1;
		boolean moveOn = false;
		while (!moveOn) {
			try {
				campNum = Integer.parseInt(userCamp);
			} catch (NumberFormatException e) {
				System.out.println("That's not a number. Please try again>> ");
				userCamp = scan.nextLine();
			}
			if (0 <= campNum && campNum <= camps.size())
				moveOn = true;
			else {
				System.out.println("That choice doesn't correspond to a listed camp.\nPlease try again>> ");
				userCamp = scan.nextLine();
			}
		}
		return campNum;
	}

	private void printHeading(String headingText) {
		System.out.println("\n" + headingText);
		for (int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}

	}
}
