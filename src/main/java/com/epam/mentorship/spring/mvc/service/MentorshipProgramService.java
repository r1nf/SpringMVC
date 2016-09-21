package com.epam.mentorship.spring.mvc.service;

import com.epam.mentorship.spring.mvc.model.MentorshipProgram;
import com.epam.mentorship.spring.mvc.repository.MentorshipProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorshipProgramService {

    @Autowired
    private MentorshipProgramRepository mentorshipProgramRepository;

    public List<MentorshipProgram> getAllMentorshipPrograms() {
        return mentorshipProgramRepository.findAll();
    }
}
