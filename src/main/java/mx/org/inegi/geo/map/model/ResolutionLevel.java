/**
 * 
 */
package mx.org.inegi.geo.map.model;

/**
 * @author femat
 *
 */
public interface ResolutionLevel {

	Double[] level1 = { 0.298582141, 0.597164283, 1.194328566 };
	Double[] level2 = { 2.388657133 };
	Double[] level3 = { 4.777314266, 9.554628533 };
	Double[] level4 = { 19.109257067, 38.218514135, 76.437028271 };
	Double[] level5 = { 152.874056542, 305.748113085 };
	Double[] level6 = { 611.496226171, 1222.992452343, 2445.984904687 };
	Double[] level7 = { 4891.969809375 };

	public int getResolutionLevel();

}
