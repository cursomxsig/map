package mx.org.inegi.geo.map.model;

import java.util.Arrays;

public class ListDenueInfo implements ResolutionLevel {

	private String polygon;

	private double resolution;

	private String centroid;

	private String type;

	private int resolutionLevel;

	public String getPolygon() {
		return polygon;
	}

	public void setPolygon(String polygon) {
		this.polygon = polygon;
	}

	public double getResolution() {
		return resolution;
	}

	public void setResolution(double resolution) {
		this.resolution = resolution;
		if (Arrays.asList(level1).contains(resolution)) {
			this.resolutionLevel = 1;
			this.type = "localidad";
		} else if (Arrays.asList(level2).contains(resolution)) {
			this.resolutionLevel = 2;
			this.type = "localidad";
		} else if (Arrays.asList(level3).contains(resolution)) {
			this.resolutionLevel = 3;
			this.type = "localidad";
		} else if (Arrays.asList(level4).contains(resolution)) {
			this.resolutionLevel = 4;
			this.type = "localidad";
		} else if (Arrays.asList(level5).contains(resolution)) {
			this.resolutionLevel = 5;
			this.type = "municipal";
		} else if (Arrays.asList(level6).contains(resolution)) {
			this.resolutionLevel = 6;
			this.type = "estatal";
		} else if (Arrays.asList(level7).contains(resolution)) {
			this.resolutionLevel = 7;
			this.type = "nacional";
		} else {
			this.resolutionLevel = 0;
			this.type = "";
		}
	}

	public String getCentroid() {
		return centroid;
	}

	public void setCentroid(String centroid) {
		this.centroid = centroid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int getResolutionLevel() {
		return resolutionLevel;
	}

}
