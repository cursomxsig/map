/**
 * 
 */
package mx.org.inegi.geo.map.service;

import mx.org.inegi.geo.map.domain.Buffer;

/**
 * @author femat
 *
 */
public interface BufferService {

	public String findBufferById(Long id);

	public Buffer findBuffer(Buffer buffer);

	public Long insertGeometry(Buffer buffer);

	public Buffer insertBuffer(Buffer buffer);

}
