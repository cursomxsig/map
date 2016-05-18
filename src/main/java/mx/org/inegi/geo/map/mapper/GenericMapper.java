/**
 * 
 */
package mx.org.inegi.geo.map.mapper;

import java.util.List;

/**
 * @author femat
 *
 */
public interface GenericMapper<T> {
	
	public int selectCount(String sql);

	public T selectOne(String sql);

	public List<T> select(String sql);

}
