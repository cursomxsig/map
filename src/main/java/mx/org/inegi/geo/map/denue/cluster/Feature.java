package mx.org.inegi.geo.map.denue.cluster;

/**
 *
 * @author Aaron.Villar
 */
public class Feature {

	private String count;

	private String gid;

	public Feature() {
	}

	public Feature(String count, String gid) {
		this.count = count;
		this.gid = gid;
	}

	/**
	 * @return the count
	 */
	public String getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(String count) {
		this.count = count;
	}

	/**
	 * @return the gid
	 */
	public String getGid() {
		return gid;
	}

	/**
	 * @param gid
	 *            the gid to set
	 */
	public void setGid(String gid) {
		this.gid = gid;
	}
	
}
