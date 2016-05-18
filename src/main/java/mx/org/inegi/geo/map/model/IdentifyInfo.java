/**
 * 
 */
package mx.org.inegi.geo.map.model;

import static org.springframework.util.StringUtils.tokenizeToStringArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author femat
 *
 */
public class IdentifyInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8272407298179155823L;

	private static final String DELIMITERS = ",; \t\n";

	@NotEmpty
	private String project;

	@NotNull
	private Double resolution;

	@NotNull
	private String tables;

	@NotNull
	private Double x;

	@NotNull
	private Double y;

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
	 * @return the resolution
	 */
	public Double getResolution() {
		return resolution;
	}

	/**
	 * @param resolution
	 *            the resolution to set
	 */
	public void setResolution(Double resolution) {
		this.resolution = resolution;
	}

	/**
	 * @return the tables
	 */
	public List<String> getTables() {
		String[] t = tokenizeToStringArray(this.tables, DELIMITERS);
		List<String> tables = new ArrayList<String>(Arrays.asList(t));
		return tables;
	}

	/**
	 * @param tables
	 *            the tables to set
	 */
	public void setTables(String tables) {
		this.tables = tables;
	}

	/**
	 * @return the x
	 */
	public Double getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX1(Double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public Double getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(Double y) {
		this.y = y;
	}

}
