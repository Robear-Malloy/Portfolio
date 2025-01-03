package com.robear.portfolio.service;

import com.robear.portfolio.model.Visitor;
import com.robear.portfolio.repository.VisitorRepository;
import com.robear.portfolio.service.interfaces.IVisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import java.util.List;

@Service
public class VisitorService implements IVisitorService {
    private static Logger logger = LoggerFactory.getLogger(VisitorService.class);
    private final VisitorRepository visitorRepository;

    @Autowired
    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @Override
    public Visitor addVisitor(Visitor visitor) {
        try {
            logger.info("Saving new Visitor: {}", visitor);

            if (visitor.getName() == null || visitor.getName().isEmpty()) {
                visitor.setName("Anonymous");
            }

            String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            visitor.setDateCreated(currentDateTime);

            return visitorRepository.save(visitor);
        } catch (Exception e) {
            logger.error("Error saving new Visitor to database");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Visitor> getAllVisitors() {
        try {
            logger.info("Returning all visitors.");
            List<Visitor> visitors = visitorRepository.findAll();
            if (visitors.isEmpty()) {
                logger.warn("No visitors found");
                throw new RuntimeException("None Found");
            }
            return visitors;
        } catch (Exception e) {
            logger.error("Error retrieving all visitors.");
            throw new RuntimeException(e);
        }
    }
}
