package com.example.GasUtilityService.repo;

import com.example.GasUtilityService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String username);


    Optional<Object> findByEmail(String email);
}
