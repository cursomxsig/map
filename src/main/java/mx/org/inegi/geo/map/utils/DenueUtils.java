package mx.org.inegi.geo.map.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import mx.org.inegi.geo.map.model.Field;

public class DenueUtils {

	public Collection<Field> convertToField(Map<String, Object> response) {
		Collection<Field> element = new ArrayList<Field>();
		for (Map.Entry<String, Object> e : response.entrySet()) {
			Field field = new Field(e.getKey(), e.getValue());
			element.add(field);
		}
		return element;
	}

	public String getType(double resolution) {
		if (resolution == 4891.969809375)
			return "nacional";
		else if (resolution == 2445.984904687)
			return "estatal";
		else if (resolution == 1222.992452343)
			return "estatal";
		else if (resolution == 611.496226171)
			return "estatal";
		else if (resolution == 305.748113085)
			return "municipal";
		else if (resolution == 152.874056542)
			return "municipal";
		else if (resolution == 76.437028271)
			return "localidad";
		else if (resolution == 38.218514135)
			return "localidad";
		else if (resolution == 19.109257067)
			return "localidad";
		else if (resolution == 9.554628533)
			return "ageb";
		else if (resolution == 4.777314266)
			return "ageb";
		else if (resolution == 2.388657133)
			return "manzana";
		else if (resolution == 1.194328566)
			return "establecimientos";
		else if (resolution == 0.597164283)
			return "establecimientos";
		else if (resolution == 0.298582141)
			return "establecimientos";
		else
			return "";
	}

}
