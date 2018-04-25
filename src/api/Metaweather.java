// CLASSE INTERMEDIAIRE
package api;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;

import meteo.Metameteo;
import meteo.weather_condition.Humidity;
import meteo.weather_condition.Temperature;
import meteo.weather_condition.WindSpeed;
import api.RequestInfos;




import java.util.List;

import org.json.*;

public class Metaweather {

	
	
	public static void FillMetaweather(List <Metameteo> meta_bulletin, int blt_size, String town) throws MalformedURLException, IOException{
		
		//Request 1
		JSONArray root = HttpRequest1(town);
		
		JSONObject tab = root.getJSONObject(0);
        int id = tab.getInt("woeid");			 
	
        //Request 2
        JSONArray full_weather = HttpRequest2(id);
	
        for(int i = 0; i < blt_size; i++){ 
        	JSONObject day = full_weather.getJSONObject(i);
        	Double t = day.getDouble("the_temp");
        	Double w = day.getDouble("wind_speed");
        	
        	//Creation of the meteo parameters
        	Temperature temperature = new Temperature(t);
        	temperature.setUnity("°C");
        	Humidity humidity = new Humidity(day.getDouble("humidity"));
        	WindSpeed wind_speed = new WindSpeed(w);
        	
        	//creation of the Metameteo object and adding to the bulletin
        	Metameteo meteo = new Metameteo("J+"+i,temperature, humidity, wind_speed);
        	meta_bulletin.add(meteo);
        }
	}
	
	
	
	public static JSONArray HttpRequest1(String town) throws IOException{
		// Makes the http request and extract the useful information
		String requestURL = "https://www.metaweather.com/api/location/search/?query=" + town;
		URL Request = new URL(requestURL);
		URLConnection connection = Request.openConnection();  
		connection.setDoOutput(true);  
			 
		//getting http infos
		int code = ((HttpURLConnection)connection).getResponseCode();
		String msg = ((HttpURLConnection)connection).getResponseMessage();
		URL url = ((HttpURLConnection)connection).getURL();
		String date = RequestInfos.getCurrentDate();
		//Saving the Request infos
		RequestInfos infos = new RequestInfos(date, code, msg, url);
		infos.dispInfos();
			
			 
		JSONTokener tokener = new JSONTokener(Request.openStream());
		JSONArray root = new JSONArray(tokener);
		return root;
	}
		




	public static JSONArray HttpRequest2(int id) throws IOException{
		// Makes the http 2nd request and extract the useful information
		String requestURL2 = "https://www.metaweather.com/api/location/" + id;
		URL Request2 = new URL(requestURL2);
		URLConnection connection2 = Request2.openConnection();  
		connection2.setDoOutput(true);  
			
		//getting http infos
		int code = ((HttpURLConnection)connection2).getResponseCode();
		String msg = ((HttpURLConnection)connection2).getResponseMessage();
		URL url = ((HttpURLConnection)connection2).getURL();
		String date = RequestInfos.getCurrentDate();
		RequestInfos infos = new RequestInfos(date, code, msg, url);
			
		infos.dispInfos();
			
		JSONTokener tokener2 = new JSONTokener(Request2.openStream());
		JSONObject root2 = new JSONObject(tokener2);
		
		JSONArray full_weather = root2.getJSONArray("consolidated_weather");
		return full_weather;
	}
}	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	