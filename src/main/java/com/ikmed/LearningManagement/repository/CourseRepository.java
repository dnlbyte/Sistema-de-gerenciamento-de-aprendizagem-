package com.ikmed.LearningManagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ikmed.LearningManagement.model.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CourseRepository  extends JpaRepository<CourseModel, UUID>{
    Optional<CourseModel> findByNameAndIsActiveTrue(String name);

    Set<CourseModel> findByIsActiveTrue();
}
