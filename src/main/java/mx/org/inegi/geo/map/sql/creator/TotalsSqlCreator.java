/**
 * 
 */
package mx.org.inegi.geo.map.sql.creator;

import mx.org.inegi.geo.map.model.GeospatialFunction;
import mx.org.inegi.geo.map.model.QueryTotalsInfo;
import mx.org.inegi.geo.map.sql.postgis.PostgisFunctions;
import mx.org.inegi.geo.map.sql.util.BufferTable;
import mx.org.inegi.geo.map.sql.util.SQLElement;

/**
 * @author yan.luevano
 * @author femat
 * 
 */
public class TotalsSqlCreator implements SqlCreator {

	private QueryTotalsInfo queryTotalsInfo;

	private String buffer;

	private String sql;

	public TotalsSqlCreator(String sql, QueryTotalsInfo queryTotalsInfo,
			String buffer) {
		this.sql = sql;
		this.buffer = BufferTable.getBuffer(buffer);
		this.queryTotalsInfo = queryTotalsInfo;
	}

	private String getWhere() {
		StringBuilder sb = new StringBuilder();
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
		return sb.toString();
	}

	public String getSql() throws Exception {
		String newSql = sql.replace("THEWHERE", getWhere());
		StringBuilder sb = new StringBuilder(newSql);
		sb.append(SQLElement.LIMIT);
		sb.append("1");
		return sb.toString();
	}

}
