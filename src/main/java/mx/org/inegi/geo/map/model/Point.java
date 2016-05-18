/**
 * 
 */
package mx.org.inegi.geo.map.model;

import javax.validation.constraints.NotNull;

/**
 * @author femat
 *
 */
public class Point {

	@NotNull
	private Double x;

	@NotNull
	private Double y;

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
	public void setX(Double x) {
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

	public String getPoint() {
		StringBuilder sb = new StringBuilder("POINT(");
		sb.append(x);
		sb.append(" ");
		sb.append(y);
		sb.append(")");
		return sb.toString();
	}

}
