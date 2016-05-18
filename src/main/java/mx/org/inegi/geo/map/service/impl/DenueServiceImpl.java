package mx.org.inegi.geo.map.service.impl;

import java.util.ArrayList;
import java.util.List;

import mx.org.inegi.geo.map.domain.ListDenue;
import mx.org.inegi.geo.map.mapper.DenueMapper;
import mx.org.inegi.geo.map.model.ListDenueInfo;
import mx.org.inegi.geo.map.service.DenueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DenueServiceImpl implements DenueService {

	@Autowired
	DenueMapper mapper;

	@Override
	public List<ListDenue> getList(ListDenueInfo info) {
		String type = info.getType();
		List<ListDenue> list = new ArrayList<ListDenue>();
		if (type.equals("nacional")) {
			list.add(new ListDenue("00", ""));
		} else if (type.equals("estatal"))
			list = mapper.getListEstatal(info);
		else if (type.equals("municipal"))
			list = mapper.getListMuni(info);
		else if (type.equals("localidad"))
			list = mapper.getListLocal(info);
		else if (type.equals("ageb"))
			list = mapper.getListAgeb(info);
		else if (type.equals("manzana"))
			list = mapper.getListMzn(info);
		else if (type.equals("estable"))
			list = mapper.getListEstable(info);
		return list;
	}
}
