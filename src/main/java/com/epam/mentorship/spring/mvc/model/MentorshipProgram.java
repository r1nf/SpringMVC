package com.epam.mentorship.spring.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "mentorship")
@XmlRootElement
public class MentorshipProgram {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @XmlAttribute
    private Long id;

    @Column(name = "name")
    @NotNull
    @XmlAttribute
    private String name;

    @Column(name = "start_date")
    @NotNull
    @XmlAttribute
    private LocalDate startDate;

    @Column(name = "end_date")
    @NotNull
    @XmlAttribute
    private LocalDate endDate;

    @ManyToMany
    @JoinTable(name="user_mentorship",
            joinColumns=
            @JoinColumn(name="mentorship_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="user_id", referencedColumnName="id")
    )
    @JsonIgnore
    @XmlTransient
    private Set<User> members;

    public MentorshipProgram() {
    }

    public MentorshipProgram(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<User> getMembers() {
        return members;
    }
}
