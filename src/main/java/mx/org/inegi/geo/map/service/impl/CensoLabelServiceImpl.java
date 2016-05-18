package mx.org.inegi.geo.map.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.org.inegi.geo.map.mapper.CensoLabelMapper;
import mx.org.inegi.geo.map.model.CensoLabelGraphicInfo;
import mx.org.inegi.geo.map.model.CensoLabelInfo;
import mx.org.inegi.geo.map.service.CensoLabelService;

@Service
public class CensoLabelServiceImpl implements CensoLabelService {
	@Autowired
	CensoLabelMapper mapper;

	@Override
	public Map<String, Object> geographicalState(CensoLabelInfo info) {
		return mapper.geographicalState(info);
	}

	@Override
	public Map<String, Object> geographicalMun(CensoLabelInfo info) {
		return mapper.geographicalMun(info);
	}

	@Override
	public Map<String, Object> granEconomicalState(String cvegeo, int granSector, int typeSector, String year) {
		return mapper.granEconomicalState(cvegeo, granSector, typeSector, year);
	}

	@Override
	public Map<String, Object> granEconomicalMun(String cvegeo, int granSector, int typeSector, String year) {
		return mapper.granEconomicalMun(cvegeo, granSector, typeSector, year);
	}

	@Override
	public Map<String, Object> sectorEconomicalState(String cvegeo, int id) {
		return mapper.sectorEconomicalState(cvegeo, id);
	}

	@Override
	public Map<String, Object> sectorEconomicalMun(String cvegeo, int id) {
		return mapper.sectorEconomicalMun(cvegeo, id);
	}

	@Override
	public Map<String, Object> subSectorEconomicalState(String cvegeo, int id) {
		return mapper.subSectorEconomicalState(cvegeo, id);
	}

	@Override
	public Map<String, Object> subSectorEconomicalMun(String cvegeo, int id) {
		return mapper.subSectorEconomicalMun(cvegeo, id);
	}

	@Override
	public Double getIndicatorNational(CensoLabelGraphicInfo info) {
		return mapper.getIndicatorNational(info);
	}

	@Override
	public Double getIndicatorEst(CensoLabelGraphicInfo info) {
		return mapper.getIndicatorEst(info);
	}

	@Override
	public List<Map<String, Object>> getSates(CensoLabelGraphicInfo info) {
		return mapper.getSates(info);
	}

	@Override
	public List<Map<String, Object>> getMun(CensoLabelGraphicInfo info) {
		return mapper.getMun(info);
	}

	@Override
	public Map<String, Object> find(String point, String ent) {
		return mapper.find(point, ent);
	}

	@Override
	public int getPositionState(CensoLabelGraphicInfo info) {
		return mapper.getPositionState(info);
	}

}
