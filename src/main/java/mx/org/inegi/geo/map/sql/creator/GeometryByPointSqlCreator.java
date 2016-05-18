/**
 * 
 */
package mx.org.inegi.geo.map.sql.creator;

import java.util.List;

import mx.org.inegi.geo.map.sql.postgis.PostgisFunctions;
import mx.org.inegi.geo.map.sql.util.SQLElement;
import mx.org.inegi.geo.map.sql.util.SqlStringUtils;
import mx.org.inegi.geo.map.xml.model.Field;
import mx.org.inegi.geo.map.xml.model.Table;
import mx.org.inegi.geo.map.xml.model.TableFields;

import org.apache.log4j.Logger;

/**
 * @author femat
 *
 */
public class GeometryByPointSqlCreator implements SqlCreator {

	private static final Logger logger = Logger
			.getLogger(GeometryByPointSqlCreator.class);

	private Table table;
	private String point;

	public GeometryByPointSqlCreator(Table table, String point) {
		this.table = table;
		this.point = point;
	}

	@Override
	public String getSql() throws Exception {
		TableFields tfields = table.getFields();
		List<Field> fields = tfields.getFields();
		String geometry = table.getGeometry();
		String schema = table.getSchema();
		String name = table.getName();
		String projection = table.getProjection();

		String factor = SqlStringUtils.getFactor(geometry, projection);

		StringBuilder sb = new StringBuilder(SQLElement.SELECT);

		for (Field field : fields) {
			String fieldAlias = SqlStringUtils.normalize(field.getAlias());
			sb.append(field.getName());
			sb.append(SQLElement.AS);
			sb.append(fieldAlias);
			sb.append(SQLElement.COMMA);
		}

		String tolerance = PostgisFunctions.ST_Area(geometry)
				+ SQLElement.SLASH + factor;
		String simplify = PostgisFunctions.ST_Simplify(geometry, tolerance);
		String asText = PostgisFunctions.ST_AsText(simplify);
		sb.append(asText);
		sb.append(SQLElement.AS);
		sb.append(geometry);
		sb.append(SQLElement.FROM);
		sb.append(schema);
		sb.append(SQLElement.PERIOD);
		sb.append(name);
		sb.append(SQLElement.WHERE);

		String geomFromText = PostgisFunctions.ST_GeomFromText(point,
				projection);
		String intersects = PostgisFunctions.ST_Intersects(geometry,
				geomFromText);

		sb.append(intersects);
		sb.append(SQLElement.LIMIT);
		sb.append("1");

		String sql = sb.toString();
		logger.info(sql);

		return sql;

	}

}
