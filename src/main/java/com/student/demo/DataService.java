package com.student.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class DataService {
	
	
	//private String usurl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv"; 
	private String url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
	private List<DataModel> covidData = new ArrayList<>();
	
	@PostConstruct
	@Scheduled(cron = "0 1 1 * * ?")
	public void fetechData() throws Exception { 
		
		StringBuffer stringBuffer = new StringBuffer();
        
		URL uri = new URL(url);
		
		 HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
		 

         InputStream inputStream = connection.getInputStream();


         BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

         int i;
         List<DataModel> updatedCovidData = new ArrayList<DataModel>();

         while ((i = reader.read()) != -1) {
             char c = (char) i;
             if(c == '\n') {
                 stringBuffer.append("\n");
             }else {
                 stringBuffer.append(String.valueOf(c));
             }
         }
         StringReader csvData =  new StringReader(stringBuffer.toString());
         Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvData);
         for (CSVRecord record : records) {
        	 DataModel dm = new DataModel();
        	 dm.setCountry(record.get("Country/Region"));
        	 dm.setState(record.get("Province/State"));
        	 dm.setTotalDeath(record.get(record.size()-1));
        	 updatedCovidData.add(dm);
         }
         this.covidData = updatedCovidData;
         
         reader.close();
	}

	public List<DataModel> getCovidData() {
		return covidData;
	}
	
}
