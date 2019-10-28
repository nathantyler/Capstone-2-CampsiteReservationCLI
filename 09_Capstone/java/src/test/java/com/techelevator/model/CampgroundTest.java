package com.techelevator.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;



public class CampgroundTest {

	@Test
	public void testGetAndSetCampgroundId() {
		Campground camp = new Campground();
		camp.setCampgroundId(2);
		assertEquals(camp.getCampgroundId(),2);
	}

	@Test
	public void testGetAndSetParkId() {
		Campground camp = new Campground();
		camp.setParkId(4);
		assertEquals(camp.getParkId(),4);
	}
	
	@Test
	public void testGetAndSetName() {
		Campground camp = new Campground();
		camp.setName("joe");
		assertEquals(camp.getName(), "joe");
	}

	@Test
	public void testGetAndSetOpenFrom() {
		Campground camp = new Campground();
		camp.setOpenFrom("open right here");
		assertEquals(camp.getOpenFrom(), "open right here");
	}

	@Test
	public void testGetAndSetOpenTo() {
		Campground camp = new Campground();
		camp.setOpenTo("Open to the 5th parallel");
		assertEquals(camp.getOpenTo(),"Open to the 5th parallel");
	}


	@Test
	public void testSetDailyFeeString() {
		Campground camp = new Campground();
		String dailyFee = "12.32";
		camp.setDailyFee(dailyFee);
		assertEquals(camp.getDailyFee(),new BigDecimal (dailyFee));
	}

	@Test
	public void testSetDailyFeeBigDecimal() {
		
		BigDecimal bd1 = new BigDecimal ("1000.00");
		Campground camp = new Campground();
		camp.setDailyFee(bd1);
		assertEquals(camp.getDailyFee(),bd1);
	}

/*
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}
*/
}
