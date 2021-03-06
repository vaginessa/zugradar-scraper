package org.hisrc.zugradarscraper.geometry.model;


import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LineString extends Geometry<double[][]> {

	private final double length;

	@JsonCreator
	public LineString(@JsonProperty("coordinates") double[][] coordinates) {
		super(coordinates);
		Validate.isTrue(coordinates.length >= 2);
		this.length = calculateLength(coordinates);
	}

	private double calculateLength(double[][] coordinates) {
		double length = 0;
		double currentLon = coordinates[0][0];
		double currentLat = coordinates[0][1];
		for (int index = 1; index < coordinates.length; index++) {
			double nextLon = coordinates[index][0];
			double nextLat = coordinates[index][1];
			double dLon = nextLon - currentLon;
			double dLat = nextLat - currentLat;
			length += Math.sqrt(dLon * dLon + dLat * dLat);
			currentLon = nextLon;
			currentLat = nextLat;
		}
		return length;
	}

	@JsonIgnore
	public double getLength() {
		return length;
	}
}
