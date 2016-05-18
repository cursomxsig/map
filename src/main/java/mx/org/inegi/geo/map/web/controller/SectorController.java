package mx.org.inegi.geo.map.web.controller;

import java.util.List;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.domain.Sector;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.service.SectorService;
import mx.org.inegi.geo.map.web.response.ResponseFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sector")
public class SectorController {

	private final String DEFAULT_TABLE = "cdenue";

	@Autowired
	private SectorService service;

	@GzipResponse
	@RequestMapping(value = "{parent}", method = RequestMethod.GET)
	public Object tracking(@PathVariable int parent) {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, true);
		List<Sector> sectors = service.findByParent(parent);
		if (sectors.size() > 0) {
			return ResponseFactory.successfulResponse("sectors", sectors);
		} else {
			return ResponseFactory
					.unsuccessfulResponse("No se encontraron sectores.");
		}

	}

}
