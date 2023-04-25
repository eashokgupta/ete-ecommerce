package com.etelligens.ecommerce.auhtorization.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

@RestControllerAdvice
public class AuthException {
	
	@ExceptionHandler(value = LoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody Error handleException(LoginException e){
        return new Error(HttpStatus.BAD_REQUEST,LocalDateTime.now(),e.getMessage());
    }

}
