/**
 * 
 */
package mx.org.inegi.geo.map.web.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import mx.org.inegi.geo.map.model.Fields;
import mx.org.inegi.geo.map.xml.model.Table;

/**
 * @author femat
 *
 */
public class TableResponse {

	private String table;

	private String alias;

	private Collection<Fields> elements = new ArrayList<Fields>();

	public TableResponse(Table table, List<Map<String, Object>> response) {
		this.table = table.getUserAlias();
		this.alias = table.getAlias();
		for (Map<String, Object> map : response) {
			Fields fields = new Fields();
			for (Map.Entry<String, Object> e : map.entrySet()) {
				fields.add(e);
			}
			elements.add(fields);
		}
	}

	/**
	 * @return the table
	 */
	public String getTable() {
		return table;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @return the elements
	 */
	public Collection<Fields> getElements() {
		return elements;
	}

}
