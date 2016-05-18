package mx.org.inegi.geo.map.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class LabelInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	@NotEmpty
	private String geometry;
	@NotNull
	private Double resolution;

	private Double buffer;

	private boolean denue;

	public LabelInfo() {
		this.denue = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGeometry() {
		return geometry;
	}

	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}

	public Double getResolution() {
		return resolution;
	}

	public void setResolution(Double resolution) {
		this.resolution = resolution;
	}

	public Double getBuffer() {
		if ((this.resolution == 0.298582141)) {
			this.buffer = this.resolution * 32;
		} else if (this.resolution == 0.597164283) {
			this.buffer = this.resolution * 16;
		} else if (this.resolution == 1.194328566) {
			this.buffer = this.resolution * 8;
		}
		return buffer;
	}

	public void setBuffer(Double buffer) {
		this.buffer = buffer;
	}

	public void setDenue(boolean denue) {
		this.denue = denue;
	}

	public boolean isDenue() {
		return denue;
	}

}
