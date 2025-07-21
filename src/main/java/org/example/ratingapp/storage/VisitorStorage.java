package org.example.ratingapp.storage;

import org.example.ratingapp.model.Visitor;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class VisitorStorage {
    private final Map<Long, Visitor> visitorMap = new ConcurrentHashMap<>();
    private long idCounter = 0L;

    public Visitor create(Visitor visitor) {
        long newId = ++idCounter;
        visitor.setId(newId);
        visitorMap.put(newId, visitor);
        return visitor;
    }

    public void delete(Long id) {
        visitorMap.remove(id);
    }

    public List<Visitor> getAll() {
        return new ArrayList<>(visitorMap.values());
    }
}