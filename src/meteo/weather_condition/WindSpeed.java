package meteo.weather_condition;


public class WindSpeed extends MeteoParameter {

	
		private static String unity = " ";
		
		
		
		public WindSpeed(double value){
			super(value);
			
			
		}
		public void displayW(){
			System.out.print(value + unity);
		}
	
		public double getValue(){
			return this.value;
		}
}