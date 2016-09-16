package com.epam.mentorship.spring.mvc.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Set;

public class MentorshipProgram {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    private Set<User> members;

    public MentorshipProgram() {
    }

    public MentorshipProgram(String name, Date startDate, Date endDate, Set<User> members) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<User> getMembers() {
        return members;
    }
}
