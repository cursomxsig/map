package mx.org.inegi.geo.map.xml.model;

/**
 *
 * @author Aaron.Villar
 */
public class SqlGenerator {

	private String[] attributes;
	private String[] aliases;
	private String sql;
	final String pattern = "REMPLAZAO";

	public String getSql(String attribute) {
		int i = 0;
		String result = null;
		for (String s : attributes) {
			if (s.equalsIgnoreCase(attribute)
					|| aliases[i].equalsIgnoreCase(attribute)) {
				result = sql.replaceAll(pattern, s);
				break;
			}
			i++;
		}
		return result;
	}

	public SqlGenerator(String attributes, String aliases, String sql) {
		this.attributes = attributes.split(",");
		this.aliases = aliases.split(",");
		this.sql = sql;
	}

	/**
	 * @return the attributes
	 */
	public String[] getAttributes() {
		return this.attributes;
	}

	/**
	 * @param attributes
	 *            the attributes to set
	 */
	public void setAttributes(String[] atributos) {
		this.attributes = atributos;
	}

	/**
	 * @return the aliases
	 */
	public String[] getAliases() {
		return this.aliases;
	}

	/**
	 * @return the aliases
	 */
	public String getAliasesWeb() {
		StringBuilder sb = new StringBuilder();
		for (String aa : aliases) {
			sb.append(aa).append(",");
		}
		sb.delete(sb.length() - 1, sb.length());
		return sb.toString();
	}

	/**
	 * @param aliases
	 *            the aliases to set
	 */
	public void setAliases(String[] aliases) {
		this.aliases = aliases;
	}

	/**
	 * @return the sql
	 */
	public String getSql() {
		return this.sql;
	}

	/**
	 * @param sql
	 *            the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	public boolean isValidField(String field) {
		boolean isValid = false;
		if (field != null) {
			for (String c : aliases) {
				if (c.equalsIgnoreCase(field)) {
					isValid = true;
					break;
				}
			}
			for (String c : attributes) {
				if (c.equalsIgnoreCase(field)) {
					isValid = true;
					break;
				}
			}
		}
		return isValid;
	}
}
