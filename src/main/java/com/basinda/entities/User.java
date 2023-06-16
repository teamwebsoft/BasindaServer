package com.basinda.entities;

import com.basinda.models.eGenderType;
import lombok.Data;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

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
    private String fatherName;
    private String motherName;
    private eGenderType genderType;
    private Date birthday;
    private String mobileNumber;
    private String email;
    private String profession;
    private String devision;
    private String district;
    private String upozilla;
    private String pourosova;
    private String area;
    private String wordNo;
    private String postCode;
    private String holdingNumber;
    private String password;
}
