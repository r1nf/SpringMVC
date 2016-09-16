package com.epam.mentorship.spring.mvc.repository;

import com.epam.mentorship.spring.mvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
