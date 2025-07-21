package org.example.ratingapp.mapper;

import org.example.ratingapp.model.Feedback;
import org.example.ratingapp.web.dto.FeedbackDtos;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {
    Feedback toEntity(FeedbackDtos.NewFeedback dto);
    FeedbackDtos.FeedbackView toView(Feedback entity);
}