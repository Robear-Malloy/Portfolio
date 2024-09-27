package com.robear.portfolio.repository;

import com.robear.portfolio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
