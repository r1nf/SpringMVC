package com.epam.mentorship.spring.mvc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "mentorship")
public class MentorshipProgram {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @JacksonXmlProperty(isAttribute = true)
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "start_date")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "end_date")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Embedded
    private ModifiedEmbed modifiedEmbed;

    @ManyToMany
    @JoinTable(name="user_mentorship",
            joinColumns=
            @JoinColumn(name="mentorship_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="user_id", referencedColumnName="id")
    )
    @JsonIgnore
    private List<User> members;

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

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public ModifiedEmbed getModifiedEmbed() {
        return modifiedEmbed;
    }

    public void setModifiedEmbed(ModifiedEmbed modifiedEmbed) {
        this.modifiedEmbed = modifiedEmbed;
    }
}
