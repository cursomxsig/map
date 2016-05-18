/**
 * 
 */
package mx.org.inegi.geo.map.utils;

import java.util.List;

import mx.org.inegi.geo.map.exception.TimeFrameException;

/**
 * @author femat
 *
 */
public class TimeFrameUtil {

	public TimeFrameUtil(List<Integer> timeFrame) {
		this.timeFrame = timeFrame;
	}

	private List<Integer> timeFrame;

	public boolean isValid(Integer year) throws TimeFrameException {
		boolean valid = timeFrame.contains(year) ? true : false;
		if (!valid)
			throw new TimeFrameException(year);
		return valid;
	}

}
