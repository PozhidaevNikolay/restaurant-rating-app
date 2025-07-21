package org.example.ratingapp.storage;

import org.example.ratingapp.model.Feedback;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class FeedbackStorage {
    private final Map<Long, Feedback> feedbackMap = new ConcurrentHashMap<>();
    private long idCounter = 0L;

    public Feedback create(Feedback feedback) {
        long newId = ++idCounter;
        feedback.setId(newId);
        feedbackMap.put(newId, feedback);
        return feedback;
    }

    public void delete(Long id) {
        feedbackMap.remove(id);
    }

    public List<Feedback> getAll() {
        return new ArrayList<>(feedbackMap.values());
    }

    public List<Feedback> findByRestaurantId(Long restaurantId) {
        return feedbackMap.values().stream()
                .filter(f -> f.getRestaurantId().equals(restaurantId))
                .collect(Collectors.toList());
    }
}