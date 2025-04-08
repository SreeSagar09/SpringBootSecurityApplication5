package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	public Company findByCompanyId(Integer companyId);
	
	public Company findByCompanyApiKey(String companyApiKey);
}
