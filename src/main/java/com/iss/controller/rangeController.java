package com.iss.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iss.DAO.currencyDAO;
import com.iss.model.currencyModel;

@RestController
public class rangeController {
	dateCheckerController dateChecker = new dateCheckerController();
	String totalString;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	ApplicationContext context = new ClassPathXmlApplicationContext("database//DBModule.xml");
	currencyDAO cDAO = (currencyDAO) context.getBean("currencyDAO");

	dateController dateObj = new dateController();

	@RequestMapping("/range")
	public String getRatesByDateRange(@RequestParam("text") String dates) throws ParseException {
		totalString = new String();
		String[] tokens = dates.toString().split(" ");
		if (tokens.length != 2) {
			return "wrong entry format try /converter range 2017-01-01 2017-02-02";
		}
		String sParamdate = tokens[0];
		String eParamdate = tokens[1];
		if(sParamdate.equals(eParamdate)) {
			return "Both start and end dates are the same ";
		}
		if(dateChecker.isValidDate(sParamdate)&&dateChecker.isValidDate(eParamdate)) {
		List<currencyModel> rangeModel = null;
		currencyModel rObj = null;
		rangeModel = cDAO.findByDateRange(sParamdate, eParamdate);
		System.out.println("size of the list added" + rangeModel.size());
		Iterator<currencyModel> itr = rangeModel.iterator();
		while (itr.hasNext()) {
			rObj = itr.next();
			addData(rObj.getSourceCurrency(), rObj.getConversionRate(), rObj.getTargetCurrency());
		}

		return totalString;

	}
	
	else
	{
		return "Invalid Date format - Try 2017-01-01"; 
	}
	}
	public void addData(String sourceCurrency, String conversionRate, String targetCurrency) {
		totalString += sourceCurrency + "\t was\t" + conversionRate + "\ttimes\t" + targetCurrency + "\n";
		// System.out.println("TotalString"+totalString);
	}
	
	
}
