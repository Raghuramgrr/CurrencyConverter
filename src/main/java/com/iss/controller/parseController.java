package com.iss.controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;

import com.iss.model.currencyModel;
import com.iss.DAO.currencyDAO;
import com.iss.DAO.parseDAO;
import com.iss.DAOimpl.currencyDAOImpl;
@RestController
public class parseController {
 public static List < currencyModel > listConverter = new ArrayList < currencyModel > ();

 ApplicationContext context =
  new ClassPathXmlApplicationContext("database//DBModule.xml");
 parseDAO cDAO = (parseDAO) context.getBean("parseDAO");
 SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

 @RequestMapping(value = "/parse")
 public String parseFiles(@RequestParam("user_name")String user) {
	
	 cDAO.truncateTable();
  //File folder = new File("//resources//temp");
  ClassLoader loader = parseController.class.getClassLoader();
  File filePath = new File(loader.getResource("temp").getPath());
  System.out.println(filePath);
  File folder = new File(filePath.toString());
  File[] listofFiles = folder.listFiles();
  for (File file: listofFiles) {
   Date date = null;

   String FILENAME = file.getName();
   System.out.println("FileName" + FILENAME);
   BufferedReader br = null;
   FileReader fr = null;
   Matcher dateMatcher = Pattern.compile("(\\d{4})-(\\d{1,2})-(\\d{1,2})").matcher(FILENAME);


   while (dateMatcher.find()) {
    String str_date = dateMatcher.group();
    System.out.println(str_date);
    try {
     date = formatDate.parse(str_date);
    } catch (ParseException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
    }
   }

   try {

    fr = new FileReader(filePath.toString() + "//" + FILENAME);
    br = new BufferedReader(fr);

    String sCurrentLine;
   
    while ((sCurrentLine = br.readLine()) != null) {
     String result = Pattern.compile("[(traded)|(at)|(times)+]").matcher(sCurrentLine).replaceAll("");
     String[] splited = result.replaceFirst("1", "").split("\\s+");

     for (int i = 0; i < splited.length - 1; i++) {
      currencyModel insertObj = new currencyModel(date, splited[i=++i].toLowerCase(), splited[i = ++i].toLowerCase(), splited[i=++i].toLowerCase());
      //listConverter.add(new currencyModel(date, splited[i = ++i], splited[i = ++i], "USD"));
      listConverter.add(insertObj);
      cDAO.insert(insertObj);
     }

    }
   } catch (Exception e) {
    e.printStackTrace();
    return "Issue in parse Controller, some issue in parsing \t"+user;
   }

  }
  System.out.println(listConverter);

  return "Parsing Done \t"+user;

 }

}