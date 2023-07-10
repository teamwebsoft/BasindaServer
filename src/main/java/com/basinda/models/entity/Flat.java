package com.basinda.models.entity;

import com.basinda.models.eFlatType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.basinda.models.eAnswerType;

@Data
@Entity
@Table(name = "flats")
@NoArgsConstructor
@AllArgsConstructor
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "housename")
    private String houseName;
    @Column(name = "flatside")
    private String flatSide;
    @Column(name = "flattype")
    private eFlatType flatType;
    private String sqr;
    private String bed;
    private String drawing;
    private String dining;
    private String washroom;
    private String kitchen;
    private String baranda;
    private eAnswerType lift;
    private eAnswerType parking;
    @Column(name = "userid")
    private Long userId;
}