/**
 * 
 */
package mx.org.inegi.geo.map.sql.creator;

import static org.springframework.util.StringUtils.tokenizeToStringArray;

import java.util.LinkedList;

import mx.org.inegi.geo.map.sql.postgis.PostgisFunctions;
import mx.org.inegi.geo.map.sql.util.BufferTable;
import mx.org.inegi.geo.map.sql.util.SQLElement;

/**
 *
 * @author femat
 * 
 */
public class WktSqlCreator implements SqlCreator {

	private static final String DELIMITERS = ",; \t\n";

	private LinkedList<Integer> ids = new LinkedList<Integer>();

	public WktSqlCreator(String ids) {
		String[] id_array = tokenizeToStringArray(ids, DELIMITERS);
		for (String id : id_array) {
			this.ids.add(Integer.valueOf(id));
		}
	}

	public String getSql() throws Exception {
		StringBuilder sb = new StringBuilder(SQLElement.SELECT);
		sb.append(BufferTable.DEFAULT_ID);
		sb.append(SQLElement.AS);
		sb.append("id");
		sb.append(SQLElement.COMMA);
		String astext = PostgisFunctions
				.ST_AsText(BufferTable.DEFAULT_GEOMETRY);
		sb.append(astext);
		sb.append(SQLElement.AS);
		sb.append("wkt");
		sb.append(SQLElement.FROM);
		sb.append(BufferTable.DEFAULT_SCHEMA);
		sb.append(SQLElement.PERIOD);
		sb.append(BufferTable.DEFAULT_TABLE);
		sb.append(SQLElement.WHERE);
		sb.append(BufferTable.DEFAULT_ID);
		sb.append(SQLElement.IN);
		sb.append(SQLElement.ParenthesesOpen);
		for (Integer id : ids) {
			sb.append(id);
			sb.append(SQLElement.COMMA);
		}
		sb.deleteCharAt(sb.lastIndexOf(SQLElement.COMMA));
		sb.append(SQLElement.ParenthesesClose);
		return sb.toString();
	}

}
