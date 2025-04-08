package com.app.dto;

import com.app.model.Company;

public class CompanyDTO {
	private Integer companyId;
	private String companyName;
	private String companyOwner;
	private String companyApiKey;
	private String status;
	private String createdDate;
	private String updatedDate;
	
	public CompanyDTO() {
		super();
	}

	public CompanyDTO(Integer companyId, String companyName, String companyOwner, String companyApiKey, String status,
			String createdDate, String updatedDate) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyOwner = companyOwner;
		this.companyApiKey = companyApiKey;
		this.status = status;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	private CompanyDTO(CompanyDTOBuilder object) {
		this.companyId = object.companyId;
		this.companyName = object.companyName;
		this.companyOwner = object.companyOwner;
		this.companyApiKey = object.companyApiKey;
		this.status = object.status;
		this.createdDate = object.createdDate;
		this.updatedDate = object.updatedDate;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyOwner() {
		return companyOwner;
	}

	public void setCompanyOwner(String companyOwner) {
		this.companyOwner = companyOwner;
	}

	public String getCompanyApiKey() {
		return companyApiKey;
	}

	public void setCompanyApiKey(String companyApiKey) {
		this.companyApiKey = companyApiKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public static class CompanyDTOBuilder{
		private Integer companyId;
		private String companyName;
		private String companyOwner;
		private String companyApiKey;
		private String status;
		private String createdDate;
		private String updatedDate;
		
		public CompanyDTOBuilder(Company object) {
			this.companyId = object.getCompanyId();
			this.companyName = object.getCompanyName();
			this.companyOwner = object.getCompanyOwner();
			this.companyApiKey = object.getCompanyApiKey();
			this.status = object.getStatus();
		}
		
		public CompanyDTOBuilder setCreatedDate(String createdDate) {
			this.createdDate = createdDate;
			return this;
		}
		
		public CompanyDTOBuilder setUpdatedDate(String updatedDate) {
			this.updatedDate = updatedDate;
			return this;
		}
		
		public CompanyDTO build() {
			return new CompanyDTO(this);
		}
	}
}
