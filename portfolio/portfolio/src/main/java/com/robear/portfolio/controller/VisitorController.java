package com.robear.portfolio.controller;

import com.robear.portfolio.controller.interfaces.IVisitorController;
import com.robear.portfolio.model.Visitor;
import com.robear.portfolio.service.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitor")
public class VisitorController implements IVisitorController {
    private static final Logger logger = LoggerFactory.getLogger(VisitorController.class);

    @Autowired
    private VisitorService visitorService;

    @PostMapping("/demo")
    @Override
    public ResponseEntity<Visitor> createVisitor(
            @RequestBody Visitor visitor) {
        try {
            logger.info("Creating a new visitor: {}", visitor);
            logger.info("Name: {}", visitor.getName());
            Visitor result = visitorService.addVisitor(visitor);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Unexpected error while creating a visitor.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @Override
    public ResponseEntity<List<Visitor>> getAllVisitors() {
        try {
            logger.info("Getting All Visitors.");
            List<Visitor> result = visitorService.getAllVisitors();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting all visitors.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/auth")
    @Override
    public ResponseEntity<Visitor> createAuthVisitor(
            @RequestBody Visitor visitor) {
        try {
            logger.info("Creating a new authorized visitor: {}", visitor);
            StringBuilder authMessage = new StringBuilder();
            authMessage.append("[Authorized] ").append(visitor.getMessage());

            visitor.setMessage(authMessage.toString());
            Visitor result = visitorService.addVisitor(visitor);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Unexpected error while creating an authorized visitor.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
