package com.basinda.repositories;

import com.basinda.models.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByMobileNumber(String mobileNumber);
    User findByVerificationCode(String code);
    @Query(value = "SELECT u FROM User u WHERE u.name <> 'Admin'")
    List<User> findAllExceptAdmin();
}