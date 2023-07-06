package com.basinda.models.entity;

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
    @Column(name = "flatname")
    private String flatName;
    @Column(name = "flatside")
    private String flatSide;
    private String sqr;
    private String bed;
    private String drawing;
    private String dining;
    private String washroom;
    private String kitchen;
    private String baranda;
    private eAnswerType side;
    private eAnswerType parking;
    @Column(name = "userid")
    private Long userId;
}