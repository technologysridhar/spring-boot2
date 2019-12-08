package com.java4s.app.controller;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author magiccrafter
 */
@RestController
public class TestController {

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public void test() {
        throw new ConstraintViolationException("error", Collections.emptySet());
    }
    
    @GetMapping(value = "/test1", produces = MediaType.APPLICATION_JSON_VALUE)
    public void test1() {
        throw new NullPointerException(); 
    }
    
    @GetMapping(value = "/test2", produces = MediaType.APPLICATION_JSON_VALUE)
    public void test2(HttpServletRequest req,HttpServletResponse response) throws IOException { 
    	 throw new RuntimeException(); 
    	  
    }    
}
