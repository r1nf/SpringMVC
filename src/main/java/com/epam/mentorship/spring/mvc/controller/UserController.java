package com.epam.mentorship.spring.mvc.controller;

import com.epam.mentorship.spring.mvc.error.PageNotFound;
import com.epam.mentorship.spring.mvc.model.User;
import com.epam.mentorship.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get/all")
    public ModelAndView showAllUsers() {
        List<User> userList = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView("user_list");
        modelAndView.addObject("users", userList);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "save_user";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute(value = "user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "save_user";
        } else {
            userService.save(user);
            return "redirect:/user/get/all";
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ModelAndView showUser(@PathVariable Long id) {
        if (id == null || id <= 0) throw new PageNotFound();
        User user = userService.getUserById(id);
        if (user != null) {
            ModelAndView mav = new ModelAndView("show_user");
            mav.addObject("user", user);
            return mav;
        } else {
            throw new PageNotFound();
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        ModelAndView mav = new ModelAndView("save_user");
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/user/get/all");
    }
}
