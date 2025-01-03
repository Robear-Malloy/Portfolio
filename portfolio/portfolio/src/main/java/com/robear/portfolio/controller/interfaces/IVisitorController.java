package com.robear.portfolio.controller.interfaces;

import com.robear.portfolio.model.Visitor;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IVisitorController {
    ResponseEntity<Visitor> createVisitor(Visitor visitor);
    ResponseEntity<List<Visitor>> getAllVisitors();
    ResponseEntity<Visitor> createAuthVisitor(Visitor visitor);
}
