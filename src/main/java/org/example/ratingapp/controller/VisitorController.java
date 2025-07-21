package org.example.ratingapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.ratingapp.logic.VisitorService;
import org.example.ratingapp.dto.VisitorDtos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/visitors")
@Tag(name = "API Посетителей", description = "Методы для управления посетителями")
public class VisitorController {

    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping
    @Operation(summary = "Получить список всех посетителей")
    public List<VisitorDtos.VisitorView> getAll() {
        return visitorService.getAllVisitors();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить посетителя по ID")
    public ResponseEntity<VisitorDtos.VisitorView> getById(@PathVariable Long id) {
        return visitorService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Зарегистрировать нового посетителя")
    public VisitorDtos.VisitorView create(@RequestBody VisitorDtos.NewVisitor dto) {
        return visitorService.addVisitor(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить данные посетителя")
    public VisitorDtos.VisitorView update(@PathVariable Long id, @RequestBody VisitorDtos.NewVisitor dto) {
        return visitorService.updateVisitor(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удалить посетителя по ID")
    public void delete(@PathVariable Long id) {
        visitorService.removeVisitor(id);
    }
}