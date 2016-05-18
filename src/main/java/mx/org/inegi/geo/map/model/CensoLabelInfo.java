package mx.org.inegi.geo.map.model;

import java.util.Arrays;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class CensoLabelInfo implements ResolutionLevel {
	@NotNull
	private int id;
	@NotEmpty
	private String point;
	@NotNull
	private Double resolution;

	private String variable;

	private int resolutionLevel;

	private Double buffer;

	private int typeSector;

	private String year;
	
	private boolean national;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public Double getResolution() {
		return resolution;
	}

	public void setResolution(Double resolution) {
		if (Arrays.asList(level1).contains(resolution)) {
			this.resolutionLevel = 1;
		} else if (Arrays.asList(level2).contains(resolution)) {
			this.resolutionLevel = 2;
		} else if (Arrays.asList(level3).contains(resolution)) {
			this.resolutionLevel = 3;
		} else if (Arrays.asList(level4).contains(resolution)) {
			this.resolutionLevel = 4;
		} else if (Arrays.asList(level5).contains(resolution)) {
			this.resolutionLevel = 5;
		} else if (Arrays.asList(level6).contains(resolution)) {
			this.resolutionLevel = 6;
		} else if (Arrays.asList(level7).contains(resolution)) {
			this.resolutionLevel = 7;
		} else {
			this.resolutionLevel = 1;
		}
		this.resolution = resolution;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	@Override
	public int getResolutionLevel() {
		return this.resolutionLevel;
	}

	public void setBuffer(Double buffer) {
		this.buffer = buffer;
	}

	public Double getBuffer() {
		if ((this.resolution == 0.298582141)) {
			this.buffer = this.resolution * 32;
		} else if (this.resolution == 0.597164283) {
			this.buffer = this.resolution * 16;
		} else if (this.resolution == 1.194328566) {
			this.buffer = this.resolution * 8;
		} else
			this.buffer = this.resolution * 5;
		return buffer;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public boolean isNational() {
		return national;
	}
	public void setNational(boolean national) {
		this.national = national;
	}

	public boolean isStade() {
		if ((resolutionLevel == 6) || (resolutionLevel == 7))
			return true;
		else
			return false;

	}

	public int getTypeSector() {
		if (String.valueOf(id).length() == 1)
			this.typeSector = 1;
		else if (String.valueOf(id).length() == 2)
			this.typeSector = 2;
		else
			this.typeSector = 3;
		return typeSector;
	}

}
