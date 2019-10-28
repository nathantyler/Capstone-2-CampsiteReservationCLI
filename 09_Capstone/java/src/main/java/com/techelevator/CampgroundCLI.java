package com.techelevator;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.model.*;
import com.techelevator.model.jdbc.*;

public class CampgroundCLI {

	private static final String DATABASE_NAME = "campground";
	private static final String URL = "jdbc:postgresql://localhost:5432/" + DATABASE_NAME;
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "postgres1";
	private Menu menu;

	private static final String MAIN_MENU_OPTION_EXIT = "End Application";

	private static final String MENU_OPTION_RETURN_TO_MAIN = "Return to Park Selection";
	private static final String PARK_MENU_OPTION_VIEW_CAMPGROUNDS = "View Campgrounds";
	private static final String[] PARK_MENU_OPTIONS = new String[] { PARK_MENU_OPTION_VIEW_CAMPGROUNDS,
			MENU_OPTION_RETURN_TO_MAIN };

	private static final String CG_OPTION_SEARCH_RES = "Search for Available Reservation";
	@SuppressWarnings("unused")
	private static final String CG_OPTION_RET_TO_PREV = "Return to Previous Screen";
	private static final String[] CG_MENU_OPTIONS = new String[] { CG_OPTION_SEARCH_RES, MENU_OPTION_RETURN_TO_MAIN };

	private static final String SEARCH_AGAIN_YES = "Yes";
	private static final String SEARCH_AGAIN_NO = "No";
	private static final String[] SEARCH_AGAIN_OPTIONS = new String[] { SEARCH_AGAIN_YES, SEARCH_AGAIN_NO };

	private static final String LOCATION = "Location:";
	private static final String ESTABLISHED = "Established:";
	private static final String AREA = "Area:";
	private static final String ANNUAL_VISITORS = "Annual Visitors:";

	private static final String NUM = "#)";
	private static final String NAME = "Name";
	private static final String OPEN = "Open";
	private static final String CLOSE = "Close";
	private static final String DAILY_FEE = "Daily Fee";

//	private static final String CAMPGROUND = "Campground";
	private static final String SITE_NUM = "Site No.";
	private static final String MAX_OCCUP = "Max Occup.";
	private static final String ACCESSIBLE = "Accessible?";
	private static final String RV_LEN = "RV Len";
	private static final String UTILITY = "Utility";
	private static final String COST = "Cost";

	private static final int MAX_LINE_LENGTH = 80;

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

