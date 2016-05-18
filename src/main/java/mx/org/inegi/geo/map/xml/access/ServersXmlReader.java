package mx.org.inegi.geo.map.xml.access;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mx.org.inegi.geo.map.xml.model.Server;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author yan.luevano
 */
public class ServersXmlReader<T> extends AbstractXmlReader<T> {

	private List<T> data;

	public ServersXmlReader(File xmlFile) {
		super(xmlFile);
	}

	@SuppressWarnings("unchecked")
	private T getServer(Element el) {
		String alias = getTextValue(el, "alias");
		String ip = getTextValue(el, "ip");
		String port = getTextValue(el, "port");
		String user = getTextValue(el, "user");
		String password = getTextValue(el, "password");
		String url = getTextValue(el, "url");
		String driverClass = getTextValue(el, "driverClass");
		String validationQuery = getTextValue(el, "validationQuery");
		Server server = new Server(alias, ip, port, user, password, url,
				driverClass, validationQuery);
		return (T) server;
	}

	@Override
	protected List<T> readDocumentStructure() {
		Element docEle = dom.getDocumentElement();
		NodeList nl = docEle.getElementsByTagName("server");
		data = new ArrayList<T>();
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				Element el = (Element) nl.item(i);
				data.add(getServer(el));
			}
		}
		return data;
	}

}
