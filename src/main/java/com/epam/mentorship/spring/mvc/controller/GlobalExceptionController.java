package com.epam.mentorship.spring.mvc.controller;

import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice
public class GlobalExceptionController {

//    @ResponseStatus(value= HttpStatus.NOT_FOUND)
//    @ExceptionHandler(PageNotFoundException.class)
    public ModelAndView handleAllException(Exception ex) {
        ModelAndView model = new ModelAndView("error/404");
        //TODO log error, add to model and show message
        return model;
    }

}