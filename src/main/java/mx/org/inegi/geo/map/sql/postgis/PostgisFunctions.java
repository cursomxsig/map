/**
 * 
 */
package mx.org.inegi.geo.map.sql.postgis;

/**
 * @author femat
 *
 */
public class PostgisFunctions {

	private static final String ST_Area = "ST_Area";

	private static final String ST_AsText = "ST_AsText";

	private static final String ST_Buffer = "ST_Buffer";

	private static final String ST_Contains = "ST_Contains";

	private static final String ST_Envelope = "ST_Envelope";

	private static final String ST_GeomFromText = "ST_GeomFromText";

	private static final String ST_Intersects = "ST_Intersects";

	private static final String ST_Intersection = "ST_Intersection";

	private static final String ST_MakeBox2D = "ST_MakeBox2D";

	private static final String ST_MakePoint = "ST_MakePoint";

	private static final String ST_PointOnSurface = "ST_PointOnSurface";

	private static final String ST_SetSRID = "ST_SetSRID";

	private static final String ST_Simplify = "ST_Simplify";

	private static final String ST_Value = "ST_Value";

	private static final String OVERLAPS = " && ";

	private static final String ParenthesesOpen = "(";

	private static final String ParenthesesClose = ")";

	private static final String COMMA = ",";

	private static final String QUOTATION = "'";

	public static String ST_Area(String geometry) {
		StringBuilder sb = new StringBuilder(ST_Area);
		sb.append(ParenthesesOpen);
		sb.append(geometry);
		sb.append(ParenthesesClose);
		return sb.toString();
	}

	public static String ST_AsText(String geometry) {
		StringBuilder sb = new StringBuilder(ST_AsText);
		sb.append(ParenthesesOpen);
		sb.append(geometry);
		sb.append(ParenthesesClose);
		return sb.toString();
	}

	public static String ST_Buffer(String geometry, float radius) {
		StringBuilder sb = new StringBuilder(ST_Buffer);
		sb.append(ParenthesesOpen);
		sb.append(geometry);
		sb.append(COMMA);
		sb.append(radius);
		sb.append(ParenthesesClose);
		return sb.toString();
	}

	public static String ST_Contains(String g1, String g2) {
		StringBuilder sb = new StringBuilder(ST_Contains);
		sb.append(ParenthesesOpen);
		sb.append(g1);
		sb.append(COMMA);
		sb.append(g2);
		sb.append(ParenthesesClose);
		return sb.toString();
	}

	public static String ST_Envelope(String geometry) {
		StringBuilder sb = new StringBuilder(ST_Envelope);
		sb.append(ParenthesesOpen);
		sb.append(geometry);
		sb.append(ParenthesesClose);
		return sb.toString();
	}

	public static String ST_GeomFromText(String text, String projection) {
		StringBuilder sb = new StringBuilder(ST_GeomFromText);
		sb.append(ParenthesesOpen);
		sb.append(QUOTATION);
		sb.append(text);
		sb.append(QUOTATION);
		sb.append(COMMA);
		sb.append(projection);
		sb.append(ParenthesesClose);
		return sb.toString();
	}

	public static String ST_Intersects(String g1, String g2) {
		StringBuilder sb = new StringBuilder(ST_Intersects);
		sb.append(ParenthesesOpen);
		sb.append(g1);
		sb.append(COMMA);
		sb.append(g2);
		sb.append(ParenthesesClose);
		return sb.toString();
	}

	public static String ST_Intersection(String g1, String g2) {
		StringBuilder sb = new StringBuilder(ST_Intersection);
		sb.append(ParenthesesOpen);
		sb.append(g1);
		sb.append(COMMA);
		sb.append(g2);
		sb.append(ParenthesesClose);
		return sb.toString();
	}

	public static String ST_MakeBox2D(String p1, String p2) {
		StringBuilder sb = new StringBuilder(ST_MakeBox2D);
		sb.append(ParenthesesOpen);
		sb.append(p1);
		sb.append(COMMA);
		sb.append(p2);
		sb.append(ParenthesesClose);
		return sb.toString();
	}

	public static String ST_MakePoint(Double x1, Double y1) {
		StringBuilder sb = new StringBuilder(ST_MakePoint);
		sb.append(ParenthesesOpen);
		sb.append(x1);
		sb.append(COMMA);
		sb.append(y1);
		sb.append(ParenthesesClose);
		return sb.toString();
	}

	public static String ST_PointOnSurface(String geometry) {
		StringBuilder sb = new StringBuilder(ST_PointOnSurface);
		sb.append(ParenthesesOpen);
		sb.append(geometry);
		sb.append(ParenthesesClose);
		return sb.toString();
	}

	public static String ST_SetSRID(String geometry, String srid) {
		StringBuilder sb = new StringBuilder(ST_SetSRID);
		sb.append(ParenthesesOpen);
		sb.append(geometry);
		sb.append(COMMA);
		sb.append(srid);
		sb.append(ParenthesesClose);
		return sb.toString();
	}

	public static String ST_Simplify(String geometry, String tolerance) {
		StringBuilder sb = new StringBuilder(ST_Simplify);
		sb.append(ParenthesesOpen);
		sb.append(geometry);
		sb.append(COMMA);
		sb.append(tolerance);
		sb.append(ParenthesesClose);
		return sb.toString();
	}

	public static String ST_Value(String raster, String point) {
		StringBuilder sb = new StringBuilder(ST_Value);
		sb.append(ParenthesesOpen);
		sb.append(raster);
		sb.append(COMMA);
		sb.append(point);
		sb.append(ParenthesesClose);
		return sb.toString();
	}

	public static String Overlaps(String g1, String g2) {
		StringBuilder sb = new StringBuilder();
		sb.append(ParenthesesOpen);
		sb.append(g1);
		sb.append(OVERLAPS);
		sb.append(g2);
		sb.append(ParenthesesClose);
		return sb.toString();
	}

}
