package com.epam.mentorship.spring.mvc.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @JacksonXmlProperty(isAttribute = true)
    private Long id;

    @Column(name = "first_name")
    @NotNull
    @Size(min = 2, max = 25)
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    @Size(min = 2, max = 25)
    private String lastName;

    @Column(name = "email")
    @NotNull
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private Level level;

    @Embedded
    private ModifiedEmbed modifiedEmbed;

    @ManyToMany(mappedBy = "members")
    private List<MentorshipProgram> mentorshipPrograms;

    public User() {
    }

    public User(String firstName, String lastName, String email, Level level) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.level = level;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<MentorshipProgram> getMentorshipPrograms() {
        return mentorshipPrograms;
    }

    public void setMentorshipPrograms(List<MentorshipProgram> mentorshipPrograms) {
        this.mentorshipPrograms = mentorshipPrograms;
    }

    public ModifiedEmbed getModifiedEmbed() {
        return modifiedEmbed;
    }

    public void setModifiedEmbed(ModifiedEmbed modifiedEmbed) {
        this.modifiedEmbed = modifiedEmbed;
    }
}
