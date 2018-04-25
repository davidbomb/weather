package api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;



public class RequestInfos {
	
	
	private String date;
	private int responseCode;
	private String comment;
	private URL url;
	
	
	public RequestInfos(String date, int responseCode,String comment, URL url) {
		this.date = date;
		this.comment = comment;
		this.responseCode = responseCode;
		this.url = url;
	}
	
	
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public URL getURL() {
		return url;
	}
	public void setURL(URL url) {
		this.url = url;
	}
	
	//Print a String in a "requete.log" file, creating it if not already existing
	public static void printFile(String txt, boolean append) throws IOException{
		 File file = new File ("requetes.log");
		 FileWriter fileWriter = new FileWriter(file, append);
		 fileWriter.write(txt);
		 fileWriter.close();       
	}
	
	//Display the request infos in the requete.log file
	public void dispInfos() throws IOException{
		printFile(date, true);
		printFile(" ", true);
		printFile("- [" + responseCode + " "+ comment+"]", true);
		printFile("\n", true);
		printFile(url.toString(), true);
		printFile("\n", true);
		
		
	}
	// To fill the date field
	public static String getCurrentDate(){
		
		SimpleDateFormat formatter = new SimpleDateFormat ("dd-MM-yyyy");
		Date currentTime_1 = new Date();  
		String dateString = formatter.format(currentTime_1);
	  
		SimpleDateFormat formatter2 = new SimpleDateFormat ("HH:mm:ss");
		Date currentTime_12 = new Date();
		String dateString2 = formatter2.format(currentTime_12);
		
		return dateString + " " + dateString2;
	}
		
}
	
