package mx.org.inegi.geo.map.domain;

import org.json.simple.JSONObject;

public class Share {

	private Long id;

	private JSONObject json;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public String getJsonS() {
		return json.toJSONString();
	}

}
