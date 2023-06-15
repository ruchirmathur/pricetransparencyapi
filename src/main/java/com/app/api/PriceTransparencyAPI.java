package com.app.api;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.PriceTransparencyListRequest;
import com.app.model.PriceTransparencyResponse;

@RestController
public class PriceTransparencyAPI {
	
	/**
	 * 
	 * @param patientSearchRequest
	 * @return
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/csv/create", method = RequestMethod.POST,produces = "application/json")
	public ResponseEntity<PriceTransparencyResponse> csvCreate(@RequestBody PriceTransparencyListRequest priceTransparencyRequest) throws IOException, InterruptedException {

		PriceTransparencyResponse priceTransparencyResponse = new PriceTransparencyResponse();
		
		Util util = new Util();
		
		String sasJsonToken = util.createSASToken(priceTransparencyRequest.getHospitalName()+"json");
		
		String sasJsonFileToken = util.createFileSASToken(priceTransparencyRequest.getHospitalName()+"json");

		String sasCSVToken = util.createSASToken(priceTransparencyRequest.getHospitalName()+"csv");
		
		String sasCSVFileToken = util.createFileSASToken(priceTransparencyRequest.getHospitalName());

		util.createCSV(priceTransparencyRequest,sasCSVToken,sasCSVFileToken);

		util.createJSON(priceTransparencyRequest,sasJsonToken,sasJsonFileToken);
		

		return new ResponseEntity<PriceTransparencyResponse>(priceTransparencyResponse, HttpStatus.OK);
	}

}
