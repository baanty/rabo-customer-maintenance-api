package com.rabo.api.handler;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rabo.api.exception.GenericCustomerApplicationRuntimeException;
import com.rabo.api.jsonbody.CustomErrorResponse;

/**
 * Use this class to Handle all generci Rest controller exceptions.
 * These exceptions are thrown from the <code>CustomerController</code> class.
 * @author Pijush Kanti Das
 *
 */
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
    @ExceptionHandler(GenericCustomerApplicationRuntimeException.class)
    public ResponseEntity<CustomErrorResponse> springHandleNotFound(HttpServletResponse response, 
    			GenericCustomerApplicationRuntimeException exception) throws IOException {
    	CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(exception.getMessage());
        errors.setStatus(String.valueOf(response.getStatus()));

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
}
