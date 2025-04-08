package com.app.utils;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.app.model.Company;

public class CommonUtils {
	
	public static String generateApiKey() {
		UUID uuid = UUID.randomUUID();
		
		return uuid.toString();
	}
	
	public static Company getCurrentCompany() {
		try {
			Company company = null;
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			if(authentication != null && authentication.isAuthenticated()) {
				if(authentication.getPrincipal() instanceof Company) {
					company = (Company) authentication.getPrincipal();
				}
			}
			
			return company;
		} catch (Exception e) {
			throw e;
		}
	}
}