		pDao = new JDBCParkDAO(dataSource);
		cgDao = new JDBCCampgroundDAO(dataSource);
		sDao = new JDBCSiteDAO(dataSource);
		rDao = new JDBCReservationDAO(dataSource);
		parks = pDao.getAllParksAlphabetically();
		parkNames = new String[parks.size() + 1];
		for (int i = 0; i < parks.size(); i++)
			parkNames[i] = parks.get(i).getName();
		parkNames[parkNames.length - 1] = MAIN_MENU_OPTION_EXIT;

	}

	private void run() {
		while (true) {
			printHeading("Select a Park for Further Details");
			String choice = (String) menu.getChoiceFromOptions(parkNames);
			if (!choice.equals(MAIN_MENU_OPTION_EXIT)) {
				for (Park park : parks)
					if (choice.equals(park.getName()))
						handlePark(parks.indexOf(park));
			} else
				System.exit(0);
		}
	}

	private void handlePark(int index) {
		printParkInfo(parks.get(index));
		printHeading("Select a Command");
		String choice = (String) menu.getChoiceFromOptions(PARK_MENU_OPTIONS);
		if (choice.equals(PARK_MENU_OPTION_VIEW_CAMPGROUNDS))
			handleCampgrounds(index);

	}

	private void handleCampgrounds(int index) {
		camps = cgDao.getAllCampgroundsByParkId(parks.get(index).getParkId());
		printHeading(parks.get(index).getName() + " National Park Campgrounds");
		System.out.println("");
		printCampInfo(camps);
		printHeading("Select a Command");
		String choice = (String) menu.getChoiceFromOptions(CG_MENU_OPTIONS);
		if (choice.equals(CG_OPTION_SEARCH_RES)) {
			handleReservation(index);
		}
	}

	private void handleReservation(int index) {
		printHeading(parks.get(index).getName() + " National Park Campgrounds");
		System.out.println("");
		printCampInfo(camps);
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
			System.out.println("");
			printSiteInfo(sites, cg.getDailyFee());
			System.out.println("Which site should be reserved (enter 0 to cancel)>> ");
			int choice = grabSiteChoice(sites.size());
			if (choice != 0)
				System.out.println("The reservation has been made and the confirmation ID is "
						+ makeReservation(sites.get(choice - 1).getSiteId(), from, to) + ".");
		}

	}

	private int makeReservation(int siteId, LocalDate from, LocalDate to) {
		System.out.println("What name should the reservation be made under? ");
		String name = scan.nextLine();
		return rDao.makeReservation(siteId, from, to, name);
	}

	private int grabSiteChoice(int size) {

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

	public void printSiteInfo(List<Site> sites, BigDecimal cost) {

		int count = 0;
		System.out.println(NUM + generateSpace(NUM, 5) + SITE_NUM + generateSpace(SITE_NUM, 12) + MAX_OCCUP
				+ generateSpace(MAX_OCCUP, 15) + ACCESSIBLE + generateSpace(ACCESSIBLE, 16) + RV_LEN
				+ generateSpace(RV_LEN, 10) + UTILITY + generateSpace(UTILITY, 11) + COST);
		for (Site site : sites) {
			count++;
			String numParen = count + ")";
			System.out.println(numParen + generateSpace(numParen, 5) + site.getSiteNumber()
					+ generateSpace(site.getSiteNumber() + "", 12) + site.getMaxOccupancy()
					+ generateSpace(site.getMaxOccupancy() + "", 15) + site.getAccessibleString()
					+ generateSpace(site.getAccessibleString(), 16) + site.getRvLenString()
					+ generateSpace(site.getRvLenString(), 10) + site.getUtilitiesString()
					+ generateSpace(site.getUtilitiesString(), 11) + "$" + cost.setScale(2));
		}

	}

	public void printCampInfo(List<Campground> cgs) {
		int maxNameLen = 0;
		for (Campground cg : cgs)
			maxNameLen = maxNameLen <= cg.getName().length() ? cg.getName().length() : maxNameLen;

		int bigSpace = maxNameLen + 6, smallSpace = 5, mediumSpace = 12;
		int count = 1;
		System.out.println(NUM + generateSpace(NUM, smallSpace) + NAME + generateSpace(NAME, bigSpace) + OPEN
				+ generateSpace(OPEN, mediumSpace) + CLOSE + generateSpace(CLOSE, mediumSpace) + DAILY_FEE);
		for (Campground cg : cgs) {
			String numParen = count + ")";
			System.out.println(numParen + generateSpace(numParen, smallSpace) + cg.getName()
					+ generateSpace(cg.getName(), bigSpace) + cg.getOpenMonthName()
					+ generateSpace(cg.getOpenMonthName(), mediumSpace) + cg.getClosedMonthName()
					+ generateSpace(cg.getClosedMonthName(), mediumSpace) + "$" + cg.getDailyFee().setScale(2));
			count++;
		}
	}

	public void printParkInfo(Park park) {
		int spaces = 18;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/uuuu");
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(true);
		System.out.println(park.getName() + " National Park");
		System.out.println(LOCATION + generateSpace(LOCATION, spaces) + park.getLocation());
		System.out.println(ESTABLISHED + generateSpace(ESTABLISHED, spaces) + park.getEstablishDate().format(dtf));
		System.out.println(AREA + generateSpace(AREA, spaces) + nf.format(park.getArea()) + " sq. km.");
		System.out.println(ANNUAL_VISITORS + generateSpace(ANNUAL_VISITORS, spaces) + nf.format(park.getVisitors()));
		System.out.println("");
		System.out.println(park.breakUpDescription(MAX_LINE_LENGTH));
	}

	public String generateSpace(String str, int spaces) {
		int spaceLength = spaces - str.length();
		String space = "";
		for (int i = 1; i <= spaceLength; i++)
			space += " ";

		return space;
	}

	private void printHeading(String headingText) {
		System.out.println("\n" + headingText);
		for (int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}

	}
}
