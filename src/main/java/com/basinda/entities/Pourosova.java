package com.basinda.entities;

import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
    @Column(name = "district_id")
    private Long districtId;
}