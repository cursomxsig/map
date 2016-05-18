package mx.org.inegi.geo.map.xml.access;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author yan.luevano
 */
public abstract class AbstractXmlReader<T> {

	protected File xmlFile;

	protected Document dom;

	public AbstractXmlReader(File xmlFile) {
		this.xmlFile = xmlFile;
		try {
			parseXmlFile();
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace();
		}
	}

	private void parseXmlFile() throws ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			dom = db.parse(xmlFile);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	protected String getTextValue(Element el, String tagName) {
		String value = null;
		NodeList nl = el.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element elem = (Element) nl.item(0);
			value = elem.getFirstChild().getNodeValue();
		}
		return value;
	}

	protected int getIntValue(Element el, String tagName) {
		return Integer.parseInt(getTextValue(el, tagName));
	}

	protected abstract List<T> readDocumentStructure();

	public List<T> getData() {
		return readDocumentStructure();
	}

}
