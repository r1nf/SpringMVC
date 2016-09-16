package com.epam.mentorship.spring.mvc.service;

import com.epam.mentorship.spring.mvc.model.User;
import com.epam.mentorship.spring.mvc.repository.UserRepositoryMap;
import com.epam.mentorship.spring.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepositoryMap userRepositoryMap;

    @Autowired
    private UserRepository userRepository;

    public User getUserById(long id) {
        return userRepositoryMap.findById(id);
    }

    public void addUser(User user) {
        User save = userRepository.save(user);
        userRepositoryMap.insertUser(user);
    }
}
