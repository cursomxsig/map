/**
 * 
 */
package mx.org.inegi.geo.map.sql.creator;

import mx.org.inegi.geo.map.exception.NoQualifiedFieldsException;
import mx.org.inegi.geo.map.model.GeospatialFunction;
import mx.org.inegi.geo.map.model.QueryTotalsInfo;
import mx.org.inegi.geo.map.sql.postgis.PostgisFunctions;
import mx.org.inegi.geo.map.sql.util.BufferTable;
import mx.org.inegi.geo.map.sql.util.SQLElement;
import mx.org.inegi.geo.map.xml.model.Field;
import mx.org.inegi.geo.map.xml.model.Table;

/**
 * 
 * @author femat
 * 
 */
public class QueryTotalsSqlCreator implements SqlCreator {

	public QueryTotalsSqlCreator(QueryTotalsInfo queryTotalsInfo, Table table,
			String buffer, int limit, int offset) {
		this.queryTotalsInfo = queryTotalsInfo;
		this.buffer = BufferTable.getBuffer(buffer);
		this.table = table;
		this.limit = limit;
		this.offset = offset;
	}

	private QueryTotalsInfo queryTotalsInfo;

	private String buffer;

	private Table table;

	private int limit;

	private int offset;

	private String getFieldsAsSql() throws NoQualifiedFieldsException {
		StringBuilder sb = new StringBuilder();
		for (Field f : table.getFields()) {
			if (f.isIdentify()) {
				sb.append(f.getName());
				sb.append(SQLElement.AS);
				sb.append("\"");
				sb.append(f.getAlias());
				sb.append("\"");
				sb.append(SQLElement.COMMA);
			}
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.lastIndexOf(SQLElement.COMMA));
			return sb.toString();
		} else {
			throw new NoQualifiedFieldsException();
		}
	}

	private String getOrderByFields() {
		StringBuilder sb = new StringBuilder();
		sb.append(SQLElement.ORDER_BY);
		for (String s : table.getOrderByFields()) {
			sb.append(s);
			sb.append(SQLElement.COMMA);
		}
		sb.deleteCharAt(sb.lastIndexOf(SQLElement.COMMA));
		sb.append(" ");
		return sb.toString();
	}

	public String getSql() throws Exception {
		StringBuilder sb = new StringBuilder(SQLElement.SELECT);
		sb.append(getFieldsAsSql());
		sb.append(SQLElement.FROM);
		sb.append(table.getSchema());
		sb.append(SQLElement.PERIOD);
		sb.append(table.getName());
		sb.append(SQLElement.WHERE);
		sb.append(PostgisFunctions.Overlaps(BufferTable.DEFAULT_GEOMETRY,
				buffer));
		sb.append(SQLElement.AND);
		String function = null;
		if (queryTotalsInfo.getFunction() == GeospatialFunction.INTERSECTS) {
			function = PostgisFunctions.ST_Intersects(buffer,
					BufferTable.DEFAULT_GEOMETRY);

		} else if (queryTotalsInfo.getFunction() == GeospatialFunction.CONTAINS) {
			function = PostgisFunctions.ST_Contains(buffer,
					BufferTable.DEFAULT_GEOMETRY);
		}
		sb.append(function);
		if (table.hasOrderByFields()) {
			sb.append(getOrderByFields());
		}
		sb.append(SQLElement.LIMIT);
		sb.append(limit);
		sb.append(SQLElement.OFFSET);
		sb.append(offset);
		return sb.toString();
	}

}
