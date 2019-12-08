package com.java4s.app.rest.error;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

 
@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse constraintViolationException(ConstraintViolationException ex) {
    	
    	System.out.println("Comes toconstraintViolationException of GlobalControllerExceptionHandler ");
        return new ApiErrorResponse(500, 5001, ex.getMessage());
    }

/*    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse noHandlerFoundException(Exception ex) {
        return new ApiErrorResponse(404, 4041, ex.getMessage());
    }
    
    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse noHandlerFoundException2(Exception ex) {
        return new ApiErrorResponse(400, 4041, ex.getMessage());
    }
*/
    
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
    		WebRequest request) {
    	System.out.println("Comes to handleBindException");
    	return super.handleBindException(ex, headers, status, request);
    }
    
 
    @ExceptionHandler(value = { NullPointerException.class }) 
    public ApiErrorResponse npe(Exception ex,HttpServletResponse response) {  
    	 System.out.println("Comes here npe");
    	System.out.println(response.getStatus());
        return new ApiErrorResponse(response.getStatus(), 5002, ex.getMessage());
    }
    
    
    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ApiErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiErrorResponse error=new ApiErrorResponse(400, 400001, "........");
       
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse noHandlerFoundException2(Exception ex) {
    	System.out.println("Comes here noHandlerFoundException2");
        return new ApiErrorResponse(400, 4041, ex.getMessage());
    }
     
    
    @Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
    	
    			return ResponseEntity.status(status).body("fdfdfd");
    	
		//	return new ResponseEntity<Object>(new ApiErrorResponse(HttpStatus.BAD_REQUEST, "Required request params missing"));
	}	
		
    
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
    		HttpHeaders headers, HttpStatus status, WebRequest request) {
    	  System.out.println("Comes here handleHttpMessageNotReadable");
          //return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    	return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }
    
   
   @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
            ModelAndView mav = new ModelAndView("/404");
            mav.addObject("exception", e);  
            //mav.addObject("errorcode", "404");
            return mav;
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
    		HttpHeaders headers, HttpStatus status, WebRequest request) {
    	// TODO Auto-generated method stub
    	System.out.println("Comes here to handleMethodArgumentNotValid");
    	return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }
    
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
    		HttpHeaders headers, HttpStatus status, WebRequest request) {
    	// TODO Auto-generated method stub
    	System.out.println("handleHttpRequestMethodNotSupported not ....");
    	return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }
    
}
