package com.robear.portfolio.service.interfaces;

import com.robear.portfolio.model.Certification;
import java.util.List;

public interface ICertificationService {
    Certification addCertification(Certification certification);
    List<Certification> getAllCertification(String lang);
    void deleteCertification(Long id);
}
