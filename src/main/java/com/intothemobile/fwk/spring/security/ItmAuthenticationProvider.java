package com.intothemobile.fwk.spring.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.intothemobile.fwk.spring.entity.AuthenticatedUser;
import com.intothemobile.fwk.spring.service.AuthenticationService;

/**
 * Spring Security AuthenticationProvider 구현체.
 * 
 * @author Donggill Shin
 * @since 0.0.1
 */
public class ItmAuthenticationProvider implements AuthenticationProvider {
	private static final Logger logger = LoggerFactory.getLogger(ItmAuthenticationProvider.class);
	
	private AuthenticationService authenticationService;

	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = (String) authentication.getPrincipal();
		String userPwd = (String) authentication.getCredentials();

		if (logger.isDebugEnabled()) {
			logger.debug("Login info user inputted. {"+userId + "/" + userPwd+"}");
		}
		
		AuthenticatedUser user = authenticationService.findByIdPwd(new AuthenticatedUser(userId, userPwd));
		
		if (user != null) {
			logger.info("Login success : " + userId);
			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			
			roles.add(new SimpleGrantedAuthority("SITE_MANAGER"));

			UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userId, userPwd, roles);
			result.setDetails(user);
			return result;
		} else {
			logger.warn("Incorrect user credentials. error occured.");
			throw new BadCredentialsException("Bad credentials");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
