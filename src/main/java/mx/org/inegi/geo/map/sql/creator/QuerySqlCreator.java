/**
 * 
 */
package mx.org.inegi.geo.map.sql.creator;

import mx.org.inegi.geo.map.exception.NoQualifiedFieldsException;
import mx.org.inegi.geo.map.model.QueryInfo;
import mx.org.inegi.geo.map.sql.util.SQLElement;
import mx.org.inegi.geo.map.xml.model.Field;
import mx.org.inegi.geo.map.xml.model.FieldFunction;
import mx.org.inegi.geo.map.xml.model.Table;

/**
 * @author yan.luevano
 * @author femat
 * 
 */
public class QuerySqlCreator implements SqlCreator {

	private QueryInfo queryInfo;

	private Table table;

	public QuerySqlCreator(QueryInfo queryInfo, Table table) {
		this.queryInfo = queryInfo;
		this.table = table;
	}

	private String getFieldsAsSql() throws NoQualifiedFieldsException {
		StringBuilder sb = new StringBuilder();
		for (Field f : table.getFields()) {
			if (!f.hasFunctions()) {
				if (f.hasLink()) {
					sb.append("'");
					sb.append(f.getLink());
					sb.append("'||");
					sb.append(f.getName());
				} else {
					sb.append(f.getName());
				}
				sb.append(SQLElement.AS);
				sb.append("\"");
				sb.append(f.getAlias());
				sb.append("\"");
				sb.append(SQLElement.COMMA);
			} else if (f.isQueryDisplay()) {
				sb.append(getFieldWithFunctionsAsSql(f));
				sb.append(SQLElement.AS);
				sb.append(f.getAlias());
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

	private String getFieldWithFunctionsAsSql(Field f) {
		StringBuilder sb = new StringBuilder();
		for (FieldFunction ff : f.getFunctions()) {
			sb.append(ff.getName());
			sb.append("(");
		}
		sb.append(f.getName());
		for (int i = 0; i < f.getFunctions().size(); i++) {
			sb.append(")");
		}
		return sb.toString();
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
		String gid = "gid";
		String id = String.valueOf(queryInfo.getId());
		StringBuilder sb = new StringBuilder(SQLElement.SELECT);
		sb.append(getFieldsAsSql());
		sb.append(SQLElement.FROM);
		sb.append(table.getSchema());
		sb.append(SQLElement.PERIOD);
		sb.append(table.getName());
		sb.append(SQLElement.WHERE);
		sb.append(gid);
		sb.append(SQLElement.EQUALS);
		sb.append(id);
		if (table.hasOrderByFields()) {
			sb.append(getOrderByFields());
		}
		return sb.toString();

	}

}
