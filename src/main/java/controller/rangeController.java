package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.currencyModel;


@RestController
public class rangeController {
DateTimeFormatter format=DateTimeFormatter.ofPattern("yyyy-MM-dd");

dateController dateObj = new dateController();	
@RequestMapping("/range")

public String getRatesByDateRange(@RequestParam("sDate") String sParamdate,@RequestParam("eDate")String eParamDate) throws ParseException {
	boolean wrongStartPattern =dateObj.isValidDate(sParamdate);
	boolean wrongEndPattern = dateObj.isValidDate(eParamDate);
	
	if((wrongStartPattern && wrongEndPattern)) {
		LocalDate startdate =LocalDate.parse(sParamdate,format);
		LocalDate enddate =LocalDate.parse(eParamDate,format);
	    long daysDifference = ChronoUnit.DAYS.between(startdate, enddate);
	    
	    for(int i=1;i<daysDifference;i++){
	    	LocalDate date = startdate.plusDays(i);
	    	Iterator<currencyModel> itr = parseController.listConverter.iterator();
	    	while(itr.hasNext()) {
	    		currencyModel rangeObj=itr.next();
	    	
	    			if(rangeObj.getDate().equals(date)){
	    				System.out.println("\tRange\t"+rangeObj.getSourceCurrency() +"\twas\t"+rangeObj.getConversionRate()+"\ttimes\t"+rangeObj.getTargetCurrency() );
	    				
	    				
	    			}
	    		}	
	    		
	    	}
	    	
	    }
	
		
	
	else {
		System.out.println("Format is invalid");
		return "Invalid Date format";
	}
	return "";
	
}




}
