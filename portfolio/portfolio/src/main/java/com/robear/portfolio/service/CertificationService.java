package com.robear.portfolio.service;

import com.robear.portfolio.model.Certification;
import com.robear.portfolio.repository.CertificationRepository;
import com.robear.portfolio.service.interfaces.ICertificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificationService implements ICertificationService {
    private static Logger logger = LoggerFactory.getLogger(CertificationService.class);
    private final CertificationRepository certificationRepository;

    @Autowired
    public CertificationService(CertificationRepository certificationRepository) {
        this.certificationRepository = certificationRepository;
    }

    @Override
    public Certification addCertification(Certification certification) {
        try {
            logger.info("Add Certification to database: {}", certification);
            return certificationRepository.save(certification);
        } catch (Exception e) {
            logger.error("Error adding certification to database");
            throw e;
        }
    }

    @Override
    public List<Certification> getAllCertification() {
        try {
            logger.info("Getting all certifications from database");
            return certificationRepository.findAll();
        } catch (Exception e) {
            logger.error("Error retrieving certifications from database");
            throw e;
        }
    }

    @Override
    public void deleteCertification(Long id) {
        try {
            logger.info("Deleting certification {}", id);
            certificationRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting certification");
            throw e;
        }
    }
}
