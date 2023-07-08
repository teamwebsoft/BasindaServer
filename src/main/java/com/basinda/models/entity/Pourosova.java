package com.basinda.models.entity;

import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "pourosovas")
@NoArgsConstructor
@AllArgsConstructor
public class Pourosova {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonIgnore
    @Column(name = "upozilaid")
    private Long upozilaId;
}