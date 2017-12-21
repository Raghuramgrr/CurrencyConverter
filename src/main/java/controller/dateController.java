package controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.currencyModel;

@RestController
public class dateController {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	
	@RequestMapping("/date")
	public String getRatesByDate( @RequestParam("date")String param_date) {
		boolean wrongPattern =  isValidDate(param_date);
		if(wrongPattern) {
	    String date =param_date;
	    System.out.println(date);
	    System.out.print(parseController.listConverter);
		Iterator<currencyModel> itr = parseController.listConverter.iterator();
		while(itr.hasNext()) {
			currencyModel dateObj =itr.next();
			try {
				if(dateObj.getDate().equals(format.parse(date))) {
					System.out.println(dateObj.getSourceCurrency() +"\twas\t"+dateObj.getConversionRate()+"\ttimes\t"+dateObj.getTargetCurrency() );
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	    
		}
		else {
			System.out.println("Invalid date format ");
			return "Invalid date format";
		}
		return "";
	
		
	}

	public boolean isValidDate(String param_date) {
		try {
			System.out.println(param_date);
	          format.parse(param_date);
	          return true;
	     }
		 catch(ParseException e){
	          return false;
	     }
		
	}
	
}
