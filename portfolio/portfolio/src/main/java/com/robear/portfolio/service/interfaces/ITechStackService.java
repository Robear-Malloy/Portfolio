package com.robear.portfolio.service.interfaces;

import com.robear.portfolio.model.TechStack;
import com.robear.portfolio.enums.TechType;
import java.util.List;

public interface ITechStackService {
    TechStack createTechStack(TechStack stack);
    List<TechStack> getTechStack(Long id, TechType type);
    void deleteTechStack(Long id);
}
