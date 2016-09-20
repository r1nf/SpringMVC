package com.epam.mentorship.spring.mvc.controller;

import com.epam.mentorship.spring.mvc.model.MentorshipProgram;
import com.epam.mentorship.spring.mvc.service.MentorshipProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping(value = "/mprogram")
public class MentorshipProgramController {

    @Autowired
    private MentorshipProgramService mentorshipProgramService;

    // RESTful method
//    @RequestMapping(value="/get/all", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE})
    @RequestMapping(value="/get/all", produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<MentorshipProgram> listAllMentorshipProgramsWithMarshaling() {
        return mentorshipProgramService.getAllMentorshipPrograms();
    }

    // View-based method
    @RequestMapping(value = "/get/all")
    public String listWithView(Model model) {
        model.addAttribute("mprograms", listAllMentorshipProgramsWithMarshaling());
        return "list_mentorsip_program";
    }
}
