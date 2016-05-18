/**
 * 
 */
package mx.org.inegi.geo.map.sql.util;

/**
 * @author femat
 *
 */
public class CustomSqlFunctions {

	private static final String COMMA = ",";

	private static final String ParenthesesOpen = "(";

	private static final String ParenthesesClose = ")";

	private static final String TO_TSQuery = " to_tsquery ";

	public static String TO_TSQuery(String dictionary, String value) {
		StringBuilder sb = new StringBuilder(TO_TSQuery);
		sb.append(ParenthesesOpen);
		sb.append("'" + dictionary + "'");
		sb.append(COMMA);
		sb.append(value);
		sb.append(ParenthesesClose);
		return sb.toString();
	}

	public static String convert(String function, String value) {
		StringBuilder sb = new StringBuilder(ParenthesesOpen);
		sb.append(SQLElement.SELECT);
		sb.append(function);
		sb.append(ParenthesesOpen);
		sb.append("'" + value + "'");
		sb.append(ParenthesesClose);
		sb.append(ParenthesesClose);
		return sb.toString();
	}

}
