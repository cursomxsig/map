/**
 * 
 */
package mx.org.inegi.geo.map.service.impl;

import java.util.List;

import mx.org.inegi.geo.map.domain.Scian;
import mx.org.inegi.geo.map.domain.ScianCount;
import mx.org.inegi.geo.map.mapper.ScianMapper;
import mx.org.inegi.geo.map.model.ScianInfo;
import mx.org.inegi.geo.map.service.ScianService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author femat
 *
 */
@Service
public class ScianServiceImpl implements ScianService {

	@Autowired
	private ScianMapper mapper;

	@Override
	public int count(ScianInfo scianInfo) {
		return mapper.count(scianInfo);
	}

	@Override
	public int countDetailByResolutionLevel(ScianInfo scianInfo) {
		return mapper.countDetailByResolutionLevel(scianInfo);
	}

	@Override
	public ScianCount countByResolutionLevel(ScianInfo scianInfo) {
		return mapper.countByResolutionLevel(scianInfo);
	}

	@Override
	public List<Scian> find(ScianInfo scianInfo) {
		return mapper.find(scianInfo);
	}

	@Override
	public List<Scian> findAll(ScianInfo scianInfo) {
		return mapper.findAll(scianInfo);
	}

	@Override
	public List<Scian> findByResolutionLevel(ScianInfo scianInfo) {
		return mapper.findByResolutionLevel(scianInfo);
	}

	@Override
	public List<Scian> findDetailByResolutionLevel(ScianInfo scianInfo) {
		return mapper.findDetailByResolutionLevel(scianInfo);
	}

	@Override
	public String findEnt(String point) {
		return mapper.findEnt(point);
	}

}
