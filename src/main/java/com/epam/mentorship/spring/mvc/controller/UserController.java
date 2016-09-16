package com.epam.mentorship.spring.mvc.controller;

import com.epam.mentorship.spring.mvc.model.User;
import com.epam.mentorship.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add_user";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute(value="user") User user) {
        userService.addUser(user);
        return "add_user";
    }

    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
    public ModelAndView showUserInfo(@PathVariable Long id) {
        User user = userService.getUserById(id);
        ModelAndView mav = new ModelAndView("show_user");
        mav.addObject("user", user);
        return mav;
    }
}
