/**
 * 
 */
package mx.org.inegi.geo.map.exception;

import org.apache.log4j.Logger;

/**
 * @author femat
 *
 */
public class TimeFrameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8781274450956405543L;

	private static final Logger logger = Logger.getLogger(TimeFrameException.class);

	private String message;

	public TimeFrameException(int year) {
		message = "No existe información censal para el año " + year;
		logger.warn(message);
	}

	public String getMessage() {
		return message;
	}

}
