package mx.org.inegi.geo.map.xml.model;

import java.util.Collections;
import java.util.List;

import mx.org.inegi.geo.map.util.comparator.Comparators;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author yan.luevano
 */
@JsonAutoDetect(getterVisibility = Visibility.NONE, fieldVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
public class Field implements Cloneable {

	private String name;
	@JsonProperty
	private String alias;
	@JsonProperty
	private String value;

	private List<FieldFunction> functions;
	private boolean hasFunctions;
	private boolean searchDisplay;
	private boolean queryDisplay;
	private boolean hasLink;
	private String link;
	private boolean identify;

	public Field(String name, String alias) {
		this.name = name;
		this.alias = alias;
		searchDisplay = true;
		queryDisplay = true;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	@Override
	public boolean equals(Object obj) {
		boolean exito = false;
		if (obj instanceof Field) {
			Field f = (Field) obj;
			exito = this.getName().equalsIgnoreCase(f.getName())
					&& this.getAlias().equalsIgnoreCase(f.getAlias());
		}
		return exito;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	protected Object clone() {
		Field obj = null;
		try {
			obj = (Field) super.clone();
		} catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
		}
		return obj;
	}

	/**
	 * @return the functions
	 */
	public List<FieldFunction> getFunctions() {
		return functions;
	}

	/**
	 * @return the hasFunctions
	 */
	public boolean hasFunctions() {
		return hasFunctions;
	}

	/**
	 * @param functions
	 *            the functions to set
	 */
	public void setFunctions(List<FieldFunction> functions) {
		this.hasFunctions = functions.size() > 0;
		this.functions = functions;
		if (this.functions.size() > 0)
			Collections
					.sort(this.functions, Comparators.FieldComparators
							.getComparatorByFunctionOrder());
	}

	/**
	 * @return the searchDisplay
	 */
	public boolean isSearchDisplay() {
		return searchDisplay;
	}

	/**
	 * @param searchDisplay
	 *            the searchDisplay to set
	 */
	public void setSearchDisplay(boolean searchDisplay) {
		this.searchDisplay = searchDisplay;
	}

	/**
	 * @return the queryDisplay
	 */
	public boolean isQueryDisplay() {
		return queryDisplay;
	}

	/**
	 * @param queryDisplay
	 *            the queryDisplay to set
	 */
	public void setQueryDisplay(boolean queryDisplay) {
		this.queryDisplay = queryDisplay;
	}

	/**
	 * @return the hasLink
	 */
	public boolean hasLink() {
		return hasLink;
	}

	/**
	 * @param hasLink
	 *            the hasLink to set
	 */
	public void setHasLink(boolean hasLink) {
		this.hasLink = hasLink;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link
	 *            the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	public void setIdentify(boolean identify) {
		this.identify = identify;
	}

	public boolean isIdentify() {
		return identify;
	}

}
