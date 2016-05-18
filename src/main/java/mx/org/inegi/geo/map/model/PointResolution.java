/**
 * 
 */
package mx.org.inegi.geo.map.model;

import java.util.Arrays;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author femat
 *
 */
public class PointResolution implements ResolutionLevel {

	@NotEmpty
	private String point;

	@NotNull
	private Double resolution;

	private int resolutionLevel;

	@Override
	public int getResolutionLevel() {
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
		return this.resolutionLevel;
	}

	/**
	 * @return the point
	 */
	public String getPoint() {
		return point;
	}

	/**
	 * @param point
	 *            the point to set
	 */
	public void setPoint(String point) {
		this.point = point;
	}

	/**
	 * @param resolution
	 *            the resolution to set
	 */
	public void setResolution(Double resolution) {
		this.resolution = resolution;
	}

}
