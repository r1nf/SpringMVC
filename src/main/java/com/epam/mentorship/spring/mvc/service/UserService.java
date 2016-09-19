package com.epam.mentorship.spring.mvc.service;

import com.epam.mentorship.spring.mvc.model.Level;
import com.epam.mentorship.spring.mvc.model.User;
import com.epam.mentorship.spring.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init() {
        userRepository.save(new User("Vika", "Moiseenko", "vika.moiseenko@gmail.com", Level.D2));
        userRepository.save(new User("Alex", "Barchuk", "ax.barchuk@gmail.com", Level.D3));
        userRepository.save(new User("Max", "Rudevych", "ipx@gmail.com", Level.D1));
    }

    public User getUserById(long id) {
        return userRepository.findOne(id);
    }

    public void save(User user) {
        User save = userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.delete(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
