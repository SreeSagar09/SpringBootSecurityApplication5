package com.app.service;

import com.app.dto.CompanyDTO;
import com.app.model.Company;

public interface CompanyService {
	public Company saveCompany(CompanyDTO companyDTO);
	
	public Company updateCompany(CompanyDTO companyDTO);
	
	public CompanyDTO getCompany(String companyApiKey);
}
