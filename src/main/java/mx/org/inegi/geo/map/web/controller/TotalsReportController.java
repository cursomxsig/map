/**
 * 
 */
package mx.org.inegi.geo.map.web.controller;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
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
import mx.org.inegi.geo.map.model.Fields;
import mx.org.inegi.geo.map.model.GeospatialFunction;
import mx.org.inegi.geo.map.model.QueryTotalsInfo;
import mx.org.inegi.geo.map.report.QueryTotalsReport;
import mx.org.inegi.geo.map.report.QueryTotalsReportCsv;
import mx.org.inegi.geo.map.service.BufferService;
import mx.org.inegi.geo.map.service.GenericService;
import mx.org.inegi.geo.map.sql.creator.QueryTotalsReportSqlCreator;
import mx.org.inegi.geo.map.sql.creator.SqlCreator;
import mx.org.inegi.geo.map.sql.creator.TotalsSqlCreator;
import mx.org.inegi.geo.map.xml.loader.ServerData;
import mx.org.inegi.geo.map.xml.model.Table;

/**
 * @author femat
 *
 */
@RestController
@RequestMapping("report")
public class TotalsReportController {

	private static final Logger logger = Logger
			.getLogger(TotalsReportController.class);

	private final String PROJECT = "mdm6";

	private final String BUFFER_TABLE = "geometrias";

	@Autowired
	private BufferService bufferService;

	@Autowired
	private GenericService service;

	@Autowired
	private ServerData serverData;

	private HttpHeaders getHeaders(String filename) {
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

	private String getBuffer(String polygon) {
		ConnectionContextHolder.setConnectionInfo(BUFFER_TABLE, PROJECT, true);
		Long id = Long.parseLong(polygon);
		String buffer = bufferService.findBufferById(id);
		return buffer;
	}

	@RequestMapping(value = "totals/xls", method = RequestMethod.GET)
	public Object queryTotals(@RequestParam String polygon,
			@RequestParam String prj, @RequestParam String t,
			@RequestParam GeospatialFunction f) throws Exception {
		logger.info("Query...");

		String buffer = getBuffer(polygon);

		QueryTotalsInfo queryTotalsInfo = new QueryTotalsInfo(prj, t, polygon,
				f);
		Table table = serverData.findTable(t, prj);

		Fields totals = getTotals(queryTotalsInfo, table, buffer);
		List<Map<String, Object>> elements = getElements(queryTotalsInfo,
				buffer);
		ByteArrayOutputStream report = new QueryTotalsReport(table, totals,
				elements).generate();
		byte[] data = report.toByteArray();
		String filename = "Analisis.xlsx";
		HttpHeaders headers = getHeaders(filename);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(data,
				headers, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "totals/csv", method = RequestMethod.GET)
	public Object queryTotalsCsv(@RequestParam String polygon,
			@RequestParam String prj, @RequestParam String t,
			@RequestParam GeospatialFunction f) throws Exception {
		logger.info("Query CSV...");

		String buffer = getBuffer(polygon);

		QueryTotalsInfo queryTotalsInfo = new QueryTotalsInfo(prj, t, polygon,
				f);
		List<Map<String, Object>> elements = getElements(queryTotalsInfo,
				buffer);
		Table table = serverData.findTable(t, prj);
		ByteArrayOutputStream report = new QueryTotalsReportCsv(elements, table)
				.generate();
		byte[] data = report.toByteArray();
		String filename = "Analisis.csv";
		HttpHeaders headers = getHeadersCsv(filename);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(data,
				headers, HttpStatus.OK);
		return response;
	}

	private Fields getTotals(QueryTotalsInfo queryTotalsInfo,
			Table t, String buffer) throws Exception {
		String table = queryTotalsInfo.getTable();
		String project = queryTotalsInfo.getProject();
		if (t.getTotals() != null) {
			String defaultSql = t.getTotals().getSql();
			HashMap<String, String> columns = t.getTotals().getColumns();
			SqlCreator sqlCreator = new TotalsSqlCreator(defaultSql,
					queryTotalsInfo, buffer);
			String sql = sqlCreator.getSql();
			ConnectionContextHolder.setConnectionInfo(table, project, true);
			Map<String, Object> map = service.selectOne(sql);
			Fields fields = new Fields();
			if (map != null) {
				for (Map.Entry<String, Object> e : map.entrySet()) {
					String key = e.getKey();
					if (columns.containsKey(key)) {
						fields.add(columns.get(key), e.getValue());
					} else {
						fields.add(e);
					}
				}
			}
			return fields;
		} else {
			return null;
		}
	}

	private List<Map<String, Object>> getElements(
			QueryTotalsInfo queryTotalsInfo, String buffer) throws Exception {
		String project = queryTotalsInfo.getProject();
		String table = queryTotalsInfo.getTable();
		Table t = serverData.findTable(table);
		SqlCreator sqlCreator = new QueryTotalsReportSqlCreator(
				queryTotalsInfo, t, buffer);
		String sql = sqlCreator.getSql();
		ConnectionContextHolder.setConnectionInfo(table, project, true);
		List<Map<String, Object>> response = service.select(sql);
		if (response.size() > 0) {
			return response;
		}
		return null;
	}

}
