/**
 * 
 */
package mx.org.inegi.geo.map.service;

import java.util.List;

import mx.org.inegi.geo.map.domain.Sector;

/**
 * @author femat
 *
 */
public interface SectorService {

	public List<Sector> findByParent(int parent);

}
