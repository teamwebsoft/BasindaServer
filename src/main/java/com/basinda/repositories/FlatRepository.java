package com.basinda.repositories;

import com.basinda.entities.Flat;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FlatRepository extends JpaRepository<Flat, String> {

}