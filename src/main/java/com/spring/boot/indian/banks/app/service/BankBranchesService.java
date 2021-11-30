package com.spring.boot.indian.banks.app.service;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.boot.indian.banks.app.EntityNotFoundException;
import com.spring.boot.indian.banks.app.model.BankDetails;
import com.spring.boot.indian.banks.app.repository.BankDetailsRepository;
import com.spring.boot.indian.banks.app.response.ApiResponse;

@Service
public class BankBranchesService {

	@Autowired
	BankDetailsRepository bankDetailsRepository;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 
	 * @param ifsc
	 * @return
	 */
	public BankDetails getByIfsc(String ifsc) {

		BankDetails bankDetails = bankDetailsRepository.findByIfscIgnoreCase(ifsc);
		if (bankDetails == null) {
			throw new EntityNotFoundException(BankDetails.class, "ifsc", ifsc);
		}
		return bankDetails;

	}

	/**
	 * 
	 * @param offset
	 * @param limit
	 * @param city
	 * @return
	 * @throws Exception
	 */
	public List<BankDetails> getByCity(Integer offset, Integer limit, String city) throws Exception {
		String limitVal = null;
		if (limit == 0)
			limitVal = "All";
		else
			limitVal = String.valueOf(limit);

		String query = "select COUNT(*) OVER (), * from bank_branches where city='" + city + "' order by ifsc offset "
				+ offset + " limit " + limitVal;

		Query query1 = entityManager.createNativeQuery(query);

		List<BankDetails> bankDetailsList = new ArrayList<>();
		List<Object[]> objectList = query1.getResultList();
		if (objectList.isEmpty()) {
			throw new EntityNotFoundException(BankDetails.class, "city", city);
		}
		for (Object o[] : objectList) {
			bankDetailsList.add(new BankDetails(o));
		}
		return bankDetailsList;
	}

	/**
	 * 
	 * @param offset
	 * @param limit
	 * @param searchString
	 * @param cityName
	 * @return
	 */
	public List<BankDetails> searchAllAndFindByCity(Integer offset, Integer limit, String searchString, String cityName) {
		String limitVal = null;
		if (limit == 0)
			limitVal = "All";
		else
			limitVal = String.valueOf(limit);

		String likePsqlString = "'%" + searchString + "%'";
		String query = "SELECT COUNT(*) OVER (), * FROM bank_branches ib WHERE ( ib.ifsc ilike " + likePsqlString
				+ " or ib.branch ilike " + likePsqlString + " or ib.address ilike " + likePsqlString
				+ " or ib.district ilike " + likePsqlString + " or ib.city ilike " + likePsqlString
				+ " or ib.state ilike " + likePsqlString + " or ib.bank_name ilike " + likePsqlString
				+ " ) AND ib.city='" + cityName + "' order by ifsc limit " + limitVal + " offset " + offset;

		Query query1 = entityManager.createNativeQuery(query);

		List<BankDetails> bankDetails = new ArrayList<>();
		List<Object[]> objectList = query1.getResultList();
		if (objectList.isEmpty()) {
			throw new EntityNotFoundException(BankDetails.class, "city", cityName);
		}
		for (Object o[] : objectList) {
			bankDetails.add(new BankDetails(o));
		}
		return bankDetails;

	}

}
