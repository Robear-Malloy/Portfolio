package com.robear.portfolio.service.interfaces;

import com.robear.portfolio.model.Visitor;
import java.util.List;

public interface IVisitorService {
    Visitor addVisitor(Visitor visitor);
    List<Visitor> getAllVisitors();
}
