/**
 * 
 */
package mx.org.inegi.geo.map.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import mx.org.inegi.geo.map.model.ExportXls;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author femat
 *
 */
public class XlsReport {

	ExportXls o;

	public XlsReport(ExportXls o) {
		super();
		this.o = o;
	}

	public byte[] generate() throws IOException {
		Workbook wb = new XSSFWorkbook();

		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(font);

		Sheet sheet = wb.createSheet(o.getTitle());

		List<String> columns = o.getColumns();

		int i = 0;
		short j = 0;

		Row row = sheet.createRow(i);
		for (String column : columns) {
			Cell c = row.createCell(j);
			c.setCellStyle(style);
			c.setCellValue(column);
			j++;
		}

		i++;
		j = 0;

		for (List<String> values : o.getValues()) {
			row = sheet.createRow(i);
			for (String value : values) {
				Cell c = row.createCell(j);
				c.setCellStyle(style);
				c.setCellValue(value);
				j++;
			}
			j = 0;
			i++;
		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		os.close();
		byte[] b = os.toByteArray();

		return b;
	}

}
