package mx.org.inegi.geo.map.xml.access;

import static org.springframework.util.StringUtils.tokenizeToStringArray;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import mx.org.inegi.geo.map.xml.model.Field;
import mx.org.inegi.geo.map.xml.model.FieldFunction;
import mx.org.inegi.geo.map.xml.model.Resolution;
import mx.org.inegi.geo.map.xml.model.SearchField;
import mx.org.inegi.geo.map.xml.model.Table;
import mx.org.inegi.geo.map.xml.model.TableFields;
import mx.org.inegi.geo.map.xml.model.Totals;

/**
 * 
 * @author yan.luevano
 * @author femat
 * 
 */
public class TablesXmlReader<T> extends AbstractXmlReader<T> {

	private static final String DELIMITERS = ",; \t\n";

	private List<T> data;

	public TablesXmlReader(File xmlFile) {
		super(xmlFile);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<T> readDocumentStructure() {
		Element element = dom.getDocumentElement();
		NodeList nl = element.getElementsByTagName("table");
		data = new ArrayList<T>();
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				Element el = (Element) nl.item(i);
				Table table = (Table) getTable(el);
				getTableAttributes(table, el);
				data.add((T) table);
			}
		}
		return data;
	}

	@SuppressWarnings("unchecked")
	private T getTable(Element element) {
		String schema = getTextValue(element, "schema");
		String name = getTextValue(element, "name");
		String alias = getTextValue(element, "alias");
		String server = getTextValue(element, "server");
		String database = getTextValue(element, "database");
		String geometry = getTextValue(element, "geometry");
		String proj = getTextValue(element, "projection");
		String iconSize = getTextValue(element, "icon_size");
		String rasterAlias = getTextValue(element, "raster_alias");

		Resolution resolution = getResolution(element);
		TableFields tableFields = getTableFields(element);
		Totals totals = getTotals(element);
		Totals export = getExport(element);

		Table table = new Table(schema, name, alias, database, server,
				geometry, proj, rasterAlias, tableFields, totals,export,resolution,
				iconSize);

		List<SearchField> searchFields = getSearchFields(element);
		table.setSearchFields(searchFields);

		List<String> orderByFields = getOrderByFields(element);
		table.setOrderByFields(orderByFields);

		return (T) table;
	}

	private List<String> getOrderByFields(Element element) {
		NodeList orderbyNL = element.getElementsByTagName("orderby");
		List<String> orderByFields = new ArrayList<String>();
		for (int i = 0; i < orderbyNL.getLength(); i++) {
			Element fieldElem = (Element) orderbyNL.item(i);
			String field = getTextValue(fieldElem, "field");
			orderByFields.add(field);
		}
		return orderByFields;
	}

	private Resolution getResolution(Element element) {
		String min = null;
		String max = null;
		NodeList resolutionNL = element.getElementsByTagName("resolution");
		for (int i = 0; i < resolutionNL.getLength(); i++) {
			Element resolutionElem = (Element) resolutionNL.item(i);
			min = getTextValue(resolutionElem, "min");
			max = getTextValue(resolutionElem, "max");
		}
		Resolution resolution = new Resolution(min, max);
		return resolution;
	}

	/**
	 * 
	 * Will get as much functions as defined in the xml file in some field at a
	 * given moment
	 * 
	 * @param element
	 *            Element from tagname "field"
	 */
	private void getFunctions(Element element, List<FieldFunction> functions) {
		NodeList functionsNL = element.getElementsByTagName("functions");
		for (int i = 0; i < functionsNL.getLength(); i++) {
			Element functionsElem = (Element) functionsNL.item(i);
			NodeList functionNL = functionsElem
					.getElementsByTagName("function");
			for (int j = 0; j < functionNL.getLength(); j++) {
				Element functionElem = (Element) functionNL.item(j);
				String name = getTextValue(functionElem, "fname");
				String order = functionElem.getAttribute("order");
				FieldFunction ff = new FieldFunction(name, order);
				functions.add(ff);
			}
		}
	}

	private Field getField(Element element) {
		String name = getTextValue(element, "name");
		String alias = getTextValue(element, "alias");
		String searchDisplay = element.getAttribute("search_display");
		String queryDisplay = element.getAttribute("query_display");
		String link = element.getAttribute("link");
		String identify = element.getAttribute("identify");

		Field field = new Field(name, alias);
		if (!link.equalsIgnoreCase("")) {
			field.setHasLink(true);
			field.setLink(link);
		}
		if (searchDisplay.equalsIgnoreCase("true")
				|| searchDisplay.equalsIgnoreCase("false")) {
			field.setSearchDisplay(Boolean.valueOf(searchDisplay)
					.booleanValue());
		}
		if (queryDisplay.equalsIgnoreCase("true")
				|| queryDisplay.equalsIgnoreCase("false")) {
			field.setQueryDisplay(Boolean.valueOf(queryDisplay).booleanValue());
		}
		if (identify.equalsIgnoreCase("true")
				|| identify.equalsIgnoreCase("false")) {
			field.setIdentify(Boolean.valueOf(identify));
		}

		LinkedList<FieldFunction> fieldFunctions = new LinkedList<FieldFunction>();
		getFunctions(element, fieldFunctions);
		field.setFunctions(fieldFunctions);

		return field;
	}

	private SearchField getSearchField(Element element) {
		String name = getTextValue(element, "name");
		String type = element.getAttribute("type");
		String dictionary = element.getAttribute("dictionary");
		String convertFunction = element.getAttribute("convertf");
		SearchField searchField = new SearchField(name, type, dictionary,
				convertFunction);
		return searchField;
	}

	private TableFields getTableFields(Element element) {
		TableFields tableFields = new TableFields();
		NodeList fields = element.getElementsByTagName("fields");
		for (int i = 0; i < fields.getLength(); i++) {
			Element elem = (Element) fields.item(i);
			NodeList nl = elem.getElementsByTagName("field");
			if (nl != null && nl.getLength() > 0) {
				for (int j = 0; j < nl.getLength(); j++) {
					Element el2 = (Element) nl.item(j);
					Field f = getField(el2);
					if (f != null) {
						tableFields.addField(f);
					}
				}
			}
		}
		return tableFields;
	}

	private List<SearchField> getSearchFields(Element element) {
		List<SearchField> searchFields = new ArrayList<SearchField>();
		NodeList searchNL = element.getElementsByTagName("search");
		for (int i = 0; i < searchNL.getLength(); i++) {
			Element searchElem = (Element) searchNL.item(0);
			NodeList fieldNL = searchElem.getElementsByTagName("field");
			if (fieldNL != null && fieldNL.getLength() > 0) {
				for (int j = 0; j < fieldNL.getLength(); j++) {
					Element fieldElem = (Element) fieldNL.item(j);
					SearchField sf = getSearchField(fieldElem);
					searchFields.add(sf);
				}
			}
		}
		return searchFields;
	}

	private Totals getTotals(Element element) {
		Totals totals = null;
		NodeList totalsNL = element.getElementsByTagName("totals");
		if (totalsNL != null && totalsNL.getLength() > 0) {
			Element totalsElem = (Element) totalsNL.item(0);

			HashMap<String, String> columns = new HashMap<String, String>();
			NodeList columnsNL = totalsElem.getElementsByTagName("column");
			for (int i = 0; i < columnsNL.getLength(); i++) {
				Element columnElem = (Element) columnsNL.item(i);
				String name = getTextValue(columnElem, "name");
				String alias = getTextValue(columnElem, "alias");
				columns.put(name, alias);
			}

			String sql = getTextValue(totalsElem, "sql");
			NodeList sqlNL = totalsElem.getElementsByTagName("sql");
			Element sqlElem = (Element) sqlNL.item(0);
			String alias = sqlElem.getAttribute("alias");
			totals = new Totals(alias, sql, columns);
		}
		return totals;
	}
	
	
	private Totals getExport(Element element) {
		Totals totals = null;
		NodeList totalsNL = element.getElementsByTagName("export");
		if (totalsNL != null && totalsNL.getLength() > 0) {
			Element totalsElem = (Element) totalsNL.item(0);

			HashMap<String, String> columns = new HashMap<String, String>();
			NodeList columnsNL = totalsElem.getElementsByTagName("column");
			for (int i = 0; i < columnsNL.getLength(); i++) {
				Element columnElem = (Element) columnsNL.item(i);
				String name = getTextValue(columnElem, "name");
				String alias = getTextValue(columnElem, "alias");
				columns.put(name, alias);
			}

			String sql = getTextValue(totalsElem, "sql");
			NodeList sqlNL = totalsElem.getElementsByTagName("sql");
			Element sqlElem = (Element) sqlNL.item(0);
			String alias = sqlElem.getAttribute("type");
			totals = new Totals(alias, sql, columns);
		}
		return totals;
	}

	private void getTableAttributes(Table table, Element element) {

		String projects = element.getAttribute("projects").toLowerCase();

		String[] projs = tokenizeToStringArray(projects, DELIMITERS);
		List<String> prjs = Arrays.<String> asList(projs);
		table.setProjects(prjs);

		String search = element.getAttribute("search");
		if (search.equalsIgnoreCase("true") || search.equalsIgnoreCase("false")) {
			table.setSearch(Boolean.parseBoolean(search));
		}

		String identify = element.getAttribute("identify");
		if (identify.equalsIgnoreCase("true")
				|| identify.equalsIgnoreCase("false")) {
			table.setIdentify(Boolean.parseBoolean(identify));
		}

		String buffer = element.getAttribute("buffer");
		if (buffer.equalsIgnoreCase("true") || buffer.equalsIgnoreCase("false")) {
			table.setBuffer(Boolean.parseBoolean(buffer));
		}

		table.setUserAlias(element.getAttribute("user_alias"));

		String raster = element.getAttribute("raster");
		if (raster.equalsIgnoreCase("true") || raster.equalsIgnoreCase("false")) {
			table.setRaster(Boolean.parseBoolean(raster));
		} else {
			table.setRaster(false);
		}
	}

}
