/**
 * 
 */
package mx.org.inegi.geo.map.model;

import java.util.Arrays;

import javax.validation.constraints.NotNull;

import mx.org.inegi.geo.map.denue.cluster.ClusterData;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author femat
 *
 */
public class ScianInfo implements ResolutionLevel {

	private String ent;

	@NotNull
	private ScianType type;

	@NotEmpty
	private String superTypeId;

	@NotEmpty
	private String point;

	@NotNull
	private Double resolution;

	private Double buffer;

	private int resolutionLevel;

	private int x;

	private int y;

	private String bbox;

	private int width;

	private int height;

	private int total;

	private int limit;

	/**
	 * @return the ent
	 */
	public String getEnt() {
		return ent;
	}

	/**
	 * @param ent
	 *            the ent to set
	 */
	public void setEnt(String ent) {
		this.ent = ent;
	}

	/**
	 * @return the type
	 */
	public ScianType getType() {
		return type;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getBbox() {
		return bbox;
	}

	public void setBbox(String bbox) {
		this.bbox = bbox;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal() {
		ClusterData cu = new ClusterData();
		this.total = cu.getCount(bbox, x, y, width, height);
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(ScianType type) {
		this.type = type;
	}

	/**
	 * @return the superTypeId
	 */
	public String getSuperTypeId() {
		return superTypeId;
	}

	/**
	 * @param superTypeId
	 *            the superTypeId to set
	 */
	public void setSuperTypeId(String superTypeId) {
		this.superTypeId = superTypeId;
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

	public Double getResolution() {
		return resolution;
	}

	public void setResolution(Double resolution) {
		this.resolution = resolution;
	}

	public Double getBuffer() {
		if ((this.resolution == 0.298582141)) {
			// this.buffer = this.resolution * 20;
			this.buffer = this.resolution * 32;
		} else if (this.resolution == 0.597164283) {
			// this.buffer = this.resolution * 10;
			this.buffer = this.resolution * 16;
		} else if (this.resolution == 1.194328566) {
			this.buffer = this.resolution * 8;// 10;
		}
		return buffer;
	}

	public void setBuffer(Double buffer) {
		this.buffer = buffer;
	}

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

}
