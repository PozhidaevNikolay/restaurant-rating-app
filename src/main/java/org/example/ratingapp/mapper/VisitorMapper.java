package org.example.ratingapp.mapper;

import org.example.ratingapp.model.Visitor;
import org.example.ratingapp.web.dto.VisitorDtos;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitorMapper {
    Visitor toEntity(VisitorDtos.NewVisitor dto);
    VisitorDtos.VisitorView toView(Visitor entity);
}