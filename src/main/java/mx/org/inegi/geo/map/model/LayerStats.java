/**
 * 
 */
package mx.org.inegi.geo.map.model;

import static org.springframework.util.StringUtils.tokenizeToStringArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author femat
 *
 */
public class LayerStats {

	private String project;

	private String session;

	private List<String> layers = new ArrayList<String>();

	private String userLocation;

	private String type;

	private static final String DELIMITERS = ",; \t\n";

	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return the session
	 */
	public String getSession() {
		return session;
	}

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(String session) {
		this.session = session;
	}

	/**
	 * @return the layers
	 */
	public List<String> getLayers() {
		return layers;
	}

	/**
	 * @param layers
	 *            the layers to set
	 */
	public void setLayers(String layers) {
		String[] layers_array = tokenizeToStringArray(layers, DELIMITERS);
		for (String layer : layers_array) {
			this.layers.add(layer);
		}
	}

	/**
	 * @return the userLocation
	 */
	public String getUserLocation() {
		return userLocation;
	}

	/**
	 * @param userLocation
	 *            the userLocation to set
	 */
	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
