/**
 * 
 */
package mx.org.inegi.geo.map.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import mx.org.inegi.geo.map.model.ExportXls;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * @author femat
 *
 */
public class CsvReport {

	ExportXls o;

	public CsvReport(ExportXls o) {
		super();
		this.o = o;
	}

	public byte[] generate() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		CSVWriter writer = new CSVWriter(new OutputStreamWriter(baos));
		String[] colums = o.getColumns().toArray(
				new String[o.getColumns().size()]);
		writer.writeNext(colums);
		for (List<String> values : o.getValues()) {
			writer.writeNext(values.toArray(new String[o.getValues().size()]));
		}
		writer.close();
		baos.close();
		return baos.toByteArray();
	}
}
