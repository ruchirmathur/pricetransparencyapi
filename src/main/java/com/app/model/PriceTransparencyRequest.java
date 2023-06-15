package com.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class PriceTransparencyRequest implements java.io.Serializable {
	/**
	 * 
	 */
	private static long serialVersionUID = 1L;
	
	private String HospitalId;

	private String MedicalCode;
	
	private Type MedicalCodeTypeCode;
	
	private String Description;
	
	private BillingClass BillingClass;
	
	private AdmissionType AdmissionType;
	
	private String DrugUnitOfMeasurement;

	private DrugTypeOfMeasurement DrugTypeOfMeasurement;
	
	private String Modifiers;

	private String HospitalName;
	
	private String StandardChargeWithoutInsurance;
	
	private String StandardChargeWithoutInsuranceWithDiscounts;

	private String StandardChargeWithInsurance;
	
	private String StandardChargeWithInsurancePercentage;
	
	private int Minimum;

	private int Maximum;
	
	private String StandardChargeContractingMethod;
	
	private String AdditionalPayerNotes;
	
	private String AdditionaGenericNotes;
	
	private String PlanName;
	
	private String Payer;
	
	private List<String> AncilliaryService = new ArrayList<String>();
	
	private String LastUpdatedDate;
	
	private Double Rating;
	
	private Location Location;
	
	private Address Address;

	public enum Type {
		CPT("CPT"), HCPCS("HCPS"), ICD("ICD"), MSDRG("MS-DRG"), RDRG("R-DRG");

		private final String label;

		private Type(String label) {
			this.label = label;
		}

		@Override
		public String toString() {
			return label;
		}

	}

	public enum BillingClass {
		Professional, Facility;
	}

	public enum AdmissionType {
		inpatient("inpatient"), outpatient("outpatient"), both("both"), none("-1");

		private final String label;

		private AdmissionType(String label) {
			this.label = label;
		}

		@Override
		public String toString() {
			return label;
		}
	}

	public enum DrugTypeOfMeasurement {
		GR, ME, ML, UN;
	}

	public enum contracting_method {
		capitation, caserate, feeschedule, percent;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long serialVersionUID) {
		PriceTransparencyRequest.serialVersionUID = serialVersionUID;
	}

	public String getHospitalId() {
		return HospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.HospitalId = hospitalId;
	}

	public String getMedicalCode() {
		return MedicalCode;
	}

	public void setMedicalCode(String MedicalCode) {
		this.MedicalCode = MedicalCode;
	}

	public Type getMedicalCodeTypeCode() {
		return MedicalCodeTypeCode;
	}

	public void setMedicalCodeTypeCode(Type MedicalCodeTypeCode) {
		this.MedicalCodeTypeCode = MedicalCodeTypeCode;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	public BillingClass getBillingClass() {
		return BillingClass;
	}

	public void setBillingClass(BillingClass BillingClass) {
		this.BillingClass = BillingClass;
	}

	public AdmissionType getAdmissionType() {
		return AdmissionType;
	}

	public void setAdmissionType(AdmissionType AdmissionType) {
		this.AdmissionType = AdmissionType;
	}

	public String getDrugUnitOfMeasurement() {
		return DrugUnitOfMeasurement;
	}

	public void setDrugUnitOfMeasurement(String DrugUnitOfMeasurement) {
		this.DrugUnitOfMeasurement = DrugUnitOfMeasurement;
	}

	public DrugTypeOfMeasurement getDrugTypeOfMeasurement() {
		return DrugTypeOfMeasurement;
	}

	public void setDrugTypeOfMeasurement(DrugTypeOfMeasurement DrugTypeOfMeasurement) {
		this.DrugTypeOfMeasurement = DrugTypeOfMeasurement;
	}

	public String getModifiers() {
		return Modifiers;
	}

	public void setModifiers(String Modifiers) {
		this.Modifiers = Modifiers;
	}

	public String getHospitalName() {
		return HospitalName;
	}

	public void setHospitalName(String HospitalName) {
		this.HospitalName = HospitalName;
	}

	public String getStandardChargeWithoutInsurance() {
		return StandardChargeWithoutInsurance;
	}

	public void setStandardChargeWithoutInsurance(String StandardChargeWithoutInsurance) {
		this.StandardChargeWithoutInsurance = StandardChargeWithoutInsurance;
	}

	public String getStandardChargeWithoutInsuranceWithDiscounts() {
		return StandardChargeWithoutInsuranceWithDiscounts;
	}

	public void setStandardChargeWithoutInsuranceWithDiscounts(String StandardChargeWithoutInsuranceWithDiscounts) {
		this.StandardChargeWithoutInsuranceWithDiscounts = StandardChargeWithoutInsuranceWithDiscounts;
	}

	public String getStandardChargeWithInsurance() {
		return StandardChargeWithInsurance;
	}

	public void setStandardChargeWithInsurance(String StandardChargeWithInsurance) {
		this.StandardChargeWithInsurance = StandardChargeWithInsurance;
	}

	public String getStandardChargeWithInsurancePercentage() {
		return StandardChargeWithInsurancePercentage;
	}

	public void setStandardChargeWithInsurancePercentage(String StandardChargeWithInsurancePercentage) {
		this.StandardChargeWithInsurancePercentage = StandardChargeWithInsurancePercentage;
	}

	public int getMinimum() {
		return Minimum;
	}

	public void setMinimum(int Minimum) {
		this.Minimum = Minimum;
	}

	public int getMaximum() {
		return Maximum;
	}

	public void setMaximum(int Maximum) {
		this.Maximum = Maximum;
	}

	public String getStandardChargeContractingMethod() {
		return StandardChargeContractingMethod;
	}

	public void setStandardChargeContractingMethod(String StandardChargeContractingMethod) {
		this.StandardChargeContractingMethod = StandardChargeContractingMethod;
	}

	public String getAdditionalPayerNotes() {
		return AdditionalPayerNotes;
	}

	public void setAdditionalPayerNotes(String AdditionalPayerNotes) {
		this.AdditionalPayerNotes = AdditionalPayerNotes;
	}

	public String getAdditionaGenericNotes() {
		return AdditionaGenericNotes;
	}

	public void setAdditionaGenericNotes(String AdditionaGenericNotes) {
		this.AdditionaGenericNotes = AdditionaGenericNotes;
	}

	public String getPlanName() {
		return PlanName;
	}

	public void setPlanName(String PlanName) {
		this.PlanName = PlanName;
	}

	public String getPayer() {
		return Payer;
	}

	public void setPayer(String Payer) {
		this.Payer = Payer;
	}

	public List<String> getAncilliaryService() {
		return AncilliaryService;
	}

	public void setAncilliaryService(List<String> AncilliaryService) {
		this.AncilliaryService = AncilliaryService;
	}

	public String getLastUpdatedDate() {
		return LastUpdatedDate;
	}

	public void setLastUpdatedDate(String LastUpdatedDate) {
		this.LastUpdatedDate = LastUpdatedDate;
	}

	public Double getRating() {
		return Rating;
	}

	public void setRating(Double Rating) {
		this.Rating = Rating;
	}

	public Location getLocation() {
		return Location;
	}

	public void setLocation(Location Location) {
		this.Location = Location;
	}

	public Address getAddress() {
		return Address;
	}

	public void setAddress(Address Address) {
		this.Address = Address;
	}
}
