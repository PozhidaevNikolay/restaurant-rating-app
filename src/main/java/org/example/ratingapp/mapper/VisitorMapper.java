package org.example.ratingapp.mapper;

import org.example.ratingapp.dto.VisitorDtos;
import org.example.ratingapp.model.Visitor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VisitorMapper {


    @Mapping(target = "nickname", source = "nickname")
    Visitor toEntity(VisitorDtos.NewVisitor dto);

    VisitorDtos.VisitorView toView(Visitor entity);
}