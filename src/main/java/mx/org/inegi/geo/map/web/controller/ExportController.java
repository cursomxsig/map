/**
 * 
 */
package mx.org.inegi.geo.map.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.model.ExportXls;
import mx.org.inegi.geo.map.report.CsvReport;
import mx.org.inegi.geo.map.report.PdfReport;
import mx.org.inegi.geo.map.report.XlsReport;
import mx.org.inegi.geo.map.service.ExportService;
import mx.org.inegi.geo.map.web.response.ResponseFactory;
import mx.org.inegi.geo.map.web.response.ResponseFactory.SuccessfulResponse;

/**
 * @author femat
 *
 */
@RestController
@RequestMapping("export")
public class ExportController {

	private static final Logger logger = Logger
			.getLogger(ExportController.class);

	@Autowired
	private ExportService service;



	private final String DEFAULT_TABLE = "share";

	private HttpHeaders getXlsHeaders(String filename) {
		HttpHeaders headers = new HttpHeaders();
		MediaType excelMediaType = new MediaType("application", "vnd.ms-excel");
		headers.setContentType(excelMediaType);
		headers.setDate(System.currentTimeMillis());
		headers.setContentDispositionFormData("attachment", filename);
		return headers;
	}

	private HttpHeaders getCsvHeaders(String filename) {
		HttpHeaders headers = new HttpHeaders();
		MediaType excelMediaType = new MediaType("text", "csv");
		headers.setContentType(excelMediaType);
		headers.setDate(System.currentTimeMillis());
		headers.setContentDispositionFormData("attachment", filename);
		return headers;
	}

	private HttpHeaders getPdfHeaders(String filename) {
		HttpHeaders headers = new HttpHeaders();
		MediaType excelMediaType = new MediaType("application", "pdf");
		headers.setContentType(excelMediaType);
		headers.setDate(System.currentTimeMillis());
		headers.setContentDispositionFormData("attachment", filename);
		return headers;
	}

	@GzipResponse
	@RequestMapping(method = RequestMethod.POST)
	public Object export(@RequestBody ExportXls o) throws Exception {
		logger.info("Export...");
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, false);
		String json = new Gson().toJson(o);
		int id = service.add(json);
		SuccessfulResponse sr = ResponseFactory.successfulResponse("id", id);
		return sr;
	}

	@RequestMapping(value = "xls/{id}", method = RequestMethod.GET)
	public Object xls(@PathVariable int id) throws Exception {
		logger.info("Exporting xls...");
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, false);
		String json = service.findById(id);
		ExportXls o = new Gson().fromJson(json, ExportXls.class);
		XlsReport xls = new XlsReport(o);
		byte[] data = xls.generate();
		String filename = o.getTitle() + ".xlsx";
		HttpHeaders headers = getXlsHeaders(filename);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(data,
				headers, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "csv/{id}", method = RequestMethod.GET)
	public Object csv(@PathVariable int id) throws Exception {
		logger.info("Exporting csv...");
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, false);
		String json = service.findById(id);
		ExportXls o = new Gson().fromJson(json, ExportXls.class);
		CsvReport csv = new CsvReport(o);
		byte[] data = csv.generate();
		String filename = o.getTitle() + ".csv";
		HttpHeaders headers = getCsvHeaders(filename);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(data,
				headers, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "pdf/{id}", method = RequestMethod.GET)
	public Object pdf(@PathVariable int id) throws Exception {
		logger.info("Exporting pdf...");
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, false);
		String json = service.findById(id);
		ExportXls o = new Gson().fromJson(json, ExportXls.class);
		PdfReport pdf = new PdfReport(o);
		byte[] data = pdf.generate();
		String filename = o.getTitle() + ".pdf";
		HttpHeaders headers = getPdfHeaders(filename);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(data,
				headers, HttpStatus.OK);
		return response;
	}

}
