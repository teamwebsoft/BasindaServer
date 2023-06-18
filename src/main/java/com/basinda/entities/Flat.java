package com.basinda.entities;

import lombok.Data;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import com.basinda.models.eAnswerType;

@Data
@Entity
@Table(name = "flats")
@NoArgsConstructor
@AllArgsConstructor
public class Flat {
    @Id
    private String id;
    private String flatName;
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
    @Column(name = "user_id")
    private String userId;
}