package localhost.javaSailsRestDemo.employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 
 * Sample class that makes POSTS a JSON object to a SAIL API named employee
 * 
 * @author Mary Ches
 * @since 2016-09-07
 *
 */



public class Create 
{

	/**
	 * The URL of the API we want to connect to
	 */
	
	protected static String endPoint = "http://localhost:1337/employee/";
	
	/**
	 * The character set to use when encoding URL parameters
	 */
	
	protected static String charset = "UTF-8";
	
	public static void main(String[] args) 
	{
		try
		{
			//The first name of the new employee
			String firstName = "Michael";
			
			//The last name of the new employee
			String lastName = "Deppisch";
			
			//Email address for the new employee (someperson@email.com)
			String email = "michaeldeppisch@gmail.com";
			
			//Home phone number for new employee (###-###-#### | ###.###.####)
			String homePhone = "410-247-2094";
			
			//Cell phone number for new employee (###-###-#### | ###.###.####)
			String cellPhone = "410-428-2713";
			
			//Password for new employee (must contain 8 alphanumeric at least 1 lowercase 1 uppercase 1 number)
			String password = "M1k3D3pp";
			
			//Status of new employee (1 = active 0 = non-active)
			String active = "1";
			
			//creates the URL parameters as a string encoding them with the defined charset
			String queryString = String.format
					("firstName=%s&lastName=%s&email=%s&homePhone=%s&cellPhone=%s&password=%s&active=%s",
					URLEncoder.encode(firstName, charset),
					URLEncoder.encode(lastName, charset),
					URLEncoder.encode(email, charset),
					URLEncoder.encode(homePhone, charset),
					URLEncoder.encode(cellPhone, charset),
					URLEncoder.encode(password, charset),
					URLEncoder.encode(active, charset));
			
			//creates a new URL out of the endPoint
			URL newEmployee = new URL(endPoint + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) newEmployee.openConnection();
			connection.setRequestMethod("POST");
			
			if (connection.getResponseCode() != 201)
			{
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseMessage());
				
			}
			
			//read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			
			//loop of buffer line by line until it returns null, meaning there are no more lines
			while (br.readLine() !=null)
			{
				//print out each line to the screen
				System.out.println(br.readLine());
			}
			
			//close connection to API
			connection.disconnect();
		
		}//end try
		
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}//end main
	
}//end class
