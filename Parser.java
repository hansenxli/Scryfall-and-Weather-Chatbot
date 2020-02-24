/**
 * @author Hansen Li
 *
 * @date Jul 11, 2019
 */

// imports packages needed
import java.io.*;
import java.net.*;
import com.google.gson.*;


public class Parser {
	
	// my openweathermap key
	private static final String key = "191a41dc5a53b24e745fb73f9a6e7b94";
	
	// weather api call's endpoint url
	private static final String weatherEndpointURL = "http://api.openweathermap.org/data/2.5/weather?q=%s&APPID=%s";
	
	// scryfall api call's endpoint url
	private static final String scryfallEndpointURL = "https://api.scryfall.com/cards/named?fuzzy=%s";


	// method to call weather API with location
	public static WeatherInfo getWeather(String location) {

		// try catch block in case API call fails
		try {
			
			// forms complete url for API call
			URL weatherURL = new URL(String.format(weatherEndpointURL, location, key));
			
			HttpURLConnection weatherHttp = (HttpURLConnection) weatherURL.openConnection();
			
			// sets request method for httpurlconnection. not much more to say here
			weatherHttp.setRequestMethod("GET");
			
			// had cs mentor help for URL combiner lines
			BufferedReader reader = new BufferedReader(new InputStreamReader(weatherHttp.getInputStream()));
			StringBuilder result = new StringBuilder();
			
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
			reader.close();
			
			
			return parseWeatherJSON(result.toString());
		
		} 
		
		catch (Exception ex) {
			System.out.println("Failed to get weather info :c");
		}
		
		return null;
	}
	
	// method to call scryfall API with card name
	public static CardInfo getCard(String name) {
		try {
			URL scryfallURL = new URL(String.format(scryfallEndpointURL, name));
			
			HttpURLConnection scryfallHttp = (HttpURLConnection) scryfallURL.openConnection();
			
			scryfallHttp.setRequestMethod("GET");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(scryfallHttp.getInputStream()));
			StringBuilder result = new StringBuilder();
			
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
			reader.close();
			
			return parseScryfallJSON(result.toString());
		}
		catch (Exception e) {
			System.out.println("Failed to get card info :c");
		}
		
		return null;
	}
	
	
	// parses json from API call and returns as WeatherInfo class
	private static WeatherInfo parseWeatherJSON(String jsonInfo) {
		
		// had CS mentor help me with this line 
		JsonObject object = new JsonParser().parse(jsonInfo).getAsJsonObject();
		
		
		JsonObject main = object.getAsJsonObject("main");
		
		// gets the three temperature values
		double temp = main.get("temp").getAsDouble();
		double temp_max = main.get("temp_max").getAsDouble();
		double temp_min = main.get("temp_min").getAsDouble();
	
		// gets the weather main description as string
		// multiple lines to break down json parsing
		// probably possible to do in fewer lines
		JsonArray weatherArray = object.getAsJsonArray("weather");
		JsonObject weatherObject = weatherArray.get(0).getAsJsonObject();
		String weatherMain = weatherObject.get("main").getAsString();
		
		String location = object.get("name").getAsString();
		
		// constructs and returns WeatherInfo
		return new WeatherInfo(temp, temp_max, temp_min, weatherMain, location);
	}
	
	// parses json from API call and returns as CardInfo class
	private static CardInfo parseScryfallJSON(String jsonInfo) {
		
		JsonObject object = new JsonParser().parse(jsonInfo).getAsJsonObject();		
		String name = object.get("name").getAsString();
		String cost = object.get("mana_cost").getAsString();
		String type = object.get("type_line").getAsString();
		String oracle = object.get("oracle_text").getAsString();
		
		JsonObject images = object.getAsJsonObject("image_uris");
		
		String directURL = images.get("normal").getAsString();
		
		
		return new CardInfo(name, cost, type, oracle, directURL);
	}
	
}
