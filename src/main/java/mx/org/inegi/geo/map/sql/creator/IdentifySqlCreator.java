/**
 * 
 */
package mx.org.inegi.geo.map.sql.creator;

import mx.org.inegi.geo.map.exception.NoQualifiedFieldsException;
import mx.org.inegi.geo.map.model.IdentifyInfo;
import mx.org.inegi.geo.map.sql.postgis.PostgisFunctions;
import mx.org.inegi.geo.map.sql.postgis.SRID;
import mx.org.inegi.geo.map.sql.util.SQLElement;
import mx.org.inegi.geo.map.xml.model.Field;
import mx.org.inegi.geo.map.xml.model.Table;

/**
 * @author yan.luevano
 * @author femat
 * 
 */
public class IdentifySqlCreator implements SqlCreator {

	private IdentifyInfo identifyInfo;

	private Table table;

	public IdentifySqlCreator(IdentifyInfo identifyInfo, Table table) {
		this.identifyInfo = identifyInfo;
		this.table = table;
	}

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
		float radius = table.getIconSize();
		String geometry = table.getGeometry();
		Double resolution = identifyInfo.getResolution();
		Double x1 = identifyInfo.getX() - (radius * resolution);
		Double x2 = identifyInfo.getX() + (radius * resolution);
		Double y1 = identifyInfo.getY() - (radius * resolution);
		Double y2 = identifyInfo.getY() + (radius * resolution);
		String p1 = PostgisFunctions.ST_MakePoint(x1, y1);
		String p2 = PostgisFunctions.ST_MakePoint(x2, y2);
		String box2d = PostgisFunctions.ST_MakeBox2D(p1, p2);
		String setsrid = PostgisFunctions.ST_SetSRID(box2d, SRID.DEFAULT);
		String buffer = PostgisFunctions.ST_Buffer(setsrid, radius);
		String overlaps = PostgisFunctions.Overlaps(geometry, buffer);
		String intersects = PostgisFunctions.ST_Intersects(geometry, setsrid);
		StringBuilder sb = new StringBuilder(SQLElement.SELECT);
		sb.append(getFieldsAsSql());
		sb.append(SQLElement.FROM);
		sb.append(table.getSchema());
		sb.append(SQLElement.PERIOD);
		sb.append(table.getName());
		sb.append(SQLElement.WHERE);
		sb.append(overlaps);
		sb.append(SQLElement.AND);
		sb.append(intersects);
		if (table.hasOrderByFields()) {
			sb.append(getOrderByFields());
		}
		return sb.toString();
	}

}
