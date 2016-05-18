package mx.org.inegi.geo.map.xml.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author yan.luevano
 */
@JsonAutoDetect(getterVisibility = Visibility.NONE, fieldVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
public class TableFields implements Iterable<Field>, Cloneable {

	@JsonProperty
	private List<Field> fields;

	public TableFields() {
		fields = new ArrayList<Field>();
	}

	public void clearFields() {
		fields.clear();
	}

	public Field get(int idx) {
		return fields.get(idx);
	}

	public int size() {
		return fields.size();
	}

	public boolean addField(Field f) {
		return fields.add(f);
	}

	public boolean addField(String name, String alias) {
		return fields.add(new Field(name, alias));
	}

	public boolean removeField(Field f) {
		return fields.remove(f);
	}

	public boolean removeField(String name, String alias) {
		boolean exito = false;
		Field f = getField(name);
		if (f != null) {
			exito = removeField(f);
		}
		return exito;
	}

	private Field getField(String name) {
		Field field = null;
		for (Field f : fields) {
			if (f.getName().equalsIgnoreCase(name)) {
				field = f;
				break;
			}
		}
		return field;
	}

	public Field getFieldByName(String name) {
		return getField(name);
	}

	public Field getFieldByAlias(String name) {
		Field field = null;
		for (Field f : fields) {
			if (f.getAlias().equalsIgnoreCase(name)) {
				field = f;
				break;
			}
		}
		return field;
	}

	public Iterator<Field> iterator() {
		return fields.iterator();
	}

	@Override
	protected Object clone() {
		TableFields obj = null;
		try {
			obj = (TableFields) super.clone();
			List<Field> newFields = new ArrayList<Field>(fields.size());

			for (Field f : fields) {
				Field fi = (Field) f.clone();
				newFields.add(fi);
			}

			obj.fields = newFields;

		} catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
		}
		return obj;
	}

	public List<Field> getFields() {
		return fields;
	}

}
