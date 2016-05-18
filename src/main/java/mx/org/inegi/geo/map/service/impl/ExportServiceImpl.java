/**
 * 
 */
package mx.org.inegi.geo.map.service.impl;

import java.util.HashMap;
import java.util.Map;

import mx.org.inegi.geo.map.mapper.ExportMapper;
import mx.org.inegi.geo.map.service.ExportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author femat
 *
 */
@Service
public class ExportServiceImpl implements ExportService {

	@Autowired
	private ExportMapper mapper;

	@Override
	@Transactional
	public int add(String json) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", 0);
		data.put("json", json);
		mapper.add(data);
		int id = (Integer) data.get("id");
		return id;
	}

	@Override
	public String findById(int id) {
		String json = mapper.findById(id);
		return json;
	}

}
