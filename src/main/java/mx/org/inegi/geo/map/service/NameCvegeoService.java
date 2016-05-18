package mx.org.inegi.geo.map.service;

import java.util.List;

import mx.org.inegi.geo.map.domain.NameCvegeo;

public interface NameCvegeoService {

	public List<NameCvegeo> cvName(String ent, String mun, String Loc); 
	public String geoName(String tabla, String cvegeo); 
	public String extent(String tabla, String cvegeo); 
}
 
