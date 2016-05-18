package mx.org.inegi.geo.map.denue.cluster;

/**
 *
 * @author Aaron.Villar
 */
public class PointFeature {

	private String count;

	private String point;

	public PointFeature() {

	}

	public PointFeature(String count, String point) {
		this.count = count;
		this.point = point;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return Integer.parseInt(count);
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(String count) {
		this.count = count;
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
		this.point = "POINT(" + point.replace(",", " ") + ")";
	}

}
