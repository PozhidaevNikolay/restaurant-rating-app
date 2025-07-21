package org.example.ratingapp.web.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.ratingapp.logic.RestaurantService;
import org.example.ratingapp.web.dto.RestaurantDtos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@Tag(name = "Restaurants API", description = "Endpoints for managing restaurants")
public class RestaurantApiController {

    private final RestaurantService restaurantService;

    public RestaurantApiController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    @Operation(summary = "Get list of all restaurants")
    public List<RestaurantDtos.RestaurantView> getAll() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a restaurant by its ID")
    public ResponseEntity<RestaurantDtos.RestaurantView> getById(@PathVariable Long id) {
        return restaurantService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new restaurant")
    public RestaurantDtos.RestaurantView create(@RequestBody RestaurantDtos.NewRestaurant dto) {
        return restaurantService.addRestaurant(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a restaurant")
    public void delete(@PathVariable Long id) {
        restaurantService.removeRestaurant(id);
    }
}