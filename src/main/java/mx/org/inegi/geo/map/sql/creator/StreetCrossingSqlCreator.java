/**
 * 
 */
package mx.org.inegi.geo.map.sql.creator;

import java.util.List;

import mx.org.inegi.geo.map.model.StreetCrossing;
import mx.org.inegi.geo.map.sql.postgis.PostgisFunctions;
import mx.org.inegi.geo.map.sql.util.CustomSqlFunctions;
import mx.org.inegi.geo.map.sql.util.SQLElement;
import mx.org.inegi.geo.map.xml.model.SearchField;
import mx.org.inegi.geo.map.xml.model.Table;

/**
 *
 * @author femat
 * 
 */
public class StreetCrossingSqlCreator implements SqlCreator {

	private StreetCrossing sc;

	private Table table;

	public StreetCrossingSqlCreator(StreetCrossing sc, Table table) {
		this.sc = sc;
		this.table = table;
	}

	private final String ts_match = " @@ ";

	public String getSql() throws Exception {
		String geomA = "a." + table.getGeometry();
		String geomB = "b." + table.getGeometry();
		List<SearchField> searchFields = table.getSearchFields();
		StringBuilder sb = new StringBuilder(SQLElement.SELECT);
		sb.append("a.nomvial as Street1, a.entmunloc as Loc1, b.nomvial as Street2, b.entmunloc as Loc2");
		String intersection = PostgisFunctions.ST_Intersection(geomA, geomB);
		sb.append(SQLElement.COMMA);
		String buffer = PostgisFunctions.ST_Buffer(intersection, 0.0002f);
		String envelope = PostgisFunctions.ST_Envelope(buffer);
		String location = PostgisFunctions.ST_AsText(envelope);
		sb.append(location);
		sb.append(SQLElement.AS);
		sb.append("location");
		sb.append(SQLElement.COMMA);
		String pointOnSurface = PostgisFunctions
				.ST_PointOnSurface(intersection);
		String point = PostgisFunctions.ST_AsText(pointOnSurface);
		sb.append(point);
		sb.append(SQLElement.AS);
		sb.append("point");
		sb.append(SQLElement.FROM);

		sb.append(SQLElement.ParenthesesOpen);
		sb.append(SQLElement.SELECT);
		sb.append("gid, nomvial, entmunloc, ");
		sb.append(table.getGeometry());
		sb.append(SQLElement.FROM);
		sb.append(table.getSchema());
		sb.append(SQLElement.PERIOD);
		sb.append(table.getName());
		sb.append(SQLElement.WHERE);
		sb.append(searchFields.get(0).getName());
		sb.append(ts_match);
		String convert = CustomSqlFunctions.convert(searchFields.get(0)
				.getConvertFx(), sc.getStreet1());
		String f1 = CustomSqlFunctions.TO_TSQuery(searchFields.get(0)
				.getDictionary(), convert);
		sb.append(f1);
		if (sc.getCity() != null) {
			sb.append(SQLElement.AND);
			sb.append(searchFields.get(1).getName());
			sb.append(ts_match);
			String convert2 = CustomSqlFunctions.convert(searchFields.get(1)
					.getConvertFx(), sc.getCity());
			String f2 = CustomSqlFunctions.TO_TSQuery(searchFields.get(1)
					.getDictionary(), convert2);
			sb.append(f2);
		}
		sb.append(SQLElement.ParenthesesClose);
		sb.append(" a, ");

		sb.append(SQLElement.ParenthesesOpen);
		sb.append(SQLElement.SELECT);
		sb.append("gid, nomvial, entmunloc, ");
		sb.append(table.getGeometry());
		sb.append(SQLElement.FROM);
		sb.append(table.getSchema());
		sb.append(SQLElement.PERIOD);
		sb.append(table.getName());
		sb.append(SQLElement.WHERE);
		sb.append(searchFields.get(0).getName());
		sb.append(ts_match);
		String convert3 = CustomSqlFunctions.convert(searchFields.get(0)
				.getConvertFx(), sc.getStreet2());
		String f3 = CustomSqlFunctions.TO_TSQuery(searchFields.get(0)
				.getDictionary(), convert3);
		sb.append(f3);
		if (sc.getCity() != null) {
			sb.append(SQLElement.AND);
			sb.append(searchFields.get(1).getName());
			sb.append(ts_match);
			String convert4 = CustomSqlFunctions.convert(searchFields.get(1)
					.getConvertFx(), sc.getCity());
			String f4 = CustomSqlFunctions.TO_TSQuery(searchFields.get(1)
					.getDictionary(), convert4);
			sb.append(f4);
		}
		sb.append(SQLElement.ParenthesesClose);
		sb.append(" b ");

		sb.append(SQLElement.WHERE);
		sb.append(PostgisFunctions.Overlaps(geomA, geomB));
		sb.append(SQLElement.AND);
		sb.append(PostgisFunctions.ST_Intersects(geomA, geomB));
		sb.append(SQLElement.ORDER_BY);
		sb.append("a.entmunloc");
		sb.append(SQLElement.LIMIT);
		sb.append("10");
		return sb.toString();
	}

}
