package com.ikmed.LearningManagement.mapper;
import com.ikmed.LearningManagement.DTO.CourseDTO;
import com.ikmed.LearningManagement.form.ContentForm;
import com.ikmed.LearningManagement.form.CourseForm;
import com.ikmed.LearningManagement.model.ContentModel;
import com.ikmed.LearningManagement.DTO.ContentDTO;
import com.ikmed.LearningManagement.model.CourseModel;
import org.apache.tomcat.util.http.parser.Authorization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class ContentMapper {

    public static final  ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);
//
    public abstract ContentModel formToModel(ContentForm form);

    public abstract ContentDTO ModelToDto(ContentModel model);

    public abstract Set<ContentDTO> setModelToSetDto(Set<ContentModel> models);
}
