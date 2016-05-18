/**
 * 
 */
package mx.org.inegi.geo.map.model;

import java.util.List;

/**
 * @author femat
 *
 */
public class ColorUpdate {

	private long id;

	private List<ThemeBoundary> boundaries;

	private String variable;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<ThemeBoundary> getBoundaries() {
		return boundaries;
	}

	public void setBoundaries(List<ThemeBoundary> boundaries) {
		this.boundaries = boundaries;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

}
