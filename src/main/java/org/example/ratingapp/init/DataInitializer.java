package org.example.ratingapp.init;

import org.example.ratingapp.logic.FeedbackService;
import org.example.ratingapp.logic.RestaurantService;
import org.example.ratingapp.logic.VisitorService;
import org.example.ratingapp.model.Cuisine;
import org.example.ratingapp.model.Feedback;
import org.example.ratingapp.model.Restaurant;
import org.example.ratingapp.model.Visitor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class DataInitializer implements CommandLineRunner {

    private final VisitorService visitorService;
    private final RestaurantService restaurantService;
    private final FeedbackService feedbackService;

    public DataInitializer(VisitorService visitorService, RestaurantService restaurantService, FeedbackService feedbackService) {
        this.visitorService = visitorService;
        this.restaurantService = restaurantService;
        this.feedbackService = feedbackService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>> Инициализация приложения тестовыми данными...");

        // 1. Создаем посетителей
        Visitor visitor1 = visitorService.addVisitor(new Visitor("Алексей", 32, 'M'));
        Visitor visitor2 = visitorService.addVisitor(new Visitor("Елена", 28, 'F'));
        visitorService.addVisitor(new Visitor(null, 45, 'M')); // Анонимный посетитель

        // 2. Создаем рестораны
        Restaurant rest1 = restaurantService.addRestaurant(new Restaurant("Белла Италия", "Настоящая итальянская еда", Cuisine.ITALIAN, 2000));
        Restaurant rest2 = restaurantService.addRestaurant(new Restaurant("Токийский Залив", "Свежие суши и роллы", Cuisine.ASIAN, 2500));

        // 3. Оставляем отзывы
        feedbackService.leaveFeedback(new Feedback(visitor1.getId(), rest1.getId(), 5, "Восхитительная паста!"));
        feedbackService.leaveFeedback(new Feedback(visitor2.getId(), rest1.getId(), 4, "Уютная атмосфера, но медленное обслуживание."));
        feedbackService.leaveFeedback(new Feedback(visitor1.getId(), rest2.getId(), 5, "Лучшие суши в городе!"));

        System.out.println("\n===== Начальное состояние ресторанов =====");
        printRestaurants();

        // 4. Демонстрация логики
        System.out.println("\n>>> Оставляем еще один отзыв для ресторана 'Токийский Залив'...");
        feedbackService.leaveFeedback(new Feedback(visitor2.getId(), rest2.getId(), 3, "Было неплохо, но ожидал большего."));

        System.out.println("\n===== Состояние после нового отзыва =====");
        printRestaurants();

        System.out.println("\n>>> Приложение готово к работе!");
    }

    private void printRestaurants() {
        restaurantService.getAllRestaurants().stream()
                .sorted(Comparator.comparing(Restaurant::getId))
                .forEach(r -> System.out.printf("ID: %d, Название: %-18s, Оценка: %.1f%n", r.getId(), r.getTitle(), r.getScore()));
    }
}