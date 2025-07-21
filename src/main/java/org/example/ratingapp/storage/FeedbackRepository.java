package org.example.ratingapp.storage;

import org.example.ratingapp.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findAllByRestaurantId(Long restaurantId);
}