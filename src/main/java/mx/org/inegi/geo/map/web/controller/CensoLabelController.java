package mx.org.inegi.geo.map.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.domain.CensoLabel;
import mx.org.inegi.geo.map.model.CensoLabelGraphicInfo;
import mx.org.inegi.geo.map.model.CensoLabelInfo;
import mx.org.inegi.geo.map.model.Field;
import mx.org.inegi.geo.map.model.Fields;
import mx.org.inegi.geo.map.service.CensoLabelService;
import mx.org.inegi.geo.map.utils.DenueUtils;
import mx.org.inegi.geo.map.web.response.ResponseFactory;

@RestController
@RequestMapping("censo")
public class CensoLabelController {
	private final String DEFAULT_TABLE = "c100";

	@Autowired
	CensoLabelService service;

	@RequestMapping(value = "label/gransector", method = RequestMethod.POST)
	public Object labelGranSector(@RequestBody CensoLabelInfo info) {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, true);
		CensoLabel label = new CensoLabel();
		Map<String, Object> geographical = new LinkedHashMap<String, Object>();
		Map<String, Object> economical = new LinkedHashMap<String, Object>();
		if (info.isNational()) {
			geographical = service.geographicalState(info);
			if (geographical != null) {
				String cvegeo = getCvegeo(geographical);
				economical = service.granEconomicalState(cvegeo, info.getId(), info.getTypeSector(), info.getYear());
			}
		} else {
			geographical = service.geographicalMun(info);
			if (geographical != null) {
				String cvegeo = getCvegeoMun(geographical);
				economical = service.granEconomicalMun(cvegeo, info.getId(), info.getTypeSector(), info.getYear());
			}
		}
		if (geographical != null) {
			DenueUtils util = new DenueUtils();
			label.setEconomical(util.convertToField(economical));
			label.setGeographical(util.convertToField(geographical));
			return label;
		} else {
			return ResponseFactory.unsuccessfulResponse("No se encuentra información");
		}

	}

	@RequestMapping(value = "label/graph/gransector", method = RequestMethod.POST)
	public Object labelGraphGranSector(@Valid @RequestBody CensoLabelGraphicInfo info) {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, true);
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		if (info.isNational()) {
			Double indicatorNational = service.getIndicatorNational(info);
			data.put("IndicatorNational", indicatorNational);
			List<Map<String, Object>> states = new ArrayList<Map<String, Object>>();
			states = service.getSates(info);
			Collection<Fields> elements = createElements(states);
			data.put("elements", elements);
		} else {
			Double indicatorNational = service.getIndicatorNational(info);
			data.put("IndicatorNational", indicatorNational);
			Double indicatorEst = service.getIndicatorEst(info);
			data.put("IndicatorState", indicatorEst);
			int positionState = service.getPositionState(info);
			data.put("PositionState", positionState);
			List<Map<String, Object>> mun = new ArrayList<Map<String, Object>>();
			mun = service.getMun(info);
			Collection<Fields> elements = createElements(mun);
			data.put("elements", elements);
		}
		DenueUtils util = new DenueUtils();
		Collection<Field> label = util.convertToField(data);
		return label;

	}

	@RequestMapping(value = "find", method = RequestMethod.POST)
	public Object findByGeom(@RequestParam(value = "point") String point, @RequestParam(value = "ent") String ent) {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, "mdm6", true);
		Map<String, Object> clave = service.find(point, ent);
		return ResponseFactory.successfulResponse("values", clave);
	}

	public String getCvegeo(Map<String, Object> response) {
		String cvegeo = "";
		for (Map.Entry<String, Object> e : response.entrySet()) {
			if (e.getKey().equals("Clave"))
				cvegeo = String.valueOf(e.getValue());
		}
		return cvegeo;
	}

	public String getCvegeoMun(Map<String, Object> response) {
		String mun = "";
		String ent = "";
		for (Map.Entry<String, Object> e : response.entrySet()) {
			if (e.getKey().equals("Clave"))
				mun = String.valueOf(e.getValue());
			if (e.getKey().equals("Clave_ent"))
				ent = String.valueOf(e.getValue());
		}
		return ent + mun;
	}

	private Collection<Fields> createElements(List<Map<String, Object>> list) {
		Collection<Fields> elements = new ArrayList<Fields>();
		for (Map<String, Object> map : list) {
			Fields fields = new Fields();
			for (Map.Entry<String, Object> e : map.entrySet()) {
				fields.add(e);
			}
			elements.add(fields);
		}
		return elements;
	}

}
