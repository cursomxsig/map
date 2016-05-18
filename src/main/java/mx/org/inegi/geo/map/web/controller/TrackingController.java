package mx.org.inegi.geo.map.web.controller;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.domain.Vialidad;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.service.TrackingService;
import mx.org.inegi.geo.map.web.response.ResponseFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tracking")
public class TrackingController {
	private final String DEFAULT_TABLE = "c100";

	@Autowired
	TrackingService service;

	@GzipResponse
	@RequestMapping(method = RequestMethod.POST)
	public Object tracking(@RequestBody Vialidad vialidad) {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, true);
		vialidad = service.getVialidad(vialidad.getGeometry());
		if (vialidad == null)
			return ResponseFactory
					.unsuccessfulResponse("No se encuentra ninguna vialidad");
		else
			return ResponseFactory.successfulResponse("Vialidad", vialidad);
	}

}
