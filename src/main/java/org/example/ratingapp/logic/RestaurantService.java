package org.example.ratingapp.logic;

import org.example.ratingapp.model.Restaurant;
import org.example.ratingapp.storage.RestaurantStorage;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    private final RestaurantStorage restaurantStorage;

    public RestaurantService(RestaurantStorage restaurantStorage) {
        this.restaurantStorage = restaurantStorage;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantStorage.createOrUpdate(restaurant);
    }

    public void removeRestaurant(Long id) {
        restaurantStorage.delete(id);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantStorage.getAll();
    }

    public Optional<Restaurant> getById(Long id) {
        return restaurantStorage.findById(id);
    }
}