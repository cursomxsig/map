/**
 * 
 */
package mx.org.inegi.geo.map.report;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import mx.org.inegi.geo.map.model.ExportXls;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author femat
 *
 */
public class PdfReport {

	ExportXls o;
	Font tituloFont = FontFactory.getFont("Times-Roman", 18, Font.BOLD
			| Font.UNDERLINE, Color.lightGray);

	public PdfReport(ExportXls o) {
		super();
		this.o = o;
	}

	public byte[] generate() throws IOException {

		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			// OutputStream file = new FileOutputStream(new
			// File("SamplePDF.pdf"));
			Document document = new Document();
			PdfWriter.getInstance(document, bos);
			document.open();

			Paragraph titulo = new Paragraph("Lista de Resultados", tituloFont);
			titulo.setAlignment(Element.ALIGN_CENTER);
			document.add(titulo);

			document.add(Chunk.NEWLINE);

			PdfPTable pdfTable = new PdfPTable(o.getColumns().size());

			for (String title : o.getColumns()) {
				PdfPCell cell1 = new PdfPCell(new Phrase(title));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setBackgroundColor(Color.LIGHT_GRAY);
				pdfTable.addCell(cell1);
			}
			pdfTable.setHeaderRows(1);

			for (List<String> registro : o.getValues()) {
				for (String dato : registro) {
					pdfTable.addCell(dato);
				}

			}
			document.add(pdfTable);
			document.close();
			// file.close();
			// bos.writeTo(file);
			bos.close();
			System.out.println("Finalizado");
			return bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
