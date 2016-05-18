package mx.org.inegi.geo.map.domain;

public class ListDenue {

	private String cvegeo;

	private String name;

	public ListDenue() {
	}

	public ListDenue(String cvegeo, String name) {
		this.cvegeo = cvegeo;
		this.name = name;
	}

	public String getCvegeo() {
		return cvegeo;
	}

	public void setCvegeo(String cvegeo) {
		this.cvegeo = cvegeo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
