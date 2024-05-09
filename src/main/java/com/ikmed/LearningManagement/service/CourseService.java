package com.ikmed.LearningManagement.service;

import com.ikmed.LearningManagement.DTO.CourseDTO;
import com.ikmed.LearningManagement.form.CourseForm;
import com.ikmed.LearningManagement.form.CourseUpdateForm;
import com.ikmed.LearningManagement.mapper.CourseMapper;
import com.ikmed.LearningManagement.model.CourseModel;
import com.ikmed.LearningManagement.repository.CourseRepository;
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
            throw new RuntimeException("No active courses found");
        }
        return  CourseMapper.INSTANCE.setModelToSetDto(courseModelSet);
    }
    public CourseDTO getCourseByName(String name){
        CourseModel courseModel = findByNameAndIsActiveTrue(name);
        return CourseMapper.INSTANCE.ModelToDto(courseModel);
    }
    public CourseModel findByNameAndIsActiveTrue(String name){
        return courseRepository.findByNameAndIsActiveTrue(name).orElseThrow( () -> new RuntimeException("Faild to recive model"));
    }

    @Transactional
    public CourseDTO insertCourse(CourseForm courseForm){
        Optional<CourseModel> courseModelVerify = courseRepository.findByNameAndIsActiveTrue(courseForm.getName());
       if(courseModelVerify.isPresent()){
           throw new RuntimeException("Course already exists");
       }
        try{
            Date date = new Date();

            CourseModel courseModel = CourseMapper.INSTANCE.formToModel(courseForm);
            courseModel.setCreatedAt(date);
            courseModel.setUpdatedAt(date);
            courseModel.setActive(true);
            courseModel.setVersion(1);
            courseRepository.save(courseModel);
            return CourseMapper.INSTANCE.ModelToDto(courseModel);
        }catch (DataIntegrityViolationException err){
            throw new RuntimeException("fail to register the Course");
        }
    }

    public CourseDTO updateCourse(String name, CourseUpdateForm courseUpdateForm){
        try{
            CourseModel courseModel = findByNameAndIsActiveTrue(name);

            courseModel.setPrice(courseUpdateForm.getPrice());
            courseModel.setDescription(courseUpdateForm.getDescription());
            courseModel.setDutarion(courseUpdateForm.getDuration());
            courseModel.setDiscount(courseUpdateForm.getDiscount());
            courseModel.setStartAt(courseUpdateForm.getStartAt());

            return CourseMapper.INSTANCE.ModelToDto(courseModel);
        }catch(DataIntegrityViolationException err){
            throw new RuntimeException("Fail to update the course");
        }
    }

    public void deleteCourse(String subject){
        try{
            Date date = new Date();

            CourseModel courseModel = findByNameAndIsActiveTrue(subject);
            courseModel.setActive(false);
            courseModel.setUpdatedAt(date);
            courseRepository.save(courseModel);
        }catch(DataIntegrityViolationException err){
            throw new RuntimeException("Fail to update the course");
        }
    }

}
