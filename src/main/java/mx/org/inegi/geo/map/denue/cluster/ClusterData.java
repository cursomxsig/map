package mx.org.inegi.geo.map.denue.cluster;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author Aaron.Villar
 */
public class ClusterData {

	protected URL url;
	protected int responseCode = 0;

	private String bbox;
	
	private String featureCount = null;

	private int x;
	private int y;
	
	private int width;
	private int height;
	
	private Gson gson = new Gson();
	private final int newWidth = 450;

	private String getURL(boolean isFinal) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://10.152.11.6/fcgi-bin/ms62/mapserv.exe?map=/opt/map/mdm60/denue-aaw-1.map&SERVICE=WMS&SRS=EPSG:900913&VERSION=1.1.1&REQUEST=GetFeatureInfo&LAYERS=cdenue14&STYLES=&FORMAT=image/png&TRANSPARENT=true&QUERY_LAYERS=cdenue14&INFO_FORMAT=text%2Fhtml");
		sb.append("&BBOX=").append(bbox);
		sb.append("&WIDTH=").append(width);
		sb.append("&HEIGHT=").append(height);
		sb.append("&X=").append(x);
		sb.append("&Y=").append(y);
		if (isFinal) {
			sb.append("&FEATURE_COUNT=").append(featureCount);
		}
		return sb.toString();
	}

	public int getCount(String bbox, int x, int y, int width, int height) {
		String salida = null;
		int cuantosInt = 0;
		try {
			resize(bbox, x, y, width, height);
			featureCount = null;
			salida = process();
			Type type = new TypeToken<Feature>() {
			}.getType();
			Feature target = gson.fromJson(salida, type);
			if (target != null) {
				cuantosInt = Integer.parseInt(target.getCount());
			}
			featureCount = cuantosInt + "";
			salida = featureCount;

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return cuantosInt;
	}

	private void resize(String bbox, int x, int y, int width, int height) {
		String[] bboxs = bbox.split(",");
		double[] bbox_array = new double[4];
		this.bbox = bbox;

		for (int i = 0; i < 4; i++) {
			bbox_array[i] = Double.parseDouble(bboxs[i]);
		}
		double wg = bbox_array[2] - bbox_array[0];
		double res = Math.abs(wg / width);

		double xCentroid = bbox_array[0] + (res * x);
		double yCentroid = bbox_array[1] + (res * (height - y));

		double x1 = xCentroid - ((res * newWidth) / 2);
		double x2 = xCentroid + ((res * newWidth) / 2);

		double rel = (double) height / (double) width;

		double y1 = yCentroid - ((res * (int) ((double) newWidth * rel)) / 2);
		double y2 = yCentroid + ((res * (int) ((double) newWidth * rel)) / 2);

		this.bbox = x1 + "," + y1 + "," + x2 + "," + y2;
		this.x = 225;
		this.y = ((int) ((double) newWidth * rel) / 2);
		this.width = newWidth;
		this.height = ((int) ((double) newWidth * rel));

	}

	private String process() throws MalformedURLException, IOException {
		url = new URL(getURL(featureCount == null ? false : true));
		String response = null;
		InputStream is = null;
		URLConnection conn = null;
		OutputStream out = null;
		try {
			conn = url.openConnection();
			conn.connect();
			if (conn instanceof HttpURLConnection) {
				HttpURLConnection urlConn = (HttpURLConnection) conn;
				urlConn.setConnectTimeout(1000);
				urlConn.setReadTimeout(1000);
				responseCode = urlConn.getResponseCode();
			}
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				String inputLine;
				StringBuilder sb = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					sb.append(inputLine);
				}
				in.close();
				int a = sb.lastIndexOf(",");
				if (sb.length() > 0) {
					sb.delete(a, a + 1);
				}
				response = sb.toString().replace("[", "").replace("]", "");
			}
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException io) {
				io.printStackTrace();
			}
		}
		return response;
	}

}
