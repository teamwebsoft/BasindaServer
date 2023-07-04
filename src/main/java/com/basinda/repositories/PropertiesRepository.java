package com.basinda.repositories;

import com.basinda.entities.Properties;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PropertiesRepository extends JpaRepository<Properties, Long> {

}