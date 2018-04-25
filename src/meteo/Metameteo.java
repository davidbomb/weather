package meteo;

import meteo.weather_condition.Humidity;
import meteo.weather_condition.Temperature;
import meteo.weather_condition.WindSpeed;

//This class contains every information about the weather for a specific day
public class Metameteo extends Day {
	
	private Temperature temperature;
	private Humidity humidity;
	private WindSpeed wind;





public Metameteo(String date, Temperature temperature, Humidity humidity, WindSpeed wind) {
	super(date);
	this.temperature = temperature;
	this.humidity = humidity;
	this.wind = wind;
}



public Temperature getTemperature() {
	return temperature;
}

public void setTemperature(Temperature temperature) {
	this.temperature = temperature;
}

public Humidity getHumidity() {
	return humidity;
}

public void setHumidity(Humidity humidity) {
	this.humidity = humidity;
}

public WindSpeed getWind() {
	return wind;
}

public void setWind(WindSpeed wind) {
	this.wind = wind;
}




}