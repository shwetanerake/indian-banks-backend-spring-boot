package com.spring.boot.indian.banks.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.indian.banks.app.model.BankDetails;

@Repository
public interface BankDetailsRepository extends CrudRepository<BankDetails, Long> {

	@Query(value = "SELECT COUNT(*) OVER (),* FROM bank_branches bd WHERE bd.ifsc=?1", nativeQuery = true)
	public BankDetails findByIfscIgnoreCase(String ifsc);

	/*
	 * public Page<BankDetails> findAllByBankCity(Pageable paging, String bankCity);
	 * 
	 * @Query(value =
	 * "SELECT COUNT(*) OVER (), * FROM bank_branches bd WHERE bd.city LIKE ?1 order by bd.ifsc offset ?2 limit ?3 "
	 * , nativeQuery = true) public List<BankDetails> findByBankCityWithLimit(String
	 * city, int offset, int limit);
	 * 
	 * @Query(value =
	 * "SELECT COUNT(*) OVER (), * FROM bank_branches bd WHERE bd.city LIKE ?1 order by bd.ifsc offset ?2"
	 * , nativeQuery = true) public List<BankDetails>
	 * findByBankCityWithoutLimit(String city, int offset);
	 * 
	 * @Query(value =
	 * "SELECT COUNT(*) OVER (), * FROM bank_branches ib WHERE ( ib.ifsc ilike  %?1% or ib.branch ilike  %?1% or ib.address ilike  %?1% or ib.district ilike  %?1% or ib.city ilike  %?1% or ib.state ilike  %?1% or ib.bank_name like %?1%) AND ib.city LIKE ?2 order by ib.ifsc limit ?3 offset ?4"
	 * , nativeQuery = true) public List<BankDetails>
	 * findAllLikeAndByBankCity(String q, String city, int limit,int offset);
	 * 
	 * @Query(value =
	 * "SELECT COUNT(*) OVER (), * FROM bank_branches as e WHERE (e.bank_name ilike %:inputString% or e.ifsc ilike %:inputString% or e.branch ilike %:inputString% or e.address ilike %:inputString% or e.district ilike  %:inputString% or e.city ilike %:inputString% or e.state ilike %:inputString% or e.bank_name ilike  'COOPERATIVE BANK LIMITED') AND e.city LIKE 'MUMBAI' order by e.ifsc limit  :limit offset  :offset"
	 * , nativeQuery = true) List<BankDetails> findAllByInputString(String
	 * inputString,int limit, int offset);
	 */
	
}
