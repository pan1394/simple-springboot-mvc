package com.linkstec.mvc.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.linkstec.mvc.exception.MvcException;

@ControllerAdvice
public class MvcExceptionHandler {
	
	@ExceptionHandler
    public ModelAndView exceptionHandler(Exception ex){
		
        ModelAndView mv = new ModelAndView("error");
        if(ex instanceof MvcException) {
        	mv.addObject("exception", ex.getMessage());
        }else {
        	mv.addObject("exception", "未知异常");
        }
        return mv;
    }
}



 