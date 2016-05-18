package mx.org.inegi.geo.map.domain;

import java.io.Serializable;

public class Vialidad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3207696892213289045L;
	String name;
	String geometry;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGeometry() {
		return geometry;
	}

	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}

}
