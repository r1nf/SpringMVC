package com.epam.mentorship.spring.mvc.service;

import com.epam.mentorship.spring.mvc.model.MentorshipProgram;
import com.epam.mentorship.spring.mvc.repository.MentorshipProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class MentorshipProgramService {

    @Autowired
    private MentorshipProgramRepository mentorshipProgramRepository;

    @PostConstruct
    private void init() {
        mentorshipProgramRepository.save(new MentorshipProgram("Java H1", LocalDate.of(2016, 6, 1), LocalDate.of(2016, 8, 30)));
        mentorshipProgramRepository.save(new MentorshipProgram("Java H2", LocalDate.of(2016, 9, 1), LocalDate.of(2016, 11, 30)));
    }

    public List<MentorshipProgram> getAllMentorshipPrograms() {
        return mentorshipProgramRepository.findAll();
    }
}
