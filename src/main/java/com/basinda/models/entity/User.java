package com.basinda.models.entity;

import com.basinda.models.entity.Comment;
import com.basinda.models.entity.Flat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.basinda.models.eUserType;
import com.basinda.models.eGenderType;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "userid")
    private String userId;
    private String nid;
    private String name;
    @Column(name = "fathername")
    private String fatherName;
    @Column(name = "mothername")
    private String motherName;
    @Column(name = "gendertype")
    private eGenderType genderType;
    private Date birthday;
    @Column(name = "mobilenumber")
    private String mobileNumber;
    private String email;
    private String profession;
    private String division;
    private String district;
    private String upozilla;
    private String pourosova;
    private String area;
    @Column(name = "wordno")
    private String wordNo;
    @Column(name = "postcode")
    private String postCode;
    @Column(name = "holdingnumber")
    private String holdingNumber;
    @JsonIgnore
    private String password;
    @Column(name = "usertype")
    private eUserType userType;
    @Column(name = "verificationcode", length = 64)
    private String verificationCode;
    private Boolean enabled;
    @Column(name = "isregistered")
    private Boolean isRegistered;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private List<Flat> flats;

    /**@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private List<Comment> comments1;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ownerid", referencedColumnName = "id")
    private List<Comment> comments2;*/
}