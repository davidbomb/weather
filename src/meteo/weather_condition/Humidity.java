package meteo.weather_condition;


public class Humidity extends MeteoParameter {

	
		private static String unity = "%";
		
		
		
		public Humidity(double value){
			super(value);
			
			
		}
		public void displayH(){
			System.out.print(value + unity);
		}
		
		public double getValue(){
			return this.value;
		}
	
}