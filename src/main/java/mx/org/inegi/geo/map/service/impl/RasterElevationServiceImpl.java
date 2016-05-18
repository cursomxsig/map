/**
 * 
 */
package mx.org.inegi.geo.map.service.impl;

import mx.org.inegi.geo.map.mapper.RasterElevationMapper;
import mx.org.inegi.geo.map.model.Point;
import mx.org.inegi.geo.map.service.RasterElevationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author femat
 *
 */
@Service
public class RasterElevationServiceImpl implements RasterElevationService {

	@Autowired
	private RasterElevationMapper mapper;

	@Override
	public double findElevation(Point point) {
		String p = point.getPoint();
		String ent = mapper.findEnt(p);
		double elevation = -32767;
		if (ent != null) {
			elevation = mapper.findElevation(ent, p);
		}
		return elevation;
	}

}
