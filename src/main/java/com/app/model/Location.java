package com.app.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

public class Location {
	
	private String type;
   

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public List<Double> getCoordinates() {
		return coordinates;
	}


	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}


	private List<Double> coordinates = new ArrayList<Double>();
    
}
