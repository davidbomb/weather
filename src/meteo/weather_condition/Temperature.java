package meteo.weather_condition;


public class Temperature extends MeteoParameter {
	private String unity;
	
	

	public Temperature(double value){
		super(value);
	}
	
	public void displayT(){
		System.out.print(value + unity);
	}
	public double getValue(){
		return this.value;
	}
	public void setValue(double value){
		this.value = value;
	}
	public String getUnity() {
		return unity;
	}

	public void setUnity(String unity) {
		this.unity = unity;
	}
}