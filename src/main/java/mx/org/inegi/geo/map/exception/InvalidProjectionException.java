/**
 * 
 */
package mx.org.inegi.geo.map.exception;

/**
 * @author femat
 *
 */
public class InvalidProjectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8993148209965469853L;

	private String message = "Not a valid projection.";

	public InvalidProjectionException() {
		System.out.println(this.message);
	}

	public InvalidProjectionException(String message) {
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
