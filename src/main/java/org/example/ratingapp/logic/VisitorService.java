package org.example.ratingapp.logic;

import org.example.ratingapp.mapper.VisitorMapper;
import org.example.ratingapp.model.Visitor;
import org.example.ratingapp.storage.VisitorStorage;
import org.example.ratingapp.web.dto.VisitorDtos;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorService {
    private final VisitorStorage visitorStorage;
    private final VisitorMapper visitorMapper;

    public VisitorService(VisitorStorage visitorStorage, VisitorMapper visitorMapper) {
        this.visitorStorage = visitorStorage;
        this.visitorMapper = visitorMapper;
    }

    public VisitorDtos.VisitorView addVisitor(VisitorDtos.NewVisitor dto) {
        Visitor visitor = visitorMapper.toEntity(dto);
        Visitor savedVisitor = visitorStorage.create(visitor);
        return visitorMapper.toView(savedVisitor);
    }

    public void removeVisitor(Long id) {
        visitorStorage.delete(id);
    }

    public List<VisitorDtos.VisitorView> getAllVisitors() {
        return visitorStorage.getAll().stream()
                .map(visitorMapper::toView)
                .collect(Collectors.toList());
    }
}