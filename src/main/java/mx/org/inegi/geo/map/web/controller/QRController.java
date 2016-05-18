package mx.org.inegi.geo.map.web.controller;

import java.io.ByteArrayOutputStream;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QRController {

	@RequestMapping(value = "qr", method = RequestMethod.GET)
	public Object DownloadQr(@RequestParam("text") String text,
			@RequestParam("size") int size) throws Exception {
		ByteArrayOutputStream out = QRCode.from(text).to(ImageType.JPG)
				.withSize(size, size).stream();
		byte[] data = out.toByteArray();
		HttpHeaders headers = getHeadersJPG("QR");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(data,
				headers, HttpStatus.OK);
		return response;
	}

	private HttpHeaders getHeadersJPG(String filename) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		headers.setDate(System.currentTimeMillis());
		headers.setContentDispositionFormData("attachment", filename);
		return headers;
	}

}
