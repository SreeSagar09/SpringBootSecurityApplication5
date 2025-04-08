package com.app.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.model.Company;
import com.app.repository.CompanyRepository;

@Component
public class CompanyApiKeyAuthenticateFilter extends OncePerRequestFilter {
	public static String API_KEY = "api-key";
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String apiKey = request.getHeader(API_KEY);
		System.out.println("---- apiKey ---- "+apiKey);
		
		if(apiKey != null && !apiKey.trim().isEmpty()) {
			Company company = companyRepository.findByCompanyApiKey(apiKey);
			
			if(company != null) {
				PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken = new PreAuthenticatedAuthenticationToken(company, null, null);
				
				SecurityContextHolder.getContext().setAuthentication(preAuthenticatedAuthenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
