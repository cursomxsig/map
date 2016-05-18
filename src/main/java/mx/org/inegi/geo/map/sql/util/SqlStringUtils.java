/**
 * 
 */
package mx.org.inegi.geo.map.sql.util;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.org.inegi.geo.map.exception.InvalidProjectionException;
import mx.org.inegi.geo.map.sql.postgis.SRID;

/**
 * @author femat
 *
 */
public class SqlStringUtils {

	public static String getFactor(String geometry, String projection)
			throws InvalidProjectionException {
		String factor = null;
		if (geometry.equalsIgnoreCase(SQLElement.GEOM)
				&& projection.equalsIgnoreCase(SRID.GOOGLE)) {
			factor = "100000000000";
		} else if (geometry.equalsIgnoreCase(SQLElement.GEOM)
				&& projection.equalsIgnoreCase(SRID.GEOGRAPHIC)) {
			factor = "100000";
		} else {
			throw new InvalidProjectionException();
		}
		return factor;
	}

	public static String normalize(String value) {
		String s = removeAccentts(value);
		s = s.replaceAll(" ", "_");
		return s;
	}

	public static String removeAccentts(String value) {
		value = Normalizer.normalize(value, Normalizer.Form.NFD);
		Pattern regex = Pattern
				.compile("[^\\p{InBasic Latin}]", Pattern.DOTALL);
		Matcher m = regex.matcher(value);
		return m.replaceAll("");
	}

}
