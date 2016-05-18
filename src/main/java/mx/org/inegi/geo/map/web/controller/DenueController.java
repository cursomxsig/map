package mx.org.inegi.geo.map.web.controller;

import java.util.List;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.domain.ListDenue;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.model.ListDenueInfo;
import mx.org.inegi.geo.map.service.DenueService;
import mx.org.inegi.geo.map.web.response.ResponseFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("denue")
public class DenueController {
	private final String DEFAULT_TABLE = "cdenue";

	@Autowired
	DenueService service;

	@GzipResponse
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public Object getList(@RequestBody ListDenueInfo info) {
		// info.setType(getType(info.getResolution()));
		if (!(info.getResolutionLevel() == 0)) {
			ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, true);
			List<ListDenue> list = service.getList(info);
			if (list != null)
				return ResponseFactory.successfulResponse("list", list)
						.addField("type", info.getType());
			else
				return ResponseFactory.unsuccessfulResponse();
		} else {
			return ResponseFactory
					.unsuccessfulResponse("La resolucion esta fuera de los rangos permitidos");
		}

	}

}
