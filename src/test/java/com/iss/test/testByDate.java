package com.iss.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.text.ParseException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.iss.controller.dateController;

public class testByDate {
	dateController dateTest;

	@Before
	public void initfileTester() {

		dateTest = new dateController();
	}

	@Test
	public void testRatesByDate() {
		assertNotEquals("abc", dateTest.getRatesByDate("2017-01-01"));
		assertNotEquals("abc", dateTest.getRatesByDate("2017-01-02"));
		assertNotEquals("abc", dateTest.getRatesByDate("2017-01-03"));
		assertNotEquals("abc", dateTest.getRatesByDate("2017-01-03"));
	}
	
	@Test
	public void testLengthByDate() {
		assertEquals(7,resultLength(dateTest.getRatesByDate("2017-01-01")));
		assertEquals(7,resultLength(dateTest.getRatesByDate("2017-01-02")));
		assertEquals(7,resultLength(dateTest.getRatesByDate("2017-01-03")));
		assertEquals(7,resultLength(dateTest.getRatesByDate("2017-01-04")));
	}
	
	@Test
	public void testNoData() {
		assertEquals("No Data found in our Repo", dateTest.getRatesByDate("2018-01-03"));
		assertEquals("No Data found in our Repo", dateTest.getRatesByDate("2018-11-03"));
		assertEquals("Invalid Date Format", dateTest.getRatesByDate("2098-01-33"));
		assertEquals("No Data found in our Repo", dateTest.getRatesByDate("2118-01-23"));
		assertEquals("No Data found in our Repo", dateTest.getRatesByDate("2018-04-23"));
	}
	
	@Test
	public void testInvalidFormat() {
	assertEquals("Invalid Date Format", dateTest.getRatesByDate("2017/01-04"));
	assertEquals("Invalid Date Format", dateTest.getRatesByDate("20a7/01-04"));
	assertEquals("Invalid Date Format", dateTest.getRatesByDate("2098-01-33"));
	
	assertEquals("Invalid Date Format", dateTest.getRatesByDate("20s7/01-04"));
	assertEquals("Invalid Date Format", dateTest.getRatesByDate("3474+01+04"));
	assertEquals("Invalid Date Format", dateTest.getRatesByDate("2996-01-44"));
	assertEquals("Invalid Date Format", dateTest.getRatesByDate("3118-04-23"));
	
	}
	

	private int resultLength(String ratesByDate) {
		 String[] lines = ratesByDate.split("\r\n|\r|\n");
		   return  lines.length;
		
	}

}
