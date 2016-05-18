package mx.org.inegi.geo.map.model;

public class ThemeMinMax {
	
	
	
	public ThemeMinMax() {
		super();
	}

	public ThemeMinMax(String elementos, String rgb, int position) {
		String[] e = elementos.split(",");
		this.stratum = (1 + position) + "";
		this.n = Integer.parseInt(e[2]);
		this.cvegeo = "0";
		this.rgb = rgb;
		this.min = e[0];
		this.max = e[1];
		this.elem = e[2];
	}

	private String stratum;
	private int n;
	private String rgb;
	private String cvegeo;
	private String min;
	private String max;
	private String elem;
	

	
	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getElem() {
		return elem;
	}

	public void setElem(String elem) {
		this.elem = elem;
	}

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
 
