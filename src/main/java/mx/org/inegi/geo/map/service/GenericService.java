/**
 * 
 */
package mx.org.inegi.geo.map.service;

import java.util.List;
import java.util.Map;

/**
 * @author femat
 *
 */
public interface GenericService {

	public int selectCount(String sql);

	public Map<String, Object> selectOne(String sql);

	public List<Map<String, Object>> select(String sql);

}
