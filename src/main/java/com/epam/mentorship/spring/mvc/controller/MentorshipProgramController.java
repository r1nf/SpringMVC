package com.epam.mentorship.spring.mvc.controller;

import com.epam.mentorship.spring.mvc.model.MentorshipProgram;
import com.epam.mentorship.spring.mvc.model.User;
import com.epam.mentorship.spring.mvc.service.MentorshipProgramService;
import com.epam.mentorship.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/mprogram")
public class MentorshipProgramController {

    @Autowired
    private MentorshipProgramService mentorshipProgramService;

    @RequestMapping(value = "/get/all")
    public ModelAndView showAllMentorshipPrograms() {
        List<MentorshipProgram> mentorshipProgramList = mentorshipProgramService.getAllMentorshipPrograms();
        ModelAndView modelAndView = new ModelAndView("list_mentorsip_program");
        modelAndView.addObject("mprograms", mentorshipProgramList);
        return modelAndView;
    }
}
