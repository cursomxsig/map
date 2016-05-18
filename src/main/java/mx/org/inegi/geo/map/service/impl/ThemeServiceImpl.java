/**
 * 
 */
package mx.org.inegi.geo.map.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.org.inegi.geo.map.domain.Theme;
import mx.org.inegi.geo.map.mapper.ThemeMapper;
import mx.org.inegi.geo.map.model.ColorUpdate;
import mx.org.inegi.geo.map.model.ThemeBoundary;
import mx.org.inegi.geo.map.model.ThemeInfo;
import mx.org.inegi.geo.map.service.ThemeService;
import mx.org.inegi.geo.map.sql.util.SQLElement;
import mx.org.inegi.geo.map.utils.SensorTableUtil;

/**
 * @author femat
 *
 */
@Service
public class ThemeServiceImpl implements ThemeService {

	@Autowired
	private ThemeMapper mapper;

	@Override
	public int findType(int sector) {
		return mapper.findType(sector);
	}

	@Override
	public Map<String, Object> findMinAndMax(ThemeInfo themeInfo, int type) {
		SensorTableUtil stu = new SensorTableUtil(themeInfo, type);
		String table = stu.getSchemaAndTable();
		String sql = getSqlMinMax(themeInfo, table);
		return mapper.findMinAndMax(sql);
	}

	@Override
	@Transactional
	public double findIndicator(ThemeInfo themeInfo, int type) {
		SensorTableUtil stu = new SensorTableUtil(themeInfo, type);
		String table = stu.getTable4Indicator();
		if(themeInfo.getEnt().length()==5)
			return mapper.findIndicator4Loc(themeInfo, table);
		if(themeInfo.getEnt().length()==9)
			return mapper.findIndicator4Ageb(themeInfo, table);
		
		return mapper.findIndicator(themeInfo, table);
	}

	@Override
	@Transactional
	public Theme add(ThemeInfo themeInfo, int type) {
		int stratums = themeInfo.getEstratos();
		String resMapper ="";
		SensorTableUtil stu = new SensorTableUtil(themeInfo, type);
		String table = stu.getSchemaAndTable();
		String sql = getSql(themeInfo, table);
		
		if(themeInfo.getTipoConsulta().equals("nei"))
		resMapper = mapper.nei(sql, 1, stratums);
		
		if(themeInfo.getTipoConsulta().equals("c2r"))
		resMapper = mapper.c2r(sql, 1, stratums);
		
		if(themeInfo.getTipoConsulta().equals("d2r"))
		resMapper = mapper.d2r(sql, 1, stratums);
		
		if(themeInfo.getTipoConsulta().equals("cre"))
		resMapper = mapper.cre(sql, 1, stratums);
		
		//System.out.println("************************************************"+resMapper);
		
		Theme theme = new Theme(themeInfo, table, resMapper);
		mapper.add(theme);
		return theme;
	}

	@Override
	@Transactional
	public boolean updateColors(ColorUpdate cu) {
		boolean success = mapper.themeExists(cu.getId());
		if (success) {
			String when = createWhenClause(cu.getBoundaries(), cu.getVariable());
			mapper.updateColors(cu.getId(), when);
			return success;
		}
		return success;
	}

	private String getSqlMinMax(ThemeInfo themeInfo, String table) {
		StringBuilder sb = new StringBuilder(SQLElement.SELECT);
		sb.append("min(");
		sb.append(themeInfo.getVariable());
		sb.append(") as min,");
		sb.append("max(");
		sb.append(themeInfo.getVariable());
		sb.append(") as max");
		sb.append(SQLElement.FROM);
		sb.append(table);
		sb.append(SQLElement.WHERE);
		sb.append("sector = ");
		sb.append(themeInfo.getSector());
		if (!themeInfo.getEnt().equalsIgnoreCase("00")&&themeInfo.getEnt().length()==2) {
			sb.append(" and ");
			sb.append(table);
			sb.append(".");
			sb.append("cve_ent");
			sb.append("= '");
			sb.append(themeInfo.getEnt());
			sb.append("'");
		}
		if (themeInfo.getEnt().length()==5) {
			sb.append(" and ");
			sb.append(table);
			sb.append(".");
			sb.append("cve_ent");
			sb.append("= '");
			sb.append(themeInfo.genCveEnt());
			sb.append("'");
			sb.append(" and ");
			sb.append(table);
			sb.append(".");
			sb.append("cve_mun");
			sb.append("= '");
			sb.append(themeInfo.genCveMun());
			sb.append("'"); 
		}
		if (themeInfo.getEnt().length()==9) {
			sb.append(" and ");
			sb.append(table);
			sb.append(".");
			sb.append("cve_ent");
			sb.append("= '");
			sb.append(themeInfo.genCveEnt());
			sb.append("'");
			
			sb.append(" and ");
			sb.append(table);
			sb.append(".");
			sb.append("cve_mun");
			sb.append("= '");
			sb.append(themeInfo.genCveMun());
			sb.append("'"); 
			
			sb.append(" and ");
			sb.append(table);
			sb.append(".");
			sb.append("localidad");
			sb.append("= '");
			sb.append(themeInfo.genCveLoc());
			sb.append("'"); 
			
		}
		sb.append(" and ");
		sb.append(themeInfo.getVariable());
		sb.append(" >0");
		System.out.println("11111111111111111111111111111111111111111111  "+sb.toString());
		return sb.toString();
	}

