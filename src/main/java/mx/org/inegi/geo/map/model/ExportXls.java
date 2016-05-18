/**
 * 
 */
package mx.org.inegi.geo.map.model;

import java.util.List;

/**
 * @author femat
 *
 */
public class ExportXls {

	private String title;

	private List<String> columns;

	private List<List<String>> values;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the columns
	 */
	public List<String> getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	/**
	 * @return the values
	 */
	public List<List<String>> getValues() {
		return values;
	}

	/**
	 * @param values
	 *            the values to set
	 */
	public void setValues(List<List<String>> values) {
		this.values = values;
	}

}
