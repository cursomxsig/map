/**
 * 
 */
package mx.org.inegi.geo.map.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.domain.Buffer;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.service.BufferService;
import mx.org.inegi.geo.map.web.response.ResponseFactory;
import mx.org.inegi.geo.map.web.response.ResponseFactory.SuccessfulResponse;
import mx.org.inegi.geo.map.xml.loader.ServerData;
import mx.org.inegi.geo.map.xml.model.Table;

/**
 * @author femat
 *
 */
@RestController
public class BufferController {

	private final String DEFAULT_PROJECT = "mdm6";

	private final String DEFAULT_TABLE = "geometrias";

	@Autowired
	private ServerData serverData;

	@Autowired
	private BufferService service;

	@GzipResponse
	@RequestMapping(value = "geometry", method = RequestMethod.POST)
	public Object insertGeometry(@RequestParam("buffer") Buffer buffer) throws Exception {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE,
				DEFAULT_PROJECT);
		Long id = service.insertGeometry(buffer);
		SuccessfulResponse r = ResponseFactory.successfulResponse("id", id);
		return r;
	}

	@GzipResponse
	@RequestMapping(value = "buffer", method = RequestMethod.POST)
	public Object insertBuffer(@RequestBody Buffer buffer) throws Exception {
		Buffer b = null;
		if (buffer.getTable() != null && buffer.getTable() != "") {
			Table t = serverData.findTable(buffer.getTable());
			buffer.setSchema(t.getSchema());
			buffer.setTable(t.getName());
			ConnectionContextHolder.setConnectionInfo(t.getAlias());
			Buffer tmp = service.findBuffer(buffer);
			ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE,
					DEFAULT_PROJECT);
			b = service.insertBuffer(tmp);
			b.setSize(buffer.getSize());
		} else {
			ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE,
					DEFAULT_PROJECT);
			b = service.insertBuffer(buffer);
			b.setSize(buffer.getSize());
		}
		SuccessfulResponse r = ResponseFactory.successfulResponse("buffer", b);
		return r;
	}

}