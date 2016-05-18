/**
 * 
 */
package mx.org.inegi.geo.map.model;

/**
 * @author femat
 *
 */
public class ThemeBoundary {

	public ThemeBoundary() {
		super();
	}

	public ThemeBoundary(String boundaries, String rgb, int position) {
		String[] b = boundaries.split(",");
		this.stratum = (1 + position) + "";
		this.n = b.length;
		this.cvegeo = "'" + boundaries.replace(",", "','") + "'";
		this.rgb = rgb;
	}

	private String stratum;
	private int n;
	private String rgb;
	private String cvegeo;

	public String getStratum() {
		return stratum;
	}

	public int getN() {
		return n;
	}

	public String getRgb() {
		return rgb;
	}

	public String getCvegeo() {
		return cvegeo;
	}

	public void setStratum(String stratum) {
		this.stratum = stratum;
	}

	public void setN(int n) {
		this.n = n;
	}

	public void setRgb(String rgb) {
		this.rgb = rgb;
	}

	public void setCvegeo(String cvegeo) {
		this.cvegeo = cvegeo;
	}

}
