package controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.currencyModel;

@RestController
public class parseController {
	public static List < currencyModel > listConverter = new ArrayList < currencyModel> ();
	
	SimpleDateFormat formatDate=new SimpleDateFormat("yyyy-MM-dd"); 
	
    @RequestMapping(
    		value = "/parse",
    		method = RequestMethod.POST )
    	public String parseFiles()  {
		File folder = new File(".\\temp");
		File[] listofFiles = folder.listFiles();

		for (File file: listofFiles) {
			Date date = null;

			String FILENAME = file.getName();
			BufferedReader br = null;
			FileReader fr = null;
			Matcher dateMatcher = Pattern.compile("(\\d{4})-(\\d{1,2})-(\\d{1,2})").matcher(FILENAME);

			
			while (dateMatcher.find()) {
				String str_date = dateMatcher.group();
				System.out.println(str_date);
				try {
					date =formatDate.parse(str_date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {

				fr = new FileReader(".\\temp\\" + FILENAME);
				br = new BufferedReader(fr);

				String sCurrentLine;

				while ((sCurrentLine = br.readLine()) != null) {
					String result = Pattern.compile("[(traded)|(at)|(times)+]").matcher(sCurrentLine).replaceAll("");
					String[] splited = result.replaceFirst("1", "").split("\\s+");

					for (int i = 0; i < splited.length - 1; i++) {
						listConverter.add(new currencyModel(date, splited[i = ++i], splited[i = ++i], "USD"));

					}

				}
			} catch(Exception e) {
				System.out.println("Parse Error " + e);
				return e.toString();
			}

		}
		System.out.println(listConverter);

		return "";

	}

}