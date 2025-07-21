package org.example.ratingapp.logic;

import org.example.ratingapp.model.Visitor;
import org.example.ratingapp.storage.VisitorStorage;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VisitorService {
    private final VisitorStorage visitorStorage;

    // Явный конструктор вместо Lombok
    public VisitorService(VisitorStorage visitorStorage) {
        this.visitorStorage = visitorStorage;
    }

    public Visitor addVisitor(Visitor visitor) {
        return visitorStorage.create(visitor);
    }

    public void removeVisitor(Long id) {
        visitorStorage.delete(id);
    }

    public List<Visitor> getAllVisitors() {
        return visitorStorage.getAll();
    }
}