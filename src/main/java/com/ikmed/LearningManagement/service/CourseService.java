package com.ikmed.LearningManagement.service;

import com.ikmed.LearningManagement.DTO.CourseDTO;
import com.ikmed.LearningManagement.form.CourseForm;
import com.ikmed.LearningManagement.form.CourseUpdateForm;
import com.ikmed.LearningManagement.mapper.CourseMapper;
import com.ikmed.LearningManagement.model.CourseModel;
import com.ikmed.LearningManagement.repository.CourseRepository;
import com.ikmed.LearningManagement.service.exceptions.*;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository , CourseMapper courseMapper){
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public Set<CourseDTO> getAllCourses(){
        Set<CourseModel> courseModelSet = courseRepository.findByIsActiveTrue();

        if(courseModelSet.isEmpty()){
            throw new CourseNotFoundException("No active courses found");
        }
        return  CourseMapper.INSTANCE.setModelToSetDto(courseModelSet);
    }
    public CourseDTO getCourseByName(String name){
        CourseModel courseModel = findByNameAndIsActiveTrue(name);
        return CourseMapper.INSTANCE.ModelToDto(courseModel);
    }

    @Transactional
    public CourseDTO insertCourse(CourseForm courseForm){
        findByNameAndIsActiveTrue(courseForm.getName());
       if(isCourseExists(courseForm.getName())){
           throw new CourseAlreadyExistsException(String.format("Course already ‘%s’ exists", courseForm.getName()));
       }
        try{
            Date date = new Date();

            CourseModel courseModel = CourseMapper.INSTANCE.formToModel(courseForm);
            courseModel.setPrice(courseModel.getPrice());
            courseModel.setDiscount(courseModel.getDiscount());
            courseModel.setDescription(courseModel.getDescription());
            courseModel.setDutarion(courseModel.getDutarion());
            courseModel.setCreatedAt(date);
            courseModel.setUpdatedAt(date);
            courseModel.setActive(true);
            courseModel.setVersion(1);
            courseRepository.save(courseModel);
            return CourseMapper.INSTANCE.ModelToDto(courseModel);
        }catch (DataIntegrityViolationException err){
            throw new CourseInsertException(String.format("Fail to insert the  ‘%s’ course", courseForm.getName()));
        }
    }

    public CourseDTO updateCourse(String name, CourseUpdateForm courseUpdateForm){
        try{
            Date date = new Date();

            CourseModel courseModel = findByNameAndIsActiveTrue(name);

            courseModel.setPrice(courseModel.getPrice());
            courseModel.setDescription(courseModel.getDescription());
            courseModel.setDutarion(courseModel.getDutarion());
            courseModel.setDiscount(courseModel.getDiscount());
            courseModel.setStartAt(courseModel.getStartAt());
            courseModel.setUpdatedAt(date);
            courseRepository.save(courseModel);
            return CourseMapper.INSTANCE.ModelToDto(courseModel);
        }catch(DataIntegrityViolationException err){
            throw new CourseUpdateException(String.format("Fail to update the  ‘%s’ course", name));
        }
    }

    public void deleteCourse(String name){
        try{
            Date date = new Date();

            CourseModel courseModel = findByNameAndIsActiveTrue(name);
            courseModel.setActive(false);
            courseModel.setUpdatedAt(date);
            courseRepository.save(courseModel);
        }catch(DataIntegrityViolationException err){
            throw new RuntimeException("Fail to update the course");
        }
    }
    public Boolean isCourseExists(String name){
        try{
            courseRepository.findByNameAndIsActiveTrue(name);
            return true;
        }catch(ModuleNotFoundException err){
            return false;
        }
    }

    public CourseModel findByNameAndIsActiveTrue(String name){
        return courseRepository.findByNameAndIsActiveTrue(name).orElseThrow(() -> new CourseNotFoundException(String.format("The module ‘%s’ is already registered", name)));
    }
}
