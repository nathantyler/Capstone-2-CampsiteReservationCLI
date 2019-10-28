package com.techelevator.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class SiteTest {

	@Test
	public void testGetAndSetSiteId() {
		Site site = new Site();
		site.setSiteId(1);
		assertEquals(site.getSiteId(), 1);
	}

	@Test
	public void testGetAndSetCampgroundId() {
		Site site = new Site();
		site.setCampgroundId(2);
		assertEquals(site.getCampgroundId(), 2);
	}

	@Test
	public void testGetAndSetSiteNumber() {
		Site site = new Site();
		site.setSiteNumber(3);
		assertEquals(site.getSiteNumber(), 3);
	}

	@Test
	public void testGetAndSetMaxOccupancy() {
		Site site = new Site();
		site.setMaxOccupancy(200);
		assertEquals(site.getMaxOccupancy(), 200);
	}

	@Test
	public void testIsAccessible() {
		Site site = new Site();
		site.isAccessible();
		assertEquals(site.isAccessible(), false);
	}

	@Test
	public void testGetAndSetMaxRVLength() {
		Site site = new Site();
		site.setMaxRVLength(750);
		assertEquals(site.getMaxRVLength(), 750);
	}

	@Test
	public void testSetUtilities() {
		Site site = new Site();
		site.setUtilities(true);
		assertEquals(site.isUtilities(), true);
	}
	/*
	 * @Test public void testToString() { fail("Not yet implemented"); }
	 */
}
