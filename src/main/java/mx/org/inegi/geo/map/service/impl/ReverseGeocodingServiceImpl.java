/**
 * 
 */
package mx.org.inegi.geo.map.service.impl;

import mx.org.inegi.geo.map.domain.Ageb;
import mx.org.inegi.geo.map.domain.FrenteManzana;
import mx.org.inegi.geo.map.domain.NumeroExterior;
import mx.org.inegi.geo.map.mapper.ReverseGeocodingMapper;
import mx.org.inegi.geo.map.service.ReverseGeocodingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author femat
 *
 */
@Service
public class ReverseGeocodingServiceImpl implements ReverseGeocodingService {

	@Autowired
	private ReverseGeocodingMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public Object georeferencing(String point) {
		FrenteManzana fm = mapper.findFrenteManzana(point);
		NumeroExterior ne = null;
		Ageb ageb = null;
		if (fm != null) {
			String puntoEnVialidad = fm.getPunto();
			ne = mapper.findNumeroExterior(puntoEnVialidad);
		} else {
			ageb = mapper.findAgeb(point);
		}

		if (ne != null) {
			return ne;
		} else if (fm != null) {
			return fm;
		} else {
			return ageb;
		}

	}

}
