package com.neev.moh.web.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;

import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;

public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {
	
	private static final MohLogger logger = MohLogFactory.getLoggerInstance(AuthenticationEntryPoint.class.getName());

    @Override
    public void commence(HttpServletRequest arg0, HttpServletResponse arg1, AuthenticationException arg2) throws IOException, ServletException {
    	logger.log(MohLogger.DEBUG, "commence()");
        arg1.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
