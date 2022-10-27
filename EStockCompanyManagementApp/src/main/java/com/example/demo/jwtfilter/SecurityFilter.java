package com.example.demo.jwtfilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class SecurityFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpreq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		
		String authHeader = httpreq.getHeader("authorization");
		System.out.println("Auth Header:: "+authHeader);
		if(authHeader == null && !authHeader.startsWith("Bearer") ) {
			throw new ServletException("Missing or invalid authentication header");
			
		}
		String jwtToken = authHeader.substring(7);
		Claims claim = Jwts.parser().setSigningKey("mykey").parseClaimsJws(jwtToken).getBody();
		
		httpreq.setAttribute("username", claim);
		
		chain.doFilter(request, response);
	}

}	
