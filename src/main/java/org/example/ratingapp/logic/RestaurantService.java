package org.example.ratingapp.logic;

import org.example.ratingapp.mapper.RestaurantMapper;
import org.example.ratingapp.model.Restaurant;
import org.example.ratingapp.storage.RestaurantStorage;
import org.example.ratingapp.web.dto.RestaurantDtos;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantStorage restaurantStorage;
    private final RestaurantMapper restaurantMapper;

    public RestaurantService(RestaurantStorage restaurantStorage, RestaurantMapper restaurantMapper) {
        this.restaurantStorage = restaurantStorage;
        this.restaurantMapper = restaurantMapper;
    }

    public RestaurantDtos.RestaurantView addRestaurant(RestaurantDtos.NewRestaurant dto) {
        Restaurant restaurant = restaurantMapper.toEntity(dto);
        Restaurant savedRestaurant = restaurantStorage.createOrUpdate(restaurant);
        return restaurantMapper.toView(savedRestaurant);
    }

    public void removeRestaurant(Long id) {
        restaurantStorage.delete(id);
    }

    public List<RestaurantDtos.RestaurantView> getAllRestaurants() {
        return restaurantStorage.getAll().stream()
                .map(restaurantMapper::toView)
                .collect(Collectors.toList());
    }

    public Optional<RestaurantDtos.RestaurantView> getById(Long id) {
        return restaurantStorage.findById(id).map(restaurantMapper::toView);
    }
}