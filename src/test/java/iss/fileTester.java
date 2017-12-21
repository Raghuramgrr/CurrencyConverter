package iss;


import static org.junit.Assert.assertNotEquals;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import controller.dateController;
import controller.parseController;
import controller.rangeController;

public class fileTester {
	
	parseController fileTest ;
	dateController dateTest;
	rangeController rangeTest;
	@Before
	public void initfileTester() {
	fileTest =  new parseController();
	dateTest=new dateController();
	rangeTest=new rangeController();
	}
	
	@Test 
	public void testInput() throws InterruptedException {
	try {
		assertNotEquals("Welcome This is your first bet Zlatan" , fileTest.parseFiles());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	
	}
	
	@Test
	public void testRatesByDate() {
		assertNotEquals("abc",dateTest.getRatesByDate("2017-01-01"));
		assertNotEquals("abc",dateTest.getRatesByDate("2017-01-02"));
		assertNotEquals("abc",dateTest.getRatesByDate("2017-01-03"));
		assertNotEquals("abc",dateTest.getRatesByDate("2017-01-03"));
		
		//assertEquals("Invalid date format",dateTest.getRatesByDate("2017/01-04"));
		
		
	}
	
	
	@Test
	public void testRatesByDateRange() {
		try {
			assertNotEquals("abc",rangeTest.getRatesByDateRange("2017-01-01", "2017-01-03"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	}
	
	


