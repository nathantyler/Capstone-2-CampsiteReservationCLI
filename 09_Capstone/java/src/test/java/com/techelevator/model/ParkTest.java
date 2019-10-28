package com.techelevator.model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class ParkTest {

	@Test
	public void testSetAndGetParkId() {
		Park park = new Park();
		park.setParkId(2);
		assertEquals(park.getParkId(), 2);
	}

	@Test
	public void testGetAndSetName() {
		Park park = new Park();
		park.setName("George Popadopolis");
		assertEquals(park.getName(), "George Popadopolis");
	}

	@Test
	public void testGetAndSetLocation() {
		Park park = new Park();
		park.setLocation("Bora Bora");
		assertEquals(park.getLocation(), "Bora Bora");
	}

	@Test
	public void testGetAndSetEstablishDate() {
		Park park = new Park();
		LocalDate date = LocalDate.of(2013, 01, 21);
		park.setEstablishDate(date);
		assertEquals(park.getEstablishDate(), date);
	}

	@Test
	public void testGetAndSetArea() {
		Park park = new Park();
		park.setArea(25);
		assertEquals(park.getArea(), 25);
	}

	@Test
	public void testGetAndSetVisitors() {
		Park park = new Park();
		park.setVisitors(300);
		assertEquals(park.getVisitors(), 300);
	}

	@Test
	public void testGetAndSetDescription() {
		Park park = new Park();
		park.setDescription("The mountaintops are covered with snow and the lakes are frozen over");
		assertEquals(park.getDescription(),"The mountaintops are covered with snow and the lakes are frozen over");
	}

/*
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}
*/
}
