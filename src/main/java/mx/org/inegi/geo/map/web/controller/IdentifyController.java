/**
 * 
 */
package mx.org.inegi.geo.map.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.exception.InvalidProjectException;
import mx.org.inegi.geo.map.exception.TableNotFoundException;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.model.IdentifyInfo;
import mx.org.inegi.geo.map.service.GenericService;
import mx.org.inegi.geo.map.sql.creator.IdentifyRasterSqlCreator;
import mx.org.inegi.geo.map.sql.creator.IdentifySqlCreator;
import mx.org.inegi.geo.map.sql.creator.SqlCreator;
import mx.org.inegi.geo.map.web.response.ResponseFactory;
import mx.org.inegi.geo.map.web.response.ResponseFactory.SuccessfulResponse;
import mx.org.inegi.geo.map.web.response.TableResponse;
import mx.org.inegi.geo.map.xml.loader.ServerData;
import mx.org.inegi.geo.map.xml.model.Resolution;
import mx.org.inegi.geo.map.xml.model.Table;

/**
 * @author femat
 *
 */
@RestController
@RequestMapping("identify")
public class IdentifyController {

	private static final Logger logger = Logger
			.getLogger(IdentifyController.class);

	@Autowired
	private GenericService service;

	@Resource(name = "defaultLayers")
	private List<String> defaultLayers;

	@Autowired
	private ServerData serverData;

	@GzipResponse
	@RequestMapping(method = RequestMethod.POST)
	public Object identify(@Valid @RequestBody IdentifyInfo identifyInfo)
			throws Exception {
		logger.info("Identify...");
		String project = identifyInfo.getProject();
		List<TableResponse> r = new ArrayList<TableResponse>();
		short nonDefaultItems = 0;
		Double resolution = identifyInfo.getResolution();
		List<String> tables = addDefaultTables(identifyInfo.getTables());
		for (String table : tables) {
			try {
				if (isWithinBounds(table, project, resolution) && nonDefaultItems < 1) {
					TableResponse ir = process(table, identifyInfo);
					if (ir != null) {
						r.add(ir);
						if (!defaultLayers.contains(table)) {
							nonDefaultItems += 1;
						}
					}
				} else if (defaultLayers.contains(table)
						&& isWithinBounds(table, project, resolution)) {
					TableResponse ir = process(table, identifyInfo);
					if (ir != null) {
						r.add(ir);
					}
				}
			} catch (TableNotFoundException e) {
				logger.warn(e.getMessage());
			}
		}
		SuccessfulResponse sr = ResponseFactory.successfulResponse("tables", r);
		return sr;
	}

	private TableResponse process(String table, IdentifyInfo identifyInfo)
			throws Exception {
		String project = identifyInfo.getProject();
		Table t = serverData.findTable(table, project);
		SqlCreator sqlCreator = null;
		String sql = null;
		if (t.isRaster()) {
			sqlCreator = new IdentifyRasterSqlCreator(identifyInfo, t);
		} else {
			sqlCreator = new IdentifySqlCreator(identifyInfo, t);
		}
		sql = sqlCreator.getSql();
		ConnectionContextHolder.setConnectionInfo(table, project, true);
		List<Map<String, Object>> response = service.select(sql);
		if (response.size() > 0) {
			TableResponse ir = new TableResponse(t, response);
			return ir;
		}
		return null;
	}

	private boolean isWithinBounds(String table, String project, Double resolution)
			throws TableNotFoundException, InvalidProjectException {
		Table t = serverData.findTable(table, project);
		Resolution r = t.getResolution();
		boolean b = r.isWithinBounds(resolution);
		return b;
	}

	private List<String> addDefaultTables(List<String> tables) {
		for (String layer : defaultLayers) {
			for (String table : tables) {
				if (table.equalsIgnoreCase(layer)) {
					tables.remove(table);
					break;
				}
			}
		}
		tables.addAll(defaultLayers);
		return tables;
	}

}
