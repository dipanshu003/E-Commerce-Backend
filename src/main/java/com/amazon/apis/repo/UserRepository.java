package com.amazon.apis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazon.apis.enums.UserRole;
import com.amazon.apis.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

	boolean existsByEmail(String email);

	User findByUserRole(UserRole admin);
}
