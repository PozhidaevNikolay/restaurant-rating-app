package org.example.ratingapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.ratingapp.logic.RestaurantService;
import org.example.ratingapp.dto.RestaurantDtos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@Tag(name = "API Ресторанов", description = "Методы для управления ресторанами")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    @Operation(summary = "Получить список всех ресторанов")
    public List<RestaurantDtos.RestaurantView> getAll() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить ресторан по ID")
    public ResponseEntity<RestaurantDtos.RestaurantView> getById(@PathVariable Long id) {
        return restaurantService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать новый ресторан")
    public RestaurantDtos.RestaurantView create(@RequestBody RestaurantDtos.NewRestaurant dto) {
        return restaurantService.addRestaurant(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить данные ресторана")
    public RestaurantDtos.RestaurantView update(@PathVariable Long id, @RequestBody RestaurantDtos.NewRestaurant dto) {
        return restaurantService.updateRestaurant(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удалить ресторан по ID")
    public void delete(@PathVariable Long id) {
        restaurantService.removeRestaurant(id);
    }
}