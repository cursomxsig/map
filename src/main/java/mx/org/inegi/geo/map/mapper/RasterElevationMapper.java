/**
 * 
 */
package mx.org.inegi.geo.map.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author femat
 *
 */
public interface RasterElevationMapper {

	public String findEnt(String point);

	public double findElevation(@Param("ent") String ent,
			@Param("point") String point);

}
