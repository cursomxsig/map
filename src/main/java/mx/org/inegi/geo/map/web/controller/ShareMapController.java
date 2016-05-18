package mx.org.inegi.geo.map.web.controller;

import java.util.Map;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.domain.Share;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.model.Mailinfo;
import mx.org.inegi.geo.map.service.ShareMapService;
import mx.org.inegi.geo.map.web.response.ResponseFactory;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("share")
public class ShareMapController {
	private static final Logger logger = Logger
			.getLogger(ShareMapController.class);

	@Autowired
	ShareMapService service;

	private final String DEFAULT_TABLE = "share";

	@GzipResponse
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object add(@RequestBody JSONObject json) {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, false);
		Share share = new Share();
		share.setJson(json);
		Long id = service.add(share);
		return ResponseFactory.successfulResponse("id", id);
	}

	@GzipResponse
	@RequestMapping(value = "find/{id}", method = RequestMethod.POST)
	public Object find(@PathVariable int id) {
		logger.info("Retrieving map...");
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, true);
		Map<String, Object> json = service.find(id);

		if (json != null) {
			PGobject data = (PGobject) json.get("json");
			return ResponseFactory.successfulResponse("json", data.getValue());
		} else {
			return ResponseFactory
					.unsuccessfulResponse("No se encuentra el id");
		}
	}

	@GzipResponse
	@RequestMapping(value = "email", method = RequestMethod.POST)
	public Object simplemail(@RequestBody Mailinfo info) {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, true);
		service.emailSimpleMail(info);
		return ResponseFactory.successfulResponse("Correo Enviado");
	}

	@RequestMapping(value = "email/2", method = RequestMethod.POST)
	public Object emailSpring(@RequestBody Mailinfo info) {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, true);
		service.emailSpring(info);
		return ResponseFactory.successfulResponse("Correo Enviado");
	}

}
