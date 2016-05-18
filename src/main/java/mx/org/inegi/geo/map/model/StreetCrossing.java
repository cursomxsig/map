/**
 * 
 */
package mx.org.inegi.geo.map.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author femat
 *
 */
public class StreetCrossing {

	@NotEmpty
	private String project;

	@NotEmpty
	private String criteria;

	private String street1;

	private String street2;

	private String city;

	private final String token = "--";

	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return the criteria
	 */
	public String getCriteria() {
		return criteria;
	}

	/**
	 * @param criteria
	 *            the criteria to set
	 */
	public void setCriteria(String criteria) {
		this.criteria = criteria.trim();
		boolean valid = false;
		Pattern regex = Pattern.compile("((?:[\\w ],?)+)" + token
				+ "((?:[\\w ],?)+)", Pattern.DOTALL);
		Matcher regexMatcher = regex.matcher(criteria);
		valid = regexMatcher.matches();
		if (valid) {
			String street1 = regexMatcher.group(1).trim();
			String street2 = regexMatcher.group(2).trim();
			String[] street1data = street1.split(",");
			String[] street2data = street2.split(",");
			if (street1data.length > 1) {
				this.street1 = street1data[0].trim();
				this.city = street1data[1].trim();
			}
			if (street2data.length > 1) {
				this.street2 = street2data[0].trim();
				if (city == null) {
					this.city = street2data[1].trim();
				}
			}
			if (street2data.length == 1 && street1data.length > 1) {
				this.street2 = street2;
				this.city = street1data[1].trim();
			} else if (street1data.length == 1 && street2data.length > 1) {
				this.street1 = street1;
				if (city == null) {
					this.city = street2data[1].trim();
				}
			} else {
				this.street1 = street1;
				this.street2 = street2;
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
	 * @return the street2
	 */
	public String getStreet2() {
		return street2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

}
