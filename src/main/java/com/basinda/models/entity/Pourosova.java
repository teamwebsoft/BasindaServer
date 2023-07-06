package com.basinda.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.modelmapper.internal.bytebuddy.asm.Advice;

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
    @Column(name = "districtid")
    private Long districtId;
}