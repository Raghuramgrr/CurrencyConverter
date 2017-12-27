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
	/**
	 * Tester hits and DB Retrieves the data from db.
	 *
	 * Sample - http://ec2-**-**-98-69.compute-1.amazonaws.com:8080/converter/date?text=2017-01-01 
	 * Returns all the Exchange rates on the specified Date
	 * 
	 * Try to see the data format by having not equals 
	 * 
	 * 
	 */
	@Test
	public void testRatesByDate() {
		assertNotEquals("abc", dateTest.getRatesByDate("2017-01-01"));
		assertNotEquals("abc", dateTest.getRatesByDate("2017-01-02"));
		assertNotEquals("abc", dateTest.getRatesByDate("2017-01-03"));
		assertNotEquals("abc", dateTest.getRatesByDate("2017-01-03"));
	}
	
	/**
	 * Tester hits and DB Retrieves the data from db. Verifies the length of the Result
	 *
	 * Sample - http://ec2-**-**-98-69.compute-1.amazonaws.com:8080/converter/date?text=2017-01-01 
	 *
	 * Returns a Int Value and we know for every valid date we have 7 tuples  
	 */
	@Test
	public void testLengthByDate() {
		assertEquals(7,resultLength(dateTest.getRatesByDate("2017-01-01")));
		assertEquals(7,resultLength(dateTest.getRatesByDate("2017-01-02")));
		assertEquals(7,resultLength(dateTest.getRatesByDate("2017-01-03")));
		assertEquals(7,resultLength(dateTest.getRatesByDate("2017-01-04")));
	}
	/**
	 * Tester hits and DB Retrieves the data from db. Verifies there is no data for a valid data 
	 *
	 * Sample - http://ec2-**-**-98-69.compute-1.amazonaws.com:8080/converter/date?text=2017-01-01 
	 * 
	 * Valid Input - checks the DB and confirms no data in the DB
	 * 
	 */
	@Test
	public void testNoData() {
		assertEquals("No Data found in our Repo", dateTest.getRatesByDate("2018-01-03"));
		assertEquals("No Data found in our Repo", dateTest.getRatesByDate("2018-11-03"));
		assertEquals("Invalid Date Format", dateTest.getRatesByDate("2098-01-33"));
		assertEquals("No Data found in our Repo", dateTest.getRatesByDate("2118-01-23"));
		assertEquals("No Data found in our Repo", dateTest.getRatesByDate("2018-04-23"));
	}
	/**
	 * Tester hits and DB Retrieves the data from db. Verifies the Invalid Dates 
	 *
	 * Sample - http://ec2-**-**-98-69.compute-1.amazonaws.com:8080/converter/date?text=2017-01-01 
	 *
	 * Returns Invalid Date format if date does not follow yyyy-MM-DD
	 * 
	 */
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
	/**
	 * Tester hits and DB Retrieves the data from db. Retrieves the length of result set
	 * 
	 * Sample - http://ec2-**-**-98-69.compute-1.amazonaws.com:8080/converter/date?text=2017-01-01 
	 *
	 * Returns a Int Value counting the number of Lines in resultSet
	 *  
	 */
   
	private int resultLength(String ratesByDate) {
		 String[] lines = ratesByDate.split("\r\n|\r|\n");
		   return  lines.length;
		
	}

}
