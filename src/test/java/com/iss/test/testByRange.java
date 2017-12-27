package com.iss.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.text.ParseException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import com.iss.controller.rangeController;

public class testByRange {
	rangeController rangeTest;

	@Before
	public void initrangeTester() {

		rangeTest = new rangeController();
	}

	@Test
	public void testRatesByDateRange() {
		try {
			assertNotEquals("abc", rangeTest.getRatesByDateRange("2017-01-01 2017-01-03"));
			assertNotEquals("abc", rangeTest.getRatesByDateRange("2017-01-01 2017-01-02"));
			assertNotEquals("abc", rangeTest.getRatesByDateRange("2017-01-01 2017-01-06"));
			assertNotEquals("abc", rangeTest.getRatesByDateRange("2017-01-01 2017-01-01"));

			assertNotEquals("abc", rangeTest.getRatesByDateRange("2016-01-01 2015-01-03"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@Test 
	public void testSameDates() {
		try {
			assertEquals("Both start and end dates are the same ", rangeTest.getRatesByDateRange("2017-01-01 2017-01-01"));	
			assertEquals("Both start and end dates are the same ", rangeTest.getRatesByDateRange("2017-02-21 2017-02-21"));
			
			assertEquals("Both start and end dates are the same ", rangeTest.getRatesByDateRange("2017-01-03 2017-01-03"));
			assertEquals("Both start and end dates are the same ", rangeTest.getRatesByDateRange("2018-01-03 2018-01-03"));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	public void tesresultLength() {
		try {
		
		assertEquals(21,resultLength(rangeTest.getRatesByDateRange("2017-01-01 2017-01-03")));
		assertEquals(28,resultLength(rangeTest.getRatesByDateRange("2017-01-01 2017-01-04")));
		assertEquals(14,resultLength(rangeTest.getRatesByDateRange("2017-01-01 2017-01-02")));
		assertEquals(35,resultLength(rangeTest.getRatesByDateRange("2017-01-01 2017-01-05")));
		assertEquals(1,resultLength(rangeTest.getRatesByDateRange("2017-01-01 2017-01-01")));
		assertEquals(42,resultLength(rangeTest.getRatesByDateRange("2017-01-01 2017-01-06")));
		
		
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testInvaliddates() {
		try {
			assertEquals("Invalid Date format - Try 2017-01-01", rangeTest.getRatesByDateRange("20*7-23-01 2017-31-01"));	
			assertEquals("Invalid Date format - Try 2017-01-01", rangeTest.getRatesByDateRange("4027-01-01 2017-31-01"));	
			assertEquals("Invalid Date format - Try 2017-01-01", rangeTest.getRatesByDateRange("22227-23-01 2017-31-01"));	
			assertEquals("Invalid Date format - Try 2017-01-01", rangeTest.getRatesByDateRange("20*7-23-01 2dfa17-31-01"));	
			assertEquals("Invalid Date format - Try 2017-01-01", rangeTest.getRatesByDateRange("20*7-23-01 2017+31/01"));	
			
			
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	private int resultLength(String ratesByDate) {
		 String[] lines = ratesByDate.split("\r\n|\r|\n");
		   return  lines.length;
		
	}
}