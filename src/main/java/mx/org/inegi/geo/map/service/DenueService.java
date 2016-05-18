package mx.org.inegi.geo.map.service;

import java.util.List;

import mx.org.inegi.geo.map.domain.ListDenue;
import mx.org.inegi.geo.map.model.ListDenueInfo;

public interface DenueService {
	public List<ListDenue> getList(ListDenueInfo info);

}
