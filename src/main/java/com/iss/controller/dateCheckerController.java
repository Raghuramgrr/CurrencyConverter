package com.iss.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Non-REST controller can take Date as input and gives whether the date is
 * valid or not
 *
 * 
 * Returns boolean value - usable by all the controllers
 * 
 * Try invalid Dates returns false If an Invalid Date is entered .
 * 
 * Try : 2015-02-30 // Feb 30 is not in 2015
 */
public class dateCheckerController {

	SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

	public boolean isValidDate(String param_date) {
		java.util.Date da = null;
		try {
			da = formater.parse(param_date);
		} catch (ParseException e) {
			return false;
		}
		if (!formater.format(da).equals(param_date)) {
			return false;
		}
		System.out.println("Inside valid dates" + da);

		Calendar cal = Calendar.getInstance();

		Date d = new Date(da.getTime());
		System.out.println(d);
		cal.setTime(d);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int DaysInMonth = 0;
		System.out.println(month + "" + "day\t" + day + "\t" + year);
		boolean validMonth = (month >= 1 && month <= 12);
		boolean leapyear = ((year % 4) == 0 && ((year % 100) != 0) || (year % 400) == 0);
		boolean validYear = (year >= 1590 && year <= 2400);

		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)

			DaysInMonth = 31;
		if (month == 4 || month == 6 || month == 9 || month == 11)

			DaysInMonth = 30;

		if (month == 2)

			DaysInMonth = 28;

		if (leapyear)

			DaysInMonth = 29;
		boolean validDay = (day >= 1 && day <= DaysInMonth);
		System.out.println(validMonth + "" + leapyear + "" + validYear + "\t" + validDay + "\t" + DaysInMonth);
		if (validMonth && validDay && validYear)
			return true;
		else if (validMonth == false || validDay == false || validYear == false)
			return false;
		return false;

	}

}
