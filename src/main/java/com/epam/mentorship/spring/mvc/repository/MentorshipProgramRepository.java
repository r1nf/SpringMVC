package com.epam.mentorship.spring.mvc.repository;

import com.epam.mentorship.spring.mvc.model.MentorshipProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorshipProgramRepository extends JpaRepository<MentorshipProgram, Long>{
}
