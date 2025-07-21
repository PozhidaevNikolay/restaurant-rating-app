package org.example.ratingapp.web.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.ratingapp.logic.FeedbackService;
import org.example.ratingapp.web.dto.FeedbackDtos; // <-- Импортируем DTO
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
@Tag(name = "Feedback API", description = "Endpoints for managing feedback")
public class FeedbackApiController {

    private final FeedbackService feedbackService;

    public FeedbackApiController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    @Operation(summary = "Get all feedback entries")
    public List<FeedbackDtos.FeedbackView> getAll() {
        return feedbackService.getAllFeedback();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Leave new feedback")
    public FeedbackDtos.FeedbackView create(@RequestBody FeedbackDtos.NewFeedback dto) {
        return feedbackService.leaveFeedback(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete feedback by its ID")
    public void delete(@PathVariable Long id) {
        feedbackService.removeFeedback(id);
    }
}