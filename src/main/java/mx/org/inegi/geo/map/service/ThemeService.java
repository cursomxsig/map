/**
 * 
 */
package mx.org.inegi.geo.map.service;

import java.util.Map;

import mx.org.inegi.geo.map.domain.Theme;
import mx.org.inegi.geo.map.model.ColorUpdate;
import mx.org.inegi.geo.map.model.ThemeInfo;

/**
 * @author femat
 *
 */
public interface ThemeService {

	public int findType(int sector);

	public Map<String, Object> findMinAndMax(ThemeInfo themeInfo, int type);

	public double findIndicator(ThemeInfo themeInfo, int type);

	public Theme add(ThemeInfo themeInfo, int type);
	
	public boolean updateColors(ColorUpdate cu);
	
	public String[] findCevegeo(String min, String max, String tabla, String variable, int sector, String ent);

	public String cName(String Tabla, String cvegeo); 
}
 
