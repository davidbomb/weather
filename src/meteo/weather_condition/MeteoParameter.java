package meteo.weather_condition;



public class MeteoParameter {
	protected double value;
	
	
	public MeteoParameter(double value){
		value = Math.floor(value*10)/10;
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
}