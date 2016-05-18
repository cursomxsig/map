/**
 * 
 */
package mx.org.inegi.geo.map.web.controller;

import java.util.LinkedHashMap;
import java.util.List;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.domain.WKT;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.model.PointResolution;
import mx.org.inegi.geo.map.service.WktService;
import mx.org.inegi.geo.map.sql.creator.SqlCreator;
import mx.org.inegi.geo.map.sql.creator.WktSqlCreator;
import mx.org.inegi.geo.map.web.response.ResponseFactory;
import mx.org.inegi.geo.map.web.response.ResponseFactory.SuccessfulResponse;
import mx.org.inegi.geo.map.web.response.ResponseFactory.UnsuccessfulResponse;
import mx.org.inegi.geo.map.xml.loader.ServerData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author femat
 *
 */
@RestController
@RequestMapping("wkt")
public class WktController {

	private final String DEFAULT_PROJECT = "mdm6";

	private final String DEFAULT_TABLE = "geometrias";

	@Autowired
	private ServerData serverData;

	@Autowired
	private WktService service;

	@GzipResponse
	@RequestMapping(value = "geometries", method = RequestMethod.GET)
	public Object getWkt(@RequestParam String ids) throws Exception {
		SqlCreator sqlCreator = new WktSqlCreator(ids);
		String sql = sqlCreator.getSql();
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE,
				DEFAULT_PROJECT, true);
		List<WKT> response = service.select(sql);
		SuccessfulResponse r = ResponseFactory.successfulResponse("geometries",
				response);
		return r;
	}

	@GzipResponse
	@RequestMapping(value = "{cvegeo}", method = RequestMethod.GET)
	public Object getWktByCvegeo(@PathVariable String cvegeo) throws Exception {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE,
				DEFAULT_PROJECT, true);
		LinkedHashMap<String, Object> fields = null;
		String ne = "POLYGON((-13181079.2543797 1635334.46722463,-13181079.2543797 3858019.19709175,-9652558.16191402 3858019.19709175,-9652558.16191402 1635334.46722463,-13181079.2543797 1635334.46722463))";
		if (cvegeo.length() >= 2 && cvegeo.length() <= 5) {
			if (cvegeo.equals("00"))
				return ResponseFactory.successfulResponse("extent", ne);
			fields = service.findGeometryByCvegeo(cvegeo);
		}
		if (fields != null) {
			return ResponseFactory.successfulResponse("extent",
					fields.get("extent")).addField("geometry",
					fields.get("geometry"));
		} else {
			return ResponseFactory.unsuccessfulResponse("Not available info");
		}
	}

	@GzipResponse
	@RequestMapping(value = "feature", method = RequestMethod.POST)
	public Object getFeature(@RequestBody PointResolution pr) throws Exception {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE,
				DEFAULT_PROJECT, true);
		String polygon = service.findFeaturePolygon(pr);
		if (polygon != null) {
			SuccessfulResponse r = ResponseFactory.successfulResponse(
					"geometry", polygon);
			return r;
		} else {
			UnsuccessfulResponse ur = ResponseFactory
					.unsuccessfulResponse("Not available info");
			return ur;
		}

	}

}