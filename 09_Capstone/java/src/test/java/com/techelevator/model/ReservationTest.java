package com.techelevator.model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class ReservationTest {

	@Test
	public void testGetAndSetReservationId() {
		Reservation reserve = new Reservation();
		reserve.setReservationId(5);
		assertEquals(reserve.getReservationId(), 5);
	}

	@Test
	public void testGetAndSetSiteId() {
		Reservation reserve = new Reservation();
		reserve.setSiteId(2);
		assertEquals(reserve.getSiteId(), 2);
	}

	@Test
	public void testGetAndSetName() {
		Reservation reserve = new Reservation();
		reserve.setName("joe");
		assertEquals(reserve.getName(), "joe");
	}

	@Test
	public void testGetAndSetFromDate() {
		Reservation reserve = new Reservation();
		LocalDate date = LocalDate.of(2013, 01, 21);
		reserve.setFromDate(date);
		assertEquals(reserve.getFromDate(), date);
	}

	@Test
	public void testGetAndSetToDate() {
		Reservation reserve = new Reservation();
		LocalDate date = LocalDate.of(1913, 05, 01);
		reserve.setToDate(date);
		assertEquals(reserve.getToDate(), date);
	}

	@Test
	public void testGetCreateDate() {
		Reservation reserve = new Reservation();
		LocalDate date = LocalDate.of(1613, 11, 22);
		reserve.setCreateDate(date);
		assertEquals(reserve.getCreateDate(), date);
	}

/*
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}
*/
}
