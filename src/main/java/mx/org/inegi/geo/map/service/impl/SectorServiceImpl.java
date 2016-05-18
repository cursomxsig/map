/**
 * 
 */
package mx.org.inegi.geo.map.service.impl;

import java.util.List;

import mx.org.inegi.geo.map.domain.Sector;
import mx.org.inegi.geo.map.mapper.SectorMapper;
import mx.org.inegi.geo.map.service.SectorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author femat
 *
 */
@Service
public class SectorServiceImpl implements SectorService {

	@Autowired
	private SectorMapper mapper;

	@Override
	public List<Sector> findByParent(int parent) {
		return mapper.findByParent(parent);
	}

}
