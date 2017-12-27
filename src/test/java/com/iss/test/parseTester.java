package com.iss.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.text.ParseException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.iss.controller.dateController;
import com.iss.controller.exchangerateController;
import com.iss.controller.parseController;
import com.iss.controller.rangeController;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class parseTester {

	parseController fileTest;
	dateController dateTest;
	rangeController rangeTest;
	exchangerateController exchangeTest;

	@Before
	public void initfileTester() {
		fileTest = new parseController();
		dateTest = new dateController();
		rangeTest = new rangeController();
		exchangeTest = new exchangerateController();
	}
  
	@Test
	public void testInput() throws InterruptedException {
		try {
			assertNotEquals("parsing Done", fileTest.parseFiles("raghu"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
