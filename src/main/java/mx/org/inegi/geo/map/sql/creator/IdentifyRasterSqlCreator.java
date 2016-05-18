/**
 * 
 */
package mx.org.inegi.geo.map.sql.creator;

import mx.org.inegi.geo.map.model.IdentifyInfo;
import mx.org.inegi.geo.map.sql.postgis.PostgisFunctions;
import mx.org.inegi.geo.map.sql.postgis.SRID;
import mx.org.inegi.geo.map.sql.util.SQLElement;
import mx.org.inegi.geo.map.xml.model.Table;

/**
 * @author femat
 * 
 */
public class IdentifyRasterSqlCreator implements SqlCreator {

	private final String rast = "rast";

	private IdentifyInfo identifyInfo;

	private Table table;

	public IdentifyRasterSqlCreator(IdentifyInfo identifyInfo, Table table) {
		this.identifyInfo = identifyInfo;
		this.table = table;
	}

	public String getSql() throws Exception {
		Double x = identifyInfo.getX();
		Double y = identifyInfo.getY();
		String p = PostgisFunctions.ST_MakePoint(x, y);
		String setsrid = PostgisFunctions.ST_SetSRID(p, SRID.DEFAULT);
		String value = PostgisFunctions.ST_Value(rast, setsrid);
		String intersects = PostgisFunctions.ST_Intersects(rast, setsrid);
		StringBuilder sb = new StringBuilder(SQLElement.SELECT);
		sb.append(value);
		sb.append(SQLElement.AS);
		sb.append(table.getRasterAlias());
		sb.append(SQLElement.FROM);
		sb.append(table.getSchema());
		sb.append(SQLElement.PERIOD);
		sb.append(table.getName());
		sb.append(SQLElement.WHERE);
		sb.append(intersects);
		return sb.toString();
	}

}
