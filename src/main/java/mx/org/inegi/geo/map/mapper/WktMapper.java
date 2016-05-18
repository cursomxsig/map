/**
 * 
 */
package mx.org.inegi.geo.map.mapper;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import mx.org.inegi.geo.map.domain.WKT;
import mx.org.inegi.geo.map.model.PointResolution;

import org.apache.ibatis.annotations.Param;

/**
 * @author femat
 *
 */
public interface WktMapper {

	public LinkedList<WKT> select(String sql);

	public LinkedHashMap<String, Object> findGeometryByCvegeo(
			@Param("cvegeo") String cvegeo);

	public String findFeaturePolygon(PointResolution pr);

}
