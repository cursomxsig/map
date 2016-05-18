/**
 * 
 */
package mx.org.inegi.geo.map.exception;

/**
 * @author femat
 *
 */
public class NoQualifiedFieldsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5935209193163776255L;

	private String message = "No qualified fields present.";

	public NoQualifiedFieldsException() {
		System.out.println(this.message);
	}

	public NoQualifiedFieldsException(String message) {
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
