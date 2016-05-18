/**
 * 
 */
package mx.org.inegi.geo.map.domain;

/**
 * @author femat
 *
 */
public class Layer {

	public Layer(String project, String session, String layer,
			String userLocation, String type) {
		super();
		this.project = project;
		this.session = session;
		this.layer = layer;
		this.userLocation = userLocation;
		this.type = type;
	}

	private String project;

	private String session;

	private String layer;

	private String userLocation;

	private String type;

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
	 * @return the layer
	 */
	public String getLayer() {
		return layer;
	}

	/**
	 * @param layer
	 *            the layer to set
	 */
	public void setLayer(String layer) {
		this.layer = layer;
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
