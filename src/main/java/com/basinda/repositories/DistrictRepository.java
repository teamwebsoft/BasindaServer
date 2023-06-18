package com.basinda.repositories;

import com.basinda.entities.District;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

}