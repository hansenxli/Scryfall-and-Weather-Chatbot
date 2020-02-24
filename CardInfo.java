/**
 * @author Hansen Li
 *
 * @date Jul 11, 2019
 */
public class CardInfo {
	
	// declares variables to parse from JSON
	private String name;
	private String cost;
	private String type;
	private String oracleText;
	private String url;
	
	// constructor
	public CardInfo(String name, String cost, String type, String oracleText, String url) {
		setName(name);
		setCost(cost);
		setType(type);
		setOracle(oracleText);
		setURL(url);
	}
	
	// setters, no need for getters
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCost(String cost) {
		this.cost = cost;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	// takes out newline characters as IRC chat doesn't recognize them
	public void setOracle(String oracleText) {
		this.oracleText = oracleText.replace('\n', ' ');
	}
	
	public void setURL(String url) {
		this.url = url;
	}
	

	// converts parsed information into string for message output
	// needs multiple functions as IRC chat doesn't recognize newline character
	public String toNameString() {
		return String.format("Name: %s", name);
	}
	
	public String toCostString() {
		return String.format("Cost: %s", cost);
	}
	
	public String toTypeString() {
		return String.format("Type: %s", type);
	}
	
	public String toOracleString() {
		return String.format("Oracle Text: %s", oracleText);
	}
	
	public String toURLString() {
		return String.format("Direct URL: %s", url);
	}
	
}