package com.example.web.configuration;

import com.example.web.exception.ApplicationError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {

        String urlPath = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
        log.error(e.getLocalizedMessage());

        this.sendUnAuthorizedError(urlPath,httpServletResponse); 
    }

    private void sendInternalServerError(String urlPath, HttpServletResponse httpServletResponse) throws IOException{

        ObjectMapper mapper = new ObjectMapper();
        ApplicationError errorMessageHandler = new ApplicationError(HttpStatus.INTERNAL_SERVER_ERROR,"An internal error occurred",urlPath);
        String json = mapper.findAndRegisterModules().writeValueAsString(errorMessageHandler);
        httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(json);
    }

    private void sendForbiddenError(String urlPath, HttpServletResponse httpServletResponse) throws IOException{

        ObjectMapper mapper = new ObjectMapper();
        ApplicationError errorMessageHandler = new ApplicationError(HttpStatus.FORBIDDEN,"Access denied",urlPath);
        String json = mapper.findAndRegisterModules().writeValueAsString(errorMessageHandler);
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(json);

    }

    private void sendUnAuthorizedError(String urlPath, HttpServletResponse httpServletResponse) throws IOException{

        ObjectMapper mapper = new ObjectMapper();
        ApplicationError errorMessageHandler = new ApplicationError(HttpStatus.UNAUTHORIZED,"This ressource requires an authentification",urlPath);
        String json = mapper.findAndRegisterModules().writeValueAsString(errorMessageHandler);
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(json);

    }

}

