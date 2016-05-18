/**
 * 
 */
package mx.org.inegi.geo.map.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mx.org.inegi.geo.map.model.Field;
import mx.org.inegi.geo.map.model.Fields;
import mx.org.inegi.geo.map.xml.model.Table;

/**
 * @author femat
 *
 */
public class QueryTotalsReport {

	public QueryTotalsReport(Table table, Fields totals,
			List<Map<String, Object>> elements) {
		super();
		this.table = table;
		this.totals = totals;
		this.elements = elements;
	}

	private Table table;

	private Fields totals;

	private List<Map<String, Object>> elements;

	public ByteArrayOutputStream generate() throws IOException {
		Workbook wb = new XSSFWorkbook();

		CellStyle style1 = wb.createCellStyle();
		Font font1 = wb.createFont();
		font1.setFontName("Calibri");
		font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font1.setFontHeightInPoints((short) 10);
		style1.setFont(font1);

		CellStyle style2 = wb.createCellStyle();
		Font font2 = wb.createFont();
		font2.setFontName("Calibri");
		font2.setFontHeightInPoints((short) 10);
		style2.setFont(font2);

		Sheet sheet = wb.createSheet("Datos");

		if (elements != null) {
			int i = 0;
			short pos = 0;
			Row row2 = null;
			boolean row2created = false;
			for (Map<String, Object> map : elements) {
				Row row = sheet.createRow(i);
				short j = 0;
				for (Map.Entry<String, Object> e : map.entrySet()) {
					if (!e.getKey().equalsIgnoreCase("id") && pos == 0) {
						String v = e.getKey();
						if (v.equalsIgnoreCase("nombre")) {
							v = table.getUserAlias();
						}
						Cell label = row.createCell(j);
						label.setCellStyle(style1);
						label.setCellValue(v);
						if (!row2created) {
							int z = i + 1;
							row2 = sheet.createRow(z);
							row2created = true;
							i++;
						}
						Cell value = row2.createCell(j);
						value.setCellStyle(style2);
						value.setCellValue(String.valueOf(e.getValue()));
						j++;
					} else if (!e.getKey().equalsIgnoreCase("id") && pos > 0) {
						Cell value = row.createCell(j);
						value.setCellStyle(style2);
						value.setCellValue(String.valueOf(e.getValue()));
						j++;
					}
				}
				pos++;
				i++;
			}
		}

		if (totals != null) {
			Sheet sheet2 = wb.createSheet("Totales");
			short i = 0;
			List<Field> fields= totals.getFields();
			for (Field e : fields) {
				Row row = sheet2.createRow(i);
				Cell label = row.createCell(0);
				label.setCellStyle(style1);
				sheet2.autoSizeColumn(0);
				label.setCellValue(e.getLabel());
				Cell value = row.createCell(1);
				value.setCellStyle(style2);
				if (e.getValue() instanceof String) {
					value.setCellType(Cell.CELL_TYPE_STRING);
					value.setCellValue((String) e.getValue());
				} else if (e.getValue() instanceof BigDecimal) {
					value.setCellType(Cell.CELL_TYPE_NUMERIC);
					double v = ((BigDecimal) e.getValue()).doubleValue();
					value.setCellValue(v);
				}
				i++;
			}
		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		os.close();
		return os;
	}

}
