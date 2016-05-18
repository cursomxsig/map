/**
 * 
 */
package mx.org.inegi.geo.map.web.controller;

import java.util.Map;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.service.GenericService;
import mx.org.inegi.geo.map.sql.creator.GeometryByCvegeoSqlCreator;
import mx.org.inegi.geo.map.sql.creator.GeometryByPointSqlCreator;
import mx.org.inegi.geo.map.sql.creator.SqlCreator;
import mx.org.inegi.geo.map.xml.loader.ServerData;
import mx.org.inegi.geo.map.xml.model.Table;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author femat
 *
 */
@RestController
@RequestMapping("geometry")
public class GeometryController {

	private static final Logger logger = Logger
			.getLogger(GeometryController.class);

	@Autowired
	private GenericService service;

	@Autowired
	private ServerData serverData;

	@GzipResponse
	@RequestMapping("point")
	public Object point(@RequestParam String point, @RequestParam String alias,
			@RequestParam String project) throws Exception {
		logger.info("Find geometry by point");
		Table table = serverData.findTable(alias, project);
		SqlCreator sqlCreator = new GeometryByPointSqlCreator(table, point);
		String sql = sqlCreator.getSql();
		ConnectionContextHolder.setConnectionInfo(alias, project, true);
		Map<String, Object> response = service.selectOne(sql);
		return response;
	}

	@GzipResponse
	@RequestMapping("cvegeo")
	public Object cvegeo(@RequestParam String cvegeo,
			@RequestParam String alias, @RequestParam String project)
			throws Exception {
		logger.info("Find geometry by cvegeo");
		Table table = serverData.findTable(alias, project);
		SqlCreator sqlCreator = new GeometryByCvegeoSqlCreator(table, cvegeo);
		String sql = sqlCreator.getSql();
		ConnectionContextHolder.setConnectionInfo(alias, project, true);
		Map<String, Object> response = service.selectOne(sql);
		return response;
	}

}
