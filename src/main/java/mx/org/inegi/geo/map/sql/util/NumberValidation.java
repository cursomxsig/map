package mx.org.inegi.geo.map.sql.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author yan.luevano
 */
public class NumberValidation {

	final static Pattern numberRegex = Pattern.compile("\\d+");
	final static Pattern floatRegex = Pattern.compile("\\d+\\.\\d+");

	public static boolean isNumber(String value) {
		Matcher regexMatcher = numberRegex.matcher(value);
		return regexMatcher.matches();
	}

	public static boolean isFloat(String value) {
		if (value == null)
			return false;
		Matcher regexMatcher = floatRegex.matcher(value);
		return regexMatcher.matches();
	}

}
