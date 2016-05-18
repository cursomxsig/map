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
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author Aaron.Villar
 */
public class GetDataFromCluster {

	private String bbox;
	private String xy[];
	private String wyh[];
	private String fcount = null;
	protected URL url;
	protected int responseCode = 0;
	private Gson g = new Gson();
	private final int neww = 450;

	private String getURL(boolean esfinal) {

		StringBuilder sb = new StringBuilder();
		sb.append("http://10.152.11.5/fcgi-bin/ms62/mapserv.exe?map=/opt/map/mdm60/denue-aaw-1.map&SERVICE=WMS&SRS=EPSG:900913&VERSION=1.1.1&REQUEST=GetFeatureInfo&LAYERS=cdenue14&STYLES=&FORMAT=image/png&TRANSPARENT=true&QUERY_LAYERS=cdenue14");
		sb.append("&BBOX=").append(bbox);
		sb.append("&WIDTH=").append(wyh[0]);
		sb.append("&HEIGHT=").append(wyh[1]);
		sb.append("&X=").append(xy[0]);
		sb.append("&Y=").append(xy[1]);
		if (esfinal) {
			sb.append("&FEATURE_COUNT=").append(fcount);
			sb.append("&INFO_FORMAT=application/vnd.ogc.gml");

		} else {
			sb.append("&INFO_FORMAT=text%2Fhtml");
		}
		return sb.toString();
	}

	public PointFeature getPointFeature(String bbox, String xy, String WidthYHeight) {
		String salida = null;
		PointFeature pf = new PointFeature();
		try {
			ajustaTamanio(bbox, xy, WidthYHeight);
			fcount = null;
			salida = procesa();
			Type listType = new TypeToken<List<PointFeature>>() {
			}.getType();
			List<PointFeature> target2 = g.fromJson(salida, listType);
			int cuantosInt = 0;
			if (target2 != null && target2.size() == 1) {
				cuantosInt = target2.get(0).getCount();
			}
			fcount = cuantosInt + "";
			salida = fcount;
			
			pf.setCount(salida);
			
			if (cuantosInt > 0) {
				salida = procesa();
				String[] m_xy = salida.split("gml:coordinates");
				String[] m_xy2 = m_xy[1].substring(1, m_xy[1].length() - 2)
						.split(" ");
				salida = m_xy2[0];
				pf.setPoint(salida);
			} else {
				salida = "0,";
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return pf;
	}

	private void ajustaTamanio(String bbox1, String xy1, String WidthYHeight) {
		String[] bboxs = bbox1.split(",");
		double[] bboxi = new double[4];
		int[] xyi = new int[2];
		int[] wyhi = new int[2];
		this.bbox = bbox1;
		this.xy = xy1.split(",");
		this.wyh = WidthYHeight.split(",");

		for (int i = 0; i < 4; i++) {
			bboxi[i] = Double.parseDouble(bboxs[i]);
			if (i < 2) {
				xyi[i] = Integer.parseInt(xy[i]);
				wyhi[i] = Integer.parseInt(wyh[i]);
			}
		}
		double wg = bboxi[2] - bboxi[0];
		double res = Math.abs(wg / wyhi[0]);

		double newcenterx = bboxi[0] + (res * xyi[0]);
		double newcentery = bboxi[1] + (res * (wyhi[1] - xyi[1]));

		double newx1 = newcenterx - ((res * neww) / 2);
		double newx2 = newcenterx + ((res * neww) / 2);

		double rel = (double) wyhi[1] / (double) wyhi[0];

		double newy1 = newcentery - ((res * (int) ((double) neww * rel)) / 2);
		double newy2 = newcentery + ((res * (int) ((double) neww * rel)) / 2);

		this.bbox = newx1 + "," + newy1 + "," + newx2 + "," + newy2;
		this.xy[0] = "225";
		this.xy[1] = ((int) ((double) neww * rel) / 2) + "";
		this.wyh[0] = neww + "";
		this.wyh[1] = ((int) ((double) neww * rel)) + "";

	}

	private String procesa() throws MalformedURLException, IOException {
		url = new URL(getURL(fcount == null ? false : true));
		String salida = null;
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
				salida = sb.toString();
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
		return salida;
	}

}
