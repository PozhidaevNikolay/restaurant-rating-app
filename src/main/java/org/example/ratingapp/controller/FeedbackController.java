package org.example.ratingapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.ratingapp.logic.FeedbackService;
import org.example.ratingapp.dto.FeedbackDtos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
@Tag(name = "API Отзывов", description = "Методы для управления отзывами")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    @Operation(summary = "Получить список всех отзывов")
    public List<FeedbackDtos.FeedbackView> getAll() {
        return feedbackService.getAllFeedback();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить отзыв по ID")
    public ResponseEntity<FeedbackDtos.FeedbackView> getById(@PathVariable Long id) {
        return feedbackService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Оставить новый отзыв")
    public FeedbackDtos.FeedbackView create(@RequestBody FeedbackDtos.NewFeedback dto) {
        return feedbackService.leaveFeedback(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удалить отзыв по ID")
    public void delete(@PathVariable Long id) {
        feedbackService.removeFeedback(id);
    }
}