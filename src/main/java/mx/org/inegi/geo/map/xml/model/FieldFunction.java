package mx.org.inegi.geo.map.xml.model;

/**
 *
 * @author yan.luevano
 */
public class FieldFunction {

	private String name;

	private int order;

	public FieldFunction(String name, String order) {
		this.name = name;
		this.order = Integer.parseInt(order);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

}
