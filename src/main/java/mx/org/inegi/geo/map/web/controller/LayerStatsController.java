/**
 * 
 */
package mx.org.inegi.geo.map.web.controller;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.model.LayerStats;
import mx.org.inegi.geo.map.service.LayerStatsService;
import mx.org.inegi.geo.map.web.response.ResponseFactory;
import mx.org.inegi.geo.map.web.response.ResponseFactory.SuccessfulResponse;

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
public class LayerStatsController {

	private final String DEFAULT_PROJECT = "mdm6";

	private final String DEFAULT_TABLE = "geometrias";

	@Autowired
	private LayerStatsService service;

	@GzipResponse
	@RequestMapping(value = "stats/layers", method = RequestMethod.POST)
	public Object getWkt(@RequestBody LayerStats layerStats) throws Exception {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE,
				DEFAULT_PROJECT);
		service.insertLayers(layerStats);
		SuccessfulResponse r = ResponseFactory.successfulResponse();
		return r;
	}

}