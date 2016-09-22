package com.epam.mentorship.spring.mvc.service;

import com.epam.mentorship.spring.mvc.model.MentorshipProgram;
import com.epam.mentorship.spring.mvc.repository.MentorshipProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MentorshipProgramService {

    @Autowired
    private MentorshipProgramRepository mentorshipProgramRepository;

    public List<MentorshipProgram> getAllMentorshipPrograms() {
        return mentorshipProgramRepository.findAll();
    }

    public MentorshipProgram getMentorsipProgramById(long id) {
        return mentorshipProgramRepository.findOne(id);
    }

    public void deleteMentorsipProgram(long id) {
        mentorshipProgramRepository.delete(id);
    }

    public void saveMentorshipProgram(MentorshipProgram mentorshipProgram) {
        mentorshipProgramRepository.save(mentorshipProgram);
    }
}
