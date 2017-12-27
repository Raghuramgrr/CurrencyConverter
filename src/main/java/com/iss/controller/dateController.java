package com.iss.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iss.DAO.currencyDAO;
import com.iss.model.currencyModel;

@RestController
public class dateController { 
	dateCheckerController dateChecker = new dateCheckerController();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	ApplicationContext context = new ClassPathXmlApplicationContext("database//DBModule.xml");
	currencyDAO cDAO = (currencyDAO) context.getBean("currencyDAO");
	String totaldateString;

	@RequestMapping("/date")
	public String getRatesByDate(@RequestParam("text") String param_date) {
		totaldateString = "";
		boolean wrongPattern = dateChecker.isValidDate(param_date);
		currencyModel cObj = null;
		if (wrongPattern) {
			String date = param_date;
			System.out.println("date \t" + date);

			List<currencyModel> cModel = cDAO.findByDate(date);
			if (!cModel.isEmpty()) {
				Iterator<currencyModel> itr = cModel.iterator();
				while (itr.hasNext()) {

					cObj = itr.next();
					System.out.println(
							cObj.getSourceCurrency() + "" + cObj.getConversionRate() + "" + cObj.getTargetCurrency());
					addData(cObj.getSourceCurrency(), cObj.getConversionRate(), cObj.getTargetCurrency());
				}
			}

			else {
				return "No Data found in our Repo";
			}
			return totaldateString;
		}

		else
			return "Invalid Date Format";
	}

	public void addData(String sourceCurrency, String conversionRate, String targetCurrency) {
		totaldateString += sourceCurrency + "\t was \t " + conversionRate + "\t times \t" + targetCurrency + "\n";
		
	}

	}
