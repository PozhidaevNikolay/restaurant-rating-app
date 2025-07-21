package org.example.ratingapp.logic;

import org.example.ratingapp.mapper.FeedbackMapper;
import org.example.ratingapp.model.Feedback;
import org.example.ratingapp.storage.FeedbackStorage;
import org.example.ratingapp.storage.RestaurantStorage;
import org.example.ratingapp.web.dto.FeedbackDtos; // <-- Импортируем DTO
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {
    private final FeedbackStorage feedbackStorage;
    private final RestaurantStorage restaurantStorage;
    private final FeedbackMapper feedbackMapper;

    public FeedbackService(FeedbackStorage feedbackStorage, RestaurantStorage restaurantStorage, FeedbackMapper feedbackMapper) {
        this.feedbackStorage = feedbackStorage;
        this.restaurantStorage = restaurantStorage;
        this.feedbackMapper = feedbackMapper;
    }

    public FeedbackDtos.FeedbackView leaveFeedback(FeedbackDtos.NewFeedback dto) {
        Feedback feedback = feedbackMapper.toEntity(dto);
        Feedback savedFeedback = feedbackStorage.create(feedback);
        recalculateRestaurantScore(savedFeedback.getRestaurantId());
        return feedbackMapper.toView(savedFeedback);
    }

    public void removeFeedback(Long id) {
        feedbackStorage.getAll().stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .ifPresent(feedback -> {
                    feedbackStorage.delete(id);
                    recalculateRestaurantScore(feedback.getRestaurantId());
                });
    }

    public List<FeedbackDtos.FeedbackView> getAllFeedback() {
        return feedbackStorage.getAll().stream()
                .map(feedbackMapper::toView)
                .collect(Collectors.toList());
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