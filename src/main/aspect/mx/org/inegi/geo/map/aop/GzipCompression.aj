package mx.org.inegi.geo.map.aop;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.web.response.ResponseFactory.UnsuccessfulResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public aspect GzipCompression {

	pointcut response(): execution(@GzipResponse public * mx.org.inegi.geo.map.web.controller.*.*(..));

	Object around(): response() {
		Object o = proceed();
		ObjectMapper mapper = new ObjectMapper();
		String tmp;
		try {
			tmp = mapper.writeValueAsString(o);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(bos, tmp.length());
			gzip.write(tmp.getBytes("UTF-8"));
			gzip.close();
			ResponseEntity<byte[]> r = new ResponseEntity<byte[]>(
					bos.toByteArray(), getJsonHeaders(), HttpStatus.OK);
			return r;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return new UnsuccessfulResponse("json processing error.");

		} catch (IOException e) {
			e.printStackTrace();
			return new UnsuccessfulResponse("Internal proccessing error.");
		}
	}

	private HttpHeaders getJsonHeaders() {
		HttpHeaders headers = new HttpHeaders();
		MediaType MediaType = new MediaType("application", "json");
		headers.setContentType(MediaType);
		headers.set("Content-Encoding", "gzip");
		return headers;
	}

}
