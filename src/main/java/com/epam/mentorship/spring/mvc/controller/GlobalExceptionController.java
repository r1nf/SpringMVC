package com.epam.mentorship.spring.mvc.controller;

import com.epam.mentorship.spring.mvc.error.PageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(PageNotFoundException.class)
    public ModelAndView handleAllException(PageNotFoundException e) {
        ModelAndView model = new ModelAndView("error/404");
        return model;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(Exception e) throws Exception {
        e.printStackTrace();
        //TODO log error
        ModelAndView model = new ModelAndView("error/500");
        return model;
    }

}