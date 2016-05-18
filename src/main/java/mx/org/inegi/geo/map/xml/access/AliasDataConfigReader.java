package mx.org.inegi.geo.map.xml.access;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mx.org.inegi.geo.map.xml.model.Document;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author yan.luevano
 * 
 */
public class AliasDataConfigReader<T> extends AbstractXmlReader<T> {

	private static final Logger logger = Logger
			.getLogger(AliasDataConfigReader.class);

	private List<T> data;
	private final String path;

	public AliasDataConfigReader(File xmlFile, String path) {
		super(xmlFile);
		this.path = path + "xml" + File.separatorChar;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<T> readDocumentStructure() {
		Element docEle = dom.getDocumentElement();
		NodeList nl = docEle.getElementsByTagName("document");
		data = new ArrayList<T>();
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				Element el = (Element) nl.item(i);
				Document doc = (Document) getDocument(el);
				data.add((T) doc);
			}
		}
		return data;
	}

	@SuppressWarnings("unchecked")
	private T getDocument(Element el) {
		String filename = getTextValue(el, "filename");
		Document doc = new Document(filename);
		File f = new File(path + filename);
		if (f.exists())
			doc.setFile(f);
		else
			logger.warn("File " + filename + " was not found at " + path);
		return (T) doc;
	}

}
