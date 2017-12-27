package com.iss.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.text.ParseException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import com.iss.controller.exchangerateController;


public class testExchangeRates {
exchangerateController exchangeTest;
	@Before
	public void initExchangeTester() {
		exchangeTest =new exchangerateController();
	}
	
	@Test
	public void testexchangeRates() { 
		try {
			assertNotEquals("abc", exchangeTest.getRatesByCurrency("2017-01-01 CHF USD"));
			assertNotEquals("abc",exchangeTest.getRatesByCurrency("2017-01-01 EUR CHF"));
			assertNotEquals("abc",exchangeTest.getRatesByCurrency("2017-01-01 GBP EUR"));
			assertNotEquals("abc",exchangeTest.getRatesByCurrency("2017-01-02 CHF EUR"));
			assertNotEquals("abc",exchangeTest.getRatesByCurrency("2017-01-03 CHF EUR"));
			assertNotEquals("abc",exchangeTest.getRatesByCurrency("2017-01-01 GBP OMR"));
			assertNotEquals("abc",exchangeTest.getRatesByCurrency("2017-01-01 KWD KWD"));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	@Test
	public void testSameCurrency() {
		try {
			assertEquals("kwd	is same as	kwd",exchangeTest.getRatesByCurrency("2017-01-01 KWD KWD"));
			assertEquals("usd	is same as	usd",exchangeTest.getRatesByCurrency("2017-01-01 USD USD"));
			assertEquals("eur	is same as	eur",exchangeTest.getRatesByCurrency("2017-01-01 EUR EUR"));
			assertEquals("chf	is same as	chf",exchangeTest.getRatesByCurrency("2017-01-01 CHF CHF"));
			assertEquals("gbp	is same as	gbp",exchangeTest.getRatesByCurrency("2017-01-01 GBP GBP"));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testunSupported() {
		try {
			assertEquals("we only support BHD	CHF	EUR	GBP	KWD	OMR	SGD as of now",exchangeTest.getRatesByCurrency("2017-01-01 INR KWD"));
			assertEquals("we only support BHD	CHF	EUR	GBP	KWD	OMR	SGD as of now",exchangeTest.getRatesByCurrency("2017-01-01 MYR KWD"));
			assertEquals("we only support BHD	CHF	EUR	GBP	KWD	OMR	SGD as of now",exchangeTest.getRatesByCurrency("2017-01-01 KRD KWD"));
			assertEquals("we only support BHD	CHF	EUR	GBP	KWD	OMR	SGD as of now",exchangeTest.getRatesByCurrency("2017-01-01 YEN KWD"));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testInvalidDates() { 
		try {
			assertEquals("Invalid Date format", exchangeTest.getRatesByCurrency("20//-01-01 CHF USD"));
			assertEquals("Invalid Date format", exchangeTest.getRatesByCurrency("3099-01-01 CHF USD"));
			assertEquals("Invalid Date format", exchangeTest.getRatesByCurrency("2456-78-41 CHF USD"));
			assertEquals("Invalid Date format", exchangeTest.getRatesByCurrency("2011+01+01 CHF USD"));
			assertEquals("Invalid Date format", exchangeTest.getRatesByCurrency("2045=11=31 CHF USD"));
			assertEquals("Invalid Date format", exchangeTest.getRatesByCurrency("4567-21-21 CHF USD"));
			assertEquals("Invalid Date format", exchangeTest.getRatesByCurrency("abcd-01-01 CHF USD"));
			assertEquals("Invalid Date format", exchangeTest.getRatesByCurrency("2017-21-45 CHF USD"));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
