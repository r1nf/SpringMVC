package com.epam.mentorship.spring.mvc.aop;

import com.epam.mentorship.spring.mvc.model.MentorshipProgram;
import com.epam.mentorship.spring.mvc.model.ModifiedEmbed;
import com.epam.mentorship.spring.mvc.model.User;
import com.epam.mentorship.spring.mvc.service.MentorshipProgramService;
import com.epam.mentorship.spring.mvc.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Aspect
@Component
public class ServiceMonitor {

    @Autowired
    private MentorshipProgramService mentorshipProgramService;

    @Autowired
    private UserService userService;

    @Around("target(com.epam.mentorship.spring.mvc.service.MentorshipProgramService) && execution(void saveMentorshipProgram(com.epam.mentorship.spring.mvc.model.MentorshipProgram))&& args(mp)")
    public Object fillAutoModifiedFieldsForMentorshipProgram(ProceedingJoinPoint pjp, MentorshipProgram mp) throws Throwable {
        ModifiedEmbed modifiedEmbed = mp.getId() == null ? new ModifiedEmbed() : mentorshipProgramService.getMentorsipProgramById(mp.getId()).getModifiedEmbed();
        mp.setModifiedEmbed(applyAutoModification(modifiedEmbed));
        return pjp.proceed(new Object[] {mp});
    }

    @Around("target(com.epam.mentorship.spring.mvc.service.UserService) && execution(void saveUser(com.epam.mentorship.spring.mvc.model.User))&& args(user)")
    public Object fillAutoModifiedFieldsForUser(ProceedingJoinPoint pjp, User user) throws Throwable {
        ModifiedEmbed modifiedEmbed = user.getId() == null ? new ModifiedEmbed() : userService.getUserById(user.getId()).getModifiedEmbed();
        user.setModifiedEmbed(applyAutoModification(modifiedEmbed));
        return pjp.proceed(new Object[] {user});
    }

    private ModifiedEmbed applyAutoModification(ModifiedEmbed modifiedEmbed) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        if (request == null) {
            throw new Exception("Can't access HttpServletRequest");
        }
        if (modifiedEmbed == null) {
            modifiedEmbed = new ModifiedEmbed();
        }
        if(modifiedEmbed.getCreatedByUser() == null || modifiedEmbed.getCreatedDate() == null) {
            modifiedEmbed.setCreatedDate(LocalDate.now());
            modifiedEmbed.setCreatedByUser(request.getRemoteAddr());
        }

        modifiedEmbed.setModifiedDate(LocalDate.now());
        modifiedEmbed.setModifiedByUser(request.getRemoteAddr()); //for now IP is used for user identification

        return modifiedEmbed;
    }
}
