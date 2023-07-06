package com.basinda.models.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "properties")
@Setter
@Getter
public class Properties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String property;
    private String value;
}