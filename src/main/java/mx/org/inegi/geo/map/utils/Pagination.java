/**
 * 
 */
package mx.org.inegi.geo.map.utils;

/**
 * @author femat
 *
 */
public class Pagination {

	public int getNumberOfPages(int recordCount, int limit) {
		int pages = 0;
		if (recordCount == 0) {
			pages = 1;
		} else if ((recordCount % limit) == 0) {
			pages = recordCount / limit;
		} else {
			pages = (recordCount / limit) + 1;
		}
		return pages;
	}

	public int getOffset(int page, int limit) {
		int offset = (page - 1) * limit;
		return offset;
	}

	public int validatePage(int page, int pages) {
		if (page > pages) {
			page = pages;
		} else if (page < 0) {
			page = 1;
		}
		return page;
	}

}
