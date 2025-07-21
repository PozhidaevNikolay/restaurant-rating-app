package org.example.ratingapp.logic;

import org.example.ratingapp.model.Feedback;
import org.example.ratingapp.model.Restaurant;
import org.example.ratingapp.storage.FeedbackStorage;
import org.example.ratingapp.storage.RestaurantStorage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class FeedbackService {
    private final FeedbackStorage feedbackStorage;
    private final RestaurantStorage restaurantStorage;

    public FeedbackService(FeedbackStorage feedbackStorage, RestaurantStorage restaurantStorage) {
        this.feedbackStorage = feedbackStorage;
        this.restaurantStorage = restaurantStorage;
    }

    public Feedback leaveFeedback(Feedback feedback) {
        Feedback savedFeedback = feedbackStorage.create(feedback);
        recalculateRestaurantScore(savedFeedback.getRestaurantId());
        return savedFeedback;
    }

    public void removeFeedback(Long id) {
        // Логика немного сложнее, чтобы найти restaurantId до удаления
        feedbackStorage.getAll().stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .ifPresent(feedback -> {
                    feedbackStorage.delete(id);
                    recalculateRestaurantScore(feedback.getRestaurantId());
                });
    }

    public List<Feedback> getAllFeedback() {
        return feedbackStorage.getAll();
    }

    private void recalculateRestaurantScore(Long restaurantId) {
        restaurantStorage.findById(restaurantId).ifPresent(restaurant -> {
            List<Feedback> feedbacks = feedbackStorage.findByRestaurantId(restaurantId);
            if (feedbacks.isEmpty()) {
                restaurant.setScore(BigDecimal.ZERO);
            } else {
                double averageScore = feedbacks.stream()
                        .mapToInt(Feedback::getRating)
                        .average()
                        .orElse(0.0);
                restaurant.setScore(BigDecimal.valueOf(averageScore).setScale(1, RoundingMode.HALF_UP));
            }
            restaurantStorage.createOrUpdate(restaurant);
        });
    }
}