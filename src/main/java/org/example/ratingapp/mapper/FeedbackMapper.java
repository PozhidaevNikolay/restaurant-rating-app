package org.example.ratingapp.mapper;

import org.example.ratingapp.dto.FeedbackDtos;
import org.example.ratingapp.model.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "visitor", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "rating", source = "rating")
    @Mapping(target = "comment", source = "comment")
    Feedback toEntity(FeedbackDtos.NewFeedback dto);

    @Mapping(target = "visitorId", source = "visitor.id")
    @Mapping(target = "restaurantId", source = "restaurant.id")
    FeedbackDtos.FeedbackView toView(Feedback entity);
}