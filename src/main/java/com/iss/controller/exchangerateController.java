package com.iss.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iss.DAO.currencyDAO;
import com.iss.DAO.exchangeDAO;
import com.iss.controller.dateController;
import com.iss.controller.parseController;

import com.iss.model.currencyModel;

import net.sf.ehcache.management.CacheManager;

@RestController
public class exchangerateController {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	dateCheckerController dateChecker = new dateCheckerController();
	ApplicationContext context = new ClassPathXmlApplicationContext("database//DBModule.xml");
	exchangeDAO cDAO = (exchangeDAO) context.getBean("exchangeDAO");

	@RequestMapping("/exchange")
	public String getRatesByCurrency(@RequestParam("text") String text) throws ParseException {
		String[] tokens = text.toString().toLowerCase().split(" ");
		String actParamDate = "";
		String sCurr = "";
		String tCurr = "";
		Float sRate = null;
		Float tRate = null;
		Float exRate = null;
		boolean wrongDatePattern = false;
		boolean unsupportedcCurr =false;
		boolean unsupportedtCurr =false;
		if (tokens.length != 3) {
			return "Incorrect format Try //currencyexchange 2017-01-01 CHF EUR";
		} 
		
		else if (tokens.length == 3) {
 
			actParamDate = tokens[0];
			sCurr = tokens[1];
			tCurr = tokens[2];

			System.out.println(actParamDate + "\t" + sCurr + "\t" + tCurr);
			
			
			 wrongDatePattern = dateChecker.isValidDate(actParamDate);
			
			System.out.println(actParamDate +"\t"+wrongDatePattern);
		
			
			if(sCurr.equalsIgnoreCase(tCurr)) {
				return sCurr +"\tis same as\t"+ tCurr;
			}
			
			unsupportedcCurr=supportedCurrCheck(sCurr);
			unsupportedtCurr=supportedCurrCheck(tCurr);
			System.out.println(sCurr+"\t"+unsupportedcCurr);
			if(unsupportedcCurr==false || unsupportedtCurr==false) {
				return "we only support BHD\t" + "CHF\t" + "EUR\t" + "GBP\t"+ "KWD\t"+"OMR\t"+"SGD as of now"; 
									}
			if(wrongDatePattern && unsupportedcCurr &&unsupportedcCurr) {
			currencyModel cModel = cDAO.findExchangeRates(actParamDate, sCurr);
			System.out.println("source\t" + cModel.getSourceCurrency() + "ConvRate\t" + cModel.getConversionRate()
					+ "Target\t" + cModel.getTargetCurrency());
			
			sRate = Float.valueOf(cModel.getConversionRate());
			
			
			currencyModel tModel = cDAO.findExchangeRates(actParamDate, tCurr);
			System.out.println("Target\t" + tModel.getSourceCurrency() + "ConvRate\t" + tModel.getConversionRate()
					+ "Target\t" + tModel.getTargetCurrency());

			tRate = Float.valueOf(tModel.getConversionRate());
			exRate = sRate / tRate;
			System.out.println(sCurr + "\twas\t" + exRate + "\ttimes\t" + tCurr + "\ton\t" + actParamDate);

			return "" + sCurr + "\twas\t" + exRate + "times\t" + tCurr + "on\t" + actParamDate;

		}
				
		else {
			return "Invalid Date format";
		}
	}
		return "";

}

	private boolean supportedCurrCheck(String sCurr) {
		System.out.println(sCurr);
		if(!(sCurr.equalsIgnoreCase("BHD")
				||sCurr.equalsIgnoreCase("CHF")
				||sCurr.equalsIgnoreCase("EUR")
				||sCurr.equalsIgnoreCase("GBP")
				||sCurr.equalsIgnoreCase("KWD")
				||sCurr.equalsIgnoreCase("OMR")
				||sCurr.equalsIgnoreCase("USD")
				||sCurr.equals("SGD")))
		{
			return false;
		}
		else return true;
		
	}
}