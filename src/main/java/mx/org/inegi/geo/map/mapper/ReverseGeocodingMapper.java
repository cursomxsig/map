/**
 * 
 */
package mx.org.inegi.geo.map.mapper;

import mx.org.inegi.geo.map.domain.Ageb;
import mx.org.inegi.geo.map.domain.FrenteManzana;
import mx.org.inegi.geo.map.domain.NumeroExterior;

/**
 * @author femat
 *
 */
public interface ReverseGeocodingMapper {

	public Ageb findAgeb(String point);

	public FrenteManzana findFrenteManzana(String point);

	public NumeroExterior findNumeroExterior(String point);

}
