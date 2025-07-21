package org.example.ratingapp.storage;

import org.example.ratingapp.model.Restaurant;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RestaurantStorage {
    private final Map<Long, Restaurant> restaurantMap = new ConcurrentHashMap<>();
    private long idCounter = 0L;

    public Restaurant createOrUpdate(Restaurant restaurant) {
        if (restaurant.getId() == null) {
            long newId = ++idCounter;
            restaurant.setId(newId);
        }
        restaurantMap.put(restaurant.getId(), restaurant);
        return restaurant;
    }

    public void delete(Long id) {
        restaurantMap.remove(id);
    }

    public List<Restaurant> getAll() {
        return new ArrayList<>(restaurantMap.values());
    }

    public Optional<Restaurant> findById(Long id) {
        return Optional.ofNullable(restaurantMap.get(id));
    }
}