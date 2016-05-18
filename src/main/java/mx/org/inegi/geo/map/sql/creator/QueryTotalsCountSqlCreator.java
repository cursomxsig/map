/**
 * 
 */
package mx.org.inegi.geo.map.sql.creator;

import mx.org.inegi.geo.map.model.GeospatialFunction;
import mx.org.inegi.geo.map.model.QueryTotalsInfo;
import mx.org.inegi.geo.map.sql.postgis.PostgisFunctions;
import mx.org.inegi.geo.map.sql.util.BufferTable;
import mx.org.inegi.geo.map.sql.util.SQLElement;
import mx.org.inegi.geo.map.xml.model.Table;

/**
 * @author femat
 *
 */
public class QueryTotalsCountSqlCreator implements SqlCreator {

	public QueryTotalsCountSqlCreator(QueryTotalsInfo queryTotalsInfo,
			Table table, String buffer) {
		this.queryTotalsInfo = queryTotalsInfo;
		this.buffer = BufferTable.getBuffer(buffer);
		this.table = table;
	}

	private QueryTotalsInfo queryTotalsInfo;

	private String buffer;

	private Table table;

	public String getSql() throws Exception {
		StringBuilder sb = new StringBuilder(SQLElement.SELECT);
		sb.append("count(*)");
		sb.append(SQLElement.FROM);
		sb.append(table.getSchema());
		sb.append(SQLElement.PERIOD);
		sb.append(table.getName());
		sb.append(SQLElement.WHERE);
		sb.append(PostgisFunctions.Overlaps(buffer,
				BufferTable.DEFAULT_GEOMETRY));
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
		return sb.toString();
	}

}
