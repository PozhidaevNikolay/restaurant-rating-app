package org.example.ratingapp.config;

import org.example.ratingapp.logic.FeedbackService;
import org.example.ratingapp.logic.RestaurantService;
import org.example.ratingapp.logic.VisitorService;
import org.example.ratingapp.model.Cuisine;
import org.example.ratingapp.dto.FeedbackDtos;
import org.example.ratingapp.dto.RestaurantDtos;
import org.example.ratingapp.dto.VisitorDtos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSetupConfig {

    @Bean
    public CommandLineRunner dataLoader(VisitorService visitorService,
                                        RestaurantService restaurantService,
                                        FeedbackService feedbackService) {
        return args -> {
            if (visitorService.getAllVisitors().isEmpty()) {
                System.out.println(">>> База данных пуста. Заполняем начальными данными...");

                var visitor1 = visitorService.addVisitor(new VisitorDtos.NewVisitor("Алексей", 32, 'М'));
                var visitor2 = visitorService.addVisitor(new VisitorDtos.NewVisitor("Елена", 28, 'Ж'));

                var rest1 = restaurantService.addRestaurant(new RestaurantDtos.NewRestaurant("Белла Италия", "Аутентичная итальянская еда", Cuisine.ITALIAN, 2000));
                var rest2 = restaurantService.addRestaurant(new RestaurantDtos.NewRestaurant("Токио Бэй", "Свежие суши и роллы", Cuisine.ASIAN, 2500));

                feedbackService.leaveFeedback(new FeedbackDtos.NewFeedback(visitor1.id(), rest1.id(), 5, "Потрясающая паста!"));
                feedbackService.leaveFeedback(new FeedbackDtos.NewFeedback(visitor2.id(), rest1.id(), 4, "Уютная атмосфера."));
                feedbackService.leaveFeedback(new FeedbackDtos.NewFeedback(visitor1.id(), rest2.id(), 5, "Лучшие суши в городе!"));
                feedbackService.leaveFeedback(new FeedbackDtos.NewFeedback(visitor2.id(), rest2.id(), 4, "Очень свежая рыба."));

                System.out.println(">>> Начальные данные успешно созданы!");
            }

            System.out.println("\n=======================================================================");
            System.out.println("===== Приложение запущено. API доступно по адресу http://localhost:8080 =====");
            System.out.println("===== Документация Swagger UI: http://localhost:8080/swagger-ui.html =====");
            System.out.println("===== Консоль H2 DB: http://localhost:8080/h2-console =====");
            System.out.println("=======================================================================");
        };
    }
}