package com.epam.mentorship.spring.mvc.service;

import com.epam.mentorship.spring.mvc.model.User;
import com.epam.mentorship.spring.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(long id) {
        return userRepository.findOne(id);
    }

    public void addUser(User user) {
        User save = userRepository.save(user);
    }

    public void editUser(User user) {
        User save = userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.delete(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
