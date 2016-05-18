/**
 * 
 */
package mx.org.inegi.geo.map.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * @author femat
 *
 */
public class QueryTotalsInfo {

	public QueryTotalsInfo() {
		super();
	}

	public QueryTotalsInfo(String project, String table, String polygon) {
		super();
		this.project = project;
		this.table = table;
		this.polygon = polygon;
	}

	public QueryTotalsInfo(String project, String table, String polygon,
			GeospatialFunction function) {
		super();
		this.project = project;
		this.table = table;
		this.polygon = polygon;
		this.function = function;
	}

	@NotNull
	@Min(1)
	private int page;

	@NotNull
	@Range(min = 10, max = 50)
	private int limit;

	@NotEmpty
	private String polygon;

	private String project;

	@NotEmpty
	private String table;

	private GeospatialFunction function;

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit
	 *            the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * @return the polygon
	 */
	public String getPolygon() {
		return polygon;
	}

	/**
	 * @param polygon
	 *            the polygon to set
	 */
	public void setPolygon(String polygon) {
		this.polygon = polygon;
	}

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
	 * @return the table
	 */
	public String getTable() {
		return table;
	}

	/**
	 * @param table
	 *            the table to set
	 */
	public void setTable(String table) {
		this.table = table;
	}

	/**
	 * @return the function
	 */
	public GeospatialFunction getFunction() {
		return function;
	}

	/**
	 * @param function
	 *            the function to set
	 */
	public void setFunction(GeospatialFunction function) {
		this.function = function;
	}

}
