package mx.org.inegi.geo.map.sql.creator;

import java.util.Map;

import mx.org.inegi.geo.map.exception.NoQualifiedFieldsException;
import mx.org.inegi.geo.map.sql.postgis.PostgisFunctions;
import mx.org.inegi.geo.map.sql.util.BufferTable;
import mx.org.inegi.geo.map.sql.util.SQLElement;
import mx.org.inegi.geo.map.xml.model.Field;
import mx.org.inegi.geo.map.xml.model.Table;

public class EnergySqlCreator implements SqlCreator {

	private Table table;

	private String polygon;

	public EnergySqlCreator(Table table, String polygon) {
		this.table = table;
		this.polygon = polygon;
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
	
	private String getFieldsExportAsSql() throws NoQualifiedFieldsException {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> colum : table.getExport().getColumns().entrySet()) {		
				sb.append(colum.getKey());
				sb.append(SQLElement.AS);
				sb.append("\"");
				sb.append(colum.getValue());
				sb.append("\"");
				sb.append(SQLElement.COMMA);		
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.lastIndexOf(SQLElement.COMMA));
			return sb.toString();
		} else {
			throw new NoQualifiedFieldsException();
		}
	}

	@Override
	public String getSql() throws Exception {
		StringBuilder sb = new StringBuilder(SQLElement.SELECT);
		sb.append(getFieldsExportAsSql());
		sb.append(SQLElement.FROM);
		sb.append(table.getSchema());
		sb.append(SQLElement.PERIOD);
		sb.append(table.getName());
		if (polygon != null) {
			sb.append(SQLElement.WHERE);
			sb.append(PostgisFunctions.ST_Contains(PostgisFunctions.ST_GeomFromText(polygon, "900913"),
					BufferTable.DEFAULT_GEOMETRY));
		}
		return sb.toString();
	}

}
