/**
 * 
 */
package mx.org.inegi.geo.map.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.model.Fields;
import mx.org.inegi.geo.map.model.QueryTotalsInfo;
import mx.org.inegi.geo.map.service.BufferService;
import mx.org.inegi.geo.map.service.GenericService;
import mx.org.inegi.geo.map.sql.creator.QueryTotalsCountSqlCreator;
import mx.org.inegi.geo.map.sql.creator.QueryTotalsSqlCreator;
import mx.org.inegi.geo.map.sql.creator.SqlCreator;
import mx.org.inegi.geo.map.sql.creator.TotalsSqlCreator;
import mx.org.inegi.geo.map.utils.Pagination;
import mx.org.inegi.geo.map.web.response.ResponseFactory;
import mx.org.inegi.geo.map.web.response.ResponseFactory.SuccessfulResponse;
import mx.org.inegi.geo.map.xml.loader.ServerData;
import mx.org.inegi.geo.map.xml.model.Table;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author femat
 *
 */
@RestController
@RequestMapping("totals")
public class TotalsController {

	private static final Logger logger = Logger
			.getLogger(TotalsController.class);

	private final String PROJECT = "mdm6";

	private final String BUFFER_TABLE = "geometrias";

	@Autowired
	private BufferService bufferService;

	@Autowired
	private GenericService service;

	@Autowired
	private ServerData serverData;

	@Autowired
	private Pagination pagination;

	@GzipResponse
	@RequestMapping(method = RequestMethod.POST)
	public Object queryTotals(
			@Valid @RequestBody QueryTotalsInfo queryTotalsInfo)
			throws Exception {
		logger.info("Totals...");

		ConnectionContextHolder.setConnectionInfo(BUFFER_TABLE, PROJECT, true);
		Long id = Long.parseLong(queryTotalsInfo.getPolygon());
		String buffer = bufferService.findBufferById(id);

		String t = queryTotalsInfo.getTable();
		String project = queryTotalsInfo.getProject();
		Table table = serverData.findTable(t, project);
		String userAlias = table.getUserAlias();
		String alias = table.getAlias();

		/* Pagination process */
		SqlCreator countSqlCreator = new QueryTotalsCountSqlCreator(
				queryTotalsInfo, table, buffer);
		String countSql = countSqlCreator.getSql();
		ConnectionContextHolder.setConnectionInfo(t, project, true);
		int recordCount = service.selectCount(countSql);
		int limit = queryTotalsInfo.getLimit();
		int pages = pagination.getNumberOfPages(recordCount, limit);
		int currentPage = pagination.validatePage(queryTotalsInfo.getPage(),
				pages);
		int offset = pagination.getOffset(currentPage, limit);

		Collection<Fields> elements = getElements(queryTotalsInfo, buffer,
				limit, offset);
		Fields totals = getTotals(queryTotalsInfo, table, buffer);
		SuccessfulResponse sr = ResponseFactory
				.successfulResponse("table", userAlias)
				.addField("alias", alias).addField("recordCount", recordCount)
				.addField("currentPage", currentPage).addField("pages", pages)
				.addField("elements", elements).addField("totals", totals);
		return sr;
	}

	private Fields getTotals(QueryTotalsInfo queryTotalsInfo, Table t,
			String polygon) throws Exception {
		String table = queryTotalsInfo.getTable();
		String project = queryTotalsInfo.getProject();
		if (t.getTotals() != null) {
			String defaultSql = t.getTotals().getSql();
			HashMap<String, String> columns = t.getTotals().getColumns();
			SqlCreator sqlCreator = new TotalsSqlCreator(defaultSql,
					queryTotalsInfo, polygon);
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

	private Collection<Fields> getElements(QueryTotalsInfo queryTotalsInfo,
			String polygon, int limit, int offset) throws Exception {
		String project = queryTotalsInfo.getProject();
		String table = queryTotalsInfo.getTable();
		Table t = serverData.findTable(table, project);
		SqlCreator sqlCreator = new QueryTotalsSqlCreator(queryTotalsInfo, t,
				polygon, limit, offset);
		String sql = sqlCreator.getSql();
		ConnectionContextHolder.setConnectionInfo(table, project, true);
		List<Map<String, Object>> response = service.select(sql);
		if (response.size() > 0) {
			Collection<Fields> elements = createElements(response);
			return elements;
		}
		return null;
	}

	private Collection<Fields> createElements(List<Map<String, Object>> list) {
		Collection<Fields> elements = new ArrayList<Fields>();
		for (Map<String, Object> map : list) {
			Fields fields = new Fields();
			for (Map.Entry<String, Object> e : map.entrySet()) {
				fields.add(e);
			}
			elements.add(fields);
		}
		return elements;
	}

}
