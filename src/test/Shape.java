package test;

import java.util.List;


import meteo.*;

public class Shape {
	//This class is used to create the display of the final result via the function shaping
	private static String no_value = " - ";
	
	
	
	
	public static double FarToDeg(double value){
		value = (value-32)/1.8;
		return  Math.floor(value*10)/10;
	}

	public static double DegToFar(double value){
		value = 1.8*value + 32;
		return  Math.floor(value*10)/10;
	}
	
	
	public static void interline(int size){ //+-------------+-----+-----+-----+-----+-----+-----+
		System.out.print("+-------------+");
		for(int i = 0; i <= size; i++){
			System.out.print("-------+");
		}
		System.out.println();
	}
	
	public static void shapeDays(int size){ // J+1 | J+2 | J+3 | J+4 |J+5 |
		System.out.print("|             |");
		for(int i = 0; i <= size; i++){
			
			System.out.print("  J+"+ i +"  |");
		}
		System.out.println();
	}
	
	
	public static void shapeT(List<Metameteo> blt, int size, int far){  
	//Shape the Temperature line for any bulletin given a size
		for(int i = 0; i <= size; i++){
			
					if(i >= blt.size()){
						while(i <= size){
							System.out.print("  ");
							System.out.print(no_value);
							System.out.print("  |");
							i++;
						}
						break;
					}
					if(far == 1 && blt.get(i).getTemperature().getUnity() == "°C"){
						 //converts degrees to farhenheit
							blt.get(i).getTemperature().setValue(DegToFar(blt.get(i).getTemperature().getValue()));
							blt.get(i).getTemperature().setUnity("°F");
					}
					if(far == 0 && blt.get(i).getTemperature().getUnity() == "°F"){
						 //converts farhenheit to degrees
							blt.get(i).getTemperature().setValue(FarToDeg(blt.get(i).getTemperature().getValue()));
							blt.get(i).getTemperature().setUnity("°C");
					}
					
					if(blt.get(i).getTemperature().getValue()<100){
						System.out.print(" ");
						
					}
					if(blt.get(i).getTemperature().getValue()<10){
						System.out.print(" ");
						
					}
					blt.get(i).getTemperature().displayT();
					System.out.print("|");
				}
				System.out.println();
	}
	
	
	
	
	public static void shapeH(List<Metameteo> blt, int size, int h){  
		//Shape the Humidity line for any bulletin given a size
		if(h==1){
			System.out.print("|             |");
			for(int i = 0; i <= size; i++){
				
				if(i >= blt.size() || blt.get(i).getHumidity().getValue() == -1){
					while(i <= size){
						System.out.print("  ");
						System.out.print(no_value);
						System.out.print("  |");
						i++;
					}
					break;
				}
				if(blt.get(i).getHumidity().getValue()<100){
					System.out.print(" ");
				}
				if(blt.get(i).getHumidity().getValue()<10){
					System.out.print(" ");
				
				}
				blt.get(i).getHumidity().displayH();
				System.out.print(" |");
			}
			System.out.println();
		}
	}
	
	
	
	
	public static void shapeW(List<Metameteo> blt, int size, int w){  
		//Shape the WindSpeed line for any bulletin given a size
		if(w==1){
			System.out.print("|             |");
			for(int i = 0; i <= size; i++){
				
				if(i >= blt.size() || blt.get(i).getWind().getValue() == -1){
					while(i <= size){
						System.out.print("  ");
						System.out.print(no_value);
						System.out.print("  |");
						i++;
					}
					break;
				}
					
				if(blt.get(i).getWind().getValue()<100){
					System.out.print(" ");
						
				}
				if(blt.get(i).getWind().getValue()<10){
					System.out.print(" ");
						
				}
				blt.get(i).getWind().displayW();
				System.out.print(" |");
			}
			System.out.println();
		}
		
	}
	
	
	
	
	
	
	
	
	
	public static void shaping(List<Metameteo> meta_bulletin, List<Metameteo> prev_bulletin, List<Metameteo> yahoo_bulletin, int h, int w, int size, int far){
		
		interline(size);
		// J
		shapeDays(size);
		
		interline(size);
		
		
		System.out.print("| Metaweather |");
		// T
		shapeT(meta_bulletin,size,far);
		// H
		shapeH(meta_bulletin,size,h);
		// W
		shapeW(meta_bulletin,size,w);

		interline(size);
		
		
		System.out.print("| P-Meteo     |");
		// T
		shapeT(prev_bulletin,size,far);
		// H
		shapeH(prev_bulletin,size,h);
		// W
		shapeW(prev_bulletin,size,w);
		
		interline(size);
		
		
		System.out.print("| Yahoo!W     |");
		// T
		shapeT(yahoo_bulletin,size,far);
		// H
		shapeH(yahoo_bulletin,size,h);
		// W
		shapeW(yahoo_bulletin,size,w);
		
		interline(size);
		
	}
	
	
	
}