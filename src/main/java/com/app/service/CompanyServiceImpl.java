package com.app.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.CompanyDTO;
import com.app.model.Company;
import com.app.repository.CompanyRepository;
import com.app.utils.CommonUtils;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Company saveCompany(CompanyDTO companyDTO) {
		
		try {
			Date currentDate = new Date();
			Company company = new Company();
			
			company.setCompanyName(companyDTO.getCompanyName());
			company.setCompanyOwner(companyDTO.getCompanyOwner());
			company.setCompanyApiKey(CommonUtils.generateApiKey());
			company.setStatus("Active");
			
			company.setCreatedDate(currentDate);
			company.setUpdatedDate(currentDate);
			
			company = companyRepository.save(company);
			
			return company;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Company updateCompany(CompanyDTO companyDTO) {
		
		try {
			Date currentDate = new Date();
			
			Company company = companyRepository.findByCompanyApiKey(companyDTO.getCompanyApiKey());
			
			if(companyDTO.getCompanyName() != null && !companyDTO.getCompanyName().trim().isEmpty()) {
				company.setCompanyName(companyDTO.getCompanyName());
			}
			
			if(companyDTO.getCompanyOwner() != null && !companyDTO.getCompanyOwner().trim().isEmpty()) {
				company.setCompanyOwner(companyDTO.getCompanyOwner());
			}
			
			if(companyDTO.getStatus() != null && !companyDTO.getStatus().trim().isEmpty()) {
				if(companyDTO.getStatus().equalsIgnoreCase("Active")) {
					company.setStatus("Active");
				}else if(companyDTO.getStatus().equalsIgnoreCase("Inactive")) {
					company.setStatus("Inactive");
				}
			}
			
			company.setUpdatedDate(currentDate);
			
			company = companyRepository.save(company);
			
			return company;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public CompanyDTO getCompany(String companyApiKey) {
		
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			
			CompanyDTO companyDTO = new CompanyDTO();
			
			Company company = companyRepository.findByCompanyApiKey(companyApiKey);
			if(company != null) {
				companyDTO = new CompanyDTO.CompanyDTOBuilder(company)
						.setCreatedDate(simpleDateFormat.format(company.getCreatedDate()))
						.setUpdatedDate(simpleDateFormat.format(company.getUpdatedDate()))
						.build();
			}
				
			return companyDTO;
		} catch (Exception e) {
			throw e;
		}
	}
	
}
