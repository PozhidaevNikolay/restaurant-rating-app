package org.example.ratingapp.mapper;

import org.example.ratingapp.model.Restaurant;
import org.example.ratingapp.web.dto.RestaurantDtos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    @Mapping(target = "score", expression = "java(java.math.BigDecimal.ZERO)")
    Restaurant toEntity(RestaurantDtos.NewRestaurant dto);
    RestaurantDtos.RestaurantView toView(Restaurant entity);
}