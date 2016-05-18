package mx.org.inegi.geo.map.mapper;

import mx.org.inegi.geo.map.domain.Vialidad;

public interface TrackingMapper {
	Vialidad getVialidad(String point);

}
