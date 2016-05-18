package mx.org.inegi.geo.map.mapper;

import java.util.List;

import mx.org.inegi.geo.map.domain.ListDenue;
import mx.org.inegi.geo.map.model.ListDenueInfo;

public interface DenueMapper {

	public List<ListDenue> getListEstatal(ListDenueInfo info);

	public List<ListDenue> getListMuni(ListDenueInfo info);

	public List<ListDenue> getListLocal(ListDenueInfo info);

	public List<ListDenue> getListAgeb(ListDenueInfo info);

	public List<ListDenue> getListMzn(ListDenueInfo info);

	public List<ListDenue> getListEstable(ListDenueInfo info);

}
