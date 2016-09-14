package com.epam.mentorship.spring.mvc.repository;

import com.epam.mentorship.spring.mvc.model.Level;
import com.epam.mentorship.spring.mvc.model.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    private Map<Long, User> userMap = new HashMap<>();
    private static long lastIndex = 3;

    @PostConstruct
    private void init() {
        userMap.put(1L,  new User(1L, "Vika", "Moiseenko", "vika.moiseenko@gmail.com", Level.D2));
        userMap.put(2L,  new User(1L, "Alex", "Barchuk", "ax.barchuk@gmail.com", Level.D3));
        userMap.put(3L,  new User(1L, "Max", "Rudevych", "ipx@gmail.com", Level.D1));
    }

    public User findById(long id) {
        return userMap.get(id);
    }

    public void insertUser(User user) {
        userMap.put(++lastIndex, user);
    }
}
