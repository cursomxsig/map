/**
 * 
 */
package mx.org.inegi.geo.map.mapper;

import java.util.Map;

import mx.org.inegi.geo.map.domain.Theme;
import mx.org.inegi.geo.map.model.ThemeInfo;

import org.apache.ibatis.annotations.Param;

/**
 * @author femat
 *
 */
public interface ThemeMapper {

	public int findType(int sector);

	public boolean themeExists(long id);

	public Map<String, Object> findMinAndMax(String sql);

	public double findIndicator(@Param("theme") ThemeInfo themeInfo,
			@Param("table") String table);

	public double findIndicator4Loc(@Param("theme") ThemeInfo themeInfo,
			@Param("table") String table);
	
	public double findIndicator4Ageb(@Param("theme") ThemeInfo themeInfo,
			@Param("table") String table);
	
	public String nei(String sql, int clazz, int stratums);
 
	public String c2r(String sql, int clazz, int stratums);
	
	public String d2r(String sql, int clazz, int stratums);
	
	public String cre(String sql, int clazz, int stratums);
	
	public void add(Theme theme);

	public void updateColors(long id, String s);

	public String[] findCevegeo(String min, String max, String tabla, String variable,int sector);
	
	public String[] findCevegeoMun(String min, String max, String tabla, String variable,int sector, String cveEnt);
	
	public String[] findCevegeoLoc(String min, String max, String tabla, String variable,int sector, String cveEnt, String cveMun);
	
	public String[] findCevegeoAgeb(String min, String max, String tabla, String variable,int sector, String cveEnt, String cveMun, String cveLoc);
	
	public String cName(String Tabla, String cvegeo); 
}
