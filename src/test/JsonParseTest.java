package test;

import java.io.*;
import java.util.*;

import api.Metaweather;
import api.PrevMeteo;
import api.YahooMeteo;
import meteo.Metameteo;

import org.json.*;

public class JsonParseTest {
	
	private static List<Metameteo> meta_bulletin = new ArrayList<Metameteo>();
	private static List<Metameteo> prev_bulletin = new ArrayList<Metameteo>();
	private static List<Metameteo> yahoo_bulletin = new ArrayList<Metameteo>();
	private static int blt_size = 6;
	
	
	 public static void main(String[] args) {
		 
		 //Definition of the program arguments
		 String town;
		 int h = 0;
		 int w = 0;
		 int size = 0;
		 int far = 0;
		 String filename = null;
		 int printFileAppend = 0;
		 int printFileErase = 0;
		 
		 int argsize = args.length;
		 
		 town = args[0];
		 int i;
		 
		 //Retrieval of the command line arguments
		 
		 try{
			 
		 for(i = 0; i < argsize; i++){
			 if (args[i].equals("-h")){
				 h = 1;
			 }
			 if (args[i].equals("-w")){
				 w = 1; 
			 }
			 if (args[i].equals("-j")){
				 size = Integer.parseInt(args[i+1]);
				 if(size >= 5){
					 size = 5;
				 }
			 }
			 if (args[i].equals("-f")){
				 far = 1;	 
			 }
			 if (args[i].equals("-o")){
				 filename = args[i+1];	
				 printFileErase = 1;
			 }
			 if (args[i].equals("-a")){
				 filename = args[i+1];	
				 printFileAppend = 1;
			 }
			 
		 }
		 
		 
		 
		
		 
			 
			 
			 //Metaweather API
			 Metaweather.FillMetaweather(meta_bulletin, blt_size, town);
			 
    					
			 //Previsions-meteo API	
			 PrevMeteo.FillPrevMeteo(prev_bulletin, blt_size, town);
		
			 //Yahoo API
			 YahooMeteo.FillYahooMeteo(yahoo_bulletin, blt_size, town);
		
			 
			 System.out.println();
			 
			 //Prototype: 
			 //shaping(meta_bulletin, prev_bulletin, yahoo_bulletin, int h, int w, int size, int far)
			 Shape.shaping(meta_bulletin, prev_bulletin, yahoo_bulletin,h,w,size,far);
			 
			 //To write the result in a file 
			 if (printFileErase == 1){ 
				 PrintStream ps =  new PrintStream(filename);
				 System.setOut(ps);
				 Shape.shaping(meta_bulletin, prev_bulletin, yahoo_bulletin, h, w, size, far);
			 }
			 //To write the result in a file without erasing previous content (not functioning)
			 if (printFileAppend == 1){ // a modifier
				 FileWriter fileWriter = new FileWriter(filename, true);
				 PrintStream ps =  new PrintStream(filename);
				 System.setOut(ps);
				 Shape.shaping(meta_bulletin, prev_bulletin, yahoo_bulletin, h, w, size, far);
			 }
    					 
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
			 System.out.println("URL not valid");
		 } catch (IOException e) {
			 e.printStackTrace();
		 } catch (IndexOutOfBoundsException e) {
			 e.printStackTrace();
		 } catch (NumberFormatException e) {
			 System.out.println("You used the flag -j without precising the number of days");
			 System.out.println("Please write -j <number>");
		 } catch (JSONException e) {
			 System.out.println("Weather is not available for the city: " + town);
		 }
		
		
	}	
	 
	 
}
	
	

