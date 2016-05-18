package mx.org.inegi.geo.map.web.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.report.QueryTotalsReport;
import mx.org.inegi.geo.map.service.GenericService;
import mx.org.inegi.geo.map.sql.creator.EnergySqlCreator;
import mx.org.inegi.geo.map.sql.creator.SqlCreator;
import mx.org.inegi.geo.map.xml.loader.ServerData;
import mx.org.inegi.geo.map.xml.model.Table;

@RestController
@RequestMapping("export")
public class ExportZipController {

	@Autowired
	private ServerData serverData;

	@Autowired
	private GenericService service;

	@RequestMapping(value = "layers/zip/xls", method = RequestMethod.GET)
	public Object reportXlsByPolygon(@RequestParam(required = false) String polygon, @RequestParam String prj,
			@RequestParam String t) throws Exception {
		List<String> files = Arrays.asList(t.split(","));
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
		ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);
		for (String file : files) {
			List<Map<String, Object>> elements = getElements(file, prj, polygon);
			if (elements != null) {
				Table table = serverData.findTable(file, prj);
				ByteArrayOutputStream report = new QueryTotalsReport(table, null, elements).generate();
				InputStream isFromFirstData = new ByteArrayInputStream(report.toByteArray());
				zipOutputStream.putNextEntry(new ZipEntry(file + ".xls"));
				IOUtils.copy(isFromFirstData, zipOutputStream);
				isFromFirstData.close();
				zipOutputStream.closeEntry();
			}
		}
		if (zipOutputStream != null) {
			zipOutputStream.finish();
			zipOutputStream.flush();
			IOUtils.closeQuietly(zipOutputStream);
		}
		IOUtils.closeQuietly(bufferedOutputStream);
		IOUtils.closeQuietly(byteArrayOutputStream);
		byte[] data = byteArrayOutputStream.toByteArray();
		String filename = "Energy_info_xls.zip";

		HttpHeaders headers = getHeadersZip(filename);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "layers/zip/csv", method = RequestMethod.GET)
	public Object reportCsvByPolygon(@RequestParam(required = false) String polygon, @RequestParam String prj,
			@RequestParam String t) throws Exception {
		List<String> files = Arrays.asList(t.split(","));
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
		ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);
		for (String file : files) {
			List<Map<String, Object>> elements = getElements(file, prj, polygon);
			if (elements != null) {
				Table table = serverData.findTable(file, prj);
				ByteArrayOutputStream report = new QueryTotalsReport(table, null, elements).generate();
				InputStream isFromFirstData = new ByteArrayInputStream(report.toByteArray());
				zipOutputStream.putNextEntry(new ZipEntry(file + ".csv"));
				IOUtils.copy(isFromFirstData, zipOutputStream);
				isFromFirstData.close();
				zipOutputStream.closeEntry();
			}
		}
		if (zipOutputStream != null) {
			zipOutputStream.finish();
			zipOutputStream.flush();
			IOUtils.closeQuietly(zipOutputStream);
		}
		byte[] data = byteArrayOutputStream.toByteArray();
		String filename = "Energy_info_cvs.zip";
		HttpHeaders headers = getHeadersZip(filename);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
		return response;
	}

	private List<Map<String, Object>> getElements(String alias, String prj, String polygon) throws Exception {
		Table t = serverData.findTable(alias, prj);
		SqlCreator sqlCreator = new EnergySqlCreator(t, polygon);
		String sql = sqlCreator.getSql();
		ConnectionContextHolder.setConnectionInfo(alias, prj, true);
		List<Map<String, Object>> response = service.select(sql);
		if (response.size() > 0) {
			return response;
		}
		return null;
	}

	private HttpHeaders getHeadersXls(String filename) {
		HttpHeaders headers = new HttpHeaders();
		MediaType excelMediaType = new MediaType("application", "vnd.ms-excel");
		headers.setContentType(excelMediaType);
		headers.setDate(System.currentTimeMillis());
		headers.setContentDispositionFormData("attachment", filename);
		return headers;
	}

	private HttpHeaders getHeadersCsv(String filename) {
		HttpHeaders headers = new HttpHeaders();
		MediaType excelMediaType = new MediaType("text", "csv");
		headers.setContentType(excelMediaType);
		headers.setDate(System.currentTimeMillis());
		headers.setContentDispositionFormData("attachment", filename);
		return headers;
	}

	private HttpHeaders getHeadersZip(String filename) {
		HttpHeaders headers = new HttpHeaders();
		MediaType excelMediaType = new MediaType("application", "zip");
		headers.setContentType(excelMediaType);
		headers.setDate(System.currentTimeMillis());
		headers.setContentDispositionFormData("attachment", filename);
		return headers;
	}
}
