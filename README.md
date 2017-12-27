# CurrencyConverter
Currency Converter Slack-Bot APP using Spring , MYSQL backend , EC2 Deployment.


## Getting Started
The converter works only on 8 Currencies - we only support BHD + CHF + EUR + GBP+ KWD+ OMR + SGD + USD
Install Eclipse spring tool kit 
Use Maven
PostMan

## JAVA-DOC : 
Java - Doc Is created  and it is hosted as static site - Navigate to below link to get the Detailed Info on APIs 

Click to view JavaDoc [s3-SiteLink](https://s3.amazonaws.com/currencyconverterjavadoc/JavaDoc/index.html)



## Join jbotworkspace in slack  - Invitations sent already 

```
  1) /currencyparse   - This parses all the dump files and stores in mysql database

  2) /currencydate 2017-01-01  - This gives all the exchange rates on the specified date

  3) /currencyrange 2017-01-01 2017-01-02 - This gives all the exchange rates in specified date range

  4) /currencyexchange 2017-01-01 CHF EUR  - Gives exchangerate of CHF to EUR on specified dates 
```


### If you are not available in slack 

http://ec2-**-**-98-69.compute-1.amazonaws.com:8080/converter/parse/?user_name='ram'


http://ec2-**-**-98-69.compute-1.amazonaws.com:8080/converter/range?text=2017-01-01 2017-01-03


http://ec2-**-**-98-69.compute-1.amazonaws.com:8080/converter/date?text=2017-01-01


http://ec2-**-**-98-69.compute-1.amazonaws.com:8080/converter/exchange?text=2017-01-01 SGD EUR

### Testing
Unit Tests for the Business Functions 

POSTMAN - collections - sent up to 200 requests to see the stability of the Application

Integration Testing - There was no need for Integration testing - slack takes care of it - but in future in needed we can use POSTMAN collections run triggered by Jenkins 


### Other things : 

Try giving different dates , invalid dates ,  Different Date format - feel free to report issue

## Deployment 
Create EC2 instance to your deployment setup in 2 mins 	

	1) sudo yum update
	2)  java -version
	3)  sudo yum install java-1.8.0
    4)  yum install tomcat
    5)  sudo yum install tomcat
    6)  sudo yum install apache
    7)  sudo yum install tomcat7
    8)  sudo yum install tomcat7-webapps
    9)  sudo yum install mysql-server
  	10) sudo service mysqld start
    11)  sudo service tomcat7 start
    12)  sudo service mysqld stop
    
   ## Author
   [RaghuramGnanasekaran](http://www.raghuramg.com)
   
   ## Acknowledgements 
   Stackoverflow, 
   Uncle Google,
   AWS FREE TIER
   
    
