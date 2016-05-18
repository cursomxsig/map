/**
 * 
 */
package mx.org.inegi.geo.map.exception;

import org.apache.log4j.Logger;

/**
 * @author femat
 *
 */
public class TableNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6546363561170406114L;

	private static final Logger logger = Logger
			.getLogger(TableNotFoundException.class);

	private String message;

	public TableNotFoundException(String table) {
		this.message = "Table " + table + " not found.";
		logger.warn(this.message);
	}

	public String getMessage() {
		return message;
	}

}
