package mx.org.inegi.geo.map.web.controller;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.model.Point;
import mx.org.inegi.geo.map.service.RasterElevationService;
import mx.org.inegi.geo.map.web.response.ResponseFactory;
import mx.org.inegi.geo.map.web.response.ResponseFactory.SuccessfulResponse;
import mx.org.inegi.geo.map.web.response.ResponseFactory.UnsuccessfulResponse;
import mx.org.inegi.geo.map.xml.loader.ServerData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("raster")
public class RasterElevationController {

	@Autowired
	private RasterElevationService service;

	@Autowired
	private ServerData serverData;

	private final String table = "celevacion";

	@GzipResponse
	@RequestMapping(value = "elevation", method = RequestMethod.POST)
	public Object getElevation(@RequestBody Point point) throws Exception {
		ConnectionContextHolder.setConnectionInfo(table);
		double elevation = service.findElevation(point);
		if (elevation == -32767) {
			UnsuccessfulResponse ur = ResponseFactory.unsuccessfulResponse();
			return ur;
		}
		SuccessfulResponse sr = ResponseFactory.successfulResponse("elevation",
				elevation);
		return sr;
	}
}
