package mx.org.inegi.geo.map.service.impl;

import mx.org.inegi.geo.map.domain.Vialidad;
import mx.org.inegi.geo.map.mapper.TrackingMapper;
import mx.org.inegi.geo.map.service.TrackingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackingServiceImpl implements TrackingService {

	@Autowired
	TrackingMapper mapper;

	public Vialidad getVialidad(String point) {
		return mapper.getVialidad(point);
	}

}
