/**
 * 
 */
package mx.org.inegi.geo.map.sql.util;

import mx.org.inegi.geo.map.sql.postgis.PostgisFunctions;
import mx.org.inegi.geo.map.sql.postgis.SRID;

/**
 * @author femat
 *
 */
public class BufferTable {

	public static final String DEFAULT_GEOMETRY = "the_geom";

	public static final String DEFAULT_ID = "gid";

	public static final String DEFAULT_SCHEMA = "control";

	public static final String DEFAULT_TABLE = "mibuffer";

	public static String getBuffer(String buffer) {
		StringBuilder sb = new StringBuilder(SQLElement.ParenthesesOpen);
		sb.append(PostgisFunctions.ST_GeomFromText(buffer, SRID.DEFAULT));
		sb.append(SQLElement.ParenthesesClose);
		return sb.toString();
	}

}
