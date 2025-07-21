package org.example.ratingapp.mapper;

import org.example.ratingapp.dto.RestaurantDtos;
import org.example.ratingapp.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "feedbacks", ignore = true)
    @Mapping(target = "score", expression = "java(java.math.BigDecimal.ZERO)")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "summary", source = "summary")
    @Mapping(target = "avgBill", source = "avgBill")
    Restaurant toEntity(RestaurantDtos.NewRestaurant dto);

    @Mapping(target = "score", source = "score")
    RestaurantDtos.RestaurantView toView(Restaurant entity);
}