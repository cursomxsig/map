/**
 * 
 */
package mx.org.inegi.geo.map.web.controller;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.domain.Ageb;
import mx.org.inegi.geo.map.domain.FrenteManzana;
import mx.org.inegi.geo.map.domain.NumeroExterior;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.model.GeometryPoint;
import mx.org.inegi.geo.map.service.ReverseGeocodingService;
import mx.org.inegi.geo.map.web.response.ResponseFactory;
import mx.org.inegi.geo.map.web.response.ResponseFactory.SuccessfulResponse;
import mx.org.inegi.geo.map.web.response.ResponseFactory.UnsuccessfulResponse;

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
@RequestMapping("/reversegeocoding")
public class ReverseGeocodingController {

	@Autowired
	private ReverseGeocodingService service;

	@GzipResponse
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public Object process(@RequestBody GeometryPoint geometryPoint)
			throws Exception {
		ConnectionContextHolder.setConnectionInfo("geometrias", true);
		Object result = service.georeferencing(geometryPoint.getPoint());
		if (result == null) {
			String error = "Domicilio fuera de los límites de la manzana.";
			UnsuccessfulResponse response = ResponseFactory
					.unsuccessfulResponse(error);
			return response;
		} else {
			SuccessfulResponse response;
			response = ResponseFactory.successfulResponse("value", result);
			if (result instanceof NumeroExterior) {
				response.addField("tipo", "NE").addField("descripcion",
						"Número exterior.");
			} else if (result instanceof FrenteManzana) {
				response.addField("tipo", "FM").addField("descripcion",
						"Frente de manzana.");
			} else if (result instanceof Ageb) {
				response.addField("tipo", "AGEB").addField("descripcion",
						"Área Geoestadística Básica.");
			}
			return response;
		}
	}

}
