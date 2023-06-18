package com.basinda.entities;

import com.basinda.models.eUserType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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
    private String id;
    private String nid;
    private String name;
    private String fatherName;
    private String motherName;
    private eGenderType genderType;
    private Date birthday;
    private String mobileNumber;
    private String email;
    private String profession;
    private String division;
    private String district;
    private String upozilla;
    private String pourosova;
    private String area;
    private String wordNo;
    private String postCode;
    private String holdingNumber;
    private String password;
    private eUserType userType;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Flat> flats;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Comment> comments1;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private List<Comment> comments2;
}