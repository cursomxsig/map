/**
 * 
 */
package mx.org.inegi.geo.map.model;

/**
 * @author femat
 *
 */
public enum ScianType {

	sector, subsector, rama, subrama, clase;

	public String getParent() {
		switch (this) {
		case clase:
			return ScianType.subrama.toString();
		case subrama:
			return ScianType.rama.toString();
		case rama:
			return ScianType.subsector.toString();
		case subsector:
			return ScianType.sector.toString();
		case sector:
			return ScianType.sector.toString();
		default:
			return ScianType.sector.toString();
		}
	}

}
