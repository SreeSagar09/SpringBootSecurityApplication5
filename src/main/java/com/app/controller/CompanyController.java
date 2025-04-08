package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CompanyDTO;
import com.app.model.Company;
import com.app.repository.CompanyRepository;
import com.app.service.CompanyService;
import com.app.utils.CommonUtils;

@RestController
@RequestMapping(path = "/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CompanyRepository companyRepository;

	@PostMapping(path = "/saveCompany")
	public ResponseEntity<Map<String, Object>> saveCompany(@RequestBody CompanyDTO companyDTO){

		ResponseEntity<Map<String, Object>> responseEntity = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			Company company = companyService.saveCompany(companyDTO);

			companyDTO = new CompanyDTO.CompanyDTOBuilder(company)
					.setCreatedDate(simpleDateFormat.format(company.getCreatedDate()))
					.setUpdatedDate(simpleDateFormat.format(company.getUpdatedDate()))
					.build();

			responseEntity = new ResponseEntity<Map<String,Object>>(Map.of("company", companyDTO), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@PutMapping(path = "/updateCompany")
	public ResponseEntity<Map<String, Object>> updateCompany(@RequestBody CompanyDTO companyDTO){

		ResponseEntity<Map<String, Object>> responseEntity = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			Company company = CommonUtils.getCurrentCompany();

			Map<String, Object> errorMap = new LinkedHashMap<>();
			if(company == null) {
				errorMap.put("companyApiKey", "No Comapny find with the given given companyApiKey");
			}

			if(errorMap.isEmpty()) {
				companyDTO.setCompanyApiKey(company.getCompanyApiKey());
				company = companyService.updateCompany(companyDTO);

				companyDTO = new CompanyDTO.CompanyDTOBuilder(company)
						.setCreatedDate(simpleDateFormat.format(company.getCreatedDate()))
						.setUpdatedDate(simpleDateFormat.format(company.getUpdatedDate()))
						.build();

				responseEntity = new ResponseEntity<Map<String,Object>>(Map.of("company", companyDTO), HttpStatus.OK);
			}else {
				responseEntity = new ResponseEntity<Map<String,Object>>(errorMap, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@GetMapping(path = "/getCompany")
	public ResponseEntity<Map<String, Object>> getCompany(){

		ResponseEntity<Map<String, Object>> responseEntity = null;
		try {
			Company company = CommonUtils.getCurrentCompany();
			CompanyDTO companyDTO = companyService.getCompany(company.getCompanyApiKey());

			responseEntity = new ResponseEntity<Map<String,Object>>(Map.of("company", companyDTO), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
}
