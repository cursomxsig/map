package mx.org.inegi.geo.map.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.org.inegi.geo.map.domain.NameCvegeo;
import mx.org.inegi.geo.map.mapper.NameMapper;
import mx.org.inegi.geo.map.service.NameCvegeoService;


@Service
public class NameCvegeoServiceImpl implements NameCvegeoService{

	@Autowired
	public NameMapper mapper;
	
	public List<NameCvegeo> cvName(String ent, String mun, String loc) {
		List<NameCvegeo> ls=new ArrayList<NameCvegeo>();
		if (ent.equals("00")&&mun.equals("")&&loc.equals(""))
			ls=mapper.entName();
		if (!ent.equals("00")&&mun.equals("")&&loc.equals(""))
			ls=mapper.munName(ent);
		if (!ent.equals("00")&&!mun.equals("")&&loc.equals(""))
			ls=mapper.locName(ent,mun);
		if (!ent.equals("00")&&!mun.equals("")&&!loc.equals(""))
			ls=mapper.agebName(ent, mun, loc);
		return ls;	
	}
	public String geoName(String tabla, String cvegeo){
		return mapper.geoName(tabla, cvegeo);
	}
	public String extent(String tabla, String cvegeo){
		return mapper.extent(tabla, cvegeo);
		
	}
} 

