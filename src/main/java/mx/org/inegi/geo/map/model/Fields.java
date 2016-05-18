/**
 * 
 */
package mx.org.inegi.geo.map.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author femat
 *
 */
public class Fields {

	private List<Field> fields = new ArrayList<Field>();

	public void add(Field field) {
		fields.add(field);
	}

	public void add(String label, Object value) {
		fields.add(new Field(label, value));
	}

	public void add(Entry<String, Object> entry) {
		Field f = new Field(entry.getKey(), entry.getValue());
		fields.add(f);
	}

	/**
	 * @return the fields
	 */
	public List<Field> getFields() {
		return fields;
	}

}
