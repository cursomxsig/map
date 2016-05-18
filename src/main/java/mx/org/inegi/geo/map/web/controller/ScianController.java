/**
 * 
 */
package mx.org.inegi.geo.map.web.controller;

import java.util.List;

import javax.validation.Valid;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.denue.cluster.GetDataFromCluster;
import mx.org.inegi.geo.map.denue.cluster.PointFeature;
import mx.org.inegi.geo.map.domain.Scian;
import mx.org.inegi.geo.map.domain.ScianCount;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.model.ScianInfo;
import mx.org.inegi.geo.map.service.ScianService;
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
@RequestMapping("denue")
public class ScianController {

	private final String DEFAULT_PROJECT = "denue";

	private final String DEFAULT_TABLE = "cdenue";

	private final String DEFAULT_NAME = "root";

	private final String ROOT_COLOR = "#CB6120";

	@Autowired
	private ScianService service;

	@GzipResponse
	@RequestMapping(value = "scian", method = RequestMethod.POST)
	public Object scian(@Valid @RequestBody ScianInfo scianInfo)
			throws Exception {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE,
				DEFAULT_PROJECT);
		int size = 0;
		String label = "";
		List<Scian> children = null;
		if (scianInfo.getResolutionLevel() > 1) {
			ScianCount sc = service.countByResolutionLevel(scianInfo);
			size = sc.getSize();
			label = sc.getName();
			children = service.findByResolutionLevel(scianInfo);
		} else {
			GetDataFromCluster c = new GetDataFromCluster();
			PointFeature pf = c.getPointFeature(scianInfo.getBbox(),
					scianInfo.getX() + "," + scianInfo.getY(),
					scianInfo.getWidth() + "," + scianInfo.getHeight());
			String ent = service.findEnt(pf.getPoint());
			scianInfo.setEnt(ent);
			scianInfo.setPoint(pf.getPoint());
			scianInfo.setLimit(pf.getCount());
			size = pf.getCount();
			// size = service.count(scianInfo);
			children = service.find(scianInfo);
		}
		SuccessfulResponse r = ResponseFactory
				.successfulResponse("name", DEFAULT_NAME)
				.addField("label", label).addField("color", ROOT_COLOR)
				.addField("size", size).addField("children", children);
		return r;
	}

	@GzipResponse
	@RequestMapping(value = "scian/detail", method = RequestMethod.POST)
	public Object scianDetail(@Valid @RequestBody ScianInfo scianInfo)
			throws Exception {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE,
				DEFAULT_PROJECT);
		int size = 0;
		List<Scian> children = null;
		if (scianInfo.getResolutionLevel() > 1) {
			size = service.countDetailByResolutionLevel(scianInfo);
			children = service.findDetailByResolutionLevel(scianInfo);
		} else {
			size = service.count(scianInfo);
			children = service.findAll(scianInfo);
		}
		SuccessfulResponse r = ResponseFactory
				.successfulResponse("name", DEFAULT_NAME)
				.addField("color", ROOT_COLOR).addField("size", size)
				.addField("children", children);
		return r;
	}

}