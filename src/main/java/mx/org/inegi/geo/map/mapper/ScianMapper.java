/**
 * 
 */
package mx.org.inegi.geo.map.mapper;

import java.util.List;

import mx.org.inegi.geo.map.domain.Scian;
import mx.org.inegi.geo.map.domain.ScianCount;
import mx.org.inegi.geo.map.model.ScianInfo;

/**
 * @author femat
 *
 */
public interface ScianMapper {

	public int count(ScianInfo scianInfo);

	public int countDetailByResolutionLevel(ScianInfo scianInfo);

	public ScianCount countByResolutionLevel(ScianInfo scianInfo);

	public List<Scian> find(ScianInfo scianInfo);

	public List<Scian> findAll(ScianInfo scianInfo);

	public List<Scian> findByResolutionLevel(ScianInfo scianInfo);

	public List<Scian> findDetailByResolutionLevel(ScianInfo scianInfo);

	public String findEnt(String point);

}
