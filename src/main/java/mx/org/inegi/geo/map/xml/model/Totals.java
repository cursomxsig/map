package mx.org.inegi.geo.map.xml.model;

import java.util.HashMap;

/**
 * 
 * @author femat
 *
 */
public class Totals {

	public Totals() {
		super();
	}

	public Totals(String alias, String sql, HashMap<String, String> columns) {
		super();
		this.columns = columns;
		this.alias = alias;
		this.sql = sql;
	}

	private HashMap<String, String> columns;

	private String alias;

	private String sql;

	public HashMap<String, String> getColumns() {
		return columns;
	}

	public String getAlias() {
		return alias;
	}

	public String getSql() {
		return sql;
	}

}
