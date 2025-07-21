package org.example.ratingapp.logic;

import jakarta.persistence.EntityNotFoundException;
import org.example.ratingapp.dto.VisitorDtos;
import org.example.ratingapp.mapper.VisitorMapper;
import org.example.ratingapp.model.Visitor;
import org.example.ratingapp.storage.VisitorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitorService {
    private final VisitorRepository visitorRepository;
    private final VisitorMapper visitorMapper;

    public VisitorService(VisitorRepository visitorRepository, VisitorMapper visitorMapper) {
        this.visitorRepository = visitorRepository;
        this.visitorMapper = visitorMapper;
    }

    public VisitorDtos.VisitorView addVisitor(VisitorDtos.NewVisitor dto) {
        Visitor visitor = visitorMapper.toEntity(dto);
        return visitorMapper.toView(visitorRepository.save(visitor));
    }

    public VisitorDtos.VisitorView updateVisitor(Long id, VisitorDtos.NewVisitor dto) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Visitor with id " + id + " not found"));
        visitor.setNickname(dto.nickname());
        visitor.setAge(dto.age());
        visitor.setGender(dto.gender());
        return visitorMapper.toView(visitorRepository.save(visitor));
    }

    public void removeVisitor(Long id) {
        visitorRepository.deleteById(id);
    }

    public List<VisitorDtos.VisitorView> getAllVisitors() {
        return visitorRepository.findAll().stream()
                .map(visitorMapper::toView)
                .collect(Collectors.toList());
    }

    public Optional<VisitorDtos.VisitorView> getById(Long id) {
        return visitorRepository.findById(id).map(visitorMapper::toView);
    }
}