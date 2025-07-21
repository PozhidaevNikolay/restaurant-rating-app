package org.example.ratingapp.logic;

import jakarta.persistence.EntityNotFoundException;
import org.example.ratingapp.dto.FeedbackDtos;
import org.example.ratingapp.mapper.FeedbackMapper;
import org.example.ratingapp.model.Feedback;
import org.example.ratingapp.model.Restaurant;
import org.example.ratingapp.model.Visitor;
import org.example.ratingapp.storage.FeedbackRepository;
import org.example.ratingapp.storage.RestaurantRepository;
import org.example.ratingapp.storage.VisitorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final RestaurantRepository restaurantRepository;
    private final VisitorRepository visitorRepository;
    private final FeedbackMapper feedbackMapper;

    public FeedbackService(FeedbackRepository feedbackRepository, RestaurantRepository restaurantRepository,
                           VisitorRepository visitorRepository, FeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.restaurantRepository = restaurantRepository;
        this.visitorRepository = visitorRepository;
        this.feedbackMapper = feedbackMapper;
    }

    @Transactional
    public FeedbackDtos.FeedbackView leaveFeedback(FeedbackDtos.NewFeedback dto) {
        Visitor visitor = visitorRepository.findById(dto.visitorId())
                .orElseThrow(() -> new EntityNotFoundException("Visitor with id " + dto.visitorId() + " not found"));
        Restaurant restaurant = restaurantRepository.findById(dto.restaurantId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant with id " + dto.restaurantId() + " not found"));

        Feedback feedback = feedbackMapper.toEntity(dto);
        feedback.setVisitor(visitor);
        feedback.setRestaurant(restaurant);

        Feedback savedFeedback = feedbackRepository.save(feedback);
        recalculateRestaurantScore(restaurant.getId());

        return feedbackMapper.toView(savedFeedback);
    }

    @Transactional
    public FeedbackDtos.FeedbackView updateFeedback(Long id, FeedbackDtos.NewFeedback dto) {
        Feedback existingFeedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feedback with id " + id + " not found"));

        if (!existingFeedback.getVisitor().getId().equals(dto.visitorId()) ||
                !existingFeedback.getRestaurant().getId().equals(dto.restaurantId())) {
            throw new IllegalArgumentException("Cannot change visitor or restaurant for an existing feedback.");
        }

        existingFeedback.setRating(dto.rating());
        existingFeedback.setComment(dto.comment());

        Feedback updatedFeedback = feedbackRepository.save(existingFeedback);
        recalculateRestaurantScore(updatedFeedback.getRestaurant().getId());

        return feedbackMapper.toView(updatedFeedback);
    }

    @Transactional
    public void removeFeedback(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feedback with id " + id + " not found"));

        Long restaurantId = feedback.getRestaurant().getId();
        feedbackRepository.delete(feedback);
        recalculateRestaurantScore(restaurantId);
    }

    public List<FeedbackDtos.FeedbackView> getAllFeedback() {
        return feedbackRepository.findAll().stream()
                .map(feedbackMapper::toView)
                .collect(Collectors.toList());
    }

    public Optional<FeedbackDtos.FeedbackView> getById(Long id) {
        return feedbackRepository.findById(id).map(feedbackMapper::toView);
    }

    private void recalculateRestaurantScore(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant with id " + restaurantId + " not found while recalculating score"));

        List<Feedback> feedbacks = feedbackRepository.findAllByRestaurantId(restaurantId);

        if (feedbacks.isEmpty()) {
            restaurant.setScore(BigDecimal.ZERO);
        } else {
            double averageScore = feedbacks.stream()
                    .mapToInt(Feedback::getRating)
                    .average()
                    .orElse(0.0);
            restaurant.setScore(BigDecimal.valueOf(averageScore).setScale(1, RoundingMode.HALF_UP));
        }
    }
}