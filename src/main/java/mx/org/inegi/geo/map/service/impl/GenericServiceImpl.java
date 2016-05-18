/**
 * 
 */
package mx.org.inegi.geo.map.service.impl;

import java.util.List;
import java.util.Map;

import mx.org.inegi.geo.map.mapper.GenericMapper;
import mx.org.inegi.geo.map.service.GenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author femat
 *
 */
@Service
public class GenericServiceImpl implements GenericService {

	@Autowired
	private GenericMapper<Map<String, Object>> mapper;

	@Override
	public int selectCount(String sql) {
		int count = mapper.selectCount(sql);
		return count;
	}

	@Override
	public Map<String, Object> selectOne(String sql) {
		Map<String, Object> result = mapper.selectOne(sql);
		return result;
	}

	@Override
	public List<Map<String, Object>> select(String sql) {
		List<Map<String, Object>> result = mapper.select(sql);
		return result;
	}

}
