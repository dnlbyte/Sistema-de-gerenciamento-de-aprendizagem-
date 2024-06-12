package com.ikmed.LearningManagement.mapper;
import com.ikmed.LearningManagement.DTO.CourseDTO;
import com.ikmed.LearningManagement.form.CourseForm;
import com.ikmed.LearningManagement.model.CourseModel;
import org.apache.tomcat.util.http.parser.Authorization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class CourseMapper {
    public static final  CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    public abstract CourseModel formToModel(CourseForm form);

    public abstract CourseDTO ModelToDto(CourseModel model);

    public abstract Set<CourseDTO> setModelToSetDto(Set<CourseModel> models);

}
