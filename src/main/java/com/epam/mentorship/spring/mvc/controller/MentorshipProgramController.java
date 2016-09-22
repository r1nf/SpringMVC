package com.epam.mentorship.spring.mvc.controller;

import com.epam.mentorship.spring.mvc.error.PageNotFoundException;
import com.epam.mentorship.spring.mvc.model.MentorshipProgram;
import com.epam.mentorship.spring.mvc.service.MentorshipProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/mprogram")
public class MentorshipProgramController {

    @Autowired
    private MentorshipProgramService mentorshipProgramService;

    @RequestMapping(value="/get/all", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<MentorshipProgram> listAllMentorshipProgramsWithMarshaling() {
        return mentorshipProgramService.getAllMentorshipPrograms();
    }

    @RequestMapping(value = "/get/all")
    public String listAllMentorshipPrograms(Model model) {
        model.addAttribute("mprograms", listAllMentorshipProgramsWithMarshaling());
        return "list_mentorsip_program";
    }

    @RequestMapping(value = "/get/{id:[\\d]+}", method = RequestMethod.GET)
    public String showMentorshipProgram(@PathVariable Long id, Model model) {
        model.addAttribute("mprogram", showMentorshipProgram(id));
        return "show_mentorship_program";
    }

    @RequestMapping(value = "/get/{id:[\\d]+}", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody MentorshipProgram showMentorshipProgram(@PathVariable Long id) {
        if (id == null || id <= 0) throw new PageNotFoundException();
        MentorshipProgram mentorshipProgram = mentorshipProgramService.getMentorsipProgramById(id);
        if (mentorshipProgram == null) {
            throw new PageNotFoundException();
        } else {
            return mentorshipProgram;
        }
    }

    @RequestMapping(value = "/delete/{id:[\\d]+}", method = RequestMethod.GET)
    public ModelAndView deleteMentorshipProgram(@PathVariable Long id) {
        mentorshipProgramService.deleteMentorsipProgram(id);
        return new ModelAndView("redirect:/mprogram/get/all");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddMentorshipProgramForm(Model model) {
        model.addAttribute("mprogram", new MentorshipProgram());
        return "save_mentorship_program";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveMentorshipProgram(@ModelAttribute(value = "mprogram") @Valid MentorshipProgram mentorshipProgram, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "save_mentorship_program";
        } else {
            mentorshipProgramService.saveMentorshipProgram(mentorshipProgram);
            return "redirect:/mprogram/get/all";
        }
    }

    @RequestMapping(value = "/edit/{id:[\\d]+}", method = RequestMethod.GET)
    public String editMentorshipProgram(@PathVariable Long id, Model model) {
        model.addAttribute("mprogram", mentorshipProgramService.getMentorsipProgramById(id));
        return "save_mentorship_program";
    }
}
