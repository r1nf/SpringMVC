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
//@RequestMapping(value = "/mprogram")
public class MentorshipProgramController {

    @Autowired
    private MentorshipProgramService mentorshipProgramService;

    @RequestMapping(value = "/error")
    public ModelAndView error() {
        return new ModelAndView("error/404");

    }

    @RequestMapping(value = "/get/all")
    public ModelAndView showAllMentorshipPrograms() {
        List<MentorshipProgram> mentorshipProgramList = mentorshipProgramService.getAllMentorshipPrograms();
        ModelAndView modelAndView = new ModelAndView("list_mentorsip_program");
        modelAndView.addObject("mprograms", mentorshipProgramList);
        return modelAndView;
    }
//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public String showAddUserForm(Model model) {
//        model.addAttribute("user", new User());
//        return "save_user";
//    }
//
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public String saveUser(@ModelAttribute(value = "user") User user) {
//        userService.addUser(user);
//        return "redirect:/user/get/all";
//    }
//
//    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
//    public ModelAndView showUser(@PathVariable Long id) {
//        User user = userService.getUserById(id);
//        ModelAndView mav = new ModelAndView("show_user");
//        mav.addObject("user", user);
//        return mav;
//    }
//
//    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
//    public ModelAndView editUser(@PathVariable Long id) {
//        User user = userService.getUserById(id);
//        ModelAndView mav = new ModelAndView("save_user");
//        mav.addObject("user", user);
//        return mav;
//    }
//
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//    public ModelAndView deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return new ModelAndView("redirect:/user/get/all");
//    }
}
