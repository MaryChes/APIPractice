package localhost.googlemaps.directions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 
 * Sample class that makes simple request to Google Maps Directions
 * Extended RestRequestA to avoid tolls
 * 
 * @author Mary Ches
 * @since 2016-09-06
 *
 */


public class RestRequestB 
{
	/**
	 * The URL of the API we want to connect to
	 */
	
	protected static String endPoint = "https://maps.googleapis.com/maps/api/directions/";
	
	/**
	 * The character set to use when encoding URL parameters
	 */
	
	protected static String charset = "UTF-8";
	
	/**
	 * API key used for making requests to API
	 */
	
	protected static String key = "AIzaSyAI-b0OwKFzq2tHeLht0JiYzgN2kF6k_l8";
	
	
	
	public static void main(String[] args) 
	{
		try {
			
			//The origin or starting point for directions (my house)
			String origin = "Catonsville MD";
			
			//The destination or end point for directions (my parent's house)
			String destination = "Overlea MD";
			
			//Return route that avoids tolls
			String avoid = "tolls";
			
			//The return type of the response xml | json
			String returnType = "json";
			
			//creates the URL parameters as a string encoding them with the defined charset
			String queryString = String.format("origin=%s&destination=%s&key=%s&avoid=%s", 
					URLEncoder.encode(origin, charset),
					URLEncoder.encode(destination, charset), 
					URLEncoder.encode(key, charset),
					URLEncoder.encode(avoid, charset));
			
			//creates a new URL out of the endPoint, returnType, and queryString
			URL googleDirections = new URL(endPoint + returnType + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) googleDirections.openConnection();
			connection.setRequestMethod("GET");
			
			if (connection.getResponseCode() != 200) 
			{
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}
			
			//read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			
			//loop of buffer line by line until it returns null, meaning there are no more lines
			while (br.readLine() != null) 
			{
				//print out each line to the screen
				System.out.println(br.readLine());
			}
			
			//close connection to API
			connection.disconnect();
			
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}	
		
	}//end main method
	
}//end class
