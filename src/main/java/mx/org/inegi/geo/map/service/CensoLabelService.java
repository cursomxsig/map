package mx.org.inegi.geo.map.service;

import java.util.List;
import java.util.Map;

import mx.org.inegi.geo.map.model.CensoLabelGraphicInfo;
import mx.org.inegi.geo.map.model.CensoLabelInfo;

public interface CensoLabelService {

	Map<String, Object> geographicalState(CensoLabelInfo info);

	Map<String, Object> geographicalMun(CensoLabelInfo info);

	Map<String, Object> granEconomicalState(String cvegeo, int granSector, int typeSector, String year);

	Map<String, Object> granEconomicalMun(String cvegeo, int granSector, int typeSector, String year);

	Map<String, Object> sectorEconomicalState(String cvegeo, int id);

	Map<String, Object> sectorEconomicalMun(String cvegeo, int id);

	Map<String, Object> subSectorEconomicalState(String cvegeo, int id);

	Map<String, Object> subSectorEconomicalMun(String cvegeo, int id);

	Double getIndicatorNational(CensoLabelGraphicInfo info);

	Double getIndicatorEst(CensoLabelGraphicInfo info);

	List<Map<String, Object>> getSates(CensoLabelGraphicInfo info);

	List<Map<String, Object>> getMun(CensoLabelGraphicInfo info);

	Map<String, Object> find(String point, String ent);

	int getPositionState(CensoLabelGraphicInfo info);

}
