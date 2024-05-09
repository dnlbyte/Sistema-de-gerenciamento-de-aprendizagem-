package com.ikmed.LearningManagement.mapper;
import com.ikmed.LearningManagement.DTO.ModuleDTO;
import com.ikmed.LearningManagement.form.ModuleForm;
import com.ikmed.LearningManagement.model.ModuleModel;
import org.apache.tomcat.util.http.parser.Authorization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class ModuleMapper {
    public static final  CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    public abstract ModuleModel formToModel(ModuleForm form);

    public abstract ModuleDTO ModelToDto(ModuleModel model);

    public abstract Set<ModuleDTO> setModelToSetDto(Set<ModuleModel> models);

}
