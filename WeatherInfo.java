/**
 * @author Hansen Li
 *
 * @date Jul 11, 2019
 */
public class WeatherInfo {
	
	// declares variables to parse from JSON
	private double currTemp;
	private double highTemp;
	private double lowTemp;
	private String weather;
	private String location;
	
	// constructor
	public WeatherInfo(double currTemp, double highTemp, double lowTemp, String weather, String location) {
		setTemp(currTemp);
		setHigh(highTemp);
		setLow(lowTemp);
		setWeather(weather);
		setLocation(location);
	}
	
	// temperatures done in Celsius because the rest of the world isn't crazy
	
	private double kelvinToCelsius(double kelvin) {
		return (kelvin - 273.15);
	}
	
	// setters, no need for getters
	
    public void setHigh(double high) {
        this.highTemp = kelvinToCelsius(high);
    }

    public void setLow(double low) {
        this.lowTemp = kelvinToCelsius(low);
    }

    public void setWeather(String weather) {
        this.weather = weather.toLowerCase();
    }

    public void setTemp(double temp) {
        this.currTemp = kelvinToCelsius(temp);
    }

    public void setLocation(String location) {
        this.location = location;
    }
	
	// converts parsed information into string for message output
	public String toString() {
		return String.format("Results found! The current weather in %s is %s. The temperature currently is %.1f°C, and will have a high of %.1f°C and a low of %.1f°C.", location, weather, currTemp, highTemp, lowTemp);
	}
	
}
