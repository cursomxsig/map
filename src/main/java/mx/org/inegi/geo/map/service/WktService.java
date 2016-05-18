/**
 * 
 */
package mx.org.inegi.geo.map.service;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import mx.org.inegi.geo.map.domain.WKT;
import mx.org.inegi.geo.map.model.PointResolution;

/**
 * @author femat
 *
 */
public interface WktService {

	public LinkedList<WKT> select(String sql);

	public LinkedHashMap<String, Object> findGeometryByCvegeo(String cvegeo);

	public String findFeaturePolygon(PointResolution pr);

}
