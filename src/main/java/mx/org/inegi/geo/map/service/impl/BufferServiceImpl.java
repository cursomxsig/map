/**
 * 
 */
package mx.org.inegi.geo.map.service.impl;

import mx.org.inegi.geo.map.domain.Buffer;
import mx.org.inegi.geo.map.mapper.BufferMapper;
import mx.org.inegi.geo.map.service.BufferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author femat
 *
 */
@Service
public class BufferServiceImpl implements BufferService {

	@Autowired
	private BufferMapper mapper;

	@Override
	public String findBufferById(Long id) {
		String b = mapper.findBufferById(id);
		return b;
	}

	@Override
	public Buffer findBuffer(Buffer buffer) {
		Buffer b = mapper.findBuffer(buffer);
		return b;
	}

	@Override
	@Transactional
	public Long insertGeometry(Buffer buffer) {
		mapper.insertGeometry(buffer);
		Long id = buffer.getId();
		return id;
	}

	@Override
	@Transactional
	public Buffer insertBuffer(Buffer buffer) {
		mapper.insertBuffer(buffer);
		Long id = buffer.getId();
		Buffer b = mapper.simplify(id);
		return b;
	}

}
