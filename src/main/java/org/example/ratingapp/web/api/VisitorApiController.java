package org.example.ratingapp.web.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.ratingapp.logic.VisitorService;
import org.example.ratingapp.web.dto.VisitorDtos;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visitors")
@Tag(name = "Visitors API", description = "Endpoints for managing visitors")
public class VisitorApiController {

    private final VisitorService visitorService;

    public VisitorApiController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping
    @Operation(summary = "Get list of all visitors")
    public List<VisitorDtos.VisitorView> getAll() {
        return visitorService.getAllVisitors();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register a new visitor")
    public VisitorDtos.VisitorView create(@RequestBody VisitorDtos.NewVisitor dto) {
        return visitorService.addVisitor(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a visitor")
    public void delete(@PathVariable Long id) {
        visitorService.removeVisitor(id);
    }
}