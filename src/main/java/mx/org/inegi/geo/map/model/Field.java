/**
 * 
 */
package mx.org.inegi.geo.map.model;

/**
 * @author femat
 *
 */
public class Field {

	private String label;

	private Object value;

	public Field(String label, Object value) {
		this.label = label;
		this.value = value;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

}
