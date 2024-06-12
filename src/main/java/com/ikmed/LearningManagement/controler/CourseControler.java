package com.ikmed.LearningManagement.controler;

import com.ikmed.LearningManagement.DTO.CourseDTO;
import com.ikmed.LearningManagement.form.CourseForm;
import com.ikmed.LearningManagement.form.CourseUpdateForm;
import com.ikmed.LearningManagement.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
@RestController
@RequestMapping("/course")
public class CourseControler {

    private final CourseService courseService;

    public CourseControler(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<Set<CourseDTO>> getAllCourses(){
        Set<CourseDTO> courseDTOS = courseService.getAllCourses();

        return  ResponseEntity.ok().body(courseDTOS);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CourseDTO> getCourseByName(@PathVariable("name") String name){
        CourseDTO courseDTO = courseService.getCourseByName(name);
        return ResponseEntity.ok().body(courseDTO);
    }


    @PutMapping("/{name}")
    public ResponseEntity<CourseDTO> updateCourse(
            @PathVariable("name") String name,
            @Valid @RequestBody CourseUpdateForm courseUpdateForm
    ){
        CourseDTO courseDTO = courseService.updateCourse(name, courseUpdateForm);
        return ResponseEntity.ok().body(courseDTO);
    }

    @PostMapping("/{name}")
    public ResponseEntity<CourseDTO> insertCourse(CourseForm courseForm){
        CourseDTO courseDTO = courseService.insertCourse(courseForm);
       return ResponseEntity.ok().body(courseDTO);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<CourseDTO> deleteCourse( @PathVariable("name") String name){
        courseService.deleteCourse(name);
        return ResponseEntity.noContent().build();
    }

}
