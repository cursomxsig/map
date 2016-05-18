package mx.org.inegi.geo.map.mapper;

import java.util.List;

import mx.org.inegi.geo.map.domain.NameCvegeo;
import mx.org.inegi.geo.map.model.NameCvegeoInfo;

public interface NameMapper {
	
	public String geoName(String tabla, String cvegeo); 
	
	public List<NameCvegeo> entName();
	
	public List<NameCvegeo> munName(String ent);
	
	public List<NameCvegeo> locName(String ent, String mun);
	
	public List<NameCvegeo> agebName(String ent, String mun, String loc);
	
	public String extent(String tabla, String cvegeo); 
	
}
 
