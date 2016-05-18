/**
 * 
 */
package mx.org.inegi.geo.map.mapper;

import mx.org.inegi.geo.map.domain.Buffer;

/**
 * @author femat
 *
 */
public interface BufferMapper {

	public String findBufferById(Long id);

	public Buffer findBuffer(Buffer buffer);

	public Buffer simplify(Long id);

	public void insertGeometry(Buffer buffer);

	public void insertBuffer(Buffer buffer);

}
