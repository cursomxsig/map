package mx.org.inegi.geo.map.xml.loader;

/**
 * 
 * @author yan.luevano
 */
public class ConfigOptions {

	private String serversFilename;
	private String aliasFilename;
	private String stopWordsFilename;

	public String getServersFilename() {
		return serversFilename;
	}

	public void setServersFilename(String serversFilename) {
		this.serversFilename = serversFilename;
	}

	public String getAliasFilename() {
		return aliasFilename;
	}

	public void setAliasFilename(String aliasFilename) {
		this.aliasFilename = aliasFilename;
	}

	public String getStopWordsFilename() {
		return stopWordsFilename;
	}

	public void setStopWordsFilename(String stopWordsFilename) {
		this.stopWordsFilename = stopWordsFilename;
	}

}
