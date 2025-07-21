package org.example.ratingapp.logic;

import jakarta.persistence.EntityNotFoundException;
import org.example.ratingapp.dto.RestaurantDtos;
import org.example.ratingapp.mapper.RestaurantMapper;
import org.example.ratingapp.model.Restaurant;
import org.example.ratingapp.storage.RestaurantRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    public RestaurantDtos.RestaurantView addRestaurant(RestaurantDtos.NewRestaurant dto) {
        Restaurant restaurant = restaurantMapper.toEntity(dto);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toView(savedRestaurant);
    }

    public RestaurantDtos.RestaurantView updateRestaurant(Long id, RestaurantDtos.NewRestaurant dto) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant with id " + id + " not found"));

        existingRestaurant.setTitle(dto.title());
        existingRestaurant.setSummary(dto.summary());
        existingRestaurant.setCuisine(dto.cuisine());
        existingRestaurant.setAvgBill(dto.avgBill());

        Restaurant updatedRestaurant = restaurantRepository.save(existingRestaurant);
        return restaurantMapper.toView(updatedRestaurant);
    }

    public void removeRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    public List<RestaurantDtos.RestaurantView> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(restaurantMapper::toView)
                .collect(Collectors.toList());
    }

    public Optional<RestaurantDtos.RestaurantView> getById(Long id) {
        return restaurantRepository.findById(id).map(restaurantMapper::toView);
    }
}