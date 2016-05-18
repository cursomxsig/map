/**
 * 
 */
package mx.org.inegi.geo.map.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author femat
 *
 */
public class QueryInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4056159603224221624L;

	@NotNull
	private int id;

	@NotEmpty
	private String project;

	@NotEmpty
	private String table;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

}
