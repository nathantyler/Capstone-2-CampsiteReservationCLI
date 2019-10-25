package com.techelevator.model;

import static org.junit.Assert.*;

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
	public void testGetDailyFee() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDailyFeeString() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDailyFeeBigDecimal() {
		fail("Not yet implemented");
	}


	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
