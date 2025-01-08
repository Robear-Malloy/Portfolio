package com.robear.portfolio.controller.interfaces;

import com.robear.portfolio.model.Certification;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ICertificationController {
    ResponseEntity<Certification> createCertification(Certification certification);
    ResponseEntity<List<Certification>> getAllCertifications(String lang);
    ResponseEntity<Void> deleteCertification(Long id);
}
