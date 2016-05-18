/**
 * 
 */
package mx.org.inegi.geo.map.mapper;

import java.util.List;

import mx.org.inegi.geo.map.domain.Sector;

/**
 * @author femat
 *
 */
public interface SectorMapper {

	public List<Sector> findByParent(int parent);

}
