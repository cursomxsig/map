package mx.org.inegi.geo.map.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author femat
 * 
 */
public class StreetCrossingParser {

	private String street1;
	private String street2;

	public StreetCrossingParser(String value2search, String token) {
		value2search = value2search.trim();
		boolean valid = false;
		Pattern regex = Pattern.compile("((?:[\\w ],?)+)" + token
				+ "((?:[\\w ],?)+)", Pattern.DOTALL);
		Matcher regexMatcher = regex.matcher(value2search);
		valid = regexMatcher.matches();
		if (valid) {
			String street1 = regexMatcher.group(1).trim();
			String street2 = regexMatcher.group(2).trim();
			String[] street1data = street1.split(",");
			String[] street2data = street1.split(",");
			if (street1data.length > 1) {
				this.street1 = street1;
			}
			if (street2data.length > 1) {
				this.street2 = street2;
			}
			if (street2data.length == 1 && street1data.length > 1) {
				this.street2 = street2.concat("," + street1data[1]);
			} else if (street1data.length == 1 && street2data.length > 1) {
				this.street1 = street1 + "," + street2data[1];
			}
		}
	}

	/**
	 * @return the street1
	 */
	public String getStreet1() {
		return street1;
	}

	/**
	 * @param street1
	 *            the street1 to set
	 */
	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	/**
	 * @return the street2
	 */
	public String getStreet2() {
		return street2;
	}

	/**
	 * @param street2
	 *            the street2 to set
	 */
	public void setStreet2(String street2) {
		this.street2 = street2;
	}

}
