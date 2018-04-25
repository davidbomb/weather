package api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import meteo.Metameteo;
import meteo.weather_condition.Humidity;
import meteo.weather_condition.Temperature;
import meteo.weather_condition.WindSpeed;

import java.util.List;

import org.json.*;

public class YahooMeteo {
	
	public static void FillYahooMeteo(List <Metameteo> yahoo_bulletin, int blt_size, String town) throws IOException{
		//Parse the JSONObject to put every weather infos into the bulletin
		
		JSONObject root = HttpRequest(town);
		
		JSONObject yahoo_weather = root.getJSONObject("query");
		JSONObject yahoo_atmosphere = yahoo_weather.getJSONObject("results").getJSONObject("channel").getJSONObject("atmosphere");
		JSONObject yahoo_wind = yahoo_weather.getJSONObject("results").getJSONObject("channel").getJSONObject("wind");
		String hum = yahoo_atmosphere.getString("humidity");
		double h = Double.parseDouble(hum);
		Humidity humidity= new Humidity(h);

		String sp = yahoo_wind.getString("speed");
		double speed = Double.parseDouble(sp);
		WindSpeed wind_speed = new WindSpeed(speed);
		 
		JSONObject yahoo_condition = yahoo_weather.getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONObject("condition");
		double t = Double.parseDouble (yahoo_condition.getString("temp"));
		Temperature temp = new Temperature(t);
		temp.setUnity("°F");
		Metameteo meteo = new Metameteo("J+0",temp, humidity, wind_speed);
		yahoo_bulletin.add(meteo);

		// the first JSONObject is parsed outside of the loop because the result of this api varies between the different days
		for(int i = 1; i < blt_size; i++){

	       JSONArray yahoo_condition2 = yahoo_weather.getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast");
		   JSONObject yahoo_weather2 = yahoo_condition2.getJSONObject(i);
		   String t_m = yahoo_weather2.getString("low");
		   double t_min = Double.parseDouble(t_m);
		   String t_ma = yahoo_weather2.getString("high");
		   double t_max = Double.parseDouble(t_ma);
		   
		   //Creation of the meteo parameters
		   Temperature t_moy = new Temperature((t_min + t_max)/2);
		   t_moy.setUnity("°F");
		   Humidity humidity2 = new Humidity(-1);
		   WindSpeed wind_speed2 = new WindSpeed(-1);
		   
		   //creation of the Metameteo object and adding to the bulletin
		   Metameteo meteo2 = new Metameteo("J+"+i,t_moy,humidity2, wind_speed2);
		   yahoo_bulletin.add(meteo2);

		 }
	}
	
	
	public static JSONObject HttpRequest(String town) throws IOException{
		// Makes the http request and extract the useful information
		 String requestURL = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22" + town + "%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		 URL Request = new URL(requestURL);
		 URLConnection connection = Request.openConnection();
		 connection.setDoOutput(true);
		 
		 int code = ((HttpURLConnection)connection).getResponseCode();
		 String msg = ((HttpURLConnection)connection).getResponseMessage();
		 URL url = ((HttpURLConnection)connection).getURL();
		 String date = RequestInfos.getCurrentDate();
		 //Saving the request infos
		 RequestInfos infos = new RequestInfos(date, code, msg, url);
		 infos.dispInfos();

		 JSONTokener tokener = new JSONTokener(Request.openStream());
		 JSONObject root = new JSONObject(tokener);
		 return root;
	}
	
}
