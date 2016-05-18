/**
 * 
 */
package mx.org.inegi.geo.map.service.impl;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import mx.org.inegi.geo.map.domain.WKT;
import mx.org.inegi.geo.map.mapper.WktMapper;
import mx.org.inegi.geo.map.model.PointResolution;
import mx.org.inegi.geo.map.service.WktService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author femat
 *
 */
@Service
public class WktServiceImpl implements WktService {

	@Autowired
	private WktMapper mapper;

	@Override
	public LinkedList<WKT> select(String sql) {
		return mapper.select(sql);
	}

	@Override
	public LinkedHashMap<String, Object> findGeometryByCvegeo(String cvegeo) {
		return mapper.findGeometryByCvegeo(cvegeo);
	}

	@Override
	public String findFeaturePolygon(PointResolution pr) {
		return mapper.findFeaturePolygon(pr);
	}

}
