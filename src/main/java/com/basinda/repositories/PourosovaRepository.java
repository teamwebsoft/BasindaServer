package com.basinda.repositories;

import com.basinda.entities.Pourosova;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PourosovaRepository extends JpaRepository<Pourosova, Long> {

}