package com.robear.portfolio.repository;

import com.robear.portfolio.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor, Long> { }