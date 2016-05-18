/**
 * 
 */
package mx.org.inegi.geo.map.web.controller;

import java.util.List;
import java.util.Map;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.model.StreetCrossing;
import mx.org.inegi.geo.map.service.GenericService;
import mx.org.inegi.geo.map.sql.creator.SqlCreator;
import mx.org.inegi.geo.map.sql.creator.StreetCrossingSqlCreator;
import mx.org.inegi.geo.map.web.response.ResponseFactory;
import mx.org.inegi.geo.map.web.response.ResponseFactory.UnsuccessfulResponse;
import mx.org.inegi.geo.map.web.response.TableResponse;
import mx.org.inegi.geo.map.xml.loader.ServerData;
import mx.org.inegi.geo.map.xml.model.Table;

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
@RequestMapping("street")
public class StreetController {

	private final String DEFAULT_TABLE = "geocalles";

	@Autowired
	private ServerData serverData;

	@Autowired
	private GenericService service;

	@GzipResponse
	@RequestMapping(value = "crossing", method = RequestMethod.POST)
	public Object find(@RequestBody StreetCrossing sc) throws Exception {
		String project = sc.getProject();
		if (serverData.isValidProject(project)) {
			Table t = serverData.findTable(DEFAULT_TABLE, project);
			SqlCreator sqlCreator = new StreetCrossingSqlCreator(sc, t);
			String sql = sqlCreator.getSql();
			ConnectionContextHolder.setConnectionInfo(t.getName(), project);
			List<Map<String, Object>> response = service.select(sql);
			if (response.size() > 0) {
				TableResponse ir = new TableResponse(t, response);
				return ir;
			}
		}
		UnsuccessfulResponse ur = ResponseFactory.unsuccessfulResponse();
		return ur;
	}

}