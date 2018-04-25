package api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import meteo.Metameteo;
import meteo.weather_condition.Humidity;
import meteo.weather_condition.Temperature;
import meteo.weather_condition.WindSpeed;
import api.RequestInfos;

import java.util.List;

import org.json.*;


public class PrevMeteo { // doit remplir le prev_bulletin

	public static void FillPrevMeteo(List <Metameteo> prev_bulletin, int blt_size, String town) throws IOException{
	 
		JSONObject root = HttpRequest(town);
	 
	 for(int i = 0; i < blt_size-1; i++){  // J+5 doesn't exists for this api
		 //blt_size always equal 6
		 JSONObject prev_weather2 = root.getJSONObject("fcst_day_" + i);
		 JSONObject hourly_data = prev_weather2.getJSONObject("hourly_data");
		 JSONObject noon_info = hourly_data.getJSONObject("12H00");
		 
		 double t_min = prev_weather2.getDouble("tmin");
		 double t_max = prev_weather2.getDouble("tmax");
		 
		 
		 double hum = noon_info.getDouble("RH2m");
		 double wnd = noon_info.getDouble("WNDSPD10m");		 
		 			
		 //Creation of the meteo parameters
		 Temperature t_moy = new Temperature((t_min + t_max)/2);
		 t_moy.setUnity("°C");
		 Humidity humidity = new Humidity(hum);
		 WindSpeed wind_speed = new WindSpeed(wnd);
		 
		 //Creation of the Metameteo object and adding to the bulletin
		 Metameteo meteo = new Metameteo("J+"+i,t_moy,humidity, wind_speed);
		 prev_bulletin.add(meteo);
		 
	 }
	}
	

	public static JSONObject HttpRequest(String town) throws IOException{
		// Makes the http request and extract the useful information
		String requestURL = "https://www.prevision-meteo.ch/services/json/" + town;
		URL Request = new URL(requestURL);
		URLConnection connection = Request.openConnection();  
		connection.setDoOutput(true);  
		
		
		int code = ((HttpURLConnection)connection).getResponseCode();
		String msg = ((HttpURLConnection)connection).getResponseMessage();
		URL url = ((HttpURLConnection)connection).getURL();
		String date = RequestInfos.getCurrentDate();
		
		//Saving of the Request infos
		RequestInfos infos = new RequestInfos(date, code, msg, url);
		infos.dispInfos();

   	    JSONTokener tokener = new JSONTokener(Request.openStream());
		JSONObject root = new JSONObject(tokener);
		return root;
	}


	
}
