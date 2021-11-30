package com.spring.boot.indian.banks.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indian.banks.utility.STATIC;
import com.spring.boot.indian.banks.app.EntityNotFoundException;
import com.spring.boot.indian.banks.app.model.BankDetails;
import com.spring.boot.indian.banks.app.response.ApiResponse;
import com.spring.boot.indian.banks.app.service.BankBranchesService;
import com.spring.boot.indian.banks.app.service.Utility;

@RestController
@CrossOrigin(maxAge = 3600)
public class BankDetailsController {

	@Autowired
	BankBranchesService bankBranchesService;

	@GetMapping(STATIC.HTTP.API.GET_BRANCH_DETAILS)
	public ResponseEntity<Object> getByBankIfsc(@RequestParam(name = "q") String ifsc)
			throws EntityNotFoundException, Exception {

		BankDetails bankDetails = bankBranchesService.getByIfsc(ifsc.toUpperCase());
		final Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("branch_details", bankDetails);
		return Utility.buildResponseEntity(new ApiResponse(resultMap));

	}

	

	@GetMapping(STATIC.HTTP.API.LIST_BRANCHES)
	public ResponseEntity<Object> getBranchesByBankCity(@RequestParam(defaultValue = "0") Integer offset,
			@RequestParam(defaultValue = "0") int limit, @RequestParam(name = "q") String cityName)
			throws EntityNotFoundException, Exception {

		List<BankDetails> bankDetailList = bankBranchesService.getByCity(offset, limit, cityName.toUpperCase());
		final Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("branches", bankDetailList);
		return Utility.buildResponseEntity(new ApiResponse(resultMap));

	}

	@GetMapping(STATIC.HTTP.API.SEARCH)
	public ResponseEntity<Object> search(@RequestParam(defaultValue = "0") Integer offset,
			@RequestParam(defaultValue = "0") int limit, @RequestParam(defaultValue = "",name="q") String searchString,
			@RequestParam(name = "city-name") String cityName) throws EntityNotFoundException, Exception {
		
		List<BankDetails> bankDetailList = bankBranchesService.searchAllAndFindByCity(offset, limit, searchString,cityName.toUpperCase());
		final Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("branches", bankDetailList);
		return Utility.buildResponseEntity(new ApiResponse(resultMap));

	}

}
