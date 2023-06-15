package com.app.model;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author 119107
 *
 */
public class PriceTransparencyListRequest {

	private String HospitalName;
	
	private String LastUpdatedDate;

	private String Version;
	
	private String HospitalLocation;
	
	private String HospitalLicenseNumber;

	private String State;

	public ArrayList<PriceTransparencyRequest> getValue() {
		return value;
	}

	public void setValue(ArrayList<PriceTransparencyRequest> value) {
		this.value = value;
	}

	private String HospitalFinancialAidPolicy;
	

	public String getHospitalFinancialAidPolicy() {
		return HospitalFinancialAidPolicy;
	}

	public void setHospitalFinancialAidPolicy(String hospitalFinancialAidPolicy) {
		this.HospitalFinancialAidPolicy = hospitalFinancialAidPolicy;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	private ArrayList<PriceTransparencyRequest> value;


	public String getLastUpdatedDate() {
		return LastUpdatedDate;
	}

	public void setLastUpdatedDate(String lastUpdatedDate) {
		LastUpdatedDate = lastUpdatedDate;
	}

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public String getHospitalLocation() {
		return HospitalLocation;
	}

	public void setHospitalLocation(String hospitalLocation) {
		HospitalLocation = hospitalLocation;
	}

	public String getHospitalName() {
		return HospitalName;
	}

	public void setHospitalName(String hospitalName) {
		HospitalName = hospitalName;
	}
	
	public String getHospitalLicenseNumber() {
		return HospitalLicenseNumber;
	}

	public void setHospitalLicenseNumber(String hospitalLicenseNumber) {
		HospitalLicenseNumber = hospitalLicenseNumber;
	}

}
