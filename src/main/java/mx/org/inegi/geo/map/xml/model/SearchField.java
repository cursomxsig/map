package mx.org.inegi.geo.map.xml.model;

/**
 *
 * @author yan.luevano
 */
public class SearchField {

	private String name;

	private String dictionary;

	private String convertfx;

	public SearchField(String name, String type) {
		this(name, type, null, null);
	}

	public SearchField(String name, String type, String dictionary,
			String convertfx) {
		this.name = name;
		if (dictionary != null && !dictionary.isEmpty()) {
			this.dictionary = dictionary;
		} else {
			this.dictionary = "spanish";
		}
		if (convertfx != null && !convertfx.isEmpty()) {
			this.convertfx = convertfx;
		} else {
			this.convertfx = "convierte";
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the dictionary
	 */
	public String getDictionary() {
		return dictionary;
	}

	/**
	 * @param dictionary
	 *            the dictionary to set
	 */
	public void setDictionary(String dictionary) {
		this.dictionary = dictionary;
	}

	/**
	 * @param convertfx
	 *            the convertfx to set
	 */
	public String getConvertFx() {
		return convertfx;
	}

	/**
	 * @param convertfx
	 *            the dictionary to set
	 */
	public void setConvertFx(String convertfx) {
		this.convertfx = convertfx;
	}

}
