/**
 * 
 */
package mx.org.inegi.geo.map.web.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.model.QueryInfo;
import mx.org.inegi.geo.map.service.GenericService;
import mx.org.inegi.geo.map.sql.creator.QuerySqlCreator;
import mx.org.inegi.geo.map.sql.creator.SqlCreator;
import mx.org.inegi.geo.map.web.response.ResponseFactory;
import mx.org.inegi.geo.map.web.response.ResponseFactory.SuccessfulResponse;
import mx.org.inegi.geo.map.web.response.TableResponse;
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
@RequestMapping("query")
public class QueryController {

	private static final Logger logger = Logger
			.getLogger(QueryController.class);

	@Autowired
	private GenericService service;

	@Autowired
	private ServerData serverData;

	@GzipResponse
	@RequestMapping(method = RequestMethod.POST)
	public Object query(@Valid @RequestBody QueryInfo queryInfo)
			throws Exception {
		logger.info("QueryInfo...");
		TableResponse tr = process(queryInfo);
		SuccessfulResponse sr = ResponseFactory.successfulResponse("table", tr);
		return sr;
	}

	private TableResponse process(QueryInfo queryInfo) throws Exception {
		String project = queryInfo.getProject();
		String table = queryInfo.getTable();
		Table t = serverData.findTable(table, project);
		SqlCreator sqlCreator = new QuerySqlCreator(queryInfo, t);
		String sql = sqlCreator.getSql();
		ConnectionContextHolder.setConnectionInfo(table, project, true);
		List<Map<String, Object>> response = service.select(sql);
		if (response.size() > 0) {
			TableResponse ir = new TableResponse(t, response);
			return ir;
		}
		return null;
	}

}
