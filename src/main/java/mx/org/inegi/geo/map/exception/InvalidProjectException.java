/**
 * 
 */
package mx.org.inegi.geo.map.exception;

/**
 * @author femat
 *
 */
public class InvalidProjectException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1787347572023016138L;

	private String message = "Not a valid project.";

	public InvalidProjectException() {
		System.out.println(this.message);
	}

	public InvalidProjectException(String message) {
		this.message = message;
		System.out.println(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
