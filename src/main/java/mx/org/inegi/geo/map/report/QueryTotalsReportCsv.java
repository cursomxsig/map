/**
 * 
 */
package mx.org.inegi.geo.map.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

import mx.org.inegi.geo.map.xml.model.Table;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * @author femat
 *
 */
public class QueryTotalsReportCsv {

	public QueryTotalsReportCsv(List<Map<String, Object>> elements, Table table) {
		super();
		this.elements = elements;
		this.table = table;
	}

	private List<Map<String, Object>> elements;
	private Table table;

	public ByteArrayOutputStream generate() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		CSVWriter writer = new CSVWriter(new OutputStreamWriter(baos,
				"ISO8859-1"), ',', CSVWriter.DEFAULT_QUOTE_CHARACTER,
				CSVWriter.NO_ESCAPE_CHARACTER, "\n");

		int numrow = 0;
		String field = "";
		String[] rows = null;
		for (Map<String, Object> map : elements) { // Lista de resultados
			int numField = 1;
			for (Map.Entry<String, Object> dato : map.entrySet()) { // registros
				if (!dato.getKey().equalsIgnoreCase("id")) {// Encabezados
					String v = dato.getKey();
					if (v.equalsIgnoreCase("nombre")) {
						v = table.getUserAlias();
					}
					if (numrow == 0) {
						if (numField == 1) {
							field = String.valueOf(v);
						} else {
							field = field + "," + String.valueOf(v);
						}
						numField++;
						rows = field.split(",");
						writer.writeNext(rows);
						field = "";
						numField = 1;
					}
				}
				if (!dato.getKey().equalsIgnoreCase("id")) {

					if (numField == 1) {
						field = String.valueOf(dato.getValue());
					} else {
						field = field + "," + String.valueOf(dato.getValue());
					}
					numField++;

				}
			}
			rows = field.split(",");
			writer.writeNext(rows);
			field = "";
			numrow++;
		}
		writer.close();
		baos.close();
		return baos;
	}

	public void headerscolums(CSVWriter writer, Map.Entry<String, Object> dato,
			int numrow, int numField) {
		String field = "";
		String[] header = null;

		if (numrow == 0) {
			if (numField == 1) {
				field = String.valueOf(dato.getKey());
			} else {
				field = field + "," + String.valueOf(dato.getKey());
			}
			header = field.split(",");
			writer.writeNext(header);

		}
	}

}
