package mx.org.inegi.geo.map.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import mx.org.inegi.geo.map.model.CensoLabelGraphicInfo;
import mx.org.inegi.geo.map.model.CensoLabelInfo;

public interface CensoLabelMapper {

	Map<String, Object> geographicalState(CensoLabelInfo info);

	Map<String, Object> geographicalMun(CensoLabelInfo info);

	Map<String, Object> granEconomicalState(@Param("cvegeo") String cvegeo,
			@Param("granSector") int granSector,
			@Param("typeSector") int typeSector,
			@Param("year")  String year);
	

	Map<String, Object> granEconomicalMun(@Param("cvegeo") String cvegeo,
			@Param("granSector") int granSector,
			@Param("typeSector") int typeSector, 
			@Param("year") String year);

	Map<String, Object> sectorEconomicalState(@Param("cvegeo") String cvegeo,
			@Param("granSector") int granSector);

	Map<String, Object> sectorEconomicalMun(@Param("cvegeo") String cvegeo,
			@Param("granSector") int granSector);

	Map<String, Object> subSectorEconomicalState(
			@Param("cvegeo") String cvegeo, @Param("granSector") int granSector);

	Map<String, Object> subSectorEconomicalMun(@Param("cvegeo") String cvegeo,
			@Param("granSector") int granSector);

	Double getIndicatorNational(CensoLabelGraphicInfo info);

	Double getIndicatorEst(CensoLabelGraphicInfo info);

	Map<String, Object> getSates();

	List<Map<String, Object>> getSates(CensoLabelGraphicInfo info);

	List<Map<String, Object>> getMun(CensoLabelGraphicInfo info);

	Map<String, Object> find(@Param("point") String point,
			@Param("ent") String ent);

	int getPositionState(CensoLabelGraphicInfo info);

}
