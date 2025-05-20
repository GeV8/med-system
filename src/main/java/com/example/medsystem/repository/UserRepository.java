package com.example.medsystem.repository;

import com.example.medsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.id NOT IN (SELECT p.user.id FROM Personnel p WHERE p.user IS NOT NULL)")
    List<User> findUsersNotLinkedToPersonnel();


}
