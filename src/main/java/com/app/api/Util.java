package com.app.api;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.app.model.Address;
import com.app.model.Location;
import com.app.model.PriceTransparencyListRequest;
import com.app.model.PriceTransparencyRequest;
import com.app.model.PriceTransparencyRequest.AdmissionType;
import com.app.model.PriceTransparencyRequest.BillingClass;
import com.app.model.PriceTransparencyRequest.DrugTypeOfMeasurement;
import com.app.model.PriceTransparencyRequest.Type;
import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.storage.blob.sas.BlobContainerSasPermission;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import com.azure.storage.common.StorageSharedKeyCredential;
import com.azure.storage.common.sas.AccountSasPermission;
import com.azure.storage.common.sas.AccountSasResourceType;
import com.azure.storage.common.sas.AccountSasService;
import com.azure.storage.common.sas.AccountSasSignatureValues;
import com.azure.storage.file.datalake.DataLakeDirectoryClient;
import com.azure.storage.file.datalake.DataLakeFileClient;
import com.azure.storage.file.datalake.DataLakeFileSystemClient;
import com.azure.storage.file.datalake.DataLakeFileSystemClientBuilder;
import com.azure.storage.file.datalake.DataLakePathClientBuilder;
import com.azure.storage.file.datalake.DataLakeServiceClient;
import com.azure.storage.file.datalake.DataLakeServiceClientBuilder;
import com.azure.storage.file.datalake.sas.DataLakeServiceSasSignatureValues;
import com.azure.storage.file.datalake.sas.FileSystemSasPermission;
import com.azure.storage.file.datalake.sas.PathSasPermission;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

	public static void main(String args[]) throws IOException, InterruptedException {

		PriceTransparencyListRequest priceTransparencyListRequest = new PriceTransparencyListRequest();

		priceTransparencyListRequest.setHospitalLicenseNumber("12121212212");

		priceTransparencyListRequest.setHospitalName("hospital");

		priceTransparencyListRequest.setState("CA");

		priceTransparencyListRequest.setHospitalFinancialAidPolicy("");

		priceTransparencyListRequest.setHospitalLocation("1000");

		priceTransparencyListRequest.setLastUpdatedDate("01/01/2023");

		priceTransparencyListRequest.setVersion("1.0");

		PriceTransparencyRequest priceTransparencyRequest = new PriceTransparencyRequest();

		priceTransparencyRequest.setAdditionaGenericNotes("Test");

		priceTransparencyRequest.setAdditionalPayerNotes("Test");

		AdmissionType type = AdmissionType.inpatient;

		priceTransparencyRequest.setAdmissionType(type);

		List<String> ancilliaryService = new ArrayList<String>();

		ancilliaryService.add("Blood Test");

		ancilliaryService.add("Lipid Profile");

		priceTransparencyRequest.setAncilliaryService(ancilliaryService);

		BillingClass bill = BillingClass.Facility;

		priceTransparencyRequest.setBillingClass(bill);

		priceTransparencyRequest.setDescription("Cardiology");

		DrugTypeOfMeasurement drug = DrugTypeOfMeasurement.GR;

		priceTransparencyRequest.setDrugTypeOfMeasurement(drug);

		priceTransparencyRequest.setDrugUnitOfMeasurement("22");

		priceTransparencyRequest.setHospitalId("1");

		priceTransparencyRequest.setMaximum(222);

		priceTransparencyRequest.setMinimum(21212);

		priceTransparencyRequest.setModifiers("Test");

		priceTransparencyRequest.setHospitalName(priceTransparencyListRequest.getHospitalName());

		Address address = new Address();

		Location location = new Location();

		List<Double> coordinates = new ArrayList<Double>();

		address.setCity("Fremont");
		address.setStateProvince("CA");
		address.setCountry("USA");
		address.setPostalCode("94538");
		address.setStreetAddress("2000 Mowry Avenue");

		priceTransparencyRequest.setAddress(address);

		location.setType("Point");
		coordinates.add(34.99);
		coordinates.add((double) -1223);
		location.setCoordinates(coordinates);

		priceTransparencyRequest.setLocation(location);

		priceTransparencyRequest.setDescription("Diabetes");

		priceTransparencyRequest.setMedicalCode("646464");

		priceTransparencyRequest.setMedicalCodeTypeCode(Type.CPT);

		priceTransparencyRequest.setStandardChargeWithoutInsuranceWithDiscounts("1133");

		priceTransparencyRequest.setStandardChargeWithInsurancePercentage("2233");

		priceTransparencyRequest.setStandardChargeWithoutInsurance("222");

		priceTransparencyRequest.setStandardChargeWithInsurance("1212");

		priceTransparencyRequest.setMinimum(2222);

		priceTransparencyRequest.setMaximum(21212);

		priceTransparencyRequest.setPayer("Anthem");

		priceTransparencyRequest.setPlanName("Anthem");

		priceTransparencyRequest.setRating(new Double(222));

		priceTransparencyRequest.setStandardChargeContractingMethod("Test");

		ArrayList<PriceTransparencyRequest> priceTransparencyRequestList = new ArrayList<PriceTransparencyRequest>();

		priceTransparencyRequestList.add(priceTransparencyRequest);

		priceTransparencyListRequest.setValue(priceTransparencyRequestList);

		String sasJsonToken = createSASToken(priceTransparencyRequest.getHospitalName() + "json");

		String sasJsonFileToken = createFileSASToken(priceTransparencyRequest.getHospitalName() + "json");

		String sasCSVToken = createSASToken(priceTransparencyRequest.getHospitalName() + "csv");

		String sasCSVFileToken = createFileSASToken(priceTransparencyRequest.getHospitalName() + "csv");

		createCSV(priceTransparencyListRequest, sasCSVToken, sasCSVFileToken);

		createJSON(priceTransparencyListRequest, sasJsonToken, sasJsonFileToken);

	}

	public static void createCSV(PriceTransparencyListRequest priceTransparencyListRequest, String sasToken,
			String fileSasToken) throws IOException {

		CSVPrinter printer = new CSVPrinter(new FileWriter("/home/site/wwwroot/"+priceTransparencyListRequest.getHospitalName() + ".csv"),
				CSVFormat.DEFAULT);

		printer.printRecord("hospital_name", "last_updated_on", "version", "hospital_location", "financial_aid_policy",
				"license_number | state");

		printer.printRecord(priceTransparencyListRequest.getHospitalName(),
				priceTransparencyListRequest.getLastUpdatedDate(), priceTransparencyListRequest.getHospitalLocation(),
				priceTransparencyListRequest.getHospitalFinancialAidPolicy(),
				priceTransparencyListRequest.getHospitalLicenseNumber() + "|"
						+ priceTransparencyListRequest.getState());

		printer.printRecord("description", "code" + "|1", "code |1| CPT", "billing_class", "setting",
				"drug_unit_of_measurement", "drug_type_of_measurement", "modifiers", "standard_charge|gross",
				"standard_charge|discounted_cash", "standard_charge|[payer1]|[plan1]",
				"standard_charge| [payer1] | [plan1] | percent", "standard_charge | min", "standard_charge | max",
				"standard_charge |[ payer1] | [plan1] | contracting_method",
				"additional_payer_notes | [payer1] | [plan1]");

		for (PriceTransparencyRequest priceTransparencyRequest : priceTransparencyListRequest.getValue()) {

			printer.printRecord(priceTransparencyRequest.getDescription(), priceTransparencyRequest.getMedicalCode(),
					priceTransparencyRequest.getMedicalCodeTypeCode(), priceTransparencyRequest.getBillingClass(),
					priceTransparencyRequest.getAdmissionType(), priceTransparencyRequest.getDrugUnitOfMeasurement(),
					priceTransparencyRequest.getDrugTypeOfMeasurement(), priceTransparencyRequest.getModifiers(),
					priceTransparencyRequest.getStandardChargeWithoutInsurance(),
					priceTransparencyRequest.getStandardChargeWithoutInsuranceWithDiscounts(),
					priceTransparencyRequest.getStandardChargeWithInsurance(),
					priceTransparencyRequest.getStandardChargeWithInsurancePercentage(),
					priceTransparencyRequest.getMinimum(), priceTransparencyRequest.getMaximum(),
					priceTransparencyRequest.getMinimum(), priceTransparencyRequest.getMaximum(),
					priceTransparencyRequest.getStandardChargeContractingMethod(),
					priceTransparencyRequest.getAdditionalPayerNotes());

		}

		printer.flush();
		printer.close();
		String csvFilepath = priceTransparencyListRequest.getHospitalName() + ".csv";
		String hospitalName = priceTransparencyListRequest.getHospitalName();
		String folderName = priceTransparencyListRequest.getHospitalName() + "csv";

		createCSVFile(folderName, hospitalName, sasToken, csvFilepath, fileSasToken);

	}

	public static void createJSON(PriceTransparencyListRequest priceTransparencyListRequest, String sasToken,
			String fileSasToken) throws IOException {

		String hospitalName = priceTransparencyListRequest.getHospitalName();
		String folderName = priceTransparencyListRequest.getHospitalName() + "json";

		ObjectMapper mapper = new ObjectMapper();

		try {
			String json = mapper.writeValueAsString(priceTransparencyListRequest);

			String jsonFilePath = priceTransparencyListRequest.getHospitalName() + ".json";

			FileWriter file = new FileWriter("/home/site/wwwroot/"+jsonFilePath);
			file.write(json);
			file.close();
			
			

			createJSONFile(folderName, hospitalName, sasToken, jsonFilePath, fileSasToken);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	static public DataLakeServiceClient createCSVFile(String folder, String hospital, String sasToken,
			String csvFilePath, String fileSasToken) {

		try {
			String endpoint = "https://hospitalpricedata.dfs.core.windows.net/";

			DataLakeServiceClient dataLakeServiceClient = new DataLakeServiceClientBuilder().endpoint(endpoint)
					.sasToken(sasToken).buildClient();

			DataLakeFileSystemClient fileSystemClient = dataLakeServiceClient.getFileSystemClient(folder);

			fileSystemClient.createIfNotExists();

			System.out.println("test::::" + csvFilePath);

			Path csvpath = Paths.get(csvFilePath);

			DataLakeFileClient fileClient = new DataLakePathClientBuilder()
					.endpoint("https://hospitalpricedata.dfs.core.windows.net/").sasToken(fileSasToken)
					.fileSystemName(folder).pathName("/home/site/wwwroot/"+String.valueOf(csvpath.getFileName())).buildFileClient();

			fileClient.uploadFromFile(csvFilePath, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	static public DataLakeServiceClient createJSONFile(String folder, String hospital, String sasToken,
			String jsonFilePath, String fileSasToken) {

		try {
			String endpoint = "https://hospitalpricedata.dfs.core.windows.net/";

			DataLakeServiceClient dataLakeServiceClient = new DataLakeServiceClientBuilder().endpoint(endpoint)
					.sasToken(sasToken).buildClient();

			DataLakeFileSystemClient fileSystemClient = dataLakeServiceClient.getFileSystemClient(folder);

			fileSystemClient.createIfNotExists();

			Path jsonFile = Paths.get(jsonFilePath);

			DataLakeFileClient filejsonClient = new DataLakePathClientBuilder()
					.endpoint("https://hospitalpricedata.dfs.core.windows.net/").sasToken(fileSasToken)
					.fileSystemName(folder).pathName("/home/site/wwwroot/"+String.valueOf(jsonFile.getFileName())).buildFileClient();

			filejsonClient.uploadFromFile(jsonFilePath, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	static public String createFileSASToken(String folder) {

		String sas = null;

		try {
			String endpoint = "https://hospitalpricedata.blob.core.windows.net/";

			StorageSharedKeyCredential storageSharedKeyCredential = new StorageSharedKeyCredential("hospitalpricedata",
					"TAcZ/9PGlngM+YEYdyXF+mkw14/f+Is3i0M9BGJZzc7i5qx35rFz/tCBvKAnp7zYL8TI3kVyd5/o+AStDDOShw==");

			DataLakeServiceClient dataLakeServiceClient = new DataLakeServiceClientBuilder()
					.credential(storageSharedKeyCredential).endpoint(endpoint).buildClient();

			DataLakeFileSystemClient fileSystemClient = dataLakeServiceClient.getFileSystemClient(folder);

			OffsetDateTime expiryTime = OffsetDateTime.now().plusDays(1);

			FileSystemSasPermission permission = new FileSystemSasPermission().setWritePermission(true)
					.setCreatePermission(true).setReadPermission(true);

			DataLakeServiceSasSignatureValues values = new DataLakeServiceSasSignatureValues(expiryTime, permission)
					.setStartTime(OffsetDateTime.now());

			sas = fileSystemClient.generateSas(values);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return sas;
	}

	static public String createSASToken(String folder) {

		String sas = null;

		try {

			String endpoint = "https://hospitalpricedata.blob.core.windows.net/";

			StorageSharedKeyCredential storageSharedKeyCredential = new StorageSharedKeyCredential("hospitalpricedata",
					"TAcZ/9PGlngM+YEYdyXF+mkw14/f+Is3i0M9BGJZzc7i5qx35rFz/tCBvKAnp7zYL8TI3kVyd5/o+AStDDOShw==");

			DataLakeServiceClient dataLakeServiceClient = new DataLakeServiceClientBuilder()
					.credential(storageSharedKeyCredential).endpoint(endpoint).buildClient();

			AccountSasPermission permissions = new AccountSasPermission().setWritePermission(true)
					.setReadPermission(true).setCreatePermission(true);
			AccountSasResourceType resourceTypes = new AccountSasResourceType().setContainer(true);
			AccountSasService services = new AccountSasService().setBlobAccess(true).setFileAccess(true);
			OffsetDateTime expiryTime = OffsetDateTime.now().plus(Duration.ofDays(2));

			AccountSasSignatureValues sasValues = new AccountSasSignatureValues(expiryTime, permissions, services,
					resourceTypes);

			sas = dataLakeServiceClient.generateAccountSas(sasValues);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return sas;
	}
}
