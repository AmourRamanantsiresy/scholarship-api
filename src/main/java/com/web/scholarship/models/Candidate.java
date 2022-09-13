package com.web.scholarship.models;

import com.web.scholarship.models.dbUser.DBUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="\"candidate\"")
@Data
@ToString
@EqualsAndHashCode
public class Candidate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 150)
    private String lastName;

    @Column(length = 150)
    private String firstName;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false, length = 150)
    private String schoolOrigin;

    @Column(columnDefinition = "TEXT")
    private String about;

    @Column(nullable = false, length = 15, unique = true)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "study_level_id")
    private StudyLevel studyLevel;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @OneToOne
    @JoinColumn(name = "credentials_id")
    private DBUser credentials;
}