package com.epam.mentorship.spring.mvc.controller;

import com.epam.mentorship.spring.mvc.error.PageNotFoundException;
import com.epam.mentorship.spring.mvc.model.User;
import com.epam.mentorship.spring.mvc.service.UserService;
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
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get/all")
    public String showAllUsers(Model model) {
        model.addAttribute("users", listAllUsersWithMarshaling());
        return "user_list";
    }

    @RequestMapping(value="/get/all", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<User> listAllUsersWithMarshaling() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/get/{id:[\\d]+}", method = RequestMethod.GET)
    public String showUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", showUserWithMarshaling(id));
        return "show_user";
    }

    @RequestMapping(value = "/get/{id:[\\d]+}", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public User showUserWithMarshaling(@PathVariable Long id) {
        if (id == null || id <= 0) throw new PageNotFoundException();
        User user = userService.getUserById(id);
        if (user == null) {
            throw new PageNotFoundException();
        } else {
            return user;
        }
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

    @RequestMapping(value = "/edit/{id:[\\d]+}", method = RequestMethod.GET)
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "save_user";
    }

    @RequestMapping(value = "/delete/{id:[\\d]+}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/user/get/all");
    }
}