	private String getSql(ThemeInfo themeInfo, String table) {
		StringBuilder sb = new StringBuilder(SQLElement.SELECT);
		sb.append("cvegeo, ");
		sb.append(themeInfo.getVariable());
		sb.append(SQLElement.FROM);
		sb.append(table);
		sb.append(SQLElement.WHERE);
		sb.append("sector = ");
		sb.append(themeInfo.getSector());
		if (!themeInfo.getEnt().equalsIgnoreCase("00")&&themeInfo.getEnt().length()==2) {
			sb.append(" and ");
			sb.append(table);
			sb.append(".");
			sb.append("cve_ent");
			sb.append("= '");
			sb.append(themeInfo.getEnt());
			sb.append("'");
		}
		if (themeInfo.getEnt().length()==5) {
			sb.append(" and ");
			sb.append(table);
			sb.append(".");
			sb.append("cve_ent");
			sb.append("= '");
			sb.append(themeInfo.genCveEnt());
			sb.append("'");
			
			sb.append(" and ");
			sb.append(table);
			sb.append(".");
			sb.append("cve_mun");
			sb.append("= '");
			sb.append(themeInfo.genCveMun());
			sb.append("'");
			
			
		}
		if (themeInfo.getEnt().length()==9) {
			sb.append(" and ");
			sb.append(table);
			sb.append(".");
			sb.append("cve_ent");
			sb.append("= '");
			sb.append(themeInfo.genCveEnt());
			sb.append("'");
			
			sb.append(" and ");
			sb.append(table);
			sb.append(".");
			sb.append("cve_mun");
			sb.append("= '");
			sb.append(themeInfo.genCveMun());
			sb.append("'");
			
			sb.append(" and ");
			sb.append(table);
			sb.append(".");
			sb.append("localidad");
			sb.append("= '");
			sb.append(themeInfo.genCveLoc());
			sb.append("'");
		}
		
		
		System.out.println("****************************"+sb.toString());
		return sb.toString();
	}

	public String createWhenClause(List<ThemeBoundary> boundaries, String variable) {
		// String and = " and ";
		String when = " when ";
		String then = " then ";
		// String lt = "<";
		// String gt = ">";
		// String equals = "=";
		String quote = "'";
		// int i = 0;
		StringBuilder sb = new StringBuilder();
		for (ThemeBoundary b : boundaries) {
			sb.append(when);
			sb.append("b.cvegeo");
			sb.append(" in");
			sb.append("(");
			sb.append(b.getCvegeo());
			sb.append(")");
			sb.append(then);
			sb.append(quote);
			sb.append(b.getRgb());
			sb.append(quote);
		}
		return sb.toString();
	}
	public String genCevegeoD2r(){
		
		
		
		return null;
	}
	
	public String[] findCevegeo(String min, String max, String tabla, String variable, int sector, String ent){
		
		if(ent.equals("00")&&ent.length()==2)
			return mapper.findCevegeo(min, max, tabla, variable,sector);
		if(!ent.equals("00")&&ent.length()==2)
			return mapper.findCevegeoMun(min, max, tabla, variable,sector,ent);
		if(!ent.equals("00")&&ent.length()==5)
			return mapper.findCevegeoLoc(min, max, tabla, variable,sector,ent.substring(0,2),ent.substring(2,5));
		if(!ent.equals("00")&&ent.length()==9)
			return mapper.findCevegeoAgeb(min, max, tabla, variable,sector,ent.substring(0,2),ent.substring(2,5),ent.substring(5,9));
		
		return null;
	}
	public String cName(String Tabla, String cvegeo){
		return mapper.cName(Tabla, cvegeo);
	}
}
 




