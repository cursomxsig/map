/**
 * 
 */
package mx.org.inegi.geo.map.service;

import mx.org.inegi.geo.map.model.Point;

/**
 * @author femat
 *
 */
public interface RasterElevationService {

	public double findElevation(Point point);

}
