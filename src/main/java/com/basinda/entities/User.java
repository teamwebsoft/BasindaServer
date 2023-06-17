package com.basinda.entities;

import jakarta.persistence.Column;
import lombok.Data;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import com.basinda.models.eGenderType;

import java.util.Date;

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
    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "mother_name")
    private String motherName;
    @Column(name = "gender")
    private eGenderType genderType;
    private Date birthday;
    @Column(name = "mobile")
    private String mobileNumber;
    private String email;
    private String profession;
    private String division;
    private String district;
    private String upozilla;
    private String pourosova;
    private String area;
    @Column(name = "word")
    private String wordNo;
    @Column(name = "post_code")
    private String postCode;
    @Column(name = "holding")
    private String holdingNumber;
    private String password;
}